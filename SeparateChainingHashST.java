public class SeparateChainingHashST<Key, Value> {
    private int M = 1000; // hash table size
    private Node[] st = new Node[M];
    private int comparisons = 0;
    private boolean setHashOld;

    public SeparateChainingHashST(boolean setHashOld){ //sets which hashcode to used based on declaration in main
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
        if (setHashOld){ //old hash code
            hashCode = hashCodeOld(key.toString());
        }
        else{ //new hash code
            hashCode = hashCodeNew(key.toString());
        }
        return (hashCode & 0x7fffffff) % M;
    }

    public Value get(Key key){
        comparisons = 0;
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            comparisons++;
            if (key.equals(x.key)) {
                return (Value) x.val;
            }
        }
        return null;
    }
    public void put(Key key, Value val){
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next){
            if (key.equals(x.key)){
                x.val = val;
                return;
            }
        }
        st[i] = new Node(key, val, st[i]);
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