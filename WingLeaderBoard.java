import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.*;


public class WingLeaderBoard extends JFrame{
	JPanel leaderBoardPanel;
	JTable leaderBoardTable;
	JButton btn10;
	JLabel leadBoardLabel1;
	WingDatabase tempBase;
	static Object[][] databaseInfo;
	static DefaultTableModel dTableModel;
	LeaderBoardListener leadListener = new LeaderBoardListener();
	
	public WingLeaderBoard(){
		this.setIconImage(new ImageIcon("frameicon.png").getImage());
		this.setTitle("EVERWING");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1366,768);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.leaderBoardPanel = new JPanel();
		this.leaderBoardPanel.setLayout(null);
		this.tempBase = new WingDatabase();
		addComponent();
		this.setVisible(true);
	}
	
	public void addComponent(){
		this.leaderBoardPanel.setBackground(new Color(120,21,45));
		
		
		
		this.btn10 = new JButton("BACK");
		this.btn10.setBounds(1200,690,120,30);
		this.btn10.setForeground(Color.blue);
		this.leaderBoardPanel.add(btn10);
		this.btn10.addActionListener(leadListener); 
		
		showLeaderBoard();
		
		this.leadBoardLabel1 = new JLabel();
		this.leadBoardLabel1.setBounds(360,0,800,200);
		this.leadBoardLabel1.setIcon(new ImageIcon("icon.png"));
		this.leaderBoardPanel.add(leadBoardLabel1);
	
		this.add(leaderBoardPanel);
	}
	
	public void showLeaderBoard(){
		        Object[] columns = {"Name", "Score", "Country"};
				dTableModel = new DefaultTableModel(databaseInfo, columns);
				WingDatabase tempScore = new WingDatabase();
				ArrayList<WingUser> list = tempScore.wingUserList();
				Object[] tempRow = new Object[3];
				
				for(int i=0;i<list.size();i++){
					
					tempRow[0]=list.get(i).getuserName();
					tempRow[1]=list.get(i).getuserScore();
					tempRow[2]=list.get(i).getuserLocation();
					
					dTableModel.addRow(tempRow);
				}
				
				JTable table = new JTable(dTableModel);
				table.setRowHeight(table.getRowHeight()+10);
				table.setAutoCreateRowSorter(true);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

				TableColumn col1 = table.getColumnModel().getColumn(0);
				col1.setPreferredWidth(100);
				
				TableColumn col2 = table.getColumnModel().getColumn(1);
				col2.setPreferredWidth(190);
				
				TableColumn col3 = table.getColumnModel().getColumn(2);
				col3.setPreferredWidth(150);
				
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(350,300,470,150);
				add(scrollPane);
			}
	
	private class LeaderBoardListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==btn10){
				dispose();
				WingGamePlayOption tempOption = new WingGamePlayOption();
			}
		}
	}
}