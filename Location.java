public class Location{
    private int row;
    private int column;

    public Location(int row,int column){
        this.row=row;
        this.column=column;
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public int hashCode(){
        return row*100+column;
    }

    @Override
    public boolean equals(Object obj){
        Location ob = (Location) obj;
        if(ob.getRow()==row&&ob.getColumn()==column){
            return true;
        }else{
            return false;
        }
    }
}