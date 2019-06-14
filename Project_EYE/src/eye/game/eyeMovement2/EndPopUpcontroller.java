package eye.game.eyeMovement2;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import eye.Music;
import eye.db.dbconn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
public class EndPopUpcontroller {

	dbconn db = new dbconn();
	
	@FXML
	private AnchorPane SuccessPage, EndPopUpPage, zigzagPage;

	@FXML
	private Button Equal;

	@FXML
	private TextField answer;

	// 색변환 정답 확인 메소드
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
				try {
					db.insertUpGame(cTime, "zigzag", 1);
					db.insertTimes("zigzag", cTime);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
				effectMusic.start();
				zigzagPage = FXMLLoader.load(getClass().getResource("gameSuccess.fxml"));
				EndPopUpPage.getChildren().setAll(zigzagPage);
				System.out.println(ganswer);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				
				//오답시 디비저장
				try {
					db.insertUpGame(cTime, "zigzag", 0);
					db.insertTimes("zigzag", cTime);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
				effectMusic.start();
				zigzagPage = FXMLLoader.load(getClass().getResource("gameFail.fxml"));
				EndPopUpPage.getChildren().setAll(zigzagPage);
				System.out.println(ganswer);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
