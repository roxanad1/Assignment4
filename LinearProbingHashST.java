
public class LinearProbingHashST<Key, Value>
{
    private int M = 20000; // hash table size
    private Value[] vals = (Value[]) new Object[M];
    private Key[] keys = (Key[]) new Object[M];
    private int comparisons = 0;
    private boolean setHashOld;

    public LinearProbingHashST(boolean setHashOld){ //sets which hashcode to used based on declaration in main
        this.setHashOld = setHashOld;
    }

    private static class Node<Key, Value>{
        private Object key;
        private Object val;
        private Node next;

        public Node(Key key, Value val, Node<Key, Value> next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private int hash(Key key){
        int hashCode;
        if (setHashOld){ //old hash
            hashCode = hashCodeOld(key.toString());
        }
        else{ //new hash
            hashCode = hashCodeNew(key.toString());
        }
        return (hashCode & 0x7fffffff) % M;
    }

    public Value get(Key key){
        comparisons = 0;
        for (int i = hash(key); keys[i] != null; i = (i+1 %M)) {
            comparisons++;
            if (key.equals(keys[i])) {
                return vals[i];
            }
        }
        return null;
    }

    public void put(Key key, Value val){
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M){
            if (keys[i].equals(key)){
                break;
            }
        }
        keys[i] = key;
        vals[i] = val;
    }

    public int hashCodeOld(String key){
        int hash = 0;
        int skip = Math.max(1, key.length()/8);
        for (int i = 0; i < key.length(); i += skip){
            hash = (hash * 37) + key.charAt(i);
        }
        return hash;
    }

    public int hashCodeNew(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++){
            hash = (hash * 31) + key.charAt(i);
        }
        return hash;
    }

    public int getComparisons(){
        return comparisons;
    }
}