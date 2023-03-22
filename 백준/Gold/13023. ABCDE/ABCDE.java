import java.io.*;
import java.util.*;

public class Main {
	
	/*
	아마도 이 문제도 재귀형태로 풀어야할듯?
	근데, 꼭 DFS로 풀어야하나? 걍 하나하나 체크하면 시간복잡도 문제가 생길라나? [불가]
	ex) 0 1 2 3 4 의 경우 되지않나? 의 생각을 해봤는데 순서대로 친구가 아니고 연결만 성립하면 조건 성립이라
	경우의 수 다 따지면서 체크하는게 맞음
	
	일단 사람의 수, 관계의 수 입력 받고
	관계의 수 만큼 관계 입력 받은 다음에
	해당 관계 리스트던 뭐던 하나 잡고 재귀형태로 쭉 잡고 다음사람 다음사람 체크해나가야할듯
	
	조건에 맞는경우 1 출력 아니면 0 출력이니까
	하나라도 존재하는걸 발견한순간 1 출력하고 스탑
	
	head에 해당하는 사람은 0 이상의 인덱스
	tail에 해당하는 사람은 사람수-1 이하의 인덱스
	
	head와 tail이 동일하게 주어지는 관계는 없음
	같은 관계가 두번 주어지는 경우는 없음
	
	하나의 시작 노드에서 출발해 인접노드를 이동했을 때 5스택 쌓이면 성공
	*/
	
	static int manCount;
	static int relationCount;
	static ArrayList<Integer>[] adjacencyList;
	static int[] checkVisit;

	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] firstLineInput = br.readLine().split(" ");
		manCount = Integer.parseInt(firstLineInput[0]);
		relationCount = Integer.parseInt(firstLineInput[1]);
		
		// 인접리스트 생성 초기화
		adjacencyList = new ArrayList[manCount];
		for (int i = 0; i < manCount; i++) {
			adjacencyList[i] = new ArrayList<Integer>();
		}
		
		// 관계 입력
		for (int i = 0; i < relationCount; i++) {
			String consoleInput[] = br.readLine().split(" ");
			int head = Integer.parseInt(consoleInput[0]);
			int tail = Integer.parseInt(consoleInput[1]);
			adjacencyList[head].add(tail);
			adjacencyList[tail].add(head);
		}
		
		for (int i = 0; i < manCount; i++) {
			// dfs 시작노드 초기화 시 방문배열도 초기화
			checkVisit = new int[manCount];
			dfs(i, 1);
		}
		
		// dfs 재귀 반복 시 종료되지 않았다면 만족하는 경우의 수가 없는 것
		System.out.println(0);
		
	}
	
	public static void dfs(int i, int stack) {
		// 5스택에 도달했을 경우 조건을 만족하므로 종료
		if (stack >= 5) {
			System.out.println(1);
			System.exit(0);
		}
		checkVisit[i] = 1;
		for (int j = 0; j < adjacencyList[i].size(); j++) {
			// 방문 여부 체크
			if (checkVisit[adjacencyList[i].get(j)] != 1) {
				dfs(adjacencyList[i].get(j), stack + 1);
			}
		}
		checkVisit[i] = 0;
	}

}
