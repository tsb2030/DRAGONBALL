package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EndPopupController {
	
	@FXML
	AnchorPane endPopup,menuPage,gamePage;
	

	// 다시하기 버튼 누를 때 실행
	public void tryAgain(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
			Scene scene = new Scene(root);		
			GamePageController.gamePageStage.setScene(scene);
			GamePageController.gamePageStage.setTitle("5점 운동");
			
			Stage currentStage = (Stage) endPopup.getScene().getWindow();
			currentStage.close();
			
		
			}catch (Exception e1) {
				// TODO: handle exception
			}
		
		
	}

	// 뒤로가기 버튼 누르면 실행
	public void backToGameSelectPage(ActionEvent e) throws IOException {
		try {
		Parent root = FXMLLoader.load(getClass().getResource("MenuPage.fxml"));
		Scene scene = new Scene(root);		
		GamePageController.gamePageStage.setScene(scene);
		GamePageController.gamePageStage.setTitle("게임 고르기 페이지");
		
		Stage currentStage = (Stage) endPopup.getScene().getWindow();
		currentStage.close();
		
	
		}catch (Exception e1) {
			// TODO: handle exception
		}
		
		
	}
	

}
