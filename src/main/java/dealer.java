import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.Stack;

public class dealer {


    public static int[][] deckmaker(){
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

        for (int y=0; y<100; y++){
            int randomNumber1 = random.nextInt(51) + 0;
            int randomNumber2 = random.nextInt(51) + 0;
            int saver1 = unshuffled[randomNumber1][0];
            int saver2 = unshuffled[randomNumber1][1];
            unshuffled[randomNumber1][0] = unshuffled[randomNumber2][0];
            unshuffled[randomNumber1][1] = unshuffled[randomNumber2][1];
            unshuffled[randomNumber2][0] = saver1;
            unshuffled[randomNumber2][1] = saver2;

        }/*
        for (int p=1; p<52; p++){
            System.out.println(unshuffled[p][0]);
           if (unshuffled[p][1] == 1){
               System.out.println("spade");
           } else if (unshuffled[p][1] == 2) {
               System.out.println("heart");
           } else if (unshuffled[p][1] == 3) {
               System.out.println("diamond");
           } else if (unshuffled[p][1] == 4) {
               System.out.println("club");
           }
        }*/
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
