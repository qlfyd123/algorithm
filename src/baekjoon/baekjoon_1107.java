package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/1107"/> 백준1107</a>
 */
public class baekjoon_1107 {
    static int[] except;
    static int N;

    private static boolean isPossible(int n) {
        for (int i : String.valueOf(n).toCharArray()) {
            int temp = i - '0';
            for (int j : except) {
                if (temp == j) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        except = new int[Integer.parseInt(br.readLine())];
        if (except.length == 0) {
            if (N == 0) {
                System.out.println(1);
            } else {
                System.out.println(Math.min((int) Math.log10(N) + 1, Math.abs(N - 100)));
            }
            return;
        } else if (N == 100) {
            System.out.println(0);
            return;
        }
        StringTokenizer exceptionNumber = new StringTokenizer(br.readLine());
        for (int i = 0; i < except.length; i++) {
            except[i] = Integer.parseInt(exceptionNumber.nextToken());
        }
        int ans = Math.abs(N - 100);
        if (isPossible(0)) {
            ans = Math.min(ans, N + 1);
        }
        for (int i = 1; i < 1000000; i++) {
            if (isPossible(i)) {
                ans = Math.min(ans, (int) (Math.log10(i) + 1 + Math.abs(i - N)));
            }
        }
        System.out.println(ans);
    }
}
