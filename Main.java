//Roxana Dicusara Assignment 4
public class Main {
    public static void main(String[] args) {

        boolean setHashOld = false; //new hashcode function will be used

        SeparateChainingHashST<Integer, String> separate = new SeparateChainingHashST<>(setHashOld);
        LinearProbingHashST<Integer, String> linear = new LinearProbingHashST<>(setHashOld);

        //insert words in hash tables
        In in = new In("WordList.txt");
        int sCount = 0;
        int lCount = 0;
        while(!in.isEmpty()){
            String word = in.readString();
            separate.put(sCount++, word);
            linear.put(lCount++, word);
        }

        //get input
        System.out.print("\nType your password: ");
        String password = StdIn.readLine().trim().toLowerCase();

        //display if output is strong
        boolean checkS = checkSeparate(password, separate);
        boolean checkL = checkLinear(password, linear);

        if (checkS == true && checkL == true){
            System.out.println("\nPassword is STRONG!");
        }
        else{
            System.out.println("\nPassword is WEAK");
        }

        //show number of comparisons for both hashCode() function in separate chaining and linear probing
        System.out.println("\n ~ if searching for word ~");
        System.out.println("Separate Chaining Comparison (NEW): " + separate.getComparisons());
        System.out.println("Linear Probing Comparison (NEW): " + linear.getComparisons());


    //OLD HASH CODE COMPARISON with the same password
        setHashOld = true;

        SeparateChainingHashST<Integer, String> separate2 = new SeparateChainingHashST<>(setHashOld);
        LinearProbingHashST<Integer, String> linear2 = new LinearProbingHashST<>(setHashOld);

        //insert words in hash tables for old hash comparison
        In in2 = new In("WordList.txt");
        int sCount2 = 0;
        int lCount2 = 0;
        while(!in2.isEmpty()){
            String word = in2.readString();
            separate2.put(sCount2++, word);
            linear2.put(lCount2++, word);
        }

        checkS = checkSeparate(password, separate2);
        checkL = checkLinear(password, linear2);

        System.out.println("Separate Chaining Comparison (OLD): " + separate2.getComparisons());
        System.out.println("Linear Probing Comparison (OLD): " + linear2.getComparisons());

    }

    //function for checking if password is strong
    public static boolean checkSeparate(String password, SeparateChainingHashST<Integer, String> separate){
        //if input is < 8 chars long -> output password is weak
        if (password.length() < 8){
            System.out.println("\nPassword is too short");
            return false;
        }

        // if password is not in dictionary and has 0-9 after word -> weak
        for (int i = 0; i < 10000; i++){
            String word = separate.get(i);
            if (password.replaceAll("\\d", "").trim().equals(word)) {
                return false;
            }
        }

        //if password is not in dictionary -> password is weak
        for (int i = 0; i < 10000; i++){
            String word = separate.get(i);
            if (password.equals(word)){
                return false;
            }
        }

        //if password covers all checks, it's strong
        return true;
    }

    //same check for linear probing
    public static boolean checkLinear(String password, LinearProbingHashST<Integer, String> linear){
        //if input is < 8 chars long -> output password is weak
        if (password.length() < 8){
            return false;
        }

        // if password is not in dictionary and has 0-9 after word -> weak
        for (int i = 0; i < 10000; i++){
            String word = linear.get(i);
            if (password.replaceAll("\\d", "").trim().equals(word)) {
                return false;
            }
        }

        //if password is not in dictionary -> password is weak
        for (int i = 0; i < 10000; i++){
            String pass = linear.get(i);
            if (password.equals(pass)){
                return false;
            }
        }

        //if password covers all checks, it's strong
        return true;
    }
}