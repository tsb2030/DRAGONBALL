package eye.game.eyeMovement2;

import java.net.URL;
import java.util.ResourceBundle;

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
	private AnchorPane acpane;

	@FXML
	private ImageView nextBtn;

	@FXML
	private ImageView backBtn;
	
	@FXML
	private Text nextBtn2;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				try {
					Parent root = FXMLLoader.load(getClass().getResource("../../game/view/game_main_page.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) acpane.getScene().getWindow();
					scene.getStylesheets().add(getClass().getResource("../../main/controller/application.css").toExternalForm());
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
					Parent root = FXMLLoader.load(getClass().getResource("zz_priorPage2.fxml"));
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
					Parent root = FXMLLoader.load(getClass().getResource("zz_priorPage2.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) acpane.getScene().getWindow();
					primaryStage.setScene(scene);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
	}

}
