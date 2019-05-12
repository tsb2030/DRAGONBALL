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
/*�޴������� ���� Ŭ����
 * main_page.fxml�� �����*/
public class mainController implements Initializable{

	//���� ������ mainPage�� ��ü�� �������� gameMainPage�� �̸� ����
	@FXML
	private AnchorPane gameMainPage,mainPage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	//���� ��ư�� ������ �� ������ �޼ҵ�
	public void goGameMain() {
		try {
			Main.mainMusic.stopMusic();
			Main.mainMusic.resetNameAudioStream("LaLaLa");
			//��ü�� �������� game_main_page.fxml�� �����ͼ� gameMainPage�� �־��ش�.
			gameMainPage = FXMLLoader.load(getClass().getResource("../../game/view/game_main_page.fxml"));
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException | URISyntaxException e) {
			e.printStackTrace();
		}
		//������ ��ü
		mainPage.getChildren().setAll(gameMainPage);
	}
	
	public void go1() {
		
	}

	public void go2() {
	
	}

	public void go3() {
	
	}		

}
