package cn.houno.hotel.ui.fragment.room;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;
import cn.houno.hotel.adapter.RommTypeAdapter;
import cn.houno.hotel.adapter.WorkCategoryAdapter;
import cn.houno.hotel.ui.fragment.BaseFragment;


/**
 * 项目名称：Houniaolvju
 * 类描述：首页-酒店拼团
 * 创建人：qzc
 * 创建时间：2016/12/13 14:08
 * 修改人：qzc
 * 修改时间：2016/12/13 14:08
 * 修改备注：
 */
public class HotelBulkPage extends BaseFragment {


    @Bind(R.id.iv_my_toggle)
    ImageView ivMyToggle;
    @Bind(R.id.gv_room_status)
    GridView gvRoomStatus;
    @Bind(R.id.ll_view)
    LinearLayout llView;
    @Bind(R.id.gv_room_type)
    GridView gvRoomType;
    private WorkCategoryAdapter categoryAdapter;
    private RommTypeAdapter rommTypeAdapter;
    // 图片封装为一个数组
    private String[] categorTitle = {"总房数", "在住房", "预离房", "停售房", "可售房", "预定房", "出租率", "平均房价"};
    private String[] roomType = {"行政大床房", "雅致大床房", "行政套房", "高级大床房", "高级标准房", "高级大床房"};

    private String[] categoryName = {"201", "200", "100", "201", "201", "201", "20%", "419"};
    private String[] roomTypeName = {"201", "200", "100", "201", "201", "201"};

    private List<Map<String, Object>> categoryList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View mView = inflater.inflate(R.layout.page_home_bulk, container, false);

        return mView;
    }

    @Override
    protected void initFindViewById(View view) {

    }


  /*  private void initView(View view) {

    }
*/

    @Override
    public void initData() {
        // Bundle arguments = getArguments();
      /*  categoryList = getCategoryData();
        String[] from = {"image", "text"};
        int[] to = {R.id.tv_grid_title, R.id.tv_grid_txt};*/
        categoryAdapter = new WorkCategoryAdapter(mActivity, categorTitle, categoryName);
        gvRoomStatus.setAdapter(categoryAdapter);

        rommTypeAdapter = new RommTypeAdapter(mActivity, roomType, roomTypeName);
        gvRoomType.setAdapter(rommTypeAdapter);

        // gvRoomStatus.setSelector(new ColorDrawable(Color.TRANSPARENT));

    }


    /*
   * 初始化分类列表数据
   * */
    private List<Map<String, Object>> getCategoryData() {
        //icon和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < categorTitle.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", categorTitle[i]);
            map.put("text", categoryName[i]);
            categoryList.add(map);
        }

        return categoryList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}



