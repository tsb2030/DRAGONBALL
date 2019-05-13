package eye.game.catchBall;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import eye.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class IntroducePageController {

    @FXML
    private Button backButton;

    @FXML
    private JFXButton startButton;

    @FXML
    void backButtonAction(ActionEvent event) {
    	//backButtonEvent!!
    	try {

			Main.mainMusic.stopMusic();
			Main.mainMusic.resetNameAudioStream("LaLaLa");
    		Parent gameMainPage = FXMLLoader.load(getClass().getResource("../view/game_main_page.fxml"));
    		Scene scene = new Scene(gameMainPage);
    		scene.getStylesheets().add(getClass().getResource("../../main/controller/application.css").toExternalForm());
    		Stage primaryStage = (Stage) startButton.getScene().getWindow();  // ���� ������ ��������
			primaryStage.setScene(scene);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void startButtonAction(ActionEvent event) {
    	try {
			Parent selectSpeedPage = FXMLLoader.load(getClass().getResource("SelectSpeedPage.fxml"));
			Scene scene = new Scene(selectSpeedPage);
			scene.getStylesheets().add(getClass().getResource("../../main/controller/application.css").toExternalForm());
			Stage primaryStage = (Stage) startButton.getScene().getWindow();  // ���� ������ ��������
			primaryStage.setScene(scene);
			primaryStage.setTitle("setSpeed");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    

}
