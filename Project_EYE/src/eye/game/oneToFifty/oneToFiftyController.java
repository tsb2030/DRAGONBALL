package eye.game.oneToFifty;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.jfoenix.controls.JFXButton;

import eye.main.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
/*1to50����*/
public class oneToFiftyController implements Initializable{
	//�ٽ� ���Ӹ���ȭ������ ���ư������� ȭ���� �����ϰ� ������ ��ư�� ����� ���ؼ� ��ư�迭 ����
	@FXML
	AnchorPane apane,oneToFiftyPage,gameMainPage;
	private JFXButton btnarr[] = new JFXButton[25];
	
	@FXML
	JFXButton goGameMainBtn;
	
	//arr1�� 1~25 ������, arr2�� 26~50������ �������� ������ ���� ����
	@FXML
	private Text timer;
	private int arr1[] = new int[25];
	private int arr2[] = new int[25];
	Random rd = new Random();
	//���� � ���� ������ �ϴ��� �Ǻ��ϱ����ؼ� ���Ǵ� ��
	private int number = 1;
	
	//Ÿ�Ӷ����� ����ϱ����� ����
	private Boolean isStart = false; // �������� �Ǵ��� �ʵ�.
	 private Timeline timeLine; 
	 private DoubleProperty timeSeconds = new SimpleDoubleProperty();
	 private Duration time = Duration.ZERO;
	
	 //��ư�� ���ǿ� �̺�Ʈ�ڵ鷯�� �޾��ְ� 1~25������ �������� ������ ��ư�� �޾��ش�.
	 public void buttonSetting() {
		 int ly = 60;
		 //��ư����
		 for(int i=0;i<25;i++) {
			 btnarr[i] = new JFXButton();
		 }
		 int tmp=0;
		 for(int i=0;i<5;i++) {
			 int lx = 100;
			 for(int j=0;j<5;j++) {
				 //��ư�� �̺�Ʈ�� �޾��ִ� �κ�
				 btnarr[tmp].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						/*��ư�� �ؽ�Ʈ�� ������ �� ���ڰ� number�� ���ؼ� ������ arr2�迭�� �ִ� ���� ���ʴ�� �־��ְ� number�� 1 �����ش�.
						 * ���� number�� 25���� Ŭ �ÿ��� number���� 1�÷��ְ� �ؽ�Ʈ�� ""�� �־��ش�.*/
						Button btn = (Button) event.getSource();
						String btnstr = btn.getText();
						if(Integer.parseInt(btnstr)==number) {
							if(number<=25) {
								btn.setText(arr2[number-1]+"");
							}else {
								btn.setText("");
							}
							number++;
							System.out.println(number);
							//number�� 51�̵Ǹ� ������ ���� ���̹Ƿ� Ÿ�Ӷ����� ���߰� ���ڹ�ư�� �������ʰ� ó���Ѵ�.
							if(number==51) {
								timeLine.stop();
								for(int i=0;i<25;i++) {
									 btnarr[i].setDisable(true);
								 }
							}
						}
					}
				});
				 //���� ��ư�� ũ��� ��ġ�� �������ֱ� ���� �κ�
				 btnarr[tmp].setPrefSize(100, 100);
				 btnarr[tmp].setLayoutX(lx);
				 btnarr[tmp++].setLayoutY(ly);
				 lx += 240;
			 }
			 ly+=120;
		 }
	 }
	 
	 //���� ���۽ÿ� ��ư�� �ؽ�Ʈ�� 1~25������ �������� �־��ֱ� ���� �κ�
	 public void textSetting(int[] intarr) {
		 int tmp=0;
		 for(int i=0;i<25;i++) {
			 btnarr[tmp].setText(intarr[tmp]+"");
			 tmp++;
		 }
	 }
	 
	 //arr1,arr2�� ���� 1~25,26~50������ ���� �־��ִºκ�
	 public void random() {
		 for(int i=0; i<25 ;){
	         Boolean tp=true;
	         int r = rd.nextInt(25)+1;
	         for(int j=0;j<25;j++){if(arr1[j]==r)tp=false;}
	         if(tp){arr1[i]=r;i++; }
	     }
		 for(int i=0; i<25 ;){
	         Boolean tp=true;
	         int r = rd.nextInt(25)+26;
	         for(int j=0;j<25;j++){if(arr2[j]==r)tp=false;}
	         if(tp){arr2[i]=r;i++; }
	     }
	 }
	 
	 //���� ���۽� ����Ǵ� �޼ҵ�
	 public void start() {
		    random();
			textSetting(arr1);
			timeLine.stop(); // ���� �ð��� �����Ϸ��� timeLine�� �ʱ�ȭ�Ǿ� �ϹǷ� stop()
	    	time = Duration.ZERO; // time�� ���� ���� ���� �� ������ 0�̵Ǿ�� ��.
	    	timer.textProperty().bind(timeSeconds.asString()); // timeCheck �� timeSeconds �� ����
	    	isStart=true; //newButton�� Ŭ�������Ƿ� isStart �� true��
	    	//��ž��ġ�� �����ϴºκ�
	    	if(isStart == true){ 
	        	
	        	 if (timeLine == null) {
	        		  // ���� �� �� ����.
	             } else {
	                 timeLine = new Timeline(
	                     new KeyFrame(Duration.millis(10), // 0.01�� ������ üũ
	                     new EventHandler<ActionEvent>() {
	                         @Override
	                         public void handle(ActionEvent t) {
	                             Duration duration = ((KeyFrame)t.getSource()).getTime();
	                             time = time.add(duration);
	                             timeSeconds.set(time.toSeconds());
	                          }
	                     })
	                 );
	                 timeLine.setCycleCount(Timeline.INDEFINITE);
	                 timeLine.play();
	             }
	        	
	        }
	 }
	 
	 //pause������ �� ��ưŬ���� ���ϵ��� �ϴ� �κ�
	 public void pause() {
		 for(int i=0;i<25;i++) {
			 btnarr[i].setDisable(true);
		 }
		 timeLine.stop();
	 }

	//restart��ư�� ������ �� ��ưŬ���� �����ϵ��� �ϴ� �κ�
	 public void restart() {
		 for(int i=0;i<25;i++) {
			 btnarr[i].setDisable(false);
		 }
		 timeLine.play();
	 }

	 //���Ӹ���ȭ������ �̵�
	public void goGameMainBtn() {
		try {

			Main.mainMusic.stopMusic();
			Main.mainMusic.resetNameAudioStream("LaLaLa");
			gameMainPage = FXMLLoader.load(getClass().getResource("../view/game_main_page.fxml"));
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		oneToFiftyPage.getChildren().setAll(gameMainPage);
	}
	 
	//����ȭ���� ����ÿ� ����� ���ǳ� �޼ҵ�
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		buttonSetting();
		for(int i=0;i<25;i++) {
			
			apane.getChildren().add(btnarr[i]);
		}
		timeLine = new Timeline(); // timeLine ��ü �ʱ�ȭ
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
	}
}
