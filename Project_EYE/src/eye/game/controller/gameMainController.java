package eye.game.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import eye.main.Main;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/*게임 메뉴에서 사용되는 클래스
 * game_main_page.fxml에 연결되어있다.*/
public class gameMainController implements Initializable {

	// 화면 전환을 위해 필요한 페이지들을 미리 정의한다.
	@FXML
	AnchorPane gameMainPage, mainPage, oneToFiftyPage, catchBallPage, zigzagPage;

	// 이미지뷰를 버튼화 시키기 위해서 필요한 정의
	@FXML
	ImageView oneToFiftyBtn, backBtn;

	// 운동별 설명문을 넣기 위해서 필요한 정의
	@FXML
	TextFlow oneToFiftyText, mobiusText, zigzagText, fivedotText, catchmoleText, catchballText, findpictureText;

	// 버튼을 미리 정의해주어야 하기 때문에 initialize부분에서 이미지뷰들의 이벤트처리를 한다.
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 각 게임별(7개) 설명 넣기
		// 1to50
		Text t = new Text(
				"<순서대로 따라가기>" + "\n\"다음 문자는 어디에 있지?\"" + "\n시간 내에 모양도 순서도 모두 흩어져 있는 숫자나 문자를 찾아보세요" + "\nenjoy!!");
		oneToFiftyText.getChildren().add(t);

		// 뫼비우스띠
		t = new Text("<뫼비우스 띠>" + "\n\"다음 문자는 어디에 있지?\"" + "\n시간 내에 모양도 순서도 모두 흩어져 있는 숫자나 문자를 찾아보세요" + "\nenjoy!!");
		mobiusText.getChildren().add(t);

		// 지그재그
		t = new Text("<지그재그>" + "\n\"다음 문자는 어디에 있지?\"" + "\n시간 내에 모양도 순서도 모두 흩어져 있는 숫자나 문자를 찾아보세요" + "\nenjoy!!");
		zigzagText.getChildren().add(t);

		// 5점 카드 트레이닝
		t = new Text("<5점 카드 트레이닝>" + "\n\"다음 문자는 어디에 있지?\"" + "\n시간 내에 모양도 순서도 모두 흩어져 있는 숫자나 문자를 찾아보세요" + "\nenjoy!!");
		fivedotText.getChildren().add(t);

		// 두더지잡기
		t = new Text("<두더지 잡기>" + "\n\"다음 문자는 어디에 있지?\"" + "\n시간 내에 모양도 순서도 모두 흩어져 있는 숫자나 문자를 찾아보세요" + "\nenjoy!!");
		catchmoleText.getChildren().add(t);

		// 캐치볼
		t = new Text("<Catch ball>" + "\n\"다음 문자는 어디에 있지?\"" + "\n시간 내에 모양도 순서도 모두 흩어져 있는 숫자나 문자를 찾아보세요" + "\nenjoy!!");
		catchballText.getChildren().add(t);

		// 같은그림 찾기
		t = new Text("<같은 그림 찾기>" + "\n\"다음 문자는 어디에 있지?\"" + "\n시간 내에 모양도 순서도 모두 흩어져 있는 숫자나 문자를 찾아보세요" + "\nenjoy!!");
		findpictureText.getChildren().add(t);

		// 메인화면으로 돌아가기
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					Main.mainMusic.stopMusic();
					Main.mainMusic.resetNameAudioStream("MableJ");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException
						| URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					mainPage = FXMLLoader.load(getClass().getResource("../../main/view/main_page.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				gameMainPage.getChildren().setAll(mainPage);
			}
		});

		// 1to50게임으로 화면전환
		oneToFiftyBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub

				try {

					Main.mainMusic.stopMusic();
					Main.mainMusic.resetNameAudioStream("introMusic");
					oneToFiftyPage = FXMLLoader.load(getClass().getResource("../oneToFifty/oneToFiftyGame.fxml"));

				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				gameMainPage.getChildren().setAll(oneToFiftyPage);
			}
		});

	}

	@FXML
	void catchBallGameAction(MouseEvent event) throws IOException, UnsupportedAudioFileException, LineUnavailableException, URISyntaxException {
		Main.mainMusic.stopMusic();
		Main.mainMusic.resetNameAudioStream("introMusic");
		catchBallPage = FXMLLoader.load(getClass().getResource("../catchBall/IntroducePage.fxml"));
		gameMainPage.getChildren().setAll(catchBallPage);
	}


    @FXML
    void moleGameAction(MouseEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
    	Main.mainMusic.stopMusic();
		Main.mainMusic.resetNameAudioStream("introMusic");
		catchBallPage = FXMLLoader.load(getClass().getResource("../catchMole/IntroducePage.fxml"));
		gameMainPage.getChildren().setAll(catchBallPage);
    }


    @FXML
    void zigzagtrainigAction(MouseEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
    	Main.mainMusic.stopMusic();
		Main.mainMusic.resetNameAudioStream("introMusic");
		zigzagPage = FXMLLoader.load(getClass().getResource("../eyeMovement2/zz_priorPage1.fxml"));
		gameMainPage.getChildren().setAll(zigzagPage);
    }

}
