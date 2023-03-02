import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 사용할 변수 초기화 및 입력
		String firstLineInput[] = br.readLine().split(" ");
		int arraySize = Integer.parseInt(firstLineInput[0]);
		int answerCount = Integer.parseInt(firstLineInput[1]);
		long basicArray[][] = new long[arraySize + 1][arraySize + 1];
		long sumArray[][] = new long[arraySize + 1][arraySize + 1];

		// 기본 2차원 배열 입력
		for (int i = 1; i <= arraySize; i++) {
			String numStrArray[] = br.readLine().split(" ");
			for (int j = 1; j <= arraySize; j++) {
				basicArray[i][j] = Long.parseLong(numStrArray[j-1]);
			}
		}
		
		// 합 2차원 배열 입력
		for (int i = 1; i <= arraySize; i++) {
			for (int j = 1; j <= arraySize; j++) {
				sumArray[i][j] = sumArray[i-1][j] + sumArray[i][j-1] + basicArray[i][j] - sumArray[i-1][j-1];
			}
		}
			
		// 정답 출력
		for (int i = 0; i < answerCount; i++) {
			String positionArray[] = br.readLine().split(" ");
			int x1 = Integer.parseInt(positionArray[0]);
			int y1 = Integer.parseInt(positionArray[1]);
			int x2 = Integer.parseInt(positionArray[2]);
			int y2 = Integer.parseInt(positionArray[3]);
			System.out.println(sumArray[x2][y2] - sumArray[x1-1][y2] - sumArray[x2][y1-1] + sumArray[x1-1][y1-1]);
		
		}
	
		/*
		 * 2차원 배열일때 미리 합을 구해놓는 경우의 구조 이해
		 * */

	}

}
