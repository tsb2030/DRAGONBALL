package eye.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/*게임 메뉴에서 사용되는 클래스
 * game_main_page.fxml에 연결되어있다.*/
public class gameMainController implements Initializable{

	//화면 전환을 위해 필요한 페이지들을 미리 정의한다.
	@FXML
	AnchorPane gameMainPage, mainPage, oneToFiftyPage;
	
	//이미지뷰를 버튼화 시키기 위해서 필요한 정의
	@FXML
	ImageView oneToFiftyBtn,backBtn;
	
	//버튼을 미리 정의해주어야 하기 때문에 initialize부분에서 이미지뷰들의 이벤트처리를 한다.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		//메인화면으로 돌아가기
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					mainPage = FXMLLoader.load(getClass().getResource("../../main/view/main_page.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				gameMainPage.getChildren().setAll(mainPage);
			}
		});
		
		//1to50게임으로 화면전환
		oneToFiftyBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					oneToFiftyPage = FXMLLoader.load(getClass().getResource("../oneToFifty/oneToFiftyGame.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				gameMainPage.getChildren().setAll(oneToFiftyPage);
			}
		});
	}

}
