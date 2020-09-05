package jdk18;

/**
 * 1.如果一个父接口提供一个默认方法，而另一个接 口也提供了一个具有相同名称和参数列表的方法（不管方法 是否是默认方法），那么必须覆盖该方法来解决冲突
 *
 * 2.接口默认方法的”类优先”原则
 * 若一个接口中定义了一个默认方法，而另外一个父类或接口中 又定义了一个同名的方法时
 * 选择父类中的方法。如果一个父类提供了具体的实现，那么 接口中具有相同名称和参数的默认方法会被忽略。
 */
public class InterfaceDefault {
}
