package org.example.baekjoon

val br = System.`in`.bufferedReader()
val N: Int = br.readLine().toInt()
val arr: Array<Int> = br.readLine().split(" ").map { it.toInt() }.toTypedArray()
val M: Int = br.readLine().toInt()
val operation: List<Pair<Int, Int>> by lazy {
    val list: MutableList<Pair<Int, Int>> = mutableListOf()
    br.lines()
        .limit(M.toLong())
        .map { it.split(" ").map { it.toInt() } }.forEach {
            list.add(Pair(it[0] - 1, it[1] - 1))
        }
    list
}
val bw = System.out.bufferedWriter()
val dp: Array<BooleanArray> = Array(N) { BooleanArray(N) }
fun main() {
    for (i in 0 until N) {
        dp[i][i] = true
    }
    for (i in 0 until N - 1) {
        dp[i][i + 1] = arr[i] == arr[i + 1]
    }
    for (length in 2 until N) {

        for (i in 0 until N - length) {
            val left = i;
            val right = i + length
            dp[left][right] = (dp[left + 1][right - 1] && arr[left] == arr[right])
        }
    }

    for (pair in operation) {
        if (dp[pair.first][pair.second]) {
            bw.write("1\n")
        } else {
            bw.write("0\n")
        }
    }
    bw.flush()
    bw.close()
}
