import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int edgNum = 4;
        int edgNumPlus = 2;
        Board board = new Board(edgNum);
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        while (true){
            System.out.println("-----当前棋盘-----");
            board.showArr();
            Node a = new Node(0,0,0,0,null,null);
            Node b = new Node(0,0,0,0,null,null);
            Scanner scan = new Scanner(System.in);
            System.out.println("请输入第一个坐标的X :");
            a.x = scan.nextInt();
            if(a.x >edgNum+edgNumPlus-1){
                continue;
            }
            System.out.println("请输入第一个坐标的Y :");
            a.y = scan.nextInt();
            if(a.y >edgNum+edgNumPlus-1){
                continue;
            }
            System.out.println("请输入第二个坐标的X :");
            b.x = scan.nextInt();
            if(b.x >edgNum+edgNumPlus-1){
                continue;
            }
            System.out.println("请输入第二个坐标的Y :");
            b.y = scan.nextInt();
            if(b.y >edgNum+edgNumPlus-1){
                continue;
            }
            if(bfs.bfs(a,b,board)){
                System.out.println("连接消除成功，请继续");
            }else{
                System.out.println("连接消除失败，请继续");
            }


        }

    }


}
