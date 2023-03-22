import java.io.*;

public class Main {
	
	static int N = 0;

	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 사용할 변수 초기화 및 입력
		N = Integer.parseInt(br.readLine());
		
		// 일의 자리 소수는 2,3,5,7 이 전부
		dfs(2, 1);
		dfs(3, 1);
		dfs(5, 1);
		dfs(7, 1);
	}
	
	// 재귀를 이용해서 풀이할것이므로 함수 생성
	public static void dfs(int num, int count) {
		// 소수판별
		if (N == count) {
			if (sosuCheck(num)) {
				System.out.println(num);
			}
			return;
		}
		for (int i = 1; i <= 9; i+=2) {	// 다음 자리수 추가 1 3 5 7 9만 (두번째 자리부터는 짝수는 소수불가)
			// 다음 수가 소수인 경우에만 재귀 반복
			if (sosuCheck(num * 10 + i)) {
				dfs(num * 10 + i, count + 1);
			}
		}
	}
	
	public static boolean sosuCheck(int num) {
		for (int i = 2; i <= num / 2; i++) {
			// 1 과 자기 자신이 아닌 수로 한번이라도 나눠졌다면 소수
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

}
