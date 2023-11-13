package com.example.blackjack

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.finishAffinity
import java.util.Scanner

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val game = BlackJack();

        val hitButton: Button = findViewById(R.id.hitButton)
        val standButton: Button = findViewById(R.id.standButton)

        val playerText: TextView = findViewById(R.id.playerHand)
        val computerText: TextView = findViewById(R.id.computerHand)

        val computerScore: TextView = findViewById(R.id.computerScore)
        val playerScore: TextView = findViewById(R.id.playerScore)

        // Initial Printing of both hands
        playerText.text = game.printHand(game.playerHand)
        computerText.text = game.printHandComp(game.compHand)

        // Initial Check For Black Jack Condition
        checkBlackJack(game, this, this, playerText, computerText, computerScore, playerScore)


        // hit Button -- Progresses the game
        hitButton.setOnClickListener {
            playTurn(game, playerText, computerText, this, this, computerScore, playerScore)
        }

        // stand Button -- When player is done playing -- compares hands and such
        standButton.setOnClickListener {
            endDialog(this, this, game.compareHands(), game, playerText, computerText, computerScore, playerScore)
        }
    }
}

fun checkBlackJack(game: BlackJack, context: Context, activity: Activity, playerText: TextView, computerText: TextView,
                   computerScore: TextView, playerScore: TextView) {
    if (game.blackJack(game.compHand) && game.blackJack(game.playerHand)) {
        endDialog(context, activity, "Both of you", game, playerText, computerText, computerScore, playerScore)
    }
    else if (game.blackJack(game.compHand)) {
        endDialog(context, activity, "Computer", game, playerText, computerText,computerScore,playerScore)
    }
    else if (game.blackJack(game.playerHand)) {
        endDialog(context, activity, "Player", game, playerText, computerText,computerScore,playerScore)
    }
}

fun endDialog(context: Context, activity: Activity, winner: String, game: BlackJack, playerText: TextView, computerText: TextView,
              computerScore: TextView, playerScore: TextView) {
    /*change the dealer facedown card to the image of whatever card
     *it actually is to show user at the end of the game*/


    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
    builder
        .setMessage("The $winner won!\n" +
                    "Computer's Hand("+ game.compHandSum + "): " + game.printHand(game.compHand) +
                    "\nYour Hand(" + game.playerHandSum + "): " + game.printHand(game.playerHand))
        .setTitle("Would You Like to Play Again?")
        .setPositiveButton("Yes") { dialog, which ->
            reset(game, playerText, computerText) // Reset game and Screen
            computerScore.text = "Computer Score: " + game.computerScore
            playerScore.text = "Player Score: " + game.playerScore
            dialog.dismiss();
            checkBlackJack(game, context, activity, playerText, computerText, computerScore, playerScore)
        }
        .setNegativeButton("No") { dialog, which ->
            activity.finish()
        }

    val dialog: AlertDialog = builder.create()
    dialog.setCanceledOnTouchOutside(false)
    dialog.show()
}

fun reset(game: BlackJack, playerText: TextView, computerText: TextView) {
    game.reset()
    playerText.text = game.printHand(game.playerHand)
    computerText.text = game.printHandComp(game.compHand)
    /*Have code here where you reset all the image view for user and dealer
     *You would set the dealer to first card face down then second card up
     *then show both card for player*/

}

fun playTurn(game: BlackJack, playerText: TextView, computerText: TextView, context: Context, activity: Activity, computerScore: TextView,
             playerScore: TextView) {


    // Deals next card to Player
    game.dealCardPlayer(game.generateRandom())
    playerText.text = game.printHand(game.playerHand)
    /*have code here to put next image in view for player
     *depending on the card it is get the right image*/


    // Check to see if player busts
    if(game.playerHandSum > 21) {
        endDialog(context, activity, "Computer", game, playerText, computerText, computerScore, playerScore)
        game.computerScore++;
        return
    }

    // Decides if computer takes a card or not
    if(game.compHandSum < 16) {
        game.dealCardComp(game.generateRandom())
        computerText.text = game.printHandComp(game.compHand)
        /*make sure to add an image to the dealer next image view
        *based on the card they take*/

    }

    // Check to see if computer busts
    if(game.compHandSum > 21) {
        endDialog(context, activity, "Player", game, playerText, computerText, computerScore, playerScore)
        game.playerScore++;
        return
    }

    // Check to see if Draw
    if(game.playerHandSum == game.compHandSum)
        endDialog(context, activity, "Both of you", game, playerText, computerText, computerScore, playerScore)
}





