package eye.main.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import eye.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
/*메뉴선택을 위한 클래스입니다.
 * main_page.fxml에 연결됨*/
public class mainController implements Initializable{

	//현재 페이인 mainPage와 교체할 페이지인 gameMainPage를 미리 선언
	@FXML
	private AnchorPane gameMainPage,mainPage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	//게임 버튼을 눌렀을 때 동작할 메소드
	public void goGameMain() {
		try {
			Main.mainMusic.stopMusic();
			Main.mainMusic.resetNameAudioStream("mainMusic");
			//교체할 페이지인 game_main_page.fxml를 가져와서 gameMainPage에 넣어준다.
			gameMainPage = FXMLLoader.load(getClass().getResource("../../game/view/game_main_page.fxml"));
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException | URISyntaxException e) {
			e.printStackTrace();
		}
		//페이지 교체
		mainPage.getChildren().setAll(gameMainPage);
	}
	
	public void go1() {
		
	}

	public void go2() {
	
	}

	public void go3() {
	
	}		

}
