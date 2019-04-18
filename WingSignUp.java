import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class WingSignUp extends JFrame{
	
	JButton btn7,btn8;
	JRadioButton rb1,rb2;
	JLabel signupLabel1,signupLabel2,signuppassLabel,signupLabel4,signupLabel5,signupCombo,signupBackgroundLabel1,signupBackgroundLabel2,radioLabel;
	JTextField signupText1,signupText2;
	JPasswordField signupPass;
	JPanel signupPanel;
	JOptionPane signuppopup;
	JComboBox locationCombo,birthYearCombo;
	SignUpListener sgnListener = new SignUpListener();
	UIManager um;
	WingSignUp frame = this;
	
	public WingSignUp(){
		this.setIconImage(new ImageIcon("frameicon.png").getImage());
		this.setTitle("EVERWING");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1366,768);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.signupPanel = new JPanel();
		addComponent();
		this.setVisible(true);
	}
	
	public void addComponent(){
		this.signupPanel.setLayout(null);
		this.signupPanel.setBackground(new Color(120,21,45));
		
		this.signupLabel1 = new JLabel("NAME");
		this.signupLabel1.setForeground(Color.yellow);
		this.signupLabel1.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.signupLabel1.setBounds(600,200,180,100);
		this.signupPanel.add(signupLabel1);
		
		this.signupText1 = new JTextField();
		this.signupText1.setBounds(680,235,200,30);
		this.signupPanel.add(signupText1);
		
		this.signupLabel2 = new JLabel("USER ID*");
		this.signupLabel2.setForeground(Color.yellow);
		this.signupLabel2.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.signupLabel2.setBounds(580,245,180,100);
		this.signupPanel.add(signupLabel2);
		
		this.signupLabel5 = new JLabel("*must be unique.eg. tawhid420 arftdipt34 are more secured");
		this.signupLabel5.setForeground(Color.orange);
		this.signupLabel5.setFont(new Font("Courier", Font.ITALIC, 15));
		this.signupLabel5.setBounds(730,265,570,100);
		this.signupPanel.add(signupLabel5);
		
		this.signupText2 = new JTextField();
		this.signupText2.setBounds(680,280,200,30);
		this.signupPanel.add(signupText2);

		this.signuppassLabel = new JLabel("PASSWORD");
		this.signuppassLabel.setForeground(Color.yellow);
		this.signuppassLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.signuppassLabel.setBounds(560,290,130,100);
		this.signupPanel.add(signuppassLabel);
		
		this.signupPass = new JPasswordField();
		this.signupPass.setBounds(680,325,200,30);
		this.signupPanel.add(signupPass);
		
		this.signupLabel4 = new JLabel("YEAR OF BIRTH");
		this.signupLabel4.setForeground(Color.yellow);
		this.signupLabel4.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.signupLabel4.setBounds(525,335,180,100);
		this.signupPanel.add(signupLabel4);
		
		int a = 2001;
		String year[] = new String[100];
		for(int i = 0; i < 100; i++ ){
			a--;
			year[i] = String.valueOf(a); 
		}
		this.birthYearCombo = new JComboBox(year);
		this.birthYearCombo.setBounds(680,370,200,30);
		this.signupPanel.add(birthYearCombo);
		
		this.radioLabel = new JLabel("GENDER");
		this.radioLabel.setForeground(Color.yellow);
		this.radioLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.radioLabel.setBounds(575,385,180,100);
		this.signupPanel.add(radioLabel);
		
		this.rb1 = new JRadioButton("Male");
		this.rb1.setBounds(680,420,90,30);
		this.rb2 = new JRadioButton("Female");
		this.rb2.setBounds(780,420,100,30);
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(rb1);
		bGroup.add(rb2);
		this.signupPanel.add(rb1);
		this.signupPanel.add(rb2);
		
		
		this.signupCombo = new JLabel("COUNTRY");
		this.signupCombo.setForeground(Color.yellow);
		this.signupCombo.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.signupCombo.setBounds(570,430,180,100);
		this.signupPanel.add(signupCombo);
		
		String arr[]={"Bangladesh","Canada","Japan","India","England","Pakistan","Srilanka","USA","UK","Brazil"};
		this.locationCombo = new JComboBox(arr);
		this.locationCombo.setBounds(680,465,200,30);
		this.signupPanel.add(locationCombo);
	
		this.btn7 = new JButton("SIGN UP");
		this.btn7.setBounds(720,530,80,30);
		this.btn7.setForeground(Color.blue);
		this.signupPanel.add(btn7);
		this.btn7.addActionListener(sgnListener);
		
		this.btn8 = new JButton("BACK");
		this.btn8.setBounds(1200,690,120,30);
		this.btn8.setForeground(Color.blue);
		this.signupPanel.add(btn8);
		this.btn8.addActionListener(sgnListener); 
		
		this.signuppopup = new JOptionPane();
		this.um = new UIManager();
		this.um.put("OptionPane.background",Color.orange);
		this.um.put("Panel.background",Color.orange);
		this.um.put("Button.background",Color.white);
		
		this.signupBackgroundLabel1 = new JLabel();
		this.signupBackgroundLabel1.setBounds(30,50,1366,768);
		this.signupBackgroundLabel1.setIcon(new ImageIcon("signup.png"));
		this.signupPanel.add(signupBackgroundLabel1);

		this.signupBackgroundLabel2 = new JLabel();
		this.signupBackgroundLabel2.setBounds(360,0,800,200);
		this.signupBackgroundLabel2.setIcon(new ImageIcon("icon.png"));
		this.signupPanel.add(signupBackgroundLabel2);
		
		this.add(signupPanel);
	}
	
	private class SignUpListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==btn7){
				String tempName,tempuID,tempPass,tempBirthYear,tempLocation,tempGender="-",temp="";
				int tempintYear;
				boolean flag,btnflag = false;
				tempName      = signupText1.getText();
				tempuID       = signupText2.getText();
				tempPass      = String.valueOf(signupPass.getPassword());
				tempBirthYear = (String)birthYearCombo.getItemAt(birthYearCombo.getSelectedIndex());
				tempLocation  = (String)locationCombo.getItemAt(locationCombo.getSelectedIndex());
				
				if(rb1.isSelected()){
					btnflag = true;
					tempGender = "Male";
				}
				if(rb2.isSelected()){
					btnflag = true;
					tempGender = "FeMale";
				}
				
				if(tempName.equals(temp) || tempuID.equals(temp) || tempPass.equals(temp) || tempBirthYear.equals(temp) || tempLocation.equals(temp) || btnflag==false){
					showMessage1();
				}
				else{
				    tempintYear   = Integer.valueOf(tempBirthYear);
					WingDatabase wbase = new WingDatabase();
				    flag = wbase.checkValidity(tempuID);
				    if(flag==false){
						WingUser newWing = new WingUser(frame,tempName,tempuID,tempPass,tempintYear,tempGender,tempLocation);						
					}
				    else{
						showMessage2();
				    }
				}
			}
			
			if(e.getSource()==btn8){
				dispose();
				WingLogIn winglog2 = new WingLogIn();
			}
		}
	}
	
	public void showMessage1(){
		signuppopup.showMessageDialog(this,"Please Fill Up All The Fields");
	}
	
    public void showMessage2(){
		signuppopup.showMessageDialog(this,"USER ID already exists! Try another");
	}
	
	public void showMessage3(){
		signuppopup.showMessageDialog(this,"Successfully Done! Log in to Continue");
	}
	
	public void showMessage4(){
		signuppopup.showMessageDialog(this,"Can't connect to the server! Try Later");
	}
}