package eye.game.eyeMovement1;

import eye.main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PathTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Playcontroller implements Initializable {
	@FXML
	private AnchorPane eyePlayPage1;

	@FXML
	private ImageView backBtn;

	static Stage currentStage;
	private PathTransition pathTransition;
	public static int cnt = 0;
	boolean flag = true;
	int dx = 0;
	int duration = 4;
	int count = 0;

	@FXML
	private Text score;
	@FXML
	private Text title;

	@FXML
	private Button closeBtn;

	@FXML
	private Circle pointer; // 공 객체

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Moving();

		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Parent root = FXMLLoader.load(getClass().getResource("strip_priorPage3.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) eyePlayPage1.getScene().getWindow();
					primaryStage.setScene(scene);
					pathTransition.pause();
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		});
	}

	public void Moving() {

		// Creating a Path
		Path path = new Path();

		// Moving to the starting point
		MoveTo moveTo = new MoveTo(0, 0);

		// 공의 방향을 선언
		ArcTo line1 = new  ArcTo(200, 200, 180, -500, 0, true, false);
		ArcTo line2 = new  ArcTo(200, 200, 180, 0, 0, true, false);
		ArcTo line3 = new  ArcTo(200, 200, 180, 500, 0, true, true);
		ArcTo line4 = new  ArcTo(200, 200, 180, 0, 0, true, true);


		// Adding all the elements to the path
		path.getElements().add(moveTo);
		path.getElements().addAll(line1, line2, line3, line4);

		// Creating the path transition
		pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.seconds(Selectcontroller.duration));
		pathTransition.setNode(pointer);

		// Setting the path for the transition
		pathTransition.setPath(path);
		//시간을 입력받게합니다.
		pathTransition.cycleDurationProperty();
		// Setting the orientation of the path

		// 시간을 순간 순간 받아내어서 공색을 변하게 하게 하는 반복문입니다.
		pathTransition.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
			double rndValue = Math.random();
			double tmp = oldValue.toSeconds();
			if (flag) {
				if ((tmp >= duration * 0.1225 && tmp <= duration * 0.125)
						|| (tmp >= duration * 0.3725 && tmp <= duration * 0.375)
						|| (tmp >= duration * 0.6225 && tmp <= duration * 0.625)
						|| (tmp >= duration * 0.8725 && tmp <= duration * 0.875)) {
					if (rndValue < 0.14) {
						if (rndValue >= 0 && rndValue <= 0.03 && dx != 1) {
							pointer.setFill(Color.GREY);
							dx = 1;
						} else if (rndValue > 0.03 && rndValue <= 0.06 && dx != 2) {
							pointer.setFill(Color.ANTIQUEWHITE);
							dx = 2;

						} else if (rndValue > 0.06 && rndValue <= 0.09 && dx != 3) {
							pointer.setFill(Color.CORAL);
							dx = 3;
						} else if (rndValue > 0.09 && rndValue <= 0.12 && dx != 4) {
							pointer.setFill(Color.WHITE);
							dx = 4;
						} else if (rndValue > 0.12 && rndValue <= 0.14 && dx != 5) {
							pointer.setFill(Color.BLACK);
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
				score.setText(String.valueOf(count));
				count++;
				System.out.println(count);
			}

			if (count == 20 && tmp >= Selectcontroller.duration - 0.015) {
				showEndPopUp();
				score.setText(String.valueOf(count));
			}

		});
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

		pathTransition.setCycleCount(20);
		pathTransition.setDelay(new Duration(000));

		// Playing the animation
		pathTransition.play();


	}

	public void showEndPopUp() {
		currentStage = (Stage) backBtn.getScene().getWindow();
		FXMLLoader another = new FXMLLoader(Main.class.getResource("/eye/game/eyeMovement1/gameQ&A.fxml"));
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

}
