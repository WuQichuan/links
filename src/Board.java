/**
 * @author qichuan.wu@hand-china.com
 * @version 1.0
 * @description
 * @date 2018/10/9
 */

/**
 * @author 武琦川@hand-china.com
 * @version 1.0
 * @name Board
 * @description
 * @date 2018/10/9 9:27
 */

public class Board {
    private Integer[][] arr;
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
        this.edgNum = edgeNum;
        for(int i = 1; i<=edgeNum;i++){
            for(int j = 1;j<=edgeNum;j++){
                arr[i][j] = 1+(int)(Math.random()*9);
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
