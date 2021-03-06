package eye.game.catchBall;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.Music;
import eye.main.Main;
import eye.set.controller.setController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class IntroducePageController implements Initializable {

	@FXML
	AnchorPane gamePage, catchBallPage, gameMainPage;

	@FXML
	Pane startButton;

	@FXML
	ImageView backButton;

	@FXML
	Button easyButton, normalButton, hardButton;

	public static double gameSpeed = 0.0; // second
	@FXML
	private Pane easyPane;

	@FXML
	private Pane hardPane;

	@FXML
	private Pane normalPane;

	@Override// 초기화 할 때 실행
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("java version: " + System.getProperty("java.version"));
		System.out.println("javafx.version: " + System.getProperty("javafx.version"));

		backButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				try {
					setController.isGameStart = false;
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();

					Main.setMusic("mainMusic", true, 1);
					gameMainPage = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				catchBallPage.getChildren().setAll(gameMainPage);
			}
		});

	}

	@FXML	//쉬움 난이도
	void easyPaneClickEvent(MouseEvent event) {
		gameSpeed = 5;
		CatchballGameController.bigScore = 0;
		try {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();

			Parent CatchBallGame = FXMLLoader.load(getClass().getResource("CatchballGame.fxml")); // �ҷ��� ������ ����
			Scene scene = new Scene(CatchBallGame);
			scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
			Stage primaryStage = (Stage) easyPane.getScene().getWindow(); // ���� ������ ��������
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("CatchBallGame!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML//어려움 난이도
	void hardPaneClickEvent(MouseEvent event) {
		gameSpeed = 3;
		CatchballGameController.bigScore = 0;
		try {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();

			Parent CatchBallGame = FXMLLoader.load(getClass().getResource("CatchballGame.fxml")); // �ҷ��� ������ ����
			Scene scene = new Scene(CatchBallGame);
			scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
			Stage primaryStage = (Stage) easyPane.getScene().getWindow(); // ���� ������ ��������
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("CatchBallGame!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML		//보통난이도
	void normalPaneClickEvent(MouseEvent event) {
		gameSpeed = 4;
		CatchballGameController.bigScore = 0;
		try {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();

			Parent CatchBallGame = FXMLLoader.load(getClass().getResource("CatchballGame.fxml")); // �ҷ��� ������ ����
			Scene scene = new Scene(CatchBallGame);
			scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
			Stage primaryStage = (Stage) easyPane.getScene().getWindow(); // ���� ������ ��������
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("CatchBallGame!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
