import java.util.Scanner;

public class Player {

    public static void Player(){}

    public boolean In_game;

    public void setIn_game(boolean in_game1){
        this.In_game= in_game1;
    }
    public boolean getIn_game(){
        return In_game;
    }
    public  int chips;
    public void setchips(int chips_1) {
        this.chips = chips_1;
    }
    public  int getchips(){
        return chips;
    }

    public int[] [] Playerhand;
    public void setPlayerhand(int[] [] Playerhand1){this.Playerhand = Playerhand1;}
    public int[][]getPlayerhand(){return Playerhand;}



    public Boolean Straight;
    public void setStraight(Boolean Straight1){this.Straight = Straight1;}
    public Boolean getStraight(){return Straight;}

    public Boolean Flush;
    public void setFlush(Boolean Flush1){this.Flush = Flush1;}
    public Boolean getFlush(){return Flush;}


    public int[] twopair;
    public void setTwopair(int[] twopair1){this.twopair=twopair1;}
    public int[] getTwopair(){return twopair;}

    public int[] threepair;
    public void setThreepair(int[] threepair1){this.threepair=threepair1;}
    public int[] getThreepair(){return threepair;}

    public int[] fourpair;
    public void setfourpair(int[] fourpair1){this.fourpair=fourpair1;}
    public int[] getfourpair(){return fourpair;}

    public boolean fullhouse;
    public void setfullhouse(boolean fullhouse1){this.fullhouse=fullhouse1;}
    public boolean getfullhouse(){return fullhouse;}


    public int points;
    public void setPoints(int points1){this.points=points1;}
    public int getPoints(){return points;}

    public static int[] Raise(int chips, int dchips, boolean allin){
        int raiseamount = 0;
        if (allin== false){
            System.out.println("by how much");
            Scanner raiser = new Scanner(System.in);
             raiseamount = Integer.parseInt(raiser.nextLine());
        }else{
             raiseamount = chips;
        }
        if (raiseamount>chips){
            System.out.println("you dont have that much");
        }else {
            chips = chips - raiseamount;
            dchips = dchips + raiseamount;
        }
        return new int[]{chips, dchips, raiseamount}; //returns all 3

    }
    public static void Check(){

    }
    public static int[] Call(int chips, int dchips, int amount){
        if (chips - amount >= 0){
            dchips= dchips + amount;
            chips = chips - amount;
        }
        else{
            chips=0;
            dchips= dchips+chips;
        }
        return new int[]{chips, dchips};
    }
    public static void AllIn(){

    }
    public static void Fold(){}
}