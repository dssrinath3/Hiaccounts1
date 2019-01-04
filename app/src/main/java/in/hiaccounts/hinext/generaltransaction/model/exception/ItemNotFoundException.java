package in.hiaccounts.hinext.generaltransaction.model.exception;

/**
 * Throw this exception to indicate invalid operation on product which does not belong to a shopping cart
 */
public class ItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 43L;

    private static final String DEFAULT_MESSAGE = "Product is not found in the shopping cart.";

    public ItemNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
