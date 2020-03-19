/**
 * 
 */
package CardGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author weiliang
 *
 */
public class Game {

	/**
	 * @param args
	 */

	private Deck deck;
	
	public Game() {
		deck = null;
	}
	
	private void start() {
		
		boolean toPlay = true;
		while (toPlay) {
			char option = optionPrompts();
			if ("12345".indexOf(option) == -1) {
				System.out.println("Invalid option!");
			} else {
				play(option);
			}
		}
	}
	
	private char optionPrompts() {

		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println(" PLEASE SELECT OPTION #:");
		System.out.println();
		System.out.println(" OPTION 1: Get a shuffled deck of cards");
		System.out.println(" OPTION 2: Draw the top card");
		System.out.println(" OPTION 3: Sort the cards by a given color list");
		System.out.println(" OPTION 4: Play a game");
		System.out.println(" OPTION 5: Exit");
		System.out.println("--------------------------------------------------");
		System.out.println();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		char option;
		try {
			String input = reader.readLine();
			if (input.length() != 1) {
				option = 0;
			} else {
				option = input.charAt(0);
			}
		} catch (StringIndexOutOfBoundsException e) {
			option = 0;
		} catch (IOException e) {
			option = 0;
		}
		return option;
	}
	
	private String colorPrompts() {
		
		System.out.println("> Please indicate preferred color list by the 3-letter code format:");
		System.out.println("> RGY = [RED, GREEN, YELLOW]");
		System.out.println("> RYG = [RED, YELLOW, GREEN]");
		System.out.println("> GRY = [GREEN, RED, YELLOW]");
		System.out.println("> GYR = [GREEN, YELLOW, RED]");
		System.out.println("> YRG = [YELLOW, RED, GREEN]");
		System.out.println("> YGR = [YELLOW, GREEN, RED]");
		System.out.println();
		
		//if (selection is not in the list, choose again)
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String colorOption = null;
		try {
			colorOption = reader.readLine();
		} catch (IOException e) {
			colorOption = null;
		}
		
		return colorOption;
	}
	
	private void play(char option) {
		
		switch (option) {
		case '1': // Get a new shuffled deck of cards
			System.out.println("> Selected OPTION 1: Get a new deck of cards.");
			System.out.println("> Here is the new deck of cards:");
			System.out.println();
			deck = new Deck();
			deck.reveal();
			break;
			
		case '2': // Draw the top card of the deck
			System.out.println("> Selected OPTION 2: Deal the top card.");
			System.out.println("> Here is the top card dealt:");
			System.out.println();
			if (Objects.isNull(deck)) {
				deck = new Deck();
			}
			
			Card card1 = deck.draw();
			if (Objects.isNull(card1)) {
				System.out.println("No more card to draw!");
			} else {
				System.out.println(card1);
			}

			break;
			
		case '3': // Sort the cards by the input color order

			List<String> colorList = Arrays.asList("RGY", "RYG", "GRY", "GYR", "YRG", "YGR");
			boolean colorOptionMissing = true;
			String colorOption = null;
			
			while (colorOptionMissing) {
				colorOption =  colorPrompts();
				if (colorList.contains(colorOption)) {
					colorOptionMissing = false;
				}
			}
			
			System.out.println("> Preferred color list is: " + colorOption);
			System.out.println();
			
			if (Objects.isNull(deck)) {
				deck = new Deck();
			}

			deck.sort(colorOption);
			deck.reveal();
			
			break;
			
		case '4': // Play a 2-players card game
			
			System.out.println("> Selected OPTION 4: Play a game.");
			
			if (Objects.isNull(deck)) {
				deck = new Deck();
				deck.reveal();
			}

			Player pa = new Player("Player A");
			Player pb = new Player("Player B");
			Card card =null;
			
			for (int i = 0; i < 6; i++) {
				card = deck.draw();
				
				if (Objects.isNull(card)) {
					System.out.println("No more card to draw!");
					break;
				}
				
				if (i % 2 == 0) {
					pa.drawCard(card);
					System.out.println(pa.getName() + " has drawn " + card.toString());
				} else {
					pb.drawCard(card);
					System.out.println(pb.getName() + " has drawn " + card.toString());
				}
			}
			
			if (!Objects.isNull(card)) {
				if (pa.calculateScore() > pb.calculateScore()) {
					System.out.println("\nThe winner is Player A! Score = " + pa.getScore());
				} else if (pa.calculateScore() < pb.calculateScore()) {
					System.out.println("\nThe winner is Player B! Score = " + pb.getScore());
				} else {
					System.out.println("\nPlayer A and player B tie! Score = " + pa.getScore());
				}
			}
			break;
			
		case '5':
			System.out.println("\nGame over!");
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		new Game().start();
	}
}
