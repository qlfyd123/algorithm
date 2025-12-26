package org.example.baekjoon.BOJ2170;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		List<Line> list = new ArrayList<>();

		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.add(new Line(start, end));
		}

		list.sort(Comparator.comparingInt(line -> line.start));

		Stack<Line> connectedLines = new Stack<>();
		for (Line line : list) {
			if (connectedLines.isEmpty()) {
				connectedLines.push(line);
				continue;
			}

			Line lastConnectedLine = connectedLines.peek();
			if (lastConnectedLine.end >= line.start) {
				lastConnectedLine.end = Math.max(lastConnectedLine.end, line.end);
			} else {
				connectedLines.push(line);
			}
		}


		int totalLineLange = 0;
		for (Line line : connectedLines) {
			totalLineLange += (line.end - line.start);
		}

		System.out.println(totalLineLange);
	}


	private static class Line {
		int start;
		int end;

		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}
