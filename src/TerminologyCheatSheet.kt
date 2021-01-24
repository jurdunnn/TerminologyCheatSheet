import java.io.File
import kotlin.random.Random.Default.nextInt

var questionsAndAnswersMap = mapOf<String, String>()
const val answerListLength = 4

fun main(args: Array<String>) {
    //read the questions file
    readQuestionsFile()

    //loop questions indefinitely until break
    while (true) {
        //get random question/answer from map
        fun randomIndex(): Int = nextInt(1, questionsAndAnswersMap.size) //get random index
        val questionAndAnswer = questionsAndAnswersMap.entries.elementAt(randomIndex())//get question and answer grouping
        val question = questionAndAnswer.key //get question
        val answer = questionAndAnswer.value //get answer

        //get 3 more random answers for answer variety
        val additionalAnswers = mutableListOf<String>()

        additionalAnswers.add(answer) //add correct answer to list

        for (i in 1..answerListLength) {
            val possibleAdAnswer = questionsAndAnswersMap.entries.elementAt(randomIndex()).value
            additionalAnswers.add(possibleAdAnswer)
        }

        additionalAnswers.shuffle() //shuffle list

        //display and wait for user choice
        println("\nQuestion: $question")
        for (j in 1 until additionalAnswers.size) {
            println("$j: ${additionalAnswers[j]}")
        }

        //for break and program exit
        println("5: Exit Program")

        val userAnswer: Int = readLine()!!.toInt() //wait for user input

        //if user answer if 5, then beak from program
        if(userAnswer == 5) {
            break
        }

        //compare user choice with answer
        if (additionalAnswers[userAnswer] == answer) {
            println("\n\n$answer was the correct answer! Well done!")
        } else {
            println("\n\n${additionalAnswers[userAnswer]} was not the correct answer, $answer was! Better luck next time...")
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