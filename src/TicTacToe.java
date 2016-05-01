import java.util.Scanner;

public class TicTacToe{
	
	/*TicTacToe contains one PlayerManager, one GamePlayer, one Interpreter, one keyboard which is 
	 *responsible for handing the game operation
	 */
	private PlayerManager pm;
	private Interpreter inp;
	private GameManager gm;
	public static Scanner keyBoard;
	
	/*When an object of TicTacToe is created, it will create the following objects*/
	TicTacToe(){
		pm=new PlayerManager();
		inp=new Interpreter();
		gm=new GameManager();
		keyBoard=new Scanner(System.in);
	}
	
	/*Run different function based on the input commands*/
	public void chooseFunction(){
		
		String commands=keyBoard.nextLine();
		
		switch(inp.getFunctionName(commands)){
			case "addplayer":
				pm.addPlayer(inp.getParameter(commands));
				break;
			case "removeplayer":
				if(inp.getParameter(commands)==null){
					pm.removePlayer();
				}else{
					pm.removePlayer(inp.getParameter(commands));
				}
				break;
			case "editplayer":
				pm.editPlayer(inp.getParameter(commands));
				break;
			case "resetstats":
				if(inp.getParameter(commands)==null){
					pm.resetStats();
				}else{
					pm.resetStats(inp.getParameter(commands));
				}
				break;
			case "displayplayer":
				if(inp.getParameter(commands)==null){
					pm.displayPlayer();
				}else{
					pm.displayPlayer(inp.getParameter(commands));
				}
				break;
			case "rankings":
				pm.displayRanking();
				break;
			case "playgame":
				pm.assignPlayerToGM(inp.getParameter(commands),gm);
				gm.playGame();
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

