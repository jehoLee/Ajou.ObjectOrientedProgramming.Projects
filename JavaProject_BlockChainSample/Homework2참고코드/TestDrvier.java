package BlockChainSim;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TestDrvier {
	

	
	public static void main(String[] args) {
		Scanner cli = new Scanner(System.in);
		
		//�� ����Ʈ�� ���� List<Wallet>�� �ʱ�ȭ �Ұ��Դϴ�.
		final int initCoin = 100;
		String[] userName = {"a", 
		                          "b",
		                          "c",
		                          "d",
		                          "miner"}; //miner�� ä������ �̸�. 
		
		
		//Make Command Scheme, �̰� �������� Exception�� �����ϸ� ���Ұ��Դϴ�.
		String[] commands = {"send", "create_transaction", "block", "wallet", "create_fakeblock", "status", "flush"};
		int[] eachCommandsParameterNum = {4, 1, 1, 1, 0, 0, 0};
		ArrayList<CommandScheme> commandScheme = new ArrayList<CommandScheme>();
		for(int i=0; i< commands.length; i++) {
			commandScheme.add(new CommandScheme(commands[i], eachCommandsParameterNum[i]));
		}
		

		
		User dijkstra; //User���� Wallet�� Ledger�� ����
		Miner euler;  //Miner���� Wallet�� Ledger�� ����.
		
		Ledger blockchain; //User Node�� Miner Node�� ������ ��� 
		ArrayList<Wallet> wallet = new ArrayList<Wallet>();//User Node�� Miner Node�� ������ ����
		
		//Ledger�� �ʱ�ȭ

		//Wallet List�� �ʱ�ȭ

		
		//Ledger�� Wallet ����Ʈ�� �ʱ�ȭ�ϰ�, �̰��� ���� user node�� miner node�� �ʱ�ȭ �ϴ� �ڵ尡 ���� ����. 
		
		String cmd = "";
		String[] cmdSplited;
		int commandIdx = -1;
		while(true){
			try {
				System.out.print(">");
				System.out.flush();
				cmd = cli.nextLine();
				cmdSplited = cmd.split(" ");
				//Ŀ�ǵ��� ��ȿ���� Ȯ���Ѵ�. 
				if(commandIdx == 0) {
				//send ��ɾ��� ������ ����
				}
				else if(commandIdx == 1) {
				//create_transaction ��ɾ��� ���� �� ����

				}
				else if(commandIdx == 2) {
				//block ��ɾ��� ���� �� ����
				}
				else if(commandIdx == 3) {
				//wallet ��ɾ��� ���� �� ����
				}
				else if(commandIdx == 4) {
				//create_fakeblock ��ɾ��� ���� �� ����
				}
				else if(commandIdx == 5) {
				//status ��ɾ��� ����
				}
				else if(commandIdx == 6) {
				//flush ��ɾ��� ����
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}


				
		
		}
			
	}
}
