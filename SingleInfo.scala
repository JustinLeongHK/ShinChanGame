package view


import scalafx.scene.{Group, Scene}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.text.{Font, Text}
import scalafx.Includes._

class SingleInfo extends Scene {
  val info = new Info()

  val singleInfoImage = new ImageView {
    image = new Image(getClass.getResourceAsStream("/jump.png"))
    layoutX = 800
    layoutY = 400
  }
  val singleInfo = new Text(
    "Welcome to Shin Chan's Wild Toy Hunt!\nIn this hilarious and adrenaline-pumping single player mode,\nyou'll step into the mischievous shoes of the legendary Shin Chan himself. \n\nTime is running out, and Shin Chan's beloved Action Kamen toy figures \nhave mysteriously scattered all over the place!\nIt's up to you to help our pint-sized hero collect them all before the clock ticks down to zero.\n\nTo navigate: \nUse Arrow Keys to Move Around"
  ) {
    font = info.infoFont
    layoutX = 70
    layoutY = 110
  }

  val contentGroup = new Group()

  contentGroup.children.addAll(info.content)
  contentGroup.children.add(singleInfoImage)
  contentGroup.children.add(singleInfo)

  content = contentGroup
  fill = info.gradient

  onKeyPressed = (e: KeyEvent) => {
    e.code match {
      case KeyCode.P =>
        println("Play")
      case KeyCode.M =>
        println("Main Menu")
      case _ =>
    }
  }
}
