package homework2;

import java.util.ArrayList;
import java.util.Scanner;

public class TestDriver {
	public static void main(String[] args) {
		Scanner cli = new Scanner(System.in);
		
		final int initCoin = 100;
		String[] userName = {"a",
		                          "b",
		                          "c",
		                          "d",
		                          "miner"};
		
		String[] commands = {"send", "create_transaction", "block", "wallet", "create_fakeblock", "status", "flush"};
		int[] eachCommandsParameterNum = {4, 1, 1, 1, 0, 0, 0};
		ArrayList<CommandScheme> commandScheme = new ArrayList<CommandScheme>();
		for(int i=0; i< commands.length; i++) {
			commandScheme.add(new CommandScheme(commands[i], eachCommandsParameterNum[i], i));
		}
		
		User dijkstra;
		Miner euler;
		
		Ledger blockchain; //User Node와 Miner Node가 공유할 장부
		ArrayList<Wallet> wallet = new ArrayList<Wallet>();//User Node와 Miner Node가 공유할 지갑
		
		//Ledger 초기화
		blockchain = new Ledger();
		
		//Wallet List 초기화
		for(String name : userName) {
			wallet.add(new Wallet(name, initCoin));
		}
		
		dijkstra = new User(blockchain, wallet);
		euler = new Miner(blockchain, wallet, userName[4]);
		
		String cmd = "";
		String[] cmdSplited;
		int commandIdx = -1;
		CommandScheme currentCmd;
		
		while(true){
			currentCmd = null;
			try {
				System.out.print(">");
				System.out.flush();
				cmd = cli.nextLine();
				cmdSplited = cmd.split(" ");
				for(CommandScheme command : commandScheme) {
					if(command.commandName.equals(cmdSplited[0])) {
						commandIdx = command.commandIdx;
						currentCmd = command;
					}
				}
				
				//현재 입력된 Command의 유효성확인
				CommandScheme.checkCurrentCmd(currentCmd, cmdSplited);
				
				if(commandIdx == 0) {
				//send 명령어의 검증
					float coin = Float.parseFloat(cmdSplited[3]);
					float fee = Float.parseFloat(cmdSplited[4]);
					Wallet sender = dijkstra.findWallet(cmdSplited[1]);
					Wallet receiver = dijkstra.findWallet(cmdSplited[2]);
					
					Wallet.checkWallet(cmdSplited, sender, receiver, coin, fee);
				//send 명령어의 실행	
					dijkstra.sendTransaction(sender, receiver, coin, fee);
					euler.mine(5);
					if(blockchain.isNewBlockCreated())
						dijkstra.validateNewBlock();
				
				}
				else if(commandIdx == 1) {
				//create_transaction 명령어의 검증 
					int n = Integer.parseInt(cmdSplited[1]); //for loop
					if(n > 100) throw new IllegalArgumentException("너무 많은 트랜잭션 요청");
				//create_transaction 명령어의 실행 	
					Wallet sender, receiver;
					float coin, fee, totalCost;
					int ranNum;
					
					for(int i = 0; i<n; i++) {
						ranNum = (int)(Math.random() * (wallet.size()));//0 ~ (wallet size)
						sender = wallet.get(ranNum);
						ranNum = (int)(Math.random() * (wallet.size()));
						receiver = wallet.get(ranNum);
						
						if(sender.getUserCoin() < 3) {
							coin = 0; fee = 0;
						}
						else {
							totalCost = sender.getRandomCoinToSend();
							fee = (float) (totalCost * (0.1));	
							coin = totalCost - fee;
						}
						
						dijkstra.sendTransaction(sender, receiver, coin, fee);
						euler.mine(5);
						if(blockchain.isNewBlockCreated())
							dijkstra.validateNewBlock();
					}
				
				}
				else if(commandIdx == 2) {
				//*block의 Height는 1부터 시작
				//block 명령어의 검증 및 실행
					Block block = blockchain.findBlock((Integer.parseInt(cmdSplited[1])));
					if(block == null) {
						throw new IllegalArgumentException("존재하지 않는 블록 입니다(블록의 Height는 1부터입니다).");
					}
					else {
						System.out.println("Block Found!\n");
						System.out.println(block);
					}
				}
				else if(commandIdx == 3) {
				//wallet 명령어의 검증 및 실행
					Wallet targetWallet = dijkstra.findWallet(cmdSplited[1]);
					if(targetWallet == null)
						throw new IllegalArgumentException("존재하지 않는 유저 입니다!");
					else
						System.out.println(targetWallet);
					
				}
				else if(commandIdx == 4) {
				//create_fakeblock 명령어의 검증 및 실행
					euler.makeFakeBlock();
					dijkstra.validateNewBlock();
				}
				else if(commandIdx == 5) {
				//status 명령어의 실행
					System.out.println(wallet);
					if(blockchain.getBlockchain().size() > 0) {
						System.out.printf("Current Height : %d\n", blockchain.getBlockchain().size());
						System.out.println("=========================Up to Date Block===============\n");
						System.out.println(blockchain.getBlockchain().get(blockchain.getBlockchain().size() - 1));
					}
					System.out.println("=========================Unconfirmed Transaction===============\n");
					System.out.println(blockchain.getUnconfirmedTransaction());
				}
				else if(commandIdx == 6) {
				//flush 명령어의 실행
					euler.mine(3);
					if(blockchain.isNewBlockCreated())
						dijkstra.validateNewBlock();
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}


				
		}
			
	}
}