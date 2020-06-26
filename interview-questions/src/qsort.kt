
fun main() {
    println(qsort(mutableListOf("b", "x", "t", "abc", "dd", "zzzz", "a", "c")))
}

fun qsort(l :MutableList<String>) : MutableList<String> {
    if (l.size <= 1) {
        return l
    }
    var pivot = l.removeAt(0)
    var left = mutableListOf<String>()
    var right = mutableListOf<String>()
    l.forEach{
        s ->
        if (s > pivot) {
            right.add(s)
        } else {
            left.add(s)
        }
    }
    var result = qsort(left)
    result.add(pivot)
    result.addAll(qsort(right))
    return result
}