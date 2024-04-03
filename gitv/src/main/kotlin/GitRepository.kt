package com.cliv_git.vilp

import java.io.File
import java.nio.file.Path
import kotlin.io.path.*


class GitRepository(path: String, force: Boolean = false) {
    val worktree = Path(path)
    val gitDir: Path = Path(path).resolve(".git")
    private val iniParser: INIParser = INIParser()

    init {
        if (!(force or gitDir.isDirectory())) {
            throw Exception("Not a valid Git repository $path")
        }

        val cf = gitDir.resolve("config")
        if (cf.exists()) {
            iniParser.parse(cf.absolutePathString())
        } else if (!force) {
            throw Exception("Configuration file is missing!")
        }

        if (!force) {
            val version: Int = iniParser["core"]["repositoryformatversion"]?.toInt() ?: throw Exception(
                "Version number in config isn't a valid integer!"
            )
            if (version != 0) {
                throw Exception("Unsupported repositoryformatversion $version")
            }
        }
    }
}

fun createDir(path: Path) {
    if (!path.exists()) {
        path.createDirectory()
    }
}

fun createGitRepository(path: String): GitRepository {
    val repo = GitRepository(path, true)

    // First, we make sure the path either doesn't exist or is an empty dir
    if (repo.worktree.exists()) {
        if (!repo.worktree.isDirectory())
            throw Exception("$path is not a directory!")
        if (repo.gitDir.exists() and repo.gitDir.listDirectoryEntries().isEmpty())
            throw Exception("${repo.gitDir} is not empty!")
    } else
        repo.worktree.createDirectory()

    createDir(repo.gitDir)
    createDir(repo.gitDir.resolve("branches"))
    createDir(repo.gitDir.resolve("objects"))
    createDir(repo.gitDir.resolve("refs"))
    createDir(repo.gitDir.resolve("refs").resolve("tags"))
    createDir(repo.gitDir.resolve("refs").resolve("heads"))

    with(File(repo.gitDir.resolve("description").toString())) {
        writeText(
            "Unnamed repository; edit this file 'description' to name the repository.\n"
        )
    }

    with(File(repo.gitDir.resolve("HEAD").toString())) {
        writeText(
            "ref: refs/heads/master\n"
        )
    }

    with(File(repo.gitDir.resolve("config").toString())) {
        val config = INIParser()
        config.addSection("core")
        config["core"] = Pair("repositoryformatversion", "0")
        config["core"] = Pair("filemode", "false")
        config["core"] = Pair("bare", "false")
        config.write(this)
    }

    return repo
}