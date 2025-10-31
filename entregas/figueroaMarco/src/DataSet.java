public class DataSet {
    private Object[] data;
    private int before;
    private int after;
    
    public DataSet(Object[] data, String datatype, int before, int after) {
        this.data = data;
        this.before = before;
        this.after = after;
    }
    
    public DataSet() {
      
    }
    
}

