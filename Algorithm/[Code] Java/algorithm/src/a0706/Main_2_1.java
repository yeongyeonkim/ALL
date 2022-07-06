package a0706;

public class Main_2_1 {

	public static void main(String[] args) {
		System.out.println(solution(4, 3, new int[][] { { 2, 2 } }));
	}
    public static int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n+1][m+1];
        for(int[] puddle : puddles) {
        	map[puddle[0]][puddle[1]] = -1;
        }
        map[1][1] = 1;
        for(int i=1; i<=n; i++) {
        	for(int j=1; j<=m; j++) {
        		if(map[i][j] == -1) {
        			map[i][j] = 0;
        			continue;
        		}
        		// 연산마다 나눗셈하는 것이 싫어 long으로 변환하였으나 오히려 효율성이 떨어졌다.
        		if(i != 1) map[i][j] += map[i-1][j] % 1000000007; 
        		if(j != 1) map[i][j] += map[i][j-1] % 1000000007;
        	}
        }
        return map[n][m] % 1000000007;
    }
}
