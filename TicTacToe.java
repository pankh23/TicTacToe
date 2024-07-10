import java.util.Scanner;

public class TicTacToe {
    
    private Player player1, player2;
    private Board board;
    private int numPlayers;

    public void startGame(){

        Scanner s=new Scanner(System.in);
        player1=playerTakeInput(++numPlayers);
        player2=playerTakeInput(++numPlayers);

        while(player1.getSymbol()==player2.getSymbol()){
            System.out.println("Symbol is already taken");
            player2.setSymbol(s.next().charAt(0));
        }

        board=new Board(player1.getSymbol(), player2.getSymbol());

        boolean player1Turn=true;

        int status=Board.INCOMPLETE;
        while(status==Board.INCOMPLETE || status==Board.INVALIDMOVE){
            if(player1Turn){
                System.out.println("Player 1 - " + player1.getName() + "'s turn");
                System.out.println("Enter x:");
                int x = s.nextInt();
                System.out.println("Enter y:");
                int y = s.nextInt();
                status = board.move(player1.getSymbol(), x, y);

                if(status == Board.INVALIDMOVE){
                    System.out.println("Invalid move, try again.");
                    continue;
                }

            } else {
                System.out.println("Player 2 - " + player2.getName() + "'s turn");
                System.out.println("Enter x:");
                int x = s.nextInt();
                System.out.println("Enter y:");
                int y = s.nextInt();
                status = board.move(player2.getSymbol(), x, y);

                if(status == Board.INVALIDMOVE){
                    System.out.println("Invalid move, try again.");
                    continue;
                }
            }

            player1Turn = !player1Turn;
            board.print();
        }
            
        if(status==Board.PLAYER1WINS){
            System.out.println("Player 1- "+player1.getName()+" wins");
        }
        else if(status==Board.PLAYER2WINS){
            System.out.println("Player 2- "+player2.getName()+" wins");
        }
        else{
            System.out.println("Draw");
        }

        s.close();
    }
    
  private Player playerTakeInput(int num){
        
        @SuppressWarnings("resource")
        Scanner s=new Scanner(System.in);
        System.out.println("Enter player "+num+" name: ");
        String str=s.nextLine();

        System.out.println("Enter player "+num+" symbol: ");
        char c=s.next().charAt(0);

        Player p=new Player(str, c);
        return p;

    }
    
    public static void main(String[] args) {
        
        TicTacToe t=new TicTacToe();
        t.startGame();
    }   
}
