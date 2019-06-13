package eye.game.findPicture;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import eye.Music;
import eye.main.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import eye.db.*;
public class GamePageController implements Initializable {

	dbconn db = new dbconn();
	AchievementDB aDB = new AchievementDB();
	
	@FXML
	AnchorPane findPicturegamePage;

	@FXML
	AnchorPane findPictureStart;

	@FXML
	ImageView BackBtn, sampleImage, PauseBtn, ranImage1, ranImage2, ranImage3, ranImage4, ranImage5, ranImage6,
			ranImage7, ranImage8, ranImage9, ranImage10, ranImage11, ranImage12, ranImage13, ranImage14, ranImage15,
			ranImage16, ranImage17, ranImage18, ranImage19, ranImage20, ranImage21;

	int[] code = new int[22];
	ImageView[] B = new ImageView[21];

	@FXML
	private Button pausebutton;

	@FXML
	Image image1, image2, image3;

	@FXML
	Text timeLabel;

	public static int timeTime = 10;

	public static Stage gamePageStage, currentStage, dodugeStage;

	@FXML
	Label ScoreLabel;

	boolean a = true;

	int randomI, randomJ, tmp;

	int pauseSwitch = 0;

	public static int bigScore = 0;

	public ArrayList<WrongImage> wrongImage = new ArrayList<WrongImage>();
	public Clock clock;

	// pause이벤트
	@FXML
	void pauseAction(MouseEvent event) {
		pauseEvent();
	}

	public void pauseEvent() {
		pauseSwitch = 1;
		// pauseEvent Start!
		clock.animation.pause();
		findPicturegamePage.setOpacity(0.45);
	}

	// restart이벤트
	@FXML
	void restartAction(MouseEvent event) {
		restartEvent();

	}

	public void restartEvent() {
		pauseSwitch = 0;
		clock.animation.play();
		findPicturegamePage.setOpacity(1.0);
		restartGame();

	}

	@FXML
	void BackBtnAction(MouseEvent event) {
		if (pauseSwitch == 0) {
			clock.animation.pause();
			gamePageStage = (Stage) BackBtn.getScene().getWindow();
			try {Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
			effectMusic.start();
				findPictureStart = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			findPicturegamePage.getChildren().setAll(findPictureStart);
		}

	}

	public void restartGame() {
		if (pauseSwitch == 0) {
			B[0].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[0] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[1].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[1] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[2].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[2] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[3].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[3] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[4].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[4] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[5].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[5] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[6].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[6] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[7].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[7] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[8].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[8] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[9].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[9] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[10].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[10] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[11].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[11] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[12].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[12] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[13].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[13] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[14].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[14] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[15].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[15] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[16].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[16] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[17].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[17] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[18].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[18] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[19].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[19] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[20].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[20] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		findPicturegamePage.setOpacity(1.0);
		pauseSwitch = 0;
		clock = new Clock();

		B[0] = ranImage1;
		B[1] = ranImage2;
		B[2] = ranImage3;
		B[3] = ranImage4;
		B[4] = ranImage5;
		B[5] = ranImage6;
		B[6] = ranImage7;
		B[7] = ranImage8;
		B[8] = ranImage9;
		B[9] = ranImage10;
		B[10] = ranImage11;
		B[11] = ranImage12;
		B[12] = ranImage13;
		B[13] = ranImage14;
		B[14] = ranImage15;
		B[15] = ranImage16;
		B[16] = ranImage17;
		B[17] = ranImage18;
		B[18] = ranImage19;
		B[19] = ranImage20;
		B[20] = ranImage21;

		try {
			InputStream is1 = Music.class.getResourceAsStream("/eye/game/findPicture/abocado.png");
			image1 = new Image(is1);

			InputStream is2 = Music.class.getResourceAsStream("/eye/game/findPicture/apple.png");
			image2 = new Image(is2);

			InputStream is3 = Music.class.getResourceAsStream("/eye/game/findPicture/kiwi.png");
			image3 = new Image(is3);

			newGame();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (pauseSwitch == 0) {
			// 난 이렇게 안함 ㅎ

//			BackBtn.setOnMouseClicked(new EventHandler<Event>() {
//				@Override
//				public void handle(Event event) {
//					try {
//						findPictureStart = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//
//					gamePage.getChildren().setAll(findPictureStart);
//				}
//			});

			// 난 이렇게 안함 ㅎ

//			PauseBtn.setOnMouseClicked(new EventHandler<Event>() {
//				@Override
//				public void handle(Event event) {
//					if (pauseSwitch == 0) {
//						pauseSwitch = 1;
//						// pauseEvent Start!
//						timer.animation.pause();
//						gamePage.setOpacity(0.45);
//					} else if (pauseSwitch == 1) {
//						pauseSwitch = 0;
//						timer.animation.play();
//						gamePage.setOpacity(1.0);
//					}
//				}
//			});

			B[0].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[0] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[1].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[1] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[2].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[2] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[3].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[3] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[4].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[4] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[5].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[5] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[6].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[6] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[7].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[7] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[8].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[8] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[9].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[9] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[10].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[10] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[11].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[11] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[12].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[12] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[13].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[13] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[14].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[14] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[15].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[15] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[16].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[16] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[17].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[17] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[18].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[18] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[19].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[19] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
			B[20].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//						ImageView findView = (ImageView)event.getSource();

					if (code[20] == code[21] && pauseSwitch == 0) {
						System.out.println("check");
						bigScore = bigScore + 1;
						ScoreLabel.setText("Score: " + bigScore);
						newGame();
					}
				}
			});
		}

	}

	// 이미지 배치 메소드
	public void newGame() {
		Random random = new Random();
		randomI = random.nextInt(3) + 1;

		if (randomI == 1) {
			sampleImage.setImage(image1);

			for (int i = 0; i <= 20; i++) {
				Random random2 = new Random();
				randomI = random2.nextInt(5) + 1;
				if (randomI % 2 == 0) {
					B[i].setImage(image2);
					code[i] = 2;
				} else if (randomI % 2 == 1) {
					B[i].setImage(image3);
					code[i] = 3;
				}
			}

//			for (int i = 0;i<=20;i++) {
//				B[i].setImage(image2);
//				wrongImage.add(new WrongImage(B[i], 2));
//
//			}

			code[21] = 1;

		} else if (randomI == 2) {
			sampleImage.setImage(image2);

			for (int i = 0; i <= 20; i++) {
				Random random2 = new Random();
				randomI = random2.nextInt(5) + 1;
				if (randomI % 2 == 0) {
					B[i].setImage(image1);
					code[i] = 1;
				} else if (randomI % 2 == 1) {
					B[i].setImage(image3);
					code[i] = 3;
				}
			}

//			for (int i = 0;i<=20;i++) {
//				B[i].setImage(image3);
//				wrongImage.add(new WrongImage(B[i], 3));
//
//			}
			code[21] = 2;

		} else if (randomI == 3) {
			sampleImage.setImage(image3);

			for (int i = 0; i <= 20; i++) {
				Random random2 = new Random();
				randomI = random2.nextInt(5) + 1;
				if (randomI % 2 == 0) {
					B[i].setImage(image2);
					code[i] = 2;
				} else if (randomI % 2 == 1) {
					B[i].setImage(image1);
					code[i] = 1;
				}
			}

//			for (int i = 0;i<=20;i++) {
//				B[i].setImage(image1);
//				wrongImage.add(new WrongImage(B[i], 1));
//
//			}
			code[21] = 3;

		}

		Random random1 = new Random();
		randomJ = random1.nextInt(21); // 0 에서 20까지
		B[randomJ].setImage(sampleImage.getImage());
		code[randomJ] = code[21]; // 22번째 코드

	}

	public class Clock extends Pane {

		private Timeline animation;
		private int timeTmp = 10;
		private String S = "";
		private boolean flag = false;

		public Clock() {
			animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timeLabel()));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();
		}

		private void timeLabel() {
			if (timeTmp > 0) {
				timeTmp--;
				timeTime = timeTmp;
			}
			if (timeTime <= 0 && flag == false) {
				pauseSwitch = 1;
				findPicturegamePage.setOpacity(0.45);
				for (int i = 0; i >= 20; i++) {
					B[i].setOnMouseClicked(new EventHandler<Event>() {
						@Override
						public void handle(Event event) {
						}
					});
				}
				
				//디비에 점수 저장
				SimpleDateFormat sDateForm = new SimpleDateFormat("yyyy/MM/dd");
				Date currentTime = new Date();
				String cTime = sDateForm.format(currentTime);
				try {
					db.insertUpGame(cTime, "findPicture", bigScore);
					db.insertTimes("findPicture", cTime);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//오늘 하루 운동횟수 추가
				AchievementDB.DayPlaycount = true;
				aDB.ach();
				clock.animation.stop();
				currentStage = (Stage) timeLabel.getScene().getWindow();
				dodugeStage = (Stage) sampleImage.getScene().getWindow();
				FXMLLoader EndGamePopupLoader = new FXMLLoader(
						Main.class.getResource("/eye/game/findPicture/EndPopup.fxml")); // �ҷ��� �˾�â
				try {
					AnchorPane EndGamePopupPane = (AnchorPane) EndGamePopupLoader.load();
					Scene EndGamePopupScene = new Scene(EndGamePopupPane);
					Stage stage = new Stage();
					stage.setScene(EndGamePopupScene);
					stage.show();
					flag = true;
				} catch (IOException e) {
					e.printStackTrace();
					e.printStackTrace();
				}
			}
			S = "Time: " + timeTmp + "";
			timeLabel.setText(S);
		}

	}

}
