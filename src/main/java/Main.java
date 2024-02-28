import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static Player player1 = new Player();
    public static Player player2 = new Player();
    public static Player player3 = new Player();
    public static Player player4 = new Player();
    public static Player player5 = new Player();
    public static dealer middle = new dealer();


    public static void main(String[] args) {


        int[][] shuffled =dealer.deckmaker();
        Scanner input = new Scanner(System.in);
        System.out.println("how many players, max 5");
        //takes count
        int pcount = Integer.parseInt(input.nextLine());


        //creates array holding object each of the players
        Player[] players = new Player[5];
        players[0]= player1;
        players[1]=player2;
        players[2]=player3;
        players[3]=player4;
        players[4]=player5;
        //dealer starts with 0
        middle.setDealerchips(0);


        //sets every player to have 500 chips and to be in the game
        for (int w=0; w<pcount; w++){
            players[w].setchips(500);
            players[w].setIn_game(true);
        }


        int minraise = 0;
        System.out.println("the pot starts with " +middle.chips +" chips");


        for (int y=2; y<pcount+2; y++) {
            //creates phand, placeholder for the players hand
            int [] [] phand =new int[5][2];
            //where the second card is in the deck
            int o=2*pcount+y;


            //sets the 2d array to hold the values
            phand[0][0] = shuffled[y][0];
            phand[1][0]=shuffled[y][1];
            phand[0][1] = shuffled[o][0];
            phand[1][1]=shuffled[o][1];


            //sets player attribute
            players[y-2].setPlayerhand(phand);
            System.out.println("player" +y+" starts with " + players[y-2].chips + " chips and the cards");


        }
        int stage = 0;
        boolean gameover = false;
        String suite ="";
        while (gameover==false) {
            boolean turn_over = false;


            if (stage==pcount){
                System.out.println("the flop is ");
                for(int e=51; e>48; e--){
                    if (shuffled[e][1] == 1){
                         suite="spades";
                    } else if (shuffled[e][1] == 2) {
                         suite="hearts";
                    } else if (shuffled[e][1] == 3) {
                         suite="diamonds";
                    } else if (shuffled[e][1] == 4) {
                         suite ="clubs";
                    }
                    System.out.println(shuffled[e][0] + " of "+ suite);

                }
            }

            if (stage==2*pcount){
                System.out.println("the fourth street is ");

                for(int e=51; e>47; e--){
                    if (shuffled[e][1] == 1){
                        suite="spades";
                    } else if (shuffled[e][1] == 2) {
                        suite="hearts";
                    } else if (shuffled[e][1] == 3) {
                        suite="diamonds";
                    } else if (shuffled[e][1] == 4) {
                        suite ="clubs";
                    }
                    System.out.println(shuffled[e][0] + " of "+ suite);
                }
            }

            if (stage==3*pcount) {
                System.out.println("the river is ");

                for (int e = 51; e > 46; e--) {
                    if (shuffled[e][1] == 1) {
                        suite = "spades";
                    } else if (shuffled[e][1] == 2) {
                        suite = "hearts";
                    } else if (shuffled[e][1] == 3) {
                        suite = "diamonds";
                    } else if (shuffled[e][1] == 4) {
                        suite = "clubs";
                    }
                    System.out.println(shuffled[e][0] + " of " + suite);

                }
            }


            for (int i=0; i<pcount; i++) {
                stage = stage+1;
                boolean allin = false;


                //makes object current player which is whatever object players[i]
                Player currentPlayer = players[i];


                turn_over = false;
                while (turn_over == false /*is players turn*/ && players[i].In_game==true/*player hasn't folded*/) {
                    System.out.println("it is player "+(i+1)+"'s turn");
                    System.out.println("input anything when that player has the computer");
                    //holds until something is inputted
                    String confirmer = input.nextLine();
                    //outputs the current players hand
                    System.out.println(players[i].Playerhand[0][0]);


                    //converts from code into the name of the suite
                        if (players[i].Playerhand[1][0] == 1){
                            System.out.println("spade");
                        } else if (players[i].Playerhand[1][0] == 2) {
                            System.out.println("heart");
                        } else if (players[i].Playerhand[1][0] == 3) {
                            System.out.println("diamond");
                        } else if (players[i].Playerhand[1][0] == 4) {
                            System.out.println("club");
                        }
                        System.out.println(players[i].Playerhand[1][0]);
                        if (players[i].Playerhand[1][1] == 1){
                            System.out.println("spade");
                        } else if (players[i].Playerhand[1][1] == 2) {
                            System.out.println("heart");
                        } else if (players[i].Playerhand[1][1] == 3) {
                            System.out.println("diamond");
                        } else if (players[i].Playerhand[1][1] == 4) {
                            System.out.println("club");
                        }


                    //checks for raise
                    System.out.println("what do you want to do");
                    String action = input.nextLine();


                    if (action.equalsIgnoreCase("raise")) {
                        //puts the returned values into array updatechips
                        int[] updatedChips = Player.Raise(players[i].chips, middle.chips, allin);
                        if(updatedChips[2] >= minraise){


                            //updates attributes to returned values
                            currentPlayer.chips = updatedChips[0];
                            middle.chips = updatedChips[1];
                            minraise = updatedChips[2];


                            System.out.println("you now have " + players[i].chips + " chips");
                            System.out.println("the pot now has " + middle.chips + " chips");
                            turn_over = true;
                        }
                        else{
                            System.out.println("you have to raise at least "+ minraise);
                        }
                    }



                    else if (action.equalsIgnoreCase("check") && minraise<=0) {//cant check if someon has raised
                        Player.Check();
                        turn_over = true;//ends turn without changing if player is still in game, player will have a turn next turn
                    }

                    else if (action.equalsIgnoreCase("check") && minraise>0) {//cant check bc already been raised
                        System.out.println("you can not check");
                        turn_over= false;
                    }


                    else if (action.equalsIgnoreCase("fold")) {
                        players[i].setIn_game(false);
                        turn_over = true; //fully kicks player off
                    }


                    else if (action.equalsIgnoreCase("call")) {
                        //same as raise just does minraise instead of asking
                        int[] newvalues = Player.Call(players[i].chips, middle.chips, minraise);
                        players[i].setchips(newvalues[0]);
                        middle.setDealerchips(newvalues[1]);


                        System.out.println("you now have " + players[i].chips + " chips");
                        System.out.println("the pot now has " + middle.chips + " chips");
                        turn_over = true;
                    }


                    else if (action.equalsIgnoreCase("all in")) {
                         allin = true;
                        Player.AllIn();
                        turn_over = true;


                        //same as raise but doesn't check for minraise.
                        int[] updatedChips = Player.Raise(players[i].chips, middle.chips, allin);
                        currentPlayer.chips = updatedChips[0];//updated chips0 = returned value
                        middle.chips = updatedChips[1];
                        minraise = updatedChips[2];


                        System.out.println("you now have " + players[i].chips + " chips");
                        System.out.println("the pot now has " + middle.chips + " chips");
                    }


                    //incorrect input
                    else {
                        System.out.println("you can, raise, check, fold, call or go all in");
                    }
                }
            }
        }
    }

    public static String win_conditions(){
        String Winner="";
        return Winner;
    }
}