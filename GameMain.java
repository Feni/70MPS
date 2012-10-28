import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameMain extends JFrame implements KeyListener{
	public static void main(String args[]){
		System.out.println("70 Miles Per Second");
		new GameMain();
	}
	
	int width = 800;
	int height = 600;
	
	
	ArrayList attacks = new ArrayList<Attack>();
	
	public GameMain(){
		setVisible(true);
		setBounds(0,0,width,height);
		addKeyListener(this);
		createBufferStrategy(2);
		
		while(true){
			try{
				
				draw();
				update();
				Thread.sleep(100);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
	public void draw(){
		// Top down
		if(prespective == 3){
			topDown();
		}
		else if(prespective == 2){	// 3D
			side();
		}
	}
	
	public void topDown(){
		Graphics g = getBufferStrategy().getDrawGraphics();
		g.fillRect(0,0,width,height);
		g.setColor(Color.red);
		g.drawRect((int)c1.x,(int)c1.y,(int)c1.width,(int)c1.height);
		g.setColor(Color.green);
		g.drawRect((int)c2.x,(int)c2.y,(int)c2.width,(int)c2.height);
		
		g.setColor(Color.blue);
		for(int k = 0; k < attacks.size();k++){
			Attack a = (Attack) attacks.get(k);
			g.drawRect((int)a.x,(int)a.y,(int)a.width,(int)a.height);
		}
		g.dispose();
		getBufferStrategy().show();		
	}
	
	public void side(){
		
	}
	
	
	int prespective = 3;
	public void p1Draw(){
		Graphics g = getBufferStrategy().getDrawGraphics();
		g.fillRect(0,0,width,height);
		g.setColor(Color.red);
		
		int xOffset = (int) ((width/2)-c1.x);
		int yOffset = (int)((height/2)-c1.y);
		
		g.drawRect((int)c1.x+xOffset,(int)c1.y+yOffset,(int)c1.width,(int)c1.height);
		g.setColor(Color.green);
		g.drawRect((int)c2.x+xOffset,(int)c2.y+yOffset,(int)c2.width,(int)c2.height);
		g.setColor(Color.white);
		g.drawRect(100+xOffset,100+yOffset,10,10);
		
		g.dispose();
		getBufferStrategy().show();
	}
	
	public void p2Draw(){
		Graphics g = getBufferStrategy().getDrawGraphics();
		g.fillRect(0,0,width,height);
		g.setColor(Color.red);
		
		int xOffset = (int) ((width/2)-c2.x);
		int yOffset = (int)((height/2)-c2.y);
		
		g.drawRect((int)c1.x+xOffset,(int)c1.y+yOffset,(int)c1.width,(int)c1.height);
		g.setColor(Color.green);
		g.drawRect((int)c2.x+xOffset,(int)c2.y+yOffset,(int)c2.width,(int)c2.height);
		g.setColor(Color.white);
		g.drawRect(100+xOffset,100+yOffset,10,10);
		
		g.dispose();
		getBufferStrategy().show();		
	}
	
	public void update(){
		c1.interact(c2);
		c1.update();
		c2.update();
		
		for(int k = 0; k < attacks.size(); k++){
			Attack a = (Attack) attacks.get(k);
			a.interact(c1);			// this WILL NOT work the other way around. c1.interact(a)...
			a.interact(c2);
			
			a.update();
			if(a.hp <= 0){
				attacks.remove(k);
				k--;
			}
		}
		
		if(c1.hp <= 0){
			c1.x = -1000;
			c1.y = -1000;
			System.out.println("C1 hp: "+c1.hp);
		}
		
		if(c2.hp <= 0){
			c2.x = -1000;
			c2.y = -1000;
			System.out.println("C2 hp: "+c2.hp);
		}
	}
	
	Car c1 = new Car(200.0,100.0,20,20,10,0,0,0);
	Car c2 = new Car(100.0,100.0,20,20,10,0,0,0);
	
	public void keyPressed(KeyEvent e) {
 		if(e.getKeyCode() == KeyEvent.VK_1){
 			prespective = 1;
 		}
 		else if(e.getKeyCode() == KeyEvent.VK_2){
 			prespective = 2;
 		}
 		else if(e.getKeyCode() == KeyEvent.VK_3){
 			prespective = 3;
 		}

		if(e.getKeyCode() == KeyEvent.VK_UP){
			c1.up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			c1.down = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			c1.left = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			c1.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_W){
			c2.up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S){
			c2.down = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_A){
			c2.left = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){
			c2.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			attacks.add(new Attack(c1));
		}
		else if(e.getKeyCode() == KeyEvent.VK_E){
			attacks.add(new Attack(c2));
		}
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			c1.up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			c1.down = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			c1.left = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			c1.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_W){
			c2.up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S){
			c2.down = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_A){
			c2.left = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){
			c2.right = false;
		}
	}
 	public void keyTyped(KeyEvent e) {
 		
 	}
}

class GameObject{
	double x, y, width, height;
	
	double mass;
	
	double vx, vy;
	
	double theta;
	
	boolean collidable = true;
	
	Rectangle rect;
	
	double force = 0.0;
	
	double hp = 10;
	
	public GameObject(double ix, double iy, double iw, double ih, double m, double ivx, double ivy, double t){
		x = ix;
		y = iy;
		width = iw;
		height = ih;
		mass = m;
		vx = ivx;
		vy = ivy;
		theta = t;
		rect = new Rectangle( (int) ix,(int)iy,(int)iw,(int)ih);
	}
	
	public void interact(GameObject obj){
		if(obj.rect.intersects(rect)){
			System.out.println("Collision");
			double f1 = getForce();
			double t1 = getTheta();
			double f2 = obj.getForce();
			double t2 = obj.getTheta();
			
			System.out.println("Obj1: F "+f1+" T: "+t1);
			System.out.println("Obj1: F "+f2+" T: "+t2);
			obj.applyForce(-1*f1,-1*t1);
			applyForce(f1,t1);
			
			obj.applyForce(f2,t2);
			applyForce(-1*f2,-1*t2);
		}
	}
	public void applyForce(double f, double t){
		// TODO: this is just placeholder code. Put in some real math and physics here!!!
		force -= f;
		theta -= t;
		
		vx = Math.cos(theta) * (force/mass);
		vy = Math.sin(theta) * (force/mass);
	}
	
	public double getTheta(){
		if(vy != 0)
			return (theta = Math.atan(vx/vy));
		else{
			return (theta = 0);
		}
	}
	
	public double getForce(){
		return mass * getVelocity();
	}
	
	public double getVelocity()	{
		return Math.sqrt(Math.pow(vx,2)+Math.pow(vy,2));
	}
		
}

class Car extends GameObject{
	boolean up,down,left,right;
	
	public Car(){
		super(0,0,10,10,10,0,0,0);
		hp = 100;
	}
	
	public Car(double ix, double iy, double iw, double ih, double m, double ivx, double ivy, double t){
		super(ix,iy,iw,ih,m,ivx,ivy,t);
		hp = 100;
	}
	
	double topSpeed = 30;
	double maxAcc = 5;
	
	
	// ice has low coef friction
	double coefFriction = 0.01;
	
	public void update(){
		if(up && vy > -1 * topSpeed){
			vy-=maxAcc;
		}
		if(down && vy <  topSpeed){
			vy+=maxAcc;
		}
		if(left && vx > -1 * topSpeed){
			vx-=maxAcc;
		}
		if(right && vx <  topSpeed){
			vx+=maxAcc;
		}
		
		// Friction
		vx-=vx/(coefFriction*100)/10;
		vy-=vy/(coefFriction*100)/10;
		
		// Actual movement
		x+= vx;
		y+=vy;
		rect.setLocation((int)x,(int)y);
	}
}

class Attack extends GameObject{
	
	public Attack(Car c){
		super(c.x+c.width+c.vx+10,c.y+c.height/2,3,3,1,1,0,0);
	}
	
	public Attack(double ix, double iy, double iw, double ih, double m, double ivx, double ivy, double t){
		super(ix,iy,iw,ih,m,ivx,ivy,t);
		hp = 1;
		mass = 1;
	}
	public void interact(GameObject obj){
		if(obj.rect.intersects(rect)){
			System.out.println("Collision");
			super.interact(obj);
			obj.hp -= hp;
			hp = 0;
		}
	}
	
	public void update(){
		x+=vx;
		y+=vy;
	}
}