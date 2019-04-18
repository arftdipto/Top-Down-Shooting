import javax.swing.JFrame;

public class Game{
	public Game(){
		JFrame window = new JFrame("Everwing");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new GamePanel());
		window.pack();
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
	}
}