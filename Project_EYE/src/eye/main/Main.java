package eye.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import eye.SimpleAudioPlayer;
import eye.Song;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	//정적 음악 변수 추가: player입니다.
	public static SimpleAudioPlayer mainMusic;

	@Override
	public void start(Stage primaryStage) {
		try {
			Font.loadFont(getClass().getResourceAsStream("font.a소나무B.ttf"), 10);
			Font.loadFont(getClass().getResourceAsStream("font.a소나무L.ttf"), 10);
			Font.loadFont(getClass().getResourceAsStream("font.a소나무M.ttf"), 10);

			Parent root = FXMLLoader.load(getClass().getResource("view/loading.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//
			mainMusic = new SimpleAudioPlayer("MableJ");
			mainMusic.start();
			
			//사용할 음악 목록들을 selectedSong이라는 ArrayList<Song>에 하나씩 넣어준다.
			mainMusic.selectedSong = new ArrayList<Song>();
			mainMusic.selectedSong.add(new Song("MableJ"));
			mainMusic.selectedSong.add(new Song("LaLaLa"));
			mainMusic.selectedSong.add(new Song("introMusic"));
			
			//곡을 변경할 때 인덱스를 사용할 경우를 대비해 인덱스 변수도 만들어준다. 사용 안할수 도 있음
			mainMusic.index = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		launch(args);
		//음악 스레드를 종료 시킨다.
		mainMusic.stopMusic();
	}
}
