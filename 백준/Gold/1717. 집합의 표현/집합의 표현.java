import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 집합의 표현
	 
	 유니온 파인드를 활용하는 문제로 각 인덱스의 head 인덱스를 변경해가면서 풀이
	 
	 # 풀이과정에서 느낀 핵심 부분
	 물론 합 연산 시 대표노드와 대표노드를 잇는다는 개념도 중요하겠지만
	 해당 부분은 직관적으로 떠올릴 수 있는 내용이라고 생각함
	 그러나 find 연산 시 재귀 형태로 대표 노드에 도달 했을 때 재귀 형태로 돌아오면서
	 모든 연관 노드에 대해 대표 노드로 직통으로 지정하는 아이디어는 직관적으로 떠올리기 어려움
	 이 부분이 유니온 파인드의 핵심 개념이라고 생각
	 */
	
	static int elementCount;	// 원소 개수
	static int questionCount;	// 질의 개수
	static int[] indexArray;	// 원소 인덱스 배열

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] consoleInput = br.readLine().split(" ");
		elementCount = Integer.parseInt(consoleInput[0]);
		questionCount = Integer.parseInt(consoleInput[1]);
		
		// 원소 개수만큼 배열 할당 및 값 할당
		indexArray = new int[elementCount + 1];
		for (int i = 0; i < indexArray.length; i++) {
			indexArray[i] = i;
		}
		
		// 질의 개수만큼 반복
		for (int i = 1; i <= questionCount; i++) {
			// 콘솔입력
			consoleInput = br.readLine().split(" ");
			int questionType = Integer.parseInt(consoleInput[0]);
			int firstElement = Integer.parseInt(consoleInput[1]);
			int secondElement = Integer.parseInt(consoleInput[2]);
			int temp = 0;
			
			// 더 작은 숫자가 앞의 원소로 오도록 스왑처리
			if (firstElement > secondElement) {
				temp = firstElement;
				firstElement = secondElement;
				secondElement = temp;
			}
			
			// 합집합 연산
			if (questionType == 0) {
				// 두 원소가 같은 합집합 연산의 경우 아무일도 일어나지 않음
				if (firstElement == secondElement) {
					continue;
				}
				sumCal(firstElement, secondElement);
				
			// 확인 연산
			}else if (questionType == 1) {
				// 두 원소가 같은 확인 연산의 경우 무조건 YES 출력
				if (firstElement == secondElement) {
					System.out.println("YES");
					continue;
				}
				checkCal(firstElement, secondElement);
				
			}
			
		}
		
	}
	
	// 두 노드의 합 연산
	public static void sumCal(int firstElement, int secondElement) {
		int firstHeadIndex = findHeadElement(firstElement);
		int secondHeadIndex = findHeadElement(secondElement);
		indexArray[secondHeadIndex] = firstHeadIndex;

	}
	
	// 두 노드가 같은 집합에 있는지 확인 연산
	public static void checkCal(int firstElement, int secondElement) {
		int firstHeadIndex = findHeadElement(firstElement);
		int secondHeadIndex = findHeadElement(secondElement);
		if (firstHeadIndex == secondHeadIndex) {
			System.out.println("YES");
		}else if (firstHeadIndex != secondHeadIndex) {
			System.out.println("NO");
		}
	}
	
	// 현재 노드의 대표 노드 찾기
	public static int findHeadElement(int nowElement) {
		if (nowElement == indexArray[nowElement]) {
			return nowElement;
		}else {
			return indexArray[nowElement] = findHeadElement(indexArray[nowElement]);
		} 
		
	}

}
