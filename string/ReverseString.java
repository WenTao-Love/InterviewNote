package string;

/**
 * Created by zk on 2015/4/12.
 * 反转字符串
 */
public class ReverseString {
    public static void main(String[] args) {
        String text1 = "ABC1DEF";
        String text2 = "ABC1DEFG";
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();

        reversePartOfString(t1, 1, t1.length - 2);
        reversePartOfString(t2, 1, t2.length - 2);

        String s1 = new String(t1);
        String s2 = new String(t2);

        System.out.println(s1);
        System.out.println(s2);

        String text3 = "ABC1DEF";
        String text4 = "ABC1DEFG";

        char[] t3 = text3.toCharArray();
        char[] t4 = text4.toCharArray();

        reverseArrayByXor(t3);
        reverseArrayByXor(t4);

        String s3 = new String(t3);
        String s4 = new String(t4);

        System.out.println(s3);
        System.out.println(s4);
    }

    public static void reversePartOfString(char[] str, int begin, int length) {
        char temp;

        for (int i = begin, j = begin + length - 1; i < j; i++, j--) {
            temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
    }

    public static void reversePartOfStringWithWhile(char[] str, int begin, int length) {
        char temp;
        int i = begin;
        int j = begin + length - 1;

        while (i < j) {
            temp = str[i];
            str[i] = str[j];
            str[j] = temp;
            i++;
            j--;
        }
    }

    public static void reverseArray(char[] str) {
        char temp;

        for (int i = 0, j = str.length - 1; i < j; i++, j--) {
            temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
    }

    public static void reverseArrayByXor(char[] str) {
        for (int i = 0, j = str.length - 1; i < j; i++, j--) {
            str[i] ^= str[j];
            str[j] ^= str[i];
            str[i] ^= str[j];
        }
    }
}
