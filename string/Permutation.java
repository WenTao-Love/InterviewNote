package string;

/**
 * Created by zk on 2015/4/12.
 * 给定字符串 s1 和 s2，判断是否能够将 s1 中的字符重新排列后变成 s2。假设字符全部为小写 a-z 字符，
 * 字符串中没有空格。
 * <p/>
 * 变位词（anagram）：是由变换某个词或短语的字母顺序而构成的新的词或短语。
 * </p>
 */
public class Permutation {
    public static void main(String[] args) {
        System.out.println(isPermutation("hello".toCharArray(), "ehollu".toCharArray()));
        System.out.println(isPermutation("hello".toCharArray(), "eholu".toCharArray()));
        System.out.println(isPermutation("hello".toCharArray(), "eholl".toCharArray()));
    }

    public static boolean isPermutation(char[] s1, char[] s2) {
        if (s1.length != s2.length) {
            return false;
        }

        int[] letters = new int[256];
        for (int i = 0; i < s1.length; i++) {
            letters[s1[i]]++;
        }

        for (int i = 0; i < s1.length; i++) {
            letters[s2[i]]--;

            if (letters[s2[i]] < 0) {
                return false;
            }
        }

        return true;
    }
}
