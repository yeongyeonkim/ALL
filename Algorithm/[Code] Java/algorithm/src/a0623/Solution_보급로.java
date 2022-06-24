package a0623;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution_보급로 {
	static class Node implements Comparable<Node>{
        int x;
        int y;
        int sum;
        public Node(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }
        @Override
        public int compareTo(Node o) {
            return this.sum - o.sum;
        }
         
    }
    static int[][] map;
    static boolean[][] visit;
    static int n, result;
    static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
    static PriorityQueue<Node> q;
     
    public static void move() {
        q = new PriorityQueue<Node>();
        q.add(new Node(0,0,map[0][0]));
        visit[0][0] = true;
        while(!q.isEmpty()) {
            Node temp = q.poll();
            System.out.println(temp.x + " : " + temp.y + " : " + temp.sum);
            for(int i=0; i<4; i++) {
                int nx = temp.x + dx[i];
                int ny = temp.y + dy[i];
                if(0<=nx&&nx<n&&0<=ny&&ny<n&&!visit[nx][ny]) {
                    if(nx == n-1 && ny == n-1) {
                        result = temp.sum;
                        return;
                    }
                    q.add(new Node(nx, ny, map[nx][ny] + temp.sum));
                    visit[nx][ny] = true;
                }
            }
        }
    }
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for(int t=1; t<=tc; t++) {
            result = 0;
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            visit = new boolean[n][n];
            for(int i=0; i<n; i++) {
                String str = br.readLine();
                for(int j=0; j<n; j++) map[i][j] = str.charAt(j) - '0';
            }
            move();
            System.out.println("#"+t+" "+result);
        }	
	}

}
