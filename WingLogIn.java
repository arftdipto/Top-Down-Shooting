import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class WingLogIn extends JFrame{
	WingLogIn tempLogIn = this;
	JButton btn1,btn2,btn3;
	JLabel logInLabel1,logInLabel2,logInLabel3,logInLabel4,logInLabel5;
	JTextField logInText;
	JPasswordField logInPass;
	JPanel logInPanel;
	JOptionPane popup;
	LogIn logInListener = new LogIn();
	UIManager um;
	
	public WingLogIn(){
		this.setIconImage(new ImageIcon("frameicon.png").getImage());
		this.setTitle("EVERWING");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1366,768);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.logInPanel = new JPanel();
		addComponent();
		this.setVisible(true);
	}
	
	public void addComponent(){
		this.logInPanel.setLayout(null);
		this.logInPanel.setBackground(new Color(120,21,45));
		
		this.logInLabel1 = new JLabel("USER ID");
		this.logInLabel1.setForeground(Color.yellow);
		this.logInLabel1.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.logInLabel1.setBounds(530,550,80,100);
		this.logInPanel.add(logInLabel1);
		
		this.logInText = new JTextField();
		this.logInText.setBounds(620,585,130,30);
		this.logInPanel.add(logInText);
		
		this.logInLabel2 = new JLabel("PASSWORD");
		this.logInLabel2.setForeground(Color.yellow);
		this.logInLabel2.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.logInLabel2.setBounds(505,580,130,100);
		this.logInPanel.add(logInLabel2);
		
		this.logInPass = new JPasswordField();
		this.logInPass.setBounds(620,620,130,30);
		this.logInPanel.add(logInPass);
		
		this.btn1 = new JButton("SUBMIT");
		this.btn1.setBounds(640,655,80,30);
		this.btn1.setForeground(Color.blue);
		this.logInPanel.add(btn1);
		this.btn1.addActionListener(logInListener);
		
		this.logInLabel3 = new JLabel("New to Everwing? Welcome Fighter");
		this.logInLabel3.setForeground(Color.yellow);
		this.logInLabel3.setFont(new Font("Courier", Font.BOLD, 16));
		this.logInLabel3.setBounds(430,655,320,100);
		this.logInPanel.add(logInLabel3);
		
		this.btn2 = new JButton("JOIN NOW");
		this.btn2.setBounds(760,690,120,30);
		this.btn2.setForeground(Color.blue);
		this.logInPanel.add(btn2);
		this.btn2.addActionListener(logInListener); 
		
		this.btn3 = new JButton("EXIT");
		this.btn3.setBounds(1200,690,120,30);
		this.btn3.setForeground(Color.blue);
		this.logInPanel.add(btn3);
		this.btn3.addActionListener(logInListener);
		
		this.popup = new JOptionPane();
		this.um = new UIManager();
		this.um.put("OptionPane.background",Color.orange);
		this.um.put("Panel.background",Color.orange);
		this.um.put("Button.background",Color.white);
		
		this.logInLabel4 = new JLabel();
		this.logInLabel4.setBounds(135,-40,1366,768);
		this.logInLabel4.setIcon(new ImageIcon("alleverwing.png"));
		this.logInPanel.add(logInLabel4);

		this.logInLabel3 = new JLabel();
		this.logInLabel3.setBounds(360,0,800,200);
		this.logInLabel3.setIcon(new ImageIcon("icon.png"));
		this.logInPanel.add(logInLabel3);
		
		this.add(logInPanel);
	}
	
	private class LogIn implements ActionListener{
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==btn1){
			String ID = logInText.getText();
	     	String pass =String.valueOf(logInPass.getPassword());
			boolean access;
			WingDatabase d1 = new WingDatabase();
			access = d1.approveAccess(ID,pass,tempLogIn);
			if(access==true){
				dispose();
				WingGamePlayOption optn = new WingGamePlayOption();
			}
		}
		if(e.getSource()==btn2){
			dispose();
			WingSignUp wingsgn = new WingSignUp();
		}
		if(e.getSource()==btn3){
			System.exit(0);
		}

	} 
	}
	
	public void showMessage5(){
		popup.showMessageDialog(this,"Incorrect USER ID or Password.Try Again"); 
	}
	
	public void showMessage6(){
		popup.showMessageDialog(this,"Can't Connect to The Server"); 
	}
	
}