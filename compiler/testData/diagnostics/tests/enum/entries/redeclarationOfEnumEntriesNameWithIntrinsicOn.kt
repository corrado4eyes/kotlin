// !LANGUAGE: +EnumEntries
// WITH_STDLIB

enum class E {
    <!DEPRECATED_DECLARATION_OF_ENUM_ENTRY!>entries,<!> Entries;

    fun foo() {
        entries.ordinal
        E.entries.ordinal
    }
}
