package string;

/**
 * Created by zk on 2015/4/12.
 * <p>
 * 给定两个字符串 s1 和 s2，如何判断 s1 是 s2 的一个旋转版本？
 * </p>
 */
public class RotateMatch {
    public static void main(String[] args) {
        String s1 = "apple";
        String s2 = "elppa";
        String s3 = "elppp";

        System.out.println(checkRotate(s1, s2));
        System.out.println(checkRotate(s1, s3));
    }

    public static boolean checkRotate(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        for (int i = 0, j = s2.length() - 1; i < s2.length(); i++, j--) {
            if (s1.charAt(i) != s2.charAt(j)) {
                return false;
            }
        }

        return true;
    }
}
