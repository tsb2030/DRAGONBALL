package eye;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SimpleAudioPlayer extends Thread {

	private static SimpleAudioPlayer music;

	public ArrayList<Song> selectedSong = new ArrayList<Song>();
	public int index = 0;

	Boolean isLoop = true;
	static Long currentFrame;
	static Clip clip;

	String status;
	static String name = "introMusic";

	AudioInputStream audioInputStream;
	static String filePath;

	public SimpleAudioPlayer(String name)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
				new File(SimpleAudioPlayer.class.getResource("../musics/" + name + ".wav").toURI()).getAbsoluteFile());
		clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	@Override
	public void run() {
		try {
			do {
				clip.start();
				status = "play";
			} while (isLoop);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Method to play the audio
	public void play() {
		// start the clip
		clip.start();
		status = "play";
	}

	// Method to stop the audio
	public void stopMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		isLoop = false;
		currentFrame = 0L;
		clip.stop();
		clip.close();
		this.interrupt();
		this.isLoop = false;
	}

	public void startMusic(int selectedIndex)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		if (music != null)
			stopMusic();
		music = new SimpleAudioPlayer(selectedSong.get(selectedIndex).getSongName());
		music.start();
	}

	public void changeMusic()
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		if (index == selectedSong.size() - 1)
			index = 0;
		else
			index++;
		startMusic(index);
	}
	
	public void setSong(String name) throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		if (music != null)
			stopMusic();
		music = new SimpleAudioPlayer(name);
		music.start();
	}

	// Method to pause the audio
	public void pause() {
		if (status.equals("paused")) {
			System.out.println("audio is already paused");
			return;
		}
		SimpleAudioPlayer.currentFrame = SimpleAudioPlayer.clip.getMicrosecondPosition();
		clip.stop();
		status = "paused";
	}

	// Method to resume the audio
	public void resumeAudio()
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		if (status.equals("play")) {
			System.out.println("Audio is already " + "being played");
			return;
		}
		clip.close();
		resetAudioStream();
		clip.setMicrosecondPosition(currentFrame);
		this.play();
	}

	// Method to reset audio stream
	public void resetAudioStream()
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
				new File(SimpleAudioPlayer.class.getResource("../musics/" + name + ".wav").toURI()).getAbsoluteFile());
		clip.open(audioInputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void resetNameAudioStream(String name)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
				new File(SimpleAudioPlayer.class.getResource("../musics/" + name + ".wav").toURI()).getAbsoluteFile());
		clip.open(audioInputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
}