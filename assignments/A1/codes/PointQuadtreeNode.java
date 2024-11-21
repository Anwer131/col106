class CellTower {
    int x;
    int y;
    int cost;

    public CellTower(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    public double distance(int x, int y) {
        return Math.sqrt(Math.pow(this.x-x,2)+Math.pow(this.y-y,2));
    }
}

public class PointQuadtreeNode {

    public CellTower celltower;
    public PointQuadtreeNode[] quadrants;

    public PointQuadtreeNode(CellTower a) {
        this.celltower = a;
        this.quadrants = new PointQuadtreeNode[4];
    }
}
