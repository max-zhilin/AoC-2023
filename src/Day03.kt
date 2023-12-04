fun main() {
    fun List<String>.isFree(line: Int, start: Int, end: Int): Boolean {
        val thisLine = this[line]
        val lastIndex = thisLine.lastIndex

        var topClear = true
        if (line != 0) {
            for (i in (start - 1).coerceAtLeast(0)..(end + 1).coerceAtMost(lastIndex)) {
                with(this[line - 1][i]) {
                    if (this != '.' && !this.isDigit()) {
                        topClear = false
                        //break
                    }
                }
            }
        }
        var bottomClear = true
        if (line != this.lastIndex) {
            for (i in (start - 1).coerceAtLeast(0)..(end + 1).coerceAtMost(lastIndex)) {
                with(this[line + 1][i]) {
                    if (this != '.' && !this.isDigit()) {
                        bottomClear = false
                        //break
                    }
                }
            }
        }
        val leftClear = if (start == 0) true else thisLine[start - 1] == '.'
        val rightClear = if (end == lastIndex) true else thisLine[end + 1] == '.'
        return (topClear && bottomClear && leftClear && rightClear)
    }

    fun part1(input: List<String>): Int {
        val regex = """\d+""".toRegex()
        return input.mapIndexed { line, s ->

            regex.findAll(s)
                .sumOf { if (input.isFree(line, it.range.first, it.range.last)) 0 else it.value.toInt() }

        }.sumOf { it }
    }

    fun part2(input: List<String>): Int {
        val digits = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
            , "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        return input.map {
            it.findAnyOf(digits)!!.second.fromWordToInt() * 10 +
            it.findLastAnyOf(digits)!!.second.fromWordToInt()
        }.sumOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)

    val testInput2 = readInput("Day01_test2")
    check(part2(testInput2) == 281)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
