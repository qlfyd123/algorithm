package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/1759">BOJ1759 암호 만들기</a>
 */
public class BOJ1759 {

    static int L;
    static int C;
    static char[] alphabet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alphabet = br.readLine().replace(" ", "").toCharArray();
        Arrays.sort(alphabet);
        StringBuilder sb = new StringBuilder();
        createPassword(0, sb, new Password());
        System.out.println(sb);
    }

    /**
     * @param index    alphabet 인덱스
     * @param sb       결과를 저장할 stringbuilder
     * @param password 현재까지 만들어진 비밀번호
     */
    public static void createPassword(int index, StringBuilder sb, Password password) {
        if (password.pwd.length() < L) {
            for (; index < C; index++) {
                char c = alphabet[index];
                if (isVowel(c)) {
                    createPassword(index + 1, sb, new Password(password.pwd + c, password.consonantNum, password.vowelNum - 1));
                } else {
                    createPassword(index + 1, sb, new Password(password.pwd + c, password.consonantNum - 1, password.vowelNum));
                }
            }
        } else {
            if (password.vowelNum <= 0 & password.consonantNum <= 0) {
                sb.append(password.pwd).append("\n");
            }
        }
    }

    static class Password {
        String pwd;
        int consonantNum;
        int vowelNum;

        public Password() {
            consonantNum = 2;
            vowelNum = 1;
            this.pwd = "";
        }

        public Password(String password, int consonantNum, int vowelNum) {
            this.pwd = password;
            this.consonantNum = consonantNum;
            this.vowelNum = vowelNum;
        }
    }

    /**
     * @param c 자,모음임을 판단할 문자
     * @return 문자c가 모음인지를 반환
     */
    public static boolean isVowel(char c) {
        switch (c) {
            case 97:
                return true;
            case 101:
                return true;
            case 105:
                return true;
            case 111:
                return true;
            case 117:
                return true;
            default:
                return false;
        }
    }
}
