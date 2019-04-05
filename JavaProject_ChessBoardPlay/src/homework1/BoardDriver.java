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
		
		System.out.println("체스판 생성이 완료되었습니다.\n");
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
			
			System.out.println("체스판 생성이 완료되었습니다.\n");
	}
	
	public static void printBoard() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.printf("%s ", board[i][j].toString());
			}
			System.out.printf("\n");
		}
	}
	
	/*checkLocationVaild 함수 : a1, B2 와 같이 입력받은 위치를 좌표 값으로 바꾸어 리턴 시킨다.
	 * 이때 입력 받은 위치가 범위를 벗어나지 않았는지, 위치에 말이 없는지, 잘못된 입력인지 판단한다.*/
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
				System.out.println("잘못된 입력입니다.\n");
			}
			else {
				x = 9 - x;
				if(blankCheck.equals("needCheckBlank") && board[x][y] instanceof Blank) {
					System.out.println("해당 위치에 체스 말이 없습니다.\n");
				}
				else { //올바른 위치의 입력
					loc[0] = x; loc[1] = y;
					return loc;
				}
			}
		}
		else System.out.println("잘못된 입력입니다.\n");
		
		return null;
		
	}
	
	public static void DeletePiece() {
		System.out.printf("삭제를 원하는 체스 말의 위치를 입력하세요. ");
		String locIn = input.next();
		
		int[] loc = new int[2];
		loc = checkLocationVaild(locIn, "needCheckBlank");
		if(loc != null) {
			if( (loc[0] + loc[1]) % 2 == 0 ) board[loc[0]][loc[1]] = new Blank("1 ", "");
			else board[loc[0]][loc[1]] = new Blank("0 ", "");
			System.out.println("체스 말이 삭제되었습니다.\n");
		}
		
	}
	
	
	public static void MovePiece() {
		int fromX, fromY, toX = -1, toY = -1;
		int fromRow, fromCol, toRow, toCol;
		
		System.out.printf("움직일 말의 위치를 입력하세요. ");
		String locIn = input.next();
		
		int[] loc = new int[2];
		loc = checkLocationVaild(locIn, "needCheckBlank");
		if(loc != null) {
			fromX = loc[1];
			fromY = 9 - loc[0];
			fromRow = loc[0];
			fromCol = loc[1];
			
			System.out.printf("말이 이동할 위치를 입력하세요. ");
			locIn = input.next();
			loc = checkLocationVaild(locIn, "");
			if(loc != null) { //입력 유효
				toX = loc[1];//좌표 계산을 위해
				toY = 9 - loc[0];//좌표 계산을 위해
				toRow = loc[0];//배열 index 계산을 위해
				toCol = loc[1];//배열 index 계산을 위해
				if(fromX == toX && fromY == toY) {
					System.out.printf("해당 위치로 이동할 수 없습니다 : 같은 위치 입니다.\n");
					return;
				}
				
				int moveSign; //해당 좌표로 이동 가능하면 0이상의 값, 불가능하면 -1 (추가적으로 0이상의 값을 방향 정보로 사용)
				moveSign = board[fromRow][fromCol].checkMovePieceTo(fromX, fromY, toX, toY); //계산을 위해 
				
				if(moveSign < 0) {
					System.out.printf("해당 위치로 이동할 수 없습니다 : 말이 이동할 수 있는 경로가 아닙니다.\n");
					return;
				}
				else { //class단에서 각 종류의 말의 이동 가능 방향에따라 주어진 moveTo로 갈 수 있느냐 없느냐만 판단을 한 상태
					//여기서는 board단에서 판단.-> 건너뛸수있는지, 해당 자리에 같은색의말있는지 판단 (범위 이외의 입력은 이미 거른 상태)					
					if (board[toRow][toCol].getColor() == board[fromRow][fromCol].getColor()) {
						System.out.printf("해당 위치로 이동할 수 없습니다 : 같은 색의 말이 놓여져 있습니다.\n");
					}
					else {
						if(board[fromRow][fromCol] instanceof Knight) { //knight는 건너뛸 수 있음.
							board[toRow][toCol] = board[fromRow][fromCol];
							if( (fromRow + fromCol) % 2 == 0 ) board[fromRow][fromCol] = new Blank("1 ", "");
							else board[fromRow][fromCol] = new Blank("0 ", "");
						}
						else {
							//knight가 아닌 경우, 건너뛰지 못하는 것 고려
							//방향 : 0(북), 1(북동), 2(동), 3(남동), 4(남), 5(남서), 6(서), 7(북서) - 시계방향
							
							if(checkPathEmpty(moveSign, fromRow, fromCol, toRow, toCol)) {
								board[toRow][toCol] = board[fromRow][fromCol];
								if( (fromRow + fromCol) % 2 == 0 ) board[fromRow][fromCol] = new Blank("1 ", "");
								else board[fromRow][fromCol] = new Blank("0 ", "");
								System.out.printf("말을 %s(으)로 이동 시켰습니다.\n", locIn);
							}
						
						}
					}
				}
				
			}
			
		}
		
		
	}
	
	/*checkPathEmpty 함수 : 경로 내에 다른 말이 있는지 check
	 * 해당 경로 방향으로 한 칸 씩 이동하면서 instanceof Blank를 호출하여 Blank가 아닌 Class가 있으면 경로내에 다른 말이 있는것*/
	public static boolean checkPathEmpty(int direction, int fromRow, int fromCol, int toRow, int toCol) {
		//direction : 0(북), 1(북동), 2(동), 3(남동), 4(남), 5(남서), 6(서), 7(북서) - 시계방향
		int i, j;
		
		switch(direction) {
		case 0: 
			for(i = fromRow - 1; i > toRow; i--) {
				if(!(board[i][fromCol] instanceof Blank)) {
					System.out.printf("해당 위치로 이동할 수 없습니다 : 경로 중간에 다른 말이 있습니다.\n");
					return false;
				}
			}
			break;
			
		case 1: 
			for(i = fromRow - 1; i > toRow; i--) {
				j = fromCol + 1;
				if(!(board[i][j] instanceof Blank)) {
					System.out.printf("해당 위치로 이동할 수 없습니다 : 경로 중간에 다른 말이 있습니다.\n");
					return false;
				}
			}
			break;
			
		case 2: 
			for(i = fromCol + 1; i < toCol; i++) {
				if(!(board[fromRow][i] instanceof Blank)) {
					System.out.printf("해당 위치로 이동할 수 없습니다 : 경로 중간에 다른 말이 있습니다.\n");
					return false;
				}
			}
			break;
			
		case 3: 
			for(i = fromCol + 1; i < toCol; i++) {
				j = fromRow + 1;
				if(!(board[j][i] instanceof Blank)) {
					System.out.printf("해당 위치로 이동할 수 없습니다 : 경로 중간에 다른 말이 있습니다.\n");
					return false;
				}
			}
			break;
			
		case 4: 
			for(i = fromRow + 1; i < toRow; i++) {
				if(!(board[i][fromCol] instanceof Blank)) {
					System.out.printf("해당 위치로 이동할 수 없습니다 : 경로 중간에 다른 말이 있습니다.\n");
					return false;
				}
			}
			break;
			
		case 5: 
			for(i = fromRow + 1; i < toRow; i++) {
				j = fromCol - 1;
				if(!(board[i][j] instanceof Blank)) {
					System.out.printf("해당 위치로 이동할 수 없습니다 : 경로 중간에 다른 말이 있습니다.\n");
					return false;
				}
			}
			break;
			
		case 6: 
			for(i = fromCol - 1; i > toCol; i--) {
				if(!(board[fromRow][i] instanceof Blank)) {
					System.out.printf("해당 위치로 이동할 수 없습니다 : 경로 중간에 다른 말이 있습니다.\n");
					return false;
				}
			}
			break;
			
		case 7: 
			for(i = fromCol - 1; i > toCol; i--) {
				j = fromRow - 1;
				if(!(board[j][i] instanceof Blank)) {
					System.out.printf("해당 위치로 이동할 수 없습니다 : 경로 중간에 다른 말이 있습니다.\n");
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
			System.out.println("<Ajou Chess Board>\n\n원하는 메뉴를 선택하세요.\n"
					+ "1. 체스 말 생성\n2. 체스 말 랜덤 생성\n3. 체스 말 삭제\n4. 체스판 보기\n5. 체스 말 이동\n6. 종료\n>>");
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
					System.out.println("프로그램을 종료 합니다.\n");
					return;
			}
			System.out.println("============================\n");
			}
			else {
				System.out.println("메뉴는 1부터 6까지 선택 가능합니다.\n");
			}
		}
		
	}
	
		
}










