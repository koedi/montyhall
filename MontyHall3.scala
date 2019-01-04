  /**
   * MONTY HALL SIMULATOR
   */

  var runs = 10000
  
  val rnd = new scala.util.Random

  var prize    = 0
  var selected = 0
  
  var changeWins = 0
  var noChangeWins = 0
  
  for ( i<- 0 to runs) {
    prize = rnd.nextInt(3)
    selected = rnd.nextInt(3)
    tempChange +=    {if (prize == selected) 0 else 1}
    tempNoChange +=  {if (prize == selected) 1 else 0}
  }
  
  println("Runs: " + runs)
  println("No change: " + 1.0*noChangeWins/runs + "\nChange:\t   " + 1.0*changeWins/runs)
  
  