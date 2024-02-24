import java.util.Scanner;
import java.util.Random;
import java.util.Stack;

public class dealer {
    public Stack Deck_n;
    public Stack Deck_s;

    public static Stack<Integer> deckmaker(){
        Stack<Integer> Deck_n = new Stack<>();
        int[] checker = new int[13];
        for (int i = 0; i < 13; i++) {
            boolean doubled = false;
            Random random = new Random();
            int cardnumber;
            do {
                cardnumber = random.nextInt(13) + 1;
                doubled = false;
                for (int w = 0; w < i; w++) {
                    if (checker[w] == cardnumber) {
                        doubled = true;
                        break;
                    }
                }
            } while (doubled);
            checker[i] = cardnumber;
            Deck_n.push(cardnumber);
        }
        for (int x = 0; x < 13; x++) {
            System.out.println(Deck_n.pop());
        }
        return Deck_n;
    }

    public int chips;
    public void setDealerchips(int Middlechips) {
        this.chips = Middlechips;
    }
    public  int getDealerchips(){
        return chips;
    }

}
