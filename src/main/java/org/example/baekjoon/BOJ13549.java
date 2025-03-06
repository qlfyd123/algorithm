package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/13549">BOJ13549 숨바꼭질3</a>
 */
public class BOJ13549 {
    static int start, end;
    static boolean[] visited = new boolean[100001];
    static Queue<Location> locationQueue = new LinkedList<>();

    static class Location {
        int count, location;

        public Location(int l, int c) {
            location = l;
            count = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        locationQueue.add(new Location(start, 0));
        while (!locationQueue.isEmpty()) {
            hideAndSeek();
        }
    }

    public static void hideAndSeek() {
        Location location = locationQueue.poll();
        if (location.location < 0 || location.location > 100000 || visited[location.location]) {
            return;
        }
        visited[location.location] = true;
        if (location.location == end) {
            System.out.println(location.count);
            locationQueue.clear();
        } else {
            locationQueue.add(new Location(location.location << 1, location.count));
            locationQueue.add(new Location(location.location - 1, location.count + 1));
            locationQueue.add(new Location(location.location + 1, location.count + 1));
        }
    }
}
