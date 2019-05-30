package eye.rest.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eye.main.Main;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class restController implements Initializable{
	@FXML AnchorPane restMainPage, mainPage;	//휴식 목록 페이지
	@FXML ImageView backBtn; //뒤로가기버튼
	@FXML ImageView closeEyeBtn, lookFarBtn, eyeMassageBtn, rollingEyeBtn, blinkBtn, longBreakBtn; //각 휴식별 실행페이지로 연결할 버튼
	@FXML TextFlow text1, text2, text3, text4, text5, text6; //각 휴식별 텍스트

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//눈 꼭 감기
		Text t = new Text("\n정해진 시간동안 눈을 꼭 감아보아요 ");
		text1.getChildren().add(t);
		//멀리 보기
		t = new Text("\n화면에서 눈을 떼고 멀리 있는 사물이나 창문을 바라보세요");
		text2.getChildren().add(t);
		//눈 마사지
		t = new Text("\n피로에 물든 눈을 문질러 마사지 해주세요");
		text3.getChildren().add(t);
		//눈 굴리기
		t = new Text("\n화면에서 잠시 눈을 떼고 눈동자를 좌우, 위아래로 굴려주세요");
		text4.getChildren().add(t);
		//빨리 깜빡이기
		t = new Text("\n눈을 최대한 빠르게 깜빡여 보아요");
		text5.getChildren().add(t);
		//롱 브레이크
		t = new Text("\n화면이 정해진 시간동안 어두워집니다. 잠시 눈을 쉬게 해주세요");
		text6.getChildren().add(t);


		//메인화면으로 돌아가기
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					Main.setMusic("introMusic", true);
					mainPage = FXMLLoader.load(getClass().getResource("/eye/main/view/main_page.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				restMainPage.getChildren().setAll(mainPage);
			}
		});

	}



}
