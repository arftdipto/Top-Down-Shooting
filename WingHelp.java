import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class WingHelp extends JFrame{
	JButton btn9;
	JLabel helpLabel1,helpLabel2;
	JPanel helpPanel;
	HelpListener helpList = new HelpListener();
	
	public WingHelp(){
		this.setIconImage(new ImageIcon("frameicon.png").getImage());
		this.setTitle("EVERWING");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1366,768);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.helpPanel = new JPanel();
		addComponent();
		this.setVisible(true);	
	}
	
	public void addComponent(){
		this.helpPanel.setLayout(null);
		this.helpPanel.setBackground(new Color(120,21,45));
		
		this.btn9 = new JButton("BACK");
		this.btn9.setBounds(1200,690,120,30);
		this.btn9.setForeground(Color.blue);
		this.helpPanel.add(btn9);
		this.btn9.addActionListener(helpList); 

		addInstruction();
		
		this.helpLabel1 = new JLabel();
		this.helpLabel1.setBounds(360,0,800,200);
		this.helpLabel1.setIcon(new ImageIcon("icon.png"));
		this.helpPanel.add(helpLabel1);
		
		this.add(helpPanel);
	}
	
	public void addInstruction(){
		String fileName = "help.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
			int a = 200;
			while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
				this.helpLabel2 = new JLabel(line);
				a = a+30;
				this.helpLabel2.setForeground(Color.yellow);
		        this.helpLabel2.setFont(new Font("Courier", Font.ITALIC, 17));
		        this.helpLabel2.setBounds(170,a,1200,100);
				this.helpPanel.add(helpLabel2);
            }   
            bufferedReader.close();  			
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
		
	}
	
	private class HelpListener implements ActionListener{
		public void actionPerformed(ActionEvent e ){
			if(e.getSource()==btn9){
				WingGamePlayOption winoption = new WingGamePlayOption();
				dispose();
			}
			
		}
	}
}