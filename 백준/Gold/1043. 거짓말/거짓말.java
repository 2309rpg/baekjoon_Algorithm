import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 거짓말
	 
	 특정 파티가 열린다. 파티에서 거짓말을 하고자 하는데, 거짓말을 아는 사람이 있는 경우에는
	 거짓말을 할 수가 없다. 주어진 몇번의 파티에서 거짓말을 할 수 있는 최대값 출력
	 
	 해당 문제도 유니온 파인드를 활용한 문제
	 얼핏 생각하면 8명이 있다고 할 때 거짓말을 아는 사람의 번호가 1, 2, 3번이라고 하자
	 그러면 1, 2, 3번이 없는 파티에서만 거짓말 안하면 되는거 아닌가? 라는 생각이 일차적으로 들텐데
	 해당 접근방법은 틀렸다.
	 왜냐하면 냅다 거짓말을 모르는 4번에게 거짓말을 하였다고 할 때,
	 다음 파티에 1번과 4번이 참여한 경우 1번이 거짓말을 알고 있으므로 거짓말을 할 수 없는데
	 이미 4번에게는 거짓말을 질러놓았기 때문에 4번은 1번이 참여한 파티에서 거짓말임을 알게된다.
	 때문에 단순히 거짓말을 아는 참여자 뿐만 아니라 이후의 합석여부도 고려해야 한다.
	 
	 #솔루션
	 당장 이번파티에 거짓말이 가능한지 판단 유무를 따지기전에 최종적으로 연관될 그룹을 먼저 정리한 후
	 각 파티마다 그룹과 관련된 사람이 있는지 판단 후 거짓말을 할지 정해야 함
	 
	 머리로는 이해했는데 실제 코딩테스트에서 문제가 나오면
	 1. 이 아이디어를 떠올리기
	 2. 유니온 파인드를 구현하기
	 이게 가능할까? 라는 생각이 듬 자괴감이 좀
	 */
	
	static int peopleCount;
	static int partyCount;
	static int[] knowPeopleArray;
	static int[] indexArray;
	static ArrayList<ArrayList<Integer>> partyList = new ArrayList<>();
	static int fakeCount = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] consoleInput = br.readLine().split(" ");
		peopleCount = Integer.parseInt(consoleInput[0]);
		partyCount = Integer.parseInt(consoleInput[1]);
		
		// 진실을 아는사람 정보 입력
		consoleInput = br.readLine().split(" ");
		int knowPeopleCount = Integer.parseInt(consoleInput[0]);
		knowPeopleArray = new int[knowPeopleCount];
		// 진실을 아는 사람 수 만큼 입력
		for (int i = 0; i < knowPeopleCount; i++) {
			knowPeopleArray[i] = Integer.parseInt(consoleInput[i + 1]);
		}
		
		// 각 사람별 인덱스 초기화 및 값 할당
		indexArray = new int[peopleCount + 1];
		for (int i = 0; i < indexArray.length; i++) {
			indexArray[i] = i;
		}
		
		// 파티정보 입력
		for (int i = 0; i < partyCount; i++) {
			partyList.add(new ArrayList<>());
			consoleInput = br.readLine().split(" ");
			int nowMemberCount = Integer.parseInt(consoleInput[0]);
			// 이번 파티 정보 입력
			for (int j = 0; j < nowMemberCount; j++) {
				partyList.get(i).add(Integer.parseInt(consoleInput[j + 1]));
			}
		}
		
		// 각 파티별 연관인원 처리
		for (int i = 0; i < partyCount; i++) {
			int firstMember = partyList.get(i).get(0);
			for (int j = 1; j < partyList.get(i).size(); j++) {
				sumOper(firstMember, partyList.get(i).get(j));
			}
		}
		
		// 각 파티별 가능여부 확인
		for (int i = 0; i < partyCount; i++) {
			boolean fake = true;
			int firstMember = headOper(partyList.get(i).get(0));
			for (int j = 0; j < knowPeopleArray.length; j++) {
				if (firstMember == headOper(knowPeopleArray[j])) {
					fake = false;
					break;
				}
			}
			
			// 이번 회차의 파티가 거짓말이 가능한 경우 +1
			if (fake == true) {
				fakeCount += 1 ;
			}
		}
		
		// 출력
		System.out.println(fakeCount);
		
	}
	
	// 합 연산처리 시 대표 값이 다르다면 합 연산
	public static void sumOper(int head, int tail) {
		head = headOper(head);
		tail = headOper(tail);
		if (head != tail) {
			indexArray[tail] = head;
		}
	}
	
	// 해당 값의 대표 값 찾으면서 재귀 처리
	public static int headOper(int now) {
		if (now == indexArray[now]) {
			return now;
		}else {
			return indexArray[now] = headOper(indexArray[now]);
		}
	}

}
