package SetAndMap.TreeSetAndSetProblemsInLeetCode04;

import java.util.TreeSet;

/**
 * leetcode 804
 * @author zhiyuanliu
 * @date 2020/6/23 13:50
 */
public class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        TreeSet<String> set = new TreeSet<>();
        for (String word : words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                res.append(codes[word.charAt(i) - 'a']);
            }

            set.add(res.toString());
        }

        return set.size();
    }
}
