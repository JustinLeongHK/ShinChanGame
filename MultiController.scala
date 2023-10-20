package controller

import model.Player
import scalafx.animation.AnimationTimer
import scalafx.application.Platform
import scalafx.beans.property.BooleanProperty
import scalafx.scene.image.ImageView
import scalafx.scene.input.KeyCode
import scalafx.scene.shape.Rectangle
import scalafx.scene.transform.{Scale, Translate}
import scalafx.stage.Stage
import view.MultiGamePlay

import scala.collection.mutable.ListBuffer

class MultiController(
    player1: Player,
    player2: Player,
    view: MultiGamePlay,
    stage: Stage
) {
  private val speed: Double = 5.0
  private var player1Score: Int = 0
  private var player2Score: Int = 0
  private var winner: String = null
  val isGameOverProperty: BooleanProperty = BooleanProperty(false)

  def getWinner: String = winner

  val translate = new Translate(40, 0)
  val translate2 = new Translate(100, 0)
  val mirror = new Scale(-1, 1)

  var animationTimer: AnimationTimer = _

  refreshPage()
  animationTimer = AnimationTimer(t => {
    if (view.keyPressedPlayer1.nonEmpty) {
      handleKeyPressed1(view.keyPressedPlayer1)
    }

    if (view.keyPressedPlayer2.nonEmpty) {
      handleKeyPressed2(view.keyPressedPlayer2)
    }
  })
  animationTimer.start()

  def handleKeyPressed1(keyCode: Set[KeyCode]): Unit = {
    if (keyCode.contains(KeyCode.Left)) {
      player1.updatePosition(-speed, 0)
      view.avatar1.transforms = List()
      rectangleCollisionDetector(view.avatar1, "awayFromOrigin")
    } else if (keyCode.contains(KeyCode.Right)) {
      player1.updatePosition(speed, 0)
      view.avatar1.transforms = List(translate, mirror)
      rectangleCollisionDetector(view.avatar1, "backToOrigin")
    } else if (keyCode.contains(KeyCode.Up)) {
      player1.updatePosition(0, -speed)
      rectangleCollisionDetector(view.avatar1, "awayFromOrigin")
    } else if (keyCode.contains(KeyCode.Down)) {
      player1.updatePosition(0, speed)
      rectangleCollisionDetector(view.avatar1, "backToOrigin")
    }

    collectToy(view.avatar1)
    view.updatePositionView1(player1.x(), player1.y())
    windowCollisionDetector()
  }

  def handleKeyPressed2(keyCode: Set[KeyCode]): Unit = {
    if (keyCode.contains(KeyCode.A)) {
      player2.updatePosition(-speed, 0)
      view.avatar2.transforms = List()
      rectangleCollisionDetector(view.avatar2, "awayFromOrigin")
    } else if (keyCode.contains(KeyCode.D)) {
      player2.updatePosition(speed, 0)
      view.avatar2.transforms = List(translate2, mirror)
      rectangleCollisionDetector(view.avatar2, "backToOrigin")
    } else if (keyCode.contains(KeyCode.W)) {
      player2.updatePosition(0, -speed)
      rectangleCollisionDetector(view.avatar2, "awayFromOrigin")
    } else if (keyCode.contains(KeyCode.S)) {
      player2.updatePosition(0, speed)
      rectangleCollisionDetector(view.avatar2, "backToOrigin")
    }
    collectToy(view.avatar2)
    view.updatePositionView2(player2.x(), player2.y())
    windowCollisionDetector()
  }

  def windowCollisionDetector() = {
    if (player1.y() > 765) {
      player1.y() = player1.y() - speed
    }
    if (player1.y() < 0) {
      player1.y() = player1.y() + speed
    }
    if (player1.x() < 0) {
      player1.x() = player1.x() + speed
    }
    if (player1.x() > 1425) {
      player1.x() = player1.x() - speed
    }
    //
    if (player2.y() < 0) {
      player2.y() = player2.y() + speed
    }
    if (player2.x() < 0) {
      player2.x() = player2.x() + speed
    }
    if (player2.y() > 740) {
      player2.y() = player2.y() - speed
    }
    if (player2.x() > 1385) {
      player2.x() = player2.x() - speed
    }

  }

  def rectangleCollisionDetector(actor: ImageView, direction: String): Unit = {
    val actorBounds = actor.boundsInParent.value
    val actorShape = new Rectangle {
      width = actorBounds.getWidth
      height = actorBounds.getHeight
      layoutX = actorBounds.getMinX
      layoutY = actorBounds.getMinY
    }
    if (actor.userData == "player1") {
      for (rectangle <- view.rectangles) {
        val rectangleBounds = rectangle.boundsInParent.value
        if (direction == "backToOrigin") {
          if (actorShape.getBoundsInParent.intersects(rectangleBounds)) {
            player1.x() -= speed
            player1.y() -= speed

          }
        }
        if (direction == "awayFromOrigin") {
          if (actorShape.getBoundsInParent.intersects(rectangleBounds)) {
            player1.x() += speed
            player1.y() += speed
          }
        }
      }

    } else if (actor.userData == "player2") {
      for (rectangle <- view.rectangles) {
        val rectangleBounds = rectangle.boundsInParent.value
        if (direction == "backToOrigin") {
          if (actorShape.getBoundsInParent.intersects(rectangleBounds)) {
            player2.x() -= speed
            player2.y() -= speed
          }
        }
        if (direction == "awayFromOrigin") {
          if (actorShape.getBoundsInParent.intersects(rectangleBounds)) {
            player2.x() += speed
            player2.y() += speed
          }
        }
      }
    }

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
        addScore()
      } else {
        nonIntersectingToysBuffer += toy
      }
    }

    view.toys = nonIntersectingToysBuffer.toList
    printWinner()

    def addScore(): Unit = {
      if (actor.userData == "player1") {
        player1Score = player1Score + 1
        view.player1Score.text = player1Score.toString
      } else if (actor.userData == "player2") {
        player2Score = player2Score + 1
        view.player2Score.text = player2Score.toString
      }
    }

    def printWinner(): Unit = {
      if (view.toys.isEmpty) {

        if (player1Score > player2Score) {
          winner = "player1"
        } else if (player1Score < player2Score) {
          winner = "player2"
        }
        isGameOverProperty.value = true
      }
    }

    refreshPage()
  }

  def refreshPage(): Unit = {
    // Schedule a UI update on the JavaFX application thread
    Platform.runLater {
      // Update the view with the modified toys list
      view.content = List(
        view.avatar1,
        view.avatar2
      ) ::: view.rectangles ::: view.toys ::: List(
        view.player1Score,
        view.player2Score,
        view.scoreSeperator
      )
    }
  }

  def resetGame(): Unit = {
    view.keyPressedPlayer1 = Set.empty[KeyCode]
    view.keyPressedPlayer2 = Set.empty[KeyCode]
    view.toys = view.defaultToys
    player1Score = 0
    player2Score = 0
    view.player1Score.text = player1Score.toString
    view.player2Score.text = player2Score.toString
    refreshPage()
    isGameOverProperty.value = false
    winner = null

  }

}
