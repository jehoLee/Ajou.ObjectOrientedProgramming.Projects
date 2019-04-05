package homework2;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Miner extends User{
	//채굴보상을 위한 계좌를 추가적으로 가지며, 채굴(블록생성)을 하는 역할을 한다.
	//채굴하는 것과 가짜블록을 만드는 것은 비슷한 기능이므로 가짜블록은 Miner가 생성
	
	private Wallet miner;
	
	public Miner(Ledger ledger, ArrayList<Wallet> wallet, String minerName) {
		super(ledger, wallet);
		for(int i = 0; i < wallet.size(); i++) {
			if(wallet.get(i).getUserName().equals(minerName)) {
				miner = wallet.get(i);
			}
		}
	}
	
	//채굴 - 명령어에 따라 미승인 트랜잭션의 허용크기를 다르게 한다.
	public void mine(int unconfirmedTransactionSizeLimit){
		if(publicLedger.getUnconfirmedTransaction().size() >= unconfirmedTransactionSizeLimit) {
			
			//수수료를 기준으로 미승인 트랜잭션을 내림차순 정렬
			Collections.sort(publicLedger.getUnconfirmedTransaction(), new Comparator<Transaction>() {
				public int compare(Transaction T1, Transaction T2) {
					if(T1.getFee() >= T2.getFee()) {
						return -1;
					}
					else {
						return 1;
					}
				}
			});
	
			//채굴보상 트랜잭션 생성
			Transaction rewardTransaction;
			rewardTransaction = new Transaction(miner, (float) 12.5, (float) 0);
			
			//트랜잭션 리스트와 머클트리 생성
			ArrayList<Transaction> leafTransactions = new ArrayList<Transaction>();
			String[] leafTXs = new String[4];
			for(int i = 0; i < 3; i++) {
				leafTransactions.add(publicLedger.getUnconfirmedTransaction().get(i));
				leafTXs[i] = publicLedger.getUnconfirmedTransaction().get(i).getTxID();
			}
			leafTransactions.add(rewardTransaction);
			leafTXs[3] = rewardTransaction.getTxID();
			
			//MerkleTree 생성
			MerkleTree merkleTree;
			merkleTree = new MerkleTree(leafTXs, "");
			
			//hash
			String nonce;
			String hashStr;
			
			String preBlockId;
			if(publicLedger.getBlockchain().size() > 0) {
				preBlockId = publicLedger.getNewBlock().getBlockID();
			}
			else {
				preBlockId = "";
			}
			
			while(true) {
				nonce = String.valueOf((int)(Math.random() * 1000000000));
				hashStr = Utils.getSHA256(merkleTree.getRoot() + nonce + preBlockId);
				if(hashStr.charAt(0) == '0' && hashStr.charAt(1) == '0' && hashStr.charAt(2) == '0') break;
			}
			
			System.out.println("블록 채굴 성공!\n");
			
			//found hash, 블록 생성
			Block newBlock;
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			newBlock = new Block(hashStr, "Miner", publicLedger.getBlockchain().size()+1,
					timestamp.toString(), nonce, preBlockId, 
					merkleTree.getRoot(), merkleTree, leafTransactions);
			
			//신규 블록을 장부에 추가
			publicLedger.setNewBlock(newBlock);
			publicLedger.setNewBlockCreated(true);
				
			
		}
		
		else return; //블록 생성하지 않음
		
	}

	//가짜블록을 만든다
	public void makeFakeBlock() {
		//채굴보상 트랜잭션 4개 생성
		ArrayList<Transaction> leafTransactions = new ArrayList<Transaction>();
		for(int i = 0; i < 4; i++)
			leafTransactions.add(new Transaction(miner, (float) 12.5, (float) 0));
		String[] leafTXs = new String[4];
		for(int i = 0; i < 4; i++) {
			leafTXs[i] = leafTransactions.get(i).getTxID();
		}
		
		//MerkleTree 생성
		MerkleTree merkleTree;
		merkleTree = new MerkleTree(leafTXs, "");
		
		//hash
		String nonce;
		String hashStr;
		String preBlockId;
		if(publicLedger.getBlockchain().size() > 0) {
			preBlockId = publicLedger.getNewBlock().getBlockID();
		}
		else {
			preBlockId = "";
		}
		
		while(true) {
			nonce = String.valueOf((int)(Math.random() * 1000000000));
			hashStr = Utils.getSHA256(nonce);
			if(hashStr.charAt(0) == '0' && hashStr.charAt(1) == '0' && hashStr.charAt(2) == '0') break;
		}
		
		Block newBlock;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		newBlock = new Block(hashStr, "Miner", publicLedger.getBlockchain().size()+1,
				timestamp.toString(), nonce, preBlockId, 
				merkleTree.getRoot(), merkleTree, leafTransactions);
		
		//신규 블록을 장부에 추가
		publicLedger.setNewBlock(newBlock);
		publicLedger.setNewBlockCreated(true);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
