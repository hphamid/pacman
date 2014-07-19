package hp.hamid.pacman.ui;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class TimeMachine extends Observable {
	private static TimeMachine instance = new TimeMachine();
	private Timer timer;
	private long time = 0;
	class Task extends TimerTask {
		@Override
		public void run() {
			time++;
			TimeMachine.this.setChanged();
			TimeMachine.this.notifyObservers();
		}
	}
	public static TimeMachine getInstance(){
		return instance;
	}
	private TimeMachine() {
	}

	public void stop(){
		if(timer != null){
			timer.cancel();
		}
	}
	
	public void start(){
		timer = new Timer();
		timer.schedule(new Task(), 0, 20);
	}
	public long getTime(){
		return time;
	}
	public void reset(){
		this.time = 0;
	}

}
