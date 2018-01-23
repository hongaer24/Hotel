package cn.houno.hotel.ui.activity.book.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 预订询价列表
 * Created by qzc on 2017-06-07.
 */

public class BookQueryListBean implements Serializable{

    private int status;
    private String msg;
    private List<InfoBean> info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean implements Serializable{
        /**
         * id : 03A6DC10-F9DE-42CE-948C-10A63D9A08EE
         * roomrategourpidcode : walkin
         * cid : 32268178-1195-48F9-BE93-87013A00B408
         * roomcount : 9
         * businessday : 2016-11-29 00:00:00.000
         * isdiscount : 0
         * discountamounts : .00
         * amountsex : 180.00
         * packageheaderidcode :
         * roomtypeidcode : SGVT
         * roomratemainidcode : B999
         * roomratemainname : 长租房协议
         * bid : 18D956FB-CC5C-44CF-BE74-E8208843A6AB
         * did : D5A66707-A95E-46A7-AC7C-F2DFF097AEEA
         */

        private String id;
        private String roomrategourpidcode;
        private String cid;
        private String roomcount;
        private String businessday;
        private String isdiscount;
        private String discountamounts;
        private String amountsex;
        private String packageheaderidcode;
        private String roomtypeidcode;
        private String roomratemainidcode;
        private String roomratemainname;
        private String bid;
        private String did;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRoomrategourpidcode() {
            return roomrategourpidcode;
        }

        public void setRoomrategourpidcode(String roomrategourpidcode) {
            this.roomrategourpidcode = roomrategourpidcode;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getRoomcount() {
            return roomcount;
        }

        public void setRoomcount(String roomcount) {
            this.roomcount = roomcount;
        }

        public String getBusinessday() {
            return businessday;
        }

        public void setBusinessday(String businessday) {
            this.businessday = businessday;
        }

        public String getIsdiscount() {
            return isdiscount;
        }

        public void setIsdiscount(String isdiscount) {
            this.isdiscount = isdiscount;
        }

        public String getDiscountamounts() {
            return discountamounts;
        }

        public void setDiscountamounts(String discountamounts) {
            this.discountamounts = discountamounts;
        }

        public String getAmountsex() {
            return amountsex;
        }

        public void setAmountsex(String amountsex) {
            this.amountsex = amountsex;
        }

        public String getPackageheaderidcode() {
            return packageheaderidcode;
        }

        public void setPackageheaderidcode(String packageheaderidcode) {
            this.packageheaderidcode = packageheaderidcode;
        }

        public String getRoomtypeidcode() {
            return roomtypeidcode;
        }

        public void setRoomtypeidcode(String roomtypeidcode) {
            this.roomtypeidcode = roomtypeidcode;
        }

        public String getRoomratemainidcode() {
            return roomratemainidcode;
        }

        public void setRoomratemainidcode(String roomratemainidcode) {
            this.roomratemainidcode = roomratemainidcode;
        }

        public String getRoomratemainname() {
            return roomratemainname;
        }

        public void setRoomratemainname(String roomratemainname) {
            this.roomratemainname = roomratemainname;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }
    }
}
