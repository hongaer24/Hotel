package cn.houno.hotel.ui.activity.book.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qzc on 2017-06-12.
 */

public class OrderListBean implements  Serializable{


    /**
     * info : [{"id":"AE6ADE70-8565-B895-5C37-716F78EE0B02","idcode":"201705190133","resstatus":"1","resid":"133","partyid":"0","profileidcode":"I201705190108","mainname":"吴桂芳","fisrtname":"","secondname":"","sex":"女","language":"中文","sys_title":"","address":"","country":"中华人民共和国","state":"海南省","city":"海口市","phone":"13187867700","idcard":"46010400000","isvip":"0","begindate":"2016-12-02 00:00:00.000","begindateweekname":"星期五","enddate":"2016-12-04 00:00:00.000","enddateweekname":"星期日","endtime":"12:00:00","daycount":"2","roomscount":"2","personcount":"2","roomtypeidcode":"DK","roomtypename":"豪华大床房","roomidcode":"","roomname":"","rateidcode":"walkinRate","ratename":"前台价","ratevalues":"400.00","fixedrate":"0","isupgrade":"0","isincognito":"0","packageidcode":"BreakFPG01","packagename":"早餐套餐014","paymentidcode":"","paymentname":"","restypeidcode":"","restypename":"","agentidcode":"","agentname":"","companyidcode":"","companyname":"","groupidcode":"","groupname":"","marketidcode":"","marketname":"","sourceidcode":"app","sourcename":"APP下单","creditcard":"","bankcradidcode":"","bankcradname":"","approvalcode":"","approvalamt":".00","approvalbalance":".00","arno":"","guest_balance":".00","info":"海景房，有午餐","blockidcode":"","blockname":"","iswalkin":"0","isinvented":"0","isshare":"0","businessday":"2016-11-29 00:00:00.000","hotelidcode":"LF001","companyinfoidcode":"BOTAO","createdatetime":"2017-05-19 15:05:57.000","loginname":"LF0010019","updatedatetime":"2017-05-19 15:05:57.000","uploginname":"LF0010019","checkindatetime":null,"checkoutdatetime":null,"status":"1","row_number":"1"}]
     * status : 2
     * msg : 获取数据成功
     */

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
         * id : AE6ADE70-8565-B895-5C37-716F78EE0B02
         * idcode : 201705190133
         * resstatus : 1
         * resid : 133
         * partyid : 0
         * profileidcode : I201705190108
         * mainname : 吴桂芳
         * fisrtname :
         * secondname :
         * sex : 女
         * language : 中文
         * sys_title :
         * address :
         * country : 中华人民共和国
         * state : 海南省
         * city : 海口市
         * phone : 13187867700
         * idcard : 46010400000
         * isvip : 0
         * begindate : 2016-12-02 00:00:00.000
         * begindateweekname : 星期五
         * enddate : 2016-12-04 00:00:00.000
         * enddateweekname : 星期日
         * endtime : 12:00:00
         * daycount : 2
         * roomscount : 2
         * personcount : 2
         * roomtypeidcode : DK
         * roomtypename : 豪华大床房
         * roomidcode :
         * roomname :
         * rateidcode : walkinRate
         * ratename : 前台价
         * ratevalues : 400.00
         * fixedrate : 0
         * isupgrade : 0
         * isincognito : 0
         * packageidcode : BreakFPG01
         * packagename : 早餐套餐014
         * paymentidcode :
         * paymentname :
         * restypeidcode :
         * restypename :
         * agentidcode :
         * agentname :
         * companyidcode :
         * companyname :
         * groupidcode :
         * groupname :
         * marketidcode :
         * marketname :
         * sourceidcode : app
         * sourcename : APP下单
         * creditcard :
         * bankcradidcode :
         * bankcradname :
         * approvalcode :
         * approvalamt : .00
         * approvalbalance : .00
         * arno :
         * guest_balance : .00
         * info : 海景房，有午餐
         * blockidcode :
         * blockname :
         * iswalkin : 0
         * isinvented : 0
         * isshare : 0
         * businessday : 2016-11-29 00:00:00.000
         * hotelidcode : LF001
         * companyinfoidcode : BOTAO
         * createdatetime : 2017-05-19 15:05:57.000
         * loginname : LF0010019
         * updatedatetime : 2017-05-19 15:05:57.000
         * uploginname : LF0010019
         * checkindatetime : null
         * checkoutdatetime : null
         * status : 1
         * row_number : 1
         */

        private String id;
        private String idcode;
        private String resstatus;
        private String resid;
        private String partyid;
        private String profileidcode;
        private String mainname;
        private String fisrtname;
        private String secondname;
        private String sex;
        private String language;
        private String sys_title;
        private String address;
        private String country;
        private String state;
        private String city;
        private String phone;
        private String idcard;
        private String isvip;
        private String begindate;
        private String begindateweekname;
        private String enddate;
        private String enddateweekname;
        private String endtime;
        private String daycount;
        private String roomscount;
        private String personcount;
        private String roomtypeidcode;
        private String roomtypename;
        private String roomidcode;
        private String roomname;
        private String rateidcode;
        private String ratename;
        private String ratevalues;
        private String fixedrate;
        private String isupgrade;
        private String isincognito;
        private String packageidcode;
        private String packagename;
        private String paymentidcode;
        private String paymentname;
        private String restypeidcode;
        private String restypename;
        private String agentidcode;
        private String agentname;
        private String companyidcode;
        private String companyname;
        private String groupidcode;
        private String groupname;
        private String marketidcode;
        private String marketname;
        private String sourceidcode;
        private String sourcename;
        private String creditcard;
        private String bankcradidcode;
        private String bankcradname;
        private String approvalcode;
        private String approvalamt;
        private String approvalbalance;
        private String arno;
        private String guest_balance;
        private String info;
        private String blockidcode;
        private String blockname;
        private String iswalkin;
        private String isinvented;
        private String isshare;
        private String businessday;
        private String hotelidcode;
        private String companyinfoidcode;
        private String createdatetime;
        private String loginname;
        private String updatedatetime;
        private String uploginname;
        private Object checkindatetime;
        private Object checkoutdatetime;
        private String status;
        private String row_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdcode() {
            return idcode;
        }

        public void setIdcode(String idcode) {
            this.idcode = idcode;
        }

        public String getResstatus() {
            return resstatus;
        }

        public void setResstatus(String resstatus) {
            this.resstatus = resstatus;
        }

        public String getResid() {
            return resid;
        }

        public void setResid(String resid) {
            this.resid = resid;
        }

        public String getPartyid() {
            return partyid;
        }

        public void setPartyid(String partyid) {
            this.partyid = partyid;
        }

        public String getProfileidcode() {
            return profileidcode;
        }

        public void setProfileidcode(String profileidcode) {
            this.profileidcode = profileidcode;
        }

        public String getMainname() {
            return mainname;
        }

        public void setMainname(String mainname) {
            this.mainname = mainname;
        }

        public String getFisrtname() {
            return fisrtname;
        }

        public void setFisrtname(String fisrtname) {
            this.fisrtname = fisrtname;
        }

        public String getSecondname() {
            return secondname;
        }

        public void setSecondname(String secondname) {
            this.secondname = secondname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getSys_title() {
            return sys_title;
        }

        public void setSys_title(String sys_title) {
            this.sys_title = sys_title;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getIsvip() {
            return isvip;
        }

        public void setIsvip(String isvip) {
            this.isvip = isvip;
        }

        public String getBegindate() {
            return begindate;
        }

        public void setBegindate(String begindate) {
            this.begindate = begindate;
        }

        public String getBegindateweekname() {
            return begindateweekname;
        }

        public void setBegindateweekname(String begindateweekname) {
            this.begindateweekname = begindateweekname;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getEnddateweekname() {
            return enddateweekname;
        }

        public void setEnddateweekname(String enddateweekname) {
            this.enddateweekname = enddateweekname;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getDaycount() {
            return daycount;
        }

        public void setDaycount(String daycount) {
            this.daycount = daycount;
        }

        public String getRoomscount() {
            return roomscount;
        }

        public void setRoomscount(String roomscount) {
            this.roomscount = roomscount;
        }

        public String getPersoncount() {
            return personcount;
        }

        public void setPersoncount(String personcount) {
            this.personcount = personcount;
        }

        public String getRoomtypeidcode() {
            return roomtypeidcode;
        }

        public void setRoomtypeidcode(String roomtypeidcode) {
            this.roomtypeidcode = roomtypeidcode;
        }

        public String getRoomtypename() {
            return roomtypename;
        }

        public void setRoomtypename(String roomtypename) {
            this.roomtypename = roomtypename;
        }

        public String getRoomidcode() {
            return roomidcode;
        }

        public void setRoomidcode(String roomidcode) {
            this.roomidcode = roomidcode;
        }

        public String getRoomname() {
            return roomname;
        }

        public void setRoomname(String roomname) {
            this.roomname = roomname;
        }

        public String getRateidcode() {
            return rateidcode;
        }

        public void setRateidcode(String rateidcode) {
            this.rateidcode = rateidcode;
        }

        public String getRatename() {
            return ratename;
        }

        public void setRatename(String ratename) {
            this.ratename = ratename;
        }

        public String getRatevalues() {
            return ratevalues;
        }

        public void setRatevalues(String ratevalues) {
            this.ratevalues = ratevalues;
        }

        public String getFixedrate() {
            return fixedrate;
        }

        public void setFixedrate(String fixedrate) {
            this.fixedrate = fixedrate;
        }

        public String getIsupgrade() {
            return isupgrade;
        }

        public void setIsupgrade(String isupgrade) {
            this.isupgrade = isupgrade;
        }

        public String getIsincognito() {
            return isincognito;
        }

        public void setIsincognito(String isincognito) {
            this.isincognito = isincognito;
        }

        public String getPackageidcode() {
            return packageidcode;
        }

        public void setPackageidcode(String packageidcode) {
            this.packageidcode = packageidcode;
        }

        public String getPackagename() {
            return packagename;
        }

        public void setPackagename(String packagename) {
            this.packagename = packagename;
        }

        public String getPaymentidcode() {
            return paymentidcode;
        }

        public void setPaymentidcode(String paymentidcode) {
            this.paymentidcode = paymentidcode;
        }

        public String getPaymentname() {
            return paymentname;
        }

        public void setPaymentname(String paymentname) {
            this.paymentname = paymentname;
        }

        public String getRestypeidcode() {
            return restypeidcode;
        }

        public void setRestypeidcode(String restypeidcode) {
            this.restypeidcode = restypeidcode;
        }

        public String getRestypename() {
            return restypename;
        }

        public void setRestypename(String restypename) {
            this.restypename = restypename;
        }

        public String getAgentidcode() {
            return agentidcode;
        }

        public void setAgentidcode(String agentidcode) {
            this.agentidcode = agentidcode;
        }

        public String getAgentname() {
            return agentname;
        }

        public void setAgentname(String agentname) {
            this.agentname = agentname;
        }

        public String getCompanyidcode() {
            return companyidcode;
        }

        public void setCompanyidcode(String companyidcode) {
            this.companyidcode = companyidcode;
        }

        public String getCompanyname() {
            return companyname;
        }

        public void setCompanyname(String companyname) {
            this.companyname = companyname;
        }

        public String getGroupidcode() {
            return groupidcode;
        }

        public void setGroupidcode(String groupidcode) {
            this.groupidcode = groupidcode;
        }

        public String getGroupname() {
            return groupname;
        }

        public void setGroupname(String groupname) {
            this.groupname = groupname;
        }

        public String getMarketidcode() {
            return marketidcode;
        }

        public void setMarketidcode(String marketidcode) {
            this.marketidcode = marketidcode;
        }

        public String getMarketname() {
            return marketname;
        }

        public void setMarketname(String marketname) {
            this.marketname = marketname;
        }

        public String getSourceidcode() {
            return sourceidcode;
        }

        public void setSourceidcode(String sourceidcode) {
            this.sourceidcode = sourceidcode;
        }

        public String getSourcename() {
            return sourcename;
        }

        public void setSourcename(String sourcename) {
            this.sourcename = sourcename;
        }

        public String getCreditcard() {
            return creditcard;
        }

        public void setCreditcard(String creditcard) {
            this.creditcard = creditcard;
        }

        public String getBankcradidcode() {
            return bankcradidcode;
        }

        public void setBankcradidcode(String bankcradidcode) {
            this.bankcradidcode = bankcradidcode;
        }

        public String getBankcradname() {
            return bankcradname;
        }

        public void setBankcradname(String bankcradname) {
            this.bankcradname = bankcradname;
        }

        public String getApprovalcode() {
            return approvalcode;
        }

        public void setApprovalcode(String approvalcode) {
            this.approvalcode = approvalcode;
        }

        public String getApprovalamt() {
            return approvalamt;
        }

        public void setApprovalamt(String approvalamt) {
            this.approvalamt = approvalamt;
        }

        public String getApprovalbalance() {
            return approvalbalance;
        }

        public void setApprovalbalance(String approvalbalance) {
            this.approvalbalance = approvalbalance;
        }

        public String getArno() {
            return arno;
        }

        public void setArno(String arno) {
            this.arno = arno;
        }

        public String getGuest_balance() {
            return guest_balance;
        }

        public void setGuest_balance(String guest_balance) {
            this.guest_balance = guest_balance;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getBlockidcode() {
            return blockidcode;
        }

        public void setBlockidcode(String blockidcode) {
            this.blockidcode = blockidcode;
        }

        public String getBlockname() {
            return blockname;
        }

        public void setBlockname(String blockname) {
            this.blockname = blockname;
        }

        public String getIswalkin() {
            return iswalkin;
        }

        public void setIswalkin(String iswalkin) {
            this.iswalkin = iswalkin;
        }

        public String getIsinvented() {
            return isinvented;
        }

        public void setIsinvented(String isinvented) {
            this.isinvented = isinvented;
        }

        public String getIsshare() {
            return isshare;
        }

        public void setIsshare(String isshare) {
            this.isshare = isshare;
        }

        public String getBusinessday() {
            return businessday;
        }

        public void setBusinessday(String businessday) {
            this.businessday = businessday;
        }

        public String getHotelidcode() {
            return hotelidcode;
        }

        public void setHotelidcode(String hotelidcode) {
            this.hotelidcode = hotelidcode;
        }

        public String getCompanyinfoidcode() {
            return companyinfoidcode;
        }

        public void setCompanyinfoidcode(String companyinfoidcode) {
            this.companyinfoidcode = companyinfoidcode;
        }

        public String getCreatedatetime() {
            return createdatetime;
        }

        public void setCreatedatetime(String createdatetime) {
            this.createdatetime = createdatetime;
        }

        public String getLoginname() {
            return loginname;
        }

        public void setLoginname(String loginname) {
            this.loginname = loginname;
        }

        public String getUpdatedatetime() {
            return updatedatetime;
        }

        public void setUpdatedatetime(String updatedatetime) {
            this.updatedatetime = updatedatetime;
        }

        public String getUploginname() {
            return uploginname;
        }

        public void setUploginname(String uploginname) {
            this.uploginname = uploginname;
        }

        public Object getCheckindatetime() {
            return checkindatetime;
        }

        public void setCheckindatetime(Object checkindatetime) {
            this.checkindatetime = checkindatetime;
        }

        public Object getCheckoutdatetime() {
            return checkoutdatetime;
        }

        public void setCheckoutdatetime(Object checkoutdatetime) {
            this.checkoutdatetime = checkoutdatetime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
