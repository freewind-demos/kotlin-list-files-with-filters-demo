package example

import java.io.File

private val requiredSuffixes = listOf("kt", "xml", "md", "gradle")
private val excludedDirs = listOf(".idea", ".gradle", "out", "build")
fun main(args: Array<String>) {
    File(".").walkTopDown().filter { file ->
        file.isFile && !inExcludedDirs(file) && hasRequiredSuffix(file)
    }.forEach {
        println(it)
    }
}

fun hasRequiredSuffix(file: File): Boolean {
    return requiredSuffixes.contains(file.extension)
}

fun inExcludedDirs(file: File): Boolean {
    var current: File? = file.parentFile
    while (current != null) {
        if (excludedDirs.contains(current.name)) {
            return true
        }
        current = current.parentFile
    }
    return false
}
