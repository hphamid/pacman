package hp.hamid.pacman.ui.movingItem;

import java.awt.Color;
import java.awt.Graphics;

import hp.hamid.pacman.ui.TimeMachine;
import hp.hamid.pacman.ui.items.GameItem;

public class PacMan extends MovingGameItem {
	private static PacMan instance = new PacMan();
	private int nextmoveDown = 0;
	private int nextmoveRight = 0;
	private long startedPowerTime;
	private final static int PowerTime = 250;
	private int moveDown = 0;
	private int moveRight = -1;
	private boolean jumpisEnable = false;
	private boolean jumpPressed = false;

	public void setNextMove(int down, int right) {
		this.nextmoveDown = down;
		this.nextmoveRight = right;
	}

	public void makeJumpEnable() {
		this.jumpisEnable = true;
	}
	public void jumpPressed(){
		this.jumpPressed = true;
	}

	public void startPower() {
		this.startedPowerTime = TimeMachine.getInstance().getTime();
		this.length = 3;
	}

	public void endPower() {
		this.length = 6;
	}

	public void reset() {
		instance = new PacMan();
	}

	public void die() {
		this.reset();

	}

	public static PacMan getInstance() {
		return instance;
	}

	private PacMan() {
		this.setPosition(14, 22);
		this.move(0, -1);
	}

	private void jump() {
		if(canMove(10*moveDown, 10*moveRight)){
			this.jumpisEnable = false;
			this.setPosition(getTranslatedX(getx() + 10 * moveRight), getTranslatedY(gety() + 10 * moveDown));
		}
	}

	@Override
	public void MoveDone() {
		if (jumpisEnable && jumpPressed) {
			this.jump();
		}
		jumpPressed = false;
		if (canMove(nextmoveDown, nextmoveRight)
				&& (nextmoveRight != 0 || nextmoveDown != 0)) {
			moveDown = nextmoveDown;
			moveRight = nextmoveRight;
		}
		if (canMove(moveDown, moveRight)) {
			this.move(moveDown, moveRight);
		}
	}

	@Override
	public void draw(Graphics g) {
		if (startedPowerTime + PowerTime < TimeMachine.getInstance().getTime()) {
			this.endPower();
		}
		g.setColor(new Color(255, 255, 0));
		// g.fillRect(getPositionx(), getPositiony(), width, height);
		g.fillRoundRect(getPositionx(), getPositiony(), width, height, width,
				height);
	}

	@Override
	public GameItem onCollide(GameItem item) {
		return null;
	}

}
