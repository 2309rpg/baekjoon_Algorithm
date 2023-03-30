import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 특정 거리의 도시 찾기
	 
	 그래프 챕터로 앞에서 풀어봤던 DFS, BFS를 적용해서 풀이하는 단원으로 보임
	 
	 특정 도시에서 타 도시까지의 최단거리가 '지정한' 거리인 경우를 출력하면 됨
	 */
	
	static int cityCount;		// 도시 개수
	static int roadCount;		// 도로 개수
	static int distanceLength;	// 도시간 거리
	static int startCityNum;	// 시작 도시
	static Long[] visitArray;	// 방문 리스트
	static ArrayList<ArrayList<Integer>> adjacencyList;	// 인접도시 리스트

	public static void main(String[] args) throws Exception {

		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] consoleInput = br.readLine().split(" ");
		cityCount = Integer.parseInt(consoleInput[0]);
		roadCount = Integer.parseInt(consoleInput[1]);
		distanceLength = Integer.parseInt(consoleInput[2]);
		startCityNum = Integer.parseInt(consoleInput[3]);
		
		// 방문노드 초기화 (바로 초기화 하는게 아니면 null 들어감)
		visitArray = new Long[cityCount + 1];	// 인덱스 번호와 도시 번호를 맞추기 위해 + 1
		for (int i = 0; i < visitArray.length; i++) {
			visitArray[i] = (long) 0;
		}
		
		// 인접노드 초기화 및 입력
		adjacencyList = new ArrayList<>();
		for (int i = 0; i < cityCount + 1; i++) {
			adjacencyList.add(new ArrayList<>());
		}
		for (int i = 0; i < roadCount; i++) {
			consoleInput = br.readLine().split(" ");
			int headCity = Integer.parseInt(consoleInput[0]);
			int tailCity = Integer.parseInt(consoleInput[1]);
			adjacencyList.get(headCity).add(tailCity);
		}
		
		// bfs 탐색
		bfs(startCityNum);
		
		// 출력
		boolean flag = false;	// 만족 노드 출력 여부
		for (int i = 1; i < visitArray.length; i++) {
			if (visitArray[i] == distanceLength) {
				System.out.println(i);
				flag = true;
			}
		}
		
		// 출력 시 flag가 true로 바뀌지 않았다면 한번도 출력되지 않은 것
		if (flag == false) {
			System.out.println(-1);
		}
		
	}
	
	public static void bfs(int cityNum) {
		// 큐 선언
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(cityNum);
		
		// 큐가 빌때까지 반복
		while (queue.size() > 0) {
			int nowCity = queue.poll();
			// 현재 도시 기준 인접 도시 처리
			for (int i = 0; i <adjacencyList.get(nowCity).size(); i++) {
				int adjacencyCity = adjacencyList.get(nowCity).get(i);
				// 방문하지 않았으면서, 시작 도시가 아닌경우
				if (visitArray[adjacencyCity] == 0 && adjacencyCity != startCityNum) {
					queue.add(adjacencyCity);
					visitArray[adjacencyCity] += 1 + visitArray[nowCity];
				}
				
			}
		}
		
	}

}
