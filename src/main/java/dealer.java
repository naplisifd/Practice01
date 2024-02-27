import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.Stack;

public class dealer {


    public static int[][] deckmaker(){

        //makes an ordered deck
        int[][] unshuffled = new int [52] [52];
        int x =0;
        for (int i=1; i<5; i++){
            for (int w=1; w<14; w++){
                unshuffled[x] = new int[]{w,i};
                x++;
            }
        }


        Random random = new Random();

        //swaps 2 random cards 100 times
        for (int y=0; y<100; y++){
            int randomNumber1 = random.nextInt(51) + 0;
            int randomNumber2 = random.nextInt(51) + 0;
            int saver1 = unshuffled[randomNumber1][0];
            int saver2 = unshuffled[randomNumber1][1];
            unshuffled[randomNumber1][0] = unshuffled[randomNumber2][0];
            unshuffled[randomNumber1][1] = unshuffled[randomNumber2][1];
            unshuffled[randomNumber2][0] = saver1;
            unshuffled[randomNumber2][1] = saver2;

        }


        return unshuffled;
    }

    public int chips;
    public void setDealerchips(int Middlechips) {
        this.chips = Middlechips;
    }
    public  int getDealerchips(){
        return chips;
    }

}
