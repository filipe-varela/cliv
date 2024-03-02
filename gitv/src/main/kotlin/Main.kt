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


////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//fun main() {
//    val name = "Kotlin"
//    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
//    // to see how IntelliJ IDEA suggests fixing it.
//    println("Hello, " + name + "!")
//
//    for (i in 1..5) {
//        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
//        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
//        println("i = $i")
//    }
//}