package com.ph.bittelasia.meshtv.tv.carlson.TV.View.Activity;

import android.content.Intent;
import android.os.CountDownTimer;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Activity.CarlsonActivity;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.TV.Control.FixedArrayList;
import com.ph.bittelasia.meshtv.tv.carlson.TV.View.Fragment.CarlsonTVCategory;
import com.ph.bittelasia.meshtv.tv.carlson.TV.View.Fragment.CarlsonTVList;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshArrayListCallBack;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Activity.CarlsonFullScreen;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonChannelLabel;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonPreview;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.AttachFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.RemoteControl.KR301KeyCode;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Channel.MeshChannel;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Channel.MeshChannelCategory;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ramil on 2/7/18.
 */
@Layout(R.layout.carlson_tv_layout)
@ActivitySetting()
public class CarlsonTV extends CarlsonActivity implements MeshTVFragmentListener,MeshArrayListCallBack<MeshChannel> {

    public static final String TAG=CarlsonTV.class.getSimpleName();

    CarlsonTVCategory       category;
    CarlsonTVList           tvList;
    CarlsonPreview          preview;
    CarlsonChannelLabel     tvLabel;
    Object                  object;
    boolean                 loaded=false;
    ArrayList<MeshChannel>  channelList;
    MeshChannelCategory     meshCategory;
    MeshChannel             meshChannel;
    CountDownTimer          countDownTimer;
    VelocityTracker         tracker;
    int                     channelCount;


    boolean tv=false;
    int tvId;
    int lastTvId;

    public void setTvId(int tvId) {
        this.tvId = tvId;
    }

    public int getTvId() {
        return tvId;
    }

    public void setLastTvId(int lastTvId) {
        this.lastTvId = lastTvId;
    }

    public int getLastTvId() {
        return lastTvId;
    }

    @BindWidget(R.id.ll_display)
    public LinearLayout ll_display;

    @Override
    public void onResume() {
        super.onResume();
        loaded=false;
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        try
        {
            switch (event.getAction()) {
                case KeyEvent.KEYCODE_BACK:
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        preview.getPosition();
                    } else {
                        super.onBackPressed();
                    }
                    return true;
                case KeyEvent.ACTION_DOWN:
                    switch (KR301KeyCode.getEquivalent(event.getKeyCode())) {
                        case KeyEvent.KEYCODE_DPAD_RIGHT:
                        case 92: //channel up
                                try {
                                    if (tvLabel != null) {
                                        channelCount++;
                                        if(channelCount < channelList.size()) {
                                            setTvId(channelList.get(channelCount).getId());
                                        }else
                                        {
                                            channelCount=0;
                                            setTvId(channelList.get(channelCount).getId());
                                        }
                                        Log.i("steward", "channel up:  " + getTvId());
                                        tvLabel.setSelected(getTvId());
                                        tvList.gv_grid.setSelection(channelCount);
                                        tvList.gv_grid.setItemChecked(channelCount,true);
                                        tvList.gv_grid.requestFocus();
                                    }
                                } catch (Exception e) {
                                     e.printStackTrace();
                                }
                            return true;
                        case KeyEvent.KEYCODE_DPAD_LEFT:
                        case 93: //channel down
                                try {
                                    if (tvLabel != null) {
                                        channelCount--;
                                        if(channelCount > -1) {
                                            setTvId(channelList.get(channelCount).getId());
                                            Log.i("steward", "channel down if:  " + getTvId());
                                        }else
                                        {
                                            channelCount=channelList.size()-1;
                                            setTvId(channelList.get(channelCount).getId());
                                            Log.i("steward", "channel down else:  " + getTvId());
                                        }
                                        tvLabel.setSelected(getTvId());
                                        tvList.gv_grid.setSelection(channelCount);
                                        tvList.gv_grid.setItemChecked(channelCount,true);
                                        tvList.gv_grid.requestFocus();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            return true;
                        case KeyEvent.KEYCODE_DPAD_UP:
                            category.lv_categories.requestFocus();
                            return true;
                        case KeyEvent.KEYCODE_DPAD_DOWN:
                            return true;

                    }
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return super.dispatchKeyEvent(event);

    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        int action = event.getActionMasked();
        int x=0;
        try {
            switch (action) {
                case MotionEvent.ACTION_HOVER_ENTER:
                    if (tracker == null) {
                        tracker = VelocityTracker.obtain();
                    } else {
                        tracker.clear();
                    }
                    tracker.addMovement(event);
                    break;
                case MotionEvent.ACTION_HOVER_MOVE:
                    if(tracker!=null) {
                        tracker.addMovement(event);
                        tracker.computeCurrentVelocity(1000);
                        if (countDownTimer != null) {
                            countDownTimer.cancel();
                            showFragment(tvList, true);
                        } else {
                            countDownTimer = new CountDownTimer(20000, 1000) {
                                @Override
                                public void onTick(long l) {
//                                    Log.i("steward", "ticked: " + l);
                                }

                                @Override
                                public void onFinish() {
                                    showFragment(tvList, false);
                                    Log.i("steward", "finished: ");
                                }
                            };
                        }
                        countDownTimer.start();
                    }
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                case MotionEvent.ACTION_DOWN:
                    x++;
                    Log.i("steward","moving: "+x);
                    break;
                case MotionEvent.ACTION_HOVER_EXIT:
                    Log.i("steward","exiting: ");
                    break;
                case MotionEvent.ACTION_CANCEL:
                    tracker.recycle();
                    break;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return super.onGenericMotionEvent(event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClicked(Object o) {
        if(o instanceof MeshChannel)
        {
            Intent i = new Intent(this, CarlsonFullScreen.class);
            i.putExtra("CHANNEL", ((MeshChannel)o).getId());
            i.putExtra("MEDIA","CHANNEL");
            i.putExtra("ID",channelList.get(channelList.size()-1).getId());
            this.object=o;
            startActivityForResult(i,100);
            Log.i("steward","channel: onclicked "+((MeshChannel)o).getChannel_uri());
        }
        if (o instanceof MeshChannelCategory)
        {
            meshCategory = (MeshChannelCategory) o;
            tvList.setCategory(meshCategory.getId());
//            Log.i("steward", "channel notified clicked");
        }
    }

    @Override
    public void onSelected(Object o) {
        try
        {
            if (!loaded)
            {
                if (o instanceof MeshChannelCategory)
                {
                    meshCategory = (MeshChannelCategory) o;
                    tvList.setCategory(meshCategory.getId());
                    loaded = true;
                    Log.i("steward", "channel notified ok");
                }
            }
            else
            {
                tvList.adapterV2.notifyDataSetChanged();
                tvList.gv_grid.invalidateViews();
                Log.i("steward", "channel notified");
            }
            if (o instanceof MeshChannel)
            {
                meshChannel=(MeshChannel)o;
                if (preview == null)
                {
                    preview = CarlsonPreview.preview(o, 0);
                    getSupportFragmentManager().beginTransaction().add(R.id.ll_display, preview, "display").commit();
                }
                else
                {
                    preview = CarlsonPreview.preview(o, 0);
                    getSupportFragmentManager().beginTransaction().replace(R.id.ll_display, preview, "display").commit();
                }
                tvLabel.tv_channel.setText(meshChannel.getChannel_title());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 100:
                switch (resultCode)
                {
                    case RESULT_OK:
                        preview=null;
                        preview = CarlsonPreview.preview(this.object, 0);
                        getSupportFragmentManager().beginTransaction().replace(R.id.ll_display, preview, "display").commit();
                        break;
                }
        }
    }

    @AttachFragment(container = R.id.ll_categories,tag="categories",order = 3)
    public Fragment attachCategory()
    {
        category=new CarlsonTVCategory();
        return  category;
    }

    @AttachFragment(container = R.id.ll_list,tag="tvList",order = 4)
    public Fragment attachList()
    {
        tvList =new CarlsonTVList();
        return tvList;
    }

    @AttachFragment(container = R.id.ll_label,tag="label",order=5)
    public Fragment attachLabel()
    {
        tvLabel=new CarlsonChannelLabel();
        return tvLabel;
    }
    @Override
    public void meshArrayList(ArrayList<MeshChannel> list) {
        try {
            channelList = list;
            setLastTvId(channelList.get(0).getId());
            setLastTvId(channelList.get(channelList.size() - 1).getId());

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void showFragment(Fragment fg,boolean b)
    {
        try {
            if(fg!=null) {
                if (b) {
                    if(!fg.isVisible())
                    getSupportFragmentManager()
                            .beginTransaction()
                            .show(fg)
                            .commitAllowingStateLoss();
                } else {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .hide(fg)
                            .commitAllowingStateLoss();
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
