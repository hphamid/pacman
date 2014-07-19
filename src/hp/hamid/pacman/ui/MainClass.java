package hp.hamid.pacman.ui;

import hp.hamid.pacman.ui.items.Block;
import hp.hamid.pacman.ui.items.Dead;
import hp.hamid.pacman.ui.items.Dot;
import hp.hamid.pacman.ui.items.Empty;
import hp.hamid.pacman.ui.items.GameItem;
import hp.hamid.pacman.ui.items.Gate;
import hp.hamid.pacman.ui.items.Jump;
import hp.hamid.pacman.ui.items.Power;
import hp.hamid.pacman.ui.items.Speed;
import hp.hamid.pacman.ui.movingItem.GameManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainClass {
	public static MainClass instance;
	private static Object Lock = new Object();
	private GameItem[][] map;

	private JFrame frame;
	
	public int getHeight(){
		return map.length;
	}
	public int getWidth(){
		return map[0].length;
	}
	
	public static MainClass getInstance(){
		synchronized (Lock) {
			if(instance == null){
				instance = new MainClass();
			}
			return instance;
		}
	}
	public MainClass(){
		readMap();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				prepareGUI();
			}
		});
	}
	
	public GameItem[][] getMap(){
		return this.map;
	}
	
	public void prepareGUI(){
		frame = new JFrame("PacMan");
		JPanel panel = new PacManPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(panel.getPreferredSize());
		frame.setVisible(true);
	}
	public void showMessage(final String s, final String title, final boolean reset){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JOptionPane.showMessageDialog(frame, s, title, JOptionPane.PLAIN_MESSAGE);
				if(reset){
					GameManager.getInstance().resetGame();
				}
			}
		});
	}
	private GameItem getItem(String input){
		if(input.equals("b")){
			return new Block();
		}else if(input.equals("d")){
			return new Dead();
		}else if(input.equals("o")){
			GameManager.getInstance().addDot();
			return new Dot();
		}else if(input.equals("p")){
			return new Power();
		}else if(input.equals("g")){
			return new Gate();
		}else if(input.equals("e")){
			return new Empty();
		}else if(input.equals("s")){
			return new Speed();
		}else if(input.equals("j")){
			return new Jump();
		}
		return new Dead();
	}
	public void readMap(){
		try {
			loadMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void loadMap() throws IOException{
		GameItem[][] toReturn;
		int width = 0;
		int height = 0;
		BufferedReader reader = new BufferedReader(new FileReader("files/map.txt"));
		String line = null;
		line = reader.readLine();
		String[] items = line.split(" ");
		width = Integer.valueOf(items[1]);
		height = Integer.valueOf(items[0]);
		toReturn = new GameItem[height][width];
		int h = 0;
		while ((line = reader.readLine()) != null) {
			String[] data = line.split(" ");
			for(int i=0; i<data.length; i++){
				toReturn[h][i] = getItem(data[i]);
				toReturn[h][i].setPosition(i, h);
			}
			h++;
		}
		reader.close();
		this.map = toReturn;
	}
	public static void main(String[] args){
		getInstance();
		GameManager.getInstance().loadGhosts();
	}
	public void callOncolide(int x, int y, GameItem item) {
		synchronized (map) {
			map[y][x] = getMap()[y][x].onCollide(item);			
		}
	}
}