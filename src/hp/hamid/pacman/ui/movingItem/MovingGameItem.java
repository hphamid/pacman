package hp.hamid.pacman.ui.movingItem;

import hp.hamid.pacman.ui.MainClass;
import hp.hamid.pacman.ui.TimeMachine;
import hp.hamid.pacman.ui.items.GameItem;

import java.awt.Graphics;

public abstract class MovingGameItem extends GameItem {
	public int length = 6;
	private int down = 0;
	private int right = 0;
	private long firstTime = 0;

	public MovingGameItem() {
	}

	public int getDown(){
		return down;
	}
	public int getRight(){
		return right;
	}
	
	@Override
	public abstract void draw(Graphics g);

	private void saveTime() {
		firstTime = TimeMachine.getInstance().getTime();
	}

	public void move(int down, int right) {
		saveTime();
		this.down = down;
		this.right = right;
	}

	private int getoffsetX() {
		if (right != 0) {
			if ((TimeMachine.getInstance().getTime() - firstTime) <= length) {
				return (int) ((TimeMachine.getInstance().getTime() - firstTime)
						* width / length)
						* right;
			}
			doneMoving();
		}
		return 0;
	}

	@Override
	public boolean isBlocked(int nowx, int nowy, MovingGameItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean canMove(int down, int right) {
		return this.canMovepos(getx() + right, gety() + down);
	}
	public boolean canMovepos(int x, int y){
		return !MainClass.getInstance().getMap()[getTranslatedY(y)][getTranslatedX(x)]
				.isBlocked(getx(), gety(), this);
	}

	public int getTranslatedY(int y) {
		return (y + MainClass.getInstance().getHeight())
				% MainClass.getInstance().getHeight();
	}

	public int getTranslatedX(int x) {
		return (x + MainClass.getInstance().getWidth())
				% MainClass.getInstance().getWidth();
	}

	private void doneMoving() {
		this.setPosition(this.getTranslatedX(this.getx() + right), this.getTranslatedY(this.gety() + down));
		right = 0;
		down = 0;
		MoveDone();
	}

	private int getoffsetY() {
		if (down != 0) {
			if ((TimeMachine.getInstance().getTime() - firstTime) <= length) {
				return (int) ((TimeMachine.getInstance().getTime() - firstTime)
						* height / length)
						* down;
			}
			doneMoving();
		}
		return 0;
	}

	public abstract void MoveDone();

	@Override
	public int getPositionx() {
		return getoffsetX() + super.getPositionx();
	}

	public boolean isMoving(){
		return down != 0 || right != 0;
	}
	
	@Override
	public void setPosition(int x, int y) {
		MainClass.getInstance().callOncolide(x,y, this);
		super.setPosition(x, y);
	}
	
	@Override
	public int getPositiony() {
		return getoffsetY() + super.getPositiony();
	}
}
