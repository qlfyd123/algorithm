package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ2504 {
    static char[] input;
    static Stack<Character> brackets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        brackets = new Stack<>();
        int total = 0;
        int multiplier = 1;
        for (int i = 0; i < input.length; i++) {
            char c = input[i];
            switch (c) {
                case '[':
                    multiplier *= 3;
                    brackets.push(c);
                    break;
                case '(':
                    multiplier *= 2;
                    brackets.push(c);
                    break;
                case ']':
                    if (brackets.isEmpty() || brackets.peek() != '[') {
                        System.out.println(0);
                        return;
                    } else {
                        if (input[i - 1] == '[') {
                            total += multiplier;
                        }
                        multiplier /= 3;
                        brackets.pop();
                    }
                    break;
                case ')':
                    if (brackets.empty() || brackets.peek() != '(') {
                        System.out.println(0);
                        return;
                    } else {
                        if (input[i - 1] == '(') {
                            total += multiplier;
                        }
                        multiplier /= 2;
                        brackets.pop();
                    }
                    break;
            }
        }
        System.out.println(brackets.empty() ? total : 0);
    }
}
