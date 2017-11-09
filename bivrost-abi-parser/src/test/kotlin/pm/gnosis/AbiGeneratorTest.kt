package pm.gnosis

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

class AbiGeneratorTest {
    @Rule
    @JvmField
    val folder = TemporaryFolder()

    private lateinit var generatedFolder: File

    companion object {
        const val PACKAGE_NAME = "expected"
        val PATH = PACKAGE_NAME.replace('.', File.separatorChar)
    }

    @Before
    fun setup() {
        generatedFolder = folder.newFolder("testGeneratedCode")
    }

    @Test
    fun testGeneratedCode() {
        val testsFolder = File(javaClass.classLoader.getResource("automatic_tests").toURI())
        for (testFolder in testsFolder.listFiles().sortedBy { it.name }) {
            testAbi(testFolder)
        }
    }

    private fun testAbi(testFolder: File) {
        println("Testing ${testFolder.nameWithoutExtension}")

        generatedFolder.deleteRecursively()
        val abisFolder = File(testFolder, "abis")

        val arraysMap = AbiParser.ArraysMap(PACKAGE_NAME)
        abisFolder.listFiles().forEach { jsonAbi ->
            val jsonAbiContents = readAllFromFile(jsonAbi)
            AbiParser.generateWrapper(PACKAGE_NAME, jsonAbiContents, generatedFolder, arraysMap)
        }

        arraysMap.generate(generatedFolder)

        val generatedRootFolder = File(generatedFolder, PATH)
        assertTrue("Root folder was not generated", generatedRootFolder.exists() && generatedRootFolder.isDirectory)

        val expectedFolder = File(testFolder, PATH)
        checkGeneratedFolder(generatedRootFolder, expectedFolder)
        checkGeneratedFileList(generatedRootFolder, expectedFolder)
    }

    private fun checkGeneratedFileList(generatedFolder: File, expectedFolder: File) {
        expectedFolder.listFiles().forEach {
            val generated = File(generatedFolder, it.name)
            assertTrue("$it was not generated!", generated.exists() &&
                    generated.isDirectory == it.isDirectory && generated.isFile == it.isFile)
            if (it.isDirectory) {
                checkGeneratedFileList(generated, it)
            }
        }
    }

    private fun checkGeneratedFolder(generatedFolder: File, expectedFolder: File) {
        generatedFolder.listFiles().forEach {
            if (it.isDirectory) {
                val target = File(expectedFolder, it.name)
                assertTrue("$it was not expected as a generated folder!", target.exists() && target.isDirectory)
                checkGeneratedFolder(it, target)
            } else {
                checkGeneratedFile(it, expectedFolder)
            }
        }
    }

    private fun checkGeneratedFile(generatedFile: File, expectedFolder: File) {
        val expectedFile = File(expectedFolder, generatedFile.name)
        assertTrue("$generatedFile was not expected to be generated!", expectedFile.exists() && expectedFile.isFile)

        val generatedContent = readAllFromFile(generatedFile)
        val expectedContent = readAllFromFile(expectedFile)

        assertEquals("${generatedFile.name} does not match the expected file",
                expectedContent, generatedContent)
    }

    private fun readAllFromFile(file: File) = file.bufferedReader().use { it.readText() }
}
