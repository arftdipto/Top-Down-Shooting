public class WingUser{
	public   String   userName;
	private  String   userID;
	private  String   userPassword;
	public   int      userHighScore=0;
	public   int      userBirthYear;
	public   String   userLocation;
	public   String   userGender;
	java.util.Date    joindate;
	WingSignUp        wingFrame;
	
	public WingUser(){}
	
	public WingUser(WingSignUp aFrame,String uname,String uID,String uPass,int uBirthYear,String uGender,String uCity){
		this.wingFrame     = aFrame;
		this.userName      = uname;
		this.userID        =   uID;
		this.userPassword  = uPass;
		this.userBirthYear = uBirthYear;
		this.userGender    = uGender;
		this.userLocation  = uCity;
	    this.joindate      = new java.util.Date();
		WingDatabase tempDbase = new WingDatabase();
		tempDbase.updatewingDatabase(this,aFrame);
	}
	
	public WingUser(String uName,int uScore,String uCountry){
		this.userName = uName;
		this.userHighScore = uScore;
		this.userLocation = uCountry;
	}

    public void setuserID(String a){
		this.userID = a;
	}
	public void setuserPassword(String a){
		this.userPassword = a;
	}
	public String getuserID(){
		return this.userID;
	}
	
	public String getuserName(){
		return this.userName;
	}
	
	public String getuserPassword(){
		return this.userPassword;
	}
	public int getuserScore(){
		return this.userHighScore;
	}
	public String getuserLocation(){
		return this.userLocation;
	}
}