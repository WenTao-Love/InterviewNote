package string;

/**
 * Created by zk on 2015/4/12.
 * <p>
 * 给定两个分别由字母组成的字符串 s1 和字符串 s2，将字符串 s2 中所有字符都在字符串 s1 中删除？
 * </p>
 */
public class DeleteString {
    public static void main(String[] args) {
        String s1 = "cdacbcdefabcdef";
        String s2 = "ab";

        String s3 = deleteString(s1, s2);

        System.out.println(s3);
    }

    public static String deleteString(String s1, String s2) {
        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();

        boolean[] table = new boolean[256];

        for (int i = 0; i < s2.length(); i++) {
            table[a2[i]] = true;
        }

        int faster = 0;
        int slower = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (!table[a1[i]]) {
                a1[slower] = a1[faster];
                faster++;
                slower++;
            } else {
                faster++;
            }
        }

        return new String(a1, 0, slower);
    }
}
