package BlockChainSim;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TestDrvier {
	

	
	public static void main(String[] args) {
		Scanner cli = new Scanner(System.in);
		
		//이 리스트를 통해 List<Wallet>을 초기화 할것입니다.
		final int initCoin = 100;
		String[] userName = {"a", 
		                          "b",
		                          "c",
		                          "d",
		                          "miner"}; //miner는 채굴자의 이름. 
		
		
		//Make Command Scheme, 이걸 기준으로 Exception을 생성하면 편할것입니다.
		String[] commands = {"send", "create_transaction", "block", "wallet", "create_fakeblock", "status", "flush"};
		int[] eachCommandsParameterNum = {4, 1, 1, 1, 0, 0, 0};
		ArrayList<CommandScheme> commandScheme = new ArrayList<CommandScheme>();
		for(int i=0; i< commands.length; i++) {
			commandScheme.add(new CommandScheme(commands[i], eachCommandsParameterNum[i]));
		}
		

		
		User dijkstra; //User에는 Wallet과 Ledger가 들어갈것
		Miner euler;  //Miner에도 Wallet과 Ledger가 들어갈것.
		
		Ledger blockchain; //User Node와 Miner Node가 공유할 장부 
		ArrayList<Wallet> wallet = new ArrayList<Wallet>();//User Node와 Miner Node가 공유할 지갑
		
		//Ledger를 초기화

		//Wallet List를 초기화

		
		//Ledger와 Wallet 리스트를 초기화하고, 이것을 통해 user node와 miner node를 초기화 하는 코드가 여기 들어간다. 
		
		String cmd = "";
		String[] cmdSplited;
		int commandIdx = -1;
		while(true){
			try {
				System.out.print(">");
				System.out.flush();
				cmd = cli.nextLine();
				cmdSplited = cmd.split(" ");
				//커맨드의 유효성을 확인한다. 
				if(commandIdx == 0) {
				//send 명령어의 검증및 실행
				}
				else if(commandIdx == 1) {
				//create_transaction 명령어의 검증 및 실행

				}
				else if(commandIdx == 2) {
				//block 명령어의 검증 및 실행
				}
				else if(commandIdx == 3) {
				//wallet 명령어의 검증 및 실행
				}
				else if(commandIdx == 4) {
				//create_fakeblock 명령어의 검증 및 실행
				}
				else if(commandIdx == 5) {
				//status 명령어의 실행
				}
				else if(commandIdx == 6) {
				//flush 명령어의 실행
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}


				
		
		}
			
	}
}
