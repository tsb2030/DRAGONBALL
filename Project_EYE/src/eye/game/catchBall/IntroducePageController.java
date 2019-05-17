package eye.game.catchBall;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import eye.main.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class IntroducePageController implements Initializable  {

	@FXML
	AnchorPane gamePage,catchBallPage,gameMainPage;

	@FXML
	Pane startButton;

    @FXML
    ImageView backButton;
    
    @FXML
    Button easyButton, normalButton, hardButton;

    public static double gameSpeed = 0.0;	//second

    @Override
   	public void initialize(URL location, ResourceBundle resources) {
    	System.out.println("java version: "+System.getProperty("java.version"));
		System.out.println("javafx.version: " + System.getProperty("javafx.version"));
    	startButton.setOnMouseClicked(new EventHandler<Event>() {

   			@Override
   			public void handle(Event event) {
   				// TODO Auto-generated method stub
   				try {
   					gamePage = FXMLLoader.load(getClass().getResource("CatchBallGame.fxml"));
   				} catch (IOException e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
   				}
   				
   				catchBallPage.getChildren().setAll(gamePage);
   			}
   		});
   		
   		backButton.setOnMouseClicked(new EventHandler<Event>() {
   			@Override
   			public void handle(Event event) {
   				// TODO Auto-generated method stub
   				try {
   					Main.mainMusic.stopMusic();
   					Main.mainMusic.resetNameAudioStream("mainMusic");
   					gameMainPage = FXMLLoader.load(getClass().getResource("../view/game_main_page.fxml"));
   				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException | URISyntaxException e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
   				}
   				
   				catchBallPage.getChildren().setAll(gameMainPage);
   			}
   		});
   		
   	}
    
    @FXML
    void easyButtonAction(ActionEvent event) {
    	gameSpeed = 5;
    	CatchballGameController.bigScore = 0;
    	try {
    		Parent CatchBallGame = FXMLLoader.load(getClass().getResource("CatchballGame.fxml"));  // �ҷ��� ������ ����
			Scene scene = new Scene(CatchBallGame);
			scene.getStylesheets().add(getClass().getResource("../../main/controller/application.css").toExternalForm());
			Stage primaryStage = (Stage) easyButton.getScene().getWindow();  // ���� ������ ��������
			primaryStage.setScene(scene);
			primaryStage.setTitle("CatchBallGame!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void normalButtonAction(ActionEvent event) {
    	gameSpeed = 4;
    	CatchballGameController.bigScore = 0;
    	try {
    		Parent CatchBallGame = FXMLLoader.load(getClass().getResource("CatchballGame.fxml"));  // �ҷ��� ������ ����
			Scene scene = new Scene(CatchBallGame);
			scene.getStylesheets().add(getClass().getResource("../../main/controller/application.css").toExternalForm());
			Stage primaryStage = (Stage) hardButton.getScene().getWindow();  // ���� ������ ��������
			primaryStage.setScene(scene);
			primaryStage.setTitle("CatchBallGame!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void hardButtonAction(ActionEvent event) {
    	gameSpeed = 3;
    	CatchballGameController.bigScore = 0;
    	try {
    		Parent CatchBallGame = FXMLLoader.load(getClass().getResource("CatchballGame.fxml"));  // �ҷ��� ������ ����
			Scene scene = new Scene(CatchBallGame);
			scene.getStylesheets().add(getClass().getResource("../../main/controller/application.css").toExternalForm());
			Stage primaryStage = (Stage) normalButton.getScene().getWindow();  // ���� ������ ��������
			primaryStage.setScene(scene);
			primaryStage.setTitle("CatchBallGame!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

   

}
