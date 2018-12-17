package problem;

import java.util.Scanner;

/**
 * 求解老鼠走迷宫问题类
 * 可以求得对应迷宫地图的老鼠走迷宫问题符合情况的路径条数
 * 并且得到这些路径的最大长度和最小长度
 * */
public class solveProblem {

    /**
     * 1.容易出错
     * 默认路径的最大长度应该是一个很小的值，这样才能选择更大的值
     * 默认路径的最大长度应该是一个很大的值，这样才能选择更小的值
     * */
    // 默认路径的最大长度
    private static final int MAX = Integer.MIN_VALUE;
    // 默认路径的最大长度
    private static final int MIN = Integer.MAX_VALUE;
    // Java 标准输入器
    private static Scanner cin ;
    // 迷宫地图每个位置是否经过 ，true 经过，false 没有经过
    private static boolean[][] vis;
    // 迷宫地图
    private static mapInformation information;
    // 老鼠每一步的行位移
    private static int[] xto = {1 , -1 ,0 , 0};
    // 老鼠每一步行位移对应的列位移
    private static int[] yto = {0 , 0 , 1, -1};
    // 迷宫地图每个位置是第几个经过的
    private static int[][] node_cnt;
    // 老鼠走迷宫问题符合情况的路径有几种
    private static int num;
    // 实际路径的最大长度
    private static int max;
    // 实际路径的最小长度
    private static int min;

    /**
     * 静态块
     * 在该类被类加载器加载的时候，就会运行静态块，进行类属性的初始化
     * */
    static {
        // 初始化 Java 标准输入器属性
        cin = new Scanner(System.in);
        // 初始化迷宫地图属性
        information = new mapInformation();
    }

    // 初始化迷宫地图每个位置是否经过属性，
    // 默认为全部没有经过，即数组全部为 false
    public static void setVis(int row , int col) {
        solveProblem.vis = new boolean[row][col];
    }

    // 设置迷宫地图的各种属性
    public static void setInformation(int row ,int col , int start_x ,
                                      int start_y , int end_x , int end_y ,
                                      byte[][] map) {
        // 设置迷宫地图行
        solveProblem.information.setRow(row);

        // 设置迷宫地图列
        solveProblem.information.setCol(col);

        // 设置迷宫地图起点的行
        solveProblem.information.setStart_x(start_x);

        // 设置迷宫地图起点的列
        solveProblem.information.setStart_y(start_y);

        // 设置迷宫地图终点的行
        solveProblem.information.setEnd_x(end_x);

        // 设置迷宫地图终点的列
        solveProblem.information.setEnd_y(end_y);

        // 设置迷宫地图的详细信息
        solveProblem.information.setMap(map);
    }

    // 初始化迷宫地图每个位置是第几个经过的，默认全部为 0
    public static void setNode_cnt(int row , int col) {
        solveProblem.node_cnt = new int[row][col];
    }

    // main 方法
    public static void main(String[] args){

        while (true){

            // 开始老鼠走迷宫问题
            System.out.println("老鼠走迷宫问题程序开始：");

            // 判断程序是否需要继续，1 继续，0 结束
            System.out.print("输入 1 程序继续，输入 0 程序结束：");
            int type = cin.nextInt();
            if(type==0){
                System.out.println("程序结束！");
                break;
            }

            // 新的老鼠走迷宫问题，
            // 初始化老鼠走迷宫问题符合情况的路径有几种属性为 0
            num = 0;
            // 设置路径的最大长度和最小长度
            max = MAX;
            min = MIN;

            // 输入迷宫地图的各种属性
            System.out.print("输入地图的行：");
            int row = cin.nextInt(); // 输入迷宫地图的行

            System.out.print("输入地图的列：");
            int col = cin.nextInt(); // 输入迷宫地图的列

            System.out.print("输入地图的起点(x,y)：");
            int start_x = cin.nextInt(); // 输入迷宫地图起点的行
            int start_y = cin.nextInt(); // 输入迷宫地图起点的列

            // 判断迷宫地图起点是否符合情况
            if(start_x <= 0 || start_x > row ||
               start_y <= 0 || start_y > col){
                System.out.println("迷宫地图的起点不符合情况！");
                continue;
            }

            System.out.print("输入地图的终点(x,y)：");
            int end_x = cin.nextInt(); // 输入迷宫地图终点的行
            int end_y = cin.nextInt(); // 输入迷宫地图终点的列

            // 判断迷宫地图终点是否符合情况
            if(end_x <= 0 || end_x > row ||
               end_y <= 0 || end_y > col){
                System.out.println("迷宫地图的终点不符合情况！");
                continue;
            }

            // 输入迷宫地图的位置信息
            System.out.println("输入地图的信息：");
            byte[][] map_test = new byte[ row+5 ][ col+5 ];
            for (int i = 0; i < row ; i++ ){
                for(int j = 0 ; j < col ; j++ ){
                    map_test[i][j] = cin.nextByte(); // 输入迷宫地图每个位置是否可以通过
                }
            }

            // 迷宫地图的属性输入完后，初始化类属性
            setInformation(row , col , start_x , start_y , end_x , end_y , map_test);
            setVis(row+5 , col+5);
            setNode_cnt( row+5 , col+5);

            // 因为程序设计语言数组的下标是从 0 开始的，
            // 为了适应这个性质，都自减
            start_x--;
            start_y--;
            end_x--;
            end_y--;

            // 判断迷宫地图是否符合情况 ， true 符合情况 ， false 不符合情况
            boolean map_ok = true;
            if(information.map[start_x][start_y] == 0){
                System.out.println("该迷宫地图的起点不能通过，不符合要求");
                map_ok = false;
            }
            if(information.map[end_x][end_y] == 0){
                System.out.println("该迷宫地图的终点不能通过，不符合要求");
                map_ok = false;
            }

            // 迷宫地图不符合情况 continue ， 结束此次循环
            if(!map_ok) continue;

            // 起点经过了
            vis[start_x][start_y] = true;

            // 起点为第一次经过
            node_cnt[start_x][start_y]  = 1;

            // 深度优先搜索，从起点开始
            DFS(start_x , start_y , 1);

            /**
             * 2.容易出错
             * 深度优先搜索结束后，需要把起点是否经过改为 false
             * 这个地方很容易出错，当没有重新改为 false 时，
             * 上一次搜索会对下一次搜索造成影响
             * 因为新的搜索，所有位置都应该是没有被经过的，
             * 所以上一次经过的位置应该重新设置为没有经过
             * 这样就不会对下一次搜索有影响
             */
            vis[start_x][start_y] = false;

            System.out.printf("该迷宫地图的老鼠走迷宫问" +
                    "题符合情况的路径条数为：%d\n",num);
            System.out.printf("其中符合条件路径的最大值为：%d\n",max);
            System.out.printf("其中符合条件路径的最小值为：%d\n",min);

            System.out.println();
        }
    }

    // x - 行位置 ， y 列位置 ， cnt - 位置（x,y）是第几次经过
    public static void DFS(int x,int y ,int cnt){

        // 到了终点
        if(x == information.end_x && y == information.end_y){

            // 第几种符合情况的路径
            System.out.printf("第 %d 种情况：\n",++num);
            for(int i = 0 ; i < information.row ; i++){
                for (int j = 0; j < information.col ; j++){

                    // 该位置是第几次被经过
                    if(vis[i][j]) System.out.printf("%d ",  node_cnt[i][j]);
                    // 没有经过该位置
                    else System.out.print("- ");
                }
                System.out.println();
            }
            // 更新路径的最大长度和最小长度
            min = Math.min(min , cnt);
            max = Math.max(max , cnt);
            return;
        }

        for(int i = 0; i < 4; i++){

            // 新的行位置
            int newx = x + xto[i];
            // 新的列位置
            int newy = y + yto[i];

            // 新位置不符合要求
            if(newx<0 || newy<0 || newx>=information.row ||
                    newy>=information.col) continue;

            // 新位置已经经过了，或者不可以通过
            if(vis[newx][newy] || information.map[newx][newy] == 0) continue;

            // 新位置符合要求，所以被经过了
            vis[newx][newy] = true;

            // 新位置是第几次被经过的
            node_cnt[newx][newy] = cnt+1;

            // 由新位置开始进行下一次深度优先搜索
            DFS(newx , newy , cnt+1);

            // 新位置的深度优先搜索结束，
            // 需要把新位置是否经过改为 false
            vis[newx][newy] = false;
        }
    }
}
