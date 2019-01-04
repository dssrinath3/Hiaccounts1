package in.hiaccounts.hinext.restaurant.model.category_item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Payment implements Serializable {
    @SerializedName("Discount Voucher")
    @Expose
    public Double discountVoucher;
    @SerializedName("Bank")
    @Expose
    public Double bank;
    @SerializedName("Paytm")
    @Expose
    public Double paytm;
    @SerializedName("Cash")
    @Expose
    public Double cash;
    @SerializedName("Card")
    @Expose
    public Double card;

    public Double getDiscountVoucher() {
        return discountVoucher;
    }

    public void setDiscountVoucher(Double discountVoucher) {
        this.discountVoucher = discountVoucher;
    }

    public Double getBank() {
        return bank;
    }

    public void setBank(Double bank) {
        this.bank = bank;
    }

    public Double getPaytm() {
        return paytm;
    }

    public void setPaytm(Double paytm) {
        this.paytm = paytm;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Double getCard() {
        return card;
    }

    public void setCard(Double card) {
        this.card = card;
    }
}
