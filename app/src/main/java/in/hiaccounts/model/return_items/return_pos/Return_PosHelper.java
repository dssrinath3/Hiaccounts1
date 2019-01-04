package in.hiaccounts.model.return_items.return_pos;


/**
 *
 * A helper class to retrieve the static pos cart. Call {@code getPosCreator()} to retrieve the pos cart before you perform any operation on the pos cart.
 * Created by Admin on 2/16/2017.
 */

public class Return_PosHelper {
    private static Return_PosCreator returnposCreator = new Return_PosCreator();

    /**
     * Retrieve the Pos cart. Call this before perform any manipulation on the Pos cart.
     *
     * @return the pos cart
     */
    public static Return_PosCreator getReturnPosCreator() {
        if (returnposCreator == null) {
            returnposCreator = new Return_PosCreator();
        }

        return returnposCreator;
    }
}
