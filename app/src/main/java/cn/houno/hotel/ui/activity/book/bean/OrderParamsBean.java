package cn.houno.hotel.ui.activity.book.bean;

import java.io.Serializable;

/**
 * Created by qzc on 2017-06-12.
 */

public class OrderParamsBean implements Serializable{


    private String mainname;
    private String phone;
    private String orderid;
    private String begindate;
    private String enddate;
    private String roomtypeidcode;
    private String rateidcode;
    private String packageidcode;
    private String companyidcode;
    private String groupidcode;
    private String travelidcode;
    private String linkname;

    public String getMainname() {
        return mainname;
    }

    public void setMainname(String mainname) {
        this.mainname = mainname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getRoomtypeidcode() {
        return roomtypeidcode;
    }

    public void setRoomtypeidcode(String roomtypeidcode) {
        this.roomtypeidcode = roomtypeidcode;
    }

    public String getRateidcode() {
        return rateidcode;
    }

    public void setRateidcode(String rateidcode) {
        this.rateidcode = rateidcode;
    }

    public String getPackageidcode() {
        return packageidcode;
    }

    public void setPackageidcode(String packageidcode) {
        this.packageidcode = packageidcode;
    }

    public String getCompanyidcode() {
        return companyidcode;
    }

    public void setCompanyidcode(String companyidcode) {
        this.companyidcode = companyidcode;
    }

    public String getGroupidcode() {
        return groupidcode;
    }

    public void setGroupidcode(String groupidcode) {
        this.groupidcode = groupidcode;
    }

    public String getTravelidcode() {
        return travelidcode;
    }

    public void setTravelidcode(String travelidcode) {
        this.travelidcode = travelidcode;
    }

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }
}
