package entity;

public class Node implements Comparable<Node> {
    public int row;
    public int column;
    public int parent_row;
    public int parent_column;
    public int distance;
    public String type;
    public double g;
    public double h;
    public double getDistance(){
        return this.distance;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
    public int getRow(){
        return this.row;
    }
    public void setRow(int row){
        this.row = row; 

    }
    public int getColumn(){
        return this.column ;
    }
    public void setColumn(int column)
    {
        this.column = column;
    }

    public int getParentColumn(){
        return this.parent_column ;
    }
    public void setParentColumn(int parent_column)
    {
        this.parent_column = parent_column;
    }

    public int getParentRow(){
        return this.parent_row;
    }
    public void setParentRow(int parent_row)
    {
        this.parent_row = parent_row;
    }

    public void setG(int parent_row)
    {
        this.g = g;
    }

    public void setH(int parent_row)
    {
        this.h = h;
    }

    public double getG(){
        return g;
    }

    public double getH(){
        return h;
    }
    

    @Override
    public int compareTo(Node node) {
        if (node.getDistance() == this.getDistance()) {
            return 0;
        } else if (this.getDistance() > node.getDistance()) {
            return 1;
        } else {
            return -1;
        }

    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Node)) {
            return false;
        }
        Node node = (Node) object;
        if (this.column == node.getColumn() && this.row == node.getRow()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.column;
        hash = 47 * hash + this.column;
        hash = 47 * hash + this.parent_column;
        hash = 47 * hash + this.parent_row;
        hash = 47 * hash + this.distance;
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Row=").append(getRow());
        sb.append(";").append("Column=").append(getColumn());
        return sb.toString();
    }
}
    



