
fun main() {
    var l = listOf("a", "anc", "", "d", "xyzzy")
    println(encode(l))
    println(decode(encode(l)))
}

fun encode(ls: List<String>): String {
    var b = StringBuilder()
    ls.forEach{s -> b.append("${s.length}:$s")}
    return b.toString()
}

fun decode(s: String): List<String> {
    var result = mutableListOf<String>()
    if (s.isNotEmpty()) {
        var index = s.indexOf(':')
        var len = s.substring(0 until index).toInt()
        result.add(s.substring(index + 1 until index + len + 1))
        result.addAll(decode(s.substring(index + len + 1, s.length)))
    }
    return result
}