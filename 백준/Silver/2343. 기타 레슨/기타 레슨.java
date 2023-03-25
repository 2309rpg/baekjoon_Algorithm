import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 레슨 개수, 블루레이 개수, 레슨 시간 배열
	 */

	static int lessonCount;				// 레슨 개수
	static int bluelayCount;			// 블루레이 개수
	static ArrayList<Integer> lessonList = new ArrayList<>();	// 레슨 배열
	
	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] consoleInput = br.readLine().split(" ");
		lessonCount = Integer.parseInt(consoleInput[0]);
		bluelayCount = Integer.parseInt(consoleInput[1]);

		consoleInput = br.readLine().split(" ");
		for (int i = 0; i < consoleInput.length; i++) {
			lessonList.add(Integer.parseInt(consoleInput[i]));
		}
		
		int start = Collections.max(lessonList);	// 가장 긴 레슨 시간
		int end = lessonList.stream().mapToInt(Integer::intValue).sum();	// 모든 레슨 시간 합
		int result = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;	// 블루레이 크기의 최소값
			if(bluelayCount >= check(mid)) {	// 블루레이 개수가 m개 이하인 경우 -> 블루레이 크기 줄임
				result = mid;
				end = mid - 1;
			} else {	// 블루레이 개수가 m개보다 많은 경우 -> 블루레이 크기 늘림
				start = mid + 1;
			}
		}
		
		System.out.println(result);

	}
	
	public static int check(int mid) {
		int cnt = 1;	// 블루레이 개수
		int sum = 0;	// 블루레이에 녹화된 레슨 시간
		for (int i = 0; i < lessonCount; i++) {
			if(sum + lessonList.get(i) > mid) {	// 블루레이 크기를 초과하는 경우
				cnt++;
				sum = 0;
			}
			sum += lessonList.get(i);
		}
		return cnt;
	}

}