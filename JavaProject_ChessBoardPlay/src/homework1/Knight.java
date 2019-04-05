package homework1;

public class Knight extends Piece{

	public Knight(String name, String color) {
		super(name, color);
	}

	public int checkMovePieceTo(int moveFromX, int moveFromY, int moveToX, int moveToY) {
		
		if((moveFromX == moveToX) && (moveFromY < moveToY) && (Math.abs(moveToY - moveFromY) == 3)) { //0
			return 0;
		}
		if((moveFromX > moveToX) && ( moveFromY < moveToY ) && 
				(Math.abs(moveToY - moveFromY) + Math.abs(moveToX - moveFromX)) == 3) {//1
			return 1;
		}
		if((Math.abs( moveFromX - moveToX ) == 3) && ( moveFromY == moveToY ) && (moveFromX < moveToX)) { //2
			return 2;
		}
		if((moveFromX < moveToX) && ( moveFromY < moveToY ) && 
				(Math.abs(moveToY - moveFromY) + Math.abs(moveToX - moveFromX)) == 3) {//3
			return 3;
		}
		if((moveFromX == moveToX) && (moveFromY > moveToY) && (Math.abs( moveFromY - moveToY ) == 3)) { //4
			return 4;
		}
		if((moveFromX < moveToX) && ( moveFromY > moveToY ) && 
				(Math.abs(moveToY - moveFromY) + Math.abs(moveToX - moveFromX)) == 3) {//5
			return 5;
		}
		if((Math.abs( moveFromX - moveToX ) == 3) && ( moveFromY == moveToY ) && (moveFromX > moveToX)) {//6
			return 6;
		}
		if((moveFromX > moveToX) && ( moveFromY > moveToY ) && 
				(Math.abs(moveToY - moveFromY) + Math.abs(moveToX - moveFromX)) == 3) {//7
			return 7;
		}
		
		return -1;
		
	}

}
