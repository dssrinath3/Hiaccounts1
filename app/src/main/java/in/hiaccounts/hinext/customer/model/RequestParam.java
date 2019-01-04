package in.hiaccounts.hinext.customer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 9/4/2017.
 */

public class RequestParam implements Serializable {
    @SerializedName("firstPage")
    @Expose
    public boolean firstPage;
    @SerializedName("lastPage")
    @Expose
    public boolean lastPage;
    @SerializedName("pageNo")
    @Expose
    public int pageNo;
    @SerializedName("prevPage")
    @Expose
    public boolean prevPage;
    @SerializedName("nextPage")
    @Expose
    public boolean nextPage;

    public RequestParam(boolean firstPage, boolean lastPage, int pageNo, boolean prevPage, boolean nextPage) {
        this.firstPage = firstPage;
        this.lastPage = lastPage;
        this.pageNo = pageNo;
        this.prevPage = prevPage;
        this.nextPage = nextPage;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public boolean isPrevPage() {
        return prevPage;
    }

    public void setPrevPage(boolean prevPage) {
        this.prevPage = prevPage;
    }

    public boolean isNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }
}
