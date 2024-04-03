package com.cliv_git.vilp

import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.createDirectory

enum class FileType{
    FILE,
    DIR
}

data class FileNode(
    var name: String,
    val ft: FileType = FileType.FILE,
    private var children: MutableList<FileNode>? = null
) {
    init {
        if (ft == FileType.DIR) {
            children = mutableListOf()
        }
    }
    fun addChild(child: FileNode) {
        assert(ft == FileType.DIR)
        children!!.add(child)
    }

    fun makeGraph(parent: String = "") {
        if (ft == FileType.DIR) {
            val curDir = Path(parent).resolve(name)
            if (children!!.isEmpty()) {
                curDir.runCatching {
                    createDirectory()
                }.onFailure {
                    println("$curDir already exists! I'm going to skip it")
                }
            } else {
                curDir.runCatching {
                    createDirectory()
                }.onFailure {
                    println("$curDir already exists! I'm going to skip it.")
                }
                for (child in children!!) {
                    child.makeGraph(curDir.toString())
                }
            }
        } else {
            val curFile = Path(parent).resolve(name).toString()
            File(curFile).createNewFile().also {
                if (!it) println(
                    "$curFile already exists! I'm going to skip it."
                )
            }
        }
    }
}
