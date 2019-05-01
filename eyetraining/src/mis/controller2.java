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
	private boolean pause_control = true; // pause��ư�� Ȱ��ȭ����
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
		alert.setTitle("�Ͻ�����");
		alert.setHeaderText(null);
		alert.setContentText("�׸� �Ͻðڽ��ϱ�?");

		alert.initStyle(StageStyle.UTILITY);
		ButtonType buttonTypeOne = new ButtonType("��");
		ButtonType buttonTypeTwo = new ButtonType("�ƴϿ�");

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

			// Creating line ������ ���� �����մϴ�.
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
			// �ð� ���ɼ�
			pathTransition.cycleDurationProperty();
			// Setting the orientation of the path

			// �ð��� �Է¹޾� �� �ð����� ���� ���� ��ȯ�ϴ� ��
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

			// Ʈ�縦 ������� �ٽ� �ݴ�� ���� �����Դϴ� false�� ������� ���� ������ �ѹ������� �����Դϴ�.

			// Playing the animation
			pathTransition.play();

			if (pathTransition.getStatus() == Status.RUNNING)
				pause_control = true;

		}

	}
}
