package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/14891">14891번 톱니바퀴</a>
 * */
public class BOJ14891 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Gear[] gears = new Gear[4];

        for (int i = 0; i < 4; i++) {
            gears[i] = new Gear(br.readLine());
        }

        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                gears[i].rightGear = gears[i + 1];
            } else if (i == 3) {
                gears[i].leftGear = gears[i - 1];
            } else {
                gears[i].leftGear = gears[i - 1];
                gears[i].rightGear = gears[i + 1];
            }
        }

        int K = Integer.parseInt(br.readLine());

        int score = 0;
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int targetGearIndex = Integer.parseInt(st.nextToken()) - 1;
            Gear targetGear = gears[targetGearIndex];
            int direction = Integer.parseInt(st.nextToken());
            if (direction == DIRECTION.CLOCKWISE.getValue()) {
                targetGear.rotateGearClockwise();
            } else {
                targetGear.rotateGearCounterClockwise();
            }
        }
        score += calculateScore(gears);
        System.out.println(score);
    }

    private static int calculateScore(Gear[] gears) {
        int score = 0;
        for (int i = 0; i < gears.length; i++) {
            Gear gear = gears[i];
            if (gear.magnetic.getFirst() == 1) {
                score += (1 << i);
            }
        }
        return score;
    }

    private enum DIRECTION {
        CLOCKWISE(1), COUNTER_CLOCKWISE(-1);

        private final int value;

        DIRECTION(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private static class Gear {

        Gear leftGear;
        Gear rightGear;
        Deque<Integer> magnetic;
        boolean isRotated;

        public Gear(String magnetic) {
            this.magnetic = new ArrayDeque<>();
            for (char c : magnetic.toCharArray()) {
                this.magnetic.addLast(c - '0');
            }
            this.isRotated = false;
        }

        public int get3MagneticValue() {
            Iterator<Integer> iterator = magnetic.iterator();
            iterator.next();
            iterator.next();
            return iterator.next();
        }

        public int get9MagneticValue() {
            Iterator<Integer> iterator = magnetic.descendingIterator();
            iterator.next();
            return iterator.next();
        }

        public void rotateGearClockwise() {
            if (this.isRotated) {
                return;
            }
            isRotated = true;
            if (rightGear != null) {
                if (rightGear.get9MagneticValue() != get3MagneticValue()) {
                    rightGear.rotateGearCounterClockwise();
                }
            }

            if (leftGear != null) {
                if (leftGear.get3MagneticValue() != get9MagneticValue()) {
                    leftGear.rotateGearCounterClockwise();
                }
            }
            this.magnetic.addFirst(this.magnetic.pollLast());
            isRotated = false;
        }

        public void rotateGearCounterClockwise() {
            if (isRotated) {
                return;
            }
            isRotated = true;
            if (rightGear != null) {
                if (rightGear.get9MagneticValue() != get3MagneticValue()) {
                    rightGear.rotateGearClockwise();
                }
            }

            if (leftGear != null) {
                if (leftGear.get3MagneticValue() != get9MagneticValue()) {
                    leftGear.rotateGearClockwise();
                }
            }
            this.magnetic.addLast(this.magnetic.pollFirst());
            isRotated = false;
        }

    }

}
