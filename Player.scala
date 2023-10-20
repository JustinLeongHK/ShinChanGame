package model

import scalafx.beans.property.DoubleProperty

class Player(initialX: Double, initialY: Double) {
  // Create DoubleProperty for x and y
  val x: DoubleProperty = DoubleProperty(initialX)
  val y: DoubleProperty = DoubleProperty(initialY)

  // Update the position using DoubleProperty's value property
  def updatePosition(dx: Double, dy: Double): Unit = {
    x.value += dx
    y.value += dy
  }
}
