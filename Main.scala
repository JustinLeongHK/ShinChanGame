import controller.{MultiController, SingleController}
import model.Player
import scalafx.application.JFXApp
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.stage.Stage
import view.{
  HomePage,
  MultiGamePlay,
  MultiInfo,
  MultiResultBase,
  MultiResultWin1,
  MultiResultWin2,
  SingleGamePlay,
  SingleInfo,
  SingleResultBase
}
import scalafx.Includes._
import scalafx.scene.Scene

object Main extends JFXApp {

  // Create the main stage
  val mainStage: Stage = new JFXApp.PrimaryStage {
    title = "The World of Shin Chan"
    width = 1500
    height = 900
  }

  // Create scenes and player instances
  val homepageScene = new HomePage()
  val singleInfoScene = new SingleInfo()
  val multiInfoScene = new MultiInfo()
  val player = new Player(1300, 50)
  val player1 = new Player(1300, 50)
  val player2 = new Player(50, 50)
  val singlePlayerScene = new SingleGamePlay(player)
  val singlePlayerLogic =
    new SingleController(player, singlePlayerScene, mainStage)
  val multiPlayerScene = new MultiGamePlay(player1, player2)
  val multiPlayerLogic =
    new MultiController(player1, player2, multiPlayerScene, mainStage)
  val player1WinScene = new MultiResultWin1()
  val player2WinScene = new MultiResultWin2()

  // Set the initial scene and setup gameplay
  mainStage.scene = homepageScene
  setupGamePlay()

  // Functions to handle gameplay in single and multiplayer modes
  playSingle()
  playMulti()

  // Function to setup the event listeners for the homepage scene
  def setupGamePlay(): Unit = {
    homepageScene.onKeyPressed = (e: KeyEvent) => {
      e.code match {
        case KeyCode.X =>
          mainStage.scene = singleInfoScene
        case KeyCode.O =>
          mainStage.scene = multiInfoScene
        case _ =>
      }
    }
  }

  // Function to handle gameplay in single-player mode
  def playSingle(): Unit = {
    singleInfoScene.onKeyPressed = (e: KeyEvent) => {
      e.code match {
        case KeyCode.P =>
          singlePlayerLogic.resetGame()
          mainStage.scene = singlePlayerScene
          singlePlayerLogic.clock()
        case KeyCode.M =>
          mainStage.scene = homepageScene
        case _ =>
      }
    }
  }

  // Function to handle gameplay in multiplayer mode
  def playMulti(): Unit = {
    multiInfoScene.onKeyPressed = (e: KeyEvent) => {
      e.code match {
        case KeyCode.P =>
          mainStage.scene = multiPlayerScene
        case KeyCode.M =>
          mainStage.scene = homepageScene
        case _ =>
      }
    }

    var wonSceneDisplayed = false
    var lostSceneDisplayed = false

    // Function to setup the single-player result scene
    def setupSingleResultScene(resultText: String): Unit = {
      val scene = new SingleResultBase(resultText)

      // Event listener for the single-player result scene
      scene.onKeyPressed = (e: KeyEvent) => {
        e.code match {
          case KeyCode.P =>
            singlePlayerLogic.resetGame()
            mainStage.scene = singlePlayerScene
            if (resultText == "Shin Chan Won !") wonSceneDisplayed = false
            else lostSceneDisplayed = false
          case KeyCode.M =>
            mainStage.scene = homepageScene
            if (resultText == "Shin Chan Won !") wonSceneDisplayed = false
            else lostSceneDisplayed = false
          case _ =>
        }
      }

      mainStage.scene = scene
    }

    // Event listeners for game over events in single and multiplayer modes
    singlePlayerLogic.isGameOverProperty.addListener((_, _, newValue) => {
      if (newValue) {
        if (singlePlayerLogic.isGameWonProperty.value) {
          if (!wonSceneDisplayed) {
            wonSceneDisplayed = true
            setupSingleResultScene("Shin Chan Won !")
          }
        } else {
          if (!lostSceneDisplayed) {
            lostSceneDisplayed = true
            setupSingleResultScene("Shin Chan Lost !")
          }
        }
      }
    })

    multiPlayerLogic.isGameOverProperty.addListener((_, _, newValue) => {
      if (newValue) {
        if (multiPlayerLogic.getWinner == "player1") {
          mainStage.scene = player1WinScene
        } else {
          mainStage.scene = player2WinScene
        }
        setupMultiResultScene(multiPlayerLogic.getWinner)
      }
    })

    // Function to setup the multiplayer result scene
    def setupMultiResultScene(winner: String): Unit = {
      val scene = winner match {
        case "player1" => player1WinScene
        case "player2" => player2WinScene
      }

      scene.onKeyPressed = (e: KeyEvent) => {
        e.code match {
          case KeyCode.P =>
            multiPlayerLogic.resetGame()
            mainStage.scene = multiPlayerScene
          case KeyCode.M =>
            multiPlayerLogic.resetGame()
            mainStage.scene = homepageScene
          case _ =>
        }
      }
    }

    // Show the main stage
    mainStage.show()
  }
}
