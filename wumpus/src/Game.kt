class Game {

    private var currentRoom = Cave.rooms[1]
    private var alive = true
    private var arrows = 3

    private fun moveToRoom(newRoom: Int) : Boolean {
        if (newRoom in currentRoom.tunnels) {
            currentRoom = Cave.rooms[newRoom - 1]
            if (Cave.nearBats(currentRoom)) {
                println("Oh, no there are bats in this room!!!")
                currentRoom = Cave.randomwSafeRoom()
                println("You runway to room ${currentRoom.number}")
            }
            if (newRoom == Cave.wumpusRoom) {
                println("Hrumph! You were eaten by the wumpus!")
                return false
            }
            if (currentRoom.hasPit) {
                println("Ooops! You have fallen into the pit!")
                return false
            }
        } else {
            println("This is not a valid room number. Try again!")
        }
        return true
    }

    private fun shootIntoRoom(roomNumber: Int) : Boolean {
        if (arrows == 0) {
            println("Sorry you are out of arrows!")
        }
        else {
            if (roomNumber in currentRoom.tunnels) {
                if (roomNumber == Cave.wumpusRoom) {
                    println("Wahoo! You got the wumpus!!!!")
                    return false
                }
                arrows -= 1
                println("Sorry, you missed. You have $arrows left.")
            } else {
                println ("That is not a valid rom number. Try again")
            }
        }
        return true
    }

    private fun printRoomInfo(room: Room) {
        print("You are in room ${currentRoom.number}. ")
        println("Tunnels lead to rooms: ${room.tunnels[0]}, ${room.tunnels[1]} and ${room.tunnels[2]}.")
        // Check for bats.
        if (Cave.nearBats(room)) {
            println( "You hear rustling of bat wings!")
        }
        if (Cave.nearPit(room)) {
            println("You feel cold wind from the pit!")
        }
        if (Cave.nearWumpus(room)) {
            println("You smell a wumpus!")
        }
    }

    fun play() {
        while (alive) {
            printRoomInfo(currentRoom)
            print("Shoot (s<n>), Move(m<n>) or Quit(q): ")
            val str = readLine()!!
            when (str[0]) {
                's' -> alive = shootIntoRoom(str.substring(1).toInt())
                'm' -> alive = moveToRoom(str.substring(1).toInt())
                'q' -> return
                else -> println("Huh?! try again")
            }
            Cave.moveWumpus()
            if (Cave.wumpusRoom == currentRoom.number) {
                println("Hrmph! Wumpus walked in and ate you!")
                alive = false
            }
        }
    }
}