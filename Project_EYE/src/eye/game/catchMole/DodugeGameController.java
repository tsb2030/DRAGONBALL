package eye.game.catchMole;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.Music;
import eye.main.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

	public static Stage dodugeStage; // 현재 스테이지

	@FXML
	private Text TimerLabel; // 시간을 보여줄 fxml변수

	@FXML
	private Label gameTitle, ScoreLabel; // 게임 제목, 점수 변수

	@FXML
	AnchorPane gameMainPage, gamePage; // 메인 페이지 게임 페이지 앵커팬

	@FXML
	private Line titleLine;

	@FXML
	private ImageView moleImageView, backToStart, pauseImageView; // 각 버튼의 이미지

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

	@FXML // 두더지를 클릭했을 때
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
			// 닥트리오 이벤트 체크
			countSameUpDoduge(r1, r2);
			GridPane.setConstraints(moleImageView, r1, r2);
			ScoreLabel.setText("Score: " + score);
		}

	}

	// 일시 정지 구현
	public void pause() {
		clock.animation.pause();
		isPause = true;
		gamePage.setOpacity(0.45);
		pauseImageView.setOpacity(1); // 이 버튼만 진하게 보여서 switch형이란 것을 자동으로 알 수 있게 해줌!
	}

	// 재실행 구현
	public void restart() {
		clock.animation.play();
		isPause = false;
		gamePage.setOpacity(1);
	}

	@FXML // 일시정지 버튼을 눌렀을 경우 이벤트
	void pauseAction(MouseEvent event) {
		Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
		effectMusic.start();
		pause();
	}

	@FXML // 재시작 버튼을 눌렀을 경우 이벤트
	void restartAction(MouseEvent event) {
		Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
		effectMusic.start();
		restart();
	}

	@FXML // 뒤로가기 버튼을 눌렀을 경우
	void backToStartAction(MouseEvent event) {
		if (isPause == false) {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			try {
				score = 0;
				gameMainPage = FXMLLoader.load(getClass().getResource("IntroducePage.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			gamePage.getChildren().setAll(gameMainPage);
		}

	}

	// 시간 이너 클래스 구현
	public class Clock extends Pane {

		private Timeline animation;
		private int timeTmp = 30;
		private String S = "";
		private boolean flag = false;

		public Clock() {
			animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timeLabel())); // 1초가 지날때 마다 timeLabel메소드를
																							// 실행
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();
		}

		private void timeLabel() {
			if (timeTmp > 0) {
				timeTmp--;
				timeTime = timeTmp;
			}
			if (timeTime <= 0 && flag == false) {

				// 게임이 끝나는 부분

				clock.animation.stop();
				dodugeStage = (Stage) TimerLabel.getScene().getWindow();
				dodugeStage.setResizable(false);
				FXMLLoader EndGamePopupLoader = new FXMLLoader(
						Main.class.getResource("/eye/game/catchMole/EndGamePopup.fxml"));
				try {
					AnchorPane EndGamePopupPane = (AnchorPane) EndGamePopupLoader.load();
					Scene EndGamePopupScene = new Scene(EndGamePopupPane);
					EndGamePopupScene.getStylesheets()
							.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
					Stage stage = new Stage();
					stage.setResizable(false);
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
	}

}
