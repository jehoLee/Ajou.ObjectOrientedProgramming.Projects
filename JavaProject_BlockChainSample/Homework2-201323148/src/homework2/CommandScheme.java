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
		if(cmd == null) throw new IllegalArgumentException("�������� �ʴ� ��ɾ� �Դϴ�.");
		if(cmd.parameterNum != cmdSplited.length - 1) throw new IllegalArgumentException("�Ű������� ������ �����ʽ��ϴ�.");
		
		//param type check
		if(cmd.commandIdx == 0) {
			//send
			try {
		        Float.parseFloat(cmdSplited[3]);
		        Float.parseFloat(cmdSplited[4]);
		    } catch (NumberFormatException e) {
		        throw new IllegalArgumentException("�Ű������� float Ÿ���̾�� �մϴ�.");
		    }
		}
		if (cmd.commandIdx == 1) {
			//create_transaction
			try {
		        Integer.parseInt(cmdSplited[1]);
		    } catch (NumberFormatException e) {
		        throw new IllegalArgumentException("�Ű������� int Ÿ���̾�� �մϴ�.");
		    }
		}
		if (cmd.commandIdx == 2) {
			//block
			try {
		        Integer.parseInt(cmdSplited[1]);
		    } catch (NumberFormatException e) {
		        throw new IllegalArgumentException("�Ű������� int Ÿ���̾�� �մϴ�.");
		    }
		}
		
		
	}
}
