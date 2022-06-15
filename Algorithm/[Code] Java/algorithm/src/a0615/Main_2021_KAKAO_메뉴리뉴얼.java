package a0615;

import java.util.*;

public class Main_2021_KAKAO_메뉴리뉴얼 {

	public static ArrayList<String> result;
	public static String[] arr;
	public static Map<String, Integer> map;
	public static int length, max;
	public static void main(String[] args) {
		System.out.println(solution(
				new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, 
				new int[] {2, 3, 4})
				);
		System.out.println(solution(
				new String[] {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, 
				new int[] {2, 3, 5})
				);
		System.out.println(solution(
				new String[] {"XYZ", "XWY", "WXA"},
				new int[] {2, 3, 4})
				);
	}
	public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        result = new ArrayList<>();
        for(int k=0; k<course.length; k++) {
        	map = new HashMap<>();
        	length = course[k]; // 2~
        	max = 0;
        	arr = new String[length];
        	for(int i=0; i<orders.length; i++) {
        		// 조합 Combination
        		combination(0, 0, orders[i]);
        	}
        	for(String key : map.keySet()) {
        		if(map.get(key) == max && map.get(key) >= 2) {
        			result.add(key);
        		}
        	}
        }
        Collections.sort(result); // 배열 오름차순
        answer = new String[result.size()];
        for(int i=0; i<result.size(); i++) {
        	answer[i] = result.get(i);
        }
        return answer;
    }
	public static void combination(int start, int cnt, String order) {
		if(cnt == length) {
			String[] tmp = new String[arr.length];
			for(int i=0; i<tmp.length; i++) {
				tmp[i] = arr[i]; // 이것을 해줒 ㅣ않으면 깊은 복사로 인해 이후 값들이 영향받기 때문에
			}
			Arrays.sort(tmp);
			StringBuilder sb = new StringBuilder();
			for(String s: tmp) {
				sb.append(s);
			}
			String str = sb.toString();
			if (Objects.isNull(map.get(sb.toString()))) {
				map.put(str, 1);
				changeMax(1);
			} else {
				int num = map.get(str);
				map.put(str, num + 1);
				changeMax(num + 1);
			}
		} else {
			for(int i=start; i<order.length(); i++) {
				arr[cnt] = order.charAt(i) + "";
				combination(i+1, cnt+1, order);
			}
		}
	}
	public static void changeMax(int n) {
		max = n > max ? n : max;
	}
}
