package problem;

/**
 * 迷宫地图类
 * row 地图的行
 * col 地图的列
 * start_x 入口的行坐标
 * start_y 入口的列坐标
 * end_x 出口的行坐标
 * end_y 出口的列坐标
 * map[][] 地图的位置信息，类型为 byte 二维数组
 * 1 - 代表可以通过
 * 0 - 代表不能通过
 * */
public class mapInformation {

    int row; // 地图的行
    int col; // 地图的列
    int start_x; // 入口的行坐标
    int start_y; // 入口的列坐标
    int end_x; // 出口的行坐标
    int end_y; // 出口的列坐标

    /**
     * 地图的位置信息，类型为 byte 二维数组
     * 1 - 代表可以通过
     * 0 - 代表不能通过
     * */
    byte[][] map;

    /**
     * 以下是 set、get 地图类的属性
     * */
    // get row 行属性
    public int getRow() {
        return row;
    }
    // set row 行属性
    public void setRow(int row) {
        this.row = row;
    }
    // get col 列属性
    public int getCol() {
        return col;
    }
    // set col 列属性
    public void setCol(int col) {
        this.col = col;
    }
    // get map 地图信息
    public byte[][] getMap() {
        return map;
    }
    // set map 地图信息
    public void setMap(byte[][] map) {
        this.map = map;
    }
    // get start_x 起点的行坐标
    public int getStart_x() {
        return start_x;
    }
    // set start_x 起点的行坐标
    public void setStart_x(int start_x) {
        this.start_x = start_x;
    }
    // get start_y 起点的列坐标
    public int getStart_y() {
        return start_y;
    }
    // set start_y 起点的列坐标
    public void setStart_y(int start_y) {
        this.start_y = start_y;
    }
    // get end_x 终点的行坐标
    public int getEnd_x() {
        return end_x;
    }
    // set end_x 终点的行坐标
    public void setEnd_x(int end_x) {
        this.end_x = end_x;
    }
    // get end_y 终点的列坐标
    public int getEnd_y() {
        return end_y;
    }
    // set end_y 终点的列坐标
    public void setEnd_y(int end_y) {
        this.end_y = end_y;
    }
}
