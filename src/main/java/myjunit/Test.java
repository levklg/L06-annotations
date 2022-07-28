package myjunit;



import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface Test {
}


