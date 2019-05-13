package eye.game.catchBall;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SelectSpeedPageController {

    @FXML
    private JFXButton easyButton;

    @FXML
    private JFXButton normalButton;

    @FXML
    private JFXButton hardButton;

    @FXML
    private Button backButton;

    public static double gameSpeed = 0.0;	//second
    
    @FXML
    void backButtonAction(ActionEvent event) {
    	try {
    		Parent IntroducePage = FXMLLoader.load(getClass().getResource("IntroducePage.fxml"));  // �ҷ��� ������ ����
			Scene scene = new Scene(IntroducePage);
			scene.getStylesheets().add(getClass().getResource("../../main/controller/application.css").toExternalForm());
			Stage primaryStage = (Stage) backButton.getScene().getWindow();  // ���� ������ ��������
			primaryStage.setScene(scene);
			primaryStage.setTitle("IntroducePage!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void easyButtonAction(ActionEvent event) {
    	gameSpeed = 5;
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

}
