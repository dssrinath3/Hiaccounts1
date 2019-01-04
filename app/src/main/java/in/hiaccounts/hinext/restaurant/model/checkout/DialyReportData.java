package in.hiaccounts.hinext.restaurant.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by administrator on 16/2/18.
 */

public class DialyReportData implements Serializable {

    @SerializedName("Lassi")
    @Expose
    public List<Lassi> lassi = null;

    public List<Lassi> getLassi() {
        return lassi;
    }

    public void setLassi(List<Lassi> lassi) {
        this.lassi = lassi;
    }
}
