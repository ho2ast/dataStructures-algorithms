package algorithms.programmers.highscorekit.hash;

import java.util.*;
import java.util.stream.Collectors;

public class BestAlbum {
  /*
  문제 설명
  스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다.
  노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
  
  속한 노래가 많이 재생된 장르를 먼저 수록합니다.
  장르 내에서 많이 재생된 노래를 먼저 수록합니다.
  장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
  노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때,
  베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
  
  제한사항
  genres[i]는 고유번호가 i인 노래의 장르입니다.
  plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
  genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
  장르 종류는 100개 미만입니다.
  장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
  모든 장르는 재생된 횟수가 다릅니다.
  
  입출력 예
  genres                                           plays                       return
  ["classic", "pop", "classic", "classic", "pop"]  [500, 600, 150, 800, 2500]  [4, 1, 3, 0]
   */

    public int[] solution(String[] genres, int[] plays) {
        List<Integer> result = new ArrayList<>();
        
        List<String> genresList = Arrays.asList(genres);
        
        Map<String, List<Map<String, Object>>> genreMap = new HashMap<>();
        Map<String, Integer> genreSum = new HashMap<>();
        
        for(int i = 0; i < genresList.size(); i++) {
            if (genreMap.containsKey(genresList.get(i))) {
                genreMap.get(genresList.get(i)).add(Map.of("index", i, "play", plays[i]));
                genreSum.put(genresList.get(i), genreSum.get(genresList.get(i))+plays[i]);
            } else {
                List<Map<String, Object>> list = new ArrayList<>();
                list.add(Map.of("index", i, "play", plays[i]));
                genreMap.put(genresList.get(i), list);
                genreSum.put(genresList.get(i), plays[i]);
            }
        }
        
        List<Map.Entry<String, Integer>> entries =
                genreSum.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toList());
        
        for(Map.Entry<String, Integer> map : entries) {
            genreMap.get(map.getKey()).sort(
	            Comparator.comparing((Map<String, Object> tempMap) -> (Integer) tempMap.get("play")).reversed());
            
            for(int i = 0; i < genreMap.get(map.getKey()).size(); i++) {
                if (i == 2) {
                    break;
                } else {
                    result.add((Integer)genreMap.get(map.getKey()).get(i).get("index"));
                }
            }
        }
        
        int[] answer = result.stream().mapToInt(i->i).toArray();;
        
        return answer;
    }
}
