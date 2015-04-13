package string;

/**
 * Created by zk on 2015/4/12.
 * 给定一个字符串，要求把字符串后面的若干个字符移动到字符串的头部，
 * 如把字符串 "abcdef" 后面的 2 个字符 'e' 和 'f' 移动到字符串的头部，
 * 使得原字符串变成字符串 "efabcd"。要求对长度为 n 的字符串操作的时间复杂度为 O(n)，空间复杂度为 O(1)。
 * </p>
 */
public class RightRotateString {
    public static void main(String[] args) {
        String s1 = "abcdefg";
        String s2 = "abcdefgh";

        char[] t1 = s1.toCharArray();
        char[] t2 = s2.toCharArray();

        rightRotateString(t1, 2);
        rightRotateString(t2, 2);

        String str1 = new String(t1);
        String str2 = new String(t2);

        System.out.println(str1);
        System.out.println(str2);
    }

    public static void rightRotateString(char[] str, int m) {
        reverseString(str, 0, str.length - m - 1);
        reverseString(str, str.length - m, str.length - 1);
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
}
