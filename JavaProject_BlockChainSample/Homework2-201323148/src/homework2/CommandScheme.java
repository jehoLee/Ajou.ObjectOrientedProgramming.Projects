package homework2;

public class CommandScheme {

	public String commandName;
	public int parameterNum;
	public int commandIdx;
	public CommandScheme(String name, int num, int idx) {
		this.commandName = name;
		this.parameterNum = num;
		this.commandIdx = idx;
	}
	
	public static void checkCurrentCmd(CommandScheme cmd, String[] cmdSplited) throws IllegalArgumentException{
		if(cmd == null) throw new IllegalArgumentException("존재하지 않는 명령어 입니다.");
		if(cmd.parameterNum != cmdSplited.length - 1) throw new IllegalArgumentException("매개변수의 갯수가 맞지않습니다.");
		
		//param type check
		if(cmd.commandIdx == 0) {
			//send
			try {
		        Float.parseFloat(cmdSplited[3]);
		        Float.parseFloat(cmdSplited[4]);
		    } catch (NumberFormatException e) {
		        throw new IllegalArgumentException("매개변수는 float 타입이어야 합니다.");
		    }
		}
		if (cmd.commandIdx == 1) {
			//create_transaction
			try {
		        Integer.parseInt(cmdSplited[1]);
		    } catch (NumberFormatException e) {
		        throw new IllegalArgumentException("매개변수는 int 타입이어야 합니다.");
		    }
		}
		if (cmd.commandIdx == 2) {
			//block
			try {
		        Integer.parseInt(cmdSplited[1]);
		    } catch (NumberFormatException e) {
		        throw new IllegalArgumentException("매개변수는 int 타입이어야 합니다.");
		    }
		}
		
		
	}
}
