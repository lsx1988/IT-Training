public class GameManager {
	
/*private properties, especially contains to players who will attend the game*/
	private char[][] grid;
	private char currentSymbol;
	private Player playerO;
	private Player playerX;
	
/*when an object is created, the following instances are initialized*/	
	GameManager(){
		playerO=null;
		playerX=null;
		currentSymbol='O';
		grid=new char[3][3];
	}

/*Set method to assign a player as playerO*/
	public void setPlayerO(Player playerO){
		this.playerO=playerO;
	}
	
	public void setPlayerX(Player playerX){
		this.playerX=playerX;
	}

/*Start a game operation*/
	public void playGame(){
		
		//Flag used for checking the input validation
		boolean isInputValid;
		
		//If the two assigned players are not available, sent an error message
		if(playerO==null||playerX==null){
			System.out.println("Player does not exist.");
			return;
		}
			
		initializeGrid();
		
		currentSymbol='O';
			
		//Print out the initialized grid
		printGrid();
			
			
		//Repeat below code for placing symbol and checking winner/draw
		do{			
			
			//Check player O input validation
			do{
				System.out.println(playerO.getGivenName()+"'s move:");
				isInputValid=putSymbolInGrid(TicTacToe.keyBoard.nextInt(),TicTacToe.keyBoard.nextInt());
			}while(isInputValid==false);
			
			//print out the current grid
			printGrid();
			
			//Is there a winner or draw?
			if(getGameState()==true){
				TicTacToe.keyBoard.nextLine();
				break;
			}
			
			//If no winner or draw, switch to next Player X
			switchToNextPlayer();
			
			//Check player X input validation
			do{
				System.out.println(playerX.getGivenName()+"'s move:");
				isInputValid=putSymbolInGrid(TicTacToe.keyBoard.nextInt(),TicTacToe.keyBoard.nextInt());
			}while(isInputValid==false);
			
			//print out the current grid
			printGrid();
			
			//Is there a winner or draw?
			if(getGameState()==true){
				TicTacToe.keyBoard.nextLine();
				break;
			}
				
			//If no winner or draw, change to next Player O
			switchToNextPlayer();
				
		}while(getGameState()==false);
		
		//When game is over, reset the player O and X as null
		playerO=null;
		playerX=null;
	}
		
	//Set the grid with blank elements
	private void initializeGrid(){		
		for(int row=0;row<grid.length;row++){
			for(int column=0;column<grid[row].length;column++){
				grid[row][column]=' ';				
			}
		}		
	}
		
	//Put currentSymbol in grid specified by player's move data
	private boolean putSymbolInGrid(int row,int column){
		
		//if input exceeds the range, sent an error message
		if(row<0||row>2||column<0||column>2){
			System.out.println("Invalid move. You must place at a cell within {0,1,2} {0,1,2}.");
			return false;
		
		//if input equals to a occupied space, sent an error message
		}else if(grid[row][column]!=' '){
				System.out.println("Invalid move. The cell has been occupied.");
				return false;
				
		//No error, put the symbol		
		}else{
			grid[row][column]=currentSymbol;
			return true;
		}
	}
		
	//Print out current grid with "|" and "-" symbol
	private void printGrid(){		
		for(int row=0;row<grid.length;row++){
			for(int column=0;column<grid[row].length;column++){
				if(column!=2){
					System.out.print(grid[row][column]+"|");
				}else{
					System.out.print(grid[row][column]);
				}			
			}
			System.out.println();
			if(row!=2){
				System.out.println("-----");
			}	
		}		
	}
		
	//Change current player to next player
	private void switchToNextPlayer(){		
		if(currentSymbol=='O'){
			currentSymbol='X';
		}else{
			currentSymbol='O';
		}
	}
		
	//Get current player name by currentSymbol
	private Player currentPlayer(){		
		if(currentSymbol=='O'){
			return playerO;
		}else{
			return playerX;
		}
	}	
		
	// Check winner in combination of row/column/diagonal/draw models
	private boolean getGameState(){
		
		if(checkInRow()||checkInColumn()||checkInDiagonal()){
			
			System.out.println("Game over. "+currentPlayer().getGivenName()+" won!");
			
			//when there is a winner, update the player statics
			playerO.setNumOfGamesPlayed();
			playerX.setNumOfGamesPlayed();
			currentPlayer().setNumOfGamesWon();
			currentPlayer().setWinRate();
			currentPlayer().setDrawRate();
			return true;
			
		}else if(checkInDraw()==true){
			
			System.out.println("Game over. It was a draw");
			
			//when there is a draw, update the player statics
			playerO.setNumOfGamesPlayed();
			playerX.setNumOfGamesPlayed();
			currentPlayer().setNumOfGamesDrawn();
			currentPlayer().setDrawRate();
			currentPlayer().setWinRate();
			return true;
		}
		return false;
	}
		
	//Loop through rows to check winner
	private boolean checkInRow(){		
		for(int row=0;row<=2;row++){
			if(grid[row][0]!=' '&&grid[row][0]==grid[row][1]&&grid[row][1]==grid[row][2]){
				return true;
			}
		}
		return false;
	}
		
	//Loop through columns to check winner
	private boolean checkInColumn(){		
		for(int col=0;col<=2;col++){
			if(grid[0][col]!=' '&&grid[0][col]==grid[1][col]&&grid[1][col]==grid[2][col]){
				return true;
			}
		}
		return false;
	}
		
	//Loop through two diagonals to check winner
	private boolean checkInDiagonal(){			
		if(grid[0][0]!=' '&&grid[0][0]==grid[1][1]&&grid[1][1]==grid[2][2]){
			return true;
		}else if(grid[0][2]!=' '&&grid[0][2]==grid[1][1]&&grid[1][1]==grid[2][0]){
			return true;
		}
		return false;
	}
			
	//Loop though all cells to check blank. If no blank, means draw
	private boolean checkInDraw(){
		boolean draw=true;
				
		for(int row=0;row<=2;row++){
			for(int col=0;col<=2;col++){
				if(grid[row][col]==' '){
					draw=false;
				}
			}
		}
		return draw;
	}
}
