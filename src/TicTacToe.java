import java.util.Scanner;


public class TicTacToe{
	
	PlayerManager PM;
	Interpreter Inp;
	public static Scanner keyBoard;
	
	TicTacToe(){
		PM=new PlayerManager();
		Inp=new Interpreter();
		keyBoard=new Scanner(System.in);
	}
	
	public void chooseFunction(){
		
		String commands=keyBoard.nextLine();
		
		switch(Inp.getFunctionName(commands)){
			case "addplayer":
				PM.addPlayer(Inp.getParameter(commands));
				break;
			case "removeplayer":
				if(Inp.getParameter(commands)==null){
					PM.removePlayer();
				}else{
					PM.removePlayer(Inp.getParameter(commands));
				}
				break;
			case "editplayer":
				PM.editPlayer(Inp.getParameter(commands));
				break;
			case "resetstats":
				if(Inp.getParameter(commands)==null){
					PM.resetStats();
				}else{
					PM.resetStats(Inp.getParameter(commands));
				}
				break;
			case "displayplayer":
				if(Inp.getParameter(commands)==null){
					PM.displayPlayer();
				}else{
					PM.displayPlayer(Inp.getParameter(commands));
				}
				break;
			case "ranking":
				PM.displayRanking();
				break;
			case "exit":
				System.out.println();
				System.exit(0);
		}
	}

	public void run(){	
		
		System.out.println("Welcome to Tic Tac Toe!");
		do{
			System.out.printf("\n>");
			chooseFunction();
		}while(true);		
	}

	public static void main(String[] args){
		TicTacToe gameSystem = new TicTacToe();
		gameSystem.run();		
	}
}

