package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import model.Item;
import model.ItemOrder;

public class ItemOrderTest {
    /**
     * Name of testing item quantity.
     */
    private int myTestQuan;
    /**
     * Name of testing Bulk item.
     */
    private Item myBulkItem;
    
    /**
     * Name of testing Non Bulk item.
     */
    private Item myNonBulkItem;
   
    /**
     * ItemOrder stores for itemOrder that in bulk.
     */
    private ItemOrder myItemOrderB;
    
    /**
     * ItemOrder Stores itemOrder that not in bulk.
     */
    private ItemOrder myItemOrderNB;
    /**
     * Construct and intial fields for test.
     * @throws Exception that setup has an excpetion
     */
    @Before
    public void setUp() throws Exception {
        myBulkItem = new Item("BasketBall", 
                              new BigDecimal("20.00") , 5, new BigDecimal("50.00"));
        myNonBulkItem = new Item("Ruler", new BigDecimal("1.00"));
        myTestQuan = 2;
        myItemOrderB = new ItemOrder(myBulkItem, myTestQuan);
        myItemOrderNB = new ItemOrder(myNonBulkItem, myTestQuan);
        
        
    }

    /**Test if the itemOrder take null item.
     * Should throw the IllegalArugment Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testItemOrderNullItem() {
        
        new ItemOrder(null, 2); 
        
    }

    /**Test if the itemOrder take negative quannumber.
     * It should throw the IllegalArugment Exception
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testItemOrderNegativeQuan() {
        new ItemOrder(myBulkItem, -2);
        
    }
    /**
     * Construct new Item Order , assert it to the exited itermOrder 
     * to see the constructor work functionlly.
     * 
     */
    @Test
    public void testItemOrderConstructor() {
        final ItemOrder testOrder = new ItemOrder(myNonBulkItem, 2);
        assertEquals("They should equal,but it wasn't ", myItemOrderNB, testOrder); 
        //Bug spot at equal method in item class need to fix
    }
    /**
     *Get Item class from the Item order,tehy should be the same object.
     */
    @Test
    public void testGetItem() {
        assertEquals("Return Item should be same", myBulkItem, myItemOrderB.getItem());
        assertEquals("Return Item should be same", myNonBulkItem, myItemOrderNB.getItem());
        
    }

    /**
     * Test get quantity method, it should return the same value as the myTestQuan.
     */
    @Test
    public void testGetQuantity() {
        assertEquals("Quantity should be same", myTestQuan, myItemOrderB.getQuantity());
        assertEquals("Quantity should be same", myTestQuan, myItemOrderNB.getQuantity());
    }

    /**
     * Test toString comparing the string format from the item class and the expected format.
     * it should return ture.
     */
    @Test
    public void testToString() {
        final String expectedString = myBulkItem.toString() + "," + myTestQuan;
        final String expectedStringNB = myNonBulkItem.toString() + "," + myTestQuan;
        assertEquals("The Strings should be same", expectedString, myItemOrderB.toString());
        assertEquals("The Strings should be same", expectedStringNB, myItemOrderNB.toString());
        
    }

}
