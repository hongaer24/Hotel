package cn.houno.hotel.bean;

import java.io.Serializable;

/**
 * Created by qzc on 2017-06-22.
 */

public class AllotRoomParamsBean implements Serializable {

    private String OrderIDCode;
    private String userid;
    private String BusinessDay;
    private String RoomTypeCode;
    private String Smoking;
    private String SceneryCode;
    private String IsInvented;
    private String IsAddBeb;
    private String BedCount;
    private String RoomIDCode;
    private String BeginFloor;
    private String EndFloor;
    private String IsReservation;
    private String RoomStatus_VC;
    private String RoomStatus_VD;
    private String hotelid;
    private String companyid;

    public  AllotRoomParamsBean(){

    }


    public AllotRoomParamsBean(String OrderIDCode, String userid, String BusinessDay, String RoomTypeCode,
                               String Smoking, String SceneryCode, String IsInvented, String IsAddBeb,String BedCount,
                               String RoomIDCode, String BeginFloor, String EndFloor, String IsReservation,
                               String RoomStatus_VC, String RoomStatus_VD, String hotelid, String companyid) {
        this.OrderIDCode = OrderIDCode;
        this.userid = userid;
        this.BusinessDay = BusinessDay;
        this.RoomTypeCode = RoomTypeCode;
        this.Smoking = Smoking;
        this.SceneryCode = SceneryCode;
        this.IsInvented = IsInvented;
        this.IsAddBeb = IsAddBeb;
        this.BedCount = BedCount;
        this.RoomIDCode = RoomIDCode;
        this.BeginFloor = BeginFloor;
        this.EndFloor = EndFloor;
        this.IsReservation = IsReservation;
        this.RoomStatus_VC = RoomStatus_VC;
        this.RoomStatus_VD = RoomStatus_VD;
        this.hotelid = hotelid;
        this.companyid = companyid;
    }

    public String getOrderIDCode() {
        return OrderIDCode;
    }

    public void setOrderIDCode(String orderIDCode) {
        OrderIDCode = orderIDCode;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBusinessDay() {
        return BusinessDay;
    }

    public void setBusinessDay(String businessDay) {
        BusinessDay = businessDay;
    }

    public String getRoomTypeCode() {
        return RoomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        RoomTypeCode = roomTypeCode;
    }

    public String getSmoking() {
        return Smoking;
    }

    public void setSmoking(String smoking) {
        Smoking = smoking;
    }

    public String getSceneryCode() {
        return SceneryCode;
    }

    public void setSceneryCode(String sceneryCode) {
        SceneryCode = sceneryCode;
    }

    public String getIsInvented() {
        return IsInvented;
    }

    public void setIsInvented(String isInvented) {
        IsInvented = isInvented;
    }

    public String getIsAddBeb() {
        return IsAddBeb;
    }

    public void setIsAddBeb(String isAddBeb) {
        IsAddBeb = isAddBeb;
    }

    public String getBedCount() {
        return BedCount;
    }

    public void setBedCount(String bedCount) {
        BedCount = bedCount;
    }

    public String getRoomIDCode() {
        return RoomIDCode;
    }

    public void setRoomIDCode(String roomIDCode) {
        RoomIDCode = roomIDCode;
    }

    public String getBeginFloor() {
        return BeginFloor;
    }

    public void setBeginFloor(String beginFloor) {
        BeginFloor = beginFloor;
    }

    public String getEndFloor() {
        return EndFloor;
    }

    public void setEndFloor(String endFloor) {
        EndFloor = endFloor;
    }

    public String getIsReservation() {
        return IsReservation;
    }

    public void setIsReservation(String isReservation) {
        IsReservation = isReservation;
    }

    public String getRoomStatus_VC() {
        return RoomStatus_VC;
    }

    public void setRoomStatus_VC(String roomStatus_VC) {
        RoomStatus_VC = roomStatus_VC;
    }

    public String getRoomStatus_VD() {
        return RoomStatus_VD;
    }

    public void setRoomStatus_VD(String roomStatus_VD) {
        RoomStatus_VD = roomStatus_VD;
    }

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

}
