import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 최소비용 구하기
	 
	 도시가 N개 있고, 도시와 도시를 잇는 버스 노선이 M개 있다
	 버스노선에는 각 가중치가 존재함
	 특정도시로부터 특정도시까지 최단거리 출력 문제
	 
	 다익스트라 적용에 해당하는 문제 정도로 보임
	 1753(56번)의 가벼운 응용
	 */
	
	static int nodeCount;
	static int edgeCount;
	static boolean[] visitArray;
	static int[] minLengthArray;
	static ArrayList<ArrayList<Integer>>[] relationArray;
	static int startNode;
	static int endNode;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] consoleInput/* = br.readLine().split(" ")*/;
		nodeCount = Integer.parseInt(br.readLine());
		edgeCount = Integer.parseInt(br.readLine());
		
		// 방문배열, 최소길이배열, 관계배열 초기화
		visitArray = new boolean[nodeCount + 1];
		
		minLengthArray = new int[nodeCount + 1];
		Arrays.fill(minLengthArray, Integer.MAX_VALUE);
		
		relationArray = new ArrayList[nodeCount + 1];
		for (int i = 0; i < relationArray.length; i++) {
			relationArray[i] = new ArrayList<>();
		}
		
		// 버스 노선 정보 및 가중치 입력
		for (int i = 1; i <= edgeCount; i++) {
			consoleInput = br.readLine().split(" ");
			int headCity = Integer.parseInt(consoleInput[0]);
			int tailCity = Integer.parseInt(consoleInput[1]);
			int value = Integer.parseInt(consoleInput[2]);
			ArrayList temp = new ArrayList<>();
			temp.add(tailCity);
			temp.add(value);
			relationArray[headCity].add(temp);
		}
		
		// 출발도시 도착도시 입력
		consoleInput = br.readLine().split(" ");
		startNode = Integer.parseInt(consoleInput[0]);
		endNode = Integer.parseInt(consoleInput[1]);
		
		// 출발도시 최소거리 0 설정
		minLengthArray[startNode] = 0;
		
		// 다익스트라 알고리즘 로직
		dijkstra(startNode);
		
		// 출력
		System.out.println(minLengthArray[endNode]);
		
	}
	
	public static void dijkstra(int startNode) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		Node startNodeObject = new Node(startNode, 0);
		queue.add(startNodeObject);
		
		while (queue.size() > 0) {
			Node nowNodeObject = queue.poll();
			int nowNode = nowNodeObject.end;
			// 방문여부 처리
			if (visitArray[nowNode]) {
				continue;
			}
			visitArray[nowNode] = true;
			
			for (int i = 0; i < relationArray[nowNode].size(); i++) {
				int relationNode = relationArray[nowNode].get(i).get(0);
				int relationNodeValue = relationArray[nowNode].get(i).get(1);
				// 현재노드 가중치 + 다음노드 가중치 < 다음노드 가중치 현재 최소값 인 경우 치환 처리
				if (minLengthArray[nowNode] + relationNodeValue < minLengthArray[relationNode]) {
					minLengthArray[relationNode] = minLengthArray[nowNode] + relationNodeValue;
					queue.add(new Node(relationNode, minLengthArray[relationNode]));
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