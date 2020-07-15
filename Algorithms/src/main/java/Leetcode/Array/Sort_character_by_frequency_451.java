package Leetcode.Array;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * @author liuzy
 * @date 2020/7/15 23:57
 */
public class Sort_character_by_frequency_451 {

    /**
     * 示例 1:
     * 输入:
     * "tree"
     * 输出:
     * "eert"
     * 解释:
     * 'e'出现两次，'r'和't'都只出现一次。
     * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    public String frequencySort(String s) {
//        我们要创建一个map来存每个char及其次数。
//        创建maxheap，因为我们要保证堆顶char次数是最大的。
//        要创建一个stringbuilder（不要创建string，可以自己比较一下）去存储结果。
//        弹出堆顶，按照其统计次数，建立循环，加入char到结果中，重复以上步骤。
//        输出结果即可。


        char[] chars = s.toCharArray();
        // map保存每个字符出现次数的映射
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (e1, e2) -> e2.getValue() - e1.getValue());

        maxHeap.addAll(map.entrySet());

        StringBuilder sortedString = new StringBuilder(s.length());
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            for (int i = 0; i < entry.getValue(); i++) {
                sortedString.append(entry.getKey());
            }
        }
        return sortedString.toString();

    }
}
