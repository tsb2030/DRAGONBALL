package eye.game.eyeMovement2;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;

import javafx.animation.PathTransition;
import javafx.animation.Animation.Status;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Text;

public class Selectcontroller implements Initializable {

	static int duration = 4;
	private PathTransition pathTransition1;
	private PathTransition pathTransition2;
	private PathTransition pathTransition3;

	@FXML
	private Text title;

	@FXML
	private ToggleGroup group;

	@FXML
	private ToggleGroup group2;

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
	private Text nextBtn2;

	@FXML
	private ImageView backBtn;

	public void Move1() {

		// 이동 경로 선언
		Path path = new Path();

		// 물체의 시작점
		MoveTo moveTo = new MoveTo(0, 0);

		// 공의 방향을 선언
		LineTo line1 = new LineTo(0, 250);
		LineTo line2 = new LineTo(0, 0);

		// 이동 경로 지정
		path.getElements().add(moveTo);
		path.getElements().addAll(line1, line2);

		// 물체 이동 선언
		pathTransition1 = new PathTransition();
		pathTransition1.setDuration(Duration.seconds(2));
		pathTransition1.setNode(speedCircle1);

		// 이동 경로 받기
		pathTransition1.setPath(path);

		pathTransition1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

		pathTransition1.setCycleCount(10);

		// 애니메이션 실행
		pathTransition1.play();
	}

	public void Move2() {

		Path path = new Path();

		MoveTo moveTo = new MoveTo(0, 0);

		LineTo line3 = new LineTo(0, 250);
		LineTo line4 = new LineTo(0, 0);

		path.getElements().add(moveTo);
		path.getElements().addAll(line3, line4);

		pathTransition2 = new PathTransition();
		pathTransition2.setDuration(Duration.seconds(1));
		pathTransition2.setNode(speedCircle2);

		pathTransition2.setPath(path);

		pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

		pathTransition2.setCycleCount(10);

		pathTransition2.play();
	}

	public void Move3() {

		Path path = new Path();

		MoveTo moveTo = new MoveTo(0, 0);

		LineTo line5 = new LineTo(0, 250);
		LineTo line6 = new LineTo(0, 0);

		path.getElements().add(moveTo);
		path.getElements().addAll(line5, line6);

		pathTransition3 = new PathTransition();
		pathTransition3.setDuration(Duration.seconds(0.8));
		pathTransition3.setNode(speedCircle3);

		pathTransition3.setPath(path);

		pathTransition3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

		pathTransition3.setCycleCount(10);

		pathTransition3.play();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Move1();
		Move2();
		Move3();

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

		nextBtn2.setOnMouseClicked(new EventHandler<Event>() {

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
