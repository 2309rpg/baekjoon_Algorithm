import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 그리디 알고리즘 입문
	 
	 동전의 갯수와 종류가 주어졌을 때, 목표금액을 만들 수 있는 최소 동전 개수 출력 문제
	 
	 그리디 알고리즘 튜도리얼정도 되는듯
	 직관적으로 가장 이득을 본다고 생각하는 경우의 수로 처리하는 느낌
	 */
	
	static int coinCount;	// 동전 개수
	static int targetMoney;	// 최대 금액이 1억이므로 int로 선언해도 무방
	static ArrayList<Integer> coinList = new ArrayList<>();	// 동전 리스트
	static int coinMin = 0;	// 코인최소개수
	
	public static void main(String[] args) throws Exception {
		
		// 콘솔 입력을 위해 버퍼리더 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] consoleInput = br.readLine().split(" ");
		coinCount = Integer.parseInt(consoleInput[0]);
		targetMoney = Integer.parseInt(consoleInput[1]);

		for (int i = 0; i < coinCount; i++) {
			coinList.add(Integer.parseInt(br.readLine()));
		}
		
		// 큰 수가 앞으로 오도록 정렬
		Collections.sort(coinList, Collections.reverseOrder());
		
		// 처리 할 수 있는 동전 찾기
		for (int i = 0; i < coinCount; i++) {
			// 선택한 동전이 현재처리금액 보다 작거나 같다면
			if (coinList.get(i) <= targetMoney) {
				int nowCoin = coinList.get(i);
				coinMin += (targetMoney / nowCoin);	// 이번 코인으로 나눠지는 몫 만큼 코인최소개수에 추가
				targetMoney -= (targetMoney / nowCoin) * nowCoin;	// 목표금액 마이너스 처리
			}
		}
		
		// 코인최소개수 출력
		System.out.println(coinMin);

	}

}
