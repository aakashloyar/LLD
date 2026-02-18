package Problems;
import java.util.*;
public class Main {
    public static void main(String[] args) {

    }
}
class Pair{
    int a,b;
}
class PC implements Comparator<Pair> {
    public int compare(Pair a,Pair b) {
        return a.a-b.a;
    }
}
class Trie {
    Trie[] arr;
    Trie() {
        arr=new Trie[26];
    }
    void put(String s,int ind) {
        if(ind==s.length()) return;
        if(arr==null) {
            arr[0]=new Trie();
        }

    }
}