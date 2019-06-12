package eye.rest.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.Music;
import eye.main.Main;
import eye.set.controller.setController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class restController implements Initializable {

	@FXML
	private ImageView backBtn;
	@FXML
	AnchorPane restMainPage, mainPage, closedEyeInfoPage, lookAfarInfoPage, eyeMassageInfoPage, eyeRollingInfoPage,
			warmEyeInfoPage; // 휴식 목록 페이지
//	@FXML
//	ImageView backBtn; // 뒤로가기버튼
	@FXML
	ImageView closeEyeBtn, lookFarBtn, eyeMassageBtn, rollingEyeBtn, blinkBtn, longBreakBtn; // 각 휴식별 실행페이지로 연결할 버튼
	@FXML
	TextFlow text1, text2, text3, text4, text5, text6; // 각 휴식별 텍스트
	// 눈 꼭 감기 - text1, 멀리 보기 - text2, 눈 마사지 - text3, 눈 굴리기 - text4, 손바닥 눈찜질 - text5,
	// 롱브레이크 - text6
	public static Stage currentStage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 눈 꼭 감기
		Text t = new Text("\n정해진 시간동안 눈을 꼭 감아보아요." + "\n휴식 중 속도보다는 움직임 자체에 집중해주세요! ");
		text1.getChildren().add(t);
		// 멀리 보기
		t = new Text("\n화면에서 잠시 눈을 떼고 멀리 있는 사물이나 창문을 바라보아요");
		text2.getChildren().add(t);
		// 눈 마사지
		t = new Text("\n피로에 지친 눈을 문질러 마사지 해주세요." + "\n설명글의 순서에 맞춰서 천천히 따라해주세요!");
		text3.getChildren().add(t);
		// 눈 굴리기
		t = new Text("\n눈동자를 시계방향, 반시계방향으로 굴려보아요." + "\n눈동자의 움직임에 집중하는 것이 중요해요!");
		text4.getChildren().add(t);
		// 손바닥 눈찜질
		t = new Text("\n손바닥 열을 이용하여 눈의 혈액순환을 도와요. " + "\n안구건조증을 예방해요!");
		text5.getChildren().add(t);
	

		// 메인화면으로 돌아가기
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					mainPage = FXMLLoader.load(getClass().getResource("/eye/main/view/main_page.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				restMainPage.getChildren().setAll(mainPage);
			}
		});

	}

	// 눈 꼭 감기 설명 페이지로 이동
	public void goClosedEyeInfo() {
		currentStage = (Stage) backBtn.getScene().getWindow();
		try {
			setController.isGameStart = true;
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			// 음악 바꾸기
			Main.setMusic("mainMusic", true, 1);
			// 교체할 페이지인 ClosedEyeIntro.fxml를 가져와서 restMainPage에 넣어준다.
			closedEyeInfoPage = FXMLLoader.load(getClass().getResource("/eye/rest/view/ClosedEyeInfo.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 페이지 교체
		restMainPage.getChildren().setAll(closedEyeInfoPage);
	}

	// 멀리 보기 설명 페이지로 이동
	public void goLookAfarInfo() {
		currentStage = (Stage) backBtn.getScene().getWindow();
		try {
			setController.isGameStart = true;
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			// 음악 바꾸기
			Main.setMusic("mainMusic", true, 1);
			// 교체할 페이지인 ClosedEyeIntro.fxml를 가져와서 restMainPage에 넣어준다.
			lookAfarInfoPage = FXMLLoader.load(getClass().getResource("/eye/rest/view/LookAfarInfo.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 페이지 교체
		restMainPage.getChildren().setAll(lookAfarInfoPage);
	}

	// 눈 마사지 설명 페이지로 이동
	public void goEyeMassageInfo() {
		currentStage = (Stage) backBtn.getScene().getWindow();
		try {
			setController.isGameStart = true;
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			// 음악 바꾸기
			Main.setMusic("mainMusic", true, 1);
			// 교체할 페이지인 ClosedEyeIntro.fxml를 가져와서 restMainPage에 넣어준다.
			eyeMassageInfoPage = FXMLLoader.load(getClass().getResource("/eye/rest/view/EyeMassageInfo.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 페이지 교체
		restMainPage.getChildren().setAll(eyeMassageInfoPage);
	}

	// 눈 굴리기 설명 페이지로 이동
	public void goEyeRollingInfo() {
		currentStage = (Stage) backBtn.getScene().getWindow();
		try {
			setController.isGameStart = true;
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			// 음악 바꾸기
			Main.setMusic("mainMusic", true, 1);
			// 교체할 페이지인 ClosedEyeIntro.fxml를 가져와서 restMainPage에 넣어준다.
			eyeRollingInfoPage = FXMLLoader.load(getClass().getResource("/eye/rest/view/EyeRollingInfo.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 페이지 교체
		restMainPage.getChildren().setAll(eyeRollingInfoPage);
	}

	// 손바닥 온찜질 설명 페이지로 이동
	public void goWarmEyeInfo() {
		currentStage = (Stage) backBtn.getScene().getWindow();
		try {
			setController.isGameStart = true;
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			// 음악 바꾸기
			Main.setMusic("mainMusic", true, 1);
			// 교체할 페이지인 ClosedEyeIntro.fxml를 가져와서 restMainPage에 넣어준다.
			warmEyeInfoPage = FXMLLoader.load(getClass().getResource("/eye/rest/view/WarmEyeInfo.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 페이지 교체
		restMainPage.getChildren().setAll(warmEyeInfoPage);
	}
}
