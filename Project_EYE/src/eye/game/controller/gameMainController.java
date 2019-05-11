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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/*���� �޴����� ���Ǵ� Ŭ����
 * game_main_page.fxml�� ����Ǿ��ִ�.*/
public class gameMainController implements Initializable{

	//ȭ�� ��ȯ�� ���� �ʿ��� ���������� �̸� �����Ѵ�.
	@FXML
	AnchorPane gameMainPage, mainPage, oneToFiftyPage;

	//�̹����並 ��ưȭ ��Ű�� ���ؼ� �ʿ��� ����
	@FXML
	ImageView oneToFiftyBtn,backBtn;

	//��� ������ �ֱ� ���ؼ� �ʿ��� ����
	@FXML
	TextFlow oneToFiftyText, mobiusText, zigzagText, fivedotText,catchmoleText, catchballText, findpictureText;

	//��ư�� �̸� �������־�� �ϱ� ������ initialize�κп��� �̹�������� �̺�Ʈó���� �Ѵ�.
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//�� ���Ӻ�(7��) ���� �ֱ�
		//1to50
		Text t = new Text("<������� ���󰡱�>"
				+ "\n\"���� ���ڴ� ��� ����?\""
				+ "\n�ð� ���� ��絵 ������ ��� ����� �ִ� ���ڳ� ���ڸ� ã�ƺ�����"
				+ "\nenjoy!!");
		oneToFiftyText.getChildren().add(t);

		//����콺��
		t = new Text("<����콺 ��>"
				+ "\n\"���� ���ڴ� ��� ����?\""
				+ "\n�ð� ���� ��絵 ������ ��� ����� �ִ� ���ڳ� ���ڸ� ã�ƺ�����"
				+ "\nenjoy!!");
		mobiusText.getChildren().add(t);

		//�������
		t = new Text("<�������>"
				+ "\n\"���� ���ڴ� ��� ����?\""
				+ "\n�ð� ���� ��絵 ������ ��� ����� �ִ� ���ڳ� ���ڸ� ã�ƺ�����"
				+ "\nenjoy!!");
		zigzagText.getChildren().add(t);

		//5�� ī�� Ʈ���̴�
		t = new Text("<5�� ī�� Ʈ���̴�>"
				+ "\n\"���� ���ڴ� ��� ����?\""
				+ "\n�ð� ���� ��絵 ������ ��� ����� �ִ� ���ڳ� ���ڸ� ã�ƺ�����"
				+ "\nenjoy!!");
		fivedotText.getChildren().add(t);

		//�δ������
		t = new Text("<�δ��� ���>"
				+ "\n\"���� ���ڴ� ��� ����?\""
				+ "\n�ð� ���� ��絵 ������ ��� ����� �ִ� ���ڳ� ���ڸ� ã�ƺ�����"
				+ "\nenjoy!!");
		catchmoleText.getChildren().add(t);

		//ĳġ��
		t = new Text("<Catch ball>"
				+ "\n\"���� ���ڴ� ��� ����?\""
				+ "\n�ð� ���� ��絵 ������ ��� ����� �ִ� ���ڳ� ���ڸ� ã�ƺ�����"
				+ "\nenjoy!!");
		catchballText.getChildren().add(t);

		//�����׸� ã��
		t = new Text("<���� �׸� ã��>"
				+ "\n\"���� ���ڴ� ��� ����?\""
				+ "\n�ð� ���� ��絵 ������ ��� ����� �ִ� ���ڳ� ���ڸ� ã�ƺ�����"
				+ "\nenjoy!!");
		findpictureText.getChildren().add(t);

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
