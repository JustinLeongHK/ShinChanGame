package view

import scalafx.scene.Scene
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}

class MultiResultBase extends Scene {
  val gradient: LinearGradient = new LinearGradient(
    startX = 0.0,
    startY = 0.0,
    endX = 0.0,
    endY = 1.0,
    proportional = true,
    stops = Stops(
      (Color.Green),
      (Color.Orange),
      (Color.Purple)
    )
  )
  val winnerFont = new Font("Algerian", 60)
  val myfont = new Font("Algerian", 25)
  val infoBox = new Rectangle {
    width = 750
    height = 400
    layoutX = 50
    layoutY = 80
    fill = Color.Yellow
    arcWidth = 20.0
    arcHeight = 20.0
    stroke = Color.Black
    strokeWidth = 10.0
  }

  val playAgain = new Text("P : Play Again ") {
    layoutX = 100
    layoutY = 400
    font = myfont
  }
  val mainMenu = new Text("M : Main Menu") {
    layoutX = 500
    layoutY = 400
    font = myfont
  }

  content = List(infoBox, playAgain, mainMenu)
}
