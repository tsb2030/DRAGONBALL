package eye.game.eyeMovement2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.Music;
import eye.db.AchievementDB;
import eye.main.Main;
import javafx.animation.PathTransition;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Playcontroller implements Initializable {
	AchievementDB aDB = new AchievementDB();

	@FXML
	private AnchorPane eyePlayPage;

	@FXML
	private ImageView backBtn;

	public static Stage currentStage;
	private PathTransition pathTransition;
	public static int cnt = 0;
	boolean flag = true;
	int dx = 0;
	int duration = 4;
	int count = 0;
	double tmp = 0;
	double i = 4;
	int cyclecnt = 20;
	int count2 = 0;
	public static boolean eyeAchievementFreshValue = false;

	@FXML
	private Text score;
	@FXML
	private Text title;

	@FXML
	private Button closeBtn;

	@FXML
	private Circle cir;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Moving();

		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					Parent root = FXMLLoader.load(getClass().getResource("zz_priorPage1.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) eyePlayPage.getScene().getWindow();
					primaryStage.setScene(scene);
					pathTransition.pause();
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		});
	}

	// 물체 이동 메소드
	public void Moving() {

		// 이동 경로 선언
		Path path = new Path();

		// 물체의 시작점
		MoveTo moveTo = new MoveTo(0, 0);

		// 공의 방향을 선언
		LineTo line1 = new LineTo(1200, 0);
		LineTo line2 = new LineTo(0, 550);
		LineTo line3 = new LineTo(1200, 550);
		LineTo line4 = new LineTo(0, 0);

		// 이동 경로 지정
		path.getElements().add(moveTo);
		path.getElements().addAll(line1, line2, line3, line4);

		// 물체 이동 선언
		pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.seconds(i));
		pathTransition.setNode(cir);

		// 이동 경로 받기
		pathTransition.setPath(path);
		// 시간을 입력받게합니다.
		pathTransition.cycleDurationProperty();
		// Setting the orientation of the path

		// 시간을 순간 순간 받아내어서 공색을 변하게 하게 하는 반복문입니다.
		pathTransition.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
			double rndValue = Math.random();
			tmp = oldValue.toSeconds();
			if (flag) {
				if ((tmp >= duration * 0.1225 && tmp <= duration * 0.125)
						|| (tmp >= duration * 0.3725 && tmp <= duration * 0.375)
						|| (tmp >= duration * 0.6225 && tmp <= duration * 0.625)
						|| (tmp >= duration * 0.8725 && tmp <= duration * 0.875)) {
					if (rndValue < 0.14) {
						if (rndValue >= 0 && rndValue <= 0.03 && dx != 1) {
							cir.setFill(Color.GREY);
							dx = 1;
						} else if (rndValue > 0.03 && rndValue <= 0.06 && dx != 2) {
							cir.setFill(Color.SEAGREEN);
							dx = 2;

						} else if (rndValue > 0.06 && rndValue <= 0.09 && dx != 3) {
							cir.setFill(Color.CORAL);
							dx = 3;
						} else if (rndValue > 0.09 && rndValue <= 0.12 && dx != 4) {
							cir.setFill(Color.DEEPPINK);
							dx = 4;
						} else if (rndValue > 0.12 && rndValue <= 0.14 && dx != 5) {
							cir.setFill(Color.BLACK);
							dx = 5;
						} else {
							cnt--;
						}
						cnt++;
						flag = false;
						System.out.println(cnt);
						System.out.println(oldValue.toSeconds());
					}
				}
			}
			if (tmp >= 0 && tmp <= 0.05) {
				flag = true;

			}
			if (tmp == 0) {

				score.setText(String.valueOf(count2));
				System.out.println(count2);
				count++;
				count2++;
			}

			if (count2 == 20 && tmp >= i - 0.015) {
				eyeAchievementFreshValue = true;
				aDB.ach();
				showEndPopUp();
				score.setText(String.valueOf(count2));

			}

		});
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

		pathTransition.setCycleCount(cyclecnt);

		// 애니메이션 실행
		pathTransition.play();

	}

	// 트레이닝 종료후 확인 팝업
	public void showEndPopUp() {
		currentStage = (Stage) backBtn.getScene().getWindow();
		Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
		effectMusic.start();
		FXMLLoader another = new FXMLLoader(Main.class.getResource("/eye/game/eyeMovement2/gameQ&A.fxml"));
		try {
			AnchorPane anotherPage = (AnchorPane) another.load();
			//
			Scene anotherScene = new Scene(anotherPage);
			Stage stage = new Stage();
			stage.setScene(anotherScene);
			stage.show();
			//
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void speedSound(MouseEvent event) {
		Music effectMusic = new Music("generalMouseEnteredEffect", false, 2);
		effectMusic.start();
	}

	@FXML
	void SpeedDown(ActionEvent event) {
		i = i * 1.1;
		count--;
		count2--;
		pathTransition.setDuration(Duration.seconds(i));
		pathTransition.stop();
		System.out.println("down카운트" + count);
		cyclecnt = cyclecnt - count;
		System.out.println("남은 회전수" + cyclecnt);
		pathTransition.setCycleCount(cyclecnt);
		count = 0;
		pathTransition.play();
		pathTransition.jumpTo(Duration.seconds(tmp * 1.1));
	}

	@FXML
	void SpeedUp(ActionEvent event) {
		i = i * 0.9;
		pathTransition.setDuration(Duration.seconds(i));
		count2--;
		count--;
		pathTransition.stop();
		System.out.println("up카운트" + count);
		cyclecnt = cyclecnt - count;
		System.out.println("남은 회전수" + cyclecnt);
		pathTransition.setCycleCount(cyclecnt);
		count = 0;
		pathTransition.play();
		pathTransition.jumpTo(Duration.seconds(tmp * 0.9));
	}

}
