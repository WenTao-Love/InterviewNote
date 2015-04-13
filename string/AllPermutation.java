package string;

/**
 * Created by zk on 2015/4/12.
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p/>
 * 例如：输入字符串 "abc"，则输出由字符 'a', 'b', 'c'
 * 所能排列出来的所有字符串：abc, acb, bac, bca, cab, cba。
 */
public class AllPermutation {
    public static void main(String[] args) {
        //要求首次输入有序，否则需要排序
        calculateAllPermutations("abc".toCharArray());
    }

    public static void calculateAllPermutations(char[] s) {
        System.out.println(new String(s));

        int i, j;

        // 找到排列中最右一个升序的首位位置 i
        for (i = s.length - 2; (i >= 0) && (s[i] >= s[i + 1]); --i) ;

        //已经找到所有的排列
        if (i < 0)
            return;

        // 找到排列中第 i 位右边最后一个比 s[i] 大的位置 j
        for (j = s.length - 1; (j > i) && (s[j] <= s[i]); --j) ;

        //交换s[i],s[j]
        swap(s, i, j);

        // 将第 i + 1 位到最后的部分反转
        reverse(s, i + 1, s.length - 1);

        calculateAllPermutations(s);
    }

    //交换数组中两个位置的元素
    private static void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    //反转数组
    private static void reverse(char[] s, int start, int end) {
        char temp;
        int i = start;
        int j = end;

        while (i < j) {
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
