package cn.houno.hotel.global;

/**
 * 接口
 * Created by qzc on 2017/5/8 0008.
 */

public class Api {

    //登录
    public static final String LOGIN = "http://api.hounohotel.com/login.php";
    //修改密码
    public static final String MODIFY_PWD = "http://api.hounohotel.com/editpassword.php";
    //获取酒店信息
    public static final String GET_HOTEL_INFO = "http://api.hounohotel.com/gethotelinfo.php";
    //调取房间统计数量
    public static final String GET_ROOM_STATE_COUNT = "http://api.hounohotel.com/getroomstatuscount.php";
    //获取房态
    public static final String GET_ROOM_STATE = "http://api.hounohotel.com/getroomstatus.php";
    //修改房态
    public static final String EDIT_ROOM_STATE = "http://api.hounohotel.com/changeroomstatus.php";
    //获取我的工作区域
    public static final String GET_MY_WORD = "http://api.hounohotel.com/listmywork.php";
    //获取我营业日当天的打扫房间
    public static final String GET_MY_ROOM = "http://api.hounohotel.com/listmyclearroom.php";
    //生成我营业日当天的清洁单据
    public static final String ADD_MY_BILL = "http://api.hounohotel.com/addmybill.php";
    //批量处理清洁单据
    public static final String CLEAN_MY_ROOM = "http://api.hounohotel.com/clearmyroom.php";
    //批量删除清洁单据
    public static final String DELETE_MY_ROOM = "http://api.hounohotel.com/deleteclearmyroom.php";
    //已完成单据查询
    public static final String SEARCH_MY_BILLS = "http://api.hounohotel.com/searchmyclearroom.php";
    //房态更改查询
    public static final String SEARCH_MY_ROOM_STATE = "http://api.hounohotel.com/searchmyroomstatus.php";


    //===================================预订模块===============================
    //可用房量查询
    public static final String SEARCH_ROOM_NUMBER = "http://api.hounohotel.com/searchroomnum.php";
    //获取房型代码
    public static final String GET_ROOM_TYPE_CODE = "http://api.hounohotel.com/getroomtypecode.php";
    //预订询价
    public static final String BOOK_QUERY = "http://api.hounohotel.com/listroomrate.php";
    //获取公司代码或者旅行社代码
    public static final String GET_COMPANY_OR_TRAVEL_CODE = "http://api.hounohotel.com/getcompanyortravelcode.php";
    //获取房价码
    public static final String GET_ROOM_PRICE_CODE = "http://api.hounohotel.com/getroompricecode.php";
    //获取套餐编码
    public static final String GET_ROOM_PACKAGE_CODE = "http://api.hounohotel.com/getroompackagecode.php";
    //获取个人代码或团队社代码
    public static final String GET_GROUP_OR_INDIVIDUAL_CODE = "http://api.hounohotel.com/getgrouporindividualcode.php";
    //获取国家代码
    public static final String GET_COUNTRY_CODE = "http://api.hounohotel.com/getcountrycode.php";
    //获取省份代码
    public static final String GET_STATE_CODE = "http://api.hounohotel.com/getstatecode.php";
    //获取县市代码
    public static final String GET_CITY_CODE = "http://api.hounohotel.com/getCitycode.php";
    //创建新订单
    public static final String CREATE_ORDER = "http://api.hounohotel.com/createoreder.php";
    //订单查询
    public static final String SELECT_ORDER = "http://api.hounohotel.com/selectoreder.php";
    //订单分离
    public static final String SEPARATE_ORDER = "http://api.hounohotel.com/separateoreder.php";
    //OrderIDCode=201705190134&userid=LF0010019&BusinessDay=2016-11-29
    //参数 $OrderIDCode订单编码ID,userid 用户ID　　BusinessDay营业日期

    //排房查询
    public static final String SEARCH_ALLOT_ROOM = "http://api.hounohotel.com/searchallotroom.php";
    //排房
    public static final String ALLOT_ROOM = "http://api.hounohotel.com/allotroom.php";
    //获取景观代码
    public static final String GET_ROOM_SCENERY = "http://api.hounohotel.com/getroomscenery.php";



}
