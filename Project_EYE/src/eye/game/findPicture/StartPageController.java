package eye.game.findPicture;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class StartPageController implements Initializable  {

	@FXML
	AnchorPane gamePage;
	
	@FXML
	AnchorPane findPictureStart,gameMainPage;

	@FXML
	Pane nextBtn;
	
	@FXML
	ImageView backBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		nextBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					GamePageController.bigScore = 0;
					gamePage = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				findPictureStart.getChildren().setAll(gamePage);
			}
		});

		backBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				try {
					Main.mainMusic.stopMusic();
					Main.mainMusic.resetNameAudioStream("mainMusic");
					gameMainPage = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException | URISyntaxException e) {
					e.printStackTrace();
				}
				
				findPictureStart.getChildren().setAll(gameMainPage);
			}
		});
	}
	
	

}
