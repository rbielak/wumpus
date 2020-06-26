class Node (name: String) {
    val value: String = name
    var left: Node? = null
        set(value) {field = value}
    var right: Node? = null
        set(value) {field = value}
}

class Stack() {
    val content: MutableList<Node?> = mutableListOf()
    var top = -1

    fun push(n: Node?) {
        content.add(n)
        top += 1
    }

    fun pop(): Node? {
        return content.removeAt(top--)
    }

    fun empty(): Boolean {
        return top == -1
    }
}

class InOrderTreeIterator(var root: Node?) {

    val stack = Stack()
    var tree: Node? = null

    init {
        tree = root;
        if (tree != null) {
            // Push all nodes to the left on to the stack
            this.pushLeft(tree)
        }
    }

    private fun pushLeft(node: Node?) {
        var n = node
        while (n != null) {
            stack.push(n)
            n = n.left
        }
    }

    fun hasNext() : Boolean {
        return !this.stack.empty()
    }

    fun next() : Node? {
        var result: Node? = null
        if (!this.stack.empty()) {
            result = this.stack.pop()
            this.pushLeft(result?.right)
        }
        return result
    }
}

fun printTree(root: Node?) {
    if (root == null) {
        return
    }
    printTree(root.left)
    println("Node = ${root.value}")
    printTree(root.right)
}

fun main () {

    val root = Node("root")
    root.left = Node("as")
    val right = Node("Last")
    right.left = Node("Pre-last")
    root.right = right
    printTree(root)

    val it = InOrderTreeIterator(root)

    while (it.hasNext()) {
        println("It.next() = ${it.next()?.value}")
    }
}


