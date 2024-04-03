package com.cliv_git.vilp

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val root = FileNode("root", FileType.DIR).apply {
        addChild(FileNode("file.txt"))
        addChild(FileNode("dir1", FileType.DIR).apply {
            addChild(FileNode("dir2", FileType.DIR))
            addChild(FileNode("dir3", FileType.DIR).apply {
                addChild(FileNode("file2.txt"))
            })
        })
    }
    root.makeGraph()
}
