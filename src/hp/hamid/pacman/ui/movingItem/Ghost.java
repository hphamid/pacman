package hp.hamid.pacman.ui.movingItem;

import java.awt.Color;
import java.awt.Graphics;

import hp.hamid.pacman.ui.MainClass;
import hp.hamid.pacman.ui.TimeMachine;
import hp.hamid.pacman.ui.items.GameItem;

public abstract class Ghost extends MovingGameItem {
	private Point target;
	private Point nextMove;
	private Point nextpos;
	private Point currentPos;
	private boolean runAway = true;
	private long startedRunAway = 0;
	private final static int runawaylenght = 150;
	public final static Point main = new Point(14, 10);
	private int lastGotMove = 0;
	private int lastMoved = 0;
	public boolean hasExited = false;
	public Thread calculationThread = new Thread(this.toString()) {
		@Override
		public void run() {
			findNextMove();
		}
	};

	public Ghost() {
		this.callMain();
	}

	public boolean isRunAway() {
		return this.runAway;
	}

	public void setRunAway(boolean arg) {
		this.startedRunAway = TimeMachine.getInstance().getTime();
		this.runAway = arg;
	}

	public void callMain() {
		if (calculationThread != null) {
			try {
				calculationThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.doMain();
	}

	public abstract void doMain();

	public void postCreate(Point firstMove) {
		this.runAway = false;
		this.lastGotMove = 0;
		this.lastMoved = 0;
		this.hasExited = false;
		this.nextpos = new Point(getx(), gety());
		this.setNextMove(firstMove);
		this.move(this.nextMove.y, this.nextMove.x);
		this.goAndFindNextMove();
	}

	@Override
	public void MoveDone() {
		synchronized (this) {
			if (lastMoved >= lastGotMove) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		lastMoved++;
		this.move(nextMove.y, nextMove.x);
		this.goAndFindNextMove();
	}

	private void goAndFindNextMove() {
		calculationThread.run();

	}

	@Override
	public GameItem onCollide(GameItem item) {
		return null;
	}

	public void setTarget(int x, int y) {
		target = new Point(x, y);
	}

	public void checkTarget() {
		if (this.currentPos.x == main.x && this.currentPos.y == main.y) {
			this.hasExited = true;
		}
		if (this.isActive() && this.hasExited == false) {
			this.setTarget(main.x, main.y);
		} else if (this.isRunAway()) {
			this.setTarget((int) (Math.random() * MainClass.getInstance()
					.getWidth()), (int) (Math.random() * MainClass
					.getInstance().getHeight()));
		} else {
			findTarget();
		}
	}

	public abstract void findTarget();

	public void findNextMove() {
		this.checkTarget();
		Point[] moves = { new Point(0, -1), new Point(1, 0), new Point(0, 1),
				new Point(-1, 0) };
		int index = 0;
		int min = 10000;
		for (int i = 0; i < moves.length; i++) {
			if (canMovepos(nextpos.x + moves[i].x, nextpos.y + moves[i].y)
					&& !(nextpos.x + moves[i].x == currentPos.x && nextpos.y
							+ moves[i].y == currentPos.y)) {
				if ((Math.abs(nextpos.x + moves[i].x - target.x) + Math
						.abs(nextpos.y + moves[i].y - target.y)) < min) {
					index = i;
					min = Math.abs(nextpos.x + moves[i].x - target.x)
							+ Math.abs(nextpos.y + moves[i].y - target.y);
				}
			}
		}

		setNextMove(moves[index]);
	}

	private void setNextMove(Point nextMove) {
		this.currentPos = new Point(nextpos.x, nextpos.y);
		this.nextpos.x = this.nextpos.x + nextMove.x;
		this.nextpos.y = this.nextpos.y + nextMove.y;
		synchronized (this) {
			this.lastGotMove++;
			this.nextMove = nextMove;
			this.notifyAll();
		}
	}

	@Override
	public void draw(Graphics g) {
		checkcollide();
		if (isRunAway()) {
			g.setColor(new Color(0,100,255));
			g.fillRect(getPositionx(), getPositiony(), width, height);
		} else {
			ghostDraw(g);
		}
		if(runAway == true && TimeMachine.getInstance().getTime() >= startedRunAway + runawaylenght){
			this.runAway = false;
		}
	}

	private void checkcollide() {
		if ((Math
				.abs(this.getPositionx() - PacMan.getInstance().getPositionx()) < width && this
				.getPositiony() == PacMan.getInstance().getPositiony())
				|| (Math.abs(this.getPositiony()
						- PacMan.getInstance().getPositiony()) < height && this
						.getPositionx() == PacMan.getInstance().getPositionx())) {
			GameManager.getInstance().onColide(this);
		}
	}

	public abstract void ghostDraw(Graphics g);

	public abstract boolean isActive();
}

class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}