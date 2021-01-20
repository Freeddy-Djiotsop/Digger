package digger.logic;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Digger {

	Image digUp1;
	Image digUp2;
	Image digUp3;
	Image digUp4;
	Image digUp5;
	Image digUp6;
	Image currentImageOfDigUp;
	Image digDown1;
	Image digDown2;
	Image digDown3;
	Image digDown4;
	Image digDown5;
	Image digDown6;
	Image currentImageOfDigDown;
	Image digRight1;
	Image digRight2;
	Image digRight3;
	Image digRight4;
	Image digRight5;
	Image digRight6;
	Image currentImageOfDigRight;
	Image digLeft1;
	Image digLeft2;
	Image digLeft3;
	Image digLeft4;
	Image digLeft5;
	Image digLeft6;
	Image currentImageOfDigLeft;

	public int diggerX = 400, diggerY = 540;
	public int fireBallX=diggerX , fireBallY=diggerY ;

	public Digger() {
		digUp1 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigUp1.jpg"));
		digUp2 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigUp2.jpg"));
		digUp3 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigUp3.jpg"));
		digUp4 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigUp4.jpg"));
		digUp5 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigUp5.jpg"));
		digUp6 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigUp6.jpg"));
		currentImageOfDigUp = digUp1;
		digDown1 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigDown1.jpg"));
		digDown2 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigDown2.jpg"));
		digDown3 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigDown3.jpg"));
		digDown4 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigDown4.jpg"));
		digDown5 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigDown5.jpg"));
		digDown6 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigDown6.jpg"));
		currentImageOfDigDown = digDown1;
		digRight1 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigRight1.jpg"));
		digRight2 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigRight2.jpg"));
		digRight3 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigRight3.jpg"));
		digRight4 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigRight4.jpg"));
		digRight5 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigRight5.jpg"));
		digRight6 = new Image(this.getClass().getResourceAsStream("/digger/gui/extend/DigRight6.jpg"));
		currentImageOfDigRight = digRight1;


	}

	private void setImageUp() {
		if (currentImageOfDigUp.equals(digUp1)) {
			currentImageOfDigUp = digUp2;
			return;
		}
		if (currentImageOfDigUp.equals(digUp2)) {
			currentImageOfDigUp = digUp3;
			return;
		}
		if (currentImageOfDigUp.equals(digUp3)) {
			currentImageOfDigUp = digUp4;
			return;
		}
		if (currentImageOfDigUp.equals(digUp4)) {
			currentImageOfDigUp = digUp5;
			return;
		}
		if (currentImageOfDigUp.equals(digUp5)) {
			currentImageOfDigUp = digUp6;
			return;
		}
		if (currentImageOfDigUp.equals(digUp6)) {
			currentImageOfDigUp = digUp1;
			return;
		}
	}

	private void setImageDown() {
		if (currentImageOfDigDown.equals(digDown1)) {
			currentImageOfDigDown = digDown2;
			return;
		}
		if (currentImageOfDigDown.equals(digDown2)) {
			currentImageOfDigDown = digDown3;
			return;
		}
		if (currentImageOfDigDown.equals(digDown3)) {
			currentImageOfDigDown = digDown4;
			return;
		}
		if (currentImageOfDigDown.equals(digDown4)) {
			currentImageOfDigDown = digDown5;
			return;
		}
		if (currentImageOfDigDown.equals(digDown5)) {
			currentImageOfDigDown = digDown6;
			return;
		}
		if (currentImageOfDigDown.equals(digDown6)) {
			currentImageOfDigDown = digDown1;
			return;
		}
	}

	public void changeImage(GraphicsContext gc, String direction) {
		switch (direction) {
		case "UP":
			
			gc.drawImage(currentImageOfDigUp, diggerX, diggerY, 20, 20);
			setImageUp();
			break;
		case "BOT":
			
			gc.drawImage(currentImageOfDigDown, diggerX, diggerY, 20, 20);
			setImageDown();
			break;
		case "Right":
			break;
		case "Left":
			break;
		}
	}
//	public void drawFireBall(GraphicsContext gc )
//    {
//     
//        gc.setFill(Color.BLUE);
//        gc.fillOval(fireBallX,fireBallY , 15, 15);
//    
//    
//    }

	public void draw(GraphicsContext gc) {
//		gc.drawImage(new Image(getClass().getResourceAsStream("/digger/gui/extend/diggerChar.jpg")), diggerX, diggerY,
//				20, 20);
		gc.drawImage(currentImageOfDigUp, diggerX, diggerY, 20, 20);
	}
//	public void drawDigUP(GraphicsContext gc) {
////		gc.setFill(Color.BLUE);
////		gc.fillRect(diggerX, diggerY, 20, 20);
//		gc.drawImage(currentImageOfDigUp, diggerX, diggerY, 20, 20);
//		
//	}
//	public void drawDigDown(GraphicsContext gc) {		
//		gc.drawImage(currentImageOfDigDown, diggerX, diggerY, 20, 20);
//
//	}
}