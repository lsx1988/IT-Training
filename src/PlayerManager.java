import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class PlayerManager{
	
	/*The limitation of number of player is 100, the limitation of ranking list is 10*/
	private final int PLAYER_LIMIT=100;
	private final int RANKING_LIMIT=10;
	
	/*Announce a ArrayList type to store multiple player objects*/
	private ArrayList<Player> playerList;
	
	/*Create an object of type ArrayList*/
	PlayerManager(){
		this.playerList=new ArrayList<Player>();
	}

/*--------Add player function------------------*/
	public void addPlayer(String[] parameter){
		
		//Parameter[] which is interpreted from the commands by Interpreter object
		String userName=parameter[0];
		String familyName=parameter[1];
		String givenName=parameter[2];
		
		//No player, create new player and add player directly
		if(playerList.isEmpty()){			
			Player player=new Player(userName, familyName, givenName);
			playerList.add(player);
			return;
		}
		
		//If the number of players exceeds 100, return an error
		if(playerList.size()>PLAYER_LIMIT){
			System.out.println("Error: The num of players can not be over 100");
			return;	
		}
		
		/*If there are players and the total number is less than 100 limitation
		 *check if the username of new player is already exited. If so, show up 
		 *a notification, if not, create and add new player
		 */		
		for(Player pl:playerList){
			if(pl.getUserName().equals(userName)){
				System.out.println("The username has been used already.");
				return;
			}
		}
		
		Player player=new Player(userName, familyName, givenName);
		playerList.add(player);				
	}
	
/*--------Remove specific player function with username-----------------*/
	public void removePlayer(String[] parameter){
		
		//No players, create and error message
		if(playerList.isEmpty()){
			System.out.println("The player does not exist.");
			return;
		}
		
		//Search the player based on user name, if the player exists, remove it
		//if not, create an error message
		String useName=parameter[0];
		
		for(Player pl:playerList){
			if(pl.getUserName().equals(useName)){
				playerList.remove(pl);
				return;
			}
		}
		System.out.println("The player does not exist.");	
	}
/*-------- Remove all player function ----------------------------------*/	
	public void removePlayer(){
		System.out.println("Are you sure you want to remove all players? (y/n)");
		if(TicTacToe.keyBoard.nextLine().equals("y")){
			playerList.clear();		
		}else{
			return;
		}
	}
/*-------- Edit player function ----------------------------------------*/		
	public void editPlayer(String[] parameter){
		
		//Parameter[] which is interpreted from the commands by Interpreter object
		String userName=parameter[0];
		String familyName=parameter[1];
		String givenName=parameter[2];
				
		//No players, create and error message
		if(playerList.isEmpty()){			
			System.out.println("The player does not exist.");
			return;
		}
		
		//Search the player based on username, if the player exists, re-edit it
		//if not, create an error message
		for(Player pl:playerList){
			if(pl.getUserName().equals(userName)){
				pl.setFamilyName(familyName);
				pl.setGivenName(givenName);
				return;
			}
		}
		System.out.println("The player does not exist.");			
	}
/*-------- Resetstats specific player function with username-------------*/		
	public void resetStats(String[] parameter){
		
		//No players, create and error message
		if(playerList.isEmpty()){
			System.out.println("The player does not exist.");
			return;
		}
		
		//Search the player based on username, if the player exists, reset it
		//if not, create an error message
		String useName=parameter[0];		
		for(Player pl:playerList){
			if(pl.getUserName().equals(useName)){
				pl.reset();
				return;
			}		
		}
		System.out.println("The player does not exist.");
	}
/*-------- Resetstats all player ----------------------------------------*/		
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
/*-------- Display specific player with usename -------------------------*/		
	public void displayPlayer(String[] parameter){		
		
		//No players, create and error message
		if(playerList.isEmpty()){
			System.out.println("The player does not exist.");
			return;
		}
		
		//Search the player based on username, if the player exists, display it
		//if not, create an error message
		String useName=parameter[0];		
		for(Player pl:playerList){
			if(pl.getUserName().equals(useName)){				
				System.out.println(pl);
				return;
			}
		}		
		System.out.println("The player does not exist.");	
	}
/*-------- Display all players---------------- -------------------------*/		
	public void displayPlayer(){
		Collections.sort(playerList,Player.userNameComparator);
		for(Player pl:playerList){
			System.out.println(pl);
		}
		return;
	}
/*---------Ranking all players or top 10 players -----------------------*/	
	public void displayRanking(){
		
		//Sort the players based on rules
		Collections.sort(playerList,Player.multiComparator);
		
		//Print the title
		System.out.println(" WIN  | DRAW | GAME | USERNAME");
		
		//Loop through the players to print out is statics
		for(int i=0;i<=playerList.size()-1&&i<=RANKING_LIMIT-1;i++){
			int winRate=(int)Math.round(playerList.get(i).getWinRate());
			int drawRate=(int)Math.round(playerList.get(i).getDrawRate());
			int gamesPlayed=playerList.get(i).getNumOfGamesPlayed();
			String userName=playerList.get(i).getUserName();
			System.out.printf(" %3d%% | %3d%% | %2d   | %s",winRate,drawRate,gamesPlayed,userName);
			System.out.println();
		}
	}
/*---------Find out and assign two players to GameManager --------------*/
	public void assignPlayerToGM(String[] parameter, GameManager gameMgr){
		
		String playerOName=parameter[0];
		String playerXName=parameter[1];
		
		for(Player pl: playerList){
			if(pl.getUserName().equals(playerOName)){
				gameMgr.setPlayerO(pl);
			}
			if(pl.getUserName().equals(playerXName)){
				gameMgr.setPlayerX(pl);
			}
		}
	}
}