package eye.game.catchBall;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

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
   					gameMainPage = FXMLLoader.load(getClass().getResource("../view/game_main_page.fxml"));
   				} catch (IOException e) {
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
    	try {
    		Parent CatchBallGame = FXMLLoader.load(getClass().getResource("CatchballGame.fxml"));  // �ҷ��� ������ ����
			Scene scene = new Scene(CatchBallGame);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());  // css ����
			Stage primaryStage = (Stage) easyButton.getScene().getWindow();  // ���� ������ ��������
			primaryStage.setScene(scene);
			primaryStage.setTitle("CatchBallGame!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void normalButtonAction(ActionEvent event) {
    	gameSpeed = 2.5;
    	try {
    		Parent CatchBallGame = FXMLLoader.load(getClass().getResource("CatchballGame.fxml"));  // �ҷ��� ������ ����
			Scene scene = new Scene(CatchBallGame);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());  // css ����
			Stage primaryStage = (Stage) hardButton.getScene().getWindow();  // ���� ������ ��������
			primaryStage.setScene(scene);
			primaryStage.setTitle("CatchBallGame!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void hardButtonAction(ActionEvent event) {
    	gameSpeed = 0.1;
    	try {
    		Parent CatchBallGame = FXMLLoader.load(getClass().getResource("CatchballGame.fxml"));  // �ҷ��� ������ ����
			Scene scene = new Scene(CatchBallGame);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());  // css ����
			Stage primaryStage = (Stage) normalButton.getScene().getWindow();  // ���� ������ ��������
			primaryStage.setScene(scene);
			primaryStage.setTitle("CatchBallGame!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

   

}
