import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.Stack;

public class dealer {
    public Stack Deck_n;
    public Stack Deck_s;

    public static Stack<int[]> deckmaker(){
        Stack<int[]> Deck_n = new Stack<>();
        int[][] unshuffled = new int [52] [52];
        int x =0;
        for (int i=1; i<5; i++){
            for (int w=1; w<14; w++){
                unshuffled[x] = new int[]{w,i};
                x++;
            }
        }
        Random random = new Random();
        String checker = "";
        int randomNumber = random.nextInt(52) + 1;
        for (int y=0; y<52; y++){

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
