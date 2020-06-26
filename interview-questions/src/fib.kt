
fun fibonacci() =  sequence {
    var x = 0
    var y = 1
    yield(1)
    while (true) {
        var r = x + y
        yield(r)
        x = y
        y = r
    }

}

fun main() {

    var fi = fibonacci().iterator();

    for (i in 1..30) {
        print("${fi.next()}, ")
    }
}