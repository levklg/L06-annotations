import myjunit.After;
import myjunit.Before;
import myjunit.Test;


import java.util.HashMap;


public class BasketTest {
    HashMap <String, Integer> basketMap;
    Basket basket;

   @Before
    void test(){
       basketMap = new HashMap<>();
       basket = new Basket(basketMap);
       System.out.println("TEST");
    }

  @Test
    void test1(){
       basket.add("apple",3);
      System.out.println("TEST_1");
    }

   @After
    void  test2(){
        basket.showBasket();
       System.out.println("TEST_2");
    }





}
