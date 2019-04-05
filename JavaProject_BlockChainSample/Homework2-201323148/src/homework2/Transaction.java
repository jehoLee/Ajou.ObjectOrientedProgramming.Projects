package homework2;

import java.sql.Timestamp;

public class Transaction {
	
	private String txID;
	private String sender;
	private float coin;
	private String receiver;
	private float fee;
	private String timeStamp;
	
	//Constructor 1
	public Transaction(Wallet sender, Wallet receiver, float coin, float f) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		this.timeStamp = timestamp.toString();
		this.sender = sender.getUserName();
		this.receiver = receiver.getUserName();
		this.coin = coin;
		this.fee = f;
		
		//txID : 트랜잭션의 ID는 각 트랜잭션을 구분할 수 있는 SHA-256 해시함수의 결과값
		this.txID = Utils.getSHA256(sender.toString() + receiver.toString() + timeStamp);
	}
	//Constructor 2 - 채굴 보상 트랜잭션인 경우를 위해
	public Transaction(Wallet receiver, float coin, float f) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		this.timeStamp = timestamp.toString();
		this.receiver = receiver.getUserName();
		this.coin = coin;
		this.fee = f;
		
		//txID
		this.txID = Utils.getSHA256(receiver.toString() + timeStamp);
	}

	//getter and setters
	public String getTxID() {
		return txID;
	}

	public void setTxID(String txID) {
		this.txID = txID;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public float getCoin() {
		return coin;
	}

	public void setCoin(float coin) {
		this.coin = coin;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String toString() {
		return "=======================Transaction\n" +
				txID + "===============" +
				"\nSender : " + sender +
				"\nReceiver : " + receiver +
				"\nCoin : " + coin +
				"\nFee : " + fee +
				"\nTimeStamp : " + timeStamp +
				"\n===============================================\n";
	}
}
