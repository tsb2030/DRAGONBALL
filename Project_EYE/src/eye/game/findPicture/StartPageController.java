package eye.game.findPicture;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import eye.Music;
import eye.main.Main;
import eye.set.controller.setController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class StartPageController implements Initializable {

	@FXML
	AnchorPane gamePage;

	@FXML
	AnchorPane findPictureStart, gameMainPage;

	@FXML
	Pane nextBtn;

	@FXML
	ImageView backBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 게임시작 버튼
		nextBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					GamePageController.bigScore = 0;
					gamePage = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}

				findPictureStart.getChildren().setAll(gamePage);
			}
		});

		// 뒤로가기 버튼
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

				findPictureStart.getChildren().setAll(gameMainPage);
			}
		});
	}

}
