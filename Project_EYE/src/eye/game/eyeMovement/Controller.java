package eye.game.eyeMovement;


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

public class Controller implements Initializable{

	Model model = new Model();	//�𵨰�ü ����
	Timeline timeline;	//Ÿ�Ӷ��� ��ü ����

	private PathTransition pathTransition;	//pathƮ������ ����
	private boolean pause_control = true;	//�� �������� �������̸� true. �Ͻ����� ���¸� false.��� �����Ͽ� �ʱⰪ�� true�� �ش�.

	private Color[] color = {Color.RED, Color.AQUA, Color.YELLOW, Color.VIOLET, Color.BLACK};	//�� ���� ��ȭ�� ���� color�� �迭

	@FXML private Circle pointer;	//�� ��ü
	@FXML private ImageView backBtn;	//�� ��ư(�ڷΰ��� �� �Ͻ����� ����� ����)

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Controller ������ �ڵ�
	}


	@FXML	//poiner�� #moving���� ������ �޼ҵ�, �� Ŭ���� �ִϸ��̼��� ����.
	public void moving(MouseEvent event) {

		//�� ��ư Ŭ���� �Ͻ����� �̺�Ʈ ó��
		backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(pause_control == true) {
					//���� ���� �������̸�
					pathTransition.pause();	//������ �Ͻ����� ��Ų��
					pause_control = false;

					System.out.println(pathTransition.getStatus());	//���� �� ���¸� �ֿܼ��� Ȯ���غ�����(������ �����մϴ�)
				}

				else if(pause_control == false) {
					//���� ���� �Ͻ����� �����̸�
					pathTransition.play();	//���� �����Ų��
					pause_control = true;
				}
			}
		});

		timeline = new Timeline();	//Ÿ�Ӷ��� ��ü ����

		//pointer�� ����콺 �� ����� path�� �� ����Ŭ�� 7�� �ֱ�� ȸ���ϴ� Ʈ������ ��ü ����
		pathTransition = new PathTransition(Duration.millis(7000), model.pathCreate(), pointer);
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setAutoReverse(false);	//�� ����Ŭ�� ������ ���������� ���� �ʰ� ����.

		timeline.setAutoReverse(false);		//Ÿ�Ӷ��� ���� ��������.

		//timeline�� �� ����Ŭ�� ���� ������ ����� �̺�Ʈ
		EventHandler onfinished = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				pathTransition.play();	//�� ������ ������ pathTransition������ ������� �ʽ��ϴ�.
			}
		};

		KeyValue kv = new KeyValue(pathTransition.cycleCountProperty(), 5);	//path��� �ݺ� Ƚ���� 5������ ����. pathTransition.setCycleCount(5)�� �����մϴ�!

		KeyFrame kf = new KeyFrame(new Duration(0), onfinished, kv);	//timeline.play();�� �ϸ� onfinished�� ���� pathTransition�� �ٷ� ���� ����
		KeyFrame kf2 = new KeyFrame(new Duration(10000), kv);	//10�� ������ timeline�� �ֱⰡ ����

		timeline.setCycleCount(3);	//3���� �ݺ�����. ��, 10�ʰ� 3ȸ�̹Ƿ� 30�ʰ� timeline�� ����ǰ� ����
		timeline.getKeyFrames().add(kf);	//���� keyFrame�� timeline�� �߰�
		timeline.getKeyFrames().add(kf2);

		keyValueCreate();	//���� �������� �ð����� ���� ����ǵ��� �ϴ� keyFrame�� ����
		timeline.play();	//Ÿ�Ӷ��� ����. �� ������ ������ pathTransition ���� �������� �ʽ��ϴ�


		/*
		 * �߰��߰� �̼��� �ð����� ������ �Ͻ����� ��ư�� ���� �Ŀ� ���ڱ� ���� �������� ������ ������ �߻��Ͽ� ���� �� ������ �߰��մϴ�!
		 * �������� ���� �ݵ�� true�� �����ϵ��� �ϴ� ���̿���
		 */
		if(pathTransition.getStatus() == Status.RUNNING)
			pause_control = true;

	}


	public void keyValueCreate() {
		/*
		 * �� ������ �ξ��� color�� �迭�� �̿��Ͽ� �ð������� ���Ƿ� �ٲ��ܿ� ���� ���ο� Ű�������� �����ϴ� �޼ҵ�
		 * ������� Ű �����ӵ���  timeline���� ���˴ϴ�.
		 * ��������� � ���� �� ���� ���� �����Ŀ� ���� ������ �ð����� �ش� ������ �����˴ϴ�.
		 * �� �κ��� ������ ���⿡.... �� �� �����ų� �����ϰų� ���Ͻô´�� �����ϼŵ� �����մϴ�!
		 */

		KeyValue[] kv = new KeyValue[5];	//color�迭�� ������ ũ���� �迭
		int d = 0;	//duration ����� ���� �ʱⰪ 0����

		for (int i = 0; i < kv.length; i++) {
			Collections.shuffle(Arrays.asList(color));	//color�迭�� ����Ʈȭ ���Ѽ� �迭 �� ������ ����ȭ �����ִ� ����

			kv[i] = new KeyValue(pointer.fillProperty(), color[i]);	//������ ����ȭ�� �迭���� color���� ������ keyValue��ü ����

			//duration�� ���Ƿ� ���
			if(d <= 10000)
				d+=3000;
			else if(d <= 30000)
				d+=2000;
			else if(d <= 50000)
				d+=4000;
			else if(d <= 100000)
				d+=6000;
			else if(d <= 200000)
				d+=8000;

			KeyFrame kf = new KeyFrame(Duration.millis(d), kv[i]);
			timeline.getKeyFrames().add(kf);	//keyValue���� ������ ������� KeyFrame�� Ÿ�Ӷ��ο� �߰�
		}

	}



}
