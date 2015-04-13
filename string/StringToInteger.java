package string;

/**
 * Created by zk on 2015/4/12.
 * <p>
 * 输入一个由数字组成的字符串，把它转换成整数并输出。例如：输入字符串 "123"，输出整数 123。
 * </p>
 */
public class StringToInteger {
    public static void main(String[] args) {
        System.out.println(StringToInt32("12345"));
        System.out.println(StringToInt32("-12345"));

        System.out.println(StringToInt32("2147483647"));
        System.out.println(StringToInt32("-2147483647"));

        System.out.println(StringToInt32("21474836470"));
        System.out.println(StringToInt32("-21474836470"));
    }

    public static int StringToInt32(String str) {
        if (str.length() > Integer.toString(Integer.MAX_VALUE).length()) {
            throw new IllegalArgumentException("字符串长度超出了整数的存储范围");
        }

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '-') {
                throw new IllegalArgumentException("字符串包含非数字字符，无法转换成整数");
            }
        }

        if (str.length() == 0) {
            return 0;
        }

        char[] s = str.toCharArray();
        int value = 0;
        int i = 0;

        //检查是整数还是负数
        int sign = 1;
        if (s[0] == '+' || s[0] == '-') {
            if (s[0] == '-')
                sign = -1;
            i++;
        }

        while (i < s.length) {
            int c = s[i] - '0';

            if (sign > 0 && (value > Integer.MAX_VALUE / 10 ||
                    (value == Integer.MAX_VALUE / 10 && c >= Integer.MAX_VALUE % 10))) {
                value = Integer.MAX_VALUE;
                break;
            } else if (sign < 0 && (value > -(Integer.MIN_VALUE / 10) ||
                    (value == -(Integer.MIN_VALUE / 10) && c >= -(Integer.MIN_VALUE % 10)))) {
                value = Integer.MIN_VALUE;
                break;
            }

            value = value * 10 + c;

            i++;
        }

        return sign > 0 ? value : value == Integer.MIN_VALUE ? value : -value;
    }
}
