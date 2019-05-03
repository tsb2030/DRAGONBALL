package eye.main.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoadingController implements Initializable{
	@FXML
	private AnchorPane acpane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		new loadingScreen().start();
	}
	
	class loadingScreen extends Thread{
		@Override
		public void run() {
			try {
				Thread.sleep(2000);
				FadeTransition fadeout = new FadeTransition(Duration.seconds(3),acpane);
				fadeout.setFromValue(1);
				fadeout.setToValue(0);
				fadeout.setCycleCount(1);
				fadeout.play();
				Thread.sleep(1500);
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						Parent root = null;
						try {
						root = FXMLLoader.load(getClass().getResource("../view/main_page.fxml"));
						}catch (Exception e) {
							// TODO: handle exception
						}
						Scene scene = new Scene(root);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						Stage primaryStage = new Stage();
						primaryStage.setScene(scene);
						primaryStage.show();
						acpane.getScene().getWindow().hide();
					}
				});
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}