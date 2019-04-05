package homework2;

import java.util.ArrayList;
import java.util.List;

public class Ledger {
	//��ϸ���Ʈ�� ���� �ִ� Ŭ����, ����� �˻��ϴ� ����� ����
	
	private List<Block> blockchain;
	private List<Transaction> unconfirmedTransaction;
	private Block newBlock; //preBlock ã�µ� ���
	private boolean isNewBlockCreated;
	
	public Ledger() {
		this.newBlock = null; //�ʱ�ȭ
		this.blockchain = new ArrayList<Block>();
		this.unconfirmedTransaction = new ArrayList<Transaction>();
	}
	
	public Block findBlock(long blockHeight) throws IllegalArgumentException{
		for(Block block : blockchain) {
			if(blockHeight == block.getBlockHeight()) {
				return block;
			}
		}
		
		return null;
	}
	
	public void addUnconfirmedTransaction(Transaction unconfirmedTransaction) {
		this.unconfirmedTransaction.add(unconfirmedTransaction);
	}
	
	//getter and setter
	public List<Block> getBlockchain() {
		return blockchain;
	}
	public void setBlockchain(List<Block> blockchain) {
		this.blockchain = blockchain;
	}
	public List<Transaction> getUnconfirmedTransaction() {
		return unconfirmedTransaction;
	}
	public void setUnconfirmedTransaction(List<Transaction> unconfirmedTransaction) {
		this.unconfirmedTransaction = unconfirmedTransaction;
	}
	public Block getNewBlock() {
		return newBlock;
	}
	public void setNewBlock(Block newBlock) {
		this.newBlock = newBlock;
	}
	public boolean isNewBlockCreated() {
		return isNewBlockCreated;
	}
	public void setNewBlockCreated(boolean isNewBlockCreated) {
		this.isNewBlockCreated = isNewBlockCreated;
	}
	
	
}
