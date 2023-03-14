import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 사용할 변수 초기화 및 입력
		String firstLineInput[] = br.readLine().split(" ");
		int nodeCount = Integer.parseInt(firstLineInput[0]);
		int edgeCount = Integer.parseInt(firstLineInput[1]);
		int connectedcomponentCount = 1;
		
		// 스택으로 구현
		Stack<Integer> stack = new Stack<>();
		stack.add(1);	// 1부터 시작 초기값
		int checkVisit[] = new int[nodeCount];
		checkVisit[0] = 1;
		ArrayList<Integer>[] adjacencyList = new ArrayList[nodeCount];
		
		// 인접리스트 생성 초기화
		for (int i = 0; i < nodeCount; i++) {
			adjacencyList[i] = new ArrayList<Integer>();
		}
		
		// 인접리스트 양 끝점 입력
		for (int i = 0; i < edgeCount; i++) {
			String consoleInput[] = br.readLine().split(" ");
			int head = Integer.parseInt(consoleInput[0]);
			int tail = Integer.parseInt(consoleInput[1]);
			adjacencyList[head - 1].add(tail);
			adjacencyList[tail - 1].add(head);
		}
		
		// 탐색 로직 시작
		while(Arrays.stream(checkVisit)	// 모든 노드를 다 탐색할때까지
                .boxed()				// checkVisit 배열에 탐색하지 않은 노드 초기값 0이 있는경우 계속 반복
                .collect(Collectors.toSet())
                .contains(0)) {
			int peekNode = stack.pop();
			for (int i = 0; i < adjacencyList[peekNode - 1].size(); i++) {
				if (checkVisit[adjacencyList[peekNode - 1].get(i) - 1] != 1) {
					stack.add(adjacencyList[peekNode - 1].get(i));
					checkVisit[adjacencyList[peekNode - 1].get(i) - 1] = 1;
				}
			}
			if (stack.size() == 0) {
				// 한 시작점부터 순회할 수 있는 전부를 돌았으므로 횟수 +1
				connectedcomponentCount += 1;
				// 다음 시작 노드 지정
				if (Arrays.stream(checkVisit)	// 아직 탐색하지 않는 노드가 있다면
		                .boxed()
		                .collect(Collectors.toSet())
		                .contains(0)) {
					for (int i = 0; i < checkVisit.length; i++) {
						if (checkVisit[i] == 0) {
							stack.add(i + 1);
							checkVisit[i] = 1;
							break;
						}
					}
				}
			}
			
		}
		System.out.println(connectedcomponentCount);
		
		
		

	}

}
