import java.util.*;

class Solution {
    static Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
	static StringBuilder sb;
	
	public int[] solution(String[] info, String[] query) {
		for(String volun : info){
			sb = new StringBuilder();
            String[] categorys = volun.split(" ");
            
            for (int i = 0; i < (1 << 4); i++) {
				sb = new StringBuilder();
				for (int j = 0; j < 4; j++) {
					if((i & (1 << j)) > 0) sb.append(categorys[j]);
				}
				map.computeIfAbsent(sb.toString(), s -> new ArrayList<>()).add(Integer.parseInt(categorys[4]));
			}
        }
		
		System.out.println();
		
		for(Map.Entry<String, List<Integer>> entry : map.entrySet()) {
			entry.getValue().sort(null);
		}
		
		int[] ans = new int[query.length];
		List<Integer> empty = new ArrayList<>();
		
		for (int i = 0; i < query.length; i++) {
			String[] split = query[i].replaceAll("-", "").split(" and | ");
			String key = String.join("", split[0], split[1], split[2], split[3]);
	        int score = Integer.parseInt(split[4]);
	        
	        List<Integer> list = map.getOrDefault(key, empty);
	        
	        int s = 0, e = list.size();

            while (s < e) {
                int mid = (s + e) / 2;

                if (list.get(mid) < score) s = mid + 1;
                else e = mid;
            }

	        ans[i] = list.size() - s;
		}
		
		return ans;
	}
}