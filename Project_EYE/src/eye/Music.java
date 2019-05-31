package eye;

import java.io.InputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	
	private Player player;
	private boolean isLoop;
	private InputStream is;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			is = Music.class.getResourceAsStream("/musics/" + name + ".mp3");
			player = new Player(is);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getTime() {
		if(player == null)
			return 0;
		return player.getPosition();
	}
	
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}
	
	@Override
	public void run() {
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
