package homework2;

public class Wallet {
	//�� wallet ������ ������ Ŭ����.
	
	private String userName;
	private float userCoin;
	
	public Wallet() {}
	
	public Wallet(String userName, float initCoin) {
		this.userName = userName;
		this.userCoin = initCoin;
	}

	//check user's wallet
	public static void checkWallet(String[] cmdSplited, Wallet sender, Wallet receiver, float coin, float fee) throws IllegalArgumentException{
		if(sender == null) throw new IllegalArgumentException(String.format("%s�� ���� ������Դϴ�.", cmdSplited[1]));
		if(receiver == null) throw new IllegalArgumentException(String.format("%s�� ���� ������Դϴ�.", cmdSplited[2]));
		if(coin > sender.getUserCoin()) throw new IllegalArgumentException("sender�� ����� ������ ������ ���� �ʽ��ϴ�.");
		if(coin < 0 || fee < 0) throw new IllegalArgumentException("������ ���� Ȥ�� �����ᰡ �����Դϴ�.");
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public float getUserCoin() {
		return userCoin;
	}
	public void setUserCoin(float userCoin) {
		this.userCoin = userCoin;
	}
	
	//create_transaction��ɾ ���� ������ "�����ѵ� ��"���� ������ ũ���� �۱ݿ� ���� ũ�� ����
	public float getRandomCoinToSend() {
		float totalCost = (float)(Math.random() * (userCoin + 0.1));
		
		return totalCost;
	}
	
	//Ʈ����� �ݿ��� ���� ���� ���� ����
	public void increaseCoin(float coin) {
		this.userCoin += coin;
	}
	
	//Ʈ����� �ݿ� �� ������ ���� ���� ����
	public void decreaseCoin(float coin) {
		this.userCoin -= coin;
	}
	
	public String toString() {
		return "============Wallet============" + 
				"\nUser Name : " + userName + 
				"\nUser Coin : " + userCoin + 
				"\n==============================\n";
	}
	
}
