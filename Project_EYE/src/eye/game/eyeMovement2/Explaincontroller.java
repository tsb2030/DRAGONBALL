package eye.game.eyeMovement2;

import java.net.URL;
import java.util.ResourceBundle;

import eye.Music;
import eye.main.Main;
import eye.set.controller.setController;
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

public class Explaincontroller implements Initializable {

	@FXML
	private AnchorPane ExplainPage;

	@FXML
	private ImageView nextBtn;

	@FXML
	private ImageView backBtn;

	@FXML
	private Text nextBtn2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 이전 화면 전환 버튼
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					setController.isGameStart = false;
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					Main.setMusic("mainMusic", true, 1);
					Parent root = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) ExplainPage.getScene().getWindow();
					primaryStage.setResizable(false);
					scene.getStylesheets()
							.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
					primaryStage.setScene(scene);
				} catch (Exception e) {
				}

			}

		});
		// 다음 화면 전환 버튼
		nextBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					Parent root = FXMLLoader.load(getClass().getResource("Overview2.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) ExplainPage.getScene().getWindow();
					primaryStage.setResizable(false);
					primaryStage.setScene(scene);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		// 다음 화면 전환 버튼
		nextBtn2.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					Parent root = FXMLLoader.load(getClass().getResource("Overview2.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) ExplainPage.getScene().getWindow();
					primaryStage.setResizable(false);
					primaryStage.setScene(scene);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
	}

}
