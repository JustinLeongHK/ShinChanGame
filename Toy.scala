package model

import scalafx.animation.RotateTransition
import scalafx.beans.property.DoubleProperty
import scalafx.scene.image.{Image, ImageView}
import scalafx.util.Duration

class Toy(initialX: Double, initialY: Double) {
  val x: DoubleProperty = DoubleProperty(initialX)
  val y: DoubleProperty = DoubleProperty(initialY)
  val width: Double = 30
  val height: Double = 60

  val toy = new ImageView {
    image = new Image(getClass.getResourceAsStream("/hero.png"))
    fitWidth = width
    fitHeight = height
    x <== Toy.this.x
    y <== Toy.this.y
  }

  val moveToy = new RotateTransition(Duration(1000), toy) {
    byAngle = 20
    cycleCount = RotateTransition.Indefinite
    autoReverse = true
  }
  moveToy.play()

}
