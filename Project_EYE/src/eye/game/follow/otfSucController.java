package eye.game.follow;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class otfSucController {
	
	@FXML
	private Button closeBtn,restartPopUpBtn;

		// 팝업창에서 게임 재시작
		public void numGameRestart(ActionEvent event) {
			try {
				Parent gameInfo = FXMLLoader.load(getClass().getResource("gamePageNum.fxml")); // 불러올 페이지 지정
				Scene scene = new Scene(gameInfo);
				scene.getStylesheets().add(getClass().getResource("game.css").toExternalForm()); // css 지정
				Stage primaryStage = (Stage) restartPopUpBtn.getScene().getWindow(); // 현재 윈도우 가져오기
				primaryStage.setScene(scene);
				primaryStage.setTitle("순서대로 따라가기 - 1 to 50");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 팝업창 닫기
		public void closePopUp() {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("../view/game_main_page.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = (Stage) closeBtn.getScene().getWindow();
				scene.getStylesheets().add(getClass().getResource("../../main/controller/application.css").toExternalForm());
				primaryStage.setScene(scene);
			} catch (Exception e) {
				// TODO: handle exception
			}
			Stage stage = (Stage) closeBtn.getScene().getWindow(); // 버튼이 있는 창을 닫는다
			stage.close();
		}
}
