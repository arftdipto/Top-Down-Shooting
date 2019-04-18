import java.awt.*;
import java.lang.*;

public class Bullet{
	private double x,y,dx,dy,rad,speed;
	private int r;
	private Color color1;
	
	public Bullet(double angle,int x,int y){
		this.x = x;
		this.y = y;
		r = 4;
		
		rad = Math.toRadians(angle);
		dx = Math.cos(rad)*10;
		dy = Math.sin(rad)*10;
		//speed = 15;
		
		color1 = Color.YELLOW;
	}
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public int getR(){
		return r;
	}
	
	public boolean update(){
		x += dx;
		y += dy;
		
		if(x < -r || x > GamePanel.width + r || y < -r || y > GamePanel.height + r){
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D g){
		g.setColor(color1);
		g.fillOval((int)x-r,(int)y-r,4*r,4*r);
	}
}