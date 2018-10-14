/**
 * @author qichuan.wu@hand-china.com
 * @version 1.0
 * @description
 * @date 2018/10/9
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author 武琦川@hand-china.com
 * @version 1.0
 * @name Board
 * @description
 * @date 2018/10/9 9:27
 */

public class Board {
    //存放棋盘的数组
    private Integer[][] arr;
    //用来标记的数组
    private Integer[][] mark;
    private final static int edgePlus = 2;
    private int edgNum;
    /**
     * @param edgeNum 边包含的棋子个数
     * @return
     * @throws
     * @author qichuan.wu@hand-china.com
     * @version 1.0
     * @name
     * @description 初始化edgeNum X edgeNum大小的棋盘，并加上额外最外层边
     * @date 2018/10/9 9:45
     */
    public Board(int edgeNum){
        arr = new Integer[edgeNum+edgePlus][edgeNum+edgePlus];
        mark = new Integer[edgeNum+edgePlus][edgeNum+edgePlus];
        //将用作标记的棋盘初始化全为0
        for(int i = 0; i<=edgeNum+edgePlus-1;i++){
            for(int j = 0;j<=edgeNum+edgePlus-1;j++){
                mark[i][j] = 0;
            }
        }
        List<Integer> list = new ArrayList<>();
        this.edgNum = edgeNum;
        for(int i = 1; i<=edgeNum;i++){
            for(int j = 1;j<=edgeNum;j++){
                if(i>edgeNum/2){
                    //后一半赋值从集合随机访问取数据来保证产生偶数个随机数
                    int r= (int)(Math.random()*list.size());
                    //如果集合这个未知为空则重新随机访问集合直到该未知不空
                    while (list.get(r) == null){
                        r = (int)(Math.random()*list.size());
                    }
                    arr[i][j] = list.get(r);
                    list.remove(r);

                }else{
                    //头一般赋值的时候产生随机数并放入集合
                    arr[i][j] = 1+(int)(Math.random()*edgeNum*edgeNum);
                    list.add(arr[i][j]);
                }

            }
        }
        //初始化最外层的可连通边路
        for(int i = 0;i<edgeNum+edgePlus;i++){
            arr[0][i] = 0;
            arr[i][0] = 0;
            if(i == edgeNum+1){
                for(int j = 0;j<=i;j++){
                    arr[j][i] = 0;
                    arr[i][j] = 0;
                }
            }
        }
    }

    public Integer[][] getArr() {
        return arr;
    }

    public Integer[][] getMark() {
        return mark;
    }

    /**
     * @description: 输出当前棋盘情况
     * @author WuQiChuan
     * @date 2018/10/14 14:40
     * @param
     * @return void
     * @version: 1.0
     */
    void showArr(){
        for(int i = 0;i<edgNum+edgePlus;i++){
            for(int j = 0;j<edgNum+edgePlus;j++){
                System.out.print(arr[j][i]+"\t");
                if(j == edgNum+edgePlus-1){
                    System.out.println("");
                }
            }
        }
    }


}
