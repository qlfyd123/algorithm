package baekjoon

import java.io.BufferedReader
import java.io.BufferedWriter

/**
 * BOJ 1010번: 다리 놓기(1010)
 * @see <a href="https://www.acmicpc.net/problem/1010">
 * */
fun main() {
    val br = BufferedReader(System.`in`.reader())
    val N = br.readLine().toInt()
    val bw = BufferedWriter(System.out.writer())
    for (i in 0 until N) {
        val (x, y) = br.readLine().split(" ").map { it.toInt() }
        val dpArray = Array(x + 1) { Array(y + 1) { 0 } }
        val result = bridge(x, y, dpArray)
        bw.write("$result\n")
    }
    bw.flush()
    bw.close()
}

fun bridge(x: Int, y: Int, dpArray: Array<Array<Int>>): Int {
    return when (x) {
        1 -> y
        y -> 1
        else -> {
            if (dpArray[x][y] != 0) {
                dpArray[x][y]
            } else {
                var sum = 0
                for (i in 1..y - x + 1) {
                    sum += bridge(x - 1, y - i, dpArray)
                }
                if (dpArray[x][y] == 0) dpArray[x][y] = sum
                sum
            }
        }
    }
}