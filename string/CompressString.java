package string;

/**
 * Created by zk on 2015/4/12.
 * <p>
 * 给定字符串 s，要求将连续出现的字符压缩至字符和数量，并返回新的字符串。
 * <p/>
 * <p/>
 * 比如：s = "aabccccaaa"，则压缩后的字符串为 s2 = "a2b1c4a3"。
 * </p>
 */
public class CompressString {
    public static void main(String[] args) {
        String s1 = "aabccccaaa";
        System.out.println(s1);
        char[] s2 = compress(s1.toCharArray());
        System.out.println(new String(s2));

        String s3 = "aabccdeeaa";
        System.out.println(s3);
        char[] s4 = compress(s3.toCharArray());
        System.out.println(new String(s4));
    }

    public static char[] compress(char[] s) {
        //如果压缩后比原来还长，则不必压缩
        int size = countCompression(s);
        if (size > s.length) {
            return s;
        }

        //根据压缩后的长度创建数组
        char[] compressed = new char[size];

        int index = 0;
        char last = s[0];
        int count = 1;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == last) {
                count++;
            } else {
                index = appendChar(compressed, last, index, count);

                last = s[i];
                count = 1;
            }
        }

        index = appendChar(compressed, last, index, count);

        return compressed;
    }

    public static int appendChar(char[] array, char c, int index, int count) {
        //首先追加字符
        array[index] = c;
        index++;

        char[] countString = Integer.toString(count).toCharArray();

        //追加字符个数
        for (int i = 0; i < countString.length; i++) {
            array[index] = countString[i];
            index++;
        }

        return index;
    }

    /**
     * 计算压缩后字符串的长度
     *
     * @param str
     * @return
     */
    private static int countCompression(char[] str) {
        if (str == null || str.length == 0) {
            return 0;
        }

        int size = 0;
        int count = 1;
        int last = str[0];

        for (int i = 0; i < str.length; i++) {
            if (str[i] == last) {
                count++;
            } else {
                size += 1 + Integer.toString(count).length();

                last = str[i];
                count = 1;
            }
        }

        size += 1 + Integer.toString(count).length();

        return size;
    }
}
