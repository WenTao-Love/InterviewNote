package string;

/**
 * Created by zk on 2015/4/12.
 * 将字符串 s1 中的某字符 p 全部替换成字符串 s2。假设 s1 字符数组尾部有足够的空间存放新增字符。
 */
public class ReplaceString {
    public static void main(String[] args) {
        char[] s1 = new char[100];
        for (int i = 0; i < 9; i += 3) {
            s1[i] = 'a';
            s1[i + 1] = 'b';
            s1[i + 2] = 'c';
        }

        System.out.println(new String(s1));
        replaceAllChars(s1, 9, 'b', "&^%".toCharArray());
        System.out.println(new String(s1));
    }

    public static void replaceAllChars(char[] s1, int s1Length, int p, char[] s2) {
        int count = 0;

        for (int i = 0; i < s1Length; i++) {
            if (s1[i] == p) {
                count++;
            }
        }

        int newLength = s1Length + s2.length * count;

        //从后向前进行替换，避免数据丢失
        for (int i = s1Length - 1; i >= 0; i--) {
            if (s1[i] == p) {
                for (int j = 0; j < s2.length; j++) {
                    s1[newLength - s2.length + j] = s2[j];
                }
                newLength = newLength - s2.length;
            } else {
                s1[newLength - 1] = s1[i];
                newLength = newLength - 1;
            }
        }
    }
}
