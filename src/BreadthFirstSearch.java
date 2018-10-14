import java.util.LinkedList;
import java.util.Queue;

/**
 * @author WuQiChuan
 * @Description: 广度优先搜索
 * @Date: Created in:2018/10/14 14:27
 * @Version: 1.0
 */
public class BreadthFirstSearch {
    //路径终点的节点
    private Node nodeEnd;
    //用来标记的数组
    private Integer[][] mark;
    //用来控制查找的队列
    Queue<Node> queue = new LinkedList<>();
    //控制前进方向的数组
    int[][] nextDirection = {
            {1,0},//向右前进一个单位
            {0,1},//向下前进一个单位
            {-1,0},//向左前进一个单位
            {0,-1}//向上前进一个单位
    };

    /**
     * @description: 传入开始结束两个节点，来进行链接消灭判断
     * @author WuQiChuan
     * @date 2018/10/14 14:46
     * @param star, end
     * @return boolean
     * @version: 1.0
     */
    public boolean bfs(Node star,Node end,Board board){
        nodeEnd = end;
        mark = board.getMark();
        //如果棋盘两点值相等
        if(board.getArr()[star.x][star.y] == board.getArr()[end.x][end.y]){
            if(bfs(star,board)){
                board.getArr()[star.x][star.y] = 0;
                board.getArr()[end.x][end.y] = 0;
                return true;
            }
        }
        return false;
    }

    private boolean bfs(Node node,Board board){
        //首先标记该节点为走过的节点
        mark[node.x][node.y] = 1;
        queue.offer(node);
        while (!queue.isEmpty()){
            //对四个方向进行移动依次为，右->下->左->上
            for(int i = 0;i<4;i++){
                Node nowNode = queue.peek();

                //如果该节点没有前进方向的话将当前前进方向给节点的前进方向
                if(nowNode.direction == null){
                    nowNode.direction = i;
                }
                //移动过的新节点
                Node newNode = new Node(nowNode.x+ nextDirection[i][0],nowNode.y+nextDirection[i][1],nowNode.step+1,nowNode.turn,nowNode.direction,nowNode);
                //接下来这个新位置进行判断
                //如果节点的当前前进方向不等于现在的探索方向则拐角数+1并将前进方向改为新的方向
                if(newNode.prev != null){
                    Node compare = newNode;
                    //System.out.println("拐角判断");
                    int ax = compare.x;
                    int ay = compare.y;
                   // System.out.println("当前x= " +ax+"当前y="+ay);
                    compare = compare.prev;
                    if(compare.prev != null){
                        compare = compare.prev;
                        int bx = compare.x;
                        int by = compare.y;
                        //System.out.println("两个节点前x= " +bx+"两个节点前y="+by);
                        if(ax != bx && ay != by){
                            newNode.turn ++;
                            newNode.direction = i;
                        }
                    }
                }
                /*System.out.println("|");
                System.out.println("当前队首节点为："+nowNode);
                System.out.println("新节点信息"+newNode);
                System.out.println("目标节点信息"+nodeEnd);*/
                //判断边界合法性，如果新点已经越过边界，说明新点不行，则continue，进行下次循环（更换方向再尝试）
                if(newNode.x >= mark[0].length || newNode.x<0 ||newNode.y >= mark[0].length || newNode.y<0){
                    //System.out.println("新节点超出边界");
                    continue;
                }
                //如果拐角数>2，不符合二折点要求，则continue，进行下次循环（更换方向再尝试）
                if(newNode.turn >2){
                    //System.out.println("新节点:折点>2");
                    continue;
                }
                //说明找到了目标点
                if(newNode.x == nodeEnd.x && newNode.y == nodeEnd.y){
                    System.out.println("链接成功，路径长度为："+newNode.step);
                    Node show = newNode;
                    System.out.println("最短路径依次为（倒叙）：");
                    while (show != null){
                        System.out.println("(x="+show.x+",y="+show.y+")");
                        show = show.prev;
                    }
                    return true;
                }
                //如果在棋盘的新点位置不通的话，则continue，进行下次循环（更换方向再尝试）
                if(board.getArr()[newNode.x][newNode.y] != 0){
                    //System.out.println("新节点再棋盘上位置不通");
                    continue;
                }

                //如果新点没有被走过，并且新点的是可以走的通路,则步长+1，入队，标记走过的点
                if(mark[newNode.x][newNode.y] != 1 && board.getArr()[newNode.x][newNode.y] == 0){
                    queue.offer(newNode);
                    mark[newNode.x][newNode.y] = 1;
                }



            }
            //当前节点的四个方向全尝试完之后就出队
            queue.poll();
        }
        return  false;
    }

}
