import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int edgNum = 4;
        Board board = new Board(4);
        Figure figure = new Figure();
        while (true){
            System.out.println("-----当前棋盘-----");
            board.showArr();
            Point a = new Point();
            Point b = new Point();
            Scanner scan = new Scanner(System.in);
            System.out.println("请输入第一个坐标的X :");
            a.x = scan.nextInt();
            System.out.println("请输入第一个坐标的Y :");
            a.y = scan.nextInt();
            System.out.println("请输入第二个坐标的X :");
            b.x = scan.nextInt();
            System.out.println("请输入第二个坐标的Y :");
            b.y = scan.nextInt();
            if(figure.LinksToEliminate(board,a,b)){
                System.out.println("连接消除成功，请继续");
            }else{
                System.out.println("连接消除失败，请继续");
            }


        }

    }


}
