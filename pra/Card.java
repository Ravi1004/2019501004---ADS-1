/**
 * @author Ravi V
 */

public class Card {

    private int suit;
    private int value;

    /**.
     * This is constructor for card
     * @param value its the value of the card
     * @param suit this is which suit the card belongs to
     */
    public Card(int value, int suit) {
        this.value = value;
        this.suit = suit;
    }

    public static final String[] suits = {"S","H","C","D"};
    public static final String[] values = {null,"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    /**.
     * This is a compare to method which is used to compare the cards
     * @param next this is a parameter which is passed to compare with the current card.
     * @return this will return 1 if the card is less than the current card and -1 if the card is grater than the current card
     */
    public int compareTo(Card next) {
        if (this.suit < next.suit) {
            return -1;
        }
         else if (next.suit < this.suit) {
            return 1;
        }
        if (next.value > this.value) {
            return -1;
        }
        else if (next.value < this.value) {
            return 1;
        }
        return 0;
    }

    /**.
     * this is the tostring method
     */
    public String toString() {
        return values[this.value] + " " + suits[this.suit];
    }
}
