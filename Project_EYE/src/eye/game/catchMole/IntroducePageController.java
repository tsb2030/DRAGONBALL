package eye.game.catchMole;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import eye.main.Main;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class IntroducePageController implements Initializable {

	@FXML
	AnchorPane gamePage,startPage,gameMainPage;

	@FXML
	Pane nextBtn;

    @FXML
    ImageView backBtn;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	System.out.println("java version: "+System.getProperty("java.version"));
		System.out.println("javafx.version: " + System.getProperty("javafx.version"));
		nextBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					gamePage = FXMLLoader.load(getClass().getResource("DodugeGame2.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				startPage.getChildren().setAll(gamePage);
			}
		});
		
		backBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
//					Main.mainMusic.stopMusic();
//   					Main.mainMusic.resetNameAudioStream("mainMusic");
					gameMainPage = FXMLLoader.load(getClass().getResource("../view/game_main_page.fxml"));
				} catch (IOException  e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				startPage.getChildren().setAll(gameMainPage);
			}
		});
		
	}

}
