package eye.game.fiveDotGame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class StartPageController implements Initializable {

	@FXML
	AnchorPane gamePage, fiveDotPage, gameMainPage;

	@FXML
	Pane nextBtn;

	@FXML
	ImageView backBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		nextBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					gamePage = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				fiveDotPage.getChildren().setAll(gamePage);
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

				fiveDotPage.getChildren().setAll(gameMainPage);
			}
		});
	}

}
