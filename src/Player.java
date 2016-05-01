import java.util.Comparator;

public class Player{
	
/*---------private property of player class--------------------------------------------------*/	
	
	private final String userName;
	private String familyName;
	private String givenName;
	private int numOfGamesPlayed;
	private int numOfGamesWon;
	private int numOfGamesDrawn;
	private double winRate;
	private double drawRate;
	
/*---------Create a player by name, other properties are 0-----------------------------------*/	
	
	Player(String userName, String familyName, String givenName){
		this.userName=userName;
		this.familyName=familyName;
		this.givenName=givenName;
		this.numOfGamesPlayed=0;
		this.numOfGamesWon=0;
		this.numOfGamesDrawn=0;
		this.winRate=0;
		this.drawRate=0;
	}
	
/*---------Get method of each property-------------------------------------------------------*/		
	
	public String getUserName() {
		return userName;
	}

	public String getFamilyName() {
		return familyName;
	}
	
	public String getGivenName() {
		return givenName;
	}
	
	public int getNumOfGamesPlayed() {
		return numOfGamesPlayed;
	}
	
	public int getNumOfGamesWon() {
		return numOfGamesWon;
	}
	
	public int getNumOfGamesDrawn() {
		return numOfGamesDrawn;
	}
	
	public double getWinRate() {
		return winRate;
	}
	
	public double getDrawRate() {
		return drawRate;
	}
	
/*---------Set method of each property-------------------------------------------------------*/	
/*userName is final, so there is no set method for it*/	
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public void setNumOfGamesPlayed() {
		this.numOfGamesPlayed++;
	}

	public void setNumOfGamesWon() {
		this.numOfGamesWon++;
	}
	
	public void setNumOfGamesDrawn() {
		this.numOfGamesDrawn++;
	}

	public void setWinRate() {
		if(this.numOfGamesPlayed==0){
			this.winRate=0;
		}else{
			this.winRate = 100*this.numOfGamesWon/this.numOfGamesPlayed;
		}
	}

	public void setDrawRate() {
		if(this.numOfGamesPlayed==0){
			this.drawRate=0;
		}else{
			this.drawRate = 100*this.numOfGamesDrawn/this.numOfGamesPlayed;
		}
	}
	
/*---------When resetstats method is called, set all below items as 0------------------------*/
	
	public void reset(){
		this.numOfGamesPlayed=0;
		this.numOfGamesWon=0;
		this.numOfGamesDrawn=0;
		this.setWinRate();
		this.setDrawRate();
	}
	
/*---------Two player comparator used for sorting player per self-defined rules----------------*/

	//Sorting the player based the userName
	public static Comparator<Player> userNameComparator=new Comparator<Player>(){
		public int compare(Player p1, Player p2){
			return p1.getUserName().compareTo(p2.getUserName());
		}
	};
	
	//Sorting the player based on winRrate first, then drawRate, finally userName
	
	public static Comparator<Player> multiComparator=new Comparator<Player>(){

		public int compare(Player p1, Player p2){
			if(p1.getWinRate()==p2.getWinRate()){
				if(p1.getDrawRate()==p2.getDrawRate()){
					return p1.getUserName().compareTo(p2.getUserName());
				}else{
					return -Double.compare(p1.getDrawRate(),p2.getDrawRate());
				}
			}else{
				return -Double.compare(p1.getWinRate(),p2.getWinRate());
			}
		}
	};
	
/*---------Override toString method----------------------------------------------------------*/	
	
	public String toString(){
		return userName+","+familyName+","+givenName+","+numOfGamesPlayed+" games,"
				+numOfGamesWon+" wins,"+numOfGamesDrawn+" draws";
	}
}
