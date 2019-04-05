package homework1;

/*5. ü�� �� �̵�
 * abstract method - checkMovePieceTo*/

public abstract class Piece {
	
	private String name;
	private String color;
	
	public Piece(String name, String color) {
		this.name = name;
		this.color = color;
	}
	
	public String getName() {
		return name;
	}
	
	public String getColor() {
		return color;
	}

	public String toString() {
		return name;
	}
	
	/*checkMovePieceTo�Լ� : ���� �ִ� ���� ��ǥ�� �̵��� ��ġ�� ��ǥ�� �Է¹ް�, �ΰ��� ��ǥ���� �̿��Ͽ� �ش� ������ ���� �̵��� �� �ִ�
	 * ������ �Ÿ��� ���� -> �� ��ǥ���� ���̸� �̿��� ���� ���*/
	public abstract int checkMovePieceTo(int moveFromX, int moveFromY, int moveToX, int moveToY);

}
