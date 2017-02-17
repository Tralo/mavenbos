package cn.itcast.bos.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)//运行时使用
@Target(value={ElementType.METHOD})// 修饰的方法
@Inherited
public @interface Privilege {
	String value();// 这个属性代表访问业务方法需要权限
}
