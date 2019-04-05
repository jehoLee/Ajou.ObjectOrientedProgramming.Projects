package homework2;

import java.util.ArrayList;
import java.util.List;

public class Ledger {
	//블록리스트를 갖고 있는 클래스, 블록을 검색하는 기능을 가짐
	
	private List<Block> blockchain;
	private List<Transaction> unconfirmedTransaction;
	private Block newBlock; //preBlock 찾는데 사용
	private boolean isNewBlockCreated;
	
	public Ledger() {
		this.newBlock = null; //초기화
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
