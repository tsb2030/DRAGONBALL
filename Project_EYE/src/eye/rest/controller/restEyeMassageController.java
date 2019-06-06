package eye.rest.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class restEyeMassageController implements Initializable {
	// closedEye
	@FXML
	AnchorPane restMainPage, eyeMassageInfoPage, eyeMassageRestPage;
	@FXML
	private ImageView btnBefore, backBtn, pauseBtn, restartBtn;
	@FXML
	private Button eyeMassageNextBtn;
	@FXML
	private Button closeBtn;
	@FXML
	private Button restartPopUpBtn;
	@FXML
	private Text timeLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	// 눈 마사지 설명에서 뒤로가기 누르면 휴식 메인페이지로 이동
	public void goRestMainPage1() {
		try {
			// 음악 바꾸기
			Main.setMusic("mainMusic", true);
			// 교체할 페이지인 rest_main_page.fxml를 가져와서 closedEyeIntroPage에 넣어준다.
			restMainPage = FXMLLoader.load(getClass().getResource("/eye/rest/view/rest_main_page.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 페이지 교체
		eyeMassageInfoPage.getChildren().setAll(restMainPage);
	}

	// 눈 마사지 휴식에서 뒤로가기 누르면 휴식 메인페이지로 이동
	public void goRestMainPage2() {
		try {
			// 음악 바꾸기
			Main.setMusic("mainMusic", true);
			// 교체할 페이지인 rest_main_page.fxml를 가져와서 closedEyeIntroPage에 넣어준다.
			restMainPage = FXMLLoader.load(getClass().getResource("/eye/rest/view/rest_main_page.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 페이지 교체
		eyeMassageRestPage.getChildren().setAll(restMainPage);
	}

	// 눈 마사지 휴식 페이지로 이동
	public void goEyeMassageRestPage() {
		try {
			// 음악 바꾸기
			Main.setMusic("mainMusic", true);
			// 교체할 페이지인 ClosedEyeGame.fxml를 가져와서 closedEyeIntroPage에 넣어준다.
			eyeMassageRestPage = FXMLLoader.load(getClass().getResource("/eye/rest/view/EyeMassageRest.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 페이지 교체
		eyeMassageInfoPage.getChildren().setAll(eyeMassageRestPage);
	}

}
