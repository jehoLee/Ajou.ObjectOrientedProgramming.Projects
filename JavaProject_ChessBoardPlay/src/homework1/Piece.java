package homework1;

/*5. 체스 말 이동
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
	
	/*checkMovePieceTo함수 : 원래 있던 말의 좌표와 이동할 위치의 좌표를 입력받고, 두개의 좌표값을 이용하여 해당 종류의 말이 이동할 수 있는
	 * 범위와 거리를 지정 -> 두 좌표값의 차이를 이용해 범위 계산*/
	public abstract int checkMovePieceTo(int moveFromX, int moveFromY, int moveToX, int moveToY);

}
