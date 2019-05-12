package eye;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicMain {
	public static void main(String[] args)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		
		SimpleAudioPlayer music = new SimpleAudioPlayer("MableJ");
		music.start();
		
		music.selectedSong = new ArrayList<Song>();
		music.selectedSong.add(new Song("MableJ"));
		music.selectedSong.add(new Song("LaLaLa"));
		music.selectedSong.add(new Song("introMusic"));
		music.index =0;


		Scanner sc = new Scanner(System.in);
//		while (true) {
//			System.out.println("Input");
//			int n1 = sc.nextInt();
//			if (n1 == 1) {	//∏ÿ√ﬂ±‚
//				music.stopMusic();
//			} else if (n1 == 2) {//∞Ó πŸ≤Ÿ±‚
//				music.changeMusic();
//			} else if (n1 == 3) {//∞Ó Ω√¿€«œ±‚
//				music.startMusic(music.index);
//			} else {
//				System.out.println("Nothing");
//			}
//			if (n1 == 10)
//				break;
//		}
		System.out.println("Input");
		
		int n1 = sc.nextInt();
		if(n1 == 1)
			music.isLoop= false;
		sc.close();
		System.out.println("Well done");

	}

}
