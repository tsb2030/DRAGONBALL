package eye.game.catchMole;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.main.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DodugeGameController implements Initializable {

	public static Stage dodugeStage;

	@FXML
	private Text TimerLabel;

	@FXML
	private Label gameTitle, ScoreLabel;

	@FXML
	AnchorPane gameMainPage, gamePage;

	@FXML
	private Line titleLine;

	@FXML
	private ImageView moleImageView, backToStart, pauseImageView;

	private boolean isPause = false;
	public static int timeTime = 5;

	public static int score;

	Boolean finished = false;
	Boolean started = true;
	Clock clock;
	private int sameUpDodugeCount = 0;

	// 데이터 베이스에 성취여부를 전달할 staic boolean변수
	public static boolean eyeAchievementCatchMoleValue = false;
	private int sameR1;
	private int sameR2;
	// 같은 자리에서 2번 두더지가 출현 하게 될 경우
		public void countSameUpDoduge(int r1, int r2) {
			if (sameR1 == 0 && sameR2 == 0) {
				sameR1 = r1;
				sameR2 = r2;
			} else {
				if (r1 == sameR1 && r2 == sameR2) {
					sameUpDodugeCount++;
					eyeAchievementCatchMoleValue = eyeAchievementCatchMole();
//					if(eyeAchievementCatchMoleValue == true)
//						이벤트 발생!
				} else
					sameUpDodugeCount = 0;
			}
		}

		public boolean eyeAchievementCatchMole() {
			if (sameUpDodugeCount == 1)
				return true;
			return false;
		}


	@FXML
	void clickMoleAction(MouseEvent event) {
		if (isPause == false) {
			System.out.println(timeTime);

			if (started == true) {
				started = false;
			}
			if (finished == false) {
				score++;
			}
			int r1 = (int) (Math.random() * 8);
			int r2 = (int) (Math.random() * 5);
			//닥트리오 이벤트 체크
			countSameUpDoduge(r1, r2);
			GridPane.setConstraints(moleImageView, r1, r2);
			ScoreLabel.setText("Score: " + score);
		}

	}

	public void pause() {
		clock.animation.pause();
		isPause = true;
		gamePage.setOpacity(0.45);
		pauseImageView.setOpacity(1); // 이 버튼만 진하게 보여서 switch형이란 것을 자동으로 알 수 있게 해줌!
	}

	public void restart() {
		clock.animation.play();
		isPause = false;
		gamePage.setOpacity(1);
	}

	@FXML
	void pauseAction(MouseEvent event) {
		pause();
	}

	@FXML
	void restartAction(MouseEvent event) {
		restart();
	}

	@FXML
	void backToStartAction(MouseEvent event) {
		if (isPause == false) {
			try {
				score = 0;
				gameMainPage = FXMLLoader.load(getClass().getResource("IntroducePage.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			gamePage.getChildren().setAll(gameMainPage);
		}

	}

	public class Clock extends Pane {

		private Timeline animation;
		private int timeTmp = 60;
		private String S = "";
		private boolean flag = false;

		public Clock() {
			animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timeLabel()));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();
		}

		private void timeLabel() {
			if (timeTmp > 0) {
				timeTmp--;
				timeTime = timeTmp;
			}
			if (timeTime <= 0 && flag == false) {
				clock.animation.stop();
				dodugeStage = (Stage) TimerLabel.getScene().getWindow();
				FXMLLoader EndGamePopupLoader = new FXMLLoader(
						Main.class.getResource("/eye/game/catchMole/EndGamePopup.fxml"));
				try {
					AnchorPane EndGamePopupPane = (AnchorPane) EndGamePopupLoader.load();
					Scene EndGamePopupScene = new Scene(EndGamePopupPane);
					EndGamePopupScene.getStylesheets()
							.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
					Stage stage = new Stage();
					stage.setScene(EndGamePopupScene);
					stage.show();
					flag = true;
				} catch (IOException e) {
					e.printStackTrace();
					e.printStackTrace();
				}
			}
			S = "Time: " + timeTmp + "";
			TimerLabel.setText(S);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("java version: " + System.getProperty("java.version"));
		System.out.println("javafx.version: " + System.getProperty("javafx.version"));
		clock = new Clock();

//		backToStart.setOnMouseClicked(new EventHandler<Event>() {
//			@Override
//			public void handle(Event event) {
//				try {
//					gameMainPage = FXMLLoader.load(getClass().getResource("../view/game_main_page.fxml"));
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				gamePage.getChildren().setAll(gameMainPage);
//			}
//		});

	}

}
