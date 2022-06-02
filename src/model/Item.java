/*Project name:zhengz42 for assignment 01 -BookStore
 * TCSS 305 
 * The class for storing product's information
 * 
 */
package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * @author Zheng Zhong
 * @version Janunary 24 2020
 */

//Construct an empty item
public final class Item {
    /**
     * Number Formate for turning bigdecimal to string.
     */
    private final NumberFormat myCurrentFormat =
                    NumberFormat.getCurrencyInstance(Locale.US);
    /**
     * A name of the item.
     */
    private String myItemName;    
    /**
     * A name of the price of a item.
     */
    private final BigDecimal myItemPrice; 
    /**
     * A name of the amount of item sale in bulk.
     */
    private int myItemBulkQuan;
    /**
     * A name of the price of item sale in bulk.
     */
    private BigDecimal myItemBulkPrice;

    /**
     * Constructs an item with name and price.
     * @param theName the name assigns to this item.
     * @param thePrice the price assigns to this item.
     * @throws IllegalArugmentExcpetion if the name is empty and the price is a negative value.
     * @throws NullPointerException if input argument is null. 
     */
    public Item(final String theName, final BigDecimal thePrice) {
        if (Objects.requireNonNull(theName).length() == 0
                        || Objects.requireNonNull(thePrice).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Illegal Aruguments:Item(String,BigDecimal)");
        }
        myItemName = theName;
        myItemPrice = thePrice;
    }
    /**
     * Constructs an item with name, price, quantity of bulk ,and price of item sale in bulk.
     * @param theName the name assigns to this item.
     * @param thePrice the price assigns to this item.
     * @param theBulkQuantity the bulk quantity for item sale in bulk.
     * @param theBulkPrice  the price for item sale in bulk.
     * @throws IllegalArugmentExcpetion if bulkquanity or bulkprice is a negative value.
     * @throws NullPointerException if input arguments are null. 

     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        this(theName, thePrice);

        if (theBulkQuantity < 0
                      || Objects.requireNonNull(theBulkPrice).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException
            ("Illegal Arugments:"
                            + "Item(String,BigDecimal,int,BigDecimal),"
                            + "bulkquantity and bulkprice should greater than 0");
        }
        myItemBulkQuan = theBulkQuantity;
        myItemBulkPrice = theBulkPrice;
    }

    /**Return the price of the item.
     * @return the price of item
     */
    public BigDecimal getPrice() {
        return myItemPrice;
    }
    /**Return the bulk quantity sale in bulk.
     * @return the quantity of item sale in bulk.
     */
    public int getBulkQuantity() {
        return myItemBulkQuan;
    }
    /**
     * @return the price of item sale in bulk
     */
    public BigDecimal getBulkPrice() {
        return myItemBulkPrice;
    }
    /**
     * @return determine the item sale in bulk or not.
     * true is sale in bulk,otherwise false.
     */
    public boolean isBulk() {
        boolean isBulkCheck = false;

        if (myItemBulkQuan > 0 
                        && myItemBulkPrice.compareTo(BigDecimal.ZERO) >= 0) {
            isBulkCheck = true; 
        }

        return isBulkCheck;
    }
    // methods overriden from java.lang.Object
    /** Override the toString method, it returns a string contains item name and price.
     *  If item sale in bulk, then return contains the bulk quantity and bulk price.
     *
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(100);
        if (this.isBulk()) {
            sb.append(myItemName);
            sb.append(",");
            sb.append(myCurrentFormat.format(myItemPrice));
            sb.append(" ( " + myItemBulkQuan + " for "  
                            + myCurrentFormat.format(myItemBulkPrice) + " ) "); 
        } else {
            sb.append(myItemName);
            sb.append(", ");
            sb.append(myCurrentFormat.format(myItemPrice));
        }
        return sb.toString(); 
    }

    /** Override the equal method.It verify the similairy of objects.
     * If comparing objects same as item type,it return true.,otherwise false.
     * @Throws NullPointerException if there the Object is null.
     *
     */
    @Override
    public boolean equals(final Object theOther) {
        
        boolean eq = false;

        if ((theOther != null) && (theOther.getClass().equals(this.getClass()))) {
            final Item otherItem = (Item) theOther;

            if (this.isBulk()) {
                if (myItemName.equals(otherItem.myItemName)
                                && Objects.equals(myItemPrice, otherItem.myItemPrice)
                                && myItemBulkQuan == otherItem.myItemBulkQuan
                                && myItemBulkPrice.compareTo(otherItem.myItemBulkPrice)==0)
                 {
                    eq = true; 
                }
            } else {
                if (myItemName.equals(otherItem.myItemName) 
                                && (Objects.equals(myItemPrice, otherItem.myItemPrice))) {
                    eq = true;
                }

            }
        } else {
            throw new IllegalArgumentException();
        }
        return eq;

    }
    /**
     *Override the hashcode method, return hashcode for item's parameters.
     */
    @Override
    
    public int hashCode() {
        return Objects.hash(myItemName, myItemPrice , myItemBulkQuan , myItemBulkPrice);
    }

}
