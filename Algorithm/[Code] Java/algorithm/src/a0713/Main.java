package a0713;

public class Main {
	public static int min;
	public static int[][] map;

	public static void main(String[] args) {
//		System.out.println(solution(6, 6, new int[][] { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } }));
//		System.out.println(
//				solution(3, 3, new int[][] { { 1, 1, 2, 2 }, { 1, 2, 2, 3 }, { 2, 1, 3, 2 }, { 2, 2, 3, 3 } }));
		System.out.println(solution(100, 97, new int[][] { { 1, 1, 100, 97 } }));
	}

	public static int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		map = new int[rows][columns];
		int cnt = 1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				map[i][j] = cnt++;
			}
		}
		cnt = 0;
		for (int[] query : queries) {
			rotate(query);
			answer[cnt++] = min;
		}
		return answer;
	}

	public static void rotate(int[] query) {
		int l_x = query[0]-1, l_y = query[1]-1, r_x = query[2]-1, r_y = query[3]-1;
		int tmp = map[l_x][l_y];
		min = tmp;
		//서
		for(int j=l_x; j<r_x; j++) {
			map[j][l_y] = map[j+1][l_y];
			min = map[j][l_y] > min ? min : map[j][l_y];
		}
		//남 
		for(int i=l_y; i<r_y; i++) {
			map[r_x][i] = map[r_x][i + 1];
			min = map[r_x][i] > min ? min : map[r_x][i];
		}
		//동 
		for(int j=r_x; j>l_x; j--) {
			map[j][r_y] = map[j - 1][r_y];
			min = map[j][r_y] > min ? min : map[j][r_y];
		}
		//북
		for(int i=r_y; i>l_y; i--) {
			map[l_x][i] = map[l_x][i - 1];
			min = map[l_x][i] > min ? min : map[l_x][i];
		}
		map[l_x][l_y + 1] = tmp;
	}
}
