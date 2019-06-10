package eye.game.eyeMovement1;

import java.net.URL;
import java.util.ResourceBundle;

import eye.Music;
import eye.main.Main;
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

	@FXML
	private Text title;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					Main.setMusic("mainMusic", true, 1);
					Parent root = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) ExplainPage.getScene().getWindow();
					scene.getStylesheets()
							.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
					primaryStage.setScene(scene);
				} catch (Exception e) {
				}

			}

		});
		nextBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					Parent root = FXMLLoader.load(getClass().getResource("Overview.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) ExplainPage.getScene().getWindow();
					primaryStage.setScene(scene);
				} catch (Exception e) {
				}

			}
		});

		nextBtn2.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					Parent root = FXMLLoader.load(getClass().getResource("Overview.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) ExplainPage.getScene().getWindow();
					primaryStage.setScene(scene);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
	}

}
