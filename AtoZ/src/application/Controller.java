package application;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Controller implements Initializable {
	@FXML
	private Button btn00, btn01, btn02, btn03, btn04, btn05, btn06, btn10, btn11, btn12, btn13, 
	btn14, btn15, btn16, btn20, btn21, btn22, btn23, btn24, btn25, btn26, btn31, btn32, btn33, 
	btn34, btn35;
	
	@FXML
	private Label timeLabel;

	@FXML
	private Button newBtn;
	@FXML
	private Button pauseBtn;
	@FXML
	private Button restartBtn;

	String alphabets[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
			"S", "T", "U", "V", "W", "X", "Y", "Z" }; // 인덱스 : 0-25
	int alphabetIndex = 0; // 알파벳의 인덱스를 나타낸다.

	private Boolean isStart = false; // 시작인지 판단할 필드.
	private Timeline timeLine;
	private DoubleProperty timeSeconds = new SimpleDoubleProperty();
	private Duration time = Duration.ZERO;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		timeLine = new Timeline(); // timeLine 객체 초기화
		timeLine.setCycleCount(Timeline.INDEFINITE);
		timeLine.play();

	}

	// 1~50 사이의 숫자를 선택하는 랜덤 메소드를 만듬.
	// 이 메소드는 액션 이벤트가 발생할 때 작동함.
	// Scene Builder에서 컨트롤러와 이벤트를 모두 걸어줘야 함.
	public void generateRandom(ActionEvent event) {
		alphabetIndex = 0;  // new 버튼을 눌렀을 때 인덱스를 초기화 시킨다.
		
		timeLine.stop(); // 새로 시간을 측정하려면 timeLine이 초기화되야 하므로 stop()
		time = Duration.ZERO; // time의 값도 새로 측정 할 때마다 0이되어야 함.
		timeLabel.textProperty().bind(timeSeconds.asString()); // timeCheck 에 timeSeconds 값 대입
		isStart = true; // newButton을 클릭했으므로 isStart 값 true로
		if (isStart == true) {

			if (timeLine == null) {
				// 딱히 할 거 없음.
			} else {
				timeLine = new Timeline(new KeyFrame(Duration.millis(10), // 0.01초 단위로 체크
						new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent t) {
								Duration duration = ((KeyFrame) t.getSource()).getTime();
								time = time.add(duration);
								timeSeconds.set(time.toSeconds());
							}
						}));
				timeLine.setCycleCount(Timeline.INDEFINITE);
				timeLine.play();
			}

		}

		Random rand = new Random();
		String[] alphabetList = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
				"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		int randomIndex;
		String alphaValue = null;
		int count = 0; // 몇 개가 나왔는지 횟수 카운트
		String usedAlphabet[] = new String[26];
		int reCount = 0;

		while (true) {
			randomIndex = rand.nextInt(26); // 0~25
			if (alphabetList[randomIndex] != null) {
				alphaValue = alphabetList[randomIndex];
				alphabetList[randomIndex] = null;
				usedAlphabet[count] = alphaValue;
				count++;
			}
			if (count == 26) {
				break;
			}
		}

		// 각 버튼에 값 하나씩 넣어주기
		btn00.setText(usedAlphabet[reCount++]);
		btn01.setText(usedAlphabet[reCount++]);
		btn02.setText(usedAlphabet[reCount++]);
		btn03.setText(usedAlphabet[reCount++]);
		btn04.setText(usedAlphabet[reCount++]);
		btn05.setText(usedAlphabet[reCount++]);
		btn06.setText(usedAlphabet[reCount++]);
		btn10.setText(usedAlphabet[reCount++]);
		btn11.setText(usedAlphabet[reCount++]);
		btn12.setText(usedAlphabet[reCount++]);
		btn13.setText(usedAlphabet[reCount++]);
		btn14.setText(usedAlphabet[reCount++]);
		btn15.setText(usedAlphabet[reCount++]);
		btn16.setText(usedAlphabet[reCount++]);
		btn20.setText(usedAlphabet[reCount++]);
		btn21.setText(usedAlphabet[reCount++]);
		btn22.setText(usedAlphabet[reCount++]);
		btn23.setText(usedAlphabet[reCount++]);
		btn24.setText(usedAlphabet[reCount++]);
		btn25.setText(usedAlphabet[reCount++]);
		btn26.setText(usedAlphabet[reCount++]);
		btn31.setText(usedAlphabet[reCount++]);
		btn32.setText(usedAlphabet[reCount++]);
		btn33.setText(usedAlphabet[reCount++]);
		btn34.setText(usedAlphabet[reCount++]);
		btn35.setText(usedAlphabet[reCount++]);
		
		btn00.setDisable(false); btn01.setDisable(false); btn02.setDisable(false); btn03.setDisable(false);
		btn04.setDisable(false); btn05.setDisable(false); btn06.setDisable(false); btn10.setDisable(false); 
		btn11.setDisable(false); btn12.setDisable(false); btn13.setDisable(false); btn14.setDisable(false); 
		btn15.setDisable(false); btn16.setDisable(false); btn20.setDisable(false); btn21.setDisable(false); 
		btn21.setDisable(false); btn22.setDisable(false); btn23.setDisable(false); btn24.setDisable(false); 
		btn25.setDisable(false); btn26.setDisable(false); btn31.setDisable(false); btn32.setDisable(false); 
		btn33.setDisable(false); btn34.setDisable(false); btn35.setDisable(false); 
	}

	public void pauseButton(ActionEvent event) {
		btn00.setDisable(true); btn01.setDisable(true); btn02.setDisable(true); btn03.setDisable(true);
		btn04.setDisable(true); btn05.setDisable(true); btn06.setDisable(true); btn10.setDisable(true); 
		btn11.setDisable(true); btn12.setDisable(true); btn13.setDisable(true); btn14.setDisable(true); 
		btn15.setDisable(true); btn16.setDisable(true); btn20.setDisable(true); btn21.setDisable(true); 
		btn21.setDisable(true); btn22.setDisable(true); btn23.setDisable(true); btn24.setDisable(true); 
		btn25.setDisable(true); btn26.setDisable(true); btn31.setDisable(true); btn32.setDisable(true); 
		btn33.setDisable(true); btn34.setDisable(true); btn35.setDisable(true); 
		
		timeLine.stop(); // timeLine멈춤
	}

	public void reStartButton(ActionEvent event) {
		btn00.setDisable(false); btn01.setDisable(false); btn02.setDisable(false); btn03.setDisable(false);
		btn04.setDisable(false); btn05.setDisable(false); btn06.setDisable(false); btn10.setDisable(false); 
		btn11.setDisable(false); btn12.setDisable(false); btn13.setDisable(false); btn14.setDisable(false); 
		btn15.setDisable(false); btn16.setDisable(false); btn20.setDisable(false); btn21.setDisable(false); 
		btn21.setDisable(false); btn22.setDisable(false); btn23.setDisable(false); btn24.setDisable(false); 
		btn25.setDisable(false); btn26.setDisable(false); btn31.setDisable(false); btn32.setDisable(false); 
		btn33.setDisable(false); btn34.setDisable(false); btn35.setDisable(false); 
		
		timeLine.play(); // timeLine 이어서 재시작
	}

	// 누르면 문자가 사라지게 하기
	public void invisibleAlphabet00(ActionEvent event) {
		String a = btn00.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn00.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet01(ActionEvent event) {
		String a = btn01.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn01.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet02(ActionEvent event) {
		String a = btn02.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn02.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet03(ActionEvent event) {
		String a = btn03.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn03.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet04(ActionEvent event) {
		String a = btn04.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn04.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet05(ActionEvent event) {
		String a = btn05.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn05.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet06(ActionEvent event) {
		String a = btn06.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn06.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet10(ActionEvent event) {
		String a = btn10.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn10.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet11(ActionEvent event) {
		String a = btn11.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn11.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet12(ActionEvent event) {
		String a = btn12.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn12.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet13(ActionEvent event) {
		String a = btn13.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn13.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet14(ActionEvent event) {
		String a = btn14.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn14.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet15(ActionEvent event) {
		String a = btn15.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn15.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet16(ActionEvent event) {
		String a = btn16.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn16.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet20(ActionEvent event) {
		String a = btn20.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn20.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet21(ActionEvent event) {
		String a = btn21.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn21.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet22(ActionEvent event) {
		String a = btn22.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn22.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet23(ActionEvent event) {
		String a = btn23.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn23.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet24(ActionEvent event) {
		String a = btn24.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn24.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet25(ActionEvent event) {
		String a = btn25.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn25.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet26(ActionEvent event) {
		String a = btn26.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn26.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet31(ActionEvent event) {
		String a = btn31.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn31.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet32(ActionEvent event) {
		String a = btn32.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn32.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet33(ActionEvent event) {
		String a = btn33.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn33.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet34(ActionEvent event) {
		String a = btn34.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn34.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}

	public void invisibleAlphabet35(ActionEvent event) {
		String a = btn35.getText();
		if (a.equals(alphabets[alphabetIndex])) {
			btn35.setText("");
			alphabetIndex++;

			if (alphabetIndex == 26) {
				timeLine.stop();
			}
		}
	}
}
