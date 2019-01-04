package in.hiaccounts.hinext.service.model.service_pos;

/**
 * Created by administrator on 23/2/18.
 */

public class ServicePosHelper {
    private static ServicePosCreator serviceposCreator = new ServicePosCreator();

    /**
     * Retrieve the Pos cart. Call this before perform any manipulation on the Pos cart.
     *
     * @return the pos cart
     */
    public static ServicePosCreator getPosCreator() {
        if (serviceposCreator == null) {
            serviceposCreator = new ServicePosCreator();
        }

        return serviceposCreator;
    }
}
