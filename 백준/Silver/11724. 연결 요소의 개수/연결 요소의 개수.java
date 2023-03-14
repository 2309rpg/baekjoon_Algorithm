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
        // 어떤 구조더라도 최소 한개는 있음
		int connectedcomponentCount = 1;
		
		// 스택으로 구현 (1부터 시작 초기값)
		Stack<Integer> stack = new Stack<>();
		stack.add(1);	
		int checkVisit[] = new int[nodeCount];
		checkVisit[0] = 1;
        
        // 인접리스트 생성 초기화
		ArrayList<Integer>[] adjacencyList = new ArrayList[nodeCount];
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
            // 스택 최상단 값 꺼내기
			int peekNode = stack.pop();
            // 해당 값 인접노드 처리
			for (int i = 0; i < adjacencyList[peekNode - 1].size(); i++) {    // 인접노드 개수만큼 체크
				if (checkVisit[adjacencyList[peekNode - 1].get(i) - 1] != 1) {    // 탐색여부 확인
					stack.add(adjacencyList[peekNode - 1].get(i));
					checkVisit[adjacencyList[peekNode - 1].get(i) - 1] = 1;
				}
			}
            // 한 포인트부터 인접노드 탐색을 마친 경우
			if (stack.size() == 0) {
				// 한 시작점부터 순회할 수 있는 전부를 돌았으므로 횟수 +1
				connectedcomponentCount += 1;
				// 다음 시작 노드 지정
				if (Arrays.stream(checkVisit)	// 아직 탐색하지 않은 노드가 있다면
		                .boxed()
		                .collect(Collectors.toSet())
		                .contains(0)) {
                    // 탐색여부 배열에서 시작 값 지정
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
        // 출력
		System.out.println(connectedcomponentCount);
	}

}
