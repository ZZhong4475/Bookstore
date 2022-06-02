/*Project name:zhengz42 for assignment 01 -BookStore
 * TCSS 305 Win20
 */
//Cart Class handles orders and calculates overall cost

package model;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Zheng Zhoong
 * @version January 24 2020
 *
 */

public class Cart {
    /**
     * Arraylist theCartArray stores all item information.
     * 
     * 
     */
    private final List<ItemOrder> myTheCartArray;
    
    //private final ArrayList<ItemOrder> myTheCartArray;
    /**
     * Verify membership qualification.
     */
    private boolean myMembershipState;

    public Cart() {
       
        
        myTheCartArray = new ArrayList<ItemOrder>();
    }

    /**Add orders to the cart.
     * Users changed quantity will perform replacement.
     * @param theOrder the ItemOrder added to the cart.
     * @throws nullPointerException if the ItemOrder is null.
     */
    public void add(final ItemOrder theOrder) {
        
        boolean checkPoint = false;

        for (int i = 0; i < myTheCartArray.size(); i++) {               
            if (myTheCartArray.get(i).getItem().equals(theOrder.getItem())) {
                checkPoint = true;
                myTheCartArray.set(i, theOrder); 
            }
        }

        if (!checkPoint) {
            myTheCartArray.add(theOrder);
        }   
    }


    /**Set membership state.
     * @param theMembership membership verification
     */
    public void setMembership(final boolean theMembership) {

        myMembershipState = theMembership;
    }



    /**Calculate overall cost.
     * @return total cost of the purchases
     */
    public BigDecimal calculateTotal() {
        BigDecimal toTalcost = BigDecimal.ZERO;

        for (int i = 0; i < myTheCartArray.size(); i++) {
            final int itemQuan = myTheCartArray.get(i).getQuantity();
            final BigDecimal itemQuanInB = new BigDecimal(itemQuan);
            final int itemBulkQuan = myTheCartArray.get(i).getItem().getBulkQuantity();
            
            final BigDecimal itemPrice = myTheCartArray.get(i).getItem().getPrice();
            final BigDecimal itemBulkPric = myTheCartArray.get(i).getItem().getBulkPrice();

           
            if (myMembershipState && myTheCartArray.get(i).getItem().isBulk() 
                            && itemBulkQuan <= itemQuan) {
                
                final int remainder = itemQuan % itemBulkQuan;
                final int numBulk = itemQuan / itemBulkQuan;
                final BigDecimal remainderinB = new BigDecimal(remainder);
                final BigDecimal itemBulkQuanInB = new BigDecimal(numBulk);
                BigDecimal bulktotalcost = BigDecimal.ZERO;
                BigDecimal reMaindercost = BigDecimal.ZERO;

                bulktotalcost = itemBulkQuanInB.multiply(itemBulkPric);
                reMaindercost = remainderinB.multiply(itemPrice);
                toTalcost = toTalcost.add(bulktotalcost.add(reMaindercost));

            } else {
              
                toTalcost = toTalcost.add(itemQuanInB.multiply(itemPrice)); 
            }

        }
        return toTalcost.setScale(2, RoundingMode.HALF_EVEN);
    }
    
    /**Perform clear action for cart.
     * 
     */
    public void clear() {
        myTheCartArray.clear();   
    }

    /**Get the number of product.
     * @return the number ItemOrder in the car
     */
    public int getCartSize() {
        return myTheCartArray.size();
    }
//Overriden methods for this class
    
    /**
     *Override toString method for this class.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(100);
        for (int i = 0; i < myTheCartArray.size(); i++) {
            sb.append(myTheCartArray.get(i).toString());
            sb.append("  ");
        }
        sb.append(myMembershipState + " " + "CurrentCarSize: " +  this.getCartSize());
        return sb.toString();
    }
}
