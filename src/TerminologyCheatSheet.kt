import java.io.File
import java.lang.Exception
import java.lang.NumberFormatException
import kotlin.random.Random.Default.nextInt

var questionsAndAnswersMap = mapOf<String, String>()
const val answerListLength = 4

fun main(args: Array<String>) {
    //read the questions file
    readQuestionsFile()

    //loop questions indefinitely until break
    mainLoop@ while (true) {
        //get random question/answer from map
        fun randomIndex(): Int = nextInt(1, questionsAndAnswersMap.size) //get random index
        val questionAndAnswer = questionsAndAnswersMap.entries.elementAt(randomIndex())//get question and answer grouping
        val question = questionAndAnswer.key //get question
        val answer = questionAndAnswer.value //get answer

        //get 3 more random answers for answer variety
        val answerList = mutableListOf<String>()

        answerList.add(answer) //add correct answer to list

        //add answer at random index to list
        for (i in 1..answerListLength) {
            val answerTemp = questionsAndAnswersMap.entries.elementAt(randomIndex()).value
            answerList.add(answerTemp)
        }

        answerList.shuffle() //shuffle list

        //display and wait for user choice
        println("\nQuestion: $question")
        for (j in 1 until answerList.size) {
            println("$j: ${answerList[j]}")
        }

        //for break and program exit
        println("${answerListLength + 1}: Exit Program")

        var userAnswer: Int = 0

        //try to get user answer, if the format of answer is not a number then back to loop
        try {
            userAnswer = readLine()!!.toInt() //wait for user input
        } catch (e: Exception) {
            println("Incorrect answer format!")
            continue@mainLoop
        }

        //if user answer if 5, then beak from program
        if (userAnswer == answerListLength + 1) {
            break
        }

        //compare user choice with answer
        if (answerList[userAnswer] == answer) {
            println("\n\n$answer was the correct answer! Well done!")
        } else {
            println("\n\n${answerList[userAnswer]} was not the correct answer, $answer was! Better luck next time...")
        }
    }

    println("\nProgram End...")
}

fun readQuestionsFile() {
    //get questions file
    val reader = File("src/questions")

    //build map
    questionsAndAnswersMap = reader.readLines().chunked(2) {
        it[0] to it.getOrNull(1).toString()
    }.toMap()
}