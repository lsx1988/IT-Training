public class Interpreter {

	public String getFunctionName(String command){
		String[] splitCommand=command.split("[ ]+");		
		return splitCommand[0];
	}

	public String[] getParameter(String command){
		String[] splitCommand=command.split("[ ]+");
		if(splitCommand.length==1){
			return null;
		}else{
			return splitCommand[1].split("[,]+");
		}
	}
}
