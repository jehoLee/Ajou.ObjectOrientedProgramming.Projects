package homework2;

import java.util.ArrayList;
import java.util.List;

public class User {
	//유저노드의 기능. User는 트랜잭션을 생성하여 미승인 트랙잭션 리스트에 추가
	//Miner에 의해 새로운 블록이 생성되면 블록을 검증하고, 블록 내 트랜잭션을 Waller에 반영
	
	protected Ledger publicLedger = new Ledger();
	protected List<Wallet> wallets = new ArrayList<Wallet>();
	
	public User(Ledger ledger, ArrayList<Wallet> wallet) {
		this.publicLedger = ledger;
		this.wallets = wallet;
	}
	
	//트랜잭션을 생성한다. 새로운 트랜잭션이 장부내의 미승인 트랜잭션으로 추가된다
	public void sendTransaction(Wallet sender, Wallet receiver, float coin, float fee) {
		Transaction newTransaction;
		newTransaction = new Transaction(sender, receiver, coin, fee);
		publicLedger.addUnconfirmedTransaction(newTransaction);
		
	}
	
	//새로 생성된 블록을 검증. 블록이 생성되었음을 확인한 후 call 됨
	public void validateNewBlock() throws IllegalArgumentException{
		Block newBlock = publicLedger.getNewBlock();
		String hashStr = Utils.getSHA256(newBlock.getMerkleRoot() + newBlock.getNonce() + newBlock.getPreBlockID());
		if(hashStr.equals(newBlock.getBlockID())) {
			//검증 성공
			System.out.println("New Block Is Validated!\n");
			//블록리스트에 추가
			publicLedger.getBlockchain().add(publicLedger.getNewBlock());
			
			//신규블록의 트랜잭션을 미승인 트랜잭션 리스트에서 지운다
			for(Transaction transaction : newBlock.getConfirmingTransaction()) {
				if(publicLedger.getUnconfirmedTransaction().contains(transaction)) {
					publicLedger.getUnconfirmedTransaction().remove(transaction);
				}
			}
			
			//트랜잭션을 각 wallet에 반영
			updateWallet();
			
			publicLedger.setNewBlockCreated(false);
		}
		else {
			//검증 실패. (fake block)
			System.out.println("Attack the blockchain!\n");
			//fake block으로 인해 업데이트된 ledger의 newBlock 정보를 원래대로 복구시킴
			Block block;
			if(publicLedger.getBlockchain().size() > 0) {
				block = publicLedger.findBlock(publicLedger.getBlockchain().size());
				publicLedger.setNewBlock(block);
			}
			else {
				publicLedger.setNewBlock(null);
				publicLedger.setNewBlockCreated(false);
			}
			
			throw new IllegalArgumentException("블록 인증 실패!");
		}
		
	}
	
	//새로 추가된 블록에 맞춰서 각 Wallet들을 갱신
	protected void updateWallet() {
		Block newBlock = publicLedger.getNewBlock();
		Wallet sender = null;
		Wallet receiver = null;
		float coin;
		float fee;
		
		for(Transaction transaction : newBlock.getConfirmingTransaction()) {
			coin = transaction.getCoin();
			fee = transaction.getFee();
			
			//find sender or receiver
			for(Wallet wallet : wallets) {
				if(wallet.getUserName().equals(transaction.getSender())) {
					sender = wallet; //reference
					sender.decreaseCoin(coin);
					sender.decreaseCoin(fee);
				}
				if(wallet.getUserName().equals(transaction.getReceiver())) {
					receiver = wallet;
					receiver.increaseCoin(coin);
					wallets.get(4).increaseCoin(fee); //miner 수수료 획득
				}
			}//inner for
			
		}//outer for
		
		
	}
	
	//사용자 이름으로 사용자의 지갑을 찾음
	protected Wallet findWallet(String username) {
		for(Wallet wallet : wallets) {
			if(wallet.getUserName().equals(username)) {
				return wallet;
			}
		}
		return null;
	}
	
}
