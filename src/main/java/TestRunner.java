
import myjunit.After;
import myjunit.Before;
import myjunit.Test;


import java.lang.reflect.Constructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void run(Class clazz) {

        int notPassed = 0;
        int passed = 0;

        List<Method> testMethods = new ArrayList<>();
        List<Method> beforeMethods = new ArrayList<>();
        List<Method> afterMethods = new ArrayList<>();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            } else if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            } else if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            }
        }

        for (Method testMethod : testMethods) {
            try {
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                Object object = constructor.newInstance();

                for (Method beforeMethod : beforeMethods) {
                    runMethod(beforeMethod, object);
                }

                runTestMethod(testMethod, object);
                passed++;

                for (Method afterMethod : afterMethods) {
                    runMethod(afterMethod, object);
                }
            } catch (Exception e) {
                notPassed++;
            }
        }
        System.out.println("Number of tests - " + (passed + notPassed) + " : Passed test - " + passed
                + ", Not Passed test - " + notPassed);
    }

    private static void runMethod(Method method, Object object) {
        try {
            method.setAccessible(true);
            method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {

        }
    }

    private static void runTestMethod(Method method, Object object) throws InvocationTargetException, IllegalAccessException {
        method.setAccessible(true);
        method.invoke(object);
    }
}
