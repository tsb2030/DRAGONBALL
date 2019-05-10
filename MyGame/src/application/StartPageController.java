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

public class StartPageController {

	@FXML
	AnchorPane gamePage;
	
	@FXML
	AnchorPane startPage,menuPage;
	
	
	
	// 시작하기 버튼 누르면 게임(운동)페이지로 이동
	public void gamePageButton(ActionEvent event) throws IOException, InterruptedException {
		AnchorPane gamePage=FXMLLoader.load(getClass().getResource("GamePage.fxml"));
		startPage.getChildren().setAll(gamePage);
		
	}

	
	// 뒤로가기 버튼 누르면 게임 고르기 페이지로 이동
	public void exitButton(ActionEvent e) throws IOException {
		
		AnchorPane menuPage=FXMLLoader.load(getClass().getResource("MenuPage.fxml"));
		startPage.getChildren().setAll(menuPage);
		
	}
	
	

}
