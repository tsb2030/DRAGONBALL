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

/*게임 메뉴에서 사용되는 클래스입니다.
 * game_main_page.fxml에 연결되어있다.*/
public class gameMainController implements Initializable {
	// 화면 전환을 위해 필요한 페이지들을 미리 정의한다.
	@FXML
	AnchorPane gameMainPage, mainPage,followPage, catchBallPage, zigzagPage,fiveDotPage,findPictureStart,stripPage;

	// 이미지뷰를 버튼화 시키기 위해서 필요한 정의
	@FXML
	ImageView followBtn, backBtn, fiveDotBtn,catchBallBtn,findPictureBtn;

	// 운동별 설명문을 넣기 위해서 필요한 정의
	@FXML
	TextFlow followText, mobiusText, zigzagText, fivedotText, catchmoleText, catchballText, findpictureText;

	// 버튼을 미리 정의해주어야 하기 때문에 initialize부분에서 이미지뷰들의 이벤트처리를 한다.
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 각 게임별(7개) 설명 넣기
		// 1to50
		Text t = new Text(
				"<순서대로 따라가기>" + "\n\"다음 문자는 어디에 있지?\"" + "\n정해진 시간 안에 흩어져 있는 숫자나 문자를 순서대로 찾아보아요"
						+ "\n\nTip! 빨리 움직일수록  눈운동 효과 up!");
		followText.getChildren().add(t);

		// 뫼비우스띠
		t = new Text("<뫼비우스 띠>" + "\n\"세상이 빙-빙- 돈다\"" + "\n총 20회 동안 진행되는 첫번째 시선 이동 트레이닝으로, ∞모양을 따라 움직입니다"+ "\n\nTip! 본인의 취향에 맞게 진행 속도를 조절하세요!");
		mobiusText.getChildren().add(t);

		// 지그재그
		t = new Text("<지그재그>" + "\n\"눈을 뗄 수가 없네?\"" + "\n총 20회동안 진행되는 두번째 시선 이동 트레이닝으로, Z자 모양으로 움직입니다."+ "\n\nTip! 본인의 취향에 맞게 진행 속도를 조절하세요!");
		zigzagText.getChildren().add(t);

		// 5점 카드 트레이닝
		t = new Text("<5점 카드 트레이닝>" + "\n\"눈의 시선이 꼬인 느낌이야\"" + "\n시력 향상 효과가 있다고 알려진 \"15점 카드 트레이닝\"에서 착안한 운동이에요 " +
						"\n\nTip! 운동 시 안경이나 렌즈는 빼주세요!");
		fivedotText.getChildren().add(t);

		// 두더지잡기
		t = new Text("<두더지 잡기>" + "\n\"요놈 잡았다!\"" + "\n여러 군데서 나타나는 두더지를 클릭해보세요!"
				+ "   한 마리를 잡으면 바로 다른 곳에서 나타납니다" + "\n\nTip! 두더지를 따라 움직이는 눈동자 감각에 집중해주세요!");
		catchmoleText.getChildren().add(t);

		// 캐치볼
		t = new Text("<Catch ball>" + "\n\"똑같이 따라가면 되는거지?\"" + "\n먼저 그려지는 경로를 따라 방향키로 따라가는 운동이에요"
				+ "\n\nTip! 방향키보다는 공을 따라 눈을 움직이는 감각에 집중해주세요!");
		catchballText.getChildren().add(t);

		// 같은그림 찾기
		t = new Text("<같은 그림 찾기>" + "\n\"이게 이거랑 같은건가?\"" + "\n제시되는 그림과 똑같은 그림을 찾아보세요" + "\n헷갈리는 그림이 많을 거에요~"
				+ "\n\nTip! 눈 운동 효과를 위해서는 모니터와 눈 사이 간격을 일정하게 유지해주세요!");
		findpictureText.getChildren().add(t);

		// 메인화면으로 돌아가기
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
//				try {
//					Main.mainMusic.stopMusic();
//					Main.mainMusic.resetNameAudioStream("introMusic2");
//				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException
//						| URISyntaxException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				try {
					Main.setMusic("introMusic", true);
					mainPage = FXMLLoader.load(getClass().getResource("/eye/main/view/main_page.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				gameMainPage.getChildren().setAll(mainPage);
			}
		});

		// 1to50게임으로 화면전환
		followBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub

				try {
					Main.setMusic("gameMusic", true);
//					Main.mainMusic.stopMusic();
//					Main.mainMusic.resetNameAudioStream("gameMusic");
					followPage = FXMLLoader.load(getClass().getResource("/eye/game/follow/gameChoice.fxml"));

				} catch (IOException  e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				gameMainPage.getChildren().setAll(followPage);
			}
		});

		fiveDotBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub

				try {

					Main.setMusic("gameMusic", true);
//					Main.mainMusic.stopMusic();
//					Main.mainMusic.resetNameAudioStream("gameMusic");
					fiveDotPage = FXMLLoader.load(getClass().getResource("/eye/game/fiveDotGame/StartPage.fxml"));

				} catch (IOException  e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				gameMainPage.getChildren().setAll(fiveDotPage);
			}
		});

		findPictureBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub

				try {

					Main.setMusic("gameMusic", true);
//					Main.mainMusic.stopMusic();
//					Main.mainMusic.resetNameAudioStream("gameMusic");
					findPictureStart = FXMLLoader.load(getClass().getResource("/eye/game/findPicture/StartPage.fxml"));

				} catch (IOException  e) {
					e.printStackTrace();
				}

				gameMainPage.getChildren().setAll(findPictureStart);
			}
		});
	}

	@FXML
	void catchBallGameAction(MouseEvent event) throws IOException, UnsupportedAudioFileException, LineUnavailableException, URISyntaxException {
//		Main.mainMusic.stopMusic();
//		Main.mainMusic.resetNameAudioStream("gameMusic");
		Main.setMusic("gameMusic", true);
		catchBallPage = FXMLLoader.load(getClass().getResource("/eye/game/catchBall/IntroducePage.fxml"));
		gameMainPage.getChildren().setAll(catchBallPage);
	}


    @FXML
    void moleGameAction(MouseEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
//    	Main.mainMusic.stopMusic();
//		Main.mainMusic.resetNameAudioStream("gameMusic");
		Main.setMusic("gameMusic", true);
		catchBallPage = FXMLLoader.load(getClass().getResource("/eye/game/catchMole/IntroducePage.fxml"));
		gameMainPage.getChildren().setAll(catchBallPage);
    }


    @FXML
    void zigzagtrainigAction(MouseEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
//    	Main.mainMusic.stopMusic();
//		Main.mainMusic.resetNameAudioStream("gameMusic");
		Main.setMusic("gameMusic", true);
		zigzagPage = FXMLLoader.load(getClass().getResource("/eye/game/eyeMovement2/zz_priorPage1.fxml"));
		gameMainPage.getChildren().setAll(zigzagPage);
    }

    @FXML
    void striptrainingAction(MouseEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
//    	Main.mainMusic.stopMusic();
//		Main.mainMusic.resetNameAudioStream("gameMusic");
		Main.setMusic("gameMusic", true);
		stripPage = FXMLLoader.load(getClass().getResource("/eye/game/eyeMovement1/strip_priorPage1.fxml"));
		gameMainPage.getChildren().setAll(stripPage);
    }

}
