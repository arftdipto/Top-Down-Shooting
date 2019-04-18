import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable,KeyListener{
	public static int width  = 550;
	public static int height = 700;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS = 30;
	private double averageFPS;
	
	public static Player player;
	public static ArrayList<Bullet>bullets;
	public static ArrayList<Enemy>enemies;
	public static ArrayList<PowerUp> powerups;
	public static ArrayList<Explosion> explosions;
	
	private long waveStartTimer;
	private long waveStartTimerDiff;
	private int waveNumber;
	private boolean waveStart;
	private int waveDelay = 2000;
	
	public GamePanel(){
		super();
		setPreferredSize(new Dimension(width,height));
		setBounds(0,0,550,750);
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
		addKeyListener(this);
	}
	
	public void run(){
		running = true;
		image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(
		RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(
		RenderingHints.KEY_TEXT_ANTIALIASING,
		RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		player = new Player();
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		powerups= new ArrayList<PowerUp>();
		explosions = new ArrayList<Explosion>();
		
		waveStartTimer=0;
		waveStartTimerDiff=0;
		waveStart=true;
		waveNumber=0;
			
		long startTime;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;
		
		int framecount = 0;
		int maxFrameCount = 30;
		long targetTime = 1000 / FPS;
		
		while(running){
			startTime = System.nanoTime();

			gameUpdate();
			gameRender();
			gameDraw();
			
			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMillis;
			try{
				Thread.sleep(waitTime);
			}catch(Exception e){
				
			}
			totalTime += System.nanoTime() - startTime; 
			framecount++;
			if(framecount == maxFrameCount){
				averageFPS = 1000.0 / ((totalTime / framecount) / 1000000);
				framecount = 0;
				totalTime = 0;
			}
		}
		
		g.setColor(new Color(0,100,255));
		g.fillRect(0,0,width,height);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Century Gothic",Font.PLAIN,20));
		String s = "GAME OVER";
		int length = (int) g.getFontMetrics().getStringBounds(s,g).getWidth();
		g.drawString(s, (width-length)/2, height/2);
		s = "Final Score: "+player.getScore();  
		g.drawString(s, (width-length)/2-15, height/2+40);
		gameDraw();
		//WingGamePlayOption temp = new WingGamePlayOption();
		
	}
	
	private void gameUpdate(){
		System.out.println("Updating....");
		
		if(waveStartTimer == 0 && enemies.size() == 0){
			waveNumber++;
			waveStart = false;
			waveStartTimer = System.nanoTime();
		}
		else{
			waveStartTimerDiff = (System.nanoTime() - waveStartTimer)/1000000;
			if(waveStartTimerDiff > waveDelay ){
				waveStart = true ;
				waveStartTimer = 0;
				waveStartTimerDiff = 0; 
			}
		}
		
		if(waveStart && enemies.size() == 0){
			createNewEnemies();
		}
		
		player.update();
		for(int i=0;i< bullets.size();i++){
			boolean remove = bullets.get(i).update();
			if(remove){
				bullets.remove(i);
				i--;
			}
		}
		
		for(int i=0;i<enemies.size();i++){
			enemies.get(i).update();
		}
		
		for(int i =0; i<powerups.size(); i++ ){
			boolean remove = powerups.get(i).update();
			if(remove){
				powerups.remove(i);
				i--;
			}
		}
		
		for(int i =0; i<explosions.size(); i++ ){
			boolean remove = explosions.get(i).update();
			if(remove){
				explosions.remove(i);
				i--;
			}
		}
		
		
		for(int i = 0; i < bullets.size();i++){
			Bullet b = bullets.get(i);
			double bx = b.getX();
			double by = b.getY();
			double br = b.getR();
			for(int j = 0;j< enemies.size();j++){
				Enemy e = enemies.get(j);
				double ex = e.getX();
			    double ey = e.getY();
			    double er = e.getR();
				
				double dx = bx - ex;
				double dy = by - ey;
				double dist = Math.sqrt(dx*dx+dy*dy);
				
				if(dist < br +er){
					e.hit();
					bullets.remove(i);
					i--;
					break;
				}
			}
		}
		
		for(int i = 0; i<enemies.size();i++){
			if(enemies.get(i).isDead()){
				Enemy e = enemies.get(i);
				
				double rand = Math.random();
				if(rand < 0.001) powerups.add(new PowerUp(1,e.getX(), e.getY()));
				else if (rand < 0.020) powerups.add(new PowerUp(3,e.getX(), e.getY()));
				else if (rand < 0.120) powerups.add(new PowerUp(2,e.getX(), e.getY()));
				
				
				player.addScore(e.getType() + e.getRank());
				enemies.remove(i);
				i--;
				
				e.explode();
				explosions.add(new Explosion(e.getX(), e.getY(),e.getR(),e.getR()+20));
			}
		}
		
		if(player.isDead()){
			running = false;
		}
		
		if(!player.isRecovering()){
			int px = player.getx();
			int py = player.gety();
			int pr = player.getr();
			for(int i=0 ; i< enemies.size() ; i++){
				Enemy e = enemies.get(i);
				double ex = e.getX();
				double ey = e.getY();
				double er = e.getR();
				
				double dx = px - ex;
				double dy = py - ey;
				double dist = Math.sqrt(dx * dx + dy * dy);
				
				if(dist < pr + er){
					player.loseLife();
				}
			}
		}
		
		int px = player.getx();
		int py = player.gety();
		int pr = player.getr();
		for(int i=0 ; i< powerups.size(); i++){
			PowerUp p =  powerups.get(i);
			double x = p.getx();
			double y = p.gety();
			double r = p.getr();
			double dx = px - x ;
			double dy = py - y ;
			double dist = Math.sqrt(dx * dx + dy * dy);
			
			if(dist < pr + r){
				int type = p.getType();
				if( type ==  1 ){
					player.gainLife();
				}
				if( type == 2){
					player.increasePower(1);
				}
				if( type == 3){
					player.increasePower(2);
				}
				powerups.remove(i);
			}
		}
	}
	
	private void gameRender(){
		System.out.println("Rendering...");
		g.setColor(new Color(120,21,45));
		g.fillRect(0,0,width,height);
		
		
		
		player.draw(g);
		
		for(int i=0;i < bullets.size();i++){
			bullets.get(i).draw(g);
		}
		
		
		for(int i=0;i<enemies.size();i++){
			enemies.get(i).draw(g);
		}
		
		for(int i=0;i<powerups.size();i++){
			powerups.get(i).draw(g);
		}
		for(int i=0;i<explosions.size();i++){
			explosions.get(i).draw(g);
		}
		
		
		if(waveStartTimer != 0){
			g.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			String s ="-W A V E  "+waveNumber + "  -";
			int length = (int) g.getFontMetrics().getStringBounds(s,g).getWidth();
			int alpha =(int)(255 * Math.sin(3.14 * waveStartTimerDiff / waveDelay));
			if(alpha > 255) alpha = 255;
			g.setColor(new Color(255,255,255,alpha));
			g.drawString(s, width/2 - length/2, height/2);
		}
		
		for(int i= 0; i < player.getLives() ; i++){
			g.setColor(Color.WHITE);
			g.fillOval(20 + (20 * i),20,player.getr() * 2,player.getr() * 2);
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.WHITE.darker());
			g.drawOval(20 + (20 * i),20,player.getr() * 2,player.getr() * 2);
			g.setStroke(new BasicStroke(1));
		}
		
		g.setColor(Color.YELLOW);
		g.fillRect(20,70,player.getPower() * 8,8);
		g.setColor(Color.YELLOW.darker());
		g.setStroke(new BasicStroke(2));
		for(int i=0 ; i< player.getRequiredPower(); i++){
			g.drawRect(20 + 8 * i, 70, 8,8);
		}
		g.setStroke(new BasicStroke(1));
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		g.drawString("SCORE: " +player.getScore(), width-100, 30);
		
	}
	
	private void gameDraw(){
		Graphics g2 = this.getGraphics();
		g2.drawImage(image,0,0,null);
		g2.dispose();
	}
	
	private void createNewEnemies(){
		enemies.clear();
		Enemy e ;
		if(waveNumber == 1){
			for(int i = 0; i<4; i++){
				enemies.add(new Enemy(1,1));
			}
		}
		if(waveNumber == 2){
			for(int i = 0; i<8; i++){
				enemies.add(new Enemy(1,1));
			}
			enemies.add(new Enemy(1,2));
			enemies.add(new Enemy(1,2));
		}
		
		if(waveNumber == 3){
			enemies.add(new Enemy(1,2));
			enemies.add(new Enemy(1,3));
			enemies.add(new Enemy(1,4));
		}
		
	}
	
	
	public void keyTyped(KeyEvent key){}
	public void keyPressed(KeyEvent key){
		int keyCode = key.getKeyCode();
		if(keyCode == KeyEvent.VK_LEFT){
			player.setLeft(true);
		}
		if(keyCode == KeyEvent.VK_RIGHT){
			player.setRight(true);
		}
		if(keyCode == KeyEvent.VK_UP){
			player.setUp(true);
		}
		if(keyCode == KeyEvent.VK_DOWN){
			player.setDown(true);
		}
		if(keyCode == KeyEvent.VK_F){
			player.setFiring(true);
		}
	
    }
	public void keyReleased(KeyEvent key){
		int keyCode = key.getKeyCode();
		if(keyCode == KeyEvent.VK_LEFT){
			player.setLeft(false);
		}
		if(keyCode == KeyEvent.VK_RIGHT){
			player.setRight(false);
		}
		if(keyCode == KeyEvent.VK_UP){
			player.setUp(false);
		}
		if(keyCode == KeyEvent.VK_DOWN){
			player.setDown(false);
		}
		if(keyCode == KeyEvent.VK_F){
			player.setFiring(false);
		}
	}
	
}