package in.hiaccounts.hinext.restaurant.model.checkout;

/**
 * Created by Admin on 11/22/2017.
 */

public class PaymentOption {

    String name;
    Long id;


    public PaymentOption(Long id,String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
