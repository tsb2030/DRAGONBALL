package eye.main.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import eye.Music;
import eye.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/*메뉴선택을 위한 클래스입니다.
 * main_page.fxml에 연결됨*/
public class mainController implements Initializable{

	//현재 페이인 mainPage와 교체할 페이지인 gameMainPage를 미리 선언
	@FXML
	private AnchorPane gameMainPage,mainPage;
	
	@FXML
	private Button main_record;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	//게임 버튼을 눌렀을 때 동작할 메소드
	public void goGameMain() {
		try {
			//음악 바꾸기
			Main.setMusic("mainMusic.mp3", true);
			//교체할 페이지인 game_main_page.fxml를 가져와서 gameMainPage에 넣어준다.
			gameMainPage = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//페이지 교체
		mainPage.getChildren().setAll(gameMainPage);
	}
	
	public void goRecord() {
		Parent recordPage=null;
		try {
			//음악 바꾸기
			Main.setMusic("mainMusic.mp3", true);
			recordPage = FXMLLoader.load(getClass().getResource("/eye/record/view/recordMain.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 불러올 페이지 지정
		Scene scene = new Scene(recordPage);
		scene.getStylesheets().add(getClass().getResource("/eye/record/view/recordDesign.css").toExternalForm()); // css 지정
		Stage primaryStage = (Stage) main_record.getScene().getWindow(); // 현재 윈도우 가져오기
		primaryStage.setScene(scene);
	}

	public void go2() {
	
	}

	public void go3() {
	
	}		

}
