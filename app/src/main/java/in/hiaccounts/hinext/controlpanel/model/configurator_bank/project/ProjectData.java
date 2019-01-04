package in.hiaccounts.hinext.controlpanel.model.configurator_bank.project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Prateek on 8/30/2017.
 */

public class ProjectData implements Serializable {

    @SerializedName("first")
    @Expose
    public boolean first;
    @SerializedName("prev")
    @Expose
    public boolean prev;
    @SerializedName("next")
    @Expose
    public boolean next;
    @SerializedName("last")
    @Expose
    public boolean last;
    @SerializedName("data")
    @Expose
    public List<ProjectDatum> data = null;
    @SerializedName("pageNo")
    @Expose
    public int pageNo;

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public List<ProjectDatum> getData() {
        return data;
    }

    public void setData(List<ProjectDatum> data) {
        this.data = data;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
