fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            it.first { it.isDigit() }.digitToInt() * 10 +
            it.last { it.isDigit() }.digitToInt()
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
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val testInput2 = readInput("Day01_test2")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

fun String.fromWordToInt(): Int {
    return when (this) {
        "one" -> 1
        "two" -> 2
        "three" -> 3
        "four" -> 4
        "five" -> 5
        "six" -> 6
        "seven" -> 7
        "eight" -> 8
        "nine" -> 9
        else -> toInt()
    }
}