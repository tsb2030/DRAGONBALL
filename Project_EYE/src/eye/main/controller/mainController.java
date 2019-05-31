package eye.main.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/*메뉴선택을 위한 클래스입니다.
 * main_page.fxml에 연결됨*/
public class mainController implements Initializable{

	//현재 페이인 mainPage와 교체할 페이지인 gameMainPage를 미리 선언
	@FXML
	private AnchorPane gameMainPage,mainPage, restMainPage ,setMainPage;

	@FXML
	private Button main_record, main_relax;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	//게임 버튼을 눌렀을 때 동작할 메소드
	public void goGameMain() {
		try {
			//음악 바꾸기
			Main.setMusic("mainMusic", true);
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
			Main.setMusic("mainMusic", true);
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

	public void goRest() {
		Parent restPage = null;
		try {
			//음악 바꾸기
			Main.setMusic("mainMusic", true);
			//교체할 페이지인 rest_main_page.fxml를 가져와서 gameMainPage에 넣어준다.
			restMainPage = FXMLLoader.load(getClass().getResource("/eye/rest/view/rest_main_page.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//페이지 교체
		mainPage.getChildren().setAll(restMainPage);
	}
	
	public void goSet() {
		Parent restPage = null;
		try {
			//음악 바꾸기
			Main.setMusic("mainMusic", true);
			//교체할 페이지인 rest_main_page.fxml를 가져와서 gameMainPage에 넣어준다.
			restMainPage = FXMLLoader.load(getClass().getResource("/eye/set/view/setOverview.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//페이지 교체
		mainPage.getChildren().setAll(restMainPage);
	}

//    @FXML
//    void goSet(ActionEvent event) throws IOException {
//    	//음악 바꾸기
//		Main.setMusic("mainMusic", true);
//		Font.loadFont(getClass().getResourceAsStream("A소나무.TTF"),14.0);
//		//교체할 페이지인 rest_main_page.fxml를 가져와서 gameMainPage에 넣어준다.
//		setMainPage = FXMLLoader.load(getClass().getResource("/eye/set/view/setOverview.fxml"));
//		setMainPage.getStylesheets().add(getClass().getResource("/eye/set/view/setPage.css").toString());
//		mainPage.getChildren().setAll(setMainPage);
//    }

}
