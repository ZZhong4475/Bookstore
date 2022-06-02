package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import model.Cart;
import model.Item;
import model.ItemOrder;



public class CartTest {
    /**
     * TestArray for store itemorder and quantiy of order.
     */
    private List<ItemOrder> myTestArray;
    /**
     * item name of football.
     */
    private final String myItemName = "Football";
    /**
     * bulk item name soccer.
     */
    private final String myBulkItemName = "Soccer";
    /**
     * name of item price.
     */
    private final BigDecimal myItemPrice = new BigDecimal("20.00");
    /**
     * name of bulk item price.
     */
    private final BigDecimal myBulkItemPrice = new BigDecimal("25.00");
    /**
     * name of quantity of bulkitem.
     */
    private final int myBulkQuan = 2;
    
    /**
     * name of bulkprice.
     */
    private final BigDecimal myBulkPrice = new BigDecimal("10.00");
    /**
     * name of itemorder store football information.
     */
    private final Item myFootball = new Item(myItemName, myItemPrice);
    /**
     * Name of itemorder store soccer information.
     */
    private final Item mySoccer = new Item(myBulkItemName,
                                         myBulkItemPrice, myBulkQuan, myBulkPrice);
    /**
     * name of total amount of order for each item.
     */
    private final int myOrderQuan = 10;
    /**
     * name of ratio of order quantity and bulkquan for latter calculation.
     */
    private final int myBulkQuanMT = myOrderQuan / myBulkQuan;
    /**
     * name of order quan in BigDecimal.
     */
    private final BigDecimal myOrderQuanBD = new BigDecimal(myOrderQuan);
    /**
     * name of bulk quan ratio.
     */
    private final BigDecimal myBulkQuanMTBD = new BigDecimal(myBulkQuanMT);
    /**
     * name of itemorder that is not in bulk.
     */
    private final ItemOrder myTestItemNB = new ItemOrder(myFootball, myOrderQuan);
    /**
     * name of itemorder that is in bulk.
     */
    private final ItemOrder myTestItemB = new ItemOrder(mySoccer, myOrderQuan);
    /**
     * Membership verfiication.
     */
    private boolean myMembership;
    /**
     * name of cart for testing.
     */
    private Cart myOldCart;
    /**
     * number of formate.
     */
    private  final NumberFormat CURRENCY_FORMAT =
                    NumberFormat.getCurrencyInstance(Locale.US);
  
    
    /**
     * Set up constuctos and intial file for test.
     * @throws Exception if there is execpetion 
     */
    @Before
    public void setUp() throws Exception {
        myTestArray = new ArrayList<ItemOrder>();
        myTestArray.add(myTestItemB);
        myTestArray.add(myTestItemNB);
        myOldCart = new Cart();

    }   
    /**
     * Test the constructor if there are exception happened.
     * it should construct a cart.
     */
    @Test
    public void testCart() { 
        final Cart newCart = new Cart();
       
    }

    /**
     * Apply add method to add item, and see their consistency.
     */
    @Test
    public void testAdd() {
   
        myOldCart.add(myTestItemB);
        myOldCart.add(myTestItemNB);
        
        assertEquals("Two Arrays should be same", myTestArray, myTestArray);
        
       
    }
    /**
     * It should set the memebership to true.
     */
    @Test
    public void testSetMembershipTrue() {
        final boolean test = true;
        myOldCart.setMembership(test);
        
    }
    /**
     * It should set the memebrship to false.
     */
    @Test 
    public void testSetMembershipFalse() {
        final boolean test = false;
        myOldCart.setMembership(test);
    }
    /**
     * It perform an outer calculation then perform the 
     * caltotal method, the results should be same.
     */
    @Test
    public void testCalculateTotalMemT() {
       
        final BigDecimal soccRes = myItemPrice.multiply(myOrderQuanBD);
        final BigDecimal footRes = myBulkPrice.multiply(myBulkQuanMTBD);
        BigDecimal totalRes = BigDecimal.ZERO;
        totalRes = totalRes.add(footRes.add(soccRes));
        myOldCart.add(myTestItemB);
        myOldCart.add(myTestItemNB);
        BigDecimal total = BigDecimal.ZERO;
        myOldCart.setMembership(true);
        total = total.add(myOldCart.calculateTotal());
        
        
        assertEquals("The total should be true", totalRes, total);
                
    }
    /**
     * It perform an outer calculation then perform the
     * caltotal method, the results should be same.
     */
    @Test
    public void testCalculateTotalMemF() {
        final BigDecimal soccRes = myItemPrice.multiply(myOrderQuanBD);
        final BigDecimal footRes = myBulkItemPrice.multiply(myOrderQuanBD);
        BigDecimal totalRes = BigDecimal.ZERO;
        totalRes = totalRes.add(footRes.add(soccRes));
        myOldCart.add(myTestItemB);
        myOldCart.add(myTestItemNB);
        BigDecimal total = BigDecimal.ZERO;
        myOldCart.setMembership(false);
        total = total.add(myOldCart.calculateTotal());
       
        
        assertEquals("The result should be same", totalRes, total);
    }

    /**
     * It test the clear method, the size should return zero at the end.
     */
    @Test
    public void testClear() {
        final int size = 0;
        myOldCart.clear();
        assertEquals("Size should be the same", size, myOldCart.getCartSize());
        
    }

    /**
     * It should get the cartsize when the item pass into the arraylist
     * the assertion should be same.
     */
    @Test
    public void testGetCartSize() {
        final int size1 = 1;
        final int size2 = 2;
        myOldCart.add(myTestItemB);
        assertEquals(size1, myOldCart.getCartSize());
        myOldCart.add(myTestItemNB);
        assertEquals("The size should be same", size2, myOldCart.getCartSize());
        
    }

    @Test
    public void testToString() {
        myOldCart.add(myTestItemNB);
        final String expectedString = myItemName + ", " + CURRENCY_FORMAT.format(myItemPrice)
                    + "," + myOrderQuan + "  "
                    + myMembership + " CurrentCarSize: "
                    + myOldCart.getCartSize();;
   
        assertEquals("The String format should be same", myOldCart.toString(), expectedString);
        
    }

}
