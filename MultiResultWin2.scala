package view

import scalafx.scene.{Group, Scene}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.text.Text

class MultiResultWin2 extends Scene {
  val multiResultBase = new MultiResultBase()
  val winnertext = new Text("Mama Won !") {
    layoutX = 100
    layoutY = 200
    font = multiResultBase.winnerFont
  }
  val mama = new ImageView {
    image = new Image(getClass.getResourceAsStream("/mama.png"))
    layoutX = 1000
    layoutY = 300
  }
  // Create a new Group
  val contentGroup = new Group()

  // Add all the nodes to the contentGroup
  contentGroup.children.addAll(multiResultBase.content)
  contentGroup.children.add(winnertext)
  contentGroup.children.add(mama)

  // Set the content of the scene to the contentGroup
  content = contentGroup
  fill = multiResultBase.gradient
}
