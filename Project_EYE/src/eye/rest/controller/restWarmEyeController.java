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

public class restWarmEyeController implements Initializable {
	// closedEye
	@FXML
	AnchorPane restMainPage, warmEyeInfoPage, warmEyeRestPage;
	@FXML
	private ImageView btnBefore, backBtn, pauseBtn, restartBtn;
	@FXML
	private Button warmEyeNextBtn;  // 손바닥 온찜질 실행 페이지로 이동시킬 버튼
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

	// 손바닥 온찜질 설명에서 뒤로가기 누르면 휴식 메인페이지로 이동
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
		warmEyeInfoPage.getChildren().setAll(restMainPage);
	}

	// 손바닥 온찜질 휴식에서 뒤로가기 누르면 휴식 메인페이지로 이동
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
		warmEyeRestPage.getChildren().setAll(restMainPage);
	}

	// 손바닥 온찜질 휴식 페이지로 이동
	public void goWarmEyeRestPage() {
		try {
			// 음악 바꾸기
			Main.setMusic("mainMusic", true);
			// 교체할 페이지인 ClosedEyeGame.fxml를 가져와서 closedEyeIntroPage에 넣어준다.
			warmEyeRestPage = FXMLLoader.load(getClass().getResource("/eye/rest/view/WarmEyeRest.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 페이지 교체
		warmEyeInfoPage.getChildren().setAll(warmEyeRestPage);
	}

}
