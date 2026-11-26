package com.github.kusa233.ovatoxin.samples

import com.github.kusa233.ovatoxin.samples.full.ComponentsSample

object Entrypoint {
    @JvmStatic
    fun main(inputArgs: Array<String>) {
        val args: MutableMap<String, String> = mutableMapOf()
        for (arg in inputArgs) {
            args[arg.substring(0, arg.indexOf("="))] = arg.substring(arg.indexOf("=") + 1)
        }
        when (args["-Dtarget"]) {
            "components" -> ComponentsSample.main(inputArgs)
            else -> ComponentsSample.main(inputArgs)
        }
    }
}