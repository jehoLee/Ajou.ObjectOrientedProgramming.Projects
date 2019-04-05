package homework1;

public class Rook extends Piece{

	public Rook(String name, String color) {
		super(name, color);
	}

	public int checkMovePieceTo(int moveFromX, int moveFromY, int moveToX, int moveToY) {
		//���� ��ġ �̵�, out of bound ������ �̹� ó����
		//���� : 0(��), 1(�ϵ�), 2(��), 3(����), 4(��), 5(����), 6(��), 7(�ϼ�) - �ð����
		if((moveFromX == moveToX) && (moveFromY < moveToY) && (Math.abs(moveToY - moveFromY) <= 2)) { //0
			return 0;
		}
		if((moveFromX > moveToX) && ( moveFromY < moveToY ) && (Math.abs(moveToY - moveFromY) == 1)
				&& (Math.abs(moveToX - moveFromX) == 1 && (Math.abs(moveToY - moveFromY) == Math.abs(moveToX - moveFromX)))) {//1
			return 1;
		}
		if((Math.abs( moveFromX - moveToX ) <= 2) && ( moveFromY == moveToY ) && (moveFromX < moveToX)) { //2
			return 2;
		}
		if((moveFromX < moveToX) && ( moveFromY < moveToY ) && (Math.abs(moveToY - moveFromY) == 1)
				&& (Math.abs(moveToX - moveFromX) == 1 && (Math.abs(moveToY - moveFromY) == Math.abs(moveToX - moveFromX)))) {//3
			return 3;
		}
		if((moveFromX == moveToX) && (moveFromY > moveToY) && (Math.abs( moveFromY - moveToY ) <= 2)) { //4
			return 4;
		}
		if((moveFromX < moveToX) && ( moveFromY > moveToY ) && (Math.abs(moveToY - moveFromY) == 1)
				&& (Math.abs(moveToX - moveFromX) == 1 && (Math.abs(moveToY - moveFromY) == Math.abs(moveToX - moveFromX)))) {//5
			return 5;
		}
		if((Math.abs( moveFromX - moveToX ) <= 2) && ( moveFromY == moveToY ) && (moveFromX > moveToX)) {//6
			return 6;
		}
		if((moveFromX > moveToX) && ( moveFromY > moveToY ) && (Math.abs(moveToY - moveFromY) == 1)
				&& (Math.abs(moveToX - moveFromX) == 1 && (Math.abs(moveToY - moveFromY) == Math.abs(moveToX - moveFromX)))) {//7
			return 7;
		}
		
		return -1;
		
	}
}
