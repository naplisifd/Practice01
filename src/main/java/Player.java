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
    public static void Raise(int chips){
        System.out.println("raised");

    }
    public static void Check(){

    }
    public static void Call(){

    }
    public static void AllIn(){

    }
    public static void Fold(){}
}