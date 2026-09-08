import java.util.*;

public class Apple {
    static class Node {
        Node[] children;
        boolean eow; // End of word flag

        public Node() {
            children = new Node[26];
            eow = false;
        }
    }

    static Node root = new Node();
    public static String ans = "";

    // Insert a word into the Trie
    public static void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';

            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();  // Fixed: Correct class name
            }

            if (i == word.length() - 1) {
                curr.children[idx].eow = true;
            }

            curr = curr.children[idx];
        }
    }

    // Search for a word in the Trie
    public static boolean search(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            int idx = key.charAt(i) - 'a';
            Node node = curr.children[idx];

            if (node == null) {
                return false;
            }

            if (i == key.length() - 1 && !node.eow) {
                return false;
            }

            curr = curr.children[idx];
        }
        return true;
    }

    // Find the longest word in the Trie that can be built character by character
    public static void longestWord(Node root, StringBuilder temp) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null && root.children[i].eow) {
                temp.append((char) (i + 'a'));

                if (temp.length() > ans.length()) {  // Fixed: Correct spelling of length()
                    ans = temp.toString();
                }

                longestWord(root.children[i], temp);
                temp.deleteCharAt(temp.length() - 1);  // Backtracking
            }
        }
    }

    public static void main(String[] args) {  // Fixed: Corrected method signature
        String words[] = {"a", "banana", "app", "appl", "apply", "apple"};

        for (String word : words) {  // Simplified loop
            insert(word);
        }

        longestWord(root, new StringBuilder(""));
        System.out.println(ans);  // Expected output: "apple" or "apply"
    }

    
}
