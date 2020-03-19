/**
 * 
 */
package CardGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author weiliang
 *
 */
public class Deck {
	
	private int[] numbers = {0, 1, 2, 3, 4, 5};
	private String[] colors = {"RED", "GREEN", "YELLOW"};
	private int NUMBER_OF_CARDS = numbers.length * colors.length;
	
	private ArrayList<Card> deck = null;
	private Random randomNumbers = new Random(); // Random number generator
	
	public Deck() {
		
		// Initialize the deck
		deck = new ArrayList<Card>();
		
		// Populate the deck
		for (int i = 0; i < NUMBER_OF_CARDS; i++) {
			deck.add(new Card(numbers[i % numbers.length], colors[i / numbers.length]));
		}

		// Shuffle the deck
		shuffle();
	}
	
	public void shuffle() {

		// For each card, pick another card and swap them
		for (int i = 0; i < deck.size(); i++) {
			
			// Select a random number between 0 and 17
			int j = randomNumbers.nextInt(NUMBER_OF_CARDS - 1);
			
			// Swap cards
			Card temp = deck.get(i);
			deck.set(i, deck.get(j));
			deck.set(j, temp);
		}
	};
	
	public void sort(String colorList) {
		
		// Sort all cards in the deck by their colors
		ArrayList<Card> redCards = new ArrayList<Card>();
		ArrayList<Card> greenCards = new ArrayList<Card>();
		ArrayList<Card> yellowCards = new ArrayList<Card>();
		
		deck.forEach(card -> {
			if (card.getColor().equalsIgnoreCase("RED")) {
				redCards.add(card);
			} else if (card.getColor().equalsIgnoreCase("GREEN")) {
				greenCards.add(card);
			} else if (card.getColor().equalsIgnoreCase("YELLOW")) {
				yellowCards.add(card);
			}
		});
		
		// Sort redCards by their numbers
		Collections.sort(redCards);
		
		// Sort greenCards by their numbers
		Collections.sort(greenCards);
		
		// Sort yellowCards by their numbers
		Collections.sort(yellowCards);

		// Merge the 3 stacks of color cards as preferred
		HashMap<Character, ArrayList<Card>> map = new HashMap<Character, ArrayList<Card>>();
		map.put('R', redCards);
		map.put('G', greenCards);
		map.put('Y', yellowCards);
		
		deck.clear();
		deck = (ArrayList<Card>) Stream.of(map.get(colorList.charAt(0)), map.get(colorList.charAt(1)), map.get(colorList.charAt(2)))
				.flatMap(x -> x.stream())
				.collect(Collectors.toList());
	}
	
	public Card draw() {
		
		// return null when deck is empty
		if (deck.size() == 0) {
			return null;
		}
		
		// Get the top card and remove it from the deck
		Card card = deck.get(0);
		deck.remove(0);
		return card;
	}
	
	public void reveal() {
		
		// Print all cards in the deck
		deck.forEach(card -> System.out.println(card));
		System.out.println();
	}
	
}
