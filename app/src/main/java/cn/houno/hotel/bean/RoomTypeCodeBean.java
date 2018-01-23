package cn.houno.hotel.bean;

/**
 * Created by qzc on 2017-05-13.
 */

public class RoomTypeCodeBean {

    public RoomTypeCodeBean(){

    }

    public RoomTypeCodeBean(String code,String name){
        this.code=code;
        this.name=name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String code;
    private String name;
}
