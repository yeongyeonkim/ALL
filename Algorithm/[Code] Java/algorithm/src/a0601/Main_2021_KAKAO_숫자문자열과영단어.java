package a0601;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main_2021_KAKAO_숫자문자열과영단어 {

	public static void main(String[] args) {
		System.out.println(solution("one4seveneight"));
		System.out.println(solution("23four5six7"));
		System.out.println(solution("2three45sixseven"));
		System.out.println(solution("123"));
	}
    public static int solution(String s) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> map = new HashMap<>();
        map = createMap(map);
        for(int i=0; i<s.length();) {
        	char c = s.charAt(i);
        	if(c >= '9') {
        		int idx = 3;
        		while(idx <= 5) {
        			String tmp = s.substring(i, i+idx);
        			if (Objects.isNull(map.get(tmp))) {
        				idx++;
        				continue;
        			} else {
        				sb.append(map.get(tmp));
        				i += idx;
        				break;
        			}
        		}
        	} else {
        		sb.append(c);
        		i++;
        	}
        }
        answer = Integer.parseInt(sb.toString());
        return answer;
    }
    public static Map<String, Integer> createMap(Map<String, Integer> map) {
    	map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
    	return map;
    }
}
