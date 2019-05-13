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
	// ���� ��ü���� ����
	public static Stage currentStage, dodugeStage;
	// (Stage) startButton.getScene().getWindow();

	@FXML
	private Text TimerLabel;

	@FXML
	private Label gameTitle, ScoreLabel;
	
	@FXML
	AnchorPane gameMainPage, gamePage;
	
	@FXML
	private Line titleLine;

	@FXML
	private ImageView moleImageView, backToStart;


	// ���� �������� �Ѱ��� Ÿ�� ����
	public static int timeTime = 5;

	// ���� �������� �Ѱ��� ���� ����
	public static int score;

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
				dodugeStage = (Stage) TimerLabel.getScene().getWindow();
				FXMLLoader EndGamePopupLoader = new FXMLLoader(
						Main.class.getResource("../game/catchMole/EndGamePopup.fxml")); // �ҷ��� �˾�â
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clock = new Clock();
		
		backToStart.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					gameMainPage = FXMLLoader.load(getClass().getResource("../view/game_main_page.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				gamePage.getChildren().setAll(gameMainPage);
			}
		});

	}

}
