public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int length = s.length();
        if (length == 0) return true;
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                if (wordDict.contains(s.substring(i, j)) && dp[i]) {
                    dp[j] = true;
                }
            }
        }
        return dp[length];
    }
}