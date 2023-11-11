package com.example.blackjack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.Scanner

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var playingFlag = true // if 'q' quits game
        var isStanding = false
        var resetValue = false

        val game = BlackJack();

        val hit: Button = findViewById(R.id.hitButton)
        val playerText: TextView = findViewById(R.id.playerHand)
        val computerText: TextView = findViewById(R.id.computerHand)


        hit.setOnClickListener {
            playTurn(game)
            playerText.setText(game.playerHand.toString())
            computerText.setText(game.compHand.toString())
        }
    }
}

fun playTurn(game: BlackJack) {
//    game.dealCardPlayer(game.generateRandom())
//    game.dealCardComp(game.generateRandom())
//
//    // Copy Paster
//    if (game.blackJack(game.compHand) && game.blackJack(game.playerHand)) {
//        println("The Computer Has a hand of: ")
//        game.printHand(game.compHand)
//        println()
//        println("Your Hand is: ")
//        game.printHand(game.playerHand)
//        println("")
//        println("IT WAS A DRAW because of two Black Jack Cases")
//    } else if (game.blackJack(game.compHand)) {
//        println("The Computer Has a hand of: ")
//        game.printHandComp(game.compHand)
//        println()
//        println("Your Hand is: ")
//        game.printHand(game.playerHand)
//        println("The Computer wins by Black Jack")
//    } else if (game.blackJack(game.playerHand)) {
//        println("The Computer Has a hand of: ")
//        game.printHandComp(game.compHand)
//        println()
//        println("Your Hand is: ")
//        game.printHand(game.playerHand)
//        println()
//        println("The Player wins by Black Jack")
//    } else {
//        while (playingFlag) {
//            println("The Computer Has a hand of: ")
//            game.printHandComp(game.compHand)
//            println()
//            println("Your Hand is: ")
//            game.printHand(game.playerHand)
//
//            // Players Choice
////            print("\nWould you like to HIT?: (y/n) ")
////            if (input.nextLine() == "y") game.dealCardPlayer(game.generateRandom()) else isStanding =
////                true
//            if (game.playerHandSum > 21) {
//                println("DEALERS FINAL HAND: ")
//                game.printHand(game.compHand)
//                println(
//                    """
//
//    COMP SUM: ${game.compHandSum}
//    """.trimIndent()
//                )
//                println("PLAYERS FINAL HAND: ")
//                game.printHand(game.playerHand)
//                println(
//                    """
//
//    PLAYER SUM: ${game.playerHandSum}
//    """.trimIndent()
//                )
//                println("\nTHE COMPUTER WINS!")
//                game.computerScore++
//                resetValue = true
//            }
//            if (isStanding) {
//                // Reveal Dealers cards
//                println("DEALERS FINAL HAND: ")
//                game.printHand(game.compHand)
//                println(
//                    """
//
//    COMP SUM: ${game.compHandSum}
//    """.trimIndent()
//                )
//                println("PLAYERS FINAL HAND: ")
//                game.printHand(game.playerHand)
//                println(
//                    """
//
//    PLAYER SUM: ${game.playerHandSum}
//    """.trimIndent()
//                )
//                val outcome = game.compareHands()
//
//                // 0 - Player, 1 - Computer, 2 - Draw
//                if (outcome == 2) {
//                    println("IT WAS A DRAW :/")
//                } else if (outcome == 1) {
//                    println("THE COMPUTER WON :(")
//                } else {
//                    println("THE PLAYER WON :)")
//                }
//                resetValue = true
//            } else {
//
//                // Computers Choice
//                if (game.shouldCompHit()) {
//                    game.dealCardComp(game.generateRandom())
//                    if (game.compHandSum > 21) {
//                        println("DEALERS FINAL HAND: ")
//                        game.printHand(game.compHand)
//                        println(
//                            """
//
//    COMP SUM: ${game.compHandSum}
//    """.trimIndent()
//                        )
//                        println("PLAYERS FINAL HAND: ")
//                        game.printHand(game.playerHand)
//                        println(
//                            """
//
//    PLAYER SUM: ${game.playerHandSum}
//    """.trimIndent()
//                        )
//                        println("\nTHE PLAYER WINS!")
//                        game.playerScore++
//                        resetValue = !resetValue
//                    }
//                }
//                println("\n")
//            }
//            if (resetValue) {
//                println()
//                println("COMPUTER SCORE: " + game.computerScore)
//                println("PLAYER SCORE: " + game.playerScore)
//                print("Would You like to play again? (y/n): ")
//                val choice = input.nextLine()
//                if (choice == "n") playingFlag = false else {
//                    val ps = game.playerScore
//                    val cs = game.computerScore
//                    game = BlackJack()
//                    game.playerScore = ps
//                    game.computerScore = cs
//                    isStanding = false
//                    resetValue = false
//                }
//                println()
//            }
//        }
//    }
}

fun updateText(text: String, thing: TextView) {
    thing.setText(text)
}

fun updateScreen() {

}



