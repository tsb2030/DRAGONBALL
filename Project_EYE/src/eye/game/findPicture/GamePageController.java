package eye.game.findPicture;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePageController implements Initializable {

	@FXML
	AnchorPane gamePage, findPictureStart;

	@FXML
	ImageView BackBtn, sampleImage, PauseBtn, ranImage1, ranImage2, ranImage3, ranImage4, ranImage5, ranImage6,
			ranImage7, ranImage8, ranImage9, ranImage10, ranImage11, ranImage12, ranImage13, ranImage14, ranImage15,
			ranImage16, ranImage17, ranImage18, ranImage19, ranImage20, ranImage21;

	int[] code = new int[22];
	ImageView[] B = new ImageView[21];

	@FXML
	private Button pausebutton; // �Ͻ� ���� ��ư
	
	@FXML
	Image image1, image2, image3;

	@FXML
	Text timeLabel;

	public static int timeTime = 30;

	public static Stage gamePageStage, currentStage, dodugeStage;

	@FXML
	Label ScoreLabel;

	boolean a = true;

	int randomI, randomJ, tmp;

	int pauseSwitch = 0;

	private Clock timer;

	public static int bigScore = 0;

	public ArrayList<WrongImage> wrongImage = new ArrayList<WrongImage>();
	Clock clock;

	public void initialize(URL arg0, ResourceBundle arg1) {
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

			File file = new File(Main.class.getResource("01.png").toURI());
			FileInputStream fis = new FileInputStream(file);
			image1 = new Image(fis);

			File file1 = new File(Main.class.getResource("02.png").toURI());
			FileInputStream fis1 = new FileInputStream(file1);
			image2 = new Image(fis1);

			File file2 = new File(Main.class.getResource("03.png").toURI());
			FileInputStream fis2 = new FileInputStream(file2);
			image3 = new Image(fis2);

			newGame();
			setGame();
		} catch (Exception e) {
			e.printStackTrace();
		}

		BackBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					findPictureStart = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				gamePage.getChildren().setAll(findPictureStart);
			}
		});

		PauseBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if (pauseSwitch == 0) {
					pauseSwitch = 1;
					// pauseEvent Start!
					timer.animation.pause();
					gamePage.setOpacity(0.45);
				} else if (pauseSwitch == 1) {
					pauseSwitch = 0;
					timer.animation.play();
					gamePage.setOpacity(1.0);
				}
			}
		});
		
			B[0].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[0] == code[21]) {
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
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[1] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[2].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[2] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[3].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[3] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[4].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[4] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[5].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[5] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[6].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[6] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[7].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[7] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[8].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[8] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[9].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[9] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[10].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[10] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[11].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[11] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[12].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[12] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[13].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[13] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[14].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[14] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[15].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[15] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[16].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[16] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[17].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[17] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[18].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[18] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[19].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[19] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});B[20].setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
//					ImageView findView = (ImageView)event.getSource();
					
						if (code[20] == code[21]) {
							System.out.println("check");
							bigScore = bigScore + 1;
							ScoreLabel.setText("Score: " + bigScore);
							newGame();
						}
				}
			});
	}

	public void setGame() {
		
	}

	// 이미지 배치 메소드
	public void newGame() {
		Random random = new Random();
		randomI = random.nextInt(3) + 1;

		if (randomI == 1) {
			sampleImage.setImage(image1);

			for (int i = 0; i <= 20; i++) {
				B[i].setImage(image2);
				code[i] = 2;
			}
			
			for (int i = 0;i<=20;i++) {
				B[i].setImage(image2);
				wrongImage.add(new WrongImage(B[i], 2));
				
			}
			
			code[21] = 1;

		} else if (randomI == 2) {
			sampleImage.setImage(image2);


			for (int i = 0; i <= 20; i++) {
				B[i].setImage(image3);
				code[i] = 3;
			}
			for (int i = 0;i<=20;i++) {
				B[i].setImage(image3);
				wrongImage.add(new WrongImage(B[i], 3));
				
			}
			code[21] = 2;

		} else if (randomI == 3) {
			sampleImage.setImage(image3);

			for (int i = 0; i <= 20; i++) {
				B[i].setImage(image1);
				code[i] = 1;
			}

			for (int i = 0;i<=20;i++) {
				B[i].setImage(image1);
				wrongImage.add(new WrongImage(B[i], 1));
				
			}
			code[21] = 3;

		}

		Random random1 = new Random();
		randomJ = random1.nextInt(21); // 0 에서 20까지
		B[randomJ].setImage(sampleImage.getImage());
		code[randomJ] = code[21]; // 22번째 코드
	}
	
	@FXML
	void pauseEvent(ActionEvent event) {
		// pauseEvent Start!
		timer.animation.pause();
		gamePage.setOpacity(0.45);
	}
	
	public class Clock extends Pane {

		private Timeline animation;
		private int timeTmp = 30;
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
				clock.animation.stop();
				currentStage = (Stage) timeLabel.getScene().getWindow();
				dodugeStage = (Stage) sampleImage.getScene().getWindow();
				FXMLLoader EndGamePopupLoader = new FXMLLoader(Main.class.getResource("EndPopup.fxml")); // �ҷ��� �˾�â
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