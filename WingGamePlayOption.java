import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WingGamePlayOption extends JFrame{
	JButton btn3,btn4,btn5,btn6;
	JPanel optionPanel;
	JLabel optionLabel;
	Option optionListener = new Option();
	
	public WingGamePlayOption(){
		this.setIconImage(new ImageIcon("frameicon.png").getImage());
		this.setTitle("EVERWING");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1366,768);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.optionPanel = new JPanel();
		this.optionPanel.setLayout(null);
		addComponent();
		this.setVisible(true);
	}
	
	public void addComponent(){
		this.optionPanel.setBackground(new Color(120,21,45));
		
		this.btn3 = new JButton("PLAY");
		this.btn3.setBounds(600,580,130,30);
		this.btn3.setForeground(Color.blue);
		this.optionPanel.add(btn3);
		this.btn3.addActionListener(optionListener);
		
		this.btn4 = new JButton("LEADERBOARD");
		this.btn4.setBounds(600,620,130,30);
		this.btn4.setForeground(Color.blue);
		this.optionPanel.add(btn4);
		this.btn4.addActionListener(optionListener);
		
		this.btn5 = new JButton("HELP");
		this.btn5.setBounds(600,660,130,30);
		this.btn5.setForeground(Color.blue);
		this.optionPanel.add(btn5);
		this.btn5.addActionListener(optionListener);
		
		this.btn6 = new JButton("EXIT");
		this.btn6.setBounds(600,700,130,30);
		this.btn6.setForeground(Color.blue);
		this.optionPanel.add(btn6);
		this.btn6.addActionListener(optionListener);
		
		this.optionLabel = new JLabel();
		this.optionLabel.setBounds(135,-40,1366,768);
		this.optionLabel.setIcon(new ImageIcon("alleverwing.png"));
		this.optionPanel.add(optionLabel);

		this.optionLabel = new JLabel();
		this.optionLabel.setBounds(360,0,800,200);
		this.optionLabel.setIcon(new ImageIcon("icon.png"));
		this.optionPanel.add(optionLabel);
		
		this.add(optionPanel);
	}
	
	private class Option implements ActionListener{
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==btn3){
			dispose();
			Game game = new Game();
		}
		if(e.getSource()==btn4){
			dispose();
			WingLeaderBoard tempLead = new WingLeaderBoard(); 
		
		}
		if(e.getSource()==btn5){
			WingHelp whelp = new WingHelp();
			dispose();
		}
		if(e.getSource()==btn6){
			System.exit(0);
		}
	    } 
	}
}