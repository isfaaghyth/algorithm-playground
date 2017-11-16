// https://leetcode.com/problems/number-of-atoms/discuss/
// 
// Given a chemical formula (given as a string), return the count of each atom.
// 
// An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
// 
// 1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.
// 
// Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.
// 
// A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.
// 
// Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
// 
// Example 1:
// Input: 
// formula = "H2O"
// Output: "H2O"
// Explanation: 
// The count of elements are {'H': 2, 'O': 1}.
// Example 2:
// Input: 
// formula = "Mg(OH)2"
// Output: "H2MgO2"
// Explanation: 
// The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
// Example 3:
// Input: 
// formula = "K4(ON(SO3)2)2"
// Output: "K4N2O14S4"
// Explanation: 
// The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
// Note:
// 
// All atom names consist of lowercase letters, except for the first character which is uppercase.
// The length of formula will be in the range [1, 1000].
// formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.
class Solution {
    int cursor = 0;
    char[] array;
    int length;

    private int getValue() {
        int val = 0;
        while (cursor < length && array[cursor] >= '0' && array[cursor] <= '9') {
            val = val * 10 + array[cursor++] - '0';
        }
        if (val == 0) val = 1;
        return val;
    }

    public String countOfAtoms(String formula) {
        array = formula.toCharArray();
        length = array.length;
        Deque<Map<String, Integer>> deque = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        while (cursor < length) {
            char c = array[cursor];
            cursor++;
            if (c == '(') {
                deque.push(map);
                map = new HashMap<>();
            } else if (c == ')') {
                int val = getValue();
                if (!deque.isEmpty()) {
                    Map<String, Integer> temp = map;
                    map = deque.pop();
                    for (String key : temp.keySet()) {
                        map.put(key, map.getOrDefault(key, 0) + temp.get(key) * val);
                    }
                }
            } else {
                int start = cursor - 1;
                while (cursor < length && array[cursor] >= 'a' && array[cursor] <= 'z') {
                    cursor++;
                }
                String s = new String(array, start, cursor - start);
                int val = getValue();
                map.put(s, map.getOrDefault(s,0) + val);
            }
        }
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for (String key: list){
            sb.append(key);
            if (map.get(key) > 1) sb.append(map.get(key));
        }
        return sb.toString();
    }
}