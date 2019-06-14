package eye.game.follow;

import java.net.URL;
import java.util.ResourceBundle;

import eye.Music;
import eye.main.Main;
import eye.set.controller.setController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//각각의 게임 들어가기 전까지 조작하는 컨트롤러
public class Controller implements Initializable {
	@FXML
	private ImageView btnBefore;
	@FXML
	private ImageView backBtn;
	@FXML
	private ImageView pauseBtn;
	@FXML
	private ImageView restartBtn;
	@FXML
	private Button nextBtn;
	@FXML
	private Button korBtn;
	@FXML
	private Button engBtn;
	@FXML
	private Button numBtn;
	@FXML
	private Button closeBtn;
	@FXML
	private Button restartPopUpBtn;

	// ㄱ to ㅎ 설명 페이지로 전환
	public void goToKorGameIntro(ActionEvent event) {
		try {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			Parent gameInfo = FXMLLoader.load(getClass().getResource("gameIntroKor.fxml")); // 불러올 페이지 지정
			Scene scene = new Scene(gameInfo);
			scene.getStylesheets().add(getClass().getResource("game.css").toExternalForm()); // css 지정
			Stage primaryStage = (Stage) korBtn.getScene().getWindow(); // 현재 윈도우 가져오기
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("순서대로 따라가기 - ㄱ to ㅎ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// A to Z 설명 페이지로 전환
	public void goToEngGameIntro(ActionEvent event) {
		try {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			Parent gameInfo = FXMLLoader.load(getClass().getResource("gameIntroEng.fxml")); // 불러올 페이지 지정
			Scene scene = new Scene(gameInfo);
			scene.getStylesheets().add(getClass().getResource("game.css").toExternalForm()); // css 지정
			Stage primaryStage = (Stage) engBtn.getScene().getWindow(); // 현재 윈도우 가져오기
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("순서대로 따라가기 - A to Z");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 1 to 50 설명 페이지로 전환
	public void goToNumGameIntro(ActionEvent event) {
		try {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			Parent gameInfo = FXMLLoader.load(getClass().getResource("gameIntroNum.fxml")); // 불러올 페이지 지정
			Scene scene = new Scene(gameInfo);
			scene.getStylesheets().add(getClass().getResource("game.css").toExternalForm()); // css 지정
			Stage primaryStage = (Stage) numBtn.getScene().getWindow(); // 현재 윈도우 가져오기
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("순서대로 따라가기 - 1 to 50");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게임 선택 페이지로 전환
	public void goBackToChoice(MouseEvent event) {
		try {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			Parent gameInfo = FXMLLoader.load(getClass().getResource("gameChoice.fxml")); // 불러올 페이지 지정
			Scene scene = new Scene(gameInfo);
			scene.getStylesheets().add(getClass().getResource("intro.css").toExternalForm()); // css 지정
			Stage primaryStage = (Stage) backBtn.getScene().getWindow(); // 현재 윈도우 가져오기
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("순서대로 따라가기");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	 1 to 50 게임 페이지로 전환
	public void numGameStart(ActionEvent event) { // MouseEvent, ActionEvent, 공백 되긴 하는데 느림,, 왜 Event도 느릴까,,,
		try {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			Parent gameInfo = FXMLLoader.load(getClass().getResource("gamePageNum.fxml")); // 불러올 페이지 지정
			Scene scene = new Scene(gameInfo);
			scene.getStylesheets().add(getClass().getResource("game.css").toExternalForm()); // css 지정
			Stage primaryStage = (Stage) nextBtn.getScene().getWindow(); // 현재 윈도우 가져오기
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("순서대로 따라가기 - 1 to 50");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게임 선택 페이지로 전환
	public void goBackToInfoNum(MouseEvent event) {
		try {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			Parent gameInfo = FXMLLoader.load(getClass().getResource("gameIntroNum.fxml")); // 불러올 페이지 지정
			Scene scene = new Scene(gameInfo);
			scene.getStylesheets().add(getClass().getResource("intro.css").toExternalForm()); // css 지정
			Stage primaryStage = (Stage) btnBefore.getScene().getWindow(); // 현재 윈도우 가져오기
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("순서대로 따라가기 - 1 to 50");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				try {
					setController.isGameStart = false;
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					Main.setMusic("mainMusic", true, 1);
					Parent root = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage) backBtn.getScene().getWindow();
					scene.getStylesheets()
							.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
					primaryStage.setResizable(false);
					primaryStage.setScene(scene);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	}
}
