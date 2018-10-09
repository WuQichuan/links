/**
 * @author qichuan.wu@hand-china.com
 * @version 1.0
 * @description
 * @date 2018/10/9
 */

import java.awt.*;

/**
 * @author 武琦川@hand-china.com
 * @version 1.0
 * @name Figure
 * @description
 * @date 2018/10/9 15:36
 */

public class Figure {

    public boolean LinksToEliminate(Board board,Point a, Point b){
        System.out.println("第一个点的值为："+board.getArr()[a.x][a.y]);
        System.out.println("第二个点的值为："+board.getArr()[b.x][b.y]);
        //判断直线是否能连通
        if(noTurn( board,a,b)){
            System.out.println("直线连通解决问题");
            //如果两点值相等说明可以消灭
            if(board.getArr()[a.x][a.y].equals(board.getArr()[b.x][b.y])){
                board.getArr()[a.x][a.y] = 0;
                board.getArr()[b.x][b.y] = 0;
                return true;
            }
        }
        //判断一折点是否能连通
        if (oneTurn(board, a, b)){
            System.out.println("一折点连通解决问题");
            //如果两点值相等说明可以消灭
            if(board.getArr()[a.x][a.y].equals(board.getArr()[b.x][b.y])){
                board.getArr()[a.x][a.y] = 0;
                board.getArr()[b.x][b.y] = 0;
                return true;
            }
        }

        //判断二折点是否能连通
        if(twoTurn(board, a, b)){
            System.out.println("二折点连通解决问题");
            //如果两点值相等说明可以消灭
            if(board.getArr()[a.x][a.y].equals(board.getArr()[b.x][b.y])){
                board.getArr()[a.x][a.y] = 0;
                board.getArr()[b.x][b.y] = 0;
                return true;
            }
        }
        return false;
    }

    boolean noTurn(Board board,Point a,Point b){
        //如果两个点值不相等
        if(!board.getArr()[a.x][a.y].equals(board.getArr()[b.x][b.y])  ){
            //两点不等但是两点其一有等于0的说明可以连通，返回true
            if(board.getArr()[a.x][a.y] == 0|| board.getArr()[b.x][b.y] == 0){
                return true;
            }
            //否则返回false
            return false;
        }
        //如果横向相等，a.y = by
        if(a.y == b.y){
            //如果a在b的左面
            if(a.x < b.x){
                //说明二者相邻
                if(a.x +1 ==b.x){
                    return true;
                }
                //遍历a到b之间的路径
                for(int i = a.x+1;i<b.x;i++){
                    //路径中如果有不为0的说明路径不通无法连接
                    if(board.getArr()[i][a.y] != 0){
                        System.out.println("当前路径失败，路径不通："+board.getArr()[i][a.y]);
                        return false;
                    }
                }
            }else{
                //说明二者相邻
                if(b.x +1 ==a.x){
                    return true;
                }
                for(int i = b.x+1;i<a.x;i++){
                    if(board.getArr()[i][a.y] != 0){
                        System.out.println("当前路径失败，路径不通："+board.getArr()[i][a.y]);
                        return false;
                    }
                }
            }
            return true;
        }

        //纵向相等a.x = b.x
        if(a.x == b.x){

            if(a.y < b.y){
                //说明二者相邻
                if(a.y +1 ==b.y){
                    return true;
                }
                for(int i = a.y+1;i<b.y;i++){
                    if(board.getArr()[a.x][i] != 0){
                        System.out.println("当前路径失败，路径不通："+board.getArr()[a.x][i]);
                        return false;
                    }
                }
            }else{
                //说明二者相邻
                if(b.y +1 ==a.y){
                    return true;
                }
                for(int i = b.y+1;i<a.y;i++){
                    if(board.getArr()[a.x][i] != 0){
                        System.out.println("当前路径失败，路径不通："+board.getArr()[a.x][i]);
                        return false;
                    }
                }
            }
            return true;
        }
        System.out.println("目前无法直线链接，当前两点不在同一直线");
        return false;

    }

    boolean oneTurn(Board board,Point a,Point b){
        //点C和D作为AB中间的一折点
        Point c = new Point(a.x,b.y);
        Point d = new Point(b.x,a.y);
        System.out.println("当前一折点可尝试的两个折点：");
        System.out.println("C :"+c.toString());
        System.out.println("D :"+d.toString());
        //如果C能和AB分别直线连通说明C作为折点可行，返回true
        if(noTurn(board,a,c) && noTurn(board,c,b)){
            return true;
        }

        //如果D能和AB分别直线连通说明C作为折点可行，返回true
        if(noTurn(board,a,d) && noTurn(board,d,b)){
            return true;
        }

        //C和D都不能作为折点的话说明AB直接无法一折连通
        return false;

    }

    boolean twoTurn(Board board,Point a,Point b){
        //尝试获取外二折。实例图路径二、三。
        for (int i = 0;i<board.getArr()[0].length;i++){
            //在垂直水平线中找出水平连通线
            Point c1 = new Point(a.x,i);
            Point c2 = new Point(b.x,i);
            //说明该平行线可连通
            if(noTurn(board,c1,c2)){
                //再去判断C1和A，   C2和B是否可连通
                if(noTurn(board,c1,a) && noTurn(board,c2,b)){
                    return true;
                }
            }

            //在水平线中超出垂直连通线
            Point d1 = new Point(i,a.y);
            Point d2 = new Point(i,b.y);
            //说明该平行线可连通
            if(noTurn(board,d1,d2)){
                //再去判断D1和A，   D2和B是否可连通
                if(noTurn(board,d1,a) && noTurn(board,d2,b)){
                    return true;
                }
            }

        }
        return false;
    }
}
