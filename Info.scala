package view

import scalafx.scene.Scene
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}
import scalafx.stage.Stage

class Info extends Scene() {
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
  val infoFont = new Font("Times New Roman", 30)
  val infoBox = new Rectangle {
    width = 1350
    height = 500
    layoutX = 50
    layoutY = 50
    fill = Color.Yellow
    arcWidth = 20.0
    arcHeight = 20.0
    stroke = Color.Black
    strokeWidth = 10.0
  }
  val multiInfoBack = new Text("M : Back to Menu") {
    font = myfont
    layoutX = 400
    layoutY = 500
  }
  val multiInfoPlay = new Text("P : Play Game") {
    font = myfont
    layoutX = 70
    layoutY = 500
  }
  content = List(infoBox, multiInfoBack, multiInfoPlay)


}
