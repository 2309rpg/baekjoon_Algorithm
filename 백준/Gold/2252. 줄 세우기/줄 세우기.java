import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 줄 세우기
	 
	 학생 N명과 그 학생들관의 비교 관계 M개가 주어질 때 줄 세우기
	 
	 위상정렬 튜토리얼
	 */
	
	static int studentCount;				// 학생 수
	static int compareCount;				// 비교 횟수
	static ArrayList<Integer>[] edgeList;	// 에지 리스트
	static int[] comeToArray;				// 가중치 배열
	static Queue<Integer> queue = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] consoleInput = br.readLine().split(" ");
		studentCount = Integer.parseInt(consoleInput[0]);
		compareCount = Integer.parseInt(consoleInput[1]);
		
		// 나에게 올수 있는 가중치 배열 선언 및 초기화
		comeToArray = new int[studentCount + 1];
		
		// 에지 리스트 초기화 및 입력
		edgeList = new ArrayList[studentCount + 1];
		for (int i = 0; i < edgeList.length; i++) {
			edgeList[i] = new ArrayList<>();
		}
		for (int i = 1; i <= compareCount; i++) {
			consoleInput = br.readLine().split(" ");
			int head = Integer.parseInt(consoleInput[0]);
			int tail = Integer.parseInt(consoleInput[1]);
			edgeList[head].add(tail);
			comeToArray[tail] += 1;	// 가중치 +
		}
		
		// 시작 시 최상단 노드들을 큐 입력
		for (int i = 1; i <= studentCount; i++) {
			if (comeToArray[i] == 0) {
				queue.add(i);
			}
		}
		
		// 큐가 빌때까지 반복
		while (queue.size() > 0) {
			int now = queue.poll();
			
			// 현재 노드의 관계 노드 처리
			reliationOper(now);
			
			// 출력
			System.out.printf("%d ",now);
		}
		
	}
	
	// 관계 노드 처리 (현재 노드와 이어지는 노드들 가중치 - 처리)
	public static void reliationOper(int now) {
		for (int i = 0; i < edgeList[now].size(); i++) {
			int relationNode = edgeList[now].get(i);
			// 현재노드를 처리 했으므로 관련 노드 가중치 값 - 처리
			comeToArray[relationNode] -= 1;
			// 가중치 처리 후 관계 노드가 최상단 노드인 경우 큐 추가
			if (comeToArray[relationNode] == 0) {
				queue.add(relationNode);
			}
		}
	}

}
