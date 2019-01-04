package in.hiaccounts.hinext.inventory.model.assestscategory;

/**
 * Created by shrinath on 12/4/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AssestsCategoryData {
    @SerializedName("first")
    @Expose
    private Boolean first;
    @SerializedName("prev")
    @Expose
    private Boolean prev;
    @SerializedName("next")
    @Expose
    private Boolean next;
    @SerializedName("last")
    @Expose
    private Boolean last;
    @SerializedName("data")
    @Expose
    private List<AssestsCategory> data = null;
    @SerializedName("pageNo")
    @Expose
    private int pageNo;
    @SerializedName("appleDTOLists")
    @Expose
    private List<Object> appleDTOLists = null;

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getPrev() {
        return prev;
    }

    public void setPrev(Boolean prev) {
        this.prev = prev;
    }

    public Boolean getNext() {
        return next;
    }

    public void setNext(Boolean next) {
        this.next = next;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public List<AssestsCategory> getData() {
        return data;
    }

    public void setData(List<AssestsCategory> data) {
        this.data = data;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<Object> getAppleDTOLists() {
        return appleDTOLists;
    }

    public void setAppleDTOLists(List<Object> appleDTOLists) {
        this.appleDTOLists = appleDTOLists;
    }
}
