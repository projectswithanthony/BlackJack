import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class BlackJack {

    public int playerScore;
    public int computerScore;

    public int playerHandSum;
    public int compHandSum;

    public List<String> playerHand;
    public List<String> compHand;

    final String[] array = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

    public BlackJack()
    {
        playerScore = 0;
        computerScore = 0;
        playerHandSum = 0;
        compHandSum = 0;
        playerHand = new ArrayList<>();
        compHand = new ArrayList<>();

        dealCardComp(generateRandom());
        dealCardComp(generateRandom());

        dealCardPlayer(generateRandom());
        dealCardPlayer(generateRandom());
    }

    public boolean shouldCompHit()
    {
        return compHandSum < 16;
    }

    /**
     * If the player or computer has a Face Card + Ace they win instantly unless the
     * opponent also has a BlackJack.
     * @return true if the hand is a black jack and false otherwise
     */
    public boolean blackJack(List<String> hand)
    {
        switch(hand.get(0)) {
            case "J", "Q", "K" -> {
                if(hand.get(1).equals("A"))
                    return true;
            }
            case "A" -> {
                switch (hand.get(1)) {
                    case "J", "Q", "K" -> {
                        return true;
                    }
                }
            }
        }

        return false;
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
    public int aceValue(List<String> hand, int sum)
    {
        if(sum + 11 > 21)
            return 1;

        return 11;
    }

    /**
     * Compares the Sums of the hands to check for winner
     * @return 0 - Player, 1 - Computer, 2 - Draw
     */
    public int compareHands() {
        if(playerHandSum == compHandSum)
        {
            return 2;
        }
        else if (playerHandSum < compHandSum) {
            computerScore++;
            return 1;
        }
        else {
            playerScore++;
            return 0;
        }
    }

    /**
     * Prints out the hand of the inserted List<String>
     * @param hand either the computer or the players hand
     */
    public void printHand(List<String> hand)
    {
        for(String s : hand)
        {
            System.out.print(s + " ");
        }
    }

    /**
     * Variation of the printHand method used to simulate player not seeing
     * dealers hand until the end of the game.
     * @param hand the computers hand
     */
    public void printHandComp(List<String> hand)
    {
        for(int i = 1; i < hand.size(); i++)
        {
            System.out.print(hand.get(i) + " ");
        }
    }

    /**
     * Selects a random card from the array of valid cards
     * @return a String representing the random card
     */
    public String generateRandom()
    {
        Random random = new Random();
        int randomIndex = random.nextInt(array.length);

        return array[randomIndex];
    }

    /**
     * Deals a new card to the computer
     * @param newCard should be from generateRandom()
     */
    public void dealCardComp(String newCard)
    {
        compHand.add(newCard);

        switch (newCard) {
            case "J", "Q", "K" -> compHandSum += 10;
            case "A" -> compHandSum += aceValue(compHand, compHandSum);
            default -> compHandSum += parseInt(newCard);
        }
    }

    /**
     * Deals a new card to the player
     * @param newCard should be from generateRandom()
     */
    public void dealCardPlayer(String newCard) {
        playerHand.add(newCard);

        switch (newCard) {
            case "J", "Q", "K" -> playerHandSum += 10;
            case "A" -> playerHandSum += aceValue(playerHand, playerHandSum);
            default -> playerHandSum += parseInt(newCard);
        }
    }
}
