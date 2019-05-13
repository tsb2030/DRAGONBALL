package eye.game.catchMole;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import eye.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EndGamePopupController implements Initializable {

	@FXML
	private Button backGame;

	@FXML
	private Button goMainButton;

	@FXML
	private Label gameScore;

	@FXML
	void backGameAction(ActionEvent event) {

		try {

			Stage primaryStage = (Stage) backGame.getScene().getWindow();
			primaryStage.close();
			Parent DodugeGame2 = FXMLLoader.load(getClass().getResource("DodugeGame2.fxml"));
			Scene scene = new Scene(DodugeGame2);
		
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());  // css ����
			DodugeGameController.score = 0;
			DodugeGameController.timeTime = 60;
			DodugeGameController.dodugeStage.setScene(scene);
			DodugeGameController.dodugeStage.setTitle("DodugeGame");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void goMainButtonAction(ActionEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		// ���� �˾� â�� �ݰ�, ���� â�� �����ϴ� �۾��� �����ؾ� ��
				Main.mainMusic.stopMusic();
				Main.mainMusic.resetNameAudioStream("LaLaLa");
				// go main
		    	try {
		    		DodugeGameController.score = 0;
		    		//catchballGame fxml������ scene�� �����ϴ� ���
					Parent selectSpeedPage = FXMLLoader.load(getClass().getResource("../view/game_main_page.fxml"));
					Scene scene = new Scene(selectSpeedPage);
					//���� â�� static���� ������� ������ �� â�� ���� �� �� �ִ�.
					DodugeGameController.currentStage.setScene(scene);
					DodugeGameController.currentStage.setTitle("game_main_page");
					//css����
					//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());  // css ����
					
					//���� �˾�â�� �ݱ����� �ϴ� ����
					Stage primaryStage = (Stage) backGame.getScene().getWindow(); // ���� ������ ��������
					//���� �˾� â �ݱ�
					primaryStage.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gameScore.setText(String.valueOf(DodugeGameController.score));
	}

}
