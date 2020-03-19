/**
 * 
 */
package CardGame;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author weiliang
 *
 */
public class Player {

	private String name;
	private int score;
	private ArrayList<Card> cards = null;

	public Player(String name) {
		this.name = name;
		this.cards = new ArrayList<Card>();
		this.score = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void drawCard(Card card) {
		if (cards.size() >= 3) {
			System.out.println("This player has already drawn 3 cards, no more card is allowed to be drawn.");
		} else {
			cards.add(card);
		}
	}
	
	public void refreshCards() {
		cards.clear();
	}
	
	public int calculateScore() {
		HashMap<String, Integer> colorPointMap = new HashMap<String, Integer>();
		colorPointMap.put("RED", 3);
		colorPointMap.put("YELLOW", 2);
		colorPointMap.put("GREEN", 1);
		
		cards.forEach(card -> {
			score += colorPointMap.get(card.getColor()) * card.getNumber();
		}); 
		
		return score;
	}
	
	public int getScore() {
		return score;
	}
	
	@Override
	public String toString() {
		return "Player [name = " + name + ", score = " + score + "]";
	}
}
