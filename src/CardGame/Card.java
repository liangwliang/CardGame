package CardGame;

/**
 * @author weiliang
 *
 */
public class Card implements Comparable<Card>{
	
	private int number;
	private String color;
	
	public Card(int face, String color) {
		super();
		this.number = face;
		this.color = color;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}	
	
	@Override
	public String toString() {
		return "Card [color = " + color + ", number = " + number + "]";
	}

	@Override
	public int compareTo(Card card) {
		return (this.getNumber() < card.getNumber() ? -1 : 
			(this.getNumber() == card.getNumber() ? 0 : 1));
	}
}
