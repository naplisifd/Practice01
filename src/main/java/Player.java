import java.util.Scanner;

public class Player {

    public static void Player(){
        System.out.println("asssa");
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
        System.out.println(raiseamount);
         chips = chips - raiseamount;
         dchips = dchips + raiseamount;
        return new int[]{chips, dchips};

    }
    public static void Check(){

    }
    public static void Call(){

    }
    public static void AllIn(){

    }
    public static void Fold(){}
}