package in.hiaccounts.hinext.restaurant.model.restra_pos;


/**
 *
 * A helper class to retrieve the static pos cart. Call {@code getPosCreator()} to retrieve the pos cart before you perform any operation on the pos cart.
 * Created by Admin on 2/16/2017.
 */

public class RestraPosHelper {
    private static RestraPosCreator posCreator = new RestraPosCreator();
    private static RestraPosCreator splitPosCreator = new RestraPosCreator();
    private static RestraPosCreator splitCheckoutPosCreator = new RestraPosCreator();
    private static RestraPosTableOrderCreator posTableOrderCreator = new RestraPosTableOrderCreator();

    /**
     * Retrieve the Pos cart. Call this before perform any manipulation on the Pos cart.
     *
     * @return the pos cart
     */
    public static RestraPosCreator getPosCreator() {
        if (posCreator == null) {
            posCreator = new RestraPosCreator();
        }
        return posCreator;
    }

    public static RestraPosTableOrderCreator getPosTableOrderCreator() {
        if (posTableOrderCreator == null) {
            posTableOrderCreator = new RestraPosTableOrderCreator();
        }
        return posTableOrderCreator;
    }

    public static RestraPosCreator getSplitPosCreator() {
        if (splitPosCreator == null) {
            splitPosCreator = new RestraPosCreator();
        }
        return splitPosCreator;
    }

    public static RestraPosCreator getSplitCheckoutPosCreator() {
        if (splitCheckoutPosCreator == null) {
            splitCheckoutPosCreator = new RestraPosCreator();
        }
        return splitCheckoutPosCreator;
    }
}
