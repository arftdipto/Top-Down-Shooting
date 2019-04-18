import java.sql.*;
import java.util.ArrayList;

public class WingDatabase{
	
	public void updatewingDatabase(WingUser tempWing,WingSignUp tempSign){
		try{
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/everwing","root","");
		    Statement stmt = con.createStatement();
			String sql = "INSERT INTO `everwingdbase`(`Name`, `UserID`, `Password`, `Birth_Year`, `Gender`, `High Score`, `Country`, `Join_Date`) VALUES ('"+tempWing.userName+"','"+tempWing.getuserID()+"','"+tempWing.getuserPassword()+"',"+tempWing.userBirthYear+",'"+tempWing.userGender+"',"+tempWing.userHighScore+",'"+tempWing.userLocation+"','"+tempWing.joindate+"')";
		    int a = stmt.executeUpdate(sql);
			tempSign.showMessage3();
			WingLogIn winglog1 = new WingLogIn();
			tempSign.dispose();
		}catch(Exception e){
			tempSign.showMessage4();
			//System.out.println(e);
		}
	}
	
	public boolean approveAccess(String uID,String uPass,WingLogIn tempLogIn){
		boolean key = false;
		try{
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/everwing","root","");
		    Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT `UserID`, `Password` FROM `everwingdbase` WHERE UserID='"+uID+"';");
		    while(rs.next()){
				if(rs.getString(1).equals(uID) && rs.getString(2).equals(uPass)){
					key = true;
			    }
			    else{
					key = false;
					tempLogIn.showMessage5();
				}
		   }
		con.close();
		
		}catch(Exception e){
			System.out.println(e);
			tempLogIn.showMessage6();
		}
		return key;
	}
	
	public boolean checkValidity(String uID){
		boolean key = false;
		try{
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/everwing","root","");
		    Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT `UserID` FROM `everwingdbase` WHERE UserID='"+uID+"';");
		    while(rs.next()){
				if(rs.getString(1).equals(uID)){
					key = true;
			    }
			    else key = false;
		   }
		con.close();
		
		}catch(Exception e){
			System.out.println(e);
		}	
		return key;
	}
	
	 public ArrayList<WingUser> wingUserList(){
		
		ArrayList<WingUser> wingUserList = new ArrayList<WingUser>();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/everwing","root","");
			Statement stmt = con.createStatement();
			String sql = "select * from everwingdbase";
			ResultSet rs   = stmt.executeQuery(sql);
			
			WingUser wingUser;
			
			while(rs.next())
			{
				wingUser = new WingUser(rs.getString(1), rs.getInt(6), rs.getString(7));
				wingUserList.add(wingUser);
			}
			
			for(int i=0;i<3;i++){
				System.out.println(wingUserList.get(i).getuserName()+" "+wingUserList.get(i).getuserScore());
			}
			
			con.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		return wingUserList;
	} 
}