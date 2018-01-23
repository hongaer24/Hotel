package cn.houno.hotel.bean;

import java.util.List;

import cn.houno.hotel.ui.activity.book.bean.AreaListBean;

/**
 * 房价码
 * Created by qzc on 2017-05-23.
 */

public class RoomPriceCodeListBean {

    /**
     * info : [{"IDCode":"OAT01","Name":"OAT"},{"IDCode":"walkinRate","Name":"前台价"},{"IDCode":"Ctrp","Name":"携程价"},{"IDCode":"COP2","Name":"公司协议价含双早"},{"IDCode":"COMP1","Name":"免费房"},{"IDCode":"pm","Name":"假房使用"},{"IDCode":"web1","Name":"网络价含早"},{"IDCode":"TAW","Name":"旅行社散客"},{"IDCode":"TAG","Name":"旅行社团队"},{"IDCode":"COP1","Name":"公司协议价含单早"},{"IDCode":"CompanyRate","Name":"公司协议价"},{"IDCode":"DeilyRate","Name":"每日价格"},{"IDCode":"B999","Name":"长租房协议"}]
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

    public static class InfoBean {
        /**
         * IDCode : OAT01
         * Name : OAT
         */

        public InfoBean(){
        }

        public InfoBean(String idCode,String name){
            IDCode = idCode;
            Name = name;
        }

        private String IDCode;
        private String Name;

        public String getIDCode() {
            return IDCode;
        }

        public void setIDCode(String IDCode) {
            this.IDCode = IDCode;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }
}
