import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 게임 개발
	 
	 건물을 짓는다고 했을 때 '스타크래프트' 게임의 빌드 개념처럼
	 특정 건물을 지어야만 다음 건물을 짓기 시작할 수 있다.
	 이러한 개념을 적용 해 주어지는 건물이 모두 완성되기까지의 최소시간 출력
	 
	 위상 정렬 알고리즘을 적용해보는 문제가 아닌가 싶음
	 전체적인 로직은 크게 차이가 없을것 같은데, 주어지는 빌드 개념을 어떻게 노드에지 개념으로
	 정리를 깔끔하게 해내는게 키가 아닐까 생각
	 
	 #풀고나서..
	 다 풀고 나니까 각 건물 자체의 빌드시간을 따로 입력 받아서 저장한 경우
	 굳이 가중치를 같이 배열에 저장하지 않더라도
	 해당 건물의 빌드시간을 따로 저장해 뒀기 때문에 문제가 없음
	 */
	
	static int nodeCount;	// 노드 개수
	static ArrayList<ArrayList<Integer>>[] relationArray;	// 관계 배열
	static int[] relationWeight;	// 에지 가중치
	static int[] selfBuildTime;		// 각 건물의 개인 빌드 시간 배열
	static int[] totalBuildTime;	// 각 건물의 누적 빌드 시간 배열
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		nodeCount = Integer.parseInt(br.readLine());
		
		// 연결 가중치 초기화
		relationWeight = new int[nodeCount + 1];
		
		// 각 빌드 시간 초기화
		selfBuildTime = new int[nodeCount + 1];
		
		// 누적 빌드 시간 초기화
		totalBuildTime = new int[nodeCount + 1];
		
		// 관계 배열 초기화
		relationArray = new ArrayList[nodeCount + 1];
		for (int i = 0; i < relationArray.length; i++ ) {
			relationArray[i] = new ArrayList<>();
		}
		
		// 노드 간 연결 입력
		for (int i = 1; i <= nodeCount; i++) {
			String[] consoleInput = br.readLine().split(" ");
			
			// 각 건물 개별 빌드 시간 입력
			selfBuildTime[i] = Integer.parseInt(consoleInput[0]);
			
			// 관계 배열 입력 처리
			for (int j = 1; j < consoleInput.length - 1; j++) {
				int headNode = Integer.parseInt(consoleInput[j]);
				int tailNode = i;
				int value = selfBuildTime[headNode];
				ArrayList temp = new ArrayList<>();
				temp.add(tailNode);
				temp.add(value);
				relationArray[headNode].add(temp);
				relationWeight[tailNode] += 1;	// 에지 가중치 처리
			}

		}
		
		// 최상단 노드들 큐 입력 처리
		for (int i = 1; i < relationWeight.length; i++) {
			if (relationWeight[i] == 0) {
				queue.add(i);
			}
		}
		
		// 큐가 빌때까지 반복
		while (queue.size() > 0) {
			int nowNode = queue.poll();
			relationOper(nowNode);
		}
		
		// 출력
		for (int i = 1; i < totalBuildTime.length; i++) {
			// 각 건물 누적 시간 + 개별 빌드 시간
			System.out.printf("%d\n",totalBuildTime[i] + selfBuildTime[i]);
		}
		
	}
	
	public static void relationOper(int nowNode) {
		for (int i = 0; i < relationArray[nowNode].size(); i++) {
			int nextNode = relationArray[nowNode].get(i).get(0);
			// 건물을 짓는데 누적처리된 시간보다 현재 연산의 시간이 더 긴 경우 치환 처리
			if (selfBuildTime[nowNode] + totalBuildTime[nowNode] > totalBuildTime[nextNode]) {
				totalBuildTime[nextNode] = selfBuildTime[nowNode] + totalBuildTime[nowNode];
			}
			// 연결 가중치 - 처리
			relationWeight[nextNode] -= 1;
			
			// 최상단 노드 도달 시 큐 입력
			if (relationWeight[nextNode] == 0) {
				queue.add(nextNode);
			}
			
		}
	}

}
