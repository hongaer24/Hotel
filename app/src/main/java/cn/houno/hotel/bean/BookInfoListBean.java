package cn.houno.hotel.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 公司、旅行社、团队或者个人
 * Created by qzc on 2017-05-24.
 */

public class BookInfoListBean implements Serializable {

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

    public static class InfoBean implements Serializable {
        /**
         * IDCode : I201704120050
         * ClientID : I0050
         * Stype : 个人
         * MainName : 挂账测试
         * FisrtName : Liuzuquan
         * SecondName : 刘祖全
         * Sex : 男
         * Country : 中华人民共和国
         * State : 河南省
         * City : 郑州
         * IDCard : 512501197203035172
         * ContactinformationStr :
         * Address : 1
         */

        private String IDCode;
        private String ClientID;
        private String Stype;
        private String MainName;
        private String FisrtName;
        private String SecondName;
        private String Sex;
        private String Country;
        private String State;
        private String City;
        private String IDCard;
        private String ContactinformationStr;
        private String Address;


        public InfoBean() {

        }

        public InfoBean(String idCode, String name) {
            IDCode = idCode;
            MainName = name;
        }

        public String getIDCode() {
            return IDCode;
        }

        public void setIDCode(String IDCode) {
            this.IDCode = IDCode;
        }

        public String getClientID() {
            return ClientID;
        }

        public void setClientID(String ClientID) {
            this.ClientID = ClientID;
        }

        public String getStype() {
            return Stype;
        }

        public void setStype(String Stype) {
            this.Stype = Stype;
        }

        public String getMainName() {
            return MainName;
        }

        public void setMainName(String MainName) {
            this.MainName = MainName;
        }

        public String getFisrtName() {
            return FisrtName;
        }

        public void setFisrtName(String FisrtName) {
            this.FisrtName = FisrtName;
        }

        public String getSecondName() {
            return SecondName;
        }

        public void setSecondName(String SecondName) {
            this.SecondName = SecondName;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getCountry() {
            return Country;
        }

        public void setCountry(String Country) {
            this.Country = Country;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getIDCard() {
            return IDCard;
        }

        public void setIDCard(String IDCard) {
            this.IDCard = IDCard;
        }

        public String getContactinformationStr() {
            return ContactinformationStr;
        }

        public void setContactinformationStr(String ContactinformationStr) {
            this.ContactinformationStr = ContactinformationStr;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }
    }
}
