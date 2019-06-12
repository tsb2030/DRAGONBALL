package eye.game.follow;

import java.io.IOException;
import eye.db.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

import eye.main.Main;
import eye.set.controller.setController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//1to50 팝업창 컨트롤러 - 게임3개 다 사용가능할듯?
public class korPopupController implements Initializable{

	@FXML
	private Button closeBtn,restartPopUpBtn;
	
	@FXML
	private Text timer,result;
	
	@FXML
	private Label first,second,third;

	dbconn db = new dbconn();
	
		// 팝업창에서 게임 재시작
		public void numGameRestart(ActionEvent event) {
			try {
				Parent gameInfo = FXMLLoader.load(getClass().getResource("gameIntroKor.fxml")); // 불러올 페이지 지정
				Scene scene = new Scene(gameInfo);
				scene.getStylesheets().add(getClass().getResource("intro.css").toExternalForm()); // css 지정
				Stage primaryStage = (Stage) restartPopUpBtn.getScene().getWindow(); // 현재 윈도우 가져오기
				korGameController.currentStage.setScene(scene);
				korGameController.currentStage.setTitle("ㄱ to ㅎ Game");
				primaryStage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 팝업창 닫기
		public void closePopUp() {
			try {
				setController.isGameStart = false;
				Main.setMusic("mainMusic", true, 1);
				Parent root = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
				Scene scene = new Scene(root);
				Stage primaryStage = (Stage) closeBtn.getScene().getWindow();
				scene.getStylesheets().add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
				korGameController.currentStage.setScene(scene);
				korGameController.currentStage.setTitle("gameMainPage");
				primaryStage.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			Stage stage = (Stage) closeBtn.getScene().getWindow(); // 버튼이 있는 창을 닫는다
			stage.close();
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			result.setText("게임 시간 : "+String.valueOf(korGameController.result)+"초");
			Double recordData[] = null;
			try {
				recordData = db.getGameData("korGame",0);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Arrays.toString(recordData));
			if(recordData[0]!=null)
				first.setText("1위 : "+Double.toString(recordData[0]));
			else
				first.setText("1위 : --.--");
			if(recordData[1]!=null)
				second.setText("2위 : "+Double.toString(recordData[1]));
			else
				second.setText("2위 : --.--");
			if(recordData[2]!=null)
				third.setText("3위 : "+Double.toString(recordData[2]));
			else
				third.setText("3위 : --.--");
		}
}
