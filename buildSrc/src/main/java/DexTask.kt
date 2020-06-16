import com.android.tools.r8.D8
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.work.InputChanges
import java.io.File

abstract class DexTask : DefaultTask() {
    @get:InputFile
    val inputFile: File by lazy {
        File(project.buildDir, "libs/${fileName.get()}.jar")
    }

    @get:OutputFile
    val outputFile: File by lazy {
        File(project.buildDir, "libs/${fileName.get()}.dex")
    }

    @get:Input
    abstract val fileName: Property<String>

    @TaskAction
    fun action(_inputChanges: InputChanges) {
        val file = inputFile

        D8.main(arrayOf(
                "--no-desugaring",
                "--output", file.parentFile.canonicalPath,
                file.canonicalPath
        ))

        val dexFile = File(project.buildDir, "libs/classes.dex")
        dexFile.renameTo(outputFile)
    }
}