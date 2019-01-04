package in.hiaccounts.model.pos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 4/6/2017.
 */

public class SerializableItemsDTOList implements Serializable {

    @SerializedName("serializableItemId")
    @Expose
    private long serializableItemId;
    @SerializedName("serializableSIno")
    @Expose
    private Object serializableSIno;
    @SerializedName("serializableNumbers")
    @Expose
    private String serializableNumbers;
    @SerializedName("serializableRemark")
    @Expose
    private Object serializableRemark;
    @SerializedName("serializableDate")
    @Expose
    private Object serializableDate;
    @SerializedName("serializableItemTableId")
    @Expose
    private Object serializableItemTableId;
    @SerializedName("serializableStatus")
    @Expose
    private String serializableStatus;
    @SerializedName("serializableItemCode")
    @Expose
    private String serializableItemCode;


    /**
         * No args constructor for use in serialization
         *
         */
        public SerializableItemsDTOList() {
        }

        /**
         *
         * @param serializableItemCode
         * @param serializableStatus
         * @param serializableItemTableId
         * @param serializableNumbers
         * @param serializableItemId
         * @param serializableDate
         * @param serializableRemark
         * @param serializableSIno
         */
        public SerializableItemsDTOList(long serializableItemId, Object serializableSIno, String serializableNumbers, Object serializableRemark, Object serializableDate, Object serializableItemTableId, String serializableStatus, String serializableItemCode) {
            super();
            this.serializableItemId = serializableItemId;
            this.serializableSIno = serializableSIno;
            this.serializableNumbers = serializableNumbers;
            this.serializableRemark = serializableRemark;
            this.serializableDate = serializableDate;
            this.serializableItemTableId = serializableItemTableId;
            this.serializableStatus = serializableStatus;
            this.serializableItemCode = serializableItemCode;
        }

        public long getSerializableItemId() {
            return serializableItemId;
        }

        public void setSerializableItemId(long serializableItemId) {
            this.serializableItemId = serializableItemId;
        }

        public Object getSerializableSIno() {
            return serializableSIno;
        }

        public void setSerializableSIno(Object serializableSIno) {
            this.serializableSIno = serializableSIno;
        }

        public String getSerializableNumbers() {
            return serializableNumbers;
        }

        public void setSerializableNumbers(String serializableNumbers) {
            this.serializableNumbers = serializableNumbers;
        }

        public Object getSerializableRemark() {
            return serializableRemark;
        }

        public void setSerializableRemark(Object serializableRemark) {
            this.serializableRemark = serializableRemark;
        }

        public Object getSerializableDate() {
            return serializableDate;
        }

        public void setSerializableDate(Object serializableDate) {
            this.serializableDate = serializableDate;
        }

        public Object getSerializableItemTableId() {
            return serializableItemTableId;
        }

        public void setSerializableItemTableId(Object serializableItemTableId) {
            this.serializableItemTableId = serializableItemTableId;
        }

        public String getSerializableStatus() {
            return serializableStatus;
        }

        public void setSerializableStatus(String serializableStatus) {
            this.serializableStatus = serializableStatus;
        }

        public String getSerializableItemCode() {
            return serializableItemCode;
        }

        public void setSerializableItemCode(String serializableItemCode) {
            this.serializableItemCode = serializableItemCode;
        }

    @Override
    public String toString() {
        return serializableNumbers;
    }
}

