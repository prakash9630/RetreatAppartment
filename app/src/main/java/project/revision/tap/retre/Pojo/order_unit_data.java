package project.revision.tap.retre.Pojo;

import java.io.Serializable;

/**
 * Created by prakash on 1/18/2017.
 */
public class Order_unit_data {
    String Unitname;
    String UnitQuantity;

    public Order_unit_data(String unitname, String unitQuantity) {
        Unitname = unitname;
        UnitQuantity = unitQuantity;
    }

    public String getUnitname() {
        return Unitname;
    }

    public String getUnitQuantity() {
        return UnitQuantity;
    }
}
