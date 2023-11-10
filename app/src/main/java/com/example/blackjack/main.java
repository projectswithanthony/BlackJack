import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        BlackJack game = new BlackJack();

        // Game While Loop
        boolean playingFlag = true; // if 'q' quits game
        Scanner input = new Scanner(System.in);

        boolean isStanding = false;
        boolean resetValue = false;

        if (game.blackJack(game.compHand) && game.blackJack((game.playerHand))) {
            System.out.println("The Computer Has a hand of: ");
            game.printHand(game.compHand);

            System.out.println();

            System.out.println("Your Hand is: ");
            game.printHand(game.playerHand);

            System.out.println("");

            System.out.println("IT WAS A DRAW because of two Black Jack Cases");
        } else if (game.blackJack(game.compHand)) {
            System.out.println("The Computer Has a hand of: ");
            game.printHandComp(game.compHand);

            System.out.println();

            System.out.println("Your Hand is: ");
            game.printHand(game.playerHand);

            System.out.println("The Computer wins by Black Jack");
        } else if (game.blackJack(game.playerHand)) {
            System.out.println("The Computer Has a hand of: ");
            game.printHandComp(game.compHand);

            System.out.println();

            System.out.println("Your Hand is: ");
            game.printHand(game.playerHand);

            System.out.println();

            System.out.println("The Player wins by Black Jack");
        } else {
            while (playingFlag) {

                System.out.println("The Computer Has a hand of: ");
                game.printHandComp(game.compHand);

                System.out.println();

                System.out.println("Your Hand is: ");
                game.printHand(game.playerHand);

                // Players Choice
                System.out.print("\nWould you like to HIT?: (y/n) ");
                if (input.nextLine().equals("y"))
                    game.dealCardPlayer(game.generateRandom());
                else
                    isStanding = true;

                if (game.playerHandSum > 21) {
                    System.out.println("DEALERS FINAL HAND: ");
                    game.printHand(game.compHand);
                    System.out.println("\nCOMP SUM: " + game.compHandSum);

                    System.out.println("PLAYERS FINAL HAND: ");
                    game.printHand(game.playerHand);
                    System.out.println("\nPLAYER SUM: " + game.playerHandSum);

                    System.out.println("\nTHE COMPUTER WINS!");

                    game.computerScore++;
                    resetValue = true;
                }

                if (isStanding) {
                    // Reveal Dealers cards
                    System.out.println("DEALERS FINAL HAND: ");
                    game.printHand(game.compHand);
                    System.out.println("\nCOMP SUM: " + game.compHandSum);

                    System.out.println("PLAYERS FINAL HAND: ");
                    game.printHand(game.playerHand);
                    System.out.println("\nPLAYER SUM: " + game.playerHandSum);

                    int outcome = game.compareHands();

                    // 0 - Player, 1 - Computer, 2 - Draw
                    if (outcome == 2) {
                        System.out.println("IT WAS A DRAW :/");
                    } else if (outcome == 1) {
                        System.out.println("THE COMPUTER WON :(");
                    } else {
                        System.out.println("THE PLAYER WON :)");
                    }

                    resetValue = true;
                } else {

                    // Computers Choice
                    if (game.shouldCompHit()) {
                        game.dealCardComp(game.generateRandom());

                        if (game.compHandSum > 21) {
                            System.out.println("DEALERS FINAL HAND: ");
                            game.printHand(game.compHand);
                            System.out.println("\nCOMP SUM: " + game.compHandSum);

                            System.out.println("PLAYERS FINAL HAND: ");
                            game.printHand(game.playerHand);
                            System.out.println("\nPLAYER SUM: " + game.playerHandSum);

                            System.out.println("\nTHE PLAYER WINS!");
                            game.playerScore++;
                            resetValue = !resetValue;
                        }
                    }

                    System.out.println("\n");

                }

                if (resetValue) {
                    System.out.println();
                    System.out.println("COMPUTER SCORE: " + game.computerScore);
                    System.out.println("PLAYER SCORE: " + game.playerScore);
                    System.out.print("Would You like to play again? (y/n): ");
                    String choice = input.nextLine();

                    if (choice.equals("n"))
                        playingFlag = false;
                    else {
                        int ps = game.playerScore;
                        int cs = game.computerScore;

                        game = new BlackJack();
                        game.playerScore = ps;
                        game.computerScore = cs;
                        isStanding = false;
                        resetValue = false;
                    }
                    System.out.println();
                }
            }
        }
    }
}
