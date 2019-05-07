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

/*���� �޴����� ���Ǵ� Ŭ����
 * game_main_page.fxml�� ����Ǿ��ִ�.*/
public class gameMainController implements Initializable{

	//ȭ�� ��ȯ�� ���� �ʿ��� ���������� �̸� �����Ѵ�.
	@FXML
	AnchorPane gameMainPage, mainPage, oneToFiftyPage;
	
	//�̹����並 ��ưȭ ��Ű�� ���ؼ� �ʿ��� ����
	@FXML
	ImageView oneToFiftyBtn,backBtn;
	
	//��ư�� �̸� �������־�� �ϱ� ������ initialize�κп��� �̹�������� �̺�Ʈó���� �Ѵ�.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		//����ȭ������ ���ư���
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
		
		//1to50�������� ȭ����ȯ
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
