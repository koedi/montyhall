object MontyHall extends App {

  class Room() {
    private var prize = false;
    private var selected = false;
      
    def setPrize: Unit = { this.prize = true }
    def isWin: Boolean = { this.prize }

    def setSelected: Unit = { this.selected = true }
    def isSelected: Boolean = { this.selected }

    def reset: Unit = {
      this.selected = false
      this.prize = false
    }

  } // class ROOM

  
  class Game(val rooms: Vector[MontyHall.Room]) {

    def resetGame(): Unit = {
      this.rooms.foreach(_.reset)
    }

    def dropAllButOne(): Vector[MontyHall.Room] = {
      this.rooms.filter(_.isSelected)
    }

    def changeChoice(): Vector[MontyHall.Room] = {
      this.rooms.find(x => !x.isSelected && !x.isWin).get.setSelected
      this.rooms.filter(!_.isSelected)
    }
    
    def setChoices(): Unit = {
      this.rooms(rnd.nextInt(3)).setSelected
      this.rooms(rnd.nextInt(3)).setPrize
    }
   
  } //class GAME
  

  /**
   * MAIN PART
   */

  var runs = 1000
  var i = 0

  val rnd = new scala.util.Random
  val simulatedGame = Vector(new Room(), new Room(), new Room())

  val simGame = new Game(Vector(new Room(), new Room(), new Room()))
  
  var noChangeWins = 0
  var changeWins = 0
 
  
  while ( i <= runs) {
    simGame.setChoices()

    if (simGame.dropAllButOne().head.isWin)
      noChangeWins += 1
            
    if (simGame.changeChoice().head.isWin)
      changeWins += 1  
      
    simGame.resetGame()
    i += 1
  }
  
  println("Runs: " + runs)
  println("No change:\t" + 1.0*noChangeWins/runs + "\nChange:\t\t" + 1.0*changeWins/runs)

}
