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



    public static int[] Raise(int chips, int dchips){
        System.out.println("by how much");
        Scanner raiser = new Scanner(System.in);
        int raiseamount = Integer.parseInt(raiser.nextLine());
         chips = chips - raiseamount;
         dchips = dchips + raiseamount;
        return new int[]{chips, dchips, raiseamount}; //returns all 3

    }
    public static void Check(){

    }
    public static int[] Call(int chips, int dchips, int amount){
        dchips= dchips + amount;
        chips = chips - amount;
        return new int[]{chips, dchips};
    }
    public static void AllIn(){

    }
    public static void Fold(){}
}