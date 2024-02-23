import java.util.Scanner;
public class Main {

    public static Player player1 = new Player();
    //public static int chips =Player.getchips();

    public static Player player2 = new Player();
    //public static int chips2 =Player.getchips();
    public static dealer middle = new dealer();




    public static void main(String[] args) {
        middle.setDealerchips(0);
        player1.setchips(500);
        player2.setchips(500);
        System.out.println("player 1 starts with " +player1.chips  +" chips");
        System.out.println("player 2 starts with " +player2.chips +" chips");
        System.out.println("the pot starts with " +middle.chips +" chips");
        Scanner input = new Scanner(System.in);

        //checks for raise
        System.out.println("what do you want to do");
        String action = input.nextLine();
        if (action.equalsIgnoreCase("raise")){
            int[] updatedChips = Player.Raise(player1.chips, middle.chips);
            player1.chips = updatedChips[0];
            middle.setDealerchips(updatedChips[1]);
            System.out.println("you now have "+player1.chips+" chips" );
            System.out.println(player1.chips);
            System.out.println("the pot now has "+middle.chips+" chips");
        }
        if (action.equalsIgnoreCase("check")){
            Person.Check();
        }
        if (action.equalsIgnoreCase("fold")){
            Person.Fold();
        }
        if (action.equalsIgnoreCase("call")){
            Person.Call();
        }
        if (action.equalsIgnoreCase("all in")){
            Person.AllIn();
        }
    }
}