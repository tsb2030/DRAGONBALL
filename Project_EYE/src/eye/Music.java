package eye;

import java.io.InputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {

	private Player player;
	private boolean isLoop;
	private InputStream is;
	private String name;

	public static boolean BGMFlag = true;
	public static boolean effectFlag = true;

	private int musicType = 0; // 1 == BGM, 2 == effect

	public Music(String name, boolean isLoop, int musicType) {
		try {
			this.isLoop = isLoop;
			this.name = name;
			this.musicType = musicType;
			is = Music.class.getResourceAsStream("/musics/" + name + ".mp3");
			player = new Player(is);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String getMusicName() {
		return this.name;
	}

	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}

	@Override
	public void run() {
		if (this.musicType == 1) {
			if (BGMFlag) {
				try {
					do {
						player.play();
						player = new Player(is);
					} while (isLoop);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		} else {
			if (effectFlag) {
				try {
					do {
						player.play();
						player = new Player(is);
					} while (isLoop);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
