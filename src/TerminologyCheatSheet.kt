import java.io.File

var questionsAndAnswersMap = mapOf<String,String>()

fun main(args: Array<String>) {
    //read the questions file
    readQuestionsFile()
    //loop start
    //get random question/answer from map
    //get 3 more random answers for answer variety
    //display and wait for user choice
    //compare user choice with answer
    //loop again
}

fun readQuestionsFile() {
    //get questions file
    val reader = File("src/questions")

    //build map
    questionsAndAnswersMap = reader.readLines().chunked(2) {
        it[0] to it.getOrNull(1).toString()
    }.toMap()
}