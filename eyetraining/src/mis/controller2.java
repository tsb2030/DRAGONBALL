package mis;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.PathTransition;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class controller2 implements Initializable {
	@FXML
	private AnchorPane acpane;

	private PathTransition pathTransition;
	private boolean pause_control = true; // pause버튼의 활성화조건
	int cnt = 0;
	boolean flag = true;
	int dx = 0;
	int duration = 5;

	@FXML
	private Circle cir;

	@FXML
	public void pause(ActionEvent event) {
		if (pause_control == true) {
			pathTransition.pause();
			pause_control = false;

		}

		else if (pause_control == false) {

		}
		Alert alert = new Alert(AlertType.NONE);
		alert.setTitle("일시정지");
		alert.setHeaderText(null);
		alert.setContentText("그만 하시겠습니까?");

		alert.initStyle(StageStyle.UTILITY);
		ButtonType buttonTypeOne = new ButtonType("예");
		ButtonType buttonTypeTwo = new ButtonType("아니오");

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			// ... user chose OK
			pathTransition.pause();
			pause_control = false;
		} else if (result.get() == buttonTypeTwo) {
			// ... user chose CANCEL or closed the dialog
			pathTransition.play();
			pause_control = true;
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		new moving().start();
	}

	class moving extends Thread {
		@Override
		public void run() {

			// Creating a Path
			Path path = new Path();

			// Moving to the starting point
			MoveTo moveTo = new MoveTo(0, 0);

			// Creating line 진행할 길을 설정합니다.
			LineTo line1 = new LineTo(1220, 0);
			LineTo line2 = new LineTo(0, 600);
			LineTo line3 = new LineTo(1220, 600);
			LineTo line4 = new LineTo(0, 0);

			// Adding all the elements to the path
			path.getElements().add(moveTo);
			path.getElements().addAll(line1, line2, line3, line4);

			// Creating the path transition
			pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.seconds(controller.duration));
			pathTransition.setNode(cir);

			// Setting the path for the transition
			pathTransition.setPath(path);
			// 시간 가능성
			pathTransition.cycleDurationProperty();
			// Setting the orientation of the path

			// 시간을 입력받아 그 시간대의 공의 색을 변환하는 것
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
								cir.setFill(Color.GREY);
								dx = 1;
							} else if (rndValue > 0.03 && rndValue <= 0.06 && dx != 2) {
								cir.setFill(Color.ANTIQUEWHITE);
								dx = 2;
								
							} else if (rndValue > 0.06 && rndValue <= 0.09 && dx != 3) {
								cir.setFill(Color.CORAL);
								dx = 3;
							} else if (rndValue > 0.09 && rndValue <= 0.12 && dx != 4) {
								cir.setFill(Color.WHITE);
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
			});
			pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

			pathTransition.setCycleCount(20);
			pathTransition.setDelay(new Duration(1000));

			// 트루를 햇을경우 다시 반대로 공이 움직입니다 false를 했을경우 공의 방향이 한방향으로 움직입니다.

			// Playing the animation
			pathTransition.play();

			if (pathTransition.getStatus() == Status.RUNNING)
				pause_control = true;

		}

	}
}
