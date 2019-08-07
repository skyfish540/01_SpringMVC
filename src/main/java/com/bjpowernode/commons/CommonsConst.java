package com.bjpowernode.commons;

/**
 * 一个常量类，这里我们需要定义一些项目中所需要的静态常量
 * 这个类是一个final表示它不可以被继承
 * 而且这个类不应该 被实例化因此需要私有化构造方法
 */
public final class CommonsConst {
    private CommonsConst(){

    }
    //JSON返回码中的成功编码
    public static final String OK="0";
    //JSON返回码中的错误编码
    public static final String ERROR="1";
}
