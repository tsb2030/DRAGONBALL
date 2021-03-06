package eye.rest.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import eye.Music;
import eye.db.dbconn;
import eye.main.Main;
import eye.main.controller.mainController;
import eye.set.controller.setController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ClosedEyeRestController implements Initializable {
	dbconn db = new dbconn();

	@FXML
	private AnchorPane closedEyeRestPage;

	@FXML
	private HBox titleBox;

	@FXML
	private ImageView backBtn;

	@FXML
	private ImageView pauseBtn;

	@FXML
	private Text title;

	@FXML
	private Text timeLabel;

	@FXML
	private Pane mainPanel;

	public static Stage currentStage;

	public static boolean isPause = false;
	public static boolean isRestart = false;

	public static int timeTime;

	public static Clock clock;
	AnchorPane restMainPage;

	@FXML /* 눈 꼭 감기 실행 페이지에서 뒤로가기 누르면 휴식 메인 페이지로 이동 */
	void goRestMainPage2(MouseEvent event) {
		if (isPause == false) {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			if (setController.isRestStart == true) {
				isPause = true;
				mainController.currentStage.setOpacity(0.45);
				try {
					AnchorPane interruptPopup = FXMLLoader
							.load(getClass().getResource("/eye/rest/view/interruptPopup.fxml"));
					Scene scene = new Scene(interruptPopup);
					Stage stage = new Stage();
					stage.setResizable(false);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				try {
					clock.animation.stop();
					setController.isGameStart = false;
					setController.isRestStart = false;
					// 음악 바꾸기
					Main.setMusic("mainMusic", true, 1);
					// 교체할 페이지인 rest_main_page.fxml를 가져와서 closedEyeIntroPage에 넣어준다.
					restMainPage = FXMLLoader.load(getClass().getResource("/eye/rest/view/rest_main_page.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 페이지 교체
				closedEyeRestPage.getChildren().setAll(restMainPage);
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clock = new Clock();
		if (isRestart == true) {
			clock.animation.play();
		}
	}

	public class Clock extends Pane {

		private Timeline animation;
		private int timeTmp = 25;
		private String S = "";
		private boolean flag = false;

		public Clock() {
			animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
				try {
					timeLabels();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();
		}

		private void timeLabels() throws IOException {
			if (!isPause)
				if (timeTmp > 0) {
					timeTmp--;
				}

			timeTime = timeTmp;
			S = timeTmp + "";
			timeLabel.setText(S);
			// 한 번의 휴식시간을 마쳤는가?
			if (timeTime <= 0 && flag == false) {
				SimpleDateFormat sDateForm = new SimpleDateFormat("yyyy/MM/dd");
				Date currentTime = new Date();
				String cTime = sDateForm.format(currentTime);

				try {
					db.insertTimes("ClosedEyeRest", cTime);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// 알람에 의한 종료인가?
				if (setController.isRestStart == true) {

					// 휴식 알람으로 설정했던 횟수를 모두 마쳤는가?
					if (setController.currentRestCount == setController.totalRestCount) {
						clock.animation.stop();
						currentStage = (Stage) timeLabel.getScene().getWindow();
						FXMLLoader endPopupLoader = new FXMLLoader(
								Main.class.getResource("/eye/rest/view/endPopup.fxml"));
						try {
							AnchorPane endPopupPane = (AnchorPane) endPopupLoader.load();
							Scene endPopupScene = new Scene(endPopupPane);
//							endPopupScene.getStylesheets()
//									.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
							Stage endPopupStage = new Stage();
							endPopupStage.setResizable(false);
							endPopupStage.setScene(endPopupScene);
							endPopupStage.show();
							flag = true;
						} catch (IOException e) {
							e.printStackTrace();
						}
						// 휴식 알람으로 설정했던 횟수가 남았을 경우
					} else {
						clock.animation.stop();
						currentStage = (Stage) closedEyeRestPage.getScene().getWindow();

//						지금 restType이 뭐였는지?
						if (setController.restType == 1) {// short
							setController.isPause = false; // 뒤에 pause상태를 풀어준다.

							// 주사위를 돌린다.
							setController.playShorRest();

						} else if (setController.restType == 2) {
							setController.isPause = false;

							setController.playLongRest();
						} else {
							setController.isPause = false;

							setController.playRotateRest();
						}
					}
					// 본인이 임의로 휴식을 취하는 경우
				} else {
					clock.animation.stop();
					currentStage = (Stage) title.getScene().getWindow();
					FXMLLoader endPopupLoader = new FXMLLoader(Main.class.getResource("/eye/rest/view/endPopup.fxml"));
					try {
						AnchorPane endPopupPane = (AnchorPane) endPopupLoader.load();
						Scene endPopupScene = new Scene(endPopupPane);
//						endPopupScene.getStylesheets()
//								.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
						Stage endPopupStage = new Stage();
						endPopupStage.setResizable(false);
						endPopupStage.setScene(endPopupScene);
						endPopupStage.show();
						flag = true;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}
}
