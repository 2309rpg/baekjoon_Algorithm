import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 사용할 변수 초기화 및 입력
		String firstLineInput[] = br.readLine().split(" ");
		int dataCount = Integer.parseInt(firstLineInput[0]);
		int answerCount = Integer.parseInt(firstLineInput[1]);
		String basicArray[] = br.readLine().split(" ");
		long sumArray[] = new long[dataCount + 1];

		// 합 배열 생성
		for (int i = 1; i <= dataCount; i++) {
			sumArray[i] += Integer.parseInt(basicArray[i-1]) + sumArray[i-1];
		}
		
		// 정답 출력
		for (int i = 0; i < answerCount; i++) {
			// 시작지점 끝지점 입력
			String rangeArray[] = br.readLine().split(" ");
			int startPoint = Integer.parseInt(rangeArray[0]);
			int endPoint = Integer.parseInt(rangeArray[1]);
			
			System.out.println(sumArray[endPoint] - sumArray[startPoint - 1]);
			
		}
	
		/*
		 * 구간 합을 구할 때 미리 구간연산 값을 구해놓는 발상?
		 * */

	}

}