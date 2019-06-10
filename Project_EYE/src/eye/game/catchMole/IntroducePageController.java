package eye.game.catchMole;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import eye.Music;
import eye.main.Main;
import eye.set.controller.setController;
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
	AnchorPane gamePage, startPage, gameMainPage;

	@FXML
	Pane nextBtn;

	@FXML
	ImageView backBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("java version: " + System.getProperty("java.version"));
		System.out.println("javafx.version: " + System.getProperty("javafx.version"));
		nextBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					gamePage = FXMLLoader.load(getClass().getResource("DodugeGame2.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}

				startPage.getChildren().setAll(gamePage);
			}
		});

		backBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				try {
					setController.isGameStart = false;
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					Main.setMusic("mainMusic", true, 1);
					gameMainPage = FXMLLoader.load(getClass().getResource("/eye/game/view/game_main_page.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}

				startPage.getChildren().setAll(gameMainPage);
			}
		});

	}

}
