import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
    	
    	// 콘솔 입력을 위해 버퍼리더 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
    	// 사용할 변수 초기화 및 입력
        int count = 0;
        int sum = 0;
        int left = 0;
        for (int right = 1; right <= N; right++) {
            sum += right;
            while (sum > N) {
                sum -= left++;
            }
            if (sum == N) {
                count++;
            }
        }

        System.out.println(count);
        
        /*
         * 두 개의 포인터(left, right)와 합(sum) 이용
         * right 포인터는 수열에서 현재 위치, left 포인터는 현재 위치의 왼쪽 끝
         * sum 변수는 left부터 right까지의 수들의 합을 저장
		 * right 포인터를 1부터 N까지 이동, sum 변수에 현재 위치의 값을 더함 
		 * 이후, sum이 N을 초과하면 left 포인터를 오른쪽으로 이동시키며 sum에서 left 위치의 값을 빼줌
		 * 이 과정을 반복하여 sum이 N과 같아지면 count 1 증가
		 * 모든 right 위치에 대해 이러한 과정을 수행, count 출력
		 * */
    }
}