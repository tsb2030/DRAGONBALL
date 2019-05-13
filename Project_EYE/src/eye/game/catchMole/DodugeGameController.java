package eye.game.catchMole;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.main.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DodugeGameController implements Initializable {
	// 현재 스체이지 저장
	public static Stage currentStage;
	// (Stage) startButton.getScene().getWindow();

	@FXML
	private Text TimerLabel;

	@FXML
	private Label gameTitle;

	@FXML
	private Label ScoreLabel;

	@FXML
	private Line titleLine;

	@FXML
	private ImageView moleImageView;

	@FXML
	private Button moleButton;

	// 다음 패이지에 넘겨줄 타임 변수
	public static int timeTime = 5;

	// 다음 페이지에 넘겨줄 점수 변수
	public static int score;

	public static Stage dodugeStage;

	Boolean finished = false;
	Boolean started = true;
	Clock clock;

	@FXML
	void clickMoleAction(ActionEvent event) {
		System.out.println(timeTime);

		if (started == true) {
			started = false;
		}
		if (finished == false) {
			score++;
		}
		int r1 = (int) (Math.random() * 8);
		int r2 = (int) (Math.random() * 5);
		GridPane.setConstraints(moleImageView, r1, r2);
		GridPane.setConstraints(moleButton, r1, r2);
		ScoreLabel.setText("Score: " + score);

	}

	public class Clock extends Pane {

		private Timeline animation;
		private int timeTmp = 5;
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
				currentStage = (Stage)TimerLabel.getScene().getWindow();
				dodugeStage = (Stage) moleButton.getScene().getWindow();
				FXMLLoader EndGamePopupLoader = new FXMLLoader(
						Main.class.getResource("../game/catchMole/EndGamePopup.fxml")); // 불러올 팝업창
				try {
					AnchorPane EndGamePopupPane = (AnchorPane) EndGamePopupLoader.load();
					Scene EndGamePopupScene = new Scene(EndGamePopupPane);
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
    @FXML
    void backToIntroPage(ActionEvent event) {
    	try {

    		Clock currentClock = this.clock;
    		currentClock.flag = true;
			Parent SelectSpeedPage = FXMLLoader.load(getClass().getResource("IntroducePage.fxml")); // 불러올 페이지 지정
			Scene scene = new Scene(SelectSpeedPage);
//				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());  // css 지정
			Stage primaryStage = (Stage) moleButton.getScene().getWindow(); // 현재 윈도우 가져오기
			primaryStage.setScene(scene);
			primaryStage.setTitle("IntroducePage");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clock = new Clock();

	}

}
