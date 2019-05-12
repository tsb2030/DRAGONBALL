package eye.game.eyeMovement2;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;

import eye.game.eyeMovement2.Playcontroller.moving;
import javafx.animation.PathTransition;
import javafx.animation.Animation.Status;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Selectcontroller implements Initializable {

	static int duration = 4;
	private PathTransition pathTransition1;
	private PathTransition pathTransition2;
	private PathTransition pathTransition3;

	@FXML
	private Circle speedCircle1;

	@FXML
	private Circle speedCircle2;

	@FXML
	private Circle speedCircle3;

	@FXML
	private AnchorPane acpane;

	@FXML
	private JFXRadioButton one;

	@FXML
	private JFXRadioButton two;

	@FXML
	private JFXRadioButton three;

	@FXML
	private ImageView nextBtn;

	@FXML
	private ImageView backBtn;

	class updown1 extends Thread {
		@Override
		public void run() {

			// Creating a Path
			Path path = new Path();

			// Moving to the starting point
			MoveTo moveTo = new MoveTo(0, 0);

			// Creating line 진행할 길을 설정합니다.
			LineTo line1 = new LineTo(0, 250);
			LineTo line2 = new LineTo(0, 0);

			// Adding all the elements to the path
			path.getElements().add(moveTo);
			path.getElements().addAll(line1, line2);

			// Creating the path transition
			pathTransition1 = new PathTransition();
			pathTransition1.setDuration(Duration.seconds(6));
			pathTransition1.setNode(speedCircle1);

			// Setting the path for the transition
			pathTransition1.setPath(path);

			pathTransition1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

			pathTransition1.setCycleCount(20);

			// Playing the animation
			pathTransition1.play();

		}

	}

	class updown2 extends Thread {
		@Override
		public void run() {

			// Creating a Path
			Path path = new Path();

			// Moving to the starting point
			MoveTo moveTo = new MoveTo(0, 0);

			// Creating line 진행할 길을 설정합니다.
			LineTo line1 = new LineTo(0, 250);
			LineTo line2 = new LineTo(0, 0);

			// Adding all the elements to the path
			path.getElements().add(moveTo);
			path.getElements().addAll(line1, line2);

			// Creating the path transition
			pathTransition2 = new PathTransition();
			pathTransition2.setDuration(Duration.seconds(4));
			pathTransition2.setNode(speedCircle2);

			// Setting the path for the transition
			pathTransition2.setPath(path);

			pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

			pathTransition2.setCycleCount(20);

			// Playing the animatio
			pathTransition2.play();

		}

	}

	class updown3 extends Thread {
		@Override
		public void run() {

			// Creating a Path
			Path path = new Path();

			// Moving to the starting point
			MoveTo moveTo = new MoveTo(0, 0);

			// Creating line 진행할 길을 설정합니다.
			LineTo line1 = new LineTo(0, 250);
			LineTo line2 = new LineTo(0, 0);

			// Adding all the elements to the path
			path.getElements().add(moveTo);
			path.getElements().addAll(line1, line2);

			// Creating the path transition
			pathTransition3 = new PathTransition();
			pathTransition3.setDuration(Duration.seconds(2));
			pathTransition3.setNode(speedCircle3);

			// Setting the path for the transition
			pathTransition3.setPath(path);

			pathTransition3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

			pathTransition3.setCycleCount(20);

			// Playing the animation
			pathTransition3.play();

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		new updown1().start();
		new updown2().start();
		new updown3().start();
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Parent root = FXMLLoader.load(getClass().getResource("zz_priorPage1.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) acpane.getScene().getWindow();
					primaryStage.setScene(scene);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		});
		nextBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Parent root = FXMLLoader.load(getClass().getResource("Overview2.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) acpane.getScene().getWindow();
					primaryStage.setScene(scene);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});

		one.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				duration = 6;

			}

		});

		two.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				duration = 4;

			}

		});

		three.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				duration = 2;

			}

		});
	}

}
