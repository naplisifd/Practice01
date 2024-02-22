import java.util.Scanner;
public class Main {

    public static Player player1 = new Player();
    //public static int chips =Player.getchips();

    public static Player player2 = new Player();
    //public static int chips2 =Player.getchips();





    public static void main(String[] args) {
        player1.setchips(10);
        System.out.println(player1.chips);
        System.out.println(player2.chips);
       // chips2=11;
        player2.setchips(11);
       // player1.chips=4;
        System.out.println(player1.chips);
        System.out.println(player2.chips);
        Scanner input = new Scanner(System.in);
        new Person();
        //checks for raise
        System.out.println("what do you want to do");
        String action = input.nextLine();
        if (action.equalsIgnoreCase("raise")){
          //  Person.Raise(int p1chips);
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