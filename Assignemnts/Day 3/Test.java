/**
 * @author Ravi V
 */
class Test {

    public static Card[] sort(Card[] cards) {
        int length = cards.length;
        for (int i = 1; i < length; i++) {
            Card current = cards[i];
            int j = 0;
            for (j = i - 1; j >= 0 && cards[j].compareTo(current) == 1; j--) {
                cards[j + 1] = cards[j];
            }
            cards[j + 1] = current;
        }
        return cards;
    }

    public static void main(String[] args) {
        Card[] cards = new Card[52];
        int pos = 0;
        for (int i = 0; i < pos; i++) {
            int rand = i + (int) (Math.random() * (pos - i));
            Card temp = cards[rand];
            cards[rand] = cards[i];
            cards[i] = temp;
        }

        for (int j = 0; j < 4; j++) {
            for (int k = 1; k <= 13; k++) {
                cards[pos] = new Card(k, j);
                pos++;
            }
        }

        System.out.println("Before sorting");
        for (int i = 0; i < pos; i++) {
            System.out.println(cards[i]);
        }
        System.out.println("After sorting");
        Card[] afterSort = sort(cards);
        for (int i = 0; i < pos; i++) {
            System.out.println(afterSort[i]);
        }
    }
}