package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignage;

import android.widget.ListView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Constant.AppDataSource;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Adapter.MeshTVAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Model.MeshSignage;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.View.Default.MeshDigitalSigangeList;

import java.util.ArrayList;
@Layout(R.layout.bittel_layout_1_list)
@DataSetting()
public class CarlsonDigitalSignageList extends MeshDigitalSigangeList
{
    //==========================================Variable============================================
    //------------------------------------------Constant--------------------------------------------
    public static final String TAG = CarlsonDigitalSignageList.class.getSimpleName();
    //----------------------------------------------------------------------------------------------
    //-------------------------------------------View-----------------------------------------------
    @BindWidget(R.id.lv_list)
    public ListView lv_list;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    ///====================================MeshDigitalSigangeList===================================
    @Override
    public MeshTVAdapter setAdapter(ArrayList<MeshSignage> arrayList) {
        return new CarlsonDigitalSignageAdapter(getActivity(),lv_list,R.layout.bittel_layout_1_signage_item,arrayList);
    }

    @Override
    public ListView getListView() {
        return lv_list;
    }

    @Override
    public int setDataSource() {
        return AppDataSource.RAW;
    }
    //==============================================================================================
}
