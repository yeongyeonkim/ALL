package a0530;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main_2022_KAKAO_신고결과받기 {

	public static void main(String[] args) {
		String[] id_list = { "muzi", "frodo", "apeach", "neo" };
		String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
		int k = 2;
		System.out.println(solution(id_list, report, k));
		id_list = new String[] { "con", "ryan" };
		report = new String[] { "ryan con", "ryan con", "ryan con", "ryan con" };
		k = 2;
		System.out.println(solution(id_list, report, k));
	}

	public static int[] solution(String[] id_list, String[] report, int k) {
		int n = id_list.length;
		int[] answer = new int[n];
		Map<String, HashSet<String>> map = new HashMap<>();
		Map<String, Integer> idxMap = new HashMap<>();
		for(int i=0; i<id_list.length; i++) {
			String user = id_list[i];
			map.put(user,  new HashSet<>());
			idxMap.put(user, i);
		}
		for (String s : report) {
			String[] str = s.split(" ");
			map.get(str[1]).add(str[0]);
		}
		for (int i=0; i<n; i++) {
			HashSet<String> reportSet = map.get(id_list[i]);
			if (reportSet.size() >= k) {
				for (String name : reportSet) {
					answer[idxMap.get(name)]++;
				}
			}
		}

		return answer;
	}
}
