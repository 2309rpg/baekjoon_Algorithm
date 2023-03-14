import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	
    	// 스캐너 입력
        Scanner sc = new Scanner(System.in);
        
        // 문자열 입력 받음
        String n = sc.nextLine();
        // 캐릭터 형태로 배열 입력
        char arr[] = n.toCharArray();
        
        // 함수를 이용한 정렬
        Arrays.sort(arr);
        
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.printf("%c",arr[i]);
        }
    }
    
    /*
     * 근데 아마 내장함수 이용해서 풀면 안되나..?
     * */

}
