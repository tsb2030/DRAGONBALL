package eye.game.eyeMovement1;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Controller implements Initializable {

	Model model = new Model(); // 모델객체 생성
	Timeline timeline; // 타임라인 객체 선언

	private PathTransition pathTransition; // path트랜지션 선언
	private boolean pause_control = true; // 공 움직임이 동작중이면 true. 일시정지 상태면 false.라고 가정하에 초기값을 true로 준다.

	private Color[] color = { Color.RED, Color.AQUA, Color.YELLOW, Color.VIOLET, Color.BLACK }; // 공 색깔 변화에 쓰일 color값 배열

	@FXML
	private Circle pointer; // 공 객체
	@FXML
	private ImageView backBtn; // 백 버튼(뒤로가기 및 일시정지 기능을 포함)

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Controller 생성자 코드
	}

	@FXML // poiner에 #moving으로 지정된 메소드, 공 클릭시 애니메이션이 동작.
	public void moving(MouseEvent event) {

		// 백 버튼 클릭시 일시정지 이벤트 처리
		backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (pause_control == true) {
					// 현재 공이 동작중이면
					pathTransition.pause(); // 동작을 일시중지 시킨다
					pause_control = false;

					System.out.println(pathTransition.getStatus()); // 현재 공 상태를 콘솔에서 확인해보려고(지워도 무방합니다)
				}

				else if (pause_control == false) {
					// 현재 공이 일시중지 상태이면
					pathTransition.play(); // 공을 실행시킨다
					pause_control = true;
				}
			}
		});

		timeline = new Timeline(); // 타임라인 객체 생성

		// pointer가 뫼비우스 띠 모양의 path를 한 싸이클당 7초 주기로 회전하는 트랜지션 객체 생성
		pathTransition = new PathTransition(Duration.millis(7000), model.pathCreate(), pointer);
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setAutoReverse(false); // 한 싸이클이 끝나면 역방향으로 돌지 않게 설정.

		timeline.setAutoReverse(false); // 타임라인 또한 마찬가지.

		// timeline의 한 싸이클이 끝날 때마다 실행될 이벤트
		EventHandler onfinished = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				pathTransition.play(); // 이 문장이 업으면 pathTransition동작이 실행되지 않습니다.
			}
		};

		KeyValue kv = new KeyValue(pathTransition.cycleCountProperty(), 5); // path경로 반복 횟수를 5번으로 지정.
																			// pathTransition.setCycleCount(5)와 동일합니다!

		KeyFrame kf = new KeyFrame(new Duration(0), onfinished, kv); // timeline.play();를 하면 onfinished에 의해
																		// pathTransition도 바로 실행 시작
		KeyFrame kf2 = new KeyFrame(new Duration(10000), kv); // 10초 단위로 timeline의 주기가 설정

		timeline.setCycleCount(3); // 3번의 반복설정. 즉, 10초간 3회이므로 30초간 timeline이 실행되게 설정
		timeline.getKeyFrames().add(kf); // 만든 keyFrame을 timeline에 추가
		timeline.getKeyFrames().add(kf2);

		keyValueCreate(); // 공이 무작위의 시간마다 색이 변경되도록 하는 keyFrame들 생성
		timeline.play(); // 타임라인 실행. 이 문장이 없으면 pathTransition 또한 동작하지 않습니다

		/*
		 * 중간중간 미세한 시간차이 때문에 일시정지 버튼을 눌른 후에 갑자기 공이 움직여져 버리는 오류가 발생하여 다음 두 문장을 추가합니다!
		 * 실행중일 때는 반드시 true를 유지하도록 하는 것이에요
		 */
		if (pathTransition.getStatus() == Status.RUNNING)
			pause_control = true;

	}

	public void keyValueCreate() {
		/*
		 * 위 정의해 두었던 color값 배열을 이용하여 시간간격을 임의로 바꿔줌에 따라 새로운 키프레임을 생성하는 메소드 만들어진 키 프레임들은
		 * timeline에서 사용됩니다. 결과적으로 어떤 색이 몇 초의 값을 갖느냐에 따라 임의의 시간동안 해당 색깔이 유지됩니다. 이 부분은
		 * 허점이 많기에.... 싹 다 날리거나 수정하거나 원하시는대로 수정하셔도 무방합니다!
		 */

		KeyValue[] kv = new KeyValue[5]; // color배열과 동일한 크기의 배열
		int d = 0; // duration 계산을 위해 초기값 0지정

		for (int i = 0; i < kv.length; i++) {
			Collections.shuffle(Arrays.asList(color)); // color배열을 리스트화 시켜서 배열 내 순서를 랜덤화 시켜주는 문장

			kv[i] = new KeyValue(pointer.fillProperty(), color[i]); // 순서가 랜덤화된 배열에서 color값을 가져와 keyValue객체 생성

			// duration값 임의로 계산
			if (d <= 10000)
				d += 3000;
			else if (d <= 30000)
				d += 2000;
			else if (d <= 50000)
				d += 4000;
			else if (d <= 100000)
				d += 6000;
			else if (d <= 200000)
				d += 8000;

			KeyFrame kf = new KeyFrame(Duration.millis(d), kv[i]);
			timeline.getKeyFrames().add(kf); // keyValue값을 가지고 만들어진 KeyFrame을 타임라인에 추가
		}

	}

}
