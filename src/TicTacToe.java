/* Pro B_S1_2016, Made by Shixun Liu, ID:766799*/
import java.util.Scanner;

public class TicTacToe{
	
	/*TicTacToe contains one PlayerManager, one GamePlayer, one Interpreter, one keyboard which is 
	 *responsible for handing the game operation
	 */
	private PlayerManager playerMgr;
	private Interpreter interp;
	private GameManager gameMgr;
	public static Scanner keyBoard;
	
	/*When an object of TicTacToe is created, it will create the following objects*/
	TicTacToe(){
		playerMgr=new PlayerManager();
		interp=new Interpreter();
		gameMgr=new GameManager();
		keyBoard=new Scanner(System.in);
	}
	
	/*Run different function based on the interput commands*/
	public void chooseFunction(){
		
		String commands=keyBoard.nextLine();
		
		switch(interp.getFunctionName(commands)){
			case "addplayer":
				playerMgr.addPlayer(interp.getParameter(commands));
				break;
			case "removeplayer":
				if(interp.getParameter(commands)==null){
					playerMgr.removePlayer();
				}else{
					playerMgr.removePlayer(interp.getParameter(commands));
				}
				break;
			case "editplayer":
				playerMgr.editPlayer(interp.getParameter(commands));
				break;
			case "resetstats":
				if(interp.getParameter(commands)==null){
					playerMgr.resetStats();
				}else{
					playerMgr.resetStats(interp.getParameter(commands));
				}
				break;
			case "displayplayer":
				if(interp.getParameter(commands)==null){
					playerMgr.displayPlayer();
				}else{
					playerMgr.displayPlayer(interp.getParameter(commands));
				}
				break;
			case "rankings":
				playerMgr.displayRanking();
				break;
			case "playgame":
				playerMgr.assignPlayerToGM(interp.getParameter(commands),gameMgr);
				gameMgr.playGame();
				break;
			case "exit":
				System.out.println();
				System.exit(0);
			default:
				break;
		}
	}
	
	/*Running method TicTacToe game system*/
	public void run(){		
		System.out.println("Welcome to Tic Tac Toe!");
		do{
			System.out.printf("\n>");
			chooseFunction();
		}while(true);		
	}
	
	/*Start running TicTacToe game system*/
	public static void main(String[] args){
		TicTacToe gameSystem = new TicTacToe();
		gameSystem.run();		
	}
}

