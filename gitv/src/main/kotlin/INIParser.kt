package com.cliv_git.vilp

import java.io.File

class INIParser {
    private val information: MutableMap<String, MutableMap<String, String>> = mutableMapOf()
    fun parse(path: String) {
        val content = File(path).readLines()
        var lastSection = ""
        content.filter { it != "\n" }.forEach {
            if (it.startsWith("[")) {
                lastSection = it.substring(1,it.length-1)
                information[lastSection] = mutableMapOf()
            } else {
                var k = ""
                var v = ""
                try {
                    k = it.split("=")[0].filter { c -> !c.isWhitespace() }
                    v = it.split("=")[1].filter { c -> !c.isWhitespace() }
                } catch (e: IndexOutOfBoundsException) {
                    println(it)
                }
                if (information.containsKey(lastSection)) information[lastSection]!![k] = v
                else throw Exception("Config file isn't well formated.")
            }
        }
    }

    operator fun get(s: String): Map<String, String> {
        return information[s] ?: throw Exception("Key $s doesn't exists in config file.")
    }

    operator fun set(section: String, v: Pair<String, String>) {
        if (information.containsKey(section)) {
            information[section]!![v.first] = v.second
        } else
            throw Exception("Section $section doesn't exists. Use `addSection` method to add it first!")
    }

    fun addSection(section: String) { information[section] = mutableMapOf() }
    fun write(file: File) {
        val sb = StringBuilder()
        information.forEach { (section, entry) ->
            sb.append("[$section]\n")
            entry.forEach { (k, v) ->
                sb.append("$k=$v\n")
            }
            sb.append("\n")
        }
        file.writeText(sb.toString())
    }
}