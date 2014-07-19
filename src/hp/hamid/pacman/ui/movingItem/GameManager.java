package hp.hamid.pacman.ui.movingItem;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import hp.hamid.pacman.ui.MainClass;
import hp.hamid.pacman.ui.TimeMachine;

public class GameManager {
	private static GameManager instance;
	
	public int numberOfDots = 0;
	public int numberOfEatenDots = 0;
	private final int lifeCount = 10;
	private int life = lifeCount;
	List<Ghost> ghosts = new ArrayList<Ghost>();
	public GameManager(){
		this.life = lifeCount;
	}
	
	public void loadGhosts(){
		ghosts = new ArrayList<Ghost>();
		ghosts.add(new Blinky());
		ghosts.add(new Pinky());
		ghosts.add(new Inky());
		ghosts.add(new Clyde());
	}
	public List<Ghost> getGhosts(){
		return ghosts;
	}
	
	public static GameManager getInstance(){
		if(instance == null){
			instance = new GameManager();
		}
		return instance;
	}
	
	public void addDot(){
		this.numberOfDots += 1;
	}
	public void eatDot(){
		this.numberOfEatenDots++;
		this.checkForWin();
	}
	public int getEatenDots(){
		return this.numberOfEatenDots;
	}
	public void checkForWin(){
		if(this.numberOfDots <= this.numberOfEatenDots){
			win();
		}
	}
	
	public void resetGame(){
		instance = new GameManager();
		MainClass.getInstance().readMap();
		TimeMachine.getInstance().reset();
		PacMan.getInstance().reset();
		instance.loadGhosts();
		TimeMachine.getInstance().stop();
		TimeMachine.getInstance().start();
	}
	
	public void newLife(){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				life --;
				loadGhosts();
				PacMan.getInstance().reset();
				
			}
		});
	}
	
	public void win(){
		TimeMachine.getInstance().stop();
		MainClass.getInstance().showMessage("you won!" , "won", true);
	}
	
	public void onColide(Ghost item){
		if(item.isRunAway()){
			item.callMain();
		}else{
			this.lose();
		}
	}
	
	public void lose(){
		if(life > 1){
			GameManager.getInstance().newLife();
		}else{
			TimeMachine.getInstance().stop();
			MainClass.getInstance().showMessage("you lost!" , "lost", true);
		}
	}

	public void power() {
		for(Ghost item: ghosts){
			item.setRunAway(true);
		}
	}
}
