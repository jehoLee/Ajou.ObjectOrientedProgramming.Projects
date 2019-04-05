package homework1;

public class Pawn extends Piece{

	public Pawn(String name, String color) {
		super(name, color);
	}

	public int checkMovePieceTo(int moveFromX, int moveFromY, int moveToX, int moveToY) {
		//��, ���� ����
		//���� ��ġ �̵�, out of bound ������ �̹� ó����
		//���� : 0(��), 1(�ϵ�), 2(��), 3(����), 4(��), 5(����), 6(��), 7(�ϼ�) - �ð����
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
