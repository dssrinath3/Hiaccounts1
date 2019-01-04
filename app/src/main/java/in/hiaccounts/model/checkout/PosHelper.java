package in.hiaccounts.model.checkout;



/**
 *
 * A helper class to retrieve the static pos cart. Call {@code getPosCreator()} to retrieve the pos cart before you perform any operation on the pos cart.
 * Created by Admin on 2/16/2017.
 */

public class PosHelper {
    private static PosCreator posCreator = new PosCreator();

    /**
     * Retrieve the Pos cart. Call this before perform any manipulation on the Pos cart.
     *
     * @return the pos cart
     */
    public static PosCreator getPosCreator() {
        if (posCreator == null) {
            posCreator = new PosCreator();
        }

        return posCreator;
    }
}
