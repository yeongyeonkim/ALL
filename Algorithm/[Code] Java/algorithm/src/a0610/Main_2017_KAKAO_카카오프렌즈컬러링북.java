package a0610;

public class Main_2017_KAKAO_카카오프렌즈컬러링북 {

	public static int numberOfArea, maxSizeOfOneArea, tmpSizeOfOneArea;
	public static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
	public static boolean[][] visit;
	public static void main(String[] args) {
		System.out.println(solution(6, 4, new int[][] {
			{1, 1, 1, 0}, 
			{1, 2, 2, 0}, 
			{1, 0, 0, 1}, 
			{0, 0, 0, 1}, 
			{0, 0, 0, 3}, 
			{0, 0, 0, 3}}
		)); // 136 - 16 = 120, 전형적인 BFS 문제로 보임
	}
	public static int[] solution(int m, int n, int[][] picture) {
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        visit = new boolean[m][n];
        for(int i=0; i<m; i++) {
        	for(int j=0; j<n; j++) {
        		if(picture[i][j] != 0 && !visit[i][j]) {
        			tmpSizeOfOneArea = 0;
        			numberOfArea++;
        			search(i, j, m, n, picture[i][j], picture);
        			maxSizeOfOneArea = tmpSizeOfOneArea > maxSizeOfOneArea ? tmpSizeOfOneArea : maxSizeOfOneArea;
        		}
        	}
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
	public static void search(int x, int y, int m, int n, int num, int[][] map) {
		// 일단 방문 했으면 visit하고 1인지 2인지 체크
		visit[x][y] = true;
		tmpSizeOfOneArea++;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(0<=nx&&nx<m&&0<=ny&&ny<n&&!visit[nx][ny]&&map[nx][ny]==num) { 
				search(nx, ny, m, n, num, map);
			}
		}
	}
}
