package string;

/**
 * Created by zk on 2015/4/12.
 * 判断字符串中是否包含重复字符，假设全为ASCII字符
 */
public class UniqueCharacter {
    public static void main(String[] args) {
        System.out.println(isUniqueCharacter("apple".toCharArray()));
        System.out.println(isUniqueCharacter("abc%^".toCharArray()));
        System.out.println(isUniqueSmallAlphabetChars("defghjkl".toCharArray()));
        System.out.println(isUniqueSmallAlphabetChars("#$killer".toCharArray()));
    }

    public static boolean isUniqueCharacter(char[] str) {
        if (str.length > 256)
            return false;

        //为每次字符，保留一个标记
        boolean[] charSet = new boolean[256];

        for (int i = 0; i < str.length; i++) {
            byte index = (byte) str[i];

            if (charSet[index]) {
                return false;
            }

            charSet[index] = true;
        }

        return true;
    }

    public static boolean isUniqueSmallAlphabetChars(char[] str) {
        if (str.length > 26) {
            return false;
        }

        // 使用位操作以改进空间占用
        int checker = 0;
        for (int i = 0; i < str.length; i++) {
            int index = str[i] - 'a';
            if (index < 0 || index > 26 || (checker & (1 << index)) > 0) {
                return false;
            }
            checker |= (1 << index);
        }

        return true;
    }
}
