import java.util.Scanner;
public class Main {

    public static Player player1 = new Player();
    public static Player player2 = new Player();
    public static Player player3 = new Player();
    public static Player player4 = new Player();
    public static Player player5 = new Player();


    public static dealer middle = new dealer();




    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("how many players, max 5");
        //takes count
        int pcount = Integer.parseInt(input.nextLine());
        Player[] players = new Player[5];
        players[0]= player1;
        players[1]=player2;
        players[2]=player3;
        players[3]=player4;
        players[4]=player5;
        middle.setDealerchips(0);
        for (int w=0; w<pcount; w++){
            players[w].setchips(500);
            players[w].setIn_game(true);
        }
        int minraise = 0;
        /*for (int y=0; y<pcount; y++) {
            System.out.println("player" +y+" starts with " + players[y].chips + " chips");
        }*/
        System.out.println("the pot starts with " +middle.chips +" chips");
        for (int y=0; y<pcount; y++) {
            System.out.println("player" +y+" starts with " + players[y].chips + " chips");
        }
        boolean gameover = false;
        while (gameover==false) {
            boolean turn_over = false;

            for (int i=0; i<pcount; i++) {

                //makes object current player which is whatever object players[i]
                Player currentPlayer = players[i];

                turn_over = false;
                while (turn_over == false /*is players turn*/ && players[i].In_game==true/*player hasn't folded*/) {
                    System.out.println("it is player "+(i+1)+"'s turn");
                    //checks for raise
                    System.out.println("what do you want to do");
                    String action = input.nextLine();
                    if (action.equalsIgnoreCase("raise")) {
                        //puts the returned values into array updatechips
                        int[] updatedChips = Player.Raise(players[i].chips, middle.chips);
                        currentPlayer.chips = updatedChips[0];//updated chips0 = returned value
                        middle.chips = updatedChips[1];
                         minraise = updatedChips[2];

                        System.out.println("you now have " + players[i].chips + " chips");
                        System.out.println("the pot now has " + middle.chips + " chips");

                        turn_over = true;
                    }

                    else if (action.equalsIgnoreCase("check") && minraise<=0) {
                        Person.Check();
                        turn_over = true;
                    }
                    else if (action.equalsIgnoreCase("check") && minraise>0) {//cant check bc already been raised
                        System.out.println("you can not check");
                        turn_over= false;
                    }
                    else if (action.equalsIgnoreCase("fold")) {
                        players[i].setIn_game(false);
                        turn_over = true;
                    }

                    else if (action.equalsIgnoreCase("call")) {
                        int[] newvalues = Person.Call(players[i].chips, middle.chips, minraise);
                        players[i].setchips(newvalues[0]);
                        middle.setDealerchips(newvalues[1]);
                        System.out.println("you now have " + players[i].chips + " chips");
                        System.out.println("the pot now has " + middle.chips + " chips");
                        turn_over = true;
                    }

                    else if (action.equalsIgnoreCase("all in")) {
                        Person.AllIn();
                        turn_over = true;
                    }

                    else {
                        System.out.println("you can, raise, check, fold, call or go all in");
                    }
                }
            }
        }
    }
}