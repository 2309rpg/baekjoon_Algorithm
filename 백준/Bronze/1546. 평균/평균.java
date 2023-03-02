import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 사용할 변수 초기화 및 입력
		long count = Long.parseLong(br.readLine());
		String inputString = br.readLine();
		String numArray[] = inputString.split(" ");		
		long maxScore = 0l;
		double sumScore = 0;
		
		// 최대값 도출
		for (long i = 0; i < count; i+=1) {
			if (maxScore < Long.parseLong(numArray[(int)i])) {
				maxScore = Long.parseLong(numArray[(int)i]);
			}
		}
		
		// 점수별 보정 변환값 합산
		for (long i = 0; i < count; i+=1) {
			sumScore += Double.parseDouble(numArray[(int)i])/maxScore*100;
		}
		
		// 평균값 출력
		System.out.println(sumScore/count);
		
		/*
		 * 변수 선언 시 정수형태, 소수형태 고려해서 선언할 것
		 * 로직 최적화. 불필요한 반복은 없는지, 수식을 간소화 할수는 없는지 고려
		 * */

	}

}