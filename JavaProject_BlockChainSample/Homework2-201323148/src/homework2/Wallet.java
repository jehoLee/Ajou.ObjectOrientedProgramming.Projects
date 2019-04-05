package homework2;

public class Wallet {
	//각 wallet 정보를 가지는 클래스.
	
	private String userName;
	private float userCoin;
	
	public Wallet() {}
	
	public Wallet(String userName, float initCoin) {
		this.userName = userName;
		this.userCoin = initCoin;
	}

	//check user's wallet
	public static void checkWallet(String[] cmdSplited, Wallet sender, Wallet receiver, float coin, float fee) throws IllegalArgumentException{
		if(sender == null) throw new IllegalArgumentException(String.format("%s은 없는 사용자입니다.", cmdSplited[1]));
		if(receiver == null) throw new IllegalArgumentException(String.format("%s은 없는 사용자입니다.", cmdSplited[2]));
		if(coin > sender.getUserCoin()) throw new IllegalArgumentException("sender가 충분한 코인을 가지고 있지 않습니다.");
		if(coin < 0 || fee < 0) throw new IllegalArgumentException("보내는 코인 혹은 수수료가 음수입니다.");
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
	
	//create_transaction명령어를 위해 현재의 "유저한도 내"에서 랜덤한 크기의 송금용 코인 크기 설정
	public float getRandomCoinToSend() {
		float totalCost = (float)(Math.random() * (userCoin + 0.1));
		
		return totalCost;
	}
	
	//트랜잭션 반영시 유저 보유 코인 증가
	public void increaseCoin(float coin) {
		this.userCoin += coin;
	}
	
	//트랜잭션 반영 시 유저의 보유 코인 감소
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
