package a0614;

public class Main_보행자천국_시간초과_DFS {
	/*
	 * 0 자유롭게
	 * 1 통행 금지
	 * 2 좌회전이나 우회전이 금지. (왼쪽에서 오던 차는 오른쪽으로만, 위에서 오던 차는 아래쪽으로만)
	 */
	public static int cnt;
	public static int[] dx = {0,1};
	public static int[] dy = {1,0};
	public static boolean[][] visit;
	public static void main(String[] args) {
		System.out.println(solution(3, 3, new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } }));
		System.out.println(
				solution(3, 6, new int[][] { { 0, 2, 0, 0, 0, 2 }, { 0, 0, 2, 0, 1, 0 }, { 1, 0, 0, 2, 2, 0 } }));
	}
	public static int solution(int m, int n, int[][] cityMap) {
		cnt = 0;
		visit = new boolean[m][n];
		search(0, 0, m, n, cityMap, -1);
		return cnt;
	}
	public static void search(int x, int y, int m, int n, int[][] map, int direction) {
		if(x == m-1 && y == n-1) {
			cnt++;
			if(cnt == 20170805) cnt = 0;
			return;
		}
		for(int i=0; i<2; i++) {
			if(map[x][y] == 2 &&(i != direction)) {
				continue;
			}
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(0<=nx&&nx<m&&0<=ny&&ny<n&&map[nx][ny]!=1&&!visit[nx][ny]) {
				visit[x][y] = true;
				search(nx, ny, m, n, map, i);
				visit[x][y] = false;
			}
		}
	}
}
