package homework2;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Miner extends User{
	//ä�������� ���� ���¸� �߰������� ������, ä��(��ϻ���)�� �ϴ� ������ �Ѵ�.
	//ä���ϴ� �Ͱ� ��¥����� ����� ���� ����� ����̹Ƿ� ��¥����� Miner�� ����
	
	private Wallet miner;
	
	public Miner(Ledger ledger, ArrayList<Wallet> wallet, String minerName) {
		super(ledger, wallet);
		for(int i = 0; i < wallet.size(); i++) {
			if(wallet.get(i).getUserName().equals(minerName)) {
				miner = wallet.get(i);
			}
		}
	}
	
	//ä�� - ��ɾ ���� �̽��� Ʈ������� ���ũ�⸦ �ٸ��� �Ѵ�.
	public void mine(int unconfirmedTransactionSizeLimit){
		if(publicLedger.getUnconfirmedTransaction().size() >= unconfirmedTransactionSizeLimit) {
			
			//�����Ḧ �������� �̽��� Ʈ������� �������� ����
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
	
			//ä������ Ʈ����� ����
			Transaction rewardTransaction;
			rewardTransaction = new Transaction(miner, (float) 12.5, (float) 0);
			
			//Ʈ����� ����Ʈ�� ��ŬƮ�� ����
			ArrayList<Transaction> leafTransactions = new ArrayList<Transaction>();
			String[] leafTXs = new String[4];
			for(int i = 0; i < 3; i++) {
				leafTransactions.add(publicLedger.getUnconfirmedTransaction().get(i));
				leafTXs[i] = publicLedger.getUnconfirmedTransaction().get(i).getTxID();
			}
			leafTransactions.add(rewardTransaction);
			leafTXs[3] = rewardTransaction.getTxID();
			
			//MerkleTree ����
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
			
			System.out.println("��� ä�� ����!\n");
			
			//found hash, ��� ����
			Block newBlock;
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			newBlock = new Block(hashStr, "Miner", publicLedger.getBlockchain().size()+1,
					timestamp.toString(), nonce, preBlockId, 
					merkleTree.getRoot(), merkleTree, leafTransactions);
			
			//�ű� ����� ��ο� �߰�
			publicLedger.setNewBlock(newBlock);
			publicLedger.setNewBlockCreated(true);
				
			
		}
		
		else return; //��� �������� ����
		
	}

	//��¥����� �����
	public void makeFakeBlock() {
		//ä������ Ʈ����� 4�� ����
		ArrayList<Transaction> leafTransactions = new ArrayList<Transaction>();
		for(int i = 0; i < 4; i++)
			leafTransactions.add(new Transaction(miner, (float) 12.5, (float) 0));
		String[] leafTXs = new String[4];
		for(int i = 0; i < 4; i++) {
			leafTXs[i] = leafTransactions.get(i).getTxID();
		}
		
		//MerkleTree ����
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
		
		//�ű� ����� ��ο� �߰�
		publicLedger.setNewBlock(newBlock);
		publicLedger.setNewBlockCreated(true);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
