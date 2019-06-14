package eye.main.controller;

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

/*처음 로딩화면을 설정해주기 위한 클래스입니다.
 * loading.fxml에 연결되어있는 컨트롤러*/
public class LoadingController implements Initializable{
	//loading.fxml에 있는 AnchorPane의 id값인 acpane을 가져와서 정의한다.
	@FXML
	private AnchorPane acpane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		new loadingScreen().start();
	}
	
	//페이드아웃 효과와 화면 전환을 위한 클래스
	class loadingScreen extends Thread{
		@Override
		public void run() {
			//페이드아웃 부분
			try {
				Thread.sleep(2000);
				FadeTransition fadeout = new FadeTransition(Duration.seconds(3),acpane);
				fadeout.setFromValue(1);
				fadeout.setToValue(0);
				fadeout.setCycleCount(1);
				fadeout.play();
				Thread.sleep(1500);
				Platform.runLater(new Runnable() {
					
					//화면 전환부분
					@Override
					public void run() {
						Parent root = null;
						try {
						root = FXMLLoader.load(getClass().getResource("/eye/main/view/main_page.fxml"));
						}catch (Exception e) {
							// TODO: handle exception
						}
						Scene scene = new Scene(root);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						Stage primaryStage = new Stage();
						primaryStage.setScene(scene);
						primaryStage.setResizable(false);
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