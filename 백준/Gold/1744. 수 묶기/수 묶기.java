import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 수 묶기
	 
	 정답은 항상 2^31 21억~~~ 보다 작다고 했기 때문에 int를 써도 될것 같지만
	 연산중에 넘치는 경우가 발생할 수 있으므로 long형태로 선언해서 사용해야 할 듯
	 
	 조건

	 1. 가장 큰 양수 둘을 꺼내서 곱한다.
	 2. 가장 절댓값이 큰 음수 둘을 꺼내서 곱한다. (단 곱보다 합이 더 큰 경우 합 처리 ex. 1 * 1)
	 
     ex.
	 -3	-3		-3
	 0	0	0	
	 7		7	7
	 
	 양수가 하나밖에 안남아 있다면 무조건 그냥 덧셈 처리
	 음수가 하나밖에 안남아 있고, 0이 존재한다면 곱셈 처리
	 음수가 하나밖에 안남아 있고, 0이 없다면 마이너스지만 덧셈 처리
	 
	 */
	
	static int numCount;	// 수 개수
	static ArrayList<Integer> numList = new ArrayList<>();	// 수열 리스트
	static long numSum = 0;	// 수열의 합
	static boolean zeroExist = false;	// 수열의 0 존재여부 (0을 한번만 넣기위해)

	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		numCount = Integer.parseInt(br.readLine());

		// 수열 입력 처리
		for (int i = 0; i < numCount; i++) {
			int numInput = Integer.parseInt(br.readLine());
			// 0을 한번만 처리하기 위한 로직
			if (numInput == 0) {
				if (zeroExist != true) {
					zeroExist = true;
				}else if (zeroExist == true) {
					continue;
				}
			}
			numList.add(numInput);
		}
		
		// 정렬 (오름차순)
		Collections.sort(numList);
		
		// 수열 합 연산처리
		while (numList.size() >= 2) {	// 연산할 수가 2개 이상 있을 때
			// 양수가 2개 이상 존재할 때
			if (numList.get(numList.size() - 1) > 0 && numList.get(numList.size() - 2) > 0) {
				int numMaxFirst = numList.remove(numList.size() - 1);
				int numMaxSecond = numList.remove(numList.size() - 1);
				if (numMaxFirst * numMaxSecond > numMaxFirst + numMaxSecond) {
					numSum += numMaxFirst * numMaxSecond;
				}else {
					numSum += numMaxFirst + numMaxSecond;
				}
				
			// 음수가 2개 이상 존재할 때
			}else if (numList.get(0) < 0 && numList.get(1) < 0) {
				int numMinFirst = numList.remove(0);
				int numMinSecond = numList.remove(0);
				numSum += numMinFirst * numMinSecond;

			// 양수가 단 1개만 남은 경우
			}else if (numList.get(numList.size() - 1) > 0) {
				int numMaxFirst = numList.remove(numList.size() - 1);
				numSum += numMaxFirst;
				// 더 이상 양수는 존재하지 않음
				
			// 음수가 단 1개만 남은 경우 (0이 있는 경우)
			}else if (numList.get(0) < 0 && numList.get(1) == 0) {
				int numMinFirst = numList.remove(0);
				int numMinSecond = numList.remove(0);
				numSum += numMinFirst * numMinSecond;
			}
				
		}
		
		if (numList.size() == 1) {	// 연산할 수가 1개만 있을 때
			int numMinFirst = numList.remove(0);
			numSum += numMinFirst;
		}
		
		System.out.println(numSum);
		
		
	}

}
