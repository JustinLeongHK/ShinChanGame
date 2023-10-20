package view

import scalafx.animation.RotateTransition
import scalafx.scene.Scene
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}
import scalafx.util.Duration

class HomePage extends Scene {
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
  val homePageImage = new ImageView {
    image = new Image(getClass.getResourceAsStream("/homepage.png"))
    layoutX = 450
    layoutY = 180
  }
  val selection = new Rectangle {
    width = 500
    height = 200
    layoutX = 480
    layoutY = 570
    fill = Color.Yellow
    arcWidth = 20.0
    arcHeight = 20.0
    stroke = Color.Black
    strokeWidth = 10.0
  }
  val rt = new RotateTransition(Duration(5000), homePageImage) {
    byAngle = 5
    cycleCount = RotateTransition.Indefinite
    autoReverse = true
  }
  rt.play()
  val singlePlayer = new Text("Single Player Mode     X") {
    font = myfont
    layoutX = 580
    layoutY = 650
  }
  val multiPlayer = new Text("Multi Player Mode       O") {
    font = myfont
    layoutX = 580
    layoutY = 710
  }
  content = List(homePageImage, selection, singlePlayer, multiPlayer)
  fill = gradient

}
