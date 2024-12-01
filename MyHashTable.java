public class MyHashTable<K,V>{
    private DLList<V>[] table;
    private MyHashSet<K> keyset;
    @SuppressWarnings("unchecked")
    public MyHashTable(){
        table = (DLList<V>[]) new DLList[1000000];
        keyset = new MyHashSet<K>();
    }
    public void put(K key, V value) {
        int index = key.hashCode();
        //System.out.println(key.hashCode() + "Key");

        keyset.add(key);
        //System.out.println(keyset.toDLList().toString());
        if (table[index] == null) {
            table[index] = new DLList<V>();
        }
        if(value!=null) table[index].add(value);
        
    }



    public void remove(K key, V value){
        int index = key.hashCode();
        DLList<V> dlList = table[index];

        if (dlList != null) {
            dlList.remove(value);
            if (dlList.size() == 0) {
                table[index] = null;
                keyset.remove(key);
            }
        }
    }

    public void remove(K key){
        int index = key.hashCode();
        keyset.remove(key);
        table[index] = null;
    }
    
    public DLList<V> get(K key){
        int index = key.hashCode();
        if(table[index]!=null){
            return table[index];
        }else{
            return null;
        }
    }

    public MyHashSet<K> keySet(){
        return keyset;
    }
    public String toString(){
        String stuff = "";
        for(int i=0;i<keyset.size();i++){
            stuff += table[i].toString();
        }
        return stuff;
    }

}