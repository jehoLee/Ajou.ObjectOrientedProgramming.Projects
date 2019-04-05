package homework1;

public class Queen extends Piece{

	public Queen(String name, String color) {
		super(name, color);
	}

	public int checkMovePieceTo(int moveFromX, int moveFromY, int moveToX, int moveToY) {
		//같은 위치 이동, out of bound 오류는 이미 처리함
		//방향 : 0(북), 1(북동), 2(동), 3(남동), 4(남), 5(남서), 6(서), 7(북서) - 시계방향
		
		if((moveFromX == moveToX) && (moveFromY < moveToY) && (Math.abs(moveToY - moveFromY) <= 10)) { //0
			return 0;
		}
		if((moveFromX > moveToX) && ( moveFromY < moveToY ) && (Math.abs(moveToY - moveFromY) <= 10)
				&& (Math.abs(moveToX - moveFromX) <= 10 && (Math.abs(moveToY - moveFromY) == Math.abs(moveToX - moveFromX)))) {//1
			return 1;
		}
		if((Math.abs( moveFromX - moveToX ) <= 10) && ( moveFromY == moveToY ) && (moveFromX < moveToX)) { //2
			return 2;
		}
		if((moveFromX < moveToX) && ( moveFromY < moveToY ) && (Math.abs(moveToY - moveFromY) <= 10)
				&& (Math.abs(moveToX - moveFromX) <= 10 && (Math.abs(moveToY - moveFromY) == Math.abs(moveToX - moveFromX)))) {//3
			return 3;
		}
		if((moveFromX == moveToX) && (moveFromY > moveToY) && (Math.abs( moveFromY - moveToY ) <= 10)) { //4
			return 4;
		}
		if((moveFromX < moveToX) && ( moveFromY > moveToY ) && (Math.abs(moveToY - moveFromY) <= 10)
				&& (Math.abs(moveToX - moveFromX) <= 10 && (Math.abs(moveToY - moveFromY) == Math.abs(moveToX - moveFromX)))) {//5
			return 5;
		}
		if((Math.abs( moveFromX - moveToX ) <= 10) && ( moveFromY == moveToY ) && (moveFromX > moveToX)) {//6
			return 6;
		}
		if((moveFromX > moveToX) && ( moveFromY > moveToY ) && (Math.abs(moveToY - moveFromY) <= 10)
				&& (Math.abs(moveToX - moveFromX) <= 10 && (Math.abs(moveToY - moveFromY) == Math.abs(moveToX - moveFromX)))) {//7
			return 7;
		}
		
		return -1;
		
		
		
		
		
		
		
		
	}

}
