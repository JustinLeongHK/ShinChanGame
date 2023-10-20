package view

import scalafx.scene.{Group, Scene}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}

class MultiInfo extends Scene {
  val info = new Info()

  val multiInfo = new Text(
    "Prepare for a wacky family showdown in Shin Chan's Toy Tussle!\nIn this uproarious multiplayer mode, the battle for toy supremacy is on,\npitting Shin Chan against his formidable opponent... his own mother! \nMama Hiroshi has decided to give her little troublemaker a run for his money by joining the race to collect \nall of Shin Chan's treasured toys.\n\nWill Shin Chan's cunning and mischief prevail, or will Mama Hiroshi show her son who's truly in charge?\n\nTo navigate: \nUse AWSD Keys to move Mama \nUse Arrow Keys to move Shin Chan"
  ) {
    font = info.infoFont
    layoutX = 70
    layoutY = 110
  }
  val multiInfoImage1 = new ImageView {
    image = new Image(getClass.getResourceAsStream("/shinchan2.png"))
    layoutX = 800
    layoutY = 600
    fitHeight = 130
    fitWidth = 100
  }
  val multiInfoImage2 = new ImageView {
    image = new Image(getClass.getResourceAsStream("/mama.png"))
    layoutX = 1000
    layoutY = 430
    fitWidth = 300
    fitHeight = 300
  }

  val contentGroup = new Group()

  contentGroup.children.addAll(info.content)
  contentGroup.children.add(multiInfo)
  contentGroup.children.add(multiInfoImage1)
  contentGroup.children.add(multiInfoImage2)

  content = contentGroup
  fill = info.gradient

}
