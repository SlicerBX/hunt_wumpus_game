import java.util.*;

public class Player {

	private boolean hasSpear;
	private int posX;
	private int posY;
	
	public Player() {
		hasSpear = true;
		posX = 0;
		posY = 0;
	}
	
	public void setPosX(int x) {
		posX = x;
	}
	
	public void setPosY(int y) {
		posY = y;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void moveUp() {
		posX--;
	}
	
	public void moveDown() {
		posX++;
	}
	
	public void moveRight() {
		posY++;
	}
	
	public void moveLeft() {
		posY--;
	}
	
}
