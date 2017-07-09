public class Solution {
    private String getRow(char c) {
        String top = "qwertyuiop";
        String mid = "asdfghjkl";
        String bottom = "zxcvbnm";
        if (top.indexOf(c) != -1) {
            return top;
        } else if (mid.indexOf(c) != -1) {
            return mid;
        } else return bottom;
    }

    public String[] findWords(String[] words) {
        List<String> result = new ArrayList<>();
        for (String s : words) {
            char first = Character.toLowerCase(s.charAt(0));
            String row = getRow(first);
            for (int i = 0; i <= s.length(); i++) {
                if (i == s.length()) {
                    result.add(s);
                    break;
                }
                if (row.indexOf(Character.toLowerCase(s.charAt(i))) == -1) {
                    break;
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }
}
