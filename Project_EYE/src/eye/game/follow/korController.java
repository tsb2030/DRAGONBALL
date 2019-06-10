package eye.game.follow;

import java.net.URL;
import java.util.ResourceBundle;

import eye.Music;
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
import javafx.stage.Stage;

public class korController implements Initializable {

	@FXML
	private ImageView backBtn;

	@FXML
	private AnchorPane choicePage, introPage;

	@FXML
	private Button korNextBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					Parent gameInfo = FXMLLoader.load(getClass().getResource("gameChoice.fxml")); // 불러올 페이지 지정
					Scene scene = new Scene(gameInfo);
					scene.getStylesheets().add(getClass().getResource("intro.css").toExternalForm()); // css 지정
					Stage primaryStage = (Stage) backBtn.getScene().getWindow(); // 현재 윈도우 가져오기
					primaryStage.setScene(scene);
					primaryStage.setTitle("순서대로 따라가기");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void korGameStart() {
		try {
			Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
			Parent gameInfo = FXMLLoader.load(getClass().getResource("gamePageKor.fxml")); // 불러올 페이지 지정
			Scene scene = new Scene(gameInfo);
			scene.getStylesheets().add(getClass().getResource("game.css").toExternalForm()); // css 지정
			Stage primaryStage = (Stage) korNextBtn.getScene().getWindow(); // 현재 윈도우 가져오기
			primaryStage.setScene(scene);
			primaryStage.setTitle("순서대로 따라가기 - ㄱ  to ㅎ");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
