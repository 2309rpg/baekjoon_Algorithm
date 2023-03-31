import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 이분 그래프
	 
	 그래프가 있을 때 그래프의 정점의 집합을 둘로 분할하여, 
	 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있는 경우를
	 이분 그래프라 한다.
	 
	 즉 빨강과 파랑이 있다고 할때,
	 빨강에서 시작해서 파랑, 파랑 건너서 빨강 이런식으로 계속해서 교차해야 함
	 교차하지 못할 경우 이분 그래프가 아님
	 
	 테스트 케이스 개수
	 각 테스트 케이스 별 노드와 에지 개수가 주어지고,
	 에지 개수만큼 관계가 주어짐
	 
	 이분 그래프는 방향성이 없는거니까 양쪽 노드에 모두 방향을 적용하는게 맞지 않나? 하는생각
	 */
	
	static int testCaseCount;		// 테스트 케이스 개수
	static int nodeCount;			// 노드 개수
	static int edgeCount;			// 에지 개수
	static int[] visitArray;		// 방문 리스트
	static int[] bipartiteArray;	// 이분 리스트
	static ArrayList<ArrayList<Integer>> adjacencyList;	// 인접노드 리스트
	static boolean errorCheck = false;	// 에러체크

	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		testCaseCount = Integer.parseInt(br.readLine());
		
		// 테스트 케이스 수만큼 반복
		for (int testCase = 1; testCase <= testCaseCount; testCase++) {
			
			String[] consoleInput = br.readLine().split(" ");
			nodeCount = Integer.parseInt(consoleInput[0]);
			edgeCount = Integer.parseInt(consoleInput[1]);
			
			// 방문 리스트 초기화 (바로 초기화 하는게 아니면 null 들어감)
			visitArray = new int[nodeCount + 1];
			for (int i = 0; i < visitArray.length; i++) {
				visitArray[i] = 0;
			}
			
			// 이분 리스트 초기화
			bipartiteArray = new int[nodeCount + 1];
			for (int i = 0; i < bipartiteArray.length; i++) {
				bipartiteArray[i] = 0;
			}
			
			// 인접노드 초기화 및 입력
			adjacencyList = new ArrayList<>();
			for (int i = 0; i < nodeCount + 1; i++) {
				adjacencyList.add(new ArrayList<>());
			}
			for (int i = 0; i < edgeCount; i++) {
				consoleInput = br.readLine().split(" ");
				int headNode = Integer.parseInt(consoleInput[0]);
				int tailNode = Integer.parseInt(consoleInput[1]);
				adjacencyList.get(headNode).add(tailNode);
				adjacencyList.get(tailNode).add(headNode);
			}
			
			// dfs 탐색,
			for (int i = 1; i <= nodeCount; i++) {
				if (errorCheck) {
					break;
				}else {
					dfs(i);
				}
				
			}
			
			// 에러가 발견됐다면
			if (errorCheck) {
				System.out.println("NO");
			}else {
				System.out.println("YES");
			}
			// 에러여부 초기화
			errorCheck = false;
			
		}
		
	}
	
	public static void dfs(int nowNode) {
		// 현재 노드 방문 처리
		visitArray[nowNode] = 1;

		for (int i = 0; i < adjacencyList.get(nowNode).size(); i++) {
			// 인접노드
			int adjacencyNode = adjacencyList.get(nowNode).get(i);
			
			// 방문 여부 체크
			if (visitArray[adjacencyNode] != 1) {	
				
				// 이분 처리
				bipartiteArray[adjacencyNode] = (bipartiteArray[nowNode] + 1) % 2;
				// 재귀반복처리
				dfs(adjacencyNode);
				
			// 이미 방문했다면 현재 노드와 인접노드가 이분 관계인지
			}else if (bipartiteArray[nowNode] == bipartiteArray[adjacencyNode]) {
				errorCheck = true;
			}
		}
		
	}

}
