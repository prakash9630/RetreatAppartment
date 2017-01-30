package project.revision.tap.retre.Pojo;

import java.io.Serializable;

/**
 * Created by prakash on 1/13/2017.
 */
public class Order_data implements Serializable {

    String orderno;
    String create;
    String updated;
    String total;
    String orderStatus;
    String cusomerId;

    public Order_data(String orderno, String create, String updated, String total, String orderStatus,String cusomerId) {
        this.orderno = orderno;
        this.create = create;
        this.updated = updated;
        this.total = total;
        this.orderStatus = orderStatus;
        this.cusomerId=cusomerId;
    }

    public String getCusomerId() {
        return cusomerId;
    }

    public String getOrderno() {
        return orderno;
    }

    public String getCreate() {
        return create;
    }

    public String getUpdated() {
        return updated;
    }

    public String getTotal() {
        return total;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
