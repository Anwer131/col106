public class PointQuadtree {

    enum Quad {
        NW,
        NE,
        SW,
        SE
    }

    public PointQuadtreeNode root;

    public PointQuadtree() {
        this.root = null;
    }

    public boolean insert(CellTower a) {
        if(root == null) root = new PointQuadtreeNode(a);
        else{
            PointQuadtreeNode currTw = root;
            PointQuadtreeNode parTw = null;
            Quad qTw = null;
            while(currTw!=null){
                //NW
                if(a.x < currTw.celltower.x && a.y >= currTw.celltower.y){
                    qTw = Quad.NW;
                }
                //NE
                else if(a.x >= currTw.celltower.x && a.y > currTw.celltower.y){
                    qTw = Quad.NE;
                }
                //SW
                else if(a.x <= currTw.celltower.x && a.y < currTw.celltower.y){
                    qTw = Quad.SW;
                }
                //SE
                else if(a.x > currTw.celltower.x && a.y <= currTw.celltower.y){
                    qTw = Quad.SE;
                }
                //coincide
                else{
                    return false;
                }
                parTw = currTw;
                currTw = currTw.quadrants[qTw.ordinal()];
            }
            parTw.quadrants[qTw.ordinal()] = new PointQuadtreeNode(a);
        }
        return true;
    }

    public boolean cellTowerAt(int x, int y) {
        PointQuadtreeNode currTw = root;
        Quad qTw = null;
        while(currTw!=null){
            //NW
            if(x < currTw.celltower.x && y >= currTw.celltower.y){
                qTw = Quad.NW;
            }
            //NE
            else if(x >= currTw.celltower.x && y > currTw.celltower.y){
                qTw = Quad.NE;
            }
            //SW
            else if(x <= currTw.celltower.x && y < currTw.celltower.y){
                qTw = Quad.SW;
            }
            //SE
            else if(x > currTw.celltower.x && y <= currTw.celltower.y){
                qTw = Quad.SE;
            }
            //tower found
            else{
                return true;
            }           
            currTw = currTw.quadrants[qTw.ordinal()];
        }
        return false;
    }

    public CellTower chooseCellTower(int x, int y, int r) {
        return recSearch(root, x, y, r, null);
    }
    private CellTower recSearch(PointQuadtreeNode tw, int x, int y, int r,CellTower ans){
        if(tw==null) return ans;
        //update if distance of new tower is less than r and cost is less than the previous one
        if(tw.celltower.distance(x, y)<=r && (ans==null || (tw.celltower.cost < ans.cost))) {
            ans = tw.celltower;
        }
        //case1 - NW
        if(tw.celltower.x-x>(-r) && y-tw.celltower.y>=(-r)){
            ans = recSearch(tw.quadrants[Quad.NW.ordinal()], x, y, r, ans); 
        }

        //case2 - NE
        if(x-tw.celltower.x>=(-r) && y-tw.celltower.y>(-r)){
            ans = recSearch(tw.quadrants[Quad.NE.ordinal()], x, y, r, ans);
        }
        //case3 - SW
        if(tw.celltower.x-x>=(-r) && tw.celltower.y-y>(-r)){
            ans = recSearch(tw.quadrants[Quad.SW.ordinal()], x, y, r, ans);
        }
        //case2 - SE
        if(x-tw.celltower.x>(-r) && tw.celltower.y-y>=(-r)){
            ans = recSearch(tw.quadrants[Quad.SE.ordinal()], x, y, r, ans);
        }
        return ans;
    }
}
