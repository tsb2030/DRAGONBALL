package eye.main.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class mainController implements Initializable{

	@FXML
	private AnchorPane gameMainPage,mainPage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	public void goGameMain() {
		try {
			gameMainPage = FXMLLoader.load(getClass().getResource("../../game/view/game_main_page.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainPage.getChildren().setAll(gameMainPage);
	}
	
	public void go1() {
		
	}

	public void go2() {
	
	}

	public void go3() {
	
	}		

}
