package pm.gnosis.plugin

import com.android.build.gradle.*
import com.android.build.gradle.api.BaseVariant
import org.apache.log4j.Logger
import org.gradle.api.DomainObjectSet
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.tasks.SourceSetContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import pm.gnosis.AbiParser
import java.io.File
import kotlin.reflect.KClass

open class BivrostTaskExtension {
    var packageName: String? = null
}

class BivrostPlugin : Plugin<Project> {

    private val logger = Logger.getLogger(BivrostPlugin::class.java)

    override fun apply(project: Project) {
        logger.debug("Plugin Project: $project")
        project.plugins.all {
            try {
                when (it) {
                    is FeaturePlugin -> {
                        project.extensions[FeatureExtension::class].run {
                            configureCodeGenerationAndroid(project, featureVariants)
                            configureCodeGenerationAndroid(project, libraryVariants)
                        }
                    }
                    is LibraryPlugin -> {
                        project.extensions[LibraryExtension::class].run {
                            configureCodeGenerationAndroid(project, libraryVariants)
                        }
                    }
                    is AppPlugin -> {
                        logger.debug("Found AppPlugin")
                        project.extensions[AppExtension::class].run {
                            configureCodeGenerationAndroid(project, applicationVariants)
                        }
                    }
                    is KotlinPluginWrapper -> {
                        logger.debug("Found kotlin")
                        configureCodeGenerationKotlin(project)
                    }
                }
            } catch (e: ClassNotFoundException) {
                logger.debug("Could not configure code generation", e)
            }
        }
    }

    private fun configureCodeGenerationKotlin(project: Project) {
        val extension = project.extensions.create("bivrostTask", BivrostTaskExtension::class.java)
        val outputDir = project.buildDir.resolve("generated/source/abi/main")
        project.extensions[SourceSetContainer::class].findByName("main")?.apply {
            java.srcDir(outputDir)
        }
        val task = createCodeGenTask(project, "pm.gnosis.generate.generateMainApiWrapper", outputDir) {
            extension.packageName ?: throw IllegalArgumentException("Please specific a package name in bivrostTask")
        }
        project.tasks.getByName("build").dependsOn(task)
    }

    private fun configureCodeGenerationAndroid(project: Project, variants: DomainObjectSet<out BaseVariant>) {
        variants.all { variant ->
            val outputDir = project.buildDir.resolve("generated/source/abi/${variant.dirName}")
            val task = createCodeGenTask(
                project,
                "pm.gnosis.generate${variant.name.capitalize()}AbiWrapper",
                outputDir
            ) { variant.applicationId.removeSuffix(variant.buildType.applicationIdSuffix ?: "") }
            variant.registerJavaGeneratingTask(task, outputDir)

            variant.outputs.all { output ->
                val processResources = output.processResources
                task.dependsOn(processResources)
            }
        }
    }

    private fun createCodeGenTask(project: Project, taskName: String, outputDir: File, packageNameFn: () -> String): Task {
        val task = project.tasks.create(taskName)
        task.outputs.dir(outputDir)

        val abiFolder = project.projectDir.resolve("abi")

        return task.apply {
            inputs.files(abiFolder.listFiles())

            doLast {
                val packageName = packageNameFn()
                // We can reuse arrays for all ABIs
                val arraysMap = AbiParser.ArraysMap(packageName)

                abiFolder.listFiles()?.forEach {
                    println("Generating wrapper for <$it>")
                    AbiParser.generateWrapper(packageName, it.readText(), outputDir, arraysMap)
                }

                arraysMap.generate(outputDir)
            }
        }
    }

    private operator fun <T : Any> ExtensionContainer.get(type: KClass<T>): T {
        return getByType(type.java)
    }
}