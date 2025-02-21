package baekjoon

import java.io.BufferedReader

/**
 * BOJ 1012번: 유기농 배추
 */
val dx = intArrayOf(0, 0, 1, -1)
val dy = intArrayOf(1, -1, 0, 0)
var count = 0
fun main() {
    val br = BufferedReader(System.`in`.reader())
    val T = br.readLine().toInt()
    repeat(T) {
        val (M, N, K) = br.readLine().split(" ").map { it.toInt() }
        val visited = Array(M) { Array(N) { false } }
        val map = Array(M) { Array(N) { 0 } }
        for (j in 0 until K) {
            val (x, y) = br.readLine().split(" ").map { it.toInt() }
            map[x][y] = 1
        }

        for (j in 0 until M) {
            for (k in 0 until N) {
                if (map[j][k] == 1 && !visited[j][k]) {
                    count++;
                    dfs(j, k, map, visited)
                }
            }
        }
        println(count)
        count = 0
    }
    br.close()
}

fun dfs(i: Int, j: Int, map: Array<Array<Int>>, visited: Array<Array<Boolean>>) {
    visited[i][j] = true
    for (k in 0 until 4) {
        val nx = i + dx[k]
        val ny = j + dy[k]
        if (nx in map.indices && ny in 0 until map[0].size) {
            if (map[nx][ny] == 1 && !visited[nx][ny]) {
                dfs(nx, ny, map, visited)
            }
        }
    }
}