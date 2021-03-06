package eye.main.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.Music;
import eye.main.Main;
import eye.set.controller.setController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*메뉴선택을 위한 클래스입니다.
 * main_page.fxml에 연결됨*/
public class mainController implements Initializable {

	public static Stage currentStage;
	// 현재 페이인 mainPage와 교체할 페이지인 gameMainPage를 미리 선언

	public static boolean isAlarmStart = false;
	public static int doNotAlaram = 1;
	
	@FXML
	private AnchorPane gameMainPage, mainPage, restMainPage, setMainPage;

	@FXML
	private Button main_record, main_relax;

	@FXML
	public static ImageView alarmOnImageView;

	@FXML
	public static ImageView alarmOffImageView;

	@FXML
	void alarmImageViewClick(MouseEvent event) {
		//알람을 설정하지 않았거나 setController.isStop이 true일 경우
		if ((doNotAlaram == 1) || (setController.isStop)) {
			goSet();
			//화면 전환
		}else {
			alarmOnImageView.setVisible(true);
			alarmOffImageView.setVisible(false);
			setController.isPause = false;
			setController.isStop = true;
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 알람 이미지를 어떤 것을 보이게 할 것인지?
//		alarmOnImageView.setOpacity(0);
	}

	// 게임 버튼을 눌렀을 때 동작할 메소드
	public void goGameMain() {
		currentStage = (Stage) main_record.getScene().getWindow();
		try {
			// 음악 바꾸기
			Main.setMusic("mainMusic", true, 1);
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			// 교체할 페이지인 game_main_page.fxml를 가져와서 gameMainPage에 넣어준다.
			gameMainPage = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 페이지 교체
		mainPage.getChildren().setAll(gameMainPage);
	}

	@FXML
	void exersiceButtonMouseEntered(MouseEvent event) {
		Music effectMusic = new Music("generalMouseEnteredEffect", false, 2);
		effectMusic.start();
	}

	public void goRecord() {
		currentStage = (Stage) main_record.getScene().getWindow();
		Parent recordPage = null;
		try {
			// 음악 바꾸기
			Main.setMusic("mainMusic", true, 1);
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();

			recordPage = FXMLLoader.load(getClass().getResource("/eye/record/view/recordMain.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 불러올 페이지 지정
		Scene scene = new Scene(recordPage);
		scene.getStylesheets().add(getClass().getResource("/eye/record/view/recordDesign.css").toExternalForm()); // css
																													// 지정
		Stage primaryStage = (Stage) main_record.getScene().getWindow(); // 현재 윈도우 가져오기
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
	}

	@FXML
	void recordButtonMouseEntered(MouseEvent event) {
		Music effectMusic = new Music("generalMouseEnteredEffect", false, 2);
		effectMusic.start();
	}

	public void goRest() {
		currentStage = (Stage) main_record.getScene().getWindow();
		Parent restPage = null;
		try {
			// 음악 바꾸기
			Main.setMusic("mainMusic", true, 1);
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();

			// 교체할 페이지인 rest_main_page.fxml를 가져와서 gameMainPage에 넣어준다.
			restMainPage = FXMLLoader.load(getClass().getResource("/eye/rest/view/rest_main_page.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 페이지 교체
		mainPage.getChildren().setAll(restMainPage);
	}

	@FXML
	void restButtonMouseEntered(MouseEvent event) {
		Music effectMusic = new Music("generalMouseEnteredEffect", false, 2);
		effectMusic.start();
	}

	public void goSet() {
		currentStage = (Stage) main_record.getScene().getWindow();
		Parent restPage = null;
		try {
			// 음악 바꾸기
			Main.setMusic("mainMusic", true, 1);
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();

			// 교체할 페이지인 rest_main_page.fxml를 가져와서 gameMainPage에 넣어준다.
			restMainPage = FXMLLoader.load(getClass().getResource("/eye/set/view/setOverview.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 페이지 교체
		mainPage.getChildren().setAll(restMainPage);

	}

	@FXML
	void setButtonMouseEntered(MouseEvent event) {
		Music effectMusic = new Music("generalMouseEnteredEffect", false, 2);
		effectMusic.start();
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
