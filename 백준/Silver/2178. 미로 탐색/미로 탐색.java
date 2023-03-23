import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 미로 탐색하기
	 
	 4 * 6 형태의 다음과 같은 미로가 있다고 할 때,
	 101111
	 101010
	 101011
	 111011
	 미로의 숫자 중 1은 이동할 수 있는 칸, 0은 이동할 수 없는 칸
	 한 칸에서 다른 칸으로 이동할 때는 인접한 칸으로만 이동 가능
	 이동한 칸을 셀때는 시작 위치와 도착 위치를 포함해서 카운트
	 ex. (1,1) ~ (4,6) 으로 이동할때는 15칸의 이동이 필요
	
	(1,1) 에서 출발 해 (N,M) 의 좌표로 이동하기 위해 지나야하는 최소 칸 출력
	
	최소 칸 출력이라는 개념을 볼때 최단경로를 구하는 BFS를 이용해야 할것으로 보임
	
	N, M (2 <= N, M <= 100)
	
	2차원 배열로 풀어야할것 같은데, 기존처럼 노드 관계로 풀수도 있지 않을까? 하는 생각
	기존 노드 관계를 인덱스 형태로 재작성 해야하는데 불가능할것 같음
	=> 다시 2차원 풀이로 변경
	
	문제가 원하는건 BFS를 2차원 배열에 적용해보는것 같음                      
	
	 */
	
	static int N, M;
	static int[][] checkVisit;	// 방문체크
	static int[][] maze;		// 미로
	// 현재좌표의 상하좌우 가중치 값 확인용
	static int[] adjoinX = {1, -1, 0, 0};
	static int[] adjoinY = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] firstLineInput = br.readLine().split(" ");
		N = Integer.parseInt(firstLineInput[0]);
		M = Integer.parseInt(firstLineInput[1]);
		
		// 미로와 방문 배열 생성
		maze = new int[N][M];
		checkVisit = new int[N][M];
		
		// 미로 값 입력
		for (int i = 0; i < N; i++) {
			String consoleInput[] = br.readLine().split("");
			for (int j = 0; j < consoleInput.length; j++) {
				maze[i][j] = Integer.parseInt(consoleInput[j]);
			}
		}
		
		// bfs 탐색 및 출력
		bfs();
		System.out.println(maze[N - 1][M - 1]);
	}
	
	public static void bfs() {
		// bfs는 큐로 풀어야하므로 큐 선언
		Queue<int[]> queue = new LinkedList<>();
		
		// 시작 지점 방문 및 입력
		queue.add(new int[] {0, 0});
		checkVisit[0][0] = 1;
		
		// 큐가 빌때까지 반복
		while (queue.size() != 0) {
			int nowPoint[] = queue.poll();
			
			// 현재 좌표의 상하좌우 값 체크
			for (int i = 0; i < 4; i++) {
				int x = nowPoint[0] + adjoinX[i];
				int y = nowPoint[1] + adjoinY[i];
				// 인접 좌표값이 정상 좌표값인지 확인
				if (x >= 0 && y >= 0 && x < N && y < M) {
					// 미로에서 갈 수있는 위치인지 확인
					if (maze[x][y] == 1) {
						// 방문여부 확인
						if (checkVisit[x][y] == 0) {
							queue.add(new int[] {x, y});	// 다음위치 큐에 추가
							maze[x][y] = maze[nowPoint[0]][nowPoint[1]] + 1;	// 깊이 가중치
							checkVisit[x][y] = 1;			// 방문여부 업데이트
						}
					}
					
				}
			}
		}
		
		
	}

}
