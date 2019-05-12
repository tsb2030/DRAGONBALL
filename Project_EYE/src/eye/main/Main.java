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
	//���� ���� ���� �߰�: player
	public static SimpleAudioPlayer mainMusic;

	@Override
	public void start(Stage primaryStage) {
		try {
			Font.loadFont(getClass().getResourceAsStream("font.a�ҳ���B.ttf"), 10);
			Font.loadFont(getClass().getResourceAsStream("font.a�ҳ���L.ttf"), 10);
			Font.loadFont(getClass().getResourceAsStream("font.a�ҳ���M.ttf"), 10);

			Parent root = FXMLLoader.load(getClass().getResource("view/loading.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//
			mainMusic = new SimpleAudioPlayer("MableJ");
			mainMusic.start();
			
			//����� ���� ��ϵ��� selectedSong�̶�� ArrayList<Song>�� �ϳ��� �־��ش�.
			mainMusic.selectedSong = new ArrayList<Song>();
			mainMusic.selectedSong.add(new Song("MableJ"));
			mainMusic.selectedSong.add(new Song("LaLaLa"));
			mainMusic.selectedSong.add(new Song("introMusic"));
			
			//���� ������ �� �ε����� ����� ��츦 ����� �ε��� ������ ������ش�. ��� ���Ҽ� �� ����
			mainMusic.index = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		launch(args);
		//���� �����带 ���� ��Ų��.
		mainMusic.stopMusic();
	}
}
