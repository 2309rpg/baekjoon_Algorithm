import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 효율적인 해킹
	 
	 어느 회사의 컴퓨터를 해킹하려고 한다. 이 회사에는 N개의 컴퓨터가 존재한다.
	 A 컴퓨터가 B 컴퓨터를 신뢰할 때, B 컴퓨터를 해킹하면 A 컴퓨터 또한 해킹 할 수 있다.
	 
	 한번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력
	 이 때, 다수라면 오름차순으로 컴퓨터의 번호를 출력
	 
	 양방향이 아닌 단방향 신뢰라는 점
	 BFS를 통해 풀이해야 할 것으로 보임
	 
	 */

	static int comCount;		// 컴퓨터 개수
	static int adjacencyCount;	// 관계 개수
	static int[] visitArray;	// 방문 리스트
	static int[] maxDepthArray;	// 최대깊이 리스트
	static ArrayList<ArrayList<Integer>> adjacencyList;	// 인접도시 리스트

	public static void main(String[] args) throws Exception {

		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
//		String[] consoleInput = br.readLine().split(" ");
		comCount = Integer.parseInt(st.nextToken());
		adjacencyCount = Integer.parseInt(st.nextToken());
		
		// 방문 리스트 초기화 (바로 초기화 하는게 아니면 null 들어감)
		visitArray = new int[comCount + 1];	// 인덱스 번호와 도시 번호를 맞추기 위해 + 1
		Arrays.fill(visitArray, 0);
//		for (int i = 0; i < visitArray.length; i++) {
//			visitArray[i] = 0;
//		}
		
		// 최대깊이 리스트 초기화 (바로 초기화 하는게 아니면 null 들어감)
		maxDepthArray = new int[comCount + 1];	// 인덱스 번호와 도시 번호를 맞추기 위해 + 1
		Arrays.fill(maxDepthArray, 0);
//		for (int i = 0; i < maxDepthArray.length; i++) {
//			maxDepthArray[i] = 0;
//		}
		
		// 인접노드 초기화 및 입력
		adjacencyList = new ArrayList<>();
		for (int i = 0; i < comCount + 1; i++) {
			adjacencyList.add(new ArrayList<>());
		}
		for (int i = 0; i < adjacencyCount; i++) {
//			consoleInput = br.readLine().split(" ");
			st = new StringTokenizer(br.readLine());
			
			int headCity = Integer.parseInt(st.nextToken());	// head tail이 반대
			int tailCity = Integer.parseInt(st.nextToken());
			adjacencyList.get(headCity).add(tailCity);
			//adjacencyList.get(tailCity).add(headCity);
		}
		
		// bfs 탐색
		for (int i = 1; i <= comCount; i++) {
			bfs(i);	// 각 컴퓨터별로 최대길이 탐색
			// 방문리스트 초기화
			Arrays.fill(visitArray, 0);
		}
		
		// 최대값 찾기
		int maxValue = Integer.MIN_VALUE;
		for (int i = 0; i < maxDepthArray.length; i++) {
			maxValue = Math.max(maxValue, maxDepthArray[i]);
//			if (maxDepthArray[i] > maxValue) {
//				maxValue = maxDepthArray[i];
//			}
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < maxDepthArray.length; i++) {
			if (maxDepthArray[i] == maxValue) {
				sb.append(i + " ");
				//System.out.printf("%d ",i);
			}
		}
		
		System.out.println(sb.toString());
		
	}
	
	public static void bfs(int startComNum) {
		// 큐 선언
		Queue<Integer> queue = new LinkedList<>();
		visitArray[startComNum] = 1;
		//int stack = 0;
		
		queue.add(startComNum);
		
		// 큐가 빌때까지 반복
		while (queue.size() > 0) {
			int nowComNum = queue.poll();
			// 현재 컴퓨터 기준 인접 컴퓨터 처리
			for (int i = 0; i <adjacencyList.get(nowComNum).size(); i++) {
				int adjacencyCity = adjacencyList.get(nowComNum).get(i);
				// 방문하지 않았으면서, 시작 도시가 아닌경우
				if (visitArray[adjacencyCity] == 0) {
					queue.add(adjacencyCity);
					visitArray[adjacencyCity] = 1;
					//stack += 1;
					maxDepthArray[adjacencyCity]++;
				}
				
			}
		}
		
		//maxDepthArray[startComNum] = stack;
		
	}

}
