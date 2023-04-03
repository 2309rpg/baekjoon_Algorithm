import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 여행 가자
	 
	 유니온 파인드를 문제에 실전적으로 적용해보는 문제인 듯
	 
	 
	 */
	
	static int cityCount;
	static int routeDataCount;
	static int[][] routeDataArray;
	static int[] indexArray;
	static int[] tripRoute;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		cityCount = Integer.parseInt(br.readLine());
		routeDataCount = Integer.parseInt(br.readLine());
		
		// 원소 개수만큼 배열 할당 및 값 할당
		indexArray = new int[cityCount + 1];
		for (int i = 0; i < indexArray.length; i++) {
			indexArray[i] = i;
		}
		
		// 경로 데이터 배열 초기화
		routeDataArray = new int[cityCount + 1][cityCount + 1];
		
		// 경로 데이터 입력
		for (int i = 1; i <= cityCount; i++) {
			String[] consoleInput = br.readLine().split(" ");
			for (int j = 1; j <= cityCount; j++) {
				routeDataArray[i][j] = Integer.parseInt(consoleInput[j - 1]);
			}
		}
		
		// 여행 경로 입력
		tripRoute = new int[routeDataCount + 1];
		String[] consoleInput = br.readLine().split(" ");
		for (int i = 0; i < consoleInput.length; i++) {
			tripRoute[i + 1] = Integer.parseInt(consoleInput[i]);
		}
		
		// 경로 합산처리
		for (int i = 1; i <= cityCount; i++) {
			for (int j = 1; j <= cityCount; j++) {
				if (routeDataArray[i][j] == 1) {
					sumOper(i, j);
				}
			}
		}
		
		// 여행할 도시들이 연결되어있는지 확인
		// 첫 도시 지정
		int firstCity = headOper(tripRoute[1]);
		for (int i = 2; i < tripRoute.length; i++) {
			// 이어지지 않은 경우가 하나라도 있는경우 NO 출력 후 종료
			if (firstCity != headOper(tripRoute[i])) {
				System.out.println("NO");
				System.exit(0);
			}
		}
		
		// 종료되지 않았다면 모두 이어진 것
		System.out.println("YES");

	}
	
	// 합 연산처리 시 대표 값이 다르다면 합 연산
	public static void sumOper(int head, int tail) {
		head = headOper(head);
		tail = headOper(tail);
		if (head != tail) {
			indexArray[tail] = head;
		}
	}
	
	// 해당 값의 대표 값 찾으면서 재귀 처리
	public static int headOper(int city) {
		if (city == indexArray[city]) {
			return city;
		}else {
			return indexArray[city] = headOper(indexArray[city]);
		}
	}

}
