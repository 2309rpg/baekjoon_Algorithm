import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 잃어버린 괄호
	 
	 숫자, +, - 로 이루어진 수식이 주어졌을 때 최소값을 출력 하는 문제
	 
	 접근 방법을 어떻게 생각하냐가 역시나 핵심인 문제
	 그리디 문제는 기법이라기보다 아이디어가 좋아야한다고 생각함
	 
	 단순 합 연산의 경우에도 + 끼리는 합해져서 처리되고,
	 - 연산이 앞에 있다고 하더라도 최소값을 만들기 위해서라면 뒤의 숫자를 크게 키워야 함
	 때문에 모든 + 연산자들끼리 먼저 처리한 후 남은 값은 전부 - 연산으로 처리하면 간단하고 최적 루트 가능
	 
	 ArrayList가 가변적으로 크기 지정없이 입력할 수 있다는 점에서 많이 적용해보려고 했는데,
	 이 경우에는 오히려 기본 배열을 통해서 값을 치환하는 형태로 수식을 계산하는게 더 효율적인 것 같음
	 ArrayList 남발하지말고 문제의 조건이나 상황을 잘 봐서 어떤 형태를 사용할지 생각하고 사용 필요해 보임
	 */
	
	static ArrayList<String> calArray = new ArrayList<>();
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// - 기준으로 나눠서 입력받음
		String consoleInput[] = br.readLine().split("-");
		
		// + 가 존재하는 배열의 경우에만 합 처리 후 다시 기존 배열에 재입력
		for (int i = 0; i < consoleInput.length; i++) {
			// +가 존재하는지
			if (consoleInput[i].contains("+")) {
				String[] calArray = consoleInput[i].split("[+]");
				int num = 0;
				for (int j = 0; j < calArray.length; j++) {
					num += Integer.parseInt(calArray[j]);
				}
				consoleInput[i] = num + "";
			}
		}
		
		// 첫번째 숫자는 무조건 + 처리
		answer += Integer.parseInt(consoleInput[0]);
		
		// 이후 숫자는 - 처리해서 연산
		for (int i = 1; i < consoleInput.length; i++) {
			answer -= Integer.parseInt(consoleInput[i]);
		}
		
		// 정답 출력
		System.out.println(answer);
	}

}
