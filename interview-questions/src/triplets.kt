
data class Pair(val x: Int, val y: Int) {}

fun gcd(n1: Int, n2: Int) : Int {
    if (n2 != 0)
        return gcd(n2, n1 % n2)
    return n1
}

fun main() {
    val squares : MutableMap<Int, Pair> = mutableMapOf()
    for (i in 2..1000)
       for (j in 2..1000)
           if (gcd (j, i) == 1)
               squares.put (i*i + j*j, Pair(i,j))

    for (i in 2..2000) {
        val p = squares.get(i*i)
        if (p != null) {
            println ("$i^2 = ${p.x}^2 +  ${p.y}^2")
            println (">>>> ${i*i} = ${p.x * p.x + p.y * p.y}")
        }
    }
}