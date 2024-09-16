package chucknorris

fun main() {
    while (true) {
        println("Please input operation (encode/decode/exit):")
        when (val op = readln()) {
            "encode" -> {
                println("Input string:")
                val input = readln()
                println("Encoded string:")
                println("${"0+|1+".toRegex().findAll(
                    input.map { Integer.toBinaryString(it.code).padStart(7, '0') }.joinToString("")
                ).map { "${if (it.value[0] == '0') "00" else "0"} ${"0".repeat(it.value.length)}" }.joinToString(" ")}\n")
            }
            "decode" -> {
                println("Input encoded string:")
                val input = readln()
                if (!input.all { it in "0 " } || input.split(" ").size % 2 != 0) {
                    println("Encoded string is not valid.\n")
                    continue
                }
                val binaryOutput = "(0+ 0+)".toRegex().findAll(input).map {
                    val (digit, count) = it.value.split(" ")
                    when (digit) {
                        "00" -> "0"
                        "0" -> "1"
                        else -> ""
                    }.repeat(count.length)
                }.joinToString("")
                if ((binaryOutput.length % 7) != 0) {
                    println("Encoded string is not valid.\n")
                    continue
                }
                println("Decoded string:\n${binaryOutput.chunked(7).map { it.toInt(2).toChar() }.joinToString("")}\n")
            }
            "exit" -> {
                println("Bye!")
                return
            }
            else -> println("There is no '$op' operation\n")
        }
    }
}