package controller

import javafx.beans.property.SimpleBooleanProperty
import model.Player
import scalafx.animation.AnimationTimer
import scalafx.application.Platform
import scalafx.scene.Scene
import scalafx.scene.image.ImageView
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.transform.{Scale, Translate}
import scalafx.stage.Stage
import view.{HomePage, SingleGamePlay, SingleResultBase}
import scalafx.Includes._
import scalafx.beans.property.BooleanProperty

import scala.collection.mutable.ListBuffer

class SingleController(player: Player, view: SingleGamePlay, stage: Stage) {
  private val speed: Double = 5.0
  private var gameEndedFlag: Boolean = false
  private var startTime: Long = 0

  val isGameOverProperty: BooleanProperty = BooleanProperty(false)
  val isGameWonProperty: BooleanProperty = BooleanProperty(false)

  val translate = new Translate(40, 0)
  val mirror = new Scale(-1, 1)

  var animationTimer: AnimationTimer = _

  animationTimer = AnimationTimer(t => {
    if (view.keyPressed.nonEmpty) {
      handleKeyPressed(view.keyPressed)
    }
  })
  animationTimer.start()

  def handleKeyPressed(keyCode: Set[KeyCode]): Unit = {
    if (keyCode.contains(KeyCode.Up)) {
      player.updatePosition(0, -speed)
      rectangleCollisionDetector(view.avatar, "awayFromOrigin")

    } else if (keyCode.contains(KeyCode.Down)) {
      player.updatePosition(0, speed)
      rectangleCollisionDetector(view.avatar, "backToOrigin")

    } else if (keyCode.contains(KeyCode.Left)) {
      player.updatePosition(-speed, 0)
      view.avatar.transforms = List()
      rectangleCollisionDetector(view.avatar, "awayFromOrigin")

    } else if (keyCode.contains(KeyCode.Right)) {
      player.updatePosition(speed, 0)
      view.avatar.transforms = List(translate, mirror)
      rectangleCollisionDetector(view.avatar, "backToOrigin")

    }

    collectToy(view.avatar)
    windowCollisionDetector()

  }

  def windowCollisionDetector() = {
    if (player.y() > 765) {
      player.y() = player.y() - speed
    }
    if (player.y() < 0) {
      player.y() = player.y() + speed
    }
    if (player.x() < 0) {
      player.x() = player.x() + speed
    }
    if (player.x() > 1425) {
      player.x() = player.x() - speed
    }
    view.updatePositionView(player.x(), player.y())
  }

  def rectangleCollisionDetector(actor: ImageView, direction: String): Unit = {
    val actorBounds = actor.boundsInParent.value
    val actorShape = new Rectangle {
      width = actorBounds.getWidth
      height = actorBounds.getHeight
      layoutX = actorBounds.getMinX
      layoutY = actorBounds.getMinY
    }
    for (rectangle <- view.rectangles) {
      val rectangleBounds = rectangle.boundsInParent.value
      if (direction == "backToOrigin") {
        if (actorShape.getBoundsInParent.intersects(rectangleBounds)) {
          player.x() -= speed
          player.y() -= speed
        }
      }
      if (direction == "awayFromOrigin") {
        if (actorShape.getBoundsInParent.intersects(rectangleBounds)) {
          player.x() += speed
          player.y() += speed
        }
      }
    }
    view.updatePositionView(player.x(), player.y())
  }

  def collectToy(actor: ImageView): Unit = {
    val actorBounds = actor.boundsInParent.value
    val actorShape = new Rectangle {
      width = actorBounds.getWidth
      height = actorBounds.getHeight
      layoutX = actorBounds.getMinX
      layoutY = actorBounds.getMinY
    }

    val nonIntersectingToysBuffer = ListBuffer.empty[ImageView]

    for (toy <- view.toys) {
      val toyBounds = toy.boundsInParent.value
      if (actorShape.getBoundsInParent.intersects(toyBounds)) {
        toy.userData = "Intersected"
      } else {
        nonIntersectingToysBuffer += toy
      }
    }

    view.toys = nonIntersectingToysBuffer.toList

    refreshPage()
  }

  def refreshPage(): Unit = {
    // Schedule a UI update on the JavaFX application thread
    Platform.runLater {
      // Update the view with the modified toys list
      view.content = List(
        view.avatar
      ) ::: view.rectangles ::: view.toys ::: List(view.timerLabel)
    }
  }

  def clock(): Unit = {

    val duration: Double = 18

    // Create AnimationTimer to update the timer label
    val timer = AnimationTimer { currentTime =>
      if (startTime == 0) {
        startTime = currentTime
      }
      val elapsedTime = (currentTime - startTime) / 1e9
      val remainingTime = duration - elapsedTime

      if (remainingTime > 0 && view.toys.nonEmpty) {
        if (remainingTime > 1) {
          view.timerLabel.text = s"${remainingTime.toInt} seconds"
        }
        if (remainingTime <= 5) {
          view.timerLabel.fill = Color.Red
        } else {
          view.timerLabel.fill = Color.Black
        }
      } else {
        // Check if the game has already ended
        if (!gameEndedFlag) {
          // Mark the game as ended
          gameEndedFlag = true
          isGameWonProperty.value = view.toys.isEmpty
          isGameOverProperty.value = true
        }
      }
    }
    timer.start()
  }

  def resetGame(): Unit = {
    startTime = 0
    view.keyPressed = Set.empty[KeyCode]
    view.toys = view.defaultToys
    refreshPage()
    gameEndedFlag = false
    isGameOverProperty.value = false
    isGameWonProperty.value = false

  }

}
