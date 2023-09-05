package com.xiaoma.service.impl;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListTest {
    /**
     * removeIf:根据条件删除集合中的元素
     * retainAll:可以判断两个集合是否有交集  {1,2,3} {6,4,5}
     */
    @Test
    public void test01() {
        List<String> stringList = Stream.of("10", "11", "21", "22", "23", "34", "36").collect(Collectors.toList());
        //删除集合中包含"1"的元素
        stringList.removeIf(str -> str.contains("1"));

        System.out.println(stringList);
    }

    /**
     * list01.retainAll(list02)
     * 1.如果list01和list02两者有交集,list01中只有交集元素,list02不变
     * 2.如果list01和list02两者没有交集,list01清空所有元素,list02不变
     */
    @Test
    public void test02() {
        List<String> list01 = Stream.of("10", "11", "12", "17").collect(Collectors.toList());
        List<String> list02 = Stream.of("12", "13", "14", "15").collect(Collectors.toList());
        list01.retainAll(list02);

        System.out.println(list01);
        System.out.println(list02);


        if (list01.size() > 0) {
            System.out.println("有交集");
        } else {
            System.out.println("没有交集");
        }
    }
}
