import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		
    	// 콘솔 입력을 위해 버퍼리더 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 수열의 크기
        int N = Integer.parseInt(br.readLine());
        
        // 첫번째로 그냥 드는 생각. 반복으로 돌리면 되는거 아니야? -> 아니야. 시간복잡도 문제발생
        
        // 사용할 변수 초기화 및 입력
        StringBuffer bf = new StringBuffer();
        String inputStr[] = br.readLine().split(" ");
		int inputNum[] = new int[N];
		for (int i = 0; i < N; i++) {
			inputNum[i] = Integer.parseInt(inputStr[i]);
		}
		Stack<Integer> stack = new Stack<>();
		
        for(int i = 0; i < N; i++){
        	
        	// 스택이 비어있지 않고, 스택의 top의 인덱스 값이 현재 값보다 작은 경우
            while(!stack.isEmpty() && inputNum[stack.peek()] < inputNum[i]){
            	inputNum[stack.pop()] = inputNum[i];
            }

            stack.push(i);
        }
		
		// stack에 남은 인덱스 값은 -1로 치환
		while (stack.size() > 0) {
			int nowIndex = stack.pop();
			inputNum[nowIndex] = -1;
		}
        
		// 출력
		for (int i = 0; i < inputNum.length; i++) {
			bf.append(inputNum[i]).append(" ");
		}
        System.out.println(bf);
	}

}
