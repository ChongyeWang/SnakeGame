/**
This clsss implements the JFrame of the game.
*/
import javax.swing.JFrame;
public class Snake {
    public static void main(String[] args){
    	JFrame frame = new JFrame();
    	frame.setBounds(0, 0, 875, 675);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      SnakePanel panel = new SnakePanel();
      frame.add(panel);
      frame.setVisible(true);
    }
}
