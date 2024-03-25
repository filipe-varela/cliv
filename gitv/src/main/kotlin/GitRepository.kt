package com.cliv_git.vilp

import java.io.File
import java.nio.file.Path
import kotlin.io.path.*

class GitRepository(path: String, force: Boolean = false) {
    val worktree = Path(path)
    val gitDir = Path(path).resolve(".git")
    val iniParser: INIParser = INIParser()

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

fun create(path: String): GitRepository {
    val repo = GitRepository(path, true)

    // First, we make sure the path either doesn't exist or is an empty dir
    if (repo.worktree.exists()) {
        if (!repo.worktree.isDirectory())
            throw Exception("$path is not a directory!")
        if (repo.gitDir.exists() and repo.gitDir.listDirectoryEntries().isEmpty())
            throw Exception("${repo.gitDir} is not empty!")
    } else
        repo.worktree.createDirectory()

    repo.gitDir.resolve("branches").createDirectory()
    repo.gitDir.resolve("objects").createDirectory()
    repo.gitDir.resolve("refs").createDirectory()
    repo.gitDir.resolve("refs").resolve("tags").createDirectory()
    repo.gitDir.resolve("refs").resolve("heads").createDirectory()

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
        config.addSection("config")
        config["core"] = Pair("repositoryformatversion", "0")
        config["core"] = Pair("filemode", "false")
        config["core"] = Pair("bare", "false")
        config.write(this)
    }

    return repo
}