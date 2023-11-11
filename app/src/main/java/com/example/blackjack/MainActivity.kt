package com.example.blackjack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Scanner

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val game = BlackJack();

        val hit: Button = findViewById(R.id.hitButton)
        val playerText: TextView = findViewById(R.id.playerHand)
        val computerText: TextView = findViewById(R.id.computerHand)


        hit.setOnClickListener {
            playTurn(game, playerText, computerText)
            playerText.setText(game.playerHand.toString())
            computerText.setText(game.compHand.toString())
        }
    }
}

fun playTurn(game: BlackJack, playerText: TextView, computerText: TextView) {
    game.dealCardPlayer(game.generateRandom())
    game.dealCardComp(game.generateRandom())

    // Copy Paster
    if (game.blackJack(game.compHand) && game.blackJack(game.playerHand)) {
//        println("The Computer Has a hand of: ")
//        game.printHand(game.compHand)
//        println()
//        println("Your Hand is: ")
//        game.printHand(game.playerHand)
//        println("")
//        println("IT WAS A DRAW because of two Black Jack Cases")

        playerText.setText(game.playerHand.toString())
        computerText.setText(game.compHand.toString())

    } else if (game.blackJack(game.compHand)) {
//        println("The Computer Has a hand of: ")
//        game.printHandComp(game.compHand)
//        println()
//        println("Your Hand is: ")
//        game.printHand(game.playerHand)
//        println("The Computer wins by Black Jack")

        playerText.setText(game.playerHand.toString())
        computerText.setText(game.compHand.toString())

    } else if (game.blackJack(game.playerHand)) {
//        println("The Computer Has a hand of: ")
//        game.printHandComp(game.compHand)
//        println()
//        println("Your Hand is: ")
//        game.printHand(game.playerHand)
//        println()
//        println("The Player wins by Black Jack")

        playerText.setText(game.playerHand.toString())
        computerText.setText(game.compHand.toString())

    } else {
        while (game.playingFlag) {
//            println("The Computer Has a hand of: ")
//            game.printHandComp(game.compHand)
//            println()
//            println("Your Hand is: ")
//            game.printHand(game.playerHand)

            playerText.setText(game.playerHand.toString())
            computerText.setText(game.compHand.toString())

            // Players Choice
//            print("\nWould you like to HIT?: (y/n) ")
//            if (input.nextLine() == "y") game.dealCardPlayer(game.generateRandom()) else isStanding =
//                true
            if (game.playerHandSum > 21) {
//                println("DEALERS FINAL HAND: ")
//                game.printHand(game.compHand)
                computerText.setText(game.compHand.toString())
                println(
                    """

    COMP SUM: ${game.compHandSum}
    """.trimIndent()
                )
//                println("PLAYERS FINAL HAND: ")
                playerText.setText(game.playerHand.toString())
//                println(
                    """

    PLAYER SUM: ${game.playerHandSum}
    """.trimIndent()
////                )
//                println("\nTHE COMPUTER WINS!")
                game.computerScore++
                game.resetValue = true
            }
            if (game.isStanding) {
                // Reveal Dealers cards
//                println("DEALERS FINAL HAND: ")
//                game.printHand(game.compHand)
                playerText.setText(game.playerHand.toString())
//                println(
                    """

    COMP SUM: ${game.compHandSum}
    """.trimIndent()
//                )
//                println("PLAYERS FINAL HAND: ")
//                game.printHand(game.playerHand)
                playerText.setText(game.playerHand.toString())
//                println(
                    """

    PLAYER SUM: ${game.playerHandSum}
    """.trimIndent()
//                )
                val outcome = game.compareHands()

                // 0 - Player, 1 - Computer, 2 - Draw
                if (outcome == 2) {
//                    Toast.makeText(applicationContext,"IT WAS A DRAW :/", Toast.LENGTH_SHORT).show()
                } else if (outcome == 1) {
//                    Toast.makeText(applicationContext, "THE COMPUTER WON :(", Toast.LENGTH_SHORT).show()
                } else {
//                    Toast.makeText(getContext(), "THE PLAYER WON :)", Toast.LENGTH_SHORT).show()
                }
                game.resetValue = true
            } else {

                // Computers Choice
                if (game.shouldCompHit()) {
                    game.dealCardComp(game.generateRandom())
                    if (game.compHandSum > 21) {
                        computerText.setText(game.compHand.toString())
//                        game.printHand(game.compHand)
//                        println(
                            """

    COMP SUM: ${game.compHandSum}
    """.trimIndent()
//                        )
//                        println("PLAYERS FINAL HAND: ")
////                        game.printHand(game.playerHand)
//                        println(
                        playerText.setText(game.playerHand.toString())
                            """

    PLAYER SUM: ${game.playerHandSum}
    """.trimIndent()
//                        )
                        println("\nTHE PLAYER WINS!")
                        game.playerScore++
                        game.resetValue = !game.resetValue
                    }
                }
//                println("\n")
            }
//            if (game.resetValue) {
//                println()
//                println("COMPUTER SCORE: " + game.computerScore)
//                println("PLAYER SCORE: " + game.playerScore)
//                print("Would You like to play again? (y/n): ")
//                val choice = input.nextLine()
//                if (choice == "n") game.playingFlag = false else {
//                    val ps = game.playerScore
//                    val cs = game.computerScore
//                    game = BlackJack()
//                    game.playerScore = ps
//                    game.computerScore = cs
//                    game.isStanding = false
//                    game.resetValue = false
//                }
//                println()
//            }
        }
    }
}

fun updateText(text: String, thing: TextView) {
    thing.setText(text)
}

fun updateScreen() {

}



