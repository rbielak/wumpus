class Game {

    var currentRoom = 1
    var alive = true
    var arrows = 3
    var cave = Cave()

    fun moveToRoom(newRoom: Int) {
        currentRoom = newRoom
        if (cave.rooms[newRoom-1].hasBats) {
            println("Oh, no there are bats in this room!!!")
        }
    }

    fun printRoomInfo(room: Room) {
        print("You are in room ${currentRoom}. ")
        println("Tunnels lead to rooms: ${room.tunnels[0]}, ${room.tunnels[1]} and ${room.tunnels[2]}.")
        // Check for bats.
        var nearBats = false
        room.tunnels.forEach { r -> if (cave.rooms[r].hasBats) nearBats = true}
        if (nearBats) {
            println( "You hear rustling of bat wings!")
        }
    }

    fun play() {
        while (alive) {
            printRoomInfo(cave.rooms[currentRoom - 1])
            print("Shoot (s <n>), Move(m <n>) or Quit(q): ")
            val a = readLine()!!.toString().split(' ')
            val answer = a.first()
            when (answer) {
                "s" -> println("Shooting")
                "m" -> moveToRoom(a.last().toInt())
                "q" -> return
                else -> println("Huh?! try again")
            }
        }
    }
}