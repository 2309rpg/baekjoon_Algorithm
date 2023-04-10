import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 최단경로
	 
	 다익스트라 알고리즘 이용
	 
	 최단거리니까 가중치가 있는 BFS 라고 생각하면 되지 않을까?
	 
	 #다익스트라 알고리즘
	 BFS와 같은 큐를 이용한 탐색은 맞는거같은데 최소값을 지정해서 큐에 우선순위로 처리 해야함
	 즉 탐색 시 우선순위 큐를 이용해 시작지점으로 부터 가장 짧은 값부터 처리하는게 핵심 개념
	 */
	
	static int nodeCount;	// 노드개수
	static int edgeCount;	// 에지개수
	static int startNode;	// 시작노드
	static ArrayList<ArrayList<Integer>>[] edgeInfoArray;	// 에지정보 배열
	static int[] minLengthArray;	// 최소거리 배열
	static boolean[] visitArray;	// 방문처리 배열

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] consoleInput = br.readLine().split(" ");
		nodeCount = Integer.parseInt(consoleInput[0]);
		edgeCount = Integer.parseInt(consoleInput[1]);
		
		startNode = Integer.parseInt(br.readLine());
		
		// 노드 관계 배열 선언 및 초기화
		edgeInfoArray = new ArrayList[nodeCount + 1];
		for (int i = 0; i < edgeInfoArray.length; i++) {
			edgeInfoArray[i] = new ArrayList<>();
		}
		
		// 길이 확인 배열 선언 및 초기화
		minLengthArray = new int[nodeCount + 1];
		for (int i = 0; i < minLengthArray.length; i++) {
			// int 최대값으로 초기화 처리
			minLengthArray[i] = Integer.MAX_VALUE;
		}
		// startNode는 최소거리 0 부터 시작
		minLengthArray[startNode] = 0;
		
		// 방문 배열 선언 및 초기화
		visitArray = new boolean[nodeCount + 1];
		
		
		// 관계 입력받기
		for (int i = 0; i < edgeCount; i++) {
			consoleInput = br.readLine().split(" ");
			int headNode = Integer.parseInt(consoleInput[0]);
			int tailNode = Integer.parseInt(consoleInput[1]);
			int value = Integer.parseInt(consoleInput[2]);
			// 임시 어레이리스트에 담아서 노드와 가중치 입력
			ArrayList<Integer> temp = new ArrayList<>();
			temp.add(tailNode);
			temp.add(value);
			edgeInfoArray[headNode].add(temp);
		}
		
		// 최소거리 탐색 로직
		dijkstra(startNode, 0);
		
		// 출력
		for (int i = 1; i < minLengthArray.length; i++) {
			// int MAX_VALUE 가 아니면 최소값 존재
			if (minLengthArray[i] != Integer.MAX_VALUE) {
				System.out.println(minLengthArray[i]);
			}else {
				System.out.println("INF");
			}
		}
		
	}
	
	public static void dijkstra(int startNode, int value) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		Node firstNode = new Node(startNode, value);
		queue.add(firstNode);
		
		// bfs 탐색이므로 queue를 이용해서 큐가 빌때까지 반복
		while (queue.size() > 0) {
			Node nowNodeObject = queue.poll();
			int nowNode = nowNodeObject.end;
			
			// 이미 방문한 노드라면 스킵
			if (visitArray[nowNode]) {
				continue;
			}
			// 방문처리
			visitArray[nowNode] = true;
			
			for (int i = 0; i < edgeInfoArray[nowNode].size(); i++) {
				int nextNode = edgeInfoArray[nowNode].get(i).get(0);
				int nextValue = edgeInfoArray[nowNode].get(i).get(1);
				// 현재노드 가중치 + 다음노드 가중치 < 다음노드 가중치 현재 최소값 인 경우 치환 처리
				if (minLengthArray[nowNode] + nextValue < minLengthArray[nextNode]) {
					minLengthArray[nextNode] = minLengthArray[nowNode] + nextValue;
					queue.add(new Node(nextNode, minLengthArray[nextNode]));
				}
			}
		}

	}
	
}

class Node implements Comparable<Node>{
    int end, value;

    public Node(int end, int value){
        this.end = end;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return value - o.value;
    }
}
