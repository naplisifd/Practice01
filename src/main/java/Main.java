import java.util.Scanner;
public class Main {
    public Player player = new Player();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        new Player();
        System.out.println("what do you want to do");
        String action = input.nextLine();
        if (action.equalsIgnoreCase("raise")){
            Player.Raise();
        }
    }
}