import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 그래프를 DFS, BFS로 탐색한 결과 출력
	 
	 단, 방문할수 있는 노드가 여러 개인 경우 노드 번호가 작은것부터 방문 (중요해보임)
	 더 이상 방문할 수 있는 노드가 없을 때 종료
	 (방문 후 시작 노드로부터 절대 닿지않는 단독 노드, 혹은 모든 노드 방문 시 종료)
	 
	 노드 번호는 1 부터 N 까지 (1 <= N <= 1000)
	 에지의 개수 M (1 <= N <= 10000)
	 탐색을 시작할 노드의 번호 V
	 
	 1번째 줄 DFS 실행결과, 2번째 줄 BFS 실행결과 출력
	 
	 단순히 하나의 그래프가 있을 때 DFS와 BFS를 실행해보고 비교해 개념을 잡기 위한 단순한 문제로 보임
	 DFS는 스택, 재귀를 이용해서 풀이가능 / BFS는 큐 이용가능 재귀 불가
	 
	 */

	static int nodeCount;		// 노드 개수
	static int edgeCount;		// 에지 개수
	static int startPoint;		// 시작 지점
	static ArrayList<Integer>[] adjacencyList;	// 인접관계배열
	static int[] checkVisit;	// 방문체크

	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] firstLineInput = br.readLine().split(" ");
		nodeCount = Integer.parseInt(firstLineInput[0]);
		edgeCount = Integer.parseInt(firstLineInput[1]);
		startPoint = Integer.parseInt(firstLineInput[2]);
		
		// 인접리스트 생성 초기화
		adjacencyList = new ArrayList[nodeCount + 1];
		for (int i = 1; i <= nodeCount; i++) {
			adjacencyList[i] = new ArrayList<Integer>();
		}
		
		// 관계 입력
		for (int i = 1; i <= edgeCount; i++) {
			String consoleInput[] = br.readLine().split(" ");
			int head = Integer.parseInt(consoleInput[0]);
			int tail = Integer.parseInt(consoleInput[1]);
			adjacencyList[head].add(tail);
			adjacencyList[tail].add(head);
		}
		
		// 노드번호가 작은것부터 방문해야 하므로 관계 정렬
		for (int i = 1; i < adjacencyList.length; i++) {
			Collections.sort(adjacencyList[i]);
		}
		
		// 방문 배열 초기화 및 dfs 탐색
		checkVisit = new int[nodeCount + 1];
		dfs(startPoint);
		
		// 줄 바꾸기
		System.out.println();
		
		// 방문 배열 초기화 및 bfs 탐색
		checkVisit = new int[nodeCount + 1];
		bfs(startPoint);
		

	}
	
	public static void dfs(int startPoint) {
		// 방문 처리 및 출력
		checkVisit[startPoint] = 1;
		System.out.printf("%d ",startPoint);
		
		// 인접 노드 재귀 처리
		for (int i = 0; i < adjacencyList[startPoint].size(); i++) {
			if (checkVisit[adjacencyList[startPoint].get(i)] == 0) {
				dfs(adjacencyList[startPoint].get(i));
			}
		}
	}
	
	public static void bfs(int startPoint) {
		// bfs는 큐로 풀어야하므로 큐 선언
		Queue<Integer> queue = new LinkedList<>();
		
		// 시작 지점 방문 및 입력
		queue.add(startPoint);
		checkVisit[startPoint] = 1;
		
		// 큐가 빌때까지 반복
		while (queue.size() != 0) {
			int nowPoint = queue.poll();
			System.out.printf("%d ",nowPoint);
			
			// 인접 노드 방문여부 확인 후 큐 입력(입력 시 방문처리)
			for (int i = 0; i < adjacencyList[nowPoint].size(); i++) {
				if (checkVisit[adjacencyList[nowPoint].get(i)] == 0) {
					queue.add(adjacencyList[nowPoint].get(i));
					checkVisit[adjacencyList[nowPoint].get(i)] = 1;
				}
			}
		}
		
		
	}

}
