import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		
    	// 콘솔 입력을 위해 버퍼리더 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 수열의 개수
        int N = Integer.parseInt(br.readLine());
        
        // 사용할 변수 초기화 및 입력
        StringBuffer bf = new StringBuffer();
		int number[] = new int[N];
		Stack<Integer> stack = new Stack<>();
		int standard = 1;
		
		// 자연수 입력
		for (int i = 0; i < number.length; i++) {
			number[i] = Integer.parseInt(br.readLine());
		}
        
		for (int i = 0; i < number.length; i++) {
			int nowNum = number[i];
			// 현재 처리할 자연수가 기준값보다 크거나 같은 경우
			if (nowNum >= standard) {
				while (nowNum >= standard) {
					stack.push(standard);
					standard += 1;
					bf.append("+\n");
				}
				stack.pop();
				bf.append("-\n");
				
			// 현재 처리할 자연수가 기준값보다 작은 경우
			}else {
				int stackTop = stack.pop();
				// 스택 최상단 값이 현재 출력값이랑 같은 경우
				if (stackTop == nowNum) {
					bf.append("-\n");
					
				// 스택 최상단 값이 현재 출력값과 다른 경우
				}else {
					// NO 출력 후 시스템 종료
					System.out.println("NO");
					System.exit(0);
				}
			}
		}
		// 스트링 버퍼 출력
		System.out.println(bf);

	}

}
