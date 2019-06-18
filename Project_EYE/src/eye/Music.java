package eye;

import java.io.InputStream;

import javazoom.jl.player.Player;

//음악 쓰레드
public class Music extends Thread {

	private Player player;	//외부 jar파일을 이용한 음악 플레이어 재생을 위한 변수
	private boolean isLoop;	//반복 제어 변수
	private InputStream is;	//jar에서도 실행 가능하기 위해 Stream으로 파일 입력
	private String name;	//음악 이름

	public static boolean BGMFlag = true;	//BGM토글을 위한 변수
	public static boolean effectFlag = true;	//effect토글을 위한 변수

	private int musicType = 0; // 1 == BGM, 2 == effect

	public Music(String name, boolean isLoop, int musicType) {
		try {
			this.isLoop = isLoop;
			this.name = name;
			this.musicType = musicType;
			is = Music.class.getResourceAsStream("/musics/" + name + ".mp3");	//파일경로를 통해 inpustream형태로 파일 입력
			player = new Player(is);											//플레이어 변수에 스트림 재생
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
		if (this.musicType == 1) {	//배경음 제어문
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
		} else {					//효과음 제어문
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
