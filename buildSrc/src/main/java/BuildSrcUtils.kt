import org.gradle.api.Project
import org.gradle.api.plugins.JavaApplication
import java.io.File

object BuildSrcUtils {
    @JvmStatic
    fun runDex(proj: Project, name: String) {
        val dexFile = File(proj.buildDir, "libs/$name.dex")

        proj.exec {
            it.commandLine("adb", "push", dexFile.canonicalPath, "/data/local/tmp/")
        }

        val application = proj.extensions.getByName("application") as JavaApplication
        proj.exec {
            it.commandLine(
                    "adb", "shell",
                    "app_process",
                    "-Djava.class.path=/data/local/tmp/$name.dex", "/system/bin",
                    application.mainClassName
            )
        }
    }

    @JvmStatic
    fun registerTasks(proj: Project, fileName: String) {

        val dex = proj.tasks.register("dex", DexTask::class.java) {
            it.fileName.set(fileName)
            it.dependsOn(proj.tasks.getByName("shadowJar"))
        }

        proj.tasks.register("runDex") {
            it.description = "通过 adb 运行 dex 文件。"
            it.dependsOn(dex)

            it.doLast {
                runDex(proj, fileName)
            }
        }
    }
}