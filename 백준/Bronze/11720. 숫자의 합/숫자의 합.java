import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// 콘솔 입력받을 스캐너 생성
		Scanner sc = new Scanner(System.in);
		
		// 사용할 변수 생성 및 값 입력
		long count = sc.nextLong();
		String inputText = sc.next();
		String numArray[] = inputText.split("");
		long answer = 0;
		
		// numArray에 담겨있는 문자열 형태의 숫자들 합산
		for(long i = 0; i < count; i++) {
			answer += Long.parseLong(numArray[(int)i]);
		}
		
		// 정답 출력
		System.out.println(answer);
		
		/*
		 * split을 사용하면 문자열을 쉽게 쪼개서 사용가능하다
		 * 다만, 이렇게 내장함수를 남발하는게 맞는지 의문
		 * 
		 * do it 책의 디버깅 파트의 숫자형태는 long형태로 사용하는것을
		 * 습관들이는 부분을 적용해봤는데, 인덱스 번호 지정시 형변환 이슈발생
		 */

	}

}