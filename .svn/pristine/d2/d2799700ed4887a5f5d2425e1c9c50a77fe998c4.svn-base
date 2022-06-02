package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import model.Item;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class ItemTest {
   
    private static final NumberFormat CURRENCY_FORMAT =
                    NumberFormat.getCurrencyInstance(Locale.US);
    private static final String expected = null;
    private Item myItemBulk;
    private Item myItemNoBulk;
    private String myTestItem="Book";
    private final BigDecimal myTestItemPrice=new BigDecimal("399");
    private int myTestItemBulkQuan=4;
    private final BigDecimal myTestItemBulkPrice=new BigDecimal("100");
    private final BigDecimal myNegativeValue = BigDecimal.valueOf(-9.99);
    
  
    

    @Before
    public void setUp() throws Exception {
        myItemNoBulk=new Item(myTestItem,myTestItemPrice);
        myItemBulk=new Item(myTestItem,myTestItemPrice,myTestItemBulkQuan,myTestItemBulkPrice);
        
    }

    @Test
    public void testHashCode() {
        assertEquals("HashCode Compare should reusult the same hashcode",myItemNoBulk.hashCode(),myItemNoBulk.hashCode());
    }
    @Test
    public void testHashCodeDFI() {
       
            final Item testItemNoBulk1=new Item(myTestItem,myTestItemPrice);
            assertEquals("HashCode Compare with different state should reusult the same hashcode",myItemNoBulk.hashCode(),testItemNoBulk1.hashCode());
        }

        
    @Test(expected = NullPointerException.class)
    public void testItemNameNPE() {
        new Item(null,myTestItemPrice);
        
    }
    

    @Test(expected = NullPointerException.class)
    public void testItemPriceNPE() {
        new Item(myTestItem,null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testItemIAE() {
        new Item(myTestItem,myNegativeValue);
    }
    @Test(expected = IllegalArgumentException.class)
     public void testItemEmpty() {
        
        new Item("",myTestItemPrice);
       
        
        new Item(myTestItem,myNegativeValue);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBulkItemQuanIAE() {
        final int bulkQuan=-1;
        new Item(myTestItem,myTestItemPrice,bulkQuan,myTestItemBulkPrice);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBulkItemPriceIAE() {
        new Item(myTestItem,myTestItemPrice,myTestItemBulkQuan,myNegativeValue);
    }
    
    @Test
    public void testGetPrice() {
        assertEquals("It should return the same price",myTestItemPrice,myItemNoBulk.getPrice());
    }

    @Test
    public void testGetBulkQuantity() {
        assertEquals("It should return the same quantity",myTestItemBulkQuan,myItemBulk.getBulkQuantity());
    }

    @Test
    public void testGetBulkPrice() {
        assertEquals("It should return the same bulk price",myTestItemBulkPrice,myItemBulk.getBulkPrice());
    }

    @Test
    public void testIsBulkFalse() {
        assertFalse("It should return false for the non bulk item",myItemNoBulk.isBulk());
        assertTrue("It should return true for the bulk item",myItemBulk.isBulk());
    }

    @Test
    public void testIsBulk() {
        Item testItem=new Item(myTestItem,myTestItemPrice,myTestItemBulkQuan,BigDecimal.ZERO);
        assertTrue("Asserts if bulkprice is 0, it should return true",testItem.isBulk());
       
    }

    @Test
    public void testToString() {
         String expectedString =new String(myTestItem+", "+CURRENCY_FORMAT.format(myTestItemPrice));
         String expectedStringBulk =new String(myTestItem+","
         +CURRENCY_FORMAT.format(myTestItemPrice)
         +" ( "+myTestItemBulkQuan
         +" for "
         + CURRENCY_FORMAT.format(myTestItemBulkPrice)
         +" ) ");
         assertEquals("Asserts format for non bulk item String format ",myItemNoBulk.toString(),expectedString);
         assertEquals("Asserts format for bulk item String format",myItemBulk.toString(),expectedStringBulk);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEqualsObjectIAE() {
        final Object theOther="";
        assertFalse("Null input will cause IAE",myItemNoBulk.equals(theOther));
        
    }
    @Test
    public void testEqualsObject() {
        Item Comparableobject=new Item(myTestItem,myTestItemPrice);
        Item ComparableobjectB=new Item(myTestItem,myTestItemPrice,myTestItemBulkQuan,myTestItemBulkPrice);
        Item CompaPriceChange=new Item(myTestItem,new BigDecimal("30"));
        assertTrue("Asserts same object for non bulk",myItemNoBulk.equals(myItemNoBulk));
        assertTrue("Asserts same object for bulk",myItemBulk.equals(myItemBulk));
        assertTrue("Asserts same object with different state for non bulk",myItemNoBulk.equals(Comparableobject));
        assertTrue("Asserts same object with different state for bulk",myItemBulk.equals(ComparableobjectB));
    
        assertFalse("Asserts same object for non bulk",myItemNoBulk.equals(CompaPriceChange));
    }
    

}
