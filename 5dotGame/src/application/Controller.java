package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


//import eye.main.controller.mainController.loadingScreen;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {

	@FXML
	Circle circle1,circle2,circle3,circle4,circle5;
	
	@FXML
	Text endText;
	
	@FXML
	AnchorPane gamePage;
	
	@FXML
	AnchorPane startPage;
	
	@FXML
	Button startButton;
	
	@FXML
	Label gameTitle;
	
	@FXML
	Line titleLine;
		
	
	public void gamePageButton(ActionEvent event) throws IOException, InterruptedException {
		
		//방법 1
//		Parent gamePage = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
//		Scene scene = new Scene(gamePage);
//		Stage primaryStage = (Stage) startPage.getScene().getWindow();
//		primaryStage.setScene(scene);
//		
		//방법 2
		AnchorPane gamePage=FXMLLoader.load(getClass().getResource("GamePage.fxml"));
		startPage.getChildren().setAll(gamePage);
			
		
	}

	
	 
	public void exitButton(ActionEvent e) throws IOException {
		//게임 고르기 페이지로 이동
		
		AnchorPane menuPage=FXMLLoader.load(getClass().getResource("MenuPage.fxml"));
		startPage.getChildren().setAll(menuPage);
//		
		

		
	}
	
	

}
