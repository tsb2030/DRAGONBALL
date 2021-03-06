package eye.record.controller;

import java.net.URL;
import java.util.ResourceBundle;

import eye.main.Main;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class eyeExamcontroller implements Initializable{
	@FXML
	private AnchorPane eyePlayPage;

	@FXML
	private ImageView backBtn, nextBtn, nextBtn1;

	@FXML
	private Text nextBtn2, nextBtn21,title,score,score1,score2,score3;


	public int cnt = 1;
	public static double Vis = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Main.setMusic("mainMusic", true, 1);
					Parent root = FXMLLoader.load(getClass().getResource("/eye/record/view/ExamExplain.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) eyePlayPage.getScene().getWindow();
					scene.getStylesheets()
							.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
					scene.getStylesheets()
					.add(getClass().getResource("/eye/record/view/recordDesign.css").toExternalForm());
					primaryStage.setResizable(false);
					primaryStage.setScene(scene);
				} catch (Exception e) {
				}

			}

		});
		
		
		Exam();

		nextBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Exam2();
					cnt++;
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		nextBtn2.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Exam2();
					cnt++;
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		
		nextBtn1.setOnMouseClicked(new EventHandler<Event>() {
			FXMLLoader another = new FXMLLoader(Main.class.getResource("/eye/record/view/ExamEndPopup.fxml"));
			@Override
			public void handle(Event event) {

				try {
					AnchorPane anotherPage = (AnchorPane) another.load();
					Scene anotherScene = new Scene(anotherPage);
					Stage stage = new Stage();
					stage.setResizable(false);
					stage.setScene(anotherScene);
					stage.show();
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		
		nextBtn21.setOnMouseClicked(new EventHandler<Event>() {
			FXMLLoader another = new FXMLLoader(Main.class.getResource("/eye/record/view/ExamEndPopup.fxml"));
			@Override
			public void handle(Event event) {

				try {
					AnchorPane anotherPage = (AnchorPane) another.load();
					Scene anotherScene = new Scene(anotherPage);
					Stage stage = new Stage();
					stage.setResizable(false);
					stage.setScene(anotherScene);
					stage.show();
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});

	}

	public void Exam() {

		int Random[] = new int[3];

		for (int i = 0; i < Random.length; i++) {
			Random[i] = (int) (Math.random() * 9) + 1;
			for (int j = 0; j < i; j++) {
				if (Random[i] == Random[j]) {
					i--;
					break;
				}
			}

		}

		for (int i = 0; i < Random.length; i++) {
			System.out.print(Random[i] + "");
		}
		score1.setText(String.valueOf(Random[0]));
		score2.setText(String.valueOf(Random[1]));
		score3.setText(String.valueOf(Random[2]));
		score.setText(String.valueOf(0.02));
		Vis = 0.02;
	}

	public void Exam2() {

		int Random[] = new int[3];

		for (int i = 0; i < Random.length; i++) {
			Random[i] = (int) (Math.random() * 9) + 1;
			for (int j = 0; j < i; j++) {
				if (Random[i] == Random[j]) {
					i--;
					break;
				}
			}

		}

		for (int i = 0; i < Random.length; i++) {
			System.out.print(Random[i] + "");
		}

		if (cnt == 1) {
			Vis = 0.03;
			score.setText(String.valueOf(0.03));
			score1.setStyle("-fx-font-size: 10px;");
			score2.setStyle("-fx-font-size: 10px;");
			score3.setStyle("-fx-font-size: 10px;");
			score1.setText(String.valueOf(Random[0]));
			score2.setText(String.valueOf(Random[1]));
			score3.setText(String.valueOf(Random[2]));
		}

		if (cnt == 2) {
			Vis = 0.10;
			score.setText(String.valueOf(0.10));
			score1.setStyle("-fx-font-size: 5px;");
			score2.setStyle("-fx-font-size: 5px;");
			score3.setStyle("-fx-font-size: 5px;");
			score1.setText(String.valueOf(Random[0]));
			score2.setText(String.valueOf(Random[1]));
			score3.setText(String.valueOf(Random[2]));
		}

		if (cnt == 3) {
			Vis = 0.13;
			score.setText(String.valueOf(0.13));
			score1.setStyle("-fx-font-size: 2.5px;");
			score2.setStyle("-fx-font-size: 2.5px;");
			score3.setStyle("-fx-font-size: 2.5px;");
			score1.setText(String.valueOf(Random[0]));
			score2.setText(String.valueOf(Random[1]));
			score3.setText(String.valueOf(Random[2]));
		}

		if (cnt == 4) {
			Vis = 0.16;
			score.setText(String.valueOf(0.16));
			score1.setStyle("-fx-font-size: 2px;");
			score2.setStyle("-fx-font-size: 2px;");
			score3.setStyle("-fx-font-size: 2px;");
			score1.setText(String.valueOf(Random[0]));
			score2.setText(String.valueOf(Random[1]));
			score3.setText(String.valueOf(Random[2]));
		}

		if (cnt == 5) {
			Vis = 0.20;
			score.setText(String.valueOf(0.20));
			score1.setStyle("-fx-font-size: 1.5px;");
			score2.setStyle("-fx-font-size: 1.5px;");
			score3.setStyle("-fx-font-size: 1.5px;");
			score1.setText(String.valueOf(Random[0]));
			score2.setText(String.valueOf(Random[1]));
			score3.setText(String.valueOf(Random[2]));
		}

		if (cnt == 6) {
			Vis = 0.25;
			score.setText(String.valueOf(0.25));
			score1.setStyle("-fx-font-size: 1.25px;");
			score2.setStyle("-fx-font-size: 1.25px;");
			score3.setStyle("-fx-font-size: 1.25px;");
			score1.setText(String.valueOf(Random[0]));
			score2.setText(String.valueOf(Random[1]));
			score3.setText(String.valueOf(Random[2]));
		}

		if (cnt == 7) {
			Vis = 0.32;
			score.setText(String.valueOf(0.32));
			score1.setStyle("-fx-font-size: 1px;");
			score2.setStyle("-fx-font-size: 1px;");
			score3.setStyle("-fx-font-size: 1px;");
			score1.setText(String.valueOf(Random[0]));
			score2.setText(String.valueOf(Random[1]));
			score3.setText(String.valueOf(Random[2]));
		}

		if (cnt == 8) {
			Vis = 0.40;
			score.setText(String.valueOf(0.40));
			score1.setStyle("-fx-font-size: 0.9px;");
			score2.setStyle("-fx-font-size: 0.9px;");
			score3.setStyle("-fx-font-size: 0.9px;");
			score1.setText(String.valueOf(Random[0]));
			score2.setText(String.valueOf(Random[1]));
			score3.setText(String.valueOf(Random[2]));
		}

		if (cnt == 9) {
			Vis = 0.50;
			score.setText(String.valueOf(0.50));
			score1.setStyle("-fx-font-size: 0.6px;");
			score2.setStyle("-fx-font-size: 0.6px;");
			score3.setStyle("-fx-font-size: 0.6px;");
			score1.setText(String.valueOf(Random[0]));
			score2.setText(String.valueOf(Random[1]));
			score3.setText(String.valueOf(Random[2]));
		}

		if (cnt == 10) {
			Vis = 0.63;
			score.setText(String.valueOf(0.63));
			score1.setStyle("-fx-font-size: 0.5px;");
			score2.setStyle("-fx-font-size: 0.5px;");
			score3.setStyle("-fx-font-size: 0.5px;");
			score1.setText(String.valueOf(Random[0]));
			score2.setText(String.valueOf(Random[1]));
			score3.setText(String.valueOf(Random[2]));
		}

		if (cnt == 11) {
			Vis = 0.80;
			score.setText(String.valueOf(0.80));
			score1.setStyle("-fx-font-size: 0.4px;");
			score2.setStyle("-fx-font-size: 0.4px;");
			score3.setStyle("-fx-font-size: 0.4px;");
			score1.setText(String.valueOf(Random[0]));
			score2.setText(String.valueOf(Random[1]));
			score3.setText(String.valueOf(Random[2]));
		}

		if (cnt == 12) {
			Vis = 1.00;
			score.setText(String.valueOf(1.00));
			score1.setStyle("-fx-font-size: 0.3px;");
			score2.setStyle("-fx-font-size: 0.3px;");
			score3.setStyle("-fx-font-size: 0.3px;");
			score1.setText(String.valueOf(Random[0]));
			score2.setText(String.valueOf(Random[1]));
			score3.setText(String.valueOf(Random[2]));
		}

	}
	
}
