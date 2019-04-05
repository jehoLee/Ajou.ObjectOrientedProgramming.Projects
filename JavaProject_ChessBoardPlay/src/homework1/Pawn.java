package homework1;

public class Pawn extends Piece{

	public Pawn(String name, String color) {
		super(name, color);
	}

	public int checkMovePieceTo(int moveFromX, int moveFromY, int moveToX, int moveToY) {
		//북, 남쪽 가능
		//같은 위치 이동, out of bound 오류는 이미 처리함
		//방향 : 0(북), 1(북동), 2(동), 3(남동), 4(남), 5(남서), 6(서), 7(북서) - 시계방향
		if((moveFromX == moveToX) && (moveFromY < moveToY) && (Math.abs(moveToY - moveFromY) == 1)) { //0
			return 0;
		}
		if((moveFromX > moveToX) && ( moveFromY < moveToY )) {//1
			return -1;
		}
		if((moveFromX < moveToX) && ( moveFromY == moveToY ) && (moveFromX < moveToX)) { //2
			return -1;
		}
		if((moveFromX < moveToX) && ( moveFromY < moveToY )) {//3
			return -1;
		}
		if((moveFromX == moveToX) && (moveFromY > moveToY) && (Math.abs( moveFromY - moveToY ) == 1)) { //4
			return 4;
		}
		if((moveFromX < moveToX) && ( moveFromY > moveToY )) {//5
			return -1;
		}
		if((moveFromX > moveToX) && ( moveFromY == moveToY ) && (moveFromX > moveToX)) {//6
			return -1;
		}
		if((moveFromX > moveToX) && ( moveFromY > moveToY )) {//7
			return -1;
		}
		
		return -1;
		
	}
}
