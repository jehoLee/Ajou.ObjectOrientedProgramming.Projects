package homework2;

import java.util.ArrayList;

public class Block {
	//블록정보를 담고있는 자료구조. 첫번째 블록은 이전 블록이 없다.
	
	private String blockID; //블록 ID는 채굴과정을 통해 계산된 앞자리가 0이 세개인 해시함수의 결과값
	private String blockMaker;
	private long blockHeight;
	private String timestamp;
	private String nonce;
	private String preBlockID;
	private String merkleRoot;
	private MerkleTree merkleTree;
	private ArrayList<Transaction> confirmingTransaction;
	
	public Block() {}

	//Constructor with arguments
	public Block(String blockID, String blockMaker, 
			long blockHeight, String timestamp, 
			String nonce, String preBlockID,
			String merkleRoot, MerkleTree merkleTree, 
			ArrayList<Transaction> transactions) {
	
		this.blockID = blockID;
		this.blockMaker = blockMaker;
		this.blockHeight = blockHeight;
		this.timestamp = timestamp;
		this.nonce = nonce;
		if(blockHeight == 0)
			this.preBlockID = "";
		else
			this.preBlockID = preBlockID;
		this.merkleRoot = merkleRoot;
		this.merkleTree = merkleTree;
		this.confirmingTransaction = new ArrayList<Transaction>();
		this.confirmingTransaction = transactions;
	}
	
	//getter and setter
	public String getBlockID() {
		return blockID;
	}
	public void setBlockID(String blockID) {
		this.blockID = blockID;
	}
	public String getBlockMaker() {
		return blockMaker;
	}
	public void setBlockMaker(String blockMaker) {
		this.blockMaker = blockMaker;
	}
	public long getBlockHeight() {
		return blockHeight;
	}
	public void setBlockHeight(long blockHeight) {
		this.blockHeight = blockHeight;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getPreBlockID() {
		return preBlockID;
	}
	public void setPreBlockID(String preBlockID) {
		this.preBlockID = preBlockID;
	}
	public String getMerkleRoot() {
		return merkleRoot;
	}
	public void setMerkleRoot(String merkleRoot) {
		this.merkleRoot = merkleRoot;
	}
	public MerkleTree getMerkleTree() {
		return merkleTree;
	}
	public void setMerkleTree(MerkleTree merkleTree) {
		this.merkleTree = merkleTree;
	}
	public ArrayList<Transaction> getConfirmingTransaction() {
		return confirmingTransaction;
	}
	public void setConfirmingTransaction(ArrayList<Transaction> confirmingTransaction) {
		this.confirmingTransaction = confirmingTransaction;
	}

	@Override
	public String toString() {

		return "=========================Block Head=================" +
				"\nBlock ID : " + blockID +
				"\nBlock Maker : " + blockMaker +
				"\nBlock Height : " + blockHeight +
				"\nTime Stamp : " + timestamp +
				"\nPreBlock ID : " + preBlockID +
				"\nMerkle Root : " + merkleRoot +
				"\nNonce : " + nonce +
				"\n===========================Block Body===============\n" +
				confirmingTransaction;
	}
	
}
