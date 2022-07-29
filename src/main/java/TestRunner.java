
import myjunit.After;
import myjunit.Before;
import myjunit.Test;


import java.lang.reflect.Constructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {

    public static void run(Class clazz) {

        int tests = 0;
        int passed = 0;


       Method[] methods = clazz.getDeclaredMethods();


        try {


            for(Method m : methods){


                if(m.isAnnotationPresent(Test.class)){
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    Object object = constructor.newInstance();
                    for(Method mt : methods){
                        if(mt.isAnnotationPresent(Before.class)){
                            TestRunner.runMeyhod(mt,object);}

                    }
                    runMeyhod(m,object );

                    for(Method mt : methods){
                        if(mt.isAnnotationPresent(After.class)) TestRunner.runMeyhod(mt,object);

                    }

                }



            }


        } catch (IllegalAccessException e) {
            e.printStackTrace();


        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }

    private static   void runMeyhod(Method method, Object object){
        if(object != null){
            method.setAccessible(true);
            try {
                method.invoke(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}