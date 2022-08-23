package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;
/*
* https://www.acmicpc.net/problem/20946
* */
public class baekjoon_20946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());
        Queue<Long> primeFactor = new PriorityQueue<>(Collections.reverseOrder());

        int index = 2;
        //N의 제곱근보다 큰 인수는 존재할 수 없기 때문에 index의 최대값은 N의 제곱근임
        long limit = (long) Math.sqrt(N);
        int temp = 0;
        //N이 인수에 의해 나누어 떨어질때까지 실행
        while (N > 1) {
            if (index > limit) {
                break;
            }
            //N이 index에 의해 나누어 떨어질 경우
            if (N % index == 0) {
                N /= index;
                //이 문제의 경우 합성 소인수 분해이므로 이전의 index값을 temp변수에 저장해놓았다가 2의 배수번째로
                //나누어 떨어지는 index와 곱해서 우선순위 큐에 저장
                if (temp == 0) {
                    temp = index;
                } else {
                    primeFactor.add((long) temp * index);
                    temp = 0;
                }
            } else {
                index++;
            }
        }
        //만약 위의 루프문이 N이 제곱근까지 실행되었는데도 N이 1보다 클 경우 남아있는 수는 소수임
        if (N != 1) {
            //현재까지 발생한 인수의 개수가 홀수라면 temp의 값이 남아있으므로 temp의 값과 N의 값을 곱해서 우선순위큐에 저장
            if (temp != 0) {
                primeFactor.add(temp * N);
            } else {
                //홀수개라면 우선순위 큐에서 값을 받아와 곱한후 다시 저장
                if (!primeFactor.isEmpty()) {
                    long multi = primeFactor.poll();
                    multi *= N;
                    primeFactor.add(multi);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (primeFactor.size() < 2) {
            System.out.println(-1);
        } else {
            Stream.generate(primeFactor::poll)
                    .limit(primeFactor.size())
                    .filter(Objects::nonNull)
                    .map(Object::toString)
                    .forEach(e -> sb.insert(0, e).insert(0, " "));
            System.out.println(sb.toString().trim());
        }
    }
}
