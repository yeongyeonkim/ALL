package a0830;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main_7662 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			int k = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> tree = new TreeMap<>();
			for (int i = 0; i < k; i++) {
				String[] input = br.readLine().split(" ");
				char operator = input[0].charAt(0);
				int num = Integer.parseInt(input[1]);
				if (operator == 'I') {
					// getOrDefault(Object key, V DefaultValue)
					// 찾는 key가 존재하면 값을 반환하고, 그렇지 않으면 default값
					tree.put(num, tree.getOrDefault(num, 0) + 1);
				} else {
					if (tree.size() == 0)
						continue;
					// 1이면 최댓값, -1이면 최솟값
					int tmp = num == 1 ? tree.lastKey() : tree.firstKey();
					if (tree.get(tmp) == 0) {
						// 이해 안가는 부분 put을 했을떄 0이거나 null이면 1이 되는건가?
//						if (tree.put(num, tree.get(num) - 1) == 1)
//							tree.remove(num);
					}

				}
			}
			System.out.println(tree.size() == 0 ? "EMPTY" : tree.lastKey() + " " + tree.firstKey());
		}
	}

}
