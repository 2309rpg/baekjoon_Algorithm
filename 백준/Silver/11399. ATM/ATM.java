import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	
    	// 스캐너 생성
        Scanner sc = new Scanner(System.in);
        
        // 변수 정의 및 값 입력
        int N = sc.nextInt();
        int arr[] = new int[N];
        int sum = 0;
        int time = 0;
        
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        
        // 정렬
        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
        	time += arr[i];
            sum += time;
        }
        System.out.println(sum);
    }

}
