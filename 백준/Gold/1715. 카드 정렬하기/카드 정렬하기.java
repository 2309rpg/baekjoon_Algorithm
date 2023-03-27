import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 카드 정렬하기
	 
	 카드 묶음이 여러개 있을 때, 카드 묶음을 합치는 선택을 하는 그리디 문제
	 카드 묶음을 입력 받은 후 가장 작은 단위의 두 묶음을 선택 한 후 계속 합쳐나가면 되지 않을까?
	 (미리 정렬해놓고 합쳐나간다면 매번 가장 작은 단위의 두 묶음을 찾지 않아도 되니까 훨씬 이득일 듯)
	 
	 추가로 최대 카드 묶음의 개수는 10만, 카드묶음의 최대 크기는 1000이므로
	 두개를 곱한 1억 이하의 수로 답이 나올것이기 때문에 int로 선언해도 될 듯
	 
	 # 중요사항
	 이번 풀이 시 문제가 두가지 있었음
	 
	 첫째, 카드묶음 리스트를 받은 후 정렬한 다음에 쭉 뒤로 더해나가면 되는것 아닌가? 하는 발상
	 -> 40 50 60 70 의 묶음이 있다고 할때 (40 + 50) 60 70 의 순서로 생각했지만 만들어진 90묶음 또한 재비교 후 합쳐야 함
	 
	 둘째, 카드묶음을 ArrayList로 받은 후 최초 정렬 후 앞에서 두개 뽑고 합친 후 재정렬 ~~ 반복하면 되는것 아닌가? 하는 발상
	 -> 로직 자체에는 문제가 없으나 데이터의 삽입, 삭제, 정렬이 너무 빈번함. 특히 매 반복마다 재정렬을 처리하는데 시간초과의 원인
	 	따라서 PriorityQueue(우선순위큐)를 사용함으로써 잦은 처리에 의한 시간 단축이 가능
	 	매번 정렬 할 필요가 없기 때문에. 이번 문제의 핵심은 우선순위큐에도 있다고 보여짐
	 */
	
	static int setCount;	// 카드묶음 개수
	//static ArrayList<Integer> setList = new ArrayList<>();	// 카드묶음 리스트
	static PriorityQueue<Integer> setList = new PriorityQueue<>();	// 카드묶음 리스트
	static int compareCount = 0;
	static int first = 0;
	static int second = 0;
	
	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		setCount = Integer.parseInt(br.readLine());

		for (int i = 0; i < setCount; i++) {
			setList.add(Integer.parseInt(br.readLine()));
		}
		
		// 작은 수가 앞으로 오도록 정렬 (항상 최소 묶음값부터 처리하도록)
		//Collections.sort(setList);
		
		// 카드묶음 리스트에 한 더미만 있을때까지 반복
		while (setList.size() != 1) {
			//first = setList.remove(0);	// 정렬된 카드묶음 리스트에서 0번 인덱스 값 뽑기
			//second = setList.remove(0);	// 정렬된 카드묶음 리스트에서 0번 인덱스 값 뽑기 (first가 뽑혔으므로 두번째 값이 0번 인덱스)
			
			first = setList.remove();
			second = setList.remove();
			int setSum = first + second;
			compareCount += setSum;		// 카드 묶음 비교 횟수 카운트
			
			setList.add(setSum);		// 합친 카드 묶음을 다시 카드묶음 리스트에 입력
			//Collections.sort(setList);	// 작은 수부터 비교할 것이므로 다시 정렬
		}
		
		// 비교 횟수 출력
		System.out.println(compareCount);

	}

}

