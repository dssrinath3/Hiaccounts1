package in.hiaccounts.hinext.sales.model.sales_pos;




/**
 *
 * A helper class to retrieve the static pos cart. Call {@code getPosCreator()} to retrieve the pos cart before you perform any operation on the pos cart.
 * Created by Admin on 2/16/2017.
 */

public class SalesPosHelper {
    private static SalesPosCreator salesposCreator = new SalesPosCreator();

    /**
     * Retrieve the Pos cart. Call this before perform any manipulation on the Pos cart.
     *
     * @return the pos cart
     */
    public static SalesPosCreator getPosCreator() {
        if (salesposCreator == null) {
            salesposCreator = new SalesPosCreator();
        }

        return salesposCreator;
    }
}
