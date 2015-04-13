package string;

/**
 * Created by zk on 2015/4/13.
 * 输入一个字符串，字符串里的字符是互不相同的，打印出该字符串中字符按照字典序输出所有的组合。
 * <p/>
 * 例如：输入字符串 "ab"，则输出由字符 'a', 'b' 所能排列出来的所有字符串：aa, ab, ba, bb。
 */
public class DictionaryString {
    public static void main(String[] args) {
        //要求字符是不同的，否则需要去重
        //要求输入是有序的，否则需要排序
        calculateRepeatablePermutations("abc".toCharArray(), new char[3], 0);
    }

    public static void calculateRepeatablePermutations(char[] s, char[] permutation, int p) {
        //如果只剩下一个字符
        if (p == s.length) {
            System.out.println(new String(permutation));
        } else {
            for (int i = 0; i < s.length; i++) {
                permutation[p] = s[i];
                calculateRepeatablePermutations(s, permutation, p + 1);
            }
        }
    }
}
