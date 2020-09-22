package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author laijinhan
 * @date 2020/9/22 11:29 下午
 */


public class reflection {
    public static void main(String[] args) throws Exception {
        Student student=new Student(12,"王五");

        Class object=student.getClass();
//        Class object = Class.forName("reflection.Student");
        Field[] fields=object.getDeclaredFields();

        System.out.println("获取所有字段的名字");
        for (Field field : fields) {
            field.setAccessible(true); //   设置私有变量的访问权限
            System.out.println(field.getName()+"字段值:"+field.get(student));
        }
        System.out.println("根据name 获取字段");
        Field filed = object.getDeclaredField("id");
        filed.setAccessible(true);
        filed.set(student,123);
        System.out.println(filed.get(student));

        System.out.println("获取有参构造器，并实例化对象");
        Constructor constructor = object.getConstructor(Integer.class, String.class);
        Object student1 = constructor.newInstance(1, "和");
        System.out.println(student1.toString());

        System.out.println("获取所有方法");
        Method[] methods = object.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println("根据方法名获取方法");
        Method toString = object.getMethod("toString");
        System.out.println(toString.getName());
    }
}
