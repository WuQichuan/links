import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

/**
 * @author WuQiChuan
 * @Description: 深度优先遍历，尝试
 * @Date: Created in:2018/10/14 20:28
 * @Version: 1.0
 */
public class DepthFirstSearch {
    //路径终点的节点
    private Node nodeEnd;
    //用来标记的数组
    private Integer[][] mark;
    private Integer minStep;
    private Stack<Node> shortStack;
    private boolean successFlag = false;
    //控制前进方向的数组
    int[][] nextDirection = {
            {1,0},//向右前进一个单位
            {0,1},//向下前进一个单位
            {-1,0},//向左前进一个单位
            {0,-1}//向上前进一个单位
    };
    public boolean dfs(Node star,Node end,Board board){
        //对需要的变量进行初始化
        successFlag = false;
        shortStack = null;
        minStep = 999;
        //保存终点
        nodeEnd = end;
        //获取一个新的mark标记棋盘
        mark = board.getMark(board.getEdgNum());
        //如果棋盘两点值相等，则调用bfs方法判断两点是否可连通
        if(board.getArr()[star.x][star.y] == board.getArr()[end.x][end.y]){
            //执行递归算法
            dfs(star,board);
            if(successFlag){
                //如果可联通则两点值变为0
                board.getArr()[star.x][star.y] = 0;
                board.getArr()[end.x][end.y] = 0;
                //依次出栈，为了将倒叙输出变成正序
                System.out.println("链接成功，路径长度为："+minStep);
                System.out.println("最短路径依次为：");
                while (!shortStack.isEmpty()){
                    Node top = shortStack.pop();
                    System.out.println("(x="+top.x+",y="+top.y+")");
                }
                return true;
            }
        }
        return false;
    }

    private void dfs(Node node,Board board){
        //向四个方向移动
        for(int i = 0;i<4;i++){
            //移动过的新节点
            Node newNode = new Node(node.x+ nextDirection[i][0],node.y+nextDirection[i][1],node.step+1,node.turn,node);
            //接下来对这个新位置进行判断

            /*System.out.println("|");
            System.out.println("新节点信息"+newNode);
            System.out.println("目标节点信息"+nodeEnd);*/
            //如果节点的当前前进方向不等于现在的探索方向则拐角数+1
            if(newNode.prev != null){
                Node compare = newNode;
                //记录当前坐标
                int ax = compare.x;
                int ay = compare.y;
                //节点前移
                compare = compare.prev;
                if(compare.prev != null){
                    //节点前移
                    compare = compare.prev;
                    //记录两个节点前坐标
                    int bx = compare.x;
                    int by = compare.y;
                    //如果当前坐标xy和两个节点前xy都不同了说明发生了转折，拐点数+1
                    if(ax != bx && ay != by){
                        newNode.turn ++;
                    }
                }
            }

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
            //如果在棋盘的新点位置不通的话，则continue，进行下次循环（更换方向再尝试）
            if(board.getArr()[newNode.x][newNode.y] != 0){
                //虽然新点有值，但是新点是目标点
                if(newNode.x == nodeEnd.x && newNode.y == nodeEnd.y){

                }else{
                    //不是目标单但是有值就说明不通，continue，进行下次循环（更换方向再尝试）
                    //System.out.println("新节点再棋盘上位置不通");
                    continue;
                }

            }

            //说明找到了目标点，递归结束
            if(newNode.x == nodeEnd.x && newNode.y == nodeEnd.y){
                //设置成功变量为true
                successFlag = true;
                if(newNode.step < minStep){
                    //当前步数设置为最短步数
                    minStep = newNode.step;
                    Node listNode = newNode;
                    Stack<Node> stack = new Stack<>();
                    while (listNode != null){
                        stack.push(listNode);
                        listNode = listNode.prev;
                    }
                    //将该栈设置为最短栈
                    shortStack = stack;

                }
                return ;
            }

            //如果新点没有被走过，并且新点的是可以走的通路则递归调用，传入新点
            if(mark[newNode.x][newNode.y] != 1 && board.getArr()[newNode.x][newNode.y] == 0){
                mark[newNode.x][newNode.y] = 1;
                dfs(newNode,board);
                mark[newNode.x][newNode.y] = 0;
            }
        }
        //没有找到全部尝试后都没找到目标点，递归结束
        return ;
    }
}
