import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class PlayerManager{
	
	private final int numOfPlayers=100;
	private final int numOfRanking=10;
	
	/*Announce a ArrayList type to store multiple player objects*/
	private ArrayList<Player> playerList;
	
	/*Create an object of type ArrayList*/
	PlayerManager(){
		this.playerList=new ArrayList<Player>();
	}
	
	public ArrayList<Player> getPlayerList(){
		return playerList;
	}
/*-----------------------------------------------------------*/		
	/*Whether add player or not is based on the condition of playerList*/
	public void addPlayer(String[] parameter){
		
		//Parameter[] which is interpreted from the commands by Interpreter object
		String userName=parameter[0];
		String familyName=parameter[1];
		String givenName=parameter[2];
		
		//If there is no player, create new player and add player directly
		if(playerList.isEmpty()){			
			Player player=new Player(userName, familyName, givenName);
			playerList.add(player);
			return;
		}
		
		if(playerList.size()>numOfPlayers){
			return;	
		}
		
		//If there are players and the total number is less than 100 limitation
		//check if the username of new player is already exited. If so, show up 
		//a notification, if not, add new player		
		for(Player pl:playerList){
			if(pl.getUserName().equals(userName)){
				System.out.println("The username has been used already.");
				return;
			}
		}
		
		Player player=new Player(userName, familyName, givenName);
		playerList.add(player);				
	}
/*-----------------------------------------------------------*/		
	public void removePlayer(String[] parameter){
		
		if(playerList.isEmpty()){
			System.out.println("The player does not exist.");
			return;
		}
		
		String useName=parameter[0];		
		for(Player pl:playerList){
			if(pl.getUserName().equals(useName)){
				playerList.remove(pl);
				return;
			}
		}
		System.out.println("The player does not exist.");	
	}
	
	public void removePlayer(){
		System.out.println("Are you sure you want to remove all players? (y/n)");
		if(TicTacToe.keyBoard.nextLine().equals("y")){
				playerList.clear();
		}else{
			return;
		}
	}
/*-----------------------------------------------------------*/		
	public void editPlayer(String[] parameter){
		
		//Parameter[] which is interpreted from the commands by Interpreter object
		String userName=parameter[0];
		String familyName=parameter[1];
		String givenName=parameter[2];
				
		//If there is no player
		if(playerList.isEmpty()){			
			System.out.println("The player does not exist.");
			return;
		}
		
		for(Player pl:playerList){
			if(pl.getUserName().equals(userName)){
				pl.setFamilyName(familyName);
				pl.setGivenName(givenName);
				return;
			}
		}
		System.out.println("The player does not exist.");			
	}
/*-----------------------------------------------------------*/		
	public void resetStats(String[] parameter){
	
		if(playerList.isEmpty()){
			System.out.println("The player does not exist.");
			return;
		}
	
		String useName=parameter[0];		
		for(Player pl:playerList){
			if(pl.getUserName().equals(useName)){
				pl.reset();
				return;
			}		
		}
		System.out.println("The player does not exist.");
	}
	
	public void resetStats(){
		System.out.println("Are you sure you want to reset all player statistics? (y/n)");
		if(TicTacToe.keyBoard.nextLine().equals("y")){
			for(Player pl:playerList){			
				pl.reset();
			}			
		}else{
			return;
		}
	}
/*-----------------------------------------------------------*/		
	public void displayPlayer(String[] parameter){		
		
		if(playerList.isEmpty()){
			System.out.println("The player does not exist.");
			return;
		}
	
		String useName=parameter[0];		
		for(Player pl:playerList){
			if(pl.getUserName().equals(useName)){				
				System.out.println(pl);
				return;
			}
		}		
		System.out.println("The player does not exist.");	
	}
	
	public void displayPlayer(){
		Collections.sort(playerList,Player.userNameComparator);
		for(Player pl:playerList){
			System.out.println(pl);
		}
		return;
	}
/*-----------------------------------------------------------*/	
	public void displayRanking(){
		Collections.sort(playerList,Player.multiComparator);
		System.out.println(" WIN  | DRAW | GAME | USERNAME");
		for(int i=0;i<=playerList.size()-1&&i<=numOfRanking-1;i++){
			int winRate=(int)Math.round(playerList.get(i).getWinRate());
			int drawRate=(int)Math.round(playerList.get(i).getDrawRate());
			int gamesPlayed=playerList.get(i).getNumOfGamesPlayed();
			String userName=playerList.get(i).getUserName();
			System.out.printf(" %3d%% | %3d%% | %2d   | %s",winRate,drawRate,gamesPlayed,userName);
			System.out.println();
		}
	}
/*-----------------------------------------------------------*/
	public int checkPlayer(String[] parameter){
		String playerOName=parameter[0];
		String playerXName=parameter[1];
		int playerExist=0;
		for(Player pl: playerList){
			if(pl.getUserName()==playerOName||pl.getUserName()==playerXName){
				playerExist++;
			}
		}
		if(playerExist==2){
			return playerExist;
		}else{
			System.out.println("Player does not exist.");
			return -1;
		}
	}
}