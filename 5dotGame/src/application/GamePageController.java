package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePageController implements Initializable {

	@FXML
	Circle circle1,circle2,circle3,circle4,circle5;
	
	@FXML
	AnchorPane gamePage;
	
	@FXML
	AnchorPane startPage;
	
	@FXML
	Label gameTitle;
	
	@FXML
	Line titleLine;
	
	@FXML
	Text endText;
	
	//private boolean pause_control = true;

	
//		private RotateTransition rotateTransition1;
//	   private RotateTransition rotateTransition2;
//	   private RotateTransition rotateTransition3;
//	   private RotateTransition rotateTransition4;
//	   private RotateTransition rotateTransition5;
//	   
//	   private RotateTransition rotateTransition12;
//	   private RotateTransition rotateTransition23;
//	   private RotateTransition rotateTransition34;
//	   private RotateTransition rotateTransition45;
//	   
//	   private RotateTransition rotateTransition123;
//	   private RotateTransition rotateTransition234;
//	   private RotateTransition rotateTransition345;
//	   
//	   private RotateTransition rotateTransition1234;
//	   private RotateTransition rotateTransition2345;
//	   
//	   private RotateTransition rotateTransition12345;
//	   
	   
	


	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		new gameRun().start();
		
		
	}
	
	class gameRun extends Thread{
		public void run() {
			 try {
				Thread.sleep(500);
				 //stage1
				 RotateTransition rotateTransition1 = new RotateTransition(Duration.seconds(3),circle1);
				 RotateTransition rotateTransition2 = new RotateTransition(Duration.seconds(3),circle2);
				 RotateTransition rotateTransition3 = new RotateTransition(Duration.seconds(3),circle3);
				 RotateTransition rotateTransition4 = new RotateTransition(Duration.seconds(3),circle4);
				 RotateTransition rotateTransition5 = new RotateTransition(Duration.seconds(3),circle5);
				 
				 //stage2
				 RotateTransition rotateTransition12 = new RotateTransition(Duration.seconds(3),circle1);
				 RotateTransition rotateTransition23 = new RotateTransition(Duration.seconds(3),circle2);
				 RotateTransition rotateTransition34 = new RotateTransition(Duration.seconds(3),circle3);
				 RotateTransition rotateTransition45 = new RotateTransition(Duration.seconds(3),circle4);
				 
				 //stage3
				 RotateTransition rotateTransition123 = new RotateTransition(Duration.seconds(3),circle1);
				 RotateTransition rotateTransition234 = new RotateTransition(Duration.seconds(3),circle2);
				 RotateTransition rotateTransition345 = new RotateTransition(Duration.seconds(3),circle3);
				 
				 //stage4
				 RotateTransition rotateTransition1234 = new RotateTransition(Duration.seconds(3),circle1);
				 RotateTransition rotateTransition2345 = new RotateTransition(Duration.seconds(3),circle2);
				 
				 //stage5
				 RotateTransition rotateTransition12345 = new RotateTransition(Duration.seconds(3),circle1);
						
				 
				 //stage1 점 하나씩
				 
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
				 
				 
				 //stage2
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
				 
				 //stage3
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
				 
				 //stage4
				 rotateTransition1234.setOnFinished((e) -> {
					 circle1.setFill(Color.LIGHTYELLOW);
					 circle5.setFill(Color.DARKSEAGREEN);
					 
					 rotateTransition2345.play();
				 }); 
				 
				 
				 rotateTransition2345.setOnFinished((e) -> {
					 circle1.setFill(Color.DARKSEAGREEN);
					
					 rotateTransition12345.play();
				 }); 
				 
				 //stage5
				 rotateTransition12345.setOnFinished((e) -> {
					 endText.setVisible(true);
					 
				 }); 
				 
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

			 
		}
	}
	
	public void exitButton(ActionEvent e) throws IOException {	
		//팝업 띄워서 물어보기 & 게임 중단
	
//		AnchorPane startPage=FXMLLoader.load(getClass().getResource("startPage.fxml"));
//		gamePage.getChildren().setAll(startPage);
		
//		if(pause_control == true) {
//			rotateTransition1.pause();
//			rotateTransition2.pause();
//			rotateTransition3.pause();
//			rotateTransition4.pause();
//			rotateTransition5.pause();
//			
//			rotateTransition12.pause();
//			rotateTransition23.pause();
//			rotateTransition34.pause();
//			rotateTransition45.pause();
//			
//			rotateTransition123.pause();
//			rotateTransition234.pause();
//			rotateTransition345.pause();
//			
//			rotateTransition1234.pause();
//			rotateTransition2345.pause();
//						
//			
//		}
//		
//		
		new gameRun().stop();
		
	}
	
}
