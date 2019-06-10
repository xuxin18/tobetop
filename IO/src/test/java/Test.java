import java.io.UnsupportedEncodingException;

/**
 * @author xuxin
 * @project to_be_top
 * @package PACKAGE_NAME
 * @date 2019/6/10 19:37
 * @description
 */
public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
        char a = '覅';
        System.out.println(a);
        String s = String.valueOf(a);
        System.out.println(s.getBytes("utf-8").length);
        System.out.println(s.getBytes("gbk").length);
        System.out.println(s.getBytes("iso-8859-1").length);
        System.out.println(s.getBytes("utf-16").length);
        System.out.println(s.getBytes("utf-16be").length);
        System.out.println(s.getBytes("utf-16le").length);
        System.out.println(Character.MAX_VALUE);
        System.out.println(Character.MIN_VALUE);
    }
}
