package com.cliv_git.vilp

import java.io.File

class INIParser {
    private val information: MutableMap<String, MutableMap<String, String>> = mutableMapOf()
    fun parse(path: String) {

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