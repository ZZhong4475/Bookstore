/*Project name:zhengz42 for assignment 01 -BookStore
 * TCSS 305 Win20
 * 
 */

//Class stores ordered item and order quantity.



package model;

import java.util.Objects;

/**
 * @author Zheng Zhong
 * @version Jananury 24 2020
 */
//Construct an empty ItemOrder.
public final class ItemOrder {
    /**
     * Name number of ordered number.
     */
    private int myOrderedQuan;
    /**
     * Object item stores item information.
     */
    private Item myOrderedItem;

    /** Contructs ItemOrder for given ordered item and ordered number.
     * @param theItem item object of ordered item
     * @param theQuantity number of ordered item
     * @throws illegalArugumetException if theitem is null and quantity is an negative number
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        if (Objects.isNull(theItem) || theQuantity < 0) {
            throw new IllegalArgumentException("Input aruguments. ItemOrder(Item,int)");
        } else {
            myOrderedItem = theItem;
            myOrderedQuan = theQuantity;
        }
    }


    /**return the ordered item.
     * @return the oreded item.
     */
    public Item getItem() {
        
        return myOrderedItem;
    }
    

    /**return the quantity of order.
     * @return the ordered quantity
     */
    public int getQuantity() {
        return myOrderedQuan;
    }
// Overriden Object methods for ItemOrder
    /**
     *Overide toString method return the item and the quantiy of order.
     */
    @Override
    public String toString() {
        
        final StringBuilder sb = new StringBuilder();
        sb.append(myOrderedItem.toString());
        sb.append(",");
        sb.append(myOrderedQuan);
        return sb.toString();
    }

}
