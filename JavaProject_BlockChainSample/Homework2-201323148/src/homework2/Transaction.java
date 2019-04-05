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
		
		//txID : Ʈ������� ID�� �� Ʈ������� ������ �� �ִ� SHA-256 �ؽ��Լ��� �����
		this.txID = Utils.getSHA256(sender.toString() + receiver.toString() + timeStamp);
	}
	//Constructor 2 - ä�� ���� Ʈ������� ��츦 ����
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
