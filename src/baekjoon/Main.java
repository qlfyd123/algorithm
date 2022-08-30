package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static HashMap<Integer, Set<Integer>> cityMap = new HashMap<>();
    static Set<Integer> destroyedCity = new HashSet<>();
    static Set<Integer> destroyedCityInCase = new HashSet<>();
    static Set<Integer> answer = new HashSet<>();

    public static Set<Integer> newCitySet(int left, int right) {
        Set<Integer> set = new HashSet<>();
        set.add(left);
        set.add(right);
        return set;
    }


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

    public static boolean isInRange(int city) {
        Set<Integer> set = cityMap.get(city);
        for (int i : set) {
            if (!destroyedCity.contains(i)) {
                return false;
            }
        }
        return true;
    }

    public static void boom(Set<Integer> city) {
        destroyedCityInCase.addAll(city);
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
                boom(cityMap.get(city));
                answer.add(city);
            }
        }

        if (!destroyedCity.containsAll(destroyedCityInCase)) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer.size());
        StringBuilder sb = new StringBuilder();
        answer.forEach(e -> sb.append(e).append(" "));

        System.out.println(sb.toString().trim());
    }
}
