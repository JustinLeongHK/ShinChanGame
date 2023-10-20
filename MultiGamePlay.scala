package view

import model.{Player, RectangleModel, Toy}
import scalafx.scene.Scene
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.paint.Color
import scalafx.Includes._
import scalafx.scene.text.{Font, Text}

class MultiGamePlay(player1: Player, player2: Player) extends Scene {
  val avatar1: ImageView = new ImageView {
    image = new Image(getClass.getResourceAsStream("/shinchan.png"))
    fitWidth = 50
    fitHeight = 80
    layoutX = player1.x()
    layoutY = player1.y()
    userData = "player1"
  }

  val avatar2: ImageView = new ImageView {
    image = new Image(getClass.getResourceAsStream("/mama.png"))
    fitWidth = 100
    fitHeight = 100
    layoutX = player2.x()
    layoutY = player2.y()
    userData = "player2"
  }

  // Initialize rectangles
  val rectangle1 = new RectangleModel(500, 290, 500, 30, Color.Green)
  val rectangle2 = new RectangleModel(110, 200, 180, 30, Color.DeepSkyBlue)
  val rectangle3 = new RectangleModel(1200, 220, 180, 30, Color.Brown)
  val rectangle4 = new RectangleModel(130, 450, 30, 200, Color.SlateGrey)
  val rectangle5 = new RectangleModel(1300, 450, 30, 200, Color.Coral)
  val rectangle6 = new RectangleModel(300, 500, 300, 30, Color.Red)
  val rectangle7 = new RectangleModel(810, 700, 350, 30, Color.Blue)
  val rectangle8 = new RectangleModel(680, 560, 30, 100, Color.Cyan)

  // Animation on rectangle
  rectangle6.moveRectangle(500)
  rectangle7.moveRectangle(-500)

  // Initialize toys
  val toy1 = new Toy(349, 603)
  val toy2 = new Toy(682, 172)
  val toy3 = new Toy(1421, 440)
  val toy4 = new Toy(100, 700)
  val toy5 = new Toy(1229, 640)
  val toy6 = new Toy(1174, 41)
  val toy7 = new Toy(631, 618)
  val toy8 = new Toy(72, 187)
  val toy9 = new Toy(1168, 785)
  val toy10 = new Toy(76, 382)
  val toy11 = new Toy(800, 600)

  val sign = new RectangleModel(560, 10, 350, 100, Color.Yellow)

  val scoreFont = new Font("Times New Roman", 40)
  var player1Score = new Text("0") {
    font = scoreFont
    layoutX = 800
    layoutY = 65
  }
  var player2Score = new Text("0") {
    font = scoreFont
    layoutX = 650
    layoutY = 65
  }
  val scoreSeperator = new Text(":") {
    font = scoreFont
    layoutX = 725
    layoutY = 65
  }


  val rectangles = List(
    sign.rectangle,
    rectangle1.rectangle,
    rectangle2.rectangle,
    rectangle3.rectangle,
    rectangle4.rectangle,
    rectangle5.rectangle,
    rectangle6.rectangle,
    rectangle7.rectangle,
    rectangle8.rectangle
  )

  var toys = List(
    toy1.toy,
    toy2.toy,
    toy3.toy,
    toy4.toy,
    toy5.toy,
    toy6.toy,
    toy7.toy,
    toy8.toy,
    toy9.toy,
    toy10.toy,
    toy11.toy
  )

  var defaultToys = List(
    toy1.toy,
    toy2.toy,
    toy3.toy,
    toy4.toy,
    toy5.toy,
    toy6.toy,
    toy7.toy,
    toy8.toy,
    toy9.toy,
    toy10.toy,
    toy11.toy
  )
  content = List(avatar1, avatar2) ::: rectangles ::: toys ::: List (player1Score,player2Score,scoreSeperator)


  var keyPressedPlayer1 = Set.empty[KeyCode]
  var keyPressedPlayer2 = Set.empty[KeyCode]

  onKeyPressed = (e: KeyEvent) => {
    e.code match {
      case KeyCode.Up    => keyPressedPlayer1 += KeyCode.Up
      case KeyCode.Down  => keyPressedPlayer1 += KeyCode.Down
      case KeyCode.Left  => keyPressedPlayer1 += KeyCode.Left
      case KeyCode.Right => keyPressedPlayer1 += KeyCode.Right
      case KeyCode.A     => keyPressedPlayer2 += KeyCode.A
      case KeyCode.W     => keyPressedPlayer2 += KeyCode.W
      case KeyCode.S     => keyPressedPlayer2 += KeyCode.S
      case KeyCode.D     => keyPressedPlayer2 += KeyCode.D
      case _             =>
    }

  }

  onKeyReleased = (e: KeyEvent) => {
    e.code match {
      case KeyCode.Up    => keyPressedPlayer1 -= KeyCode.Up
      case KeyCode.Down  => keyPressedPlayer1 -= KeyCode.Down
      case KeyCode.Left  => keyPressedPlayer1 -= KeyCode.Left
      case KeyCode.Right => keyPressedPlayer1 -= KeyCode.Right
      case KeyCode.A     => keyPressedPlayer2 -= KeyCode.A
      case KeyCode.W     => keyPressedPlayer2 -= KeyCode.W
      case KeyCode.S     => keyPressedPlayer2 -= KeyCode.S
      case KeyCode.D     => keyPressedPlayer2 -= KeyCode.D
      case _             =>
    }
  }

  def updatePositionView1(x: Double, y: Double): Unit = {
    // Update the position of the ImageView using the translateX and translateY properties
    avatar1.layoutX = x
    avatar1.layoutY = y
  }

  def updatePositionView2(x: Double, y: Double): Unit = {
    // Update the position of the ImageView using the translateX and translateY properties
    avatar2.layoutX = x
    avatar2.layoutY = y
  }

  stylesheets = List(getClass.getResource("/styles.css").toExternalForm)
  root = new javafx.scene.layout.AnchorPane()
}
