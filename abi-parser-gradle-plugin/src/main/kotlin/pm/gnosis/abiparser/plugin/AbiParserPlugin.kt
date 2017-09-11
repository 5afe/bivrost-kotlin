package pm.gnosis.abiparser.plugin

import com.android.build.gradle.*
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.DomainObjectSet
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionContainer
import pm.gnosis.AbiParser
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.reflect.KClass


class AbiParserPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        println("Plugin Project: " + project.toString())
        project.plugins.all {
            when (it) {
                is FeaturePlugin -> {
                    project.extensions[FeatureExtension::class].run {
                        configureCodeGeneration(project, featureVariants)
                        configureCodeGeneration(project, libraryVariants)
                    }
                }
                is LibraryPlugin -> {
                    project.extensions[LibraryExtension::class].run {
                        configureCodeGeneration(project, libraryVariants)
                    }
                }
                is AppPlugin -> {
                    project.extensions[AppExtension::class].run {
                        configureCodeGeneration(project, applicationVariants)
                    }
                }
            }
        }
    }

    private fun configureCodeGeneration(project: Project, variants: DomainObjectSet<out BaseVariant>) {
        variants.all { variant ->
            val outputDir = project.buildDir.resolve(
                    "generated/source/abi/${variant.dirName}")

            val task = project.tasks.create("pm.gnosis.generate${variant.name.capitalize()}AbiWrapper")
            task.outputs.dir(outputDir)
            variant.registerJavaGeneratingTask(task, outputDir)

            val once = AtomicBoolean()
            variant.outputs.all { output ->

                val processResources = output.processResources
                task.dependsOn(processResources)

                // Though there might be multiple outputs, their R files are all the same. Thus, we only
                // need to configure the task once with the R.java input and action.
                if (once.compareAndSet(false, true)) {
                    val abiFolder = project.projectDir.resolve("abi")

                    task.apply {
                        inputs.files(abiFolder.listFiles())

                        doLast {
                            abiFolder.listFiles().forEach {
                                println("Generating wrapper for <$it>")
                                AbiParser.generateWrapper("pm.gnosis.abiparser", it.readText(), outputDir)
                            }

                        }
                    }
                }
            }
        }
    }

    private operator fun <T : Any> ExtensionContainer.get(type: KClass<T>): T {
        return getByType(type.java)!!
    }
}