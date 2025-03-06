package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class baekjoon_18231 {
    static HashMap<Integer, Set<Integer>> cityMap = new HashMap<>();
    static Set<Integer> destroyedCity = new HashSet<>();
    static Set<Integer> destroyedCityInCase = new HashSet<>();
    static Set<Integer> answer = new HashSet<>();

    /**
     * @param left stringTokenizer의 첫번째 값
     * @param right stringTokenizer의 두번째 값
     * @return left, right 값을 초기값으로 하는 HashSet를 리턴
     */
    public static Set<Integer> newCitySet(int left, int right) {
        Set<Integer> set = new HashSet<>();
        set.add(left);
        set.add(right);
        return set;
    }


    /**
     * stringTokenizer로 입력받은 연결된 도시들을 left값과 right값을 키값으로 하는 cityMap에 저장합니다
     */
    public static void insertIntoCitySet(StringTokenizer st) {
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());

        if (cityMap.containsKey(left)) {
            cityMap.get(left).add(right);
        } else {
            cityMap.put(left, newCitySet(left, right));
        }

        if (cityMap.containsKey(right)) {
            cityMap.get(right).add(left);
        } else {
            cityMap.put(right, newCitySet(left, right));
        }
    }

    /**
     * @param city cityMap key value
     * @return city를 키값으로 하는 Set의 값 중에서 destroyedCity에 존재하지 않는 값이 있을경우 false를 리턴하고 그렇지 않을경우 true를 리턴합니다
     */
    public static boolean isInRange(int city) {
        Set<Integer> set = cityMap.get(city);
        for (int i : set) {
            if (!destroyedCity.contains(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param city cityMap key value
     */
    public static void boom(int city) {
        Set<Integer> set = cityMap.get(city);
        destroyedCityInCase.addAll(set);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int cityNum = Integer.parseInt(st.nextToken());
        int roadNum = Integer.parseInt(st.nextToken());


        while (roadNum-- > 0) {
            st = new StringTokenizer(br.readLine());
            insertIntoCitySet(st);
        }

        int T = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < T; i++) {
            destroyedCity.add(Integer.parseInt(st.nextToken()));
        }

        for (int city : cityMap.keySet()) {
            if (isInRange(city)) {
                boom(city);
                answer.add(city);
            }
        }

        destroyedCity.stream()
                .filter(e -> !cityMap.containsKey(e))
                .forEach(e -> {
                    answer.add(e);
                    destroyedCityInCase.add(e);
                });

        if (destroyedCityInCase.containsAll(destroyedCity)) {
            System.out.println(answer.size());
            StringBuilder sb = new StringBuilder();
            answer.stream().collect(Collectors.toList()).stream().sorted().forEach(e -> sb.append(e).append(" "));
            System.out.println(sb.toString().trim());
        } else {
            System.out.println(-1);
        }
    }
}
