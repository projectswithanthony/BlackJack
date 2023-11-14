package com.example.blackjack

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Make all the cards
        val c1: ImageView = findViewById(R.id.c1Card)
        val c2: ImageView = findViewById(R.id.c2Card)
        val c3: ImageView = findViewById(R.id.c3Card)
        val c4: ImageView = findViewById(R.id.c4Card)
        val c5: ImageView = findViewById(R.id.c5Card)

        val compCards: MutableList<ImageView> = mutableListOf()
        compCards.add(c1)
        compCards.add(c2)
        compCards.add(c3)
        compCards.add(c4)
        compCards.add(c5)


        val p1: ImageView = findViewById(R.id.p1Card)
        val p2: ImageView = findViewById(R.id.p2Card)
        val p3: ImageView = findViewById(R.id.p3Card)
        val p4: ImageView = findViewById(R.id.p4Card)
        val p5: ImageView = findViewById(R.id.p5Card)

        val playCards: MutableList<ImageView> = mutableListOf()
        playCards.add(p1)
        playCards.add(p2)
        playCards.add(p3)
        playCards.add(p4)
        playCards.add(p5)


        val game = BlackJack()

        val hitButton: Button = findViewById(R.id.hitButton)
        val standButton: Button = findViewById(R.id.standButton)

//        val playerText: TextView = findViewById(R.id.playerHand)
//        val computerText: TextView = findViewById(R.id.computerHand)

        val computerScore: TextView = findViewById(R.id.computerScore)
        val playerScore: TextView = findViewById(R.id.playerScore)

        // Initial Printing of both hands
//        playerText.text = game.printHand(game.playerHand)
//        computerText.text = game.printHandComp(game.compHand)


        setCards(game.playerHand, playCards, this)
        setCardsComp(game.compHand, compCards, this)


        // Initial Check For Black Jack Condition
        checkBlackJack(game, this, this, computerScore, playerScore, playCards, compCards)


        // hit Button -- Progresses the game
        hitButton.setOnClickListener {
            playTurn(game, this, this, computerScore, playerScore, playCards, compCards)
        }

        // stand Button -- When player is done playing -- compares hands and such
        standButton.setOnClickListener {
            endDialog(this, this, game.compareHands(), game, computerScore, playerScore, playCards, compCards)
        }
    }
}

@SuppressLint("DiscouragedApi")
fun setCards(hand: MutableList<String>, changeList: MutableList<ImageView>, context: Context) {

    var index = 0
    hand.forEach {card ->
        var cardName = card;

        when(cardName) {
            "J" -> cardName = "12"
            "Q" -> cardName = "13"
            "K" -> cardName = "14"
            "A" -> cardName = "15"
        }

        val fileName = "hearts$cardName"
        val resourceId = context.resources.getIdentifier(fileName, "drawable", context.packageName)

        changeList[index++].setImageResource(resourceId)
    }
}

@SuppressLint("DiscouragedApi")
fun setCardsComp(hand: MutableList<String>, compCards: MutableList<ImageView>, context: Context) {

    var index = 1

    for(ind in 1 until hand.size) {
        var cardName = hand[ind];

        when(cardName) {
            "J" -> cardName = "12"
            "Q" -> cardName = "13"
            "K" -> cardName = "14"
            "A" -> cardName = "15"
        }

        val fileName = "hearts$cardName"
        val resourceId = context.resources.getIdentifier(fileName, "drawable", context.packageName)

        compCards[index++].setImageResource(resourceId)
    }

}

fun checkBlackJack(game: BlackJack, context: Context, activity: Activity,
                   computerScore: TextView, playerScore: TextView, playCards: MutableList<ImageView>, compCards: MutableList<ImageView>) {
    if (game.blackJack(game.compHand) && game.blackJack(game.playerHand)) {
        endDialog(context, activity, "Both of you", game, computerScore, playerScore, playCards, compCards)
    }
    else if (game.blackJack(game.compHand)) {
        endDialog(context, activity, "Computer", game, computerScore,playerScore, playCards, compCards)
    }
    else if (game.blackJack(game.playerHand)) {
        endDialog(context, activity, "Player", game, computerScore,playerScore, playCards, compCards)
    }
}

@SuppressLint("SetTextI18n")
fun endDialog(context: Context, activity: Activity, winner: String, game: BlackJack,
              computerScore: TextView, playerScore: TextView, playCards: MutableList<ImageView>, compCards: MutableList<ImageView>) {
    /*change the dealer facedown card to the image of whatever card
     *it actually is to show user at the end of the game*/

    setCards(game.compHand, compCards, context)

    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
    builder
        .setMessage("The $winner won!\n" +
                    "Computer's Hand("+ game.compHandSum + "): " + game.printHand(game.compHand) +
                    "\nYour Hand(" + game.playerHandSum + "): " + game.printHand(game.playerHand))
        .setTitle("Would You Like to Play Again?")
        .setPositiveButton("Yes") { dialog, which ->
            reset(game, playCards, compCards, context) // Reset game and Screen
            computerScore.text = "Computer Score: " + game.computerScore
            playerScore.text = "Player Score: " + game.playerScore
            dialog.dismiss();
            checkBlackJack(game, context, activity, computerScore, playerScore, playCards, compCards)
        }
        .setNegativeButton("No") { dialog, which ->
            activity.finish()
        }

    val dialog: AlertDialog = builder.create()
    dialog.setCanceledOnTouchOutside(false)
    dialog.show()
}

@SuppressLint("DiscouragedApi")
fun reset(game: BlackJack, playCards: MutableList<ImageView>, compCards: MutableList<ImageView>
          , context: Context) {
    game.reset()
//    playerText.text = game.printHand(game.playerHand)
//    computerText.text = game.printHandComp(game.compHand)
    /*Have code here where you reset all the image view for user and dealer
     *You would set the dealer to first card face down then second card up
     *then show both card for player*/

    val resourceId = context.resources.getIdentifier("card_back", "drawable", context.packageName)

    for (playCard in playCards) {
        playCard.setImageResource(resourceId)
    }

    for (compCard in compCards) {
        compCard.setImageResource(resourceId)
    }
    setCards(game.playerHand, playCards, context)
    setCardsComp(game.compHand, compCards, context)
}

fun playTurn(game: BlackJack, context: Context, activity: Activity, computerScore: TextView,
             playerScore: TextView, playCards: MutableList<ImageView>, compCards: MutableList<ImageView>) {

    // Deals next card to Player
    game.dealCardPlayer(game.generateRandom())
//    playerText.text = game.printHand(game.playerHand)
    /*have code here to put next image in view for player
     *depending on the card it is get the right image*/

    setCards(game.playerHand, playCards, context)


    // Check to see if player busts
    if(game.playerHandSum > 21) {
        endDialog(context, activity, "Computer", game, computerScore, playerScore, playCards, compCards)
        game.computerScore++;
        return
    }

    // Decides if computer takes a card or not
    if(game.compHandSum < 16) {
        game.dealCardComp(game.generateRandom())
//        computerText.text = game.printHandComp(game.compHand)
        setCardsComp(game.compHand, compCards, context)
        /*make sure to add an image to the dealer next image view
        *based on the card they take*/

    }

    // Check to see if computer busts
    if(game.compHandSum > 21) {
        endDialog(context, activity, "Player", game, computerScore, playerScore, playCards, compCards)
        game.playerScore++;
        return
    }
}





