package eye.set.controller;
//설정 페이지 컨트롤러

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.jfoenix.controls.JFXToggleButton;

import eye.main.Main;
import eye.set.controller.setController.Clock;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class setController implements Initializable {
	String timeInterval[] = { "30분", "1시간", "1시간 30분", "2시간", "2시간 30분", "3시간", "3시간 30분", "4시간", "4시간 30분", "5시간" };

    @FXML
    private AnchorPane ExplainPage;
	// shortRestlist
	private ArrayList<Integer> restList = new ArrayList<Integer>();
	int startDisturbTime;
	int restCycle = 0;
	int endDisturbTime;
	int restType;
	int s = 1;
	int t = 1;
	boolean flag = false;// 게임이 진행 중인지 받아올 변수
	@FXML
	ComboBox<Integer> combobox1, combobox2;
	@FXML
	ComboBox<String> combobox3;

	@FXML
	private JFXToggleButton BGMToggle;

	@FXML
	private JFXToggleButton effectToggle;

	ObservableList<Integer> timeList = FXCollections.observableArrayList();
	ObservableList<String> intervalList = FXCollections.observableArrayList();

    @FXML
    void backButtonAction(MouseEvent event) throws IOException {
    	Main.setMusic("introMusic", true);
		Parent mainPage = FXMLLoader.load(getClass().getResource("/eye/main/view/main_page.fxml"));
		ExplainPage.getChildren().setAll(mainPage);
    }
	
	// shorRestplay
	public void playShorRest() {
		double ran = Math.random();
		int intValue = (int) (ran * 5) + 1;

		// 이미 배열에 차있으면 다른 변수를 가져오도록 한다.
		while (restList.contains(intValue))
			intValue = (int) (ran * 5) + 1;

		switch (intValue) {
		case 1:
			// 휴식 페이지 1번을 부르는 기능!
			restList.add(1);
			break;
		case 2:
			// 휴식 페이지 1번을 부르는 기능!
			restList.add(2);
			break;
		case 3:
			// 휴식 페이지 1번을 부르는 기능!
			restList.add(3);
			break;
		case 4:
			// 휴식 페이지 1번을 부르는 기능!
			restList.add(4);
			break;
		case 5:
			// 휴식 페이지 1번을 부르는 기능!
			restList.add(5);
			break;

		default:
			isSizeFull();
			break;
		}
	}

	// 만약 배열의 크기가 다 찼을 경우( == 5개의 휴식을 모두 취했을 경우) 배열을 비워두도록 한다.
	public void isSizeFull() {
		if (restList.size() == 5)
			restList.clear();
	}

	// LongRestEvent!!
	public void playLongRest() {
		// fadeOutRest.fxml로 이동!
	}

	// RotateRestEvent!!
	public void playRotateRest() {
		if (s == 1)
			playShorRest();
		else
			playLongRest();

		s = -s;
	}

	// 휴식 이벤트 시작
	public void playRest(int restType) {
		switch (restType) {

		// RotateEvent!
		case 1:
			playRotateRest();
			break;

		// ShortEvent
		case 2:
			playShorRest();
			break;

		// LongEvent
		case 3:
			playLongRest();
			break;

		default:
			break;
		}
	}

	//일단 오류때문에 사용금지
	public void setToggleColors() {
		Color firstBGMLUnToggleColor = (Color) BGMToggle.getUnToggleColor();
		Color firstBGMLUnToggleLineColor = (Color) BGMToggle.getUnToggleLineColor();
		Color firstBGMLToggleColor = (Color) BGMToggle.getToggleColor();
		Color firstBGMLToggleLineColor = (Color) BGMToggle.getToggleLineColor();

		Color firstEffectLUnToggleColor = (Color) effectToggle.getUnToggleColor();
		Color firstEffectLUnToggleLineColor = (Color) effectToggle.getUnToggleLineColor();
		Color firstEffectLToggleColor = (Color) effectToggle.getToggleColor();
		Color firstEffectLToggleLineColor = (Color) effectToggle.getToggleLineColor();

		BGMToggle.setUnToggleColor(firstBGMLUnToggleColor);
		BGMToggle.setUnToggleLineColor(firstBGMLUnToggleLineColor);
		BGMToggle.setToggleColor(firstBGMLToggleColor);
		BGMToggle.setToggleLineColor(firstBGMLToggleLineColor);

		effectToggle.setUnToggleColor(firstEffectLUnToggleColor);
		effectToggle.setUnToggleLineColor(firstEffectLUnToggleLineColor);
		effectToggle.setToggleColor(firstEffectLToggleColor);
		effectToggle.setToggleLineColor(firstEffectLToggleLineColor);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 처음에 켜진 상태이기 때문에 바꿈..
//		setToggleColors();

		for (int i = 0; i < 24; i++) {
			timeList.add(i);
		}
		for (int i = 0; i < timeInterval.length; i++) {
			intervalList.add(timeInterval[i]);
		}
		// 방해금지 시간 콤보박스에 0~23시 추가
		combobox1.setItems(timeList);
		combobox2.setItems(timeList);
		// 실행주기 간격 콤보박스에 timeInterval배열 값들 추가
		combobox3.setItems(intervalList);
	}

	@FXML
	void BGMToggleAction(ActionEvent event)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (t == 1)
			System.out.println("");
//			Main.mainMusic.stopMusic();
		else
			System.out.println("");
//			Main.mainMusic.play();

		t = -t;
	}

	@FXML
	void effectToggleAction(ActionEvent event) {

	}

	@FXML
	void cancel(MouseEvent event) {
		System.out.println("cancel");
	}


    @FXML
    void cancelText(MouseEvent event) {

		System.out.println("cancel");
    }

	
	@FXML
	void submit(MouseEvent event) {
		System.out.println("submit");

		@SuppressWarnings("unused")
		Clock clock = new Clock();
	}

	@FXML
	void submitText(MouseEvent event) {

		System.out.println("submit");

		@SuppressWarnings("unused")
		Clock clock = new Clock();
	}

	// 방해금지 종료시간 설정
	@FXML
	void setEndDisturbTime(ActionEvent event) {
		endDisturbTime = (int) combobox2.getValue();
	}

	// 방해금지 시작시간 설정
	@FXML
	void setStartDisturbTime(ActionEvent event) {
		startDisturbTime = (int) combobox1.getValue();
	}

	// 실행 주기 설정
	@FXML
	void setRestCycle(ActionEvent event) {
		String s = (String) combobox3.getValue();
		System.out.println(s);
		findOne(s);
	}

	public void findOne(String s) {
		switch (s) {
		case "30":
			restCycle = 1;
			break;
		case "1시간":
			restCycle = 2;
			break;
		case "1시간 30분":
			restCycle = 3;
			break;
		case "2시간":
			restCycle = 4;
			break;
		case "2시간 30분":
			restCycle = 5;
			break;
		case "3시간":
			restCycle = 6;
			break;
		case "3시간 30분":
			restCycle = 7;
			break;
		case "4시간":
			restCycle = 8;
			break;
		case "4시간 30분":
			restCycle = 9;
			break;
		case "5시간":
			restCycle = 10;
			break;

		default:
			break;
		}
	}

	public class Clock extends Pane {

		LocalTime currnetTime = LocalTime.now();
		int currnetTimeHour = currnetTime.getHour();
		private Timeline animation;
		private int timeTmp = 1;

		public Clock() {
			animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> {

				// 시간이 째깍 흘러갑니다.
				timeLabel();

				// 방해금지 시간이 아니라면!
				if (!((currnetTimeHour > startDisturbTime) && (currnetTimeHour < endDisturbTime))) {
					if (restCycle != 0) {
						if (!flag) {
							if (timeTmp % (restCycle * 1800) == 0) {
								playRest(restType);
							}
						} else {
							// 게임 끝나고 실행 할 수 있게 만들기
						}

					}
				}
			}));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();
		}

		private void timeLabel() {
			timeTmp++;
		}

		public int getTime() {
			return timeTmp;
		}
	}
}
