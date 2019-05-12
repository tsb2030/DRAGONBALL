package eye.game.catchMole;

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
    		Stage primaryStage = (Stage) startButton.getScene().getWindow();  // 현재 윈도우 가져오기
			primaryStage.setScene(scene);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void startButtonAction(ActionEvent event) {
    	try {
			Parent DodugeGame2Page = FXMLLoader.load(getClass().getResource("DodugeGame2.fxml"));
			Scene scene = new Scene(DodugeGame2Page);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());  // css 지정
			Stage primaryStage = (Stage) startButton.getScene().getWindow();  // 현재 윈도우 가져오기
			primaryStage.setScene(scene);
			primaryStage.setTitle("DodugeGame2Page");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    

}
