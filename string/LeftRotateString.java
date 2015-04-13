package string;

/**
 * Created by zk on 2015/4/12.
 *
 * @author zk
 *         给定一个字符串，要求把字符串前面的若干个字符移动到字符串的尾部，
 *         如把字符串 "abcdef" 前面的 2 个字符 'a' 和 'b' 移动到字符串的尾部，
 *         使得原字符串变成字符串 "cdefab"。要求对长度为 n 的字符串操作的时间复杂度为 O(n)，
 *         空间复杂度为 O(1)。
 *         </p>
 */
public class LeftRotateString {
    public static void main(String[] args) {
        String s1 = "abcdefg";
        String s2 = "abcdefgh";

        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();

        leftRotateString(a1, 2);
        leftRotateStringByGcd(a2, 2);

        String str1 = new String(a1);
        String str2 = new String(a2);

        System.out.println(str1);
        System.out.println(str2);
    }

    public static void leftRotateString(char[] str, int m) {
        reverseString(str, 0, m - 1);
        reverseString(str, m, str.length - 1);
        reverseString(str, 0, str.length - 1);
    }

    private static void reverseString(char[] str, int begin, int end) {
        char temp;
        int i = begin;
        int j = end;

        while (i < j) {
            temp = str[i];
            str[i] = str[j];
            str[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * 求最大公约数
     * <p>
     * 假如整数n除以m，结果是无余数的整数，那么我们称m就是n的<em>约数</em>。
     * 需要注意的是，唯有被除数，除数，商皆为整数，余数为零时，此关系才成立。
     * 反过来说，我们称n为m的倍数。
     * </p>
     * <p>
     * <a>http://zh.wikipedia.org/wiki/最大公因數</a>
     * </p>
     *
     * @param m first number
     * @param n second number
     * @return 两个数的最大公约数
     */
    private static int gcd(int m, int n) {
        int k = m % n;

        if (k == 0) {
            return n;
        } else {
            return gcd(n, k);
        }
    }

    public static void leftRotateStringByGcd(char[] str, int m) {
        int n = str.length;
        int g = gcd(n, m);
        int e = n / g;

        char temp;
        for (int i = 0; i < g; i++) {
            temp = str[i];

            int j = 0;
            for (; j < e - 1; j++) {
                str[(i + j * m) % n] = str[(i + (j + 1) * m) % n];
            }

            str[(i + j * m) % n] = temp;
        }
    }
}
