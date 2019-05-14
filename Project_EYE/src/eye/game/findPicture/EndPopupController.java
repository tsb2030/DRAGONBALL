package eye.game.findPicture;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EndPopupController  {

	@FXML
	private Button goMainButton, backGame;
	
	@FXML
	AnchorPane endPopup;

	@FXML
	private Label gameScore;

	@FXML
	void backGameAction(ActionEvent event) {
		try {

			Stage primaryStage = (Stage) backGame.getScene().getWindow();
			primaryStage.close();
			Parent DodugeGame2 = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
			Scene scene = new Scene(DodugeGame2);
		
			GamePageController.bigScore = 0;
			GamePageController.timeTime = 60;
			GamePageController.dodugeStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
    void goMainButtonAction(ActionEvent event) {
	 try {
		System.out.println("되나?");
		Parent root = FXMLLoader.load(getClass().getResource("../view/game_main_page.fxml"));
		Scene scene = new Scene(root);		
		GamePageController.gamePageStage.setScene(scene);
		
		Stage currentStage = (Stage) endPopup.getScene().getWindow();
		currentStage.close();
		
	
		}catch (Exception e1) {
			// TODO: handle exception
		}
    }


}
