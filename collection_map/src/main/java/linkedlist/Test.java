package linkedlist;

import java.util.LinkedList;

/**
 * @author xuxin
 * @project to_be_top
 * @package linkedlist
 * @date 2019/6/11 17:02
 * @description
 */
public class Test {
    public static void main(String[] args) {
        LinkedList a = new LinkedList();
        a.add("新");
        a.add("中");
        a.add("国");
        a.add(null);
        a.add(null);
        LinkedList b = new LinkedList(a);
    }
}
