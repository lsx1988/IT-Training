public class Interpreter {
	
/*Get the function/method name based on input commands*/
	public String getFunctionName(String command){
		
		//split the commands by 'space' character
		String[] splitCommand=command.split("[ ]+");
		
		//the first index is the function string
		return splitCommand[0];
	}

/*Get the parameter as array based on input commands*/
	public String[] getParameter(String command){
		
		//split the commands by 'space' character
		String[] splitCommand=command.split("[ ]+");
		
		//If only one elements, means there is no string after the function string
		if(splitCommand.length==1){
			return null;
			
		//Split the following part by ',' and get the parameter part as array
		}else{
			return splitCommand[1].split("[,]+");
		}
	}
}
