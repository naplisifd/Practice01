import java.util.Scanner;
import java.util.Arrays;

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


        //creates array holding object each of the players
        Player[] players = new Player[5];
        players[0]= player1;
        players[1]=player2;
        players[2]=player3;
        players[3]=player4;
        players[4]=player5;
        //dealer starts with 0
        middle.setDealerchips(0);
        for (int v=0; v>pcount; v++){
            players[v].setIn_game(true);
        }

        //sets every player to have 500 chips and to be in the game
        for (int w=0; w<pcount; w++){
            players[w].setchips(500);
        }

        int[][] shuffled =dealer.deckmaker();


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
            System.out.println("player " +(y-1)+" starts with " + players[y-2].chips);
        }
        int stage = 0;
        boolean gameover = false;
        String suite ="";
        while (gameover==false) {
            boolean turn_over = false;


            if (stage==pcount){
                System.out.println("the flop is ");
                for(int e=51; e>48; e--){
                    String suites= dSuitconverter(e,1, shuffled);

                    System.out.println(shuffled[e][0] + " of "+ suites);

                }
            }

            if (stage==2*pcount){
                System.out.println("the fourth street is ");

                for(int e=51; e>47; e--){
                    String suites= dSuitconverter(e,1, shuffled);

                    System.out.println(shuffled[e][0] + " of "+ suites);
                }
            }

            if (stage==3*pcount) {
                System.out.println("the river is ");

                for (int e = 51; e > 46; e--) {
                    String suites= dSuitconverter(e,1, shuffled);

                    System.out.println(shuffled[e][0] + " of " + suites);

                }
            }
            if(stage==4*pcount){
                wincheck(players, pcount, shuffled);
                gameover=true;
                break;

            }


            for (int i=0; i<pcount; i++) {
                stage = stage+1;
                boolean allin = false;


                //makes object current player which is whatever object players[i]
                Player currentPlayer = players[i];

                for (int v=0; v<pcount; v++){
                    players[v].setIn_game(true);
                }
                turn_over = false;
                while (turn_over == false && players[i].In_game==true) {
                    System.out.println("it is player "+(i+1)+"'s turn");
                    System.out.println("input anything when that player has the computer");
                    //holds until something is inputted
                    String confirmer = input.nextLine();
                    //outputs the current players hand


                    //converts from code into the name of the suite
                    String suit = pSuitconverter(1,0, players, i);
                    System.out.println(players[i].Playerhand[0][0] + " of "+ suit);
                     suit = pSuitconverter(1,1, players, i);
                    System.out.println(players[i].Playerhand[1][0] + " of "+ suit);




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



    public static String pSuitconverter(int x, int y, Player[] players, int i){//used for player
        String suit ="";
        if (players[i].Playerhand[x][y] == 1){
            suit ="spades";
        } else if (players[i].Playerhand[x][y] == 2) {
            suit= "hearts";
        } else if (players[i].Playerhand[x][y] == 3) {
            suit = "diamonds";
        } else if (players[i].Playerhand[x][y] == 4) {
            suit = "clubs";
        }
        return suit;
    }
    public static String dSuitconverter(int x, int y, int[][] shuffled){ //used for dealing
        String suite ="";
        if (shuffled[x][y] == 1){
            suite="spades";
        } else if (shuffled[x][y] == 2) {
            suite="hearts";
        } else if (shuffled[x][y] == 3) {
            suite="diamonds";
        } else if (shuffled[x][y] == 4) {
            suite ="clubs";
        }
        return suite;
    }

    public static void wincheck(Player[] players, int pcount, int[][] shuffled){
        twopair_checker(players, shuffled, pcount);
        threepair_checker(players, shuffled, pcount);
        fourpair_checker(players, shuffled, pcount);
        straight_checker(players, shuffled, pcount);
        flush_checker(players, shuffled, pcount);
        house_checker(players, shuffled, pcount);


        for (int i=0; i<pcount; i++){
            players[i].setPoints(0);
            if (players[i].twopair[0]>0){
                players[i].points++;
                players[i].points++;
                if (players[i].twopair[1]>0){
                    players[i].points++;
                    players[i].points++;
                }
                if (players[i].twopair[2]>0){
                    players[i].points++;
                    players[i].points++;
                }
            }
            if (players[i].threepair[0]>0){
                players[i].points=players[i].points+3;
                if (players[i].threepair[1]>0){
                    players[i].points=players[i].points+3;
                }
            }
            if (players[i].Straight==true){
                players[i].points=players[i].points+4;
            }
            if (players[i].Flush==true){
                players[i].points=players[i].points+5;
            }
            if (players[i].fullhouse==true){
                players[i].points=players[i].points+6;
            }
            if (players[i].fourpair[0]!=0){
                players[i].points=players[i].points+7;
            }



        }


        if (players[0].points==players[1].points){
            if(players[0].fourpair[0]!=0) {
                if (players[0].fourpair[0] > players[1].fourpair[0]) {
                    players[0].points++;
                }else{
                    players[1].points++;
                }
            }

            else if(players[0].fullhouse==true){
                if (players[0].threepair[0] > players[1].threepair[0] && players[0].threepair[0]>players[1].threepair[1] ||players[0].threepair[1]>players[1].threepair[1] ) {
                    players[0].points++;
                }else if (players[0].threepair[0] < players[1].threepair[0] && players[0].threepair[0]<players[1].threepair[1] ||players[0].threepair[1]<players[1].threepair[1]) {
                    players[1].points++;
                }
                else{
                    Arrays.sort(players[0].twopair);
                    Arrays.sort(players[1].twopair);
                    if(players[0].twopair[0]>players[1].twopair[0]){
                        players[0].points++;
                    } else if (players[0].twopair[0]<players[1].twopair[0]) {
                        players[1].points++;
                    }
                    else {
                        System.out.println("tie");
                    }
                }
            } else if(players[0].twopair[0]!=0){
                Arrays.sort(players[0].twopair);
                Arrays.sort(players[1].twopair);
                if(players[0].twopair[0]>players[1].twopair[0]){
                    players[0].points++;
                } else if (players[0].twopair[0]<players[1].twopair[0]) {
                    players[1].points++;
                }
            }

            else if(players[0].threepair[0]!=0){
                if (players[0].threepair[0] > players[1].threepair[0] && players[0].threepair[0]>players[1].threepair[1] ||players[0].threepair[1]>players[1].threepair[1] ) {
                    players[0].points++;
                }else{
                    players[1].points++;
                }
            }
        }


        //fix for more than 2 later
        if (players[0].points>players[1].points){
            System.out.println("p1 wins");
            player1.chips= player1.chips+middle.chips;
            middle.chips=0;
        }else{
            System.out.println("p2 wins");
            player2.chips= player2.chips+middle.chips;
            middle.chips=0;
        }
    }


    public static void flush_checker( Player[] players, int[][] shuffled, int pcount){
        int clubchecker =0;
        int heartchecker =0;
        int diamondchecker =0;
        int spadechecker=0;


        for (int i=0; i<pcount; i++){
            players[i].setFlush(false);
            for (int x=0; x<pcount; x++){
                String suit =pSuitconverter(1, x, players,i);
                switch (suit){
                    case "club":
                        clubchecker++;
                        break;
                    case "heart":
                        heartchecker++;
                        break;
                    case "diamond":
                        diamondchecker++;
                        break;
                    case "spade":
                        spadechecker++;
                        break;
                }
            }


            for (int y=51; y>46; y--){
                String dsuit= dSuitconverter(y, 1, shuffled);
                switch (dsuit){
                    case "club":
                        clubchecker++;
                        break;
                    case "heart":
                        heartchecker++;
                        break;
                    case "diamond":
                        diamondchecker++;
                        break;
                    case "spade":
                        spadechecker++;
                        break;
                }
            }
            if(clubchecker<4||heartchecker<4||diamondchecker<4||spadechecker<4){
                players[i].setFlush(true);
            }

        }
    }

    public static void straight_checker( Player[] players, int[][] shuffled, int pcount){
        for (int i=0; i<pcount; i++) {
            players[i].setStraight(false);
            int consec =0;
            int[] numbers = new int[]{players[i].Playerhand[0][0], players[i].Playerhand[1][0], shuffled[51][0], shuffled[50][0], shuffled[49][0], shuffled[48][0], shuffled[47][0]};
            Arrays.sort(numbers);

            for (int x=0; x<6;x++){
                if (numbers[x]==numbers[x+1]-1){
                    consec++;
                }
            }

            if (consec>4){
                players[i].setStraight(true);
            }

        }
    }

    public static void twopair_checker(Player[] players, int[][] shuffled, int pcount){
        int checker=0;
        int p=0;
        int q=0;
        int r=0;


        for (int i=0; i<pcount; i++) {
            players[i].setTwopair(new int[]{-2, -4, -6});
            int[] numbers = new int[]{players[i].Playerhand[0][0], players[i].Playerhand[1][0], shuffled[51][0], shuffled[50][0], shuffled[49][0], shuffled[48][0], shuffled[47][0],0,0};
            Arrays.sort(numbers);
            for (int x=0; x<7;x++){

                if (numbers[x] == numbers[x+1] && numbers[x]!=numbers[x+2]){

                    checker++;

                    switch(checker) {
                        case 1:
                            p = numbers[x];
                            break;
                        case 2:
                            q=numbers[x];
                            break;
                        case 3:
                            r=numbers[x];
                            break;
                    }

                    players[i].setTwopair(new int[]{p, q, r});

                }

            }
        }
    }


    public static void threepair_checker(Player[] players, int[][] shuffled, int pcount){
        int checker=0;
        int p=0;
        int q=0;


        for (int i=0; i<pcount; i++) {
            players[i].setThreepair(new int[]{-4, -8});
            int[] numbers = new int[]{players[i].Playerhand[0][0], players[i].Playerhand[1][0], shuffled[51][0], shuffled[50][0], shuffled[49][0], shuffled[48][0], shuffled[47][0],0,0,0};
            Arrays.sort(numbers);
            for (int x=0; x<7;x++){

                if (numbers[x] == numbers[x+1] && numbers[x]==numbers[x+2] && numbers[x+3]!=numbers[x]){

                    checker++;

                    switch(checker) {
                        case 1:
                            p = numbers[x];
                            break;
                        case 2:
                            q=numbers[x];
                            break;

                    }

                    players[i].setThreepair(new int[]{p, q});

                }

            }
        }
    }

    public static void fourpair_checker(Player[] players, int[][] shuffled, int pcount){
        int p=0;

        for (int i=0; i<pcount; i++) {
            int[] numbers = new int[]{players[i].Playerhand[0][0], players[i].Playerhand[1][0], shuffled[51][0], shuffled[50][0], shuffled[49][0], shuffled[48][0], shuffled[47][0],0,0};
            Arrays.sort(numbers);
            for (int x=0; x<7;x++){

                if (numbers[x] == numbers[x+1] && numbers[x]==numbers[x+2] && numbers[x+3]==numbers[x]){
                    p = numbers[x];
                }
                players[i].setfourpair(new int[]{p});

            }
        }
    }

    public static void house_checker(Player[] players, int[][] shuffled, int pcount){
        for (int i=0; i<pcount; i++){
            if (players[i].twopair[0]>0 && players[i].threepair[0]>0){
                players[i].setfullhouse(true);
            }
        }
    }


}