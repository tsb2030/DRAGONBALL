package eye.game.fiveDotGame;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import eye.Music;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import eye.db.*;
public class GamePageController implements Initializable {

	dbconn db = new dbconn();
	
	// 운동을 위해 필요함
	@FXML
	Circle circle1, circle2, circle3, circle4, circle5;

	@FXML
	AnchorPane gamePage, fiveDotPage;

	@FXML
	Text endText;

	@FXML
	ImageView backToStart;

	public static Stage gamePageStage;

	boolean a = true;

	public void initialize(URL arg0, ResourceBundle arg1) {
		gameStart();

		backToStart.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					fiveDotPage = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
					a = false;

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				gamePage.getChildren().setAll(fiveDotPage);
			}
		});
	}

	public void gameStart() {
		// stage1
		RotateTransition rotateTransition1 = new RotateTransition(Duration.seconds(1), circle1);
		RotateTransition rotateTransition2 = new RotateTransition(Duration.seconds(1), circle2);
		RotateTransition rotateTransition3 = new RotateTransition(Duration.seconds(1), circle3);
		RotateTransition rotateTransition4 = new RotateTransition(Duration.seconds(1), circle4);
		RotateTransition rotateTransition5 = new RotateTransition(Duration.seconds(1), circle5);

		// stage2
		RotateTransition rotateTransition12 = new RotateTransition(Duration.seconds(1), circle1);
		RotateTransition rotateTransition23 = new RotateTransition(Duration.seconds(1), circle2);
		RotateTransition rotateTransition34 = new RotateTransition(Duration.seconds(1), circle3);
		RotateTransition rotateTransition45 = new RotateTransition(Duration.seconds(1), circle4);

		// stage3
		RotateTransition rotateTransition123 = new RotateTransition(Duration.seconds(1), circle1);
		RotateTransition rotateTransition234 = new RotateTransition(Duration.seconds(1), circle2);
		RotateTransition rotateTransition345 = new RotateTransition(Duration.seconds(1), circle3);

		// stage4
		RotateTransition rotateTransition1234 = new RotateTransition(Duration.seconds(1), circle1);
		RotateTransition rotateTransition2345 = new RotateTransition(Duration.seconds(1), circle2);

		// stage5
		RotateTransition rotateTransition12345 = new RotateTransition(Duration.seconds(1), circle1);

		// stage1 점 하나씩

		rotateTransition1.play();
		rotateTransition1.setOnFinished((e) -> {
			circle1.setFill(Color.LIGHTYELLOW);
			circle2.setFill(Color.DARKSEAGREEN);
			rotateTransition2.play();
		});

		rotateTransition2.setOnFinished((e) -> {
			circle2.setFill(Color.LIGHTYELLOW);
			circle3.setFill(Color.DARKSEAGREEN);
			rotateTransition3.play();
		});

		rotateTransition3.setOnFinished((e) -> {
			circle3.setFill(Color.LIGHTYELLOW);
			circle4.setFill(Color.DARKSEAGREEN);
			rotateTransition4.play();
		});

		rotateTransition4.setOnFinished((e) -> {
			circle4.setFill(Color.LIGHTYELLOW);
			circle5.setFill(Color.DARKSEAGREEN);
			rotateTransition5.play();
		});

		rotateTransition5.setOnFinished((e) -> {
			circle5.setFill(Color.LIGHTYELLOW);
			circle1.setFill(Color.DARKSEAGREEN);
			circle2.setFill(Color.DARKSEAGREEN);
			rotateTransition12.play();
		});

		// stage2
		rotateTransition12.setOnFinished((e) -> {
			circle1.setFill(Color.LIGHTYELLOW);
			circle3.setFill(Color.DARKSEAGREEN);
			rotateTransition23.play();
		});

		rotateTransition23.setOnFinished((e) -> {
			circle2.setFill(Color.LIGHTYELLOW);
			circle4.setFill(Color.DARKSEAGREEN);
			rotateTransition34.play();
		});

		rotateTransition34.setOnFinished((e) -> {
			circle3.setFill(Color.LIGHTYELLOW);
			circle5.setFill(Color.DARKSEAGREEN);
			rotateTransition45.play();
		});

		rotateTransition45.setOnFinished((e) -> {
			circle4.setFill(Color.LIGHTYELLOW);
			circle5.setFill(Color.LIGHTYELLOW);
			circle1.setFill(Color.DARKSEAGREEN);
			circle2.setFill(Color.DARKSEAGREEN);
			circle3.setFill(Color.DARKSEAGREEN);
			rotateTransition123.play();
		});

		// stage3
		rotateTransition123.setOnFinished((e) -> {
			circle1.setFill(Color.LIGHTYELLOW);
			circle4.setFill(Color.DARKSEAGREEN);
			rotateTransition234.play();
		});

		rotateTransition234.setOnFinished((e) -> {
			circle2.setFill(Color.LIGHTYELLOW);
			circle5.setFill(Color.DARKSEAGREEN);
			rotateTransition345.play();
		});

		rotateTransition345.setOnFinished((e) -> {
			circle3.setFill(Color.LIGHTYELLOW);
			circle4.setFill(Color.LIGHTYELLOW);
			circle5.setFill(Color.LIGHTYELLOW);
			circle1.setFill(Color.DARKSEAGREEN);
			circle2.setFill(Color.DARKSEAGREEN);
			circle3.setFill(Color.DARKSEAGREEN);
			circle4.setFill(Color.DARKSEAGREEN);
			rotateTransition1234.play();
		});

		// stage4
		rotateTransition1234.setOnFinished((e) -> {
			circle1.setFill(Color.LIGHTYELLOW);
			circle5.setFill(Color.DARKSEAGREEN);

			rotateTransition2345.play();
		});

		rotateTransition2345.setOnFinished((e) -> {
			circle1.setFill(Color.DARKSEAGREEN);

			rotateTransition12345.play();
		});

		// stage5
		rotateTransition12345.setOnFinished((e) -> {
			endText.setVisible(true); // 게임 끝나면 FINISH 문구 뜸
			gamePageStage = (Stage) gamePage.getScene().getWindow();

			//디비에 운동횟수 저장
			SimpleDateFormat sDateForm = new SimpleDateFormat("yyyy/MM/dd");
			Date currentTime = new Date();
			String cTime = sDateForm.format(currentTime);
			try {
				db.insertTimes("fiveDot", cTime);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// 운동 끝나면 팝업(뒤로가기 & 다시하기)
			if (a == true) {
				FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("EndPopup.fxml"));
				Parent root;
				try {
					root = (Parent) fxmlloader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.setTitle("운동 끝!");
					stage.show();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

	}

//	// 뒤로가기 버튼 누르면 게임 설명 페이지로 이동
//	public void exitButton(ActionEvent e) throws IOException {	
//		
//		AnchorPane startPage=FXMLLoader.load(getClass().getResource("StartPage.fxml"));
//		gamePage.getChildren().setAll(startPage);
//		
//	}

}
