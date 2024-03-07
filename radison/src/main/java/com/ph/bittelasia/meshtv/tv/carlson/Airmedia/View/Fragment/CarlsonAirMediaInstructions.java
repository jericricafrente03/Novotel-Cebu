package com.ph.bittelasia.meshtv.tv.carlson.Airmedia.View.Fragment;


import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.Airmedia.Model.CarlsonAirMediaListAdapter;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.AirMedia.Control.MeshTVAirmediaControl;
import com.ph.bittelasia.meshtv.tv.mtvlib.AirMedia.Model.MeshAirmediaInstructions;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;

import java.util.ArrayList;

/**
 * Created by ramil on 12/26/17.
 */
@Layout(R.layout.carlson_airmedia_instruction_layout)
public class CarlsonAirMediaInstructions extends MeshTVFragment {

    ArrayList<MeshAirmediaInstructions> instructions;
    public static CarlsonAirMediaInstructions fragment;

    private String platform;

    CarlsonAirMediaListAdapter adapter;


    @BindWidget(R.id.tv_credentials)
    public TextView tv_credentials;

    @BindWidget(R.id.tv_label)
    public TextView tv_label;


    @BindWidget(R.id.lv_instruction)
    public ListView lv_instruction;


    public static CarlsonAirMediaInstructions get(String platform)
    {
        fragment=new CarlsonAirMediaInstructions();
        fragment.platform=platform;
        return fragment;
    }

    public String getPlatform() {
        return platform;
    }

    public void setInstruction()
    {
        try
        {
            if(tv_label!=null)
            {
                tv_label.setText(getPlatform());
            }
            if (getPlatform().equals("ANDROID"))
            {
                MeshTVAirmediaControl.changeUsername(getContext(), "Carlson");
                MeshTVAirmediaControl.changePassword(getContext(), "Carlson");
                tv_credentials.setText(("Username: Carlson   |   Password: 123456"));
                instructions = MeshTVAirmediaControl.getAndroidInstructions(getContext(), true);
                adapter = new CarlsonAirMediaListAdapter(getContext(), lv_instruction, R.layout.carlson_airmedia_list_item_layout, instructions);
            }
            else if(getPlatform().equals("IOS"))
            {
                instructions = MeshTVAirmediaControl.getIOSInstructions(getContext(), true);
                adapter = new CarlsonAirMediaListAdapter(getContext(), lv_instruction, R.layout.carlson_airmedia_list_item_layout, instructions);
            }
            else
            {
                instructions = MeshTVAirmediaControl.getWindowsInstructions(getContext(), true);
                adapter = new CarlsonAirMediaListAdapter(getContext(), lv_instruction, R.layout.carlson_airmedia_list_item_layout, instructions);
            }
            lv_instruction.setAdapter(adapter);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
    }

    @Override
    protected void onDrawDone(View view) {
        setInstruction();
    }

    @Override
    protected void onDataUpdated(ArrayList arrayList) {

    }

    @Override
    protected void onNewData(Object o) {

    }

    @Override
    protected void onDataUpdated(Object o, int i) {

    }

    @Override
    protected void onDeleteData(int i) {

    }

    @Override
    protected void onClearData() {

    }

    @Override
    protected void onDataNotFound(Class aClass) {

    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void update(Object o) {

    }
}
