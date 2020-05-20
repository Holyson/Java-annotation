# 注解 Annotation
<center>
<img src="https://img-blog.csdnimg.cn/20200520105146436.png" width="80%" alt=""/>
</center>


# 1、什么是注解？
从写法看，注解和接口（interface很像）

 1. Annotation 是从 JDK5.0 开始引入的新技术
 2. Annotation的作用：  
		不是程序本身，可以对程序作出解释（这一点和注解 comment 没什么区别）
		<font color=red>可以被其他程序（比如：编译器等 ）读取</font>

 3. Annotation的格式：
	注解是以“@注释名”在代码中存在的，还可以添加一些参数值，例如-抑制警告：@SuppressWarnings(value="unchecked")
	

 4. Annotation 在哪里使用？
 	可以附加在package、class、method、field 等上面，相当于给他们添加了额外的辅助信息，<font color=red>我们可以通过反射机制变成实现对这些元数据的访问</font>


# 2、内置注解


### @Override 
@Override（重写）：定义在 java.lang.Override中

表示方法声明旨在`覆盖`超类型中的方法声明。 如果使用此注释类型注释方法，则除非至少满足以下条件之一，否则需要编译器生成错误消息： 

 - 该方法将覆盖或实现在父（超）类中声明的方法
 - 该方法具有与Object中声明的任何公共方法的覆盖相同的签名 

```java
 /
 * @author  Peter von der Ah&eacute;
 * @author  Joshua Bloch
 * @jls 9.6.1.4 @Override
 * @since 1.5
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}

```

<center>
<img src="https://img-blog.csdnimg.cn/20200520102301439.png" width="80%" alt=""/>
</center>

### @Deprecated  
@Deprecated （已过时的）：定义在  java.lang.Deprecated 中

 - 注释@Deprecated的程序元素是程序员不鼓励使用的程序元素，通常是因为它是危险的，或者因为存在更好的替代方法

 - 编译器在不被弃用的代码中使用或覆盖不推荐使用的程序元素时发出警告。

```java
/
 * @author  Neal Gafter
 * @since 1.5
 * @jls 9.6.3.6 @Deprecated
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface Deprecated {
}
```

<center>
<img src="https://img-blog.csdnimg.cn/20200520103231378.png" width="80%" alt=""/>
</center>

### @SuppressWarning
@SuppressWarning（抑制警告）：定义在  java.lang.SuppressWarning中

 - 表示在注释元素（以及注释元素中包含的所有程序元素）中应该抑制命名的编译器警告。 请注意，给定元素中抑制的一组警告是所有包含元素中抑制的警告的超集。 例如，如果您注释一个类来抑制一个警告并注释方法来抑制另一个警告，则两个警告将在该方法中被抑制。 
 - 作为一种风格，程序员应该始终将这个注释用于最有效的嵌套元素。 如果要在特定方法中抑制警告，则应该注释该方法而不是其类

| 不同参数的用法 | 
|:--|
| @SuppressWarning("all") 【常用】 | 
|@SuppressWarning("unchecked")|
|@SuppressWarning(value={"unchecked","deprecation"})|

```java
/
 * @author Josh Bloch
 * @since 1.5
 * @jls 4.8 Raw Types
 * @jls 4.12.2 Variables of Reference Type
 * @jls 5.1.9 Unchecked Conversion
 * @jls 5.5.2 Checked Casts and Unchecked Casts
 * @jls 9.6.3.5 @SuppressWarnings
 */
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interface SuppressWarnings {
    String[] value();
}
```

<center>
<img src="https://img-blog.csdnimg.cn/20200520104226461.png" width="80%" alt=""/>
</center>

# 3、元注解 （4个 meta-annotation）
>什么是元注解？
`元注解的作用就是负责注解其他注解`

Java定义了4个标准的meta-annotation类型，他们被用来提供对其他annotation类型作说明
这些类型和他们所支持的类在java.lang.annotation 包中可以找到
### @Target
Target：用于描述注解的使用范围（即：被描述的注解可以用在设么地方）

target的返回值 ElementType 的所有参数 最为常用的为：TYPE <font color=red>【定义在类上的注解】</font>
<center>
<img src="https://img-blog.csdnimg.cn/20200520113501542.png" width="80%" alt=""/>
</center>
@Target 定义在方法上的注解，放在错误位置，会报错
<center>
<img src="https://img-blog.csdnimg.cn/2020052011380321.png" width="80%" alt=""/>
</center>

### @Retention 
Retention：表示需要在什么级别保存该注释信息，用于描述注解的生命周期
 <font color=red>**RUNTIME**</font> （常用、顶级）> CLASS  >SOURCE
| 参数 |按生命周期来划分可分为3类  |
|:--|:--|
| RetentionPolicy.SOURCE |注解只保留在 <font color=red>源文件</font>，当Java文件编译成class文件的时候，注解被遗弃  |
| RetentionPolicy.CLASS| 注解被保留到<font color=red>class文件</font>，但jvm加载class文件时候被遗弃，这是默认的生命周期 |
| RetentionPolicy.RUNTIME| 注解不仅被保存到class文件中，<font color=red>jvm加载class文件之后，仍然存在</font> |

### @Document
Document：说明该注解将被包含在 javadoc 中

### @Inherited
Inherited：说明子类可以**继承**父类中的该注解

#### 元注解的简单实用

```java
package com.ctra.annotation;
import sun.awt.SunHints;

import java.lang.annotation.*;
// 测试   元注解
@MyFirstAnnotation
public class MetaAnnotation {
    public void test(){
    }
}


//这里由于需要定义多个元注解，就省略 public 都放在一个类下
// ===================================================

// Target 表示我们的注解可以用在哪些方法
//@Target( value = ElementType.METHOD) //作用域：方法
@Target( value ={ElementType.METHOD,ElementType.TYPE})

// @Retention  表示我们的注解在什么地方还有效
@Retention(value = RetentionPolicy.RUNTIME) // RUNTIME 顶级

// Inherited 子类可以继承父类的注解
@Inherited 
@interface MyFirstAnnotation{

}
```

# 4、自定义注解
使用@interface 自定义注解时，自动继承了 java.lang.annotation.Annotation 接口

### 1. 定义一个注解
> @interface 用来声明一个注解
> 格式：public @interface 注解名称 {定义内容}

```java
public @interface MyAnnotation{
}
```
### 2.  注解的参数
>格式：注解的参数： 参数类型 + 参数名()+; 

```java
@interface MyAnnotation{
    String name() ;
}
```

<center>
<img src="https://img-blog.csdnimg.cn/20200520122240860.png" width="80%" alt=""/>
</center>

### 3. 注解参数的默认值
>可以通过default 来声明参数的默认值

```java
@interface MyAnnotation{
    String name() default "";
}
```
<center>
<img src="https://img-blog.csdnimg.cn/20200520140325204.png" width="80%" alt=""/>
</center>
### 4. 注解参数为value
☆ 自定义注解，如果只有一个参数，且将这个参数定义为 `value` 则不需要在使用时声明参数
 
```java
public class test04 {
    @MyAnnotation1(1)
    public  void test(){}
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation1{
    int value();
}
```
如果没有将参数定义为`value`，调用时不声明参数则报错
<center>
<img src="https://img-blog.csdnimg.cn/20200512111733168.png" width="80%" alt=""/>
</center>

注解元素必须要有值，我们定义注解元素时，经常使用空字符串，0作为默认值

### 5.  注解参数的返回值
>返回值类型就是参数的类型（返回值只能是基本类型、class、string、enum）

<center>
<img src="https://img-blog.csdnimg.cn/20200520135955304.png" width="80%" alt=""/>
</center>

# 5、自定义注解练习代码
为了方便练习，这里创建注解采用的是内部类的方式（省略了 public 的@interface ）

```java
package com.ctra.annotation;

import javax.xml.bind.annotation.XmlType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 自定义注解
public class test03 {
    // 注解可以显示赋值，如果没有默认值，我们就必须给注解赋值
    @MyAnnotation(age=12)
    public void test(){}

    @MyAnnotation2(1)
    public void test2(){}
}

@Target({ElementType.TYPE,ElementType.METHOD}) //作用域
@Retention(RetentionPolicy.RUNTIME) //作用范围
@interface MyAnnotation{
//    注解的参数： 参数类型 + 参数名();
    String name() default "";
    int age()  ;
    int id() default -1;
    String[] schools() default {"a","b"};
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2{
    int value();
}
```
