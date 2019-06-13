package eye.game.eyeMovement1;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import eye.Music;
import eye.game.eyeMovement1.Playcontroller;
import eye.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import eye.db.*;
public class EndPopUpcontroller {

	dbconn db = new dbconn();
	
	@FXML
	private AnchorPane SuccessPage, EndPopUpPage, stripPage;

	@FXML
	private Button Equal;

	@FXML
	private TextField answer;

	@FXML
	void answerEqual(ActionEvent event) {
		String getAnswer = answer.getText();
		int ganswer = Integer.parseInt(getAnswer);
		
		//현재 날짜 가져오는 부분
		SimpleDateFormat sDateForm = new SimpleDateFormat("yyyy/MM/dd");
		Date currentTime = new Date();
		String cTime = sDateForm.format(currentTime);
		
		if (Playcontroller.cnt == ganswer) {
			try {
				//정답시 디비저장
				System.out.println("정답");
				db.insertUpGame(cTime, "mobius", 1);
				db.insertTimes("mobius", cTime);
				
				Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
				effectMusic.start();
				stripPage = FXMLLoader.load(getClass().getResource("gameSuccess.fxml"));
				EndPopUpPage.getChildren().setAll(stripPage);
				System.out.println(ganswer);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				//오답시 디비저장
				System.out.println("오답");
				db.insertUpGame(cTime, "mobius", 0);
				db.insertTimes("mobius", cTime);
				
				Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
				effectMusic.start();
				stripPage = FXMLLoader.load(getClass().getResource("gameFail.fxml"));
				EndPopUpPage.getChildren().setAll(stripPage);
				System.out.println(ganswer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
