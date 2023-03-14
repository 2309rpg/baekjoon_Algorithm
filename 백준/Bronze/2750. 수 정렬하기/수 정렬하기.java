import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int arr[] = new int[N];
        int temp = 0;

        // 입력받은 숫자들을 배열에 저장
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        // 버블 정렬을 이용하여 오름차순으로 정렬
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                	temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        // 정렬된 숫자 출력
        for (int i = 0; i < N; i++) {
            System.out.println(arr[i]);
        }
    }
}

