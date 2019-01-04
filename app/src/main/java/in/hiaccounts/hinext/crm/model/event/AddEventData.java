package in.hiaccounts.hinext.crm.model.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Srinath on 21/12/17.
 */

public class AddEventData implements Serializable {
    @SerializedName("eventId")
    @Expose
    private Long eventId;
    @SerializedName("eventName")
    @Expose
    private String eventName;
    @SerializedName("eventDate")
    @Expose
    private String eventDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("employeeId")
    @Expose
    private Long employeeId;
    @SerializedName("activityType")
    @Expose
    private String activityType;
    @SerializedName("location")
    @Expose
    private Long location;
    @SerializedName("notification")
    @Expose
    private Boolean notification;
    @SerializedName("priority")
    @Expose
    private String priority;
    @SerializedName("employeeDTOList")
    @Expose
    private String employeeDTOList;
    @SerializedName("employeeName")
    @Expose
    private String employeeName;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getEmployeeDTOList() {
        return employeeDTOList;
    }

    public void setEmployeeDTOList(String employeeDTOList) {
        this.employeeDTOList = employeeDTOList;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
