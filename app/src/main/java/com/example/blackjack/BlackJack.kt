package com.example.blackjack

import java.util.Random

class BlackJack {
    @JvmField
    var playerScore = 0
    @JvmField
    var computerScore = 0
    @JvmField
    var playerHandSum = 0
    @JvmField
    var compHandSum = 0
    @JvmField
    var playerHand: MutableList<String>
    @JvmField
    var compHand: MutableList<String>
    val array = arrayOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")

    init {
        playerHand = ArrayList()
        compHand = ArrayList()
        dealCardComp(generateRandom())
        dealCardComp(generateRandom())
        dealCardPlayer(generateRandom())
        dealCardPlayer(generateRandom())
    }

    fun shouldCompHit(): Boolean {
        return compHandSum < 16
    }

    /**
     * If the player or computer has a Face Card + Ace they win instantly unless the
     * opponent also has a BlackJack.
     * @return true if the hand is a black jack and false otherwise
     */
    fun blackJack(hand: List<String>): Boolean {
        when (hand[0]) {
            "J", "Q", "K" -> {
                if (hand[1] == "A") return true
            }

            "A" -> {
                when (hand[1]) {
                    "J", "Q", "K" -> {
                        return true
                    }
                }
            }
        }
        return false
    }
    //    public int recheckSum(List<String> hand, int sum)
    //    {
    //        if(hand.contains("A"))
    //        {
    //            if (sum > 21)
    //                sum = sum - 10;
    //        }
    //
    //        return sum;
    //    }
    /**
     * Checks the other cards in hand and determines if the ACE(if present) should be
     * counted as a 1 or an 11
     * Will always try to choose 11 unless the player/computer would lose with that choice
     * @return either a 1 or an 11 depending on the sum of the hand.
     */
    fun aceValue(hand: List<String>?, sum: Int): Int {
        return if (sum + 11 > 21) 1 else 11
    }

    /**
     * Compares the Sums of the hands to check for winner
     * @return 0 - Player, 1 - Computer, 2 - Draw
     */
    fun compareHands(): Int {
        return if (playerHandSum == compHandSum) {
            2
        } else if (playerHandSum < compHandSum) {
            computerScore++
            1
        } else {
            playerScore++
            0
        }
    }

    /**
     * Prints out the hand of the inserted List<String>
     * @param hand either the computer or the players hand
    </String> */
    fun printHand(hand: List<String>) {
        for (s in hand) {
            print("$s ")
        }
    }

    /**
     * Variation of the printHand method used to simulate player not seeing
     * dealers hand until the end of the game.
     * @param hand the computers hand
     */
    fun printHandComp(hand: List<String>) {
        for (i in 1 until hand.size) {
            print(hand[i] + " ")
        }
    }

    /**
     * Selects a random card from the array of valid cards
     * @return a String representing the random card
     */
    fun generateRandom(): String {
        val random = Random()
        val randomIndex = random.nextInt(array.size)
        return array[randomIndex]
    }

    /**
     * Deals a new card to the computer
     * @param newCard should be from generateRandom()
     */
    fun dealCardComp(newCard: String) {
        compHand.add(newCard)
        compHandSum += when (newCard) {
            "J", "Q", "K" -> 10
            "A" -> aceValue(compHand, compHandSum)
            else -> newCard.toInt()
        }
    }

    /**
     * Deals a new card to the player
     * @param newCard should be from generateRandom()
     */
    fun dealCardPlayer(newCard: String) {
        playerHand.add(newCard)
        playerHandSum += when (newCard) {
            "J", "Q", "K" -> 10
            "A" -> aceValue(playerHand, playerHandSum)
            else -> newCard.toInt()
        }
    }
}