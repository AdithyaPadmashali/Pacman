package entity;

public class Node {
    public int row;
    public int column;
    public int parent_row;
    public int parent_column;
    public double distance;
    public String type;

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;

    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getParentColumn() {
        return this.parent_column;
    }

    public void setParentColumn(int parent_column) {
        this.parent_column = parent_column;
    }

    public int getParentRow() {
        return this.parent_row;
    }

    public void setParentRow(int parent_row) {
        this.parent_row = parent_row;
    }

}
