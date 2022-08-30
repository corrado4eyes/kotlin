/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.generators.arguments

import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.utils.Printer
import java.io.File

internal fun generateJsMainFunctionExecutionMode(
    apiDir: File,
    filePrinter: (targetFile: File, Printer.() -> Unit) -> Unit
) {
    val modeFqName = FqName("org.jetbrains.kotlin.gradle.dsl.JsMainFunctionExecutionMode")
    filePrinter(file(apiDir, modeFqName)) {
        generateDeclaration("enum class", modeFqName, afterType = "(val mode: String)") {
            val modes = hashMapOf(
                K2JsArgumentConstants::CALL.name to K2JsArgumentConstants.CALL,
                K2JsArgumentConstants::NO_CALL.name to K2JsArgumentConstants.NO_CALL
            )

            val lastIndex = modes.size - 1
            modes.entries.forEachIndexed { index, mode ->
                val lastChar = if (index == lastIndex) ";" else ","
                println("${mode.key}(\"${mode.value}\")$lastChar")
            }

            println()
            println("companion object {")
            withIndent {
                println("fun fromMode(mode: String): JsMainFunctionExecutionMode =")
                println("    JsMainFunctionExecutionMode.values().firstOrNull { it.mode == mode }")
                println("        ?: throw IllegalArgumentException(\"Unknown main function execution mode: ${'$'}mode\")")
            }
            println("}")
        }
    }
}

internal fun generateJsModuleKind(
    apiDir: File,
    filePrinter: (targetFile: File, Printer.() -> Unit) -> Unit
) {
    val jsModuleKindFqName = FqName("org.jetbrains.kotlin.gradle.dsl.JsModuleKind")
    filePrinter(file(apiDir, jsModuleKindFqName)) {
        generateDeclaration("enum class", jsModuleKindFqName, afterType = "(val kind: String)") {
            val kinds = hashMapOf(
                K2JsArgumentConstants::MODULE_PLAIN.name to K2JsArgumentConstants.MODULE_PLAIN,
                K2JsArgumentConstants::MODULE_AMD.name to K2JsArgumentConstants.MODULE_AMD,
                K2JsArgumentConstants::MODULE_COMMONJS.name to K2JsArgumentConstants.MODULE_COMMONJS,
                K2JsArgumentConstants::MODULE_UMD.name to K2JsArgumentConstants.MODULE_UMD,
                K2JsArgumentConstants::MODULE_ES.name to K2JsArgumentConstants.MODULE_ES
            )

            val lastIndex = kinds.size - 1
            kinds.entries.forEachIndexed { index, mode ->
                val lastChar = if (index == lastIndex) ";" else ","
                println("${mode.key}(\"${mode.value}\")$lastChar")
            }

            println()
            println("companion object {")
            withIndent {
                println("fun fromKind(kind: String): JsModuleKind =")
                println("    JsModuleKind.values().firstOrNull { it.kind == kind }")
                println("        ?: throw IllegalArgumentException(\"Unknown JS module kind: ${'$'}kind\")")
            }
            println("}")
        }
    }
}
