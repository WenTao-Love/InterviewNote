package string;

/**
 * Created by zk on 2015/4/12.
 * <p>
 * 给定两个分别由字母组成的字符串 s1 和字符串 s2，如何最快地判断字符串 s2 中所有字母是否都在字符串 s1 里？
 * </p>
 */
public class ContainString {
    public static void main(String[] args) {
        String s1 = "abcdefg";
        String s2 = "adg";
        String s3 = "kgl";

        System.out.println(isContainsAllChars(s1.toCharArray(), s2.toCharArray()));
        System.out.println(isContainsAllChars(s1.toCharArray(), s3.toCharArray()));
    }

    /**
     * 时间复杂度O(m + n)，空间复杂度O(1)
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isContainsAllChars(char[] s1, char[] s2) {
        int hash = 0;
        for (int i = 0; i < s1.length; i++) {
            hash |= (1 << (s1[i] - 'A'));
        }

        for (int i = 0; i < s2.length; i++) {
            if ((hash & (1 << (s2[i] - 'A'))) == 0) {
                return false;
            }
        }

        return true;
    }
}
