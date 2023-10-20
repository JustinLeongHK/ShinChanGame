package view

import scalafx.scene.Scene
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.text.Text
import scalafx.scene.Group

class MultiResultWin1 extends Scene {
  val multiResultBase = new MultiResultBase()
  val wonText = new Text("Shin Chan Won !") {
    layoutX = 100
    layoutY = 200
    font = multiResultBase.winnerFont
  }
  val shinchan = new ImageView {
    image = new Image(getClass.getResourceAsStream("/shinchan.png"))
    layoutX = 1000
    layoutY = 300
  }


  val contentGroup = new Group()


  contentGroup.children.addAll(multiResultBase.content)
  contentGroup.children.add(wonText)
  contentGroup.children.add(shinchan)


  content = contentGroup
  fill = multiResultBase.gradient


}
