object MontyHall extends App {

  class Room() {
    private var prize = false;
    private var selected = false;

    def setPrize: Unit = { this.prize = true }
    def hasPrize: Boolean = { this.prize }

    def setSelected: Unit = { this.selected = true }
    def isSelected: Boolean = { this.selected }

    def reset: Unit = { this.selected = false; this.prize = false }
   } // class ROOM

  class Game(val rooms: Vector[MontyHall.Room]) {
    
    def resetGame(): Unit = {
      this.rooms.foreach(_.reset)
    }

    def getSelectedRoom(): MontyHall.Room = {
      this.rooms.filter(_.isSelected).head
    }

    def changesChoice(): Int = {
      this.getSelectedRoom().hasPrize match {
        case true  => 0
        case false => 1
      }
    }

    def doesNotChangeChoice(): Int = {
      this.getSelectedRoom().hasPrize match {
        case true  => 1
        case false => 0
      }
    }

    def setChoices(): Unit = {
      this.rooms(rnd.nextInt(this.rooms.size)).setSelected
      this.rooms(rnd.nextInt(this.rooms.size)).setPrize      
    }
  } //class GAME
  

  /**
   * MONTY HALL SIMULATOR
   */

  var runs = 10000
  
  val rnd = new scala.util.Random
  val simGame = new Game(Vector(new Room(), new Room(), new Room()))

  var noChangeWins = 0
  var changeWins = 0
  
  for(i <- 0 to runs) {
    simGame.resetGame()
    simGame.setChoices()

    // Does not change room
    noChangeWins += simGame.doesNotChangeChoice()
      
    // Changes room
    changeWins += simGame.changesChoice()
  }
  
  println("Runs: " + runs)
  println("No change: " + 1.0*noChangeWins/runs + "\nChange:\t   " + 1.0*changeWins/runs)
  
 }


