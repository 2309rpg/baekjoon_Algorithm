import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        
        Map<String, Integer> map = new HashMap<>();
        
        // 해시맵 입력
        for (int i = 0; i < players.length; i++) {
            map.put(players[i],i);
        }
        
        // 로직
        for (int i = 0; i < callings.length; i++) {
            String nowName = callings[i];
            int nowIndex = map.get(nowName);
            map.put(nowName, nowIndex - 1);
            map.put(players[nowIndex - 1], nowIndex);
            players[nowIndex] = players[nowIndex - 1];
            players[nowIndex - 1] = nowName;
        }
        
        
        
        return players;
    }
}