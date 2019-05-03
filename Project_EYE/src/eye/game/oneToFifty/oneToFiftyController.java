package eye.game.oneToFifty;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

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

public class oneToFiftyController implements Initializable{
	
	@FXML
	AnchorPane apane,oneToFiftyPage,gameMainPage;
	private JFXButton btnarr[] = new JFXButton[25];
	
	@FXML
	JFXButton goGameMainBtn;
	
	@FXML
	private Text timer;
	private int arr1[] = new int[25];
	private int arr2[] = new int[25];
	Random rd = new Random();
	private int number = 1;
	
	private Boolean isStart = false; // 시작인지 판단할 필드.
	 private Timeline timeLine; 
	 private DoubleProperty timeSeconds = new SimpleDoubleProperty();
	 private Duration time = Duration.ZERO;
	
	 public void buttonSetting() {
		 int ly = 60;
		 for(int i=0;i<25;i++) {
			 btnarr[i] = new JFXButton();
		 }
		 int tmp=0;
		 for(int i=0;i<5;i++) {
			 int lx = 100;
			 for(int j=0;j<5;j++) {
				 btnarr[tmp].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
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
							if(number==51) {
								timeLine.stop();
								for(int i=0;i<25;i++) {
									 btnarr[i].setDisable(true);
								 }
							}
						}
					}
				});
				 btnarr[tmp].setPrefSize(100, 100);
				 btnarr[tmp].setLayoutX(lx);
				 btnarr[tmp++].setLayoutY(ly);
				 lx += 240;
			 }
			 ly+=120;
		 }
	 }
	 
	 public void textSetting(int[] intarr) {
		 int tmp=0;
		 for(int i=0;i<25;i++) {
			 btnarr[tmp].setText(intarr[tmp]+"");
			 tmp++;
		 }
	 }
	 
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
	 
	 public void start() {
		    random();
			textSetting(arr1);
			timeLine.stop(); // 새로 시간을 측정하려면 timeLine이 초기화되야 하므로 stop()
	    	time = Duration.ZERO; // time의 값도 새로 측정 할 때마다 0이되어야 함.
	    	timer.textProperty().bind(timeSeconds.asString()); // timeCheck 에 timeSeconds 값 대입
	    	isStart=true; //newButton을 클릭했으므로 isStart 값 true로
	    	if(isStart == true){ 
	        	
	        	 if (timeLine == null) {
	        		  // 딱히 할 거 없음.
	             } else {
	                 timeLine = new Timeline(
	                     new KeyFrame(Duration.millis(10), // 0.01초 단위로 체크
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
	 
	 public void pause() {
		 for(int i=0;i<25;i++) {
			 btnarr[i].setDisable(true);
		 }
		 timeLine.stop();
	 }
	 
	 public void restart() {
		 for(int i=0;i<25;i++) {
			 btnarr[i].setDisable(false);
		 }
		 timeLine.play();
	 }

	public void goGameMainBtn() {
		try {
			gameMainPage = FXMLLoader.load(getClass().getResource("../view/game_main_page.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		oneToFiftyPage.getChildren().setAll(gameMainPage);
	}
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		buttonSetting();
		for(int i=0;i<25;i++) {
			
			apane.getChildren().add(btnarr[i]);
		}
		timeLine = new Timeline(); // timeLine 객체 초기화
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
	}
}
