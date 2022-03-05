import kotlin.random.Random

// Object Cave is a singleton
object Cave {

    val rooms: Array<Room> =
        // Rooms in the cave.
        // See: https://en.wikipedia.org/wiki/Hunt_the_Wumpus
        arrayOf(
            Room(1, arrayOf(2, 5, 8)),
            Room(2, arrayOf(1, 10, 3)),
            Room(3, arrayOf(2, 12, 4)),
            Room(4, arrayOf(3, 14, 5)),
            Room(5, arrayOf(1, 6, 4)),
            Room(6, arrayOf(7, 5, 15)),
            Room(7, arrayOf(6, 8, 17)),
            Room(8, arrayOf(1, 7, 9)),
            Room(9, arrayOf(8, 10, 18)),
            Room(10, arrayOf(2, 11, 9)),
            Room(11, arrayOf(10, 12, 19)),
            Room(12, arrayOf(3, 11, 13)),
            Room(13, arrayOf(12, 14, 20)),
            Room(14, arrayOf(4, 13, 15)),
            Room(15, arrayOf(16, 14, 6)),
            Room(16, arrayOf(15, 20, 17)),
            Room(17, arrayOf(7, 18, 16)),
            Room(18, arrayOf(9, 18, 17)),
            Room(19, arrayOf(11, 20, 18)),
            Room(20, arrayOf(13, 19, 16))
            )

    private val rand = Random(System.currentTimeMillis())

    private var wumpusRoom = 0
    private var pitRoom = 0

    // TODO: make bat rooms array of ints
    fun nearBats(room: Room) : Boolean {
        room.tunnels.forEach { r -> if (rooms[r - 1].hasBats) return true }
        return false
    }

    fun nearPit(room: Room) = pitRoom in room.tunnels

    fun nearWumpus(room: Room) = wumpusRoom in room.tunnels

    fun wumpusRoomNumber(rn: Int) = wumpusRoom == rn

    fun killWumpus() {
        wumpusRoom = -1
    }

    fun randomSafeRoom() : Room {
        var nr = rand.nextInt(1, 20)
        while (rooms[nr].hasPit || rooms[nr].hasBats || nr == wumpusRoom) {
            nr = rand.nextInt(1, 20)
        }
        return rooms[nr]
    }

    fun moveWumpus() {
        if (wumpusRoom > 0) {
            val wr = rooms[wumpusRoom - 1]
            val i = rand.nextInt(0, 2)
            wumpusRoom = wr.tunnels[i]
        }
    }

    init{
        this.wumpusRoom = rand.nextInt(1, 20)
        // Add bats
        for (i in 1..2) {
            val bats = rand.nextInt(1, 20)
            rooms[bats-1].hasBats = true
        }
        // Add pit
        pitRoom = rand.nextInt(1, 20)
    }
}