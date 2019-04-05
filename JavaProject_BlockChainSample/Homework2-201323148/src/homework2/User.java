package homework2;

import java.util.ArrayList;
import java.util.List;

public class User {
	//��������� ���. User�� Ʈ������� �����Ͽ� �̽��� Ʈ����� ����Ʈ�� �߰�
	//Miner�� ���� ���ο� ����� �����Ǹ� ����� �����ϰ�, ��� �� Ʈ������� Waller�� �ݿ�
	
	protected Ledger publicLedger = new Ledger();
	protected List<Wallet> wallets = new ArrayList<Wallet>();
	
	public User(Ledger ledger, ArrayList<Wallet> wallet) {
		this.publicLedger = ledger;
		this.wallets = wallet;
	}
	
	//Ʈ������� �����Ѵ�. ���ο� Ʈ������� ��γ��� �̽��� Ʈ��������� �߰��ȴ�
	public void sendTransaction(Wallet sender, Wallet receiver, float coin, float fee) {
		Transaction newTransaction;
		newTransaction = new Transaction(sender, receiver, coin, fee);
		publicLedger.addUnconfirmedTransaction(newTransaction);
		
	}
	
	//���� ������ ����� ����. ����� �����Ǿ����� Ȯ���� �� call ��
	public void validateNewBlock() throws IllegalArgumentException{
		Block newBlock = publicLedger.getNewBlock();
		String hashStr = Utils.getSHA256(newBlock.getMerkleRoot() + newBlock.getNonce() + newBlock.getPreBlockID());
		if(hashStr.equals(newBlock.getBlockID())) {
			//���� ����
			System.out.println("New Block Is Validated!\n");
			//��ϸ���Ʈ�� �߰�
			publicLedger.getBlockchain().add(publicLedger.getNewBlock());
			
			//�űԺ���� Ʈ������� �̽��� Ʈ����� ����Ʈ���� �����
			for(Transaction transaction : newBlock.getConfirmingTransaction()) {
				if(publicLedger.getUnconfirmedTransaction().contains(transaction)) {
					publicLedger.getUnconfirmedTransaction().remove(transaction);
				}
			}
			
			//Ʈ������� �� wallet�� �ݿ�
			updateWallet();
			
			publicLedger.setNewBlockCreated(false);
		}
		else {
			//���� ����. (fake block)
			System.out.println("Attack the blockchain!\n");
			//fake block���� ���� ������Ʈ�� ledger�� newBlock ������ ������� ������Ŵ
			Block block;
			if(publicLedger.getBlockchain().size() > 0) {
				block = publicLedger.findBlock(publicLedger.getBlockchain().size());
				publicLedger.setNewBlock(block);
			}
			else {
				publicLedger.setNewBlock(null);
				publicLedger.setNewBlockCreated(false);
			}
			
			throw new IllegalArgumentException("��� ���� ����!");
		}
		
	}
	
	//���� �߰��� ��Ͽ� ���缭 �� Wallet���� ����
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
					wallets.get(4).increaseCoin(fee); //miner ������ ȹ��
				}
			}//inner for
			
		}//outer for
		
		
	}
	
	//����� �̸����� ������� ������ ã��
	protected Wallet findWallet(String username) {
		for(Wallet wallet : wallets) {
			if(wallet.getUserName().equals(username)) {
				return wallet;
			}
		}
		return null;
	}
	
}
