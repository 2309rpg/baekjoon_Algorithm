import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 데이터의 개수, 데이터
	 찾을 숫자 개수, 찾을 숫자
	 
	 입력 받아서 찾는 알고리즘 구현
	 단, 하나하나 직접 찾는 단순 반복문으로는 시간내 해결 불가
	 
	 이진탐색 써보기 튜토리얼 문제라고 생각함
	 */

	static int dataCount;				// 데이터개수
	static ArrayList<Integer> dataList = new ArrayList<>();	// 데이터배열
	static int targetCount;				// 찾을 횟수
	static ArrayList<Integer> targetList = new ArrayList<>();	// 타겟 배열
	
	static int headIndex;
	static int tailIndex;
	

	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		dataCount = Integer.parseInt(br.readLine());
		
		String[] consoleInput = br.readLine().split(" ");
		for (int i = 0; i < consoleInput.length; i++) {
			dataList.add(Integer.parseInt(consoleInput[i]));
		}
		
		targetCount = Integer.parseInt(br.readLine());
		
		consoleInput = br.readLine().split(" ");
		for (int i = 0; i < consoleInput.length; i++) {
			targetList.add(Integer.parseInt(consoleInput[i]));
		}
		
		Collections.sort(dataList);
		
		for (int i = 0; i < targetCount; i++) {
			headIndex = 0;
			tailIndex = dataList.size() - 1;
            if (binarySearch(targetList.get(i))) {
                System.out.println(1);
            }else {
                System.out.println(0);
            }
		}
		
	}
	
	public static boolean binarySearch(int targetNum) {

		while (headIndex <= tailIndex) {
			int midIndex = (headIndex + tailIndex) / 2;
			if (dataList.get(midIndex) == targetNum) {
				return true;
			}else if (dataList.get(midIndex) > targetNum) {
				tailIndex = midIndex - 1;
			}else if (dataList.get(midIndex) < targetNum) {
				headIndex = midIndex + 1;
			}
		}
		
		// while 안에서 종료되지 않았다면 없는 것
		return false;
		
	}

}
