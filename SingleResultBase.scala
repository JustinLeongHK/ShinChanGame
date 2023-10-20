package view

import scalafx.scene.Scene
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}
import scalafx.Includes._

class SingleResultBase(text: String) extends Scene {
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
  val myfont = new Font("Algerian", 25)
  val winnerFont = new Font("Algerian", 60)

  val wonText = new Text(text) {
    layoutX = 100
    layoutY = 200
    font = winnerFont
  }
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
  val shinchan = new ImageView {
    image = new Image(getClass.getResourceAsStream("/shinchan.png"))
    layoutX = 1000
    layoutY = 300
  }
  content = List(infoBox, wonText, playAgain, mainMenu, shinchan)
  fill = gradient

  private var keyPressedFlag: Boolean = false

  def keyPressed(): Boolean = {
    val flag = keyPressedFlag
    keyPressedFlag = false // Reset the flag after reading
    flag
  }

}
