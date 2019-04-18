import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Player{
	private int x;
	private int y;
	private int r;
	private int p;
	
	private int dx;
	private int dy;
	private int speed;
	
	private boolean left,right,up,down;
	private boolean firing;
	private long  firingTimer;
	private long  firingDelay;
	
	private boolean recovering;
	private long recoveryTimer;
	
	private int lives;
	private Color color1,color2;
    private Image temp = new ImageIcon("player.png").getImage();

    private int score ;
	
	private int powerLevel;
	private int power;
	private int[] requiredPower ={1, 2, 3, 4, 5};
	
	
	public Player(){
		r = 15;
		p = GamePanel.width/2;
		x = GamePanel.width/2;
		y = GamePanel.height-r;
	
		dx=0; dy = 0; speed =5;
		lives = 3;
		color1 = Color.WHITE;
		color2 = Color.RED;
		
		firing = false;
		firingTimer = System.nanoTime();
		firingDelay = 300;	
		
		recovering = false ;
		recoveryTimer = 0;
		
		score = 0;
		
	}
	public int getx(){ return x; }
	public int gety(){ return y; }
	public int getr(){ return r; }
	
	public int getScore() {return score ;}
	public int getLives() {return lives;}
	
	public boolean isDead(){
		return lives<=0;
	}
	public boolean isRecovering() { return recovering; }
	
	
	public void setLeft(boolean b){
		left = b;
	}
	public void setRight(boolean b){
		right = b;
	}
	public void setUp(boolean b){
		up = b;
	}
	public void setDown(boolean b){
		down = b;
	}
	
	public void setFiring(boolean b){
		firing = b;
	}
	
	public void addScore(int i){
		 score += i*5;
	}
	
	public void gainLife(){
		lives++;
	}
	
	
	public void loseLife(){
		lives-- ;
		recovering = true ;
		recoveryTimer = System.nanoTime();
	}
	
	public void increasePower(int i){
		power += i;
		if(power >= requiredPower[powerLevel]){
			power -= requiredPower[powerLevel];
			powerLevel++;
		}
	}
	
	public int getPowerLevel() { return powerLevel ;}
	public int getPower() { return power ;}
	public int getRequiredPower() { return requiredPower[powerLevel];}
	
	
	public void update(){
		if(left){
			dx = -speed;
		}
		if(right){
			dx = speed;
		}
		if(up){
			dy = -speed;
		}
		if(down){
			dy = speed;
		}
		x += dx;
		y += dy;
		
		if(x < r){
			x = r;
		}
		if(y < r){
			y = r;
		}
		if(x > GamePanel.width-r){
			x = 500;
		}
		if(y > GamePanel.height-r){
			y = GamePanel.height - r;
		}
		
		dx = 0;
		dy = 0;
		
		
		if(firing){
			long elapsed =(System.nanoTime()-firingTimer) / 1000000;
			
			if(elapsed > firingDelay){
				
				firingTimer = System.nanoTime();
			     
				if(powerLevel < 2){
				GamePanel.bullets.add(new Bullet(270,x,650));
				}
				
				else if(powerLevel < 4){
				GamePanel.bullets.add(new Bullet(270,x+5,650));
				GamePanel.bullets.add(new Bullet(270,x-5,650));
				}
				else {
				GamePanel.bullets.add(new Bullet(270,x,650));
				GamePanel.bullets.add(new Bullet(275,x+5,650));
				GamePanel.bullets.add(new Bullet(265,x-5,650));
				}
				
				
			  
				
			}
		}
		if(recovering){
		long elapsed = (System.nanoTime() - recoveryTimer) / 1000000;
		if(elapsed > 2000){
			recovering = false ;
			recoveryTimer =0;
		   }
	    }
	}
	public void draw(Graphics2D g){
		if(recovering){
			System.out.println("Called");
		    g.drawImage(temp,x,640,null);
		}
		else{
		System.out.println("Called");
		g.drawImage(temp,x,640,null);
		}
	}
}