/**
 * @author WuQiChuan
 * @Description: 节点,为了调用方便，
 * @Date: Created in:2018/10/14 14:27
 * @Version: 1.0
 */
public class Node {
    //坐标x
    public Integer x;
    //坐标y
    public Integer y;
    //步数
    public Integer step;
    //拐角数(默认为0)
    public Integer turn;
    //当前前进方向,0右->1下->2左->3上
    //默认优先往右
    public Integer direction;
    //上一个节点
    public Node prev;

    /**
     * @description: 构造方法，初始化节点属性
     * @author WuQiChuan
     * @date 2018/10/14 14:33
     * @param x, y, step, turn, prev
     * @return
     * @version: 1.0
     */
    public Node(Integer x,Integer y,Integer step,Integer turn,Integer direction,Node prev){
        this.x = x;
        this.y = y;
        this.step = step;
        this.turn = turn;
        this.direction = direction;
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", step=" + step +
                ", turn=" + turn +
                ", direction=" + direction +
                ", prev=" + prev +
                '}';
    }
}
