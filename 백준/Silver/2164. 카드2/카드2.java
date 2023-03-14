import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	
    	// 콘솔 입력
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 큐에 카드 숫자 1~n을 넣음
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 1; i <= N; i++) {
        	queue.offer(i);
        }

        // 카드를 버리고 남은 카드의 번호를 구하는 과정
        while (queue.size() > 1) {
        	queue.poll(); // 맨 위에 있는 카드 버리기
        	queue.offer(queue.poll()); // 맨 위에 있는 카드를 맨 아래로 옮기기
        }

        // 마지막에 남은 카드 출력
        System.out.println(queue.poll());
    }
}