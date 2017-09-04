import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements KeyListener, ActionListener {
    
	private int[] snake1x;
	private int[] snake1y;
	private int[] snake2x;
	private int[] snake2y;
	
	private int len1;
	private int len2;
	
	private String dir1;
	private String dir2;
	
	private int score1;
	private int score2;
	
	private int foodx;
	private int foody;
	private int bombx;
	private int bomby;
	private int railgunx;
	private int railguny;

	private Timer timer = new Timer(100, this);
	private Random rand = new Random();
	
	private boolean start;
	private boolean end;
	private boolean railgunAppear;
	
	private ArrayList<Integer> keysDown;
	
	private ImageIcon snake1head = new ImageIcon("snake1head.png");
	private ImageIcon snake2head = new ImageIcon("snake2head.png");
	private ImageIcon snake1body = new ImageIcon("snake1body.png");
	private ImageIcon snake2body = new ImageIcon("snake2body.png");
	private ImageIcon food = new ImageIcon("food.png");
	private ImageIcon bomb = new ImageIcon("bomb.png");
	private ImageIcon railgun = new ImageIcon("Railgun.png");
	
	
	public SnakePanel(){
		this.setFocusable(true);
		this.addKeyListener(this);
		initialize();
		timer.start();
	}
	
	
	public void initialize(){
		
		snake1x = new int[500];
		snake1y = new int[500];
		snake2x = new int[500];
		snake2y = new int[500];
	    
	    dir1 = "Right";
	    len1 = 2;
	    snake1x[0] = 200;
	    snake1y[0] = 100;
	    snake1x[1] = 175;
	    snake1y[1] = 100;
	    
	    dir2 = "Left";
	    len2 = 2;
	    snake2x[0] = 500;
	    snake2y[0] = 100;
	    snake2x[1] = 525;
	    snake2y[1] = 100;
		
	    //set up food and bomb
		foodx = rand.nextInt(32) * 25 + 25;
		foody = rand.nextInt(22) * 25 + 75;
		bombx = rand.nextInt(33) * 25 + 25;
	    bomby = rand.nextInt(22) * 25 + 75;
		while(bombx == foodx && bomby == foody){
			bombx = rand.nextInt(32) * 25 + 25;
			bomby = rand.nextInt(22) * 25 + 75;
		}
		railgunAppear = true;
		railgunx = rand.nextInt(32) * 25 + 25;
		railguny = rand.nextInt(32) * 25 + 25;
		while((railgunx == foodx && railguny == foody) || railgunx == bombx && railguny == bomby)
		{
			railgunx = rand.nextInt(32) * 25 + 25;
			railguny = rand.nextInt(32) * 25 + 25;
		}
		
		start = false;
		end = false;
		
		score1 = 0;
		score2 = 0;
		
		keysDown = new ArrayList<Integer>();
	
	}//Initialize
	
	
	public void paint(Graphics g){
		setBackground(Color.PINK);
		//g.fillRect(0, 0, 875, 675);	
		snake1head.paintIcon(this, g, snake1x[0], snake1y[0]);
		snake2head.paintIcon(this, g, snake2x[0], snake2y[0]);
		
		for(int i = 1; i < len1; i++){
			snake1body.paintIcon(this, g, snake1x[i], snake1y[i]);
		}
		
		for(int i = 1; i < len2; i++){
			snake2body.paintIcon(this, g, snake2x[i], snake2y[i]);
		}
		
		if(start == false){
			g.setColor(Color.BLUE);
			g.setFont(new Font("arial", Font.BOLD, 40));
			g.drawString("WELCOME", 330, 300);
		}
		if (end == true){
			g.setColor(Color.BLUE);
			g.setFont(new Font("arial", Font.BOLD, 30));
			if(score1 == score2){
				g.drawString("Try Again", 300, 300);
			}
			else if(score1 > score2){
			    g.drawString("WINNER: Snake1", 280, 300);
			}
			else{
				g.drawString("WINNER: Snake2", 280, 300);
			}
		}
		
		food.paintIcon(this, g, foodx, foody);
		bomb.paintIcon(this, g, bombx, bomby);
		
		if(railgunAppear == true){
			railgun.paintIcon(this, g, railgunx, railguny);
		}
		else{
			int temp = rand.nextInt(40);
    		if(temp != 7){
				railgunAppear = false;
			}
			else{
				railgunAppear = true;
			}
		}
		
		g.setColor(Color.BLUE);
		g.setFont(new Font("arial", Font.PLAIN, 20));
		g.drawString("Snake1: "+ score1, 760, 30);
		g.drawString("Snake2: "+ score2, 760, 50);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		timer.start();
		if(start == true && end == false){
			
			for(int i = len1; i > 0; i--){
	    		snake1x[i] = snake1x[i-1];
	    		snake1y[i] = snake1y[i-1];
	    	}
			
			for(int i = len2; i > 0; i--){
	    		snake2x[i] = snake2x[i-1];
	    		snake2y[i] = snake2y[i-1];
	    	}
		
		    //Snake1
			if (dir1.equals("Right")){
			    snake1x[0] = snake1x[0] + 25;
			    if(snake1x[0] > 850) snake1x[0] = 0;
			}
			else if (dir1.equals("Left")){
			    snake1x[0] = snake1x[0] - 25;
			    if(snake1x[0] < 0) snake1x[0] = 850;
			}
		 	else if (dir1.equals("Up")){
			    snake1y[0] = snake1y[0] - 25;
			    if(snake1y[0] < 0) snake1y[0] = 650;
			}
			else if (dir1.equals("Down")){
			    snake1y[0] = snake1y[0] + 25;
			    if(snake1y[0] > 650) snake1y[0] = 0;
			}
			
		    //Snake2
			if (dir2.equals("Right")){
			    snake2x[0] = snake2x[0] + 25;
			    if(snake2x[0] > 850) snake2x[0] = 0;
			}
			else if (dir2.equals("Left")){
			    snake2x[0] = snake2x[0] - 25;
			    if(snake2x[0] < 0) snake2x[0] = 850;
			}
		 	else if (dir2.equals("Up")){
			    snake2y[0] = snake2y[0] - 25;
			    if(snake2y[0] < 0) snake2y[0] = 650;
			}
			else if (dir2.equals("Down")){
			    snake2y[0] = snake2y[0] + 25;
			    if(snake2y[0] > 650) snake2y[0] = 0;
			}
			
			
			//Check food Collision
			if(snake1x[0] == foodx && snake1y[0] == foody){
	    		len1++;
	    		score1++;
	    		foodx = rand.nextInt(32) * 25 + 25;
	    		foody = rand.nextInt(22) * 25 + 75;
	    	}
			
			else if(snake2x[0] == foodx && snake2y[0] == foody){
	    		len2++;
	    		score2++;
	    		foodx = rand.nextInt(32) * 25 + 25;
	    		foody = rand.nextInt(22) * 25 + 75;
	    	}
			
			//Check railgun
			if(railgunAppear == true){
				if(snake1x[0] == railgunx && snake1y[0] == railguny){
		    		score1 = score1 + 5;
		    		railgunx = rand.nextInt(32) * 25 + 25;
		    		railguny = rand.nextInt(22) * 25 + 75;
		    		railgunAppear = false;
		    	}
				
				else if(snake2x[0] == railgunx && snake2y[0] == railguny){
		    		score2 = score2 + 5;
		    		railgunx = rand.nextInt(32) * 25 + 25;
		    		railguny = rand.nextInt(22) * 25 + 75;
		    		railgunAppear = false;
		    	}
			}
			
			
			//Check bomb Collision
			if(snake1x[0] == bombx && snake1y[0] == bomby){
	    		score1--;
	    		bombx = rand.nextInt(32) * 25 + 25;
	    		bomby = rand.nextInt(22) * 25 + 75;
	    	}
			
			else if(snake2x[0] == bombx && snake2y[0] == bomby){
	    		score2--;
	    		bombx = rand.nextInt(32) * 25 + 25;
	    		bomby = rand.nextInt(22) * 25 + 75;
	    	}
			
			//Check Self-Collision
			for(int i = 1; i < len1;i++){
	    		if(snake1x[0] == snake1x[i] && snake1y[0] == snake1y[i]){
	    			score1 = score1 - 2;
	    		}
	    	}
			
			for(int i = 1; i < len2;i++){
	    		if(snake2x[0] == snake2x[i] && snake2y[0] == snake2y[i]){
	    			score2 = score2 - 2;
	    		}
	    	}
			
			
			//Check Two-Snake-Collision
			for(int i = 0; i < len1; i++){
				for(int j = 0; j < len2; j++){
					if(snake1x[i] == snake2x[j] && snake1y[i] == snake2y[j]){
						end = true;
					}
				}
			}
		
		}
		
		repaint();/////////
			
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(!keysDown.contains(e.getKeyCode())){
			keysDown.add(new Integer(e.getKeyCode()));
		}// add multiple keyEvent
		
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_SPACE){
			if(end == true){
				initialize();
			}
			else{
				start = !start;
			}
			
		}/////////
		else{
			
			//Snake1
			if(keyCode == KeyEvent.VK_UP && dir1 != "Down"){
				dir1 = "Up";
			}
	        else if(keyCode == KeyEvent.VK_DOWN && dir1 != "Up"){
	        	dir1 = "Down";
			}
	        else if(keyCode == KeyEvent.VK_RIGHT && dir1 != "Left"){
	        	dir1 = "Right";
			}
	        else if(keyCode == KeyEvent.VK_LEFT && dir1 != "Right"){
	        	dir1 = "Left";
			}
			
			//Snake2
			if(keyCode == 87 && dir2 != "Down"){
				dir2 = "Up";
			}
	        else if(keyCode == 83 && dir2 != "Up"){
	        	dir2 = "Down";
			}
	        else if(keyCode == 68 && dir2 != "Left"){
	        	dir2 = "Right";
			}
	        else if(keyCode == 65 && dir2 != "Right"){
	        	dir2 = "Left";
			}
		
		}	
	}/////////////////
    
	public void keyReleased(KeyEvent e) {
		keysDown.remove(new Integer(e.getKeyCode()));
	}
	
    public void keyTyped(KeyEvent e) {
		
	}

}//end of class
