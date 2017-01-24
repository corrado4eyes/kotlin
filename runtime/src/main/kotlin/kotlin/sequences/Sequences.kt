package kotlin.sequences

import kotlin.comparisons.*

/**
 * Returns `true` if [element] is found in the sequence.
 */
public operator fun <@kotlin.internal.OnlyInputTypes T> Sequence<T>.contains(element: T): Boolean {
    return indexOf(element) >= 0
}

/**
 * Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this sequence.
 */
public fun <T> Sequence<T>.elementAt(index: Int): T {
    return elementAtOrElse(index) { throw IndexOutOfBoundsException("Sequence doesn't contain element at index $index.") }
}

/**
 * Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this sequence.
 */
public fun <T> Sequence<T>.elementAtOrElse(index: Int, defaultValue: (Int) -> T): T {
    if (index < 0)
        return defaultValue(index)
    val iterator = iterator()
    var count = 0
    while (iterator.hasNext()) {
        val element = iterator.next()
        if (index == count++)
            return element
    }
    return defaultValue(index)
}

/**
 * Returns an element at the given [index] or `null` if the [index] is out of bounds of this sequence.
 */
public fun <T> Sequence<T>.elementAtOrNull(index: Int): T? {
    if (index < 0)
        return null
    val iterator = iterator()
    var count = 0
    while (iterator.hasNext()) {
        val element = iterator.next()
        if (index == count++)
            return element
    }
    return null
}

/**
 * Returns the first element matching the given [predicate], or `null` if no such element was found.
 */
@kotlin.internal.InlineOnly
public inline fun <T> Sequence<T>.find(predicate: (T) -> Boolean): T? {
    return firstOrNull(predicate)
}

/**
 * Returns the last element matching the given [predicate], or `null` if no such element was found.
 */
@kotlin.internal.InlineOnly
public inline fun <T> Sequence<T>.findLast(predicate: (T) -> Boolean): T? {
    return lastOrNull(predicate)
}

/**
 * Returns first element.
 * @throws [NoSuchElementException] if the sequence is empty.
 */
public fun <T> Sequence<T>.first(): T {
    val iterator = iterator()
    if (!iterator.hasNext())
        throw NoSuchElementException("Sequence is empty.")
    return iterator.next()
}

/**
 * Returns the first element matching the given [predicate].
 * @throws [NoSuchElementException] if no such element is found.
 */
public inline fun <T> Sequence<T>.first(predicate: (T) -> Boolean): T {
    for (element in this) if (predicate(element)) return element
    throw NoSuchElementException("Sequence contains no element matching the predicate.")
}

/**
 * Returns the first element, or `null` if the sequence is empty.
 */
public fun <T> Sequence<T>.firstOrNull(): T? {
    val iterator = iterator()
    if (!iterator.hasNext())
        return null
    return iterator.next()
}

/**
 * Returns the first element matching the given [predicate], or `null` if element was not found.
 */
public inline fun <T> Sequence<T>.firstOrNull(predicate: (T) -> Boolean): T? {
    for (element in this) if (predicate(element)) return element
    return null
}

/**
 * Returns first index of [element], or -1 if the sequence does not contain element.
 */
public fun <@kotlin.internal.OnlyInputTypes T> Sequence<T>.indexOf(element: T): Int {
    var index = 0
    for (item in this) {
        if (element == item)
            return index
        index++
    }
    return -1
}

/**
 * Returns index of the first element matching the given [predicate], or -1 if the sequence does not contain such element.
 */
public inline fun <T> Sequence<T>.indexOfFirst(predicate: (T) -> Boolean): Int {
    var index = 0
    for (item in this) {
        if (predicate(item))
            return index
        index++
    }
    return -1
}

/**
 * Returns index of the last element matching the given [predicate], or -1 if the sequence does not contain such element.
 */
public inline fun <T> Sequence<T>.indexOfLast(predicate: (T) -> Boolean): Int {
    var lastIndex = -1
    var index = 0
    for (item in this) {
        if (predicate(item))
            lastIndex = index
        index++
    }
    return lastIndex
}

/**
 * Returns the last element.
 * @throws [NoSuchElementException] if the sequence is empty.
 */
public fun <T> Sequence<T>.last(): T {
    val iterator = iterator()
    if (!iterator.hasNext())
        throw NoSuchElementException("Sequence is empty.")
    var last = iterator.next()
    while (iterator.hasNext())
        last = iterator.next()
    return last
}

/**
 * Returns the last element matching the given [predicate].
 * @throws [NoSuchElementException] if no such element is found.
 */
public inline fun <T> Sequence<T>.last(predicate: (T) -> Boolean): T {
    var last: T? = null
    var found = false
    for (element in this) {
        if (predicate(element)) {
            last = element
            found = true
        }
    }
    if (!found) throw NoSuchElementException("Sequence contains no element matching the predicate.")
    return last as T
}

/**
 * Returns last index of [element], or -1 if the sequence does not contain element.
 */
public fun <@kotlin.internal.OnlyInputTypes T> Sequence<T>.lastIndexOf(element: T): Int {
    var lastIndex = -1
    var index = 0
    for (item in this) {
        if (element == item)
            lastIndex = index
        index++
    }
    return lastIndex
}

/**
 * Returns the last element, or `null` if the sequence is empty.
 */
public fun <T> Sequence<T>.lastOrNull(): T? {
    val iterator = iterator()
    if (!iterator.hasNext())
        return null
    var last = iterator.next()
    while (iterator.hasNext())
        last = iterator.next()
    return last
}

/**
 * Returns the last element matching the given [predicate], or `null` if no such element was found.
 */
public inline fun <T> Sequence<T>.lastOrNull(predicate: (T) -> Boolean): T? {
    var last: T? = null
    for (element in this) {
        if (predicate(element)) {
            last = element
        }
    }
    return last
}

/**
 * Returns the single element, or throws an exception if the sequence is empty or has more than one element.
 */
public fun <T> Sequence<T>.single(): T {
    val iterator = iterator()
    if (!iterator.hasNext())
        throw NoSuchElementException("Sequence is empty.")
    val single = iterator.next()
    if (iterator.hasNext())
        throw IllegalArgumentException("Sequence has more than one element.")
    return single
}

/**
 * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
 */
public inline fun <T> Sequence<T>.single(predicate: (T) -> Boolean): T {
    var single: T? = null
    var found = false
    for (element in this) {
        if (predicate(element)) {
            if (found) throw IllegalArgumentException("Sequence contains more than one matching element.")
            single = element
            found = true
        }
    }
    if (!found) throw NoSuchElementException("Sequence contains no element matching the predicate.")
    return single as T
}

/**
 * Returns single element, or `null` if the sequence is empty or has more than one element.
 */
public fun <T> Sequence<T>.singleOrNull(): T? {
    val iterator = iterator()
    if (!iterator.hasNext())
        return null
    val single = iterator.next()
    if (iterator.hasNext())
        return null
    return single
}

/**
 * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
 */
public inline fun <T> Sequence<T>.singleOrNull(predicate: (T) -> Boolean): T? {
    var single: T? = null
    var found = false
    for (element in this) {
        if (predicate(element)) {
            if (found) return null
            single = element
            found = true
        }
    }
    if (!found) return null
    return single
}

/**
 * Returns a sequence containing all elements except first [n] elements.
 */
@FixmeSequences
public fun <T> Sequence<T>.drop(n: Int): Sequence<T> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    TODO()
    //return when {
    //   n == 0 -> this
    //   this is DropTakeSequence -> this.drop(n)
    //     else -> DropSequence(this, n)
    //}
}

/**
 * Returns a sequence containing all elements except first elements that satisfy the given [predicate].
 */
@FixmeSequences
public fun <T> Sequence<T>.dropWhile(predicate: (T) -> Boolean): Sequence<T> {
    TODO()
    //return DropWhileSequence(this, predicate)
}

/**
 * A sequence that returns the values from the underlying [sequence] that either match or do not match
 * the specified [predicate].
 *
 * @param sendWhen If `true`, values for which the predicate returns `true` are returned. Otherwise,
 * values for which the predicate returns `false` are returned
 */
internal class FilteringSequence<T>(private val sequence: Sequence<T>,
                                    private val sendWhen: Boolean = true,
                                    private val predicate: (T) -> Boolean
) : Sequence<T> {

    override fun iterator(): Iterator<T> = object : Iterator<T> {
        val iterator = sequence.iterator()
        var nextState: Int = -1 // -1 for unknown, 0 for done, 1 for continue
        var nextItem: T? = null

        private fun calcNext() {
            while (iterator.hasNext()) {
                val item = iterator.next()
                if (predicate(item) == sendWhen) {
                    nextItem = item
                    nextState = 1
                    return
                }
            }
            nextState = 0
        }

        override fun next(): T {
            if (nextState == -1)
                calcNext()
            if (nextState == 0)
                throw NoSuchElementException()
            val result = nextItem
            nextItem = null
            nextState = -1
            return result as T
        }

        override fun hasNext(): Boolean {
            if (nextState == -1)
                calcNext()
            return nextState == 1
        }
    }
}

/**
 * Returns a sequence containing only elements matching the given [predicate].
 */
public fun <T> Sequence<T>.filter(predicate: (T) -> Boolean): Sequence<T> {
    return FilteringSequence(this, true, predicate)
}

/**
 * Returns a sequence containing only elements matching the given [predicate].
 * @param [predicate] function that takes the index of an element and the element itself
 * and returns the result of predicate evaluation on the element.
 */
@Fixme
public fun <T> Sequence<T>.filterIndexed(predicate: (Int, T) -> Boolean): Sequence<T> {
    TODO()
    // TODO: Rewrite with generalized MapFilterIndexingSequence
    // return TransformingSequence(FilteringSequence(IndexingSequence(this), true, { predicate(it.index, it.value) }), { it.value })
}

/**
 * Appends all elements matching the given [predicate] to the given [destination].
 * @param [predicate] function that takes the index of an element and the element itself
 * and returns the result of predicate evaluation on the element.
 */
public inline fun <T, C : MutableCollection<in T>> Sequence<T>.filterIndexedTo(destination: C, predicate: (Int, T) -> Boolean): C {
    forEachIndexed { index, element ->
        if (predicate(index, element)) destination.add(element)
    }
    return destination
}

/**
 * Returns a sequence containing all elements that are instances of specified type parameter R.
 */
@FixmeReified
public inline fun <reified R> Sequence<*>.filterIsInstance(): Sequence<@kotlin.internal.NoInfer R> {
    //@Suppress("UNCHECKED_CAST")
    //return filter { it is R } as Sequence<R>
    TODO()
}

/**
 * Appends all elements that are instances of specified type parameter R to the given [destination].
 */
@FixmeReified
public inline fun <reified R, C : MutableCollection<in R>> Sequence<*>.filterIsInstanceTo(destination: C): C {
    //for (element in this) if (element is R) destination.add(element)
    //return destination
    TODO()
}

/**
 * Returns a sequence containing all elements not matching the given [predicate].
 */
public fun <T> Sequence<T>.filterNot(predicate: (T) -> Boolean): Sequence<T> {
    return FilteringSequence(this, false, predicate)
}

/**
 * Returns a sequence containing all elements that are not `null`.
 */
public fun <T : Any> Sequence<T?>.filterNotNull(): Sequence<T> {
    @Suppress("UNCHECKED_CAST")
    return filterNot { it == null } as Sequence<T>
}

/**
 * Appends all elements that are not `null` to the given [destination].
 */
public fun <C : MutableCollection<in T>, T : Any> Sequence<T?>.filterNotNullTo(destination: C): C {
    for (element in this) if (element != null) destination.add(element)
    return destination
}

/**
 * Appends all elements not matching the given [predicate] to the given [destination].
 */
public inline fun <T, C : MutableCollection<in T>> Sequence<T>.filterNotTo(destination: C, predicate: (T) -> Boolean): C {
    for (element in this) if (!predicate(element)) destination.add(element)
    return destination
}

/**
 * Appends all elements matching the given [predicate] to the given [destination].
 */
public inline fun <T, C : MutableCollection<in T>> Sequence<T>.filterTo(destination: C, predicate: (T) -> Boolean): C {
    for (element in this) if (predicate(element)) destination.add(element)
    return destination
}

/**
 * Returns a sequence containing first [n] elements.
 */
@FixmeSequences
public fun <T> Sequence<T>.take(n: Int): Sequence<T> {
    //require(n >= 0) { "Requested element count $n is less than zero." }
    //return when {
    //    n == 0 -> emptySequence()
    //    this is DropTakeSequence -> this.take(n)
    //    else -> TakeSequence(this, n)
    //}
    TODO()
}

/**
 * Returns a sequence containing first elements satisfying the given [predicate].
 */
@FixmeSequences
public fun <T> Sequence<T>.takeWhile(predicate: (T) -> Boolean): Sequence<T> {
    // return TakeWhileSequence(this, predicate)
    TODO()
}

/**
 * Returns a sequence that yields elements of this sequence sorted according to their natural sort order.
 */
public fun <T : Comparable<T>> Sequence<T>.sorted(): Sequence<T> {
    return object : Sequence<T> {
        override fun iterator(): Iterator<T> {
            val sortedList = this@sorted.toMutableList()
            sortedList.sort()
            return sortedList.iterator()
        }
    }
}

/**
 * Returns a sequence that yields elements of this sequence sorted according to natural sort order of the value returned by specified [selector] function.
 */
public inline fun <T, R : Comparable<R>> Sequence<T>.sortedBy(crossinline selector: (T) -> R?): Sequence<T> {
    return sortedWith(compareBy(selector))
}

/**
 * Returns a sequence that yields elements of this sequence sorted descending according to natural sort order of the value returned by specified [selector] function.
 */
public inline fun <T, R : Comparable<R>> Sequence<T>.sortedByDescending(crossinline selector: (T) -> R?): Sequence<T> {
    return sortedWith(compareByDescending(selector))
}

/**
 * Returns a sequence that yields elements of this sequence sorted descending according to their natural sort order.
 */
public fun <T : Comparable<T>> Sequence<T>.sortedDescending(): Sequence<T> {
    return sortedWith(reverseOrder())
}

/**
 * Returns a sequence that yields elements of this sequence sorted according to the specified [comparator].
 */
public fun <T> Sequence<T>.sortedWith(comparator: Comparator<in T>): Sequence<T> {
    return object : Sequence<T> {
        override fun iterator(): Iterator<T> {
            val sortedList = this@sortedWith.toMutableList()
            sortedList.sortWith(comparator)
            return sortedList.iterator()
        }
    }
}

/**
 * Returns a [Map] containing key-value pairs provided by [transform] function
 * applied to elements of the given sequence.
 *
 * If any of two pairs would have the same key the last one gets added to the map.
 *
 * The returned map preserves the entry iteration order of the original sequence.
 */
public inline fun <T, K, V> Sequence<T>.associate(transform: (T) -> Pair<K, V>): Map<K, V> {
    return associateTo(LinkedHashMap<K, V>(), transform)
}

/**
 * Returns a [Map] containing the elements from the given sequence indexed by the key
 * returned from [keySelector] function applied to each element.
 *
 * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
 *
 * The returned map preserves the entry iteration order of the original sequence.
 */
public inline fun <T, K> Sequence<T>.associateBy(keySelector: (T) -> K): Map<K, T> {
    return associateByTo(LinkedHashMap<K, T>(), keySelector)
}

/**
 * Returns a [Map] containing the values provided by [valueTransform] and indexed by [keySelector] functions applied to elements of the given sequence.
 *
 * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
 *
 * The returned map preserves the entry iteration order of the original sequence.
 */
public inline fun <T, K, V> Sequence<T>.associateBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, V> {
    return associateByTo(LinkedHashMap<K, V>(), keySelector, valueTransform)
}

/**
 * Populates and returns the [destination] mutable map with key-value pairs,
 * where key is provided by the [keySelector] function applied to each element of the given sequence
 * and value is the element itself.
 *
 * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
 */
public inline fun <T, K, M : MutableMap<in K, in T>> Sequence<T>.associateByTo(destination: M, keySelector: (T) -> K): M {
    for (element in this) {
        destination.put(keySelector(element), element)
    }
    return destination
}

/**
 * Populates and returns the [destination] mutable map with key-value pairs,
 * where key is provided by the [keySelector] function and
 * and value is provided by the [valueTransform] function applied to elements of the given sequence.
 *
 * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
 */
public inline fun <T, K, V, M : MutableMap<in K, in V>> Sequence<T>.associateByTo(destination: M, keySelector: (T) -> K, valueTransform: (T) -> V): M {
    for (element in this) {
        destination.put(keySelector(element), valueTransform(element))
    }
    return destination
}

/**
 * Populates and returns the [destination] mutable map with key-value pairs
 * provided by [transform] function applied to each element of the given sequence.
 *
 * If any of two pairs would have the same key the last one gets added to the map.
 */
public inline fun <T, K, V, M : MutableMap<in K, in V>> Sequence<T>.associateTo(destination: M, transform: (T) -> Pair<K, V>): M {
    for (element in this) {
        destination += transform(element)
    }
    return destination
}

/**
 * Appends all elements to the given [destination] collection.
 */
public fun <T, C : MutableCollection<in T>> Sequence<T>.toCollection(destination: C): C {
    for (item in this) {
        destination.add(item)
    }
    return destination
}

/**
 * Returns a [HashSet] of all elements.
 */
public fun <T> Sequence<T>.toHashSet(): HashSet<T> {
    return toCollection(HashSet<T>())
}

/**
 * Returns a [List] containing all elements.
 */
public fun <T> Sequence<T>.toList(): List<T> {
    return this.toMutableList().optimizeReadOnlyList()
}

/**
 * Returns a [MutableList] filled with all elements of this sequence.
 */
public fun <T> Sequence<T>.toMutableList(): MutableList<T> {
    return toCollection(ArrayList<T>())
}

/**
 * Returns a [Set] of all elements.
 *
 * The returned set preserves the element iteration order of the original sequence.
 */
public fun <T> Sequence<T>.toSet(): Set<T> {
    return toCollection(LinkedHashSet<T>()).optimizeReadOnlySet()
}

/**
 * Returns a single sequence of all elements from results of [transform] function being invoked on each element of original sequence.
 */
@FixmeSequences
public fun <T, R> Sequence<T>.flatMap(transform: (T) -> Sequence<R>): Sequence<R> {
    // return FlatteningSequence(this, transform, { it.iterator() })
    TODO()
}

/**
 * Appends all elements yielded from results of [transform] function being invoked on each element of original sequence, to the given [destination].
 */
public inline fun <T, R, C : MutableCollection<in R>> Sequence<T>.flatMapTo(destination: C, transform: (T) -> Sequence<R>): C {
    for (element in this) {
        val list = transform(element)
        destination.addAll(list)
    }
    return destination
}

/**
 * Groups elements of the original sequence by the key returned by the given [keySelector] function
 * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
 *
 * The returned map preserves the entry iteration order of the keys produced from the original sequence.
 *
 * @sample samples.collections.Collections.Transformations.groupBy
 */
public inline fun <T, K> Sequence<T>.groupBy(keySelector: (T) -> K): Map<K, List<T>> {
    return groupByTo(LinkedHashMap<K, MutableList<T>>(), keySelector)
}

/**
 * Groups values returned by the [valueTransform] function applied to each element of the original sequence
 * by the key returned by the given [keySelector] function applied to the element
 * and returns a map where each group key is associated with a list of corresponding values.
 *
 * The returned map preserves the entry iteration order of the keys produced from the original sequence.
 *
 * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
 */
public inline fun <T, K, V> Sequence<T>.groupBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, List<V>> {
    return groupByTo(LinkedHashMap<K, MutableList<V>>(), keySelector, valueTransform)
}

/**
 * Groups elements of the original sequence by the key returned by the given [keySelector] function
 * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
 *
 * @return The [destination] map.
 *
 * @sample samples.collections.Collections.Transformations.groupBy
 */
public inline fun <T, K, M : MutableMap<in K, MutableList<T>>> Sequence<T>.groupByTo(destination: M, keySelector: (T) -> K): M {
    for (element in this) {
        val key = keySelector(element)
        val list = destination.getOrPut(key) { ArrayList<T>() }
        list.add(element)
    }
    return destination
}

/**
 * Groups values returned by the [valueTransform] function applied to each element of the original sequence
 * by the key returned by the given [keySelector] function applied to the element
 * and puts to the [destination] map each group key associated with a list of corresponding values.
 *
 * @return The [destination] map.
 *
 * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
 */
public inline fun <T, K, V, M : MutableMap<in K, MutableList<V>>> Sequence<T>.groupByTo(destination: M, keySelector: (T) -> K, valueTransform: (T) -> V): M {
    for (element in this) {
        val key = keySelector(element)
        val list = destination.getOrPut(key) { ArrayList<V>() }
        list.add(valueTransform(element))
    }
    return destination
}

/**
 * Creates a [Grouping] source from a sequence to be used later with one of group-and-fold operations
 * using the specified [keySelector] function to extract a key from each element.
 */
// @Fixme
// public inline fun <T, K> Sequence<T>.groupingBy(crossinline keySelector: (T) -> K): Grouping<T, K> {
//    TODO()
    //return object : Grouping<T, K> {
    //    override fun sourceIterator(): Iterator<T> = this@groupingBy.iterator()
    //    override fun keyOf(element: T): K = keySelector(element)
    //}
//}

/**
 * Returns a sequence containing the results of applying the given [transform] function
 * to each element in the original sequence.
 */
@FixmeSequences
public fun <T, R> Sequence<T>.map(transform: (T) -> R): Sequence<R> {
    // return TransformingSequence(this, transform)
    TODO()
}

/**
 * Returns a sequence containing the results of applying the given [transform] function
 * to each element and its index in the original sequence.
 * @param [transform] function that takes the index of an element and the element itself
 * and returns the result of the transform applied to the element.
 */
@FixmeSequences
public fun <T, R> Sequence<T>.mapIndexed(transform: (Int, T) -> R): Sequence<R> {
    TODO()
    //return TransformingIndexedSequence(this, transform)
}

/**
 * Returns a sequence containing only the non-null results of applying the given [transform] function
 * to each element and its index in the original sequence.
 * @param [transform] function that takes the index of an element and the element itself
 * and returns the result of the transform applied to the element.
 */
@FixmeSequences
public fun <T, R : Any> Sequence<T>.mapIndexedNotNull(transform: (Int, T) -> R?): Sequence<R> {
    TODO()
    //return TransformingIndexedSequence(this, transform).filterNotNull()
}

/**
 * Applies the given [transform] function to each element and its index in the original sequence
 * and appends only the non-null results to the given [destination].
 * @param [transform] function that takes the index of an element and the element itself
 * and returns the result of the transform applied to the element.
 */
public inline fun <T, R : Any, C : MutableCollection<in R>> Sequence<T>.mapIndexedNotNullTo(destination: C, transform: (Int, T) -> R?): C {
    forEachIndexed { index, element -> transform(index, element)?.let { destination.add(it) } }
    return destination
}

/**
 * Applies the given [transform] function to each element and its index in the original sequence
 * and appends the results to the given [destination].
 * @param [transform] function that takes the index of an element and the element itself
 * and returns the result of the transform applied to the element.
 */
public inline fun <T, R, C : MutableCollection<in R>> Sequence<T>.mapIndexedTo(destination: C, transform: (Int, T) -> R): C {
    var index = 0
    for (item in this)
        destination.add(transform(index++, item))
    return destination
}

/**
 * Returns a sequence containing only the non-null results of applying the given [transform] function
 * to each element in the original sequence.
 */
@FixmeSequences
public fun <T, R : Any> Sequence<T>.mapNotNull(transform: (T) -> R?): Sequence<R> {
    // return TransformingSequence(this, transform).filterNotNull()
    TODO()
}

/**
 * Applies the given [transform] function to each element in the original sequence
 * and appends only the non-null results to the given [destination].
 */
public inline fun <T, R : Any, C : MutableCollection<in R>> Sequence<T>.mapNotNullTo(destination: C, transform: (T) -> R?): C {
    forEach { element -> transform(element)?.let { destination.add(it) } }
    return destination
}

/**
 * Applies the given [transform] function to each element of the original sequence
 * and appends the results to the given [destination].
 */
public inline fun <T, R, C : MutableCollection<in R>> Sequence<T>.mapTo(destination: C, transform: (T) -> R): C {
    for (item in this)
        destination.add(transform(item))
    return destination
}

/**
 * Returns a sequence of [IndexedValue] for each element of the original sequence.
 */
@Fixme
public fun <T> Sequence<T>.withIndex(): Sequence<IndexedValue<T>> {
    // return IndexingSequence(this)
    TODO()
}

/**
 * Returns a sequence containing only distinct elements from the given sequence.
 *
 * The elements in the resulting sequence are in the same order as they were in the source sequence.
 */
public fun <T> Sequence<T>.distinct(): Sequence<T> {
    return this.distinctBy { it }
}

/**
 * Returns a sequence containing only elements from the given sequence
 * having distinct keys returned by the given [selector] function.
 *
 * The elements in the resulting sequence are in the same order as they were in the source sequence.
 */
@FixmeSequences
public fun <T, K> Sequence<T>.distinctBy(selector: (T) -> K): Sequence<T> {
    // return DistinctSequence(this, selector)
    TODO()
}

/**
 * Returns a mutable set containing all distinct elements from the given sequence.
 *
 * The returned set preserves the element iteration order of the original sequence.
 */
public fun <T> Sequence<T>.toMutableSet(): MutableSet<T> {
    val set = LinkedHashSet<T>()
    for (item in this) set.add(item)
    return set
}

/**
 * Returns `true` if all elements match the given [predicate].
 */
public inline fun <T> Sequence<T>.all(predicate: (T) -> Boolean): Boolean {
    for (element in this) if (!predicate(element)) return false
    return true
}

/**
 * Returns `true` if sequence has at least one element.
 */
public fun <T> Sequence<T>.any(): Boolean {
    for (element in this) return true
    return false
}

/**
 * Returns `true` if at least one element matches the given [predicate].
 */
public inline fun <T> Sequence<T>.any(predicate: (T) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return true
    return false
}

/**
 * Returns the number of elements in this sequence.
 */
public fun <T> Sequence<T>.count(): Int {
    var count = 0
    for (element in this) count++
    return count
}

/**
 * Returns the number of elements matching the given [predicate].
 */
public inline fun <T> Sequence<T>.count(predicate: (T) -> Boolean): Int {
    var count = 0
    for (element in this) if (predicate(element)) count++
    return count
}

/**
 * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
 */
public inline fun <T, R> Sequence<T>.fold(initial: R, operation: (R, T) -> R): R {
    var accumulator = initial
    for (element in this) accumulator = operation(accumulator, element)
    return accumulator
}

/**
 * Accumulates value starting with [initial] value and applying [operation] from left to right
 * to current accumulator value and each element with its index in the original sequence.
 * @param [operation] function that takes the index of an element, current accumulator value
 * and the element itself, and calculates the next accumulator value.
 */
public inline fun <T, R> Sequence<T>.foldIndexed(initial: R, operation: (Int, R, T) -> R): R {
    var index = 0
    var accumulator = initial
    for (element in this) accumulator = operation(index++, accumulator, element)
    return accumulator
}

/**
 * Performs the given [action] on each element.
 */
public inline fun <T> Sequence<T>.forEach(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}

/**
 * Performs the given [action] on each element, providing sequential index with the element.
 * @param [action] function that takes the index of an element and the element itself
 * and performs the desired action on the element.
 */
public inline fun <T> Sequence<T>.forEachIndexed(action: (Int, T) -> Unit): Unit {
    var index = 0
    for (item in this) action(index++, item)
}

/**
 * Returns the largest element or `null` if there are no elements.
 *
 * If any of elements is `NaN` returns `NaN`.
 */
public fun Sequence<Double>.max(): Double? {
    val iterator = iterator()
    if (!iterator.hasNext()) return null
    var max = iterator.next()
    if (max.isNaN()) return max
    while (iterator.hasNext()) {
        val e = iterator.next()
        if (e.isNaN()) return e
        if (max < e) max = e
    }
    return max
}

/**
 * Returns the largest element or `null` if there are no elements.
 *
 * If any of elements is `NaN` returns `NaN`.
 */
public fun Sequence<Float>.max(): Float? {
    val iterator = iterator()
    if (!iterator.hasNext()) return null
    var max = iterator.next()
    if (max.isNaN()) return max
    while (iterator.hasNext()) {
        val e = iterator.next()
        if (e.isNaN()) return e
        if (max < e) max = e
    }
    return max
}

/**
 * Returns the largest element or `null` if there are no elements.
 */
public fun <T : Comparable<T>> Sequence<T>.max(): T? {
    val iterator = iterator()
    if (!iterator.hasNext()) return null
    var max = iterator.next()
    while (iterator.hasNext()) {
        val e = iterator.next()
        if (max < e) max = e
    }
    return max
}

/**
 * Returns the first element yielding the largest value of the given function or `null` if there are no elements.
 */
public inline fun <T, R : Comparable<R>> Sequence<T>.maxBy(selector: (T) -> R): T? {
    val iterator = iterator()
    if (!iterator.hasNext()) return null
    var maxElem = iterator.next()
    var maxValue = selector(maxElem)
    while (iterator.hasNext()) {
        val e = iterator.next()
        val v = selector(e)
        if (maxValue < v) {
            maxElem = e
            maxValue = v
        }
    }
    return maxElem
}

/**
 * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
 */
public fun <T> Sequence<T>.maxWith(comparator: Comparator<in T>): T? {
    val iterator = iterator()
    if (!iterator.hasNext()) return null
    var max = iterator.next()
    while (iterator.hasNext()) {
        val e = iterator.next()
        if (comparator.compare(max, e) < 0) max = e
    }
    return max
}

/**
 * Returns the smallest element or `null` if there are no elements.
 *
 * If any of elements is `NaN` returns `NaN`.
 */
public fun Sequence<Double>.min(): Double? {
    val iterator = iterator()
    if (!iterator.hasNext()) return null
    var min = iterator.next()
    if (min.isNaN()) return min
    while (iterator.hasNext()) {
        val e = iterator.next()
        if (e.isNaN()) return e
        if (min > e) min = e
    }
    return min
}

/**
 * Returns the smallest element or `null` if there are no elements.
 *
 * If any of elements is `NaN` returns `NaN`.
 */
public fun Sequence<Float>.min(): Float? {
    val iterator = iterator()
    if (!iterator.hasNext()) return null
    var min = iterator.next()
    if (min.isNaN()) return min
    while (iterator.hasNext()) {
        val e = iterator.next()
        if (e.isNaN()) return e
        if (min > e) min = e
    }
    return min
}

/**
 * Returns the smallest element or `null` if there are no elements.
 */
public fun <T : Comparable<T>> Sequence<T>.min(): T? {
    val iterator = iterator()
    if (!iterator.hasNext()) return null
    var min = iterator.next()
    while (iterator.hasNext()) {
        val e = iterator.next()
        if (min > e) min = e
    }
    return min
}

/**
 * Returns the first element yielding the smallest value of the given function or `null` if there are no elements.
 */
public inline fun <T, R : Comparable<R>> Sequence<T>.minBy(selector: (T) -> R): T? {
    val iterator = iterator()
    if (!iterator.hasNext()) return null
    var minElem = iterator.next()
    var minValue = selector(minElem)
    while (iterator.hasNext()) {
        val e = iterator.next()
        val v = selector(e)
        if (minValue > v) {
            minElem = e
            minValue = v
        }
    }
    return minElem
}

/**
 * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
 */
public fun <T> Sequence<T>.minWith(comparator: Comparator<in T>): T? {
    val iterator = iterator()
    if (!iterator.hasNext()) return null
    var min = iterator.next()
    while (iterator.hasNext()) {
        val e = iterator.next()
        if (comparator.compare(min, e) > 0) min = e
    }
    return min
}

/**
 * Returns `true` if the sequence has no elements.
 */
public fun <T> Sequence<T>.none(): Boolean {
    for (element in this) return false
    return true
}

/**
 * Returns `true` if no elements match the given [predicate].
 */
public inline fun <T> Sequence<T>.none(predicate: (T) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return false
    return true
}

/**
 * Returns a sequence which performs the given [action] on each element of the original sequence as they pass though it.
 */
public fun <T> Sequence<T>.onEach(action: (T) -> Unit): Sequence<T> {
    return map {
        action(it)
        it
    }
}

/**
 * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
 */
public inline fun <S, T: S> Sequence<T>.reduce(operation: (S, T) -> S): S {
    val iterator = this.iterator()
    if (!iterator.hasNext()) throw UnsupportedOperationException("Empty sequence can't be reduced.")
    var accumulator: S = iterator.next()
    while (iterator.hasNext()) {
        accumulator = operation(accumulator, iterator.next())
    }
    return accumulator
}

/**
 * Accumulates value starting with the first element and applying [operation] from left to right
 * to current accumulator value and each element with its index in the original sequence.
 * @param [operation] function that takes the index of an element, current accumulator value
 * and the element itself and calculates the next accumulator value.
 */
public inline fun <S, T: S> Sequence<T>.reduceIndexed(operation: (Int, S, T) -> S): S {
    val iterator = this.iterator()
    if (!iterator.hasNext()) throw UnsupportedOperationException("Empty sequence can't be reduced.")
    var index = 1
    var accumulator: S = iterator.next()
    while (iterator.hasNext()) {
        accumulator = operation(index++, accumulator, iterator.next())
    }
    return accumulator
}

/**
 * Returns the sum of all values produced by [selector] function applied to each element in the sequence.
 */
public inline fun <T> Sequence<T>.sumBy(selector: (T) -> Int): Int {
    var sum: Int = 0
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

/**
 * Returns the sum of all values produced by [selector] function applied to each element in the sequence.
 */
public inline fun <T> Sequence<T>.sumByDouble(selector: (T) -> Double): Double {
    var sum: Double = 0.0
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

/**
 * Returns an original collection containing all the non-`null` elements, throwing an [IllegalArgumentException] if there are any `null` elements.
 */
public fun <T : Any> Sequence<T?>.requireNoNulls(): Sequence<T> {
    return map { it ?: throw IllegalArgumentException("null element found in $this.") }
}

/**
 * Returns a sequence containing all elements of the original sequence without the first occurrence of the given [element].
 */
public operator fun <T> Sequence<T>.minus(element: T): Sequence<T> {
    return object: Sequence<T> {
        override fun iterator(): Iterator<T> {
            var removed = false
            return this@minus.filter { if (!removed && it == element) { removed = true; false } else true }.iterator()
        }
    }
}

/**
 * Returns a sequence containing all elements of original sequence except the elements contained in the given [elements] array.
 *
 * Note that the source sequence and the array being subtracted are iterated only when an `iterator` is requested from
 * the resulting sequence. Changing any of them between successive calls to `iterator` may affect the result.
 */
public operator fun <T> Sequence<T>.minus(elements: Array<out T>): Sequence<T> {
    if (elements.isEmpty()) return this
    return object: Sequence<T> {
        override fun iterator(): Iterator<T> {
            val other = elements.toHashSet()
            return this@minus.filterNot { it in other }.iterator()
        }
    }
}

/**
 * Returns a sequence containing all elements of original sequence except the elements contained in the given [elements] collection.
 *
 * Note that the source sequence and the collection being subtracted are iterated only when an `iterator` is requested from
 * the resulting sequence. Changing any of them between successive calls to `iterator` may affect the result.
 */
public operator fun <T> Sequence<T>.minus(elements: Iterable<T>): Sequence<T> {
    return object: Sequence<T> {
        override fun iterator(): Iterator<T> {
            val other = elements.convertToSetForSetOperation()
            if (other.isEmpty())
                return this@minus.iterator()
            else
                return this@minus.filterNot { it in other }.iterator()
        }
    }
}

/**
 * Returns a sequence containing all elements of original sequence except the elements contained in the given [elements] sequence.
 *
 * Note that the source sequence and the sequence being subtracted are iterated only when an `iterator` is requested from
 * the resulting sequence. Changing any of them between successive calls to `iterator` may affect the result.
 */
public operator fun <T> Sequence<T>.minus(elements: Sequence<T>): Sequence<T> {
    return object: Sequence<T> {
        override fun iterator(): Iterator<T> {
            val other = elements.toHashSet()
            if (other.isEmpty())
                return this@minus.iterator()
            else
                return this@minus.filterNot { it in other }.iterator()
        }
    }
}

/**
 * Returns a sequence containing all elements of the original sequence without the first occurrence of the given [element].
 */
@kotlin.internal.InlineOnly
public inline fun <T> Sequence<T>.minusElement(element: T): Sequence<T> {
    return minus(element)
}

/**
 * Splits the original sequence into pair of lists,
 * where *first* list contains elements for which [predicate] yielded `true`,
 * while *second* list contains elements for which [predicate] yielded `false`.
 */
public inline fun <T> Sequence<T>.partition(predicate: (T) -> Boolean): Pair<List<T>, List<T>> {
    val first = ArrayList<T>()
    val second = ArrayList<T>()
    for (element in this) {
        if (predicate(element)) {
            first.add(element)
        } else {
            second.add(element)
        }
    }
    return Pair(first, second)
}

/**
 * Returns a sequence containing all elements of the original sequence and then the given [element].
 */
@FixmeSequences
public operator fun <T> Sequence<T>.plus(element: T): Sequence<T> {
    TODO()
    // return sequenceOf(this, sequenceOf(element)).flatten()
}

/**
 * Returns a sequence containing all elements of original sequence and then all elements of the given [elements] array.
 *
 * Note that the source sequence and the array being added are iterated only when an `iterator` is requested from
 * the resulting sequence. Changing any of them between successive calls to `iterator` may affect the result.
 */
public operator fun <T> Sequence<T>.plus(elements: Array<out T>): Sequence<T> {
    return this.plus(elements.asList())
}

/**
 * Returns a sequence containing all elements of original sequence and then all elements of the given [elements] collection.
 *
 * Note that the source sequence and the collection being added are iterated only when an `iterator` is requested from
 * the resulting sequence. Changing any of them between successive calls to `iterator` may affect the result.
 */
@FixmeSequences
public operator fun <T> Sequence<T>.plus(elements: Iterable<T>): Sequence<T> {
    // return sequenceOf(this, elements.asSequence()).flatten()
    TODO()
}

/**
 * Returns a sequence containing all elements of original sequence and then all elements of the given [elements] sequence.
 *
 * Note that the source sequence and the sequence being added are iterated only when an `iterator` is requested from
 * the resulting sequence. Changing any of them between successive calls to `iterator` may affect the result.
 */
@FixmeSequences
public operator fun <T> Sequence<T>.plus(elements: Sequence<T>): Sequence<T> {
    //return sequenceOf(this, elements).flatten()
    TODO()
}

/**
 * Returns a sequence containing all elements of the original sequence and then the given [element].
 */
@kotlin.internal.InlineOnly
public inline fun <T> Sequence<T>.plusElement(element: T): Sequence<T> {
    return plus(element)
}

/**
 * Returns a sequence of pairs built from elements of both sequences with same indexes.
 * Resulting sequence has length of shortest input sequence.
 */
@FixmeSequences
public infix fun <T, R> Sequence<T>.zip(other: Sequence<R>): Sequence<Pair<T, R>> {
    TODO()
    //return MergingSequence(this, other) { t1, t2 -> t1 to t2 }
}

/**
 * Returns a sequence of values built from elements of both collections with same indexes using provided [transform]. Resulting sequence has length of shortest input sequences.
 */
@FixmeSequences
public fun <T, R, V> Sequence<T>.zip(other: Sequence<R>, transform: (T, R) -> V): Sequence<V> {
    // return MergingSequence(this, other, transform)
    TODO()
}

/**
 * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
 *
 * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
 * elements will be appended, followed by the [truncated] string (which defaults to "...").
 */
@FixmeSequences
public fun <T, A : Appendable> Sequence<T>.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((T) -> CharSequence)? = null): A {
    TODO()
    /*
    buffer.append(prefix)
    var count = 0
    for (element in this) {
        if (++count > 1) buffer.append(separator)
        if (limit < 0 || count <= limit) {
            buffer.appendElement(element, transform)
        } else break
    }
    if (limit >= 0 && count > limit) buffer.append(truncated)
    buffer.append(postfix)
    return buffer */
}

/**
 * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
 *
 * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
 * elements will be appended, followed by the [truncated] string (which defaults to "...").
 */
public fun <T> Sequence<T>.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((T) -> CharSequence)? = null): String {
    return joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()
}

/**
 * Creates an [Iterable] instance that wraps the original sequence returning its elements when being iterated.
 */
public fun <T> Sequence<T>.asIterable(): Iterable<T> {
    return Iterable { this.iterator() }
}

/**
 * Returns this sequence as a [Sequence].
 */
@kotlin.internal.InlineOnly
public inline fun <T> Sequence<T>.asSequence(): Sequence<T> {
    return this
}

/**
 * Returns an average value of elements in the sequence.
 */
public fun Sequence<Byte>.average(): Double {
    var sum: Double = 0.0
    var count: Int = 0
    for (element in this) {
        sum += element
        count += 1
    }
    return if (count == 0) 0.0 else sum / count
}

/**
 * Returns an average value of elements in the sequence.
 */
public fun Sequence<Short>.average(): Double {
    var sum: Double = 0.0
    var count: Int = 0
    for (element in this) {
        sum += element
        count += 1
    }
    return if (count == 0) 0.0 else sum / count
}

/**
 * Returns an average value of elements in the sequence.
 */
public fun Sequence<Int>.average(): Double {
    var sum: Double = 0.0
    var count: Int = 0
    for (element in this) {
        sum += element
        count += 1
    }
    return if (count == 0) 0.0 else sum / count
}

/**
 * Returns an average value of elements in the sequence.
 */
public fun Sequence<Long>.average(): Double {
    var sum: Double = 0.0
    var count: Int = 0
    for (element in this) {
        sum += element
        count += 1
    }
    return if (count == 0) 0.0 else sum / count
}

/**
 * Returns an average value of elements in the sequence.
 */
public fun Sequence<Float>.average(): Double {
    var sum: Double = 0.0
    var count: Int = 0
    for (element in this) {
        sum += element
        count += 1
    }
    return if (count == 0) 0.0 else sum / count
}

/**
 * Returns an average value of elements in the sequence.
 */
public fun Sequence<Double>.average(): Double {
    var sum: Double = 0.0
    var count: Int = 0
    for (element in this) {
        sum += element
        count += 1
    }
    return if (count == 0) 0.0 else sum / count
}

/**
 * Returns the sum of all elements in the sequence.
 */
public fun Sequence<Byte>.sum(): Int {
    var sum: Int = 0
    for (element in this) {
        sum += element
    }
    return sum
}

/**
 * Returns the sum of all elements in the sequence.
 */
public fun Sequence<Short>.sum(): Int {
    var sum: Int = 0
    for (element in this) {
        sum += element
    }
    return sum
}

/**
 * Returns the sum of all elements in the sequence.
 */
public fun Sequence<Int>.sum(): Int {
    var sum: Int = 0
    for (element in this) {
        sum += element
    }
    return sum
}

/**
 * Returns the sum of all elements in the sequence.
 */
public fun Sequence<Long>.sum(): Long {
    var sum: Long = 0L
    for (element in this) {
        sum += element
    }
    return sum
}

/**
 * Returns the sum of all elements in the sequence.
 */
public fun Sequence<Float>.sum(): Float {
    var sum: Float = 0.0f
    for (element in this) {
        sum += element
    }
    return sum
}

/**
 * Returns the sum of all elements in the sequence.
 */
public fun Sequence<Double>.sum(): Double {
    var sum: Double = 0.0
    for (element in this) {
        sum += element
    }
    return sum
}

