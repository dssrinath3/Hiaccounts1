package in.hiaccounts.hinext.sales.model.sales_notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerNotificationsListData {
    @SerializedName("kafkaServerAddress")
    @Expose
    public String kafkaServerAddress;
    @SerializedName("kafkaConsumerGroupId")
    @Expose
    public String kafkaConsumerGroupId;
    @SerializedName("kafkaTopic")
    @Expose
    public String kafkaTopic;
    @SerializedName("kafkaType")
    @Expose
    public String kafkaType;
    @SerializedName("Url")
    @Expose
    public String url;
    @SerializedName("jsonData")
    @Expose
    public CustomerNotificationsJsonData jsonData;

    public String getKafkaServerAddress() {
        return kafkaServerAddress;
    }

    public void setKafkaServerAddress(String kafkaServerAddress) {
        this.kafkaServerAddress = kafkaServerAddress;
    }

    public String getKafkaConsumerGroupId() {
        return kafkaConsumerGroupId;
    }

    public void setKafkaConsumerGroupId(String kafkaConsumerGroupId) {
        this.kafkaConsumerGroupId = kafkaConsumerGroupId;
    }

    public String getKafkaTopic() {
        return kafkaTopic;
    }

    public void setKafkaTopic(String kafkaTopic) {
        this.kafkaTopic = kafkaTopic;
    }

    public String getKafkaType() {
        return kafkaType;
    }

    public void setKafkaType(String kafkaType) {
        this.kafkaType = kafkaType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CustomerNotificationsJsonData getJsonData() {
        return jsonData;
    }

    public void setJsonData(CustomerNotificationsJsonData jsonData) {
        this.jsonData = jsonData;
    }
}
