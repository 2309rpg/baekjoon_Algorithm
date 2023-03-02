import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

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
    }
}