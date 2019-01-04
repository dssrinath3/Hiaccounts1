package in.hiaccounts.hinext.generaltransaction.model;


/**
 *
 * A helper class to retrieve the static pos cart. Call {@code getPosCreator()} to retrieve the pos cart before you perform any operation on the pos cart.
 * Created by Admin on 2/16/2017.
 */

public class AccountPosHelper {
    private static AccountPosCreator accountCreator = new AccountPosCreator();

    /**
     * Retrieve the Pos cart. Call this before perform any manipulation on the Pos cart.
     *
     * @return the pos cart
     */
    public static AccountPosCreator getPosCreator() {
        if (accountCreator == null) {
            accountCreator = new AccountPosCreator();
        }

        return accountCreator;
    }
}
