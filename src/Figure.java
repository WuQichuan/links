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
        if(noTurn( board,a,b)){
            board.getArr()[a.x][a.y] = 0;
            board.getArr()[b.x][b.y] = 0;
            return true;
        }
        return false;
    }

    boolean noTurn(Board board,Point a,Point b){
        System.out.println("第一个点的值为："+board.getArr()[a.x][a.y]);
        System.out.println("第二个点的值为："+board.getArr()[b.x][b.y]);
        //如果两个点值不相等则直接返回false
        if(board.getArr()[a.x][a.y].equals(board.getArr()[b.x][b.y]) ){
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
                        return false;
                    }
                }
            }
            return true;
        }
        return false;

    }
}
