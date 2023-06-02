package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/16234">BOJ 16234 인구 이동</a>
 * */
public class BOJ16234 {
    static class Country {
        int row, column;
        int population;

        public Country(int row, int column, int population) {
            this.row = row;
            this.column = column;
            this.population = population;
        }

        public boolean canUnion(Country other, Union union) {
            int populationDiff = Math.abs(this.population - other.population);
            return populationDiff <= union.upperBound & populationDiff >= union.lowerBound;
        }
    }

    static class Union {
        int lowerBound, upperBound, countryNum, totalPopulation;
        Set<Country> unionCountries;

        public Union(int lowerBound, int upperBound, Country country) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            countryNum = 0;
            totalPopulation = 0;
            unionCountries = new HashSet<>();
            except(country);
        }

        public void except(Country country) {
            countryNum++;
            totalPopulation += country.population;
            unionCountries.add(country);
        }

    }

    public static void union(Union union, Country country, boolean[][] visited, int[][] countries) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        visited[country.row][country.column] = true;
        for (int i = 0; i < 4; i++) {
            if (inBound(country.row + dx[i], country.column + dy[i], visited.length)) {
                Country nearCountry = new Country(country.row + dx[i], country.column + dy[i], countries[country.row + dx[i]][country.column + dy[i]]);
                if (!visited[nearCountry.row][nearCountry.column]) {
                    if (country.canUnion(nearCountry, union)) {
                        union.except(nearCountry);
                        union(union, nearCountry, visited, countries);
                    }
                }
            }
        }
    }

    public static boolean inBound(int row, int column, int size) {
        if (row < 0 | row >= size) {
            return false;
        } else return (column >= 0 & column < size);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        final int lowerBound = Integer.parseInt(st.nextToken());
        final int upperBound = Integer.parseInt(st.nextToken());

        int[][] countries = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                countries[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[][] visited = new boolean[N][N];
        boolean hasUnion;
        int day = 0;
        do {
            hasUnion = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        Country country = new Country(i, j, countries[i][j]);
                        Union union = new Union(lowerBound, upperBound, country);
                        union(union, country, visited, countries);
                        int averagePopulation = union.totalPopulation / union.countryNum;
                        if (union.countryNum > 1) {
                            hasUnion = true;
                            for (Country c : union.unionCountries) {
                                countries[c.row][c.column] = averagePopulation;
                            }
                        }
                    }
                }
            }
            if (hasUnion) {
                day++;
                visited = new boolean[N][N];
            }
        } while (hasUnion);
        System.out.println(day);
    }
}
