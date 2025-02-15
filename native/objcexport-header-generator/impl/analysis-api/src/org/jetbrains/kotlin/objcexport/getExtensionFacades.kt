package org.jetbrains.kotlin.objcexport

import org.jetbrains.kotlin.analysis.api.KtAnalysisSession
import org.jetbrains.kotlin.analysis.api.symbols.KtFileSymbol
import org.jetbrains.kotlin.backend.konan.objcexport.ObjCInterface
import org.jetbrains.kotlin.backend.konan.objcexport.ObjCInterfaceImpl

private const val extensionsCategoryName = "Extensions"

internal val ObjCInterface.isExtensionsFacade: Boolean
    get() = this.categoryName == extensionsCategoryName

/**
 * Translates extension functions/properties inside the given [this] file as a single [ObjCInterface]
 * with category [extensionsCategoryName]
 *
 * Later interface should be forwarded using [isExtensionsFacade]
 *
 * ## example:
 * given a file "Foo.kt"
 *
 * ```kotlin
 *
 * fun Foo.func() = 42
 *
 * val Foo.prop get() = 42
 *
 * class Foo {
 *
 * }
 *
 * ```
 *
 * This will be exporting two Interfaces with forwarded class:
 *
 * ```
 * @class Foo
 *
 * @interface Foo: Base
 *
 * @interface Foo (Extensions)
 *      - func
 *      - prop
 * ```
 *
 * Where `Foo` would be the "top level interface file extensions facade" returned by this function.
 *
 * See related [getTopLevelFacade]
 */
context(KtAnalysisSession, KtObjCExportSession)
fun KtFileSymbol.getExtensionFacades(): List<ObjCInterface> {

    val extensions = getFileScope()
        .getCallableSymbols().filter { it.isExtension }
        .toList()
        .sortedWith(StableCallableOrder)
        .ifEmpty { return emptyList() }
        .groupBy {
            val classSymbol = it.receiverParameter?.type?.expandedClassSymbol
            classSymbol?.getObjCClassOrProtocolName()?.objCName
        }
        .mapNotNull { (key, value) ->
            if (key == null) return@mapNotNull null else key to value
        }

    return extensions.map { (objCName, extensionSymbols) ->
        ObjCInterfaceImpl(
            name = objCName,
            comment = null,
            origin = null,
            attributes = emptyList(),
            superProtocols = emptyList(),
            members = extensionSymbols.mapNotNull { ext -> ext.translateToObjCExportStub() },
            categoryName = extensionsCategoryName,
            generics = emptyList(),
            superClass = null,
            superClassGenerics = emptyList()
        )
    }
}