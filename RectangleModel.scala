package model

import scalafx.animation.TranslateTransition
import scalafx.beans.property.DoubleProperty
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.util.Duration

class RectangleModel(initialX: Double, initialY: Double, width: Double, height: Double, color: Color) {
  val x: DoubleProperty = DoubleProperty(initialX)
  val y: DoubleProperty = DoubleProperty(initialY)
  val rectWidth: DoubleProperty = DoubleProperty(width)
  val rectHeight: DoubleProperty = DoubleProperty(height)
  val rectColor: Color = color
  val arcWidth: Double = 10.0
  val arcHeight: Double = 10.0
  val stroke: Color = Color.Black
  val strokeWidth: Double = 5.0

  // Create the rectangle view using the properties from the RectangleModel
  val rectangle: Rectangle = new Rectangle {
    x <== RectangleModel.this.x
    y <== RectangleModel.this.y
    width <== rectWidth
    height <== rectHeight
    fill = rectColor
    arcWidth = RectangleModel.this.arcWidth
    arcHeight = RectangleModel.this.arcHeight
    stroke = RectangleModel.this.stroke
    strokeWidth = RectangleModel.this.strokeWidth
  }

  // Method to move the rectangle using an animation
  def moveRectangle(distance : Int): Unit = {
    val rectangleMove = new TranslateTransition(Duration(2000), rectangle) {
      byX = distance
      cycleCount = TranslateTransition.Indefinite
      autoReverse = true
    }
    rectangleMove.play()
  }
}
