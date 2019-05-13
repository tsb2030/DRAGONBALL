package eye.game.findPicture;

import javafx.scene.image.ImageView;

public class WrongImage {
	private ImageView image;
	private int index;
	
	public ImageView getImage() {
		return image;
	}
	public void setImage(ImageView image) {
		this.image = image;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public WrongImage(ImageView image, int index) {
		this.image = image;
		this.index = index;
	}
	
}
