package a0612;

import java.util.ArrayList;
import java.util.Collections;

public class Main_리틀프렌즈사천성_미완성 {

	public static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
	public static boolean[][] visit;
	public static void main(String[] args) {
		System.out.println(solution(3, 3, new String[] {"DBA", "C*A", "CDB"}));
		System.out.println(solution(2, 4, new String[] {"NRYN", "ARYA"}));
		System.out.println(solution(4, 4, new String[] {".ZI.", "M.**", "MZU.", ".IU."}));
		System.out.println(solution(2, 2, new String[] {"AB", "BA"}));
	}
	public static String solution(int m, int n, String[] board) {
        String answer = "";
        /*
  		DBA
  		C*A
  		CDB
         */
        ArrayList<String> arr = new ArrayList<>(); // 알파벳 순 문자열 리턴 용
        String[][] map = new String[m][n];
        for(int i=0; i<m; i++) {
        	for(int j=0; j<n; j++) {
        		map[i][j] = board[i].charAt(j) + "";
        	}
        }
        //문자를 모두 제거할 수 있는지 for문을 돌며 삭제. 중간 break
        for(int i=0; i<m; i++) {
        	for(int j=0; j<n; j++) {
        		visit = new boolean[m][n];
        		search(m, n, i, j, map, 0, map[i][j], -1);
        	}
        }
        
        
        
        Collections.sort(arr);
//        answer = arr.get(0);
        return answer;
    }
	public static void search(int m, int n, int x, int y, String[][] map, int moveCnt, String tmp, int direction) {
		//빈공간이면 움직일 수 있는 만큼 보낸다.
		visit[x][y] = true;
		for(int k=0; k<4; k++) {
			if(Math.abs(direction - k) == 2) continue; // 방향이 정반대면 안됨 0:2 1:3
			if(moveCnt == 2) {
				if(direction != k) continue;
			}
			int nx = x + dx[k];
			int ny = y + dy[k];
			if(0<=nx&&nx<m&&0<=ny&&ny<n&&!visit[nx][ny]) {
				if (tmp.equals("")) { //빈 거면
//					search(m, n, nx, ny, )
				} else { // 안비었으면 단어가 같아야 pop하고 서치
					
				}
			}
			if(tmp == "") {
			}
		}
		
	}

}
