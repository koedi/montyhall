object MontyHall extends App {

  class Room(val id: Int) {
    private var prize = false;
    private var selected = false;

    def setPrize: Unit = { this.prize = true }
    def hasPrize: Boolean = { this.prize }

    def setSelected: Unit = { this.selected = true }
    def isSelected: Boolean = { this.selected }

    def reset: Unit = { this.selected = false; this.prize = false }
    
    override def toString = "ID: "+ id +" S: " + this.selected + " P: " + this.prize + " "
    
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

  var runs = 1000
  var i = 0


  val rnd = new scala.util.Random
  
  val simGame = new Game(Vector(new Room(1), new Room(2), new Room(3)))
  
  var noChangeWins = 0
  var changeWins = 0
 
  
  while ( i < runs) {
    simGame.resetGame()
    simGame.setChoices()

    // Does not change room
    noChangeWins += simGame.doesNotChangeChoice()
      
    // Changes room
    changeWins += simGame.changesChoice()
      
    i += 1
  }
  
  println("Runs: " + runs)
  println("No change: " + 1.0*noChangeWins/runs + "\nChange:\t   " + 1.0*changeWins/runs)
  
 }
