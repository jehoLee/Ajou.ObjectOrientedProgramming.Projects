package homework1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BoardDriver {
	
	private static Piece[][] board = new Piece[10][10]; //10X10 Chess Board
	
	public static class Coordinate {
		int x, y;
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public Coordinate(int x, int y) {
			this.x = x; this.y = y;
		}
	}
	
	static Scanner input= new Scanner(System.in);
	
	public static void BoardInit(){
		//default board init
		board[0][0] = new Pawn("P1", "b");
		board[0][1] = new Rook("R1", "b");
		board[0][2] = new Knight("N1", "b");
		board[0][3] = new Bishop("B1", "b");
		board[0][4] = new King("K1", "b");
		board[0][5] = new Queen("Q1", "b");
		board[0][6] = new Bishop("B1", "b");
		board[0][7] = new Knight("N1", "b");
		board[0][8] = new Rook("R1", "b");
		board[0][9] = new Pawn("P1", "b");
		
		board[9][0] = new Pawn("P0", "w");
		board[9][1] = new Rook("R0", "w");
		board[9][2] = new Knight("N0", "w");
		board[9][3] = new Bishop("B0", "w");
		board[9][4] = new King("K0", "w");
		board[9][5] = new Queen("Q0", "w");
		board[9][6] = new Bishop("B0", "w");
		board[9][7] = new Knight("N0", "w");
		board[9][8] = new Rook("R0", "w");
		board[9][9] = new Pawn("P0", "w");
		
		for(int i = 1; i < 9; i++) {
			for(int j = 0; j < 10; j++) {
				if(i == 1) board[i][j] = new Pawn("P1", "b");
				if(i == 8) board[i][j] = new Pawn("P0", "w");
				//i + j == even number -> 1, odd number -> 0
				if(i > 1 && i < 8) {
					if( (i+j) % 2 == 0 ) board[i][j] = new Blank("1 ", "");
					else board[i][j] = new Blank("0 ", "");
				}
			}
		}
		
		System.out.println("ü���� ������ �Ϸ�Ǿ����ϴ�.\n");
	}	
	
	public static void BoardRandomInit(){
			//random board init
			Coordinate[] coordinate = new Coordinate[100];
			int num = 0;
			for(int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					coordinate[num++] = new Coordinate(i, j);
				}
				if (num >= 99) break;
			}
			
			List<Coordinate> list = Arrays.asList(coordinate);
			Collections.shuffle(list);
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					if( (i+j) % 2 == 0 ) board[i][j] = new Blank("1 ", "");
					else board[i][j] = new Blank("0 ", "");
				}
			}
			
			for (int i = 0; i < 100; i++) {
				if(i < 2) board[list.get(i).x][list.get(i).y] = new Rook("R1", "b");
				if(i >= 2 && i < 4) board[list.get(i).x][list.get(i).y] = new Knight("N1", "b");
				if(i >= 4 && i < 6) board[list.get(i).x][list.get(i).y] = new Bishop("B1", "b");
				if(i == 6) board[list.get(i).x][list.get(i).y] = new King("K1", "b");
				if(i == 7) board[list.get(i).x][list.get(i).y] = new Queen("Q1", "b");
				if(i > 7 && i < 20) board[list.get(i).x][list.get(i).y] = new Pawn("P1", "b");
				
				if(i >= 20 && i < 22) board[list.get(i).x][list.get(i).y] = new Rook("R0", "w");
				if(i >= 22 && i < 24) board[list.get(i).x][list.get(i).y] = new Knight("N0", "w");
				if(i >= 24 && i < 26) board[list.get(i).x][list.get(i).y] = new Bishop("B0", "w");
				if(i == 26) board[list.get(i).x][list.get(i).y] = new King("K0", "w");
				if(i == 27) board[list.get(i).x][list.get(i).y] = new Queen("Q0", "w");
				if(i > 27 && i < 40) board[list.get(i).x][list.get(i).y] = new Pawn("P0", "w");
			}
			
			System.out.println("ü���� ������ �Ϸ�Ǿ����ϴ�.\n");
	}
	
	public static void printBoard() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.printf("%s ", board[i][j].toString());
			}
			System.out.printf("\n");
		}
	}
	
	/*checkLocationVaild �Լ� : a1, B2 �� ���� �Է¹��� ��ġ�� ��ǥ ������ �ٲپ� ���� ��Ų��.
	 * �̶� �Է� ���� ��ġ�� ������ ����� �ʾҴ���, ��ġ�� ���� ������, �߸��� �Է����� �Ǵ��Ѵ�.*/
	public static int[] checkLocationVaild(String locIn, String blankCheck) {
		String col = "abcdefghij";
		String row = "12345678910";
		
		String[] str = new String[3];
		int x;
		int y;
		int[] loc = new int[2];
		
		if(locIn.length() <= 3 && locIn.length() >= 2) {
			str = locIn.split("");
			str[0] = str[0].toLowerCase();
			
			if(str.length == 3) x = row.indexOf(str[1] + str[2]);
			else x = row.indexOf(str[1]);
			y = col.indexOf(str[0]);
			
			if(x < 0 || x > 9 || y < 0) {
				System.out.println("�߸��� �Է��Դϴ�.\n");
			}
			else {
				x = 9 - x;
				if(blankCheck.equals("needCheckBlank") && board[x][y] instanceof Blank) {
					System.out.println("�ش� ��ġ�� ü�� ���� �����ϴ�.\n");
				}
				else { //�ùٸ� ��ġ�� �Է�
					loc[0] = x; loc[1] = y;
					return loc;
				}
			}
		}
		else System.out.println("�߸��� �Է��Դϴ�.\n");
		
		return null;
		
	}
	
	public static void DeletePiece() {
		System.out.printf("������ ���ϴ� ü�� ���� ��ġ�� �Է��ϼ���. ");
		String locIn = input.next();
		
		int[] loc = new int[2];
		loc = checkLocationVaild(locIn, "needCheckBlank");
		if(loc != null) {
			if( (loc[0] + loc[1]) % 2 == 0 ) board[loc[0]][loc[1]] = new Blank("1 ", "");
			else board[loc[0]][loc[1]] = new Blank("0 ", "");
			System.out.println("ü�� ���� �����Ǿ����ϴ�.\n");
		}
		
	}
	
	
	public static void MovePiece() {
		int fromX, fromY, toX = -1, toY = -1;
		int fromRow, fromCol, toRow, toCol;
		
		System.out.printf("������ ���� ��ġ�� �Է��ϼ���. ");
		String locIn = input.next();
		
		int[] loc = new int[2];
		loc = checkLocationVaild(locIn, "needCheckBlank");
		if(loc != null) {
			fromX = loc[1];
			fromY = 9 - loc[0];
			fromRow = loc[0];
			fromCol = loc[1];
			
			System.out.printf("���� �̵��� ��ġ�� �Է��ϼ���. ");
			locIn = input.next();
			loc = checkLocationVaild(locIn, "");
			if(loc != null) { //�Է� ��ȿ
				toX = loc[1];//��ǥ ����� ����
				toY = 9 - loc[0];//��ǥ ����� ����
				toRow = loc[0];//�迭 index ����� ����
				toCol = loc[1];//�迭 index ����� ����
				if(fromX == toX && fromY == toY) {
					System.out.printf("�ش� ��ġ�� �̵��� �� �����ϴ� : ���� ��ġ �Դϴ�.\n");
					return;
				}
				
				int moveSign; //�ش� ��ǥ�� �̵� �����ϸ� 0�̻��� ��, �Ұ����ϸ� -1 (�߰������� 0�̻��� ���� ���� ������ ���)
				moveSign = board[fromRow][fromCol].checkMovePieceTo(fromX, fromY, toX, toY); //����� ���� 
				
				if(moveSign < 0) {
					System.out.printf("�ش� ��ġ�� �̵��� �� �����ϴ� : ���� �̵��� �� �ִ� ��ΰ� �ƴմϴ�.\n");
					return;
				}
				else { //class�ܿ��� �� ������ ���� �̵� ���� ���⿡���� �־��� moveTo�� �� �� �ִ��� �����ĸ� �Ǵ��� �� ����
					//���⼭�� board�ܿ��� �Ǵ�.-> �ǳʶۼ��ִ���, �ش� �ڸ��� �������Ǹ��ִ��� �Ǵ� (���� �̿��� �Է��� �̹� �Ÿ� ����)					
					if (board[toRow][toCol].getColor() == board[fromRow][fromCol].getColor()) {
						System.out.printf("�ش� ��ġ�� �̵��� �� �����ϴ� : ���� ���� ���� ������ �ֽ��ϴ�.\n");
					}
					else {
						if(board[fromRow][fromCol] instanceof Knight) { //knight�� �ǳʶ� �� ����.
							board[toRow][toCol] = board[fromRow][fromCol];
							if( (fromRow + fromCol) % 2 == 0 ) board[fromRow][fromCol] = new Blank("1 ", "");
							else board[fromRow][fromCol] = new Blank("0 ", "");
						}
						else {
							//knight�� �ƴ� ���, �ǳʶ��� ���ϴ� �� ���
							//���� : 0(��), 1(�ϵ�), 2(��), 3(����), 4(��), 5(����), 6(��), 7(�ϼ�) - �ð����
							
							if(checkPathEmpty(moveSign, fromRow, fromCol, toRow, toCol)) {
								board[toRow][toCol] = board[fromRow][fromCol];
								if( (fromRow + fromCol) % 2 == 0 ) board[fromRow][fromCol] = new Blank("1 ", "");
								else board[fromRow][fromCol] = new Blank("0 ", "");
								System.out.printf("���� %s(��)�� �̵� ���׽��ϴ�.\n", locIn);
							}
						
						}
					}
				}
				
			}
			
		}
		
		
	}
	
	/*checkPathEmpty �Լ� : ��� ���� �ٸ� ���� �ִ��� check
	 * �ش� ��� �������� �� ĭ �� �̵��ϸ鼭 instanceof Blank�� ȣ���Ͽ� Blank�� �ƴ� Class�� ������ ��γ��� �ٸ� ���� �ִ°�*/
	public static boolean checkPathEmpty(int direction, int fromRow, int fromCol, int toRow, int toCol) {
		//direction : 0(��), 1(�ϵ�), 2(��), 3(����), 4(��), 5(����), 6(��), 7(�ϼ�) - �ð����
		int i, j;
		
		switch(direction) {
		case 0: 
			for(i = fromRow - 1; i > toRow; i--) {
				if(!(board[i][fromCol] instanceof Blank)) {
					System.out.printf("�ش� ��ġ�� �̵��� �� �����ϴ� : ��� �߰��� �ٸ� ���� �ֽ��ϴ�.\n");
					return false;
				}
			}
			break;
			
		case 1: 
			for(i = fromRow - 1; i > toRow; i--) {
				j = fromCol + 1;
				if(!(board[i][j] instanceof Blank)) {
					System.out.printf("�ش� ��ġ�� �̵��� �� �����ϴ� : ��� �߰��� �ٸ� ���� �ֽ��ϴ�.\n");
					return false;
				}
			}
			break;
			
		case 2: 
			for(i = fromCol + 1; i < toCol; i++) {
				if(!(board[fromRow][i] instanceof Blank)) {
					System.out.printf("�ش� ��ġ�� �̵��� �� �����ϴ� : ��� �߰��� �ٸ� ���� �ֽ��ϴ�.\n");
					return false;
				}
			}
			break;
			
		case 3: 
			for(i = fromCol + 1; i < toCol; i++) {
				j = fromRow + 1;
				if(!(board[j][i] instanceof Blank)) {
					System.out.printf("�ش� ��ġ�� �̵��� �� �����ϴ� : ��� �߰��� �ٸ� ���� �ֽ��ϴ�.\n");
					return false;
				}
			}
			break;
			
		case 4: 
			for(i = fromRow + 1; i < toRow; i++) {
				if(!(board[i][fromCol] instanceof Blank)) {
					System.out.printf("�ش� ��ġ�� �̵��� �� �����ϴ� : ��� �߰��� �ٸ� ���� �ֽ��ϴ�.\n");
					return false;
				}
			}
			break;
			
		case 5: 
			for(i = fromRow + 1; i < toRow; i++) {
				j = fromCol - 1;
				if(!(board[i][j] instanceof Blank)) {
					System.out.printf("�ش� ��ġ�� �̵��� �� �����ϴ� : ��� �߰��� �ٸ� ���� �ֽ��ϴ�.\n");
					return false;
				}
			}
			break;
			
		case 6: 
			for(i = fromCol - 1; i > toCol; i--) {
				if(!(board[fromRow][i] instanceof Blank)) {
					System.out.printf("�ش� ��ġ�� �̵��� �� �����ϴ� : ��� �߰��� �ٸ� ���� �ֽ��ϴ�.\n");
					return false;
				}
			}
			break;
			
		case 7: 
			for(i = fromCol - 1; i > toCol; i--) {
				j = fromRow - 1;
				if(!(board[j][i] instanceof Blank)) {
					System.out.printf("�ش� ��ġ�� �̵��� �� �����ϴ� : ��� �߰��� �ٸ� ���� �ֽ��ϴ�.\n");
					return false;
				}
			}
			break;
		}
		
		return true;
		
	}
	
	
	public static void main(String[] args) {
		
		String userIn;
		
		while(true) {
			System.out.println("<Ajou Chess Board>\n\n���ϴ� �޴��� �����ϼ���.\n"
					+ "1. ü�� �� ����\n2. ü�� �� ���� ����\n3. ü�� �� ����\n4. ü���� ����\n5. ü�� �� �̵�\n6. ����\n>>");
			userIn = input.next();
			if(userIn.equals("1")||userIn.equals("2")||userIn.equals("3")||userIn.equals("4")||userIn.equals("5")||userIn.equals("6")) {
			switch(userIn) {
				case "1": 
					BoardInit();
					break;
							
				case "2": 
					BoardRandomInit();
					break;
							
				case "3": 
					DeletePiece();
					break;
							
				case "4": 
					printBoard();
					break;
							
				case "5": 
					MovePiece();
					break;
							
				case "6": 
					System.out.println("���α׷��� ���� �մϴ�.\n");
					return;
			}
			System.out.println("============================\n");
			}
			else {
				System.out.println("�޴��� 1���� 6���� ���� �����մϴ�.\n");
			}
		}
		
	}
	
		
}










