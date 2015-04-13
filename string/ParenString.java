package string;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by zk on 2015/4/13.
 * 输出n对括号的有效组合
 */
public class ParenString {
    public static void main(String[] args) {
        List<String> parenList = generateParens(3);

        for (String s : parenList) {
            System.out.println(s);
        }
    }

    public static List<String> generateParens(int count) {
        char[] str = new char[count * 2];
        List<String> list = new ArrayList<>();
        addParen(list, count, count, str, 0);
        return list;
    }

    public static void addParen(List<String> list,
                                int leftRem,
                                int rightRem,
                                char[] str,
                                int count) {
        //无效状态
        if (leftRem < 0 || rightRem < leftRem) {
            return;
        }

        if (leftRem == 0 && rightRem == 0) {
            String s = new String(str);
            list.add(s);
        } else {
            //还有左括号可用
            if (leftRem > 0) {
                str[count] = '(';
                addParen(list, leftRem - 1, rightRem, str, count + 1);
            }
            //还有右括号可用
            if (rightRem > leftRem) {
                str[count] = ')';
                addParen(list, leftRem, rightRem - 1, str, count + 1);
            }
        }
    }
}
