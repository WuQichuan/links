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
            Node a = new Node(0,0,0,0,null);
            Node b = new Node(0,0,0,0,null);
            Scanner scan = new Scanner(System.in);
            System.out.println("请输入第一个坐标的 X空格Y :");
            a.x = scan.nextInt();
            if(a.x >edgNum+edgNumPlus-1){
                continue;
            }
            a.y = scan.nextInt();
            if(a.y >edgNum+edgNumPlus-1){
                continue;
            }
            System.out.println("请输入第二个坐标的 X空格Y :");
            b.x = scan.nextInt();
            if(b.x >edgNum+edgNumPlus-1){
                continue;
            }
            b.y = scan.nextInt();
            if(b.y >edgNum+edgNumPlus-1){
                continue;
            }
            if(bfs.bfs(a,b,board)){
                System.out.println("连接消除成功，请继续");
                boolean clearFlag = true;
                for(int i = 0; i<=edgNum+edgNumPlus-1;i++){
                    for(int j = 0;j<=edgNum+edgNumPlus-1;j++){
                        if(board.getArr()[i][j] != 0){
                            clearFlag = false;
                        }
                    }
                }
                if(clearFlag){
                    //全部消除成功，扩大棋盘
                    System.out.println("全部消除成功！");
                    edgNum = edgNumPlus+2*edgNumPlus;
                    board = new Board(edgNum);
                }

            }else{
                System.out.println("连接消除失败，请继续");
            }


        }

    }


}
