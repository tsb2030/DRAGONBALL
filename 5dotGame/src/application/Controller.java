package application;

import java.io.IOException;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Controller {

	@FXML
	Circle circle1,circle2,circle3,circle4,circle5;
	
	
	@FXML
	Text endText;
	
	@FXML
	AnchorPane startPage,gamePage;
	
	@FXML
	Button startButton;
	
	@FXML
	Label gameTitle;
	
	@FXML
	Line titleLine;
	

	
	
	
	public void gamePageButton(ActionEvent event) throws IOException {
		AnchorPane gamePage=FXMLLoader.load(getClass().getResource("GamePage.fxml"));
		startPage.getChildren().setAll(gamePage);
		
	}

		
	public void startButton(ActionEvent event) {
		
		
			 gameTitle.setVisible(false); //게임제목 사라짐
			 titleLine.setVisible(false); //제목 밑에 라인 사라짐
			 startButton.setVisible(false);//시작버튼 사라짐
			 circle1.setFill(Color.DARKSEAGREEN);
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
		 
		 
		
	}
	
	 
	public void exitButton(ActionEvent e) throws IOException {		
		AnchorPane startPage=FXMLLoader.load(getClass().getResource("StartPage.fxml"));
		gamePage.getChildren().setAll(startPage);
		
	}
	
	

}
