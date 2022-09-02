package org.smartregister.chw.dao;

public class AngaModel {
    String condom_type;
    String no_condom_issued;
    String condom_brand;

    public AngaModel(String condom_type, String no_condom_issued, String condom_brand) {
        this.condom_type = condom_type;
        this.no_condom_issued = no_condom_issued;
        this.condom_brand = condom_brand;
    }

    public String getCondom_type() {
        return condom_type;
    }

    public String getNo_condom_issued() {
        return no_condom_issued;
    }

    public String getCondom_brand() {
        return condom_brand;
    }
}
