package com.cliv_git.vilp

fun processGitCommands(args: MutableList<String>) {
    args.removeAt(0)
    if (args.isEmpty()) {
        println("No git command found!")
        return
    }
    when (args[0]) {
        "init" -> println("git init")
        "add" -> println("git add")
        "commit" -> println("git commit")
        "cat-file" -> println("git cat-file")
        "check-ignore" -> println("git check-ignore")
        "checkout" -> println("git checkout")
        "hash-object" -> println("git hash-object")
        "log" -> println("git log")
        "ls-files" -> println("git ls-files")
        "ls-tree" -> println("git ls-tree")
        "rev-parser" -> println("git rev-parser")
        "rm" -> println("git rm")
        "show-ref" -> println("git show-ref")
        "status" -> println("git status")
        "tag" -> println("git tag")
        else -> println("No git command found!")
    }
}
