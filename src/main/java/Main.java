import java.util.Scanner;
public class Main {

    public Player player1 = new Player();
    public static int chips =Player.getchips();
    player1.setchips(int(1));

    public static void main(String[] args) {
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