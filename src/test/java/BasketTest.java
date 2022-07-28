
import myjunit.After;
import myjunit.Before;
import myjunit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BasketTest {
    HashMap <String, Integer> basketMap = new HashMap<>();
    Basket basket;

   @Before
    void test(){
      basket = new Basket(basketMap);
    }

  @Test
    void test1(){
       basket.add("apple",3);



    }

   @After
    void  test2(){
        basket.showBasket();
    }





}
