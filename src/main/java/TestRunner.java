
import myjunit.After;
import myjunit.Before;
import myjunit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {

    public static void run(Class clazz) {
        int tests = 0;
        int passed = 0;


       Method[] methods = clazz.getDeclaredMethods();


        try {

            Object object = clazz.newInstance();
            for(Method m : methods){

                if(m.isAnnotationPresent(Before.class)){

                    m.invoke(object);


                    for(Method mt : methods) {
                        if(mt.isAnnotationPresent(Test.class)){
                            mt.setAccessible(true);
                            mt.invoke(object);
                        }
                    }


                }



                if(m.isAnnotationPresent(After.class)){
                    m.setAccessible(true);
                    m.invoke(object);


                }

            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}