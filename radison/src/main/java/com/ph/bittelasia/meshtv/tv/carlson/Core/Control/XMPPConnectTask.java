package com.ph.bittelasia.meshtv.tv.carlson.Core.Control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.ph.bittelasia.meshtv.tv.meshxmpplibrary.Core.Control.Manager.MeshXMPPManager;
import com.ph.bittelasia.meshtv.tv.meshxmpplibrary.Core.Control.Preference.MeshXMPPPreference;
import com.ph.bittelasia.meshtv.tv.meshxmpplibrary.Core.Model.MeshXMPPUser;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;

import org.jivesoftware.smack.ConnectionListener;

public class XMPPConnectTask extends AsyncTask<Void,Void,Void>
{
       @SuppressLint("StaticFieldLeak")
       private Context context;
       private ConnectionListener listener;

        public XMPPConnectTask(Context context,ConnectionListener listener)
        {
            this.context=context;
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {

                MeshXMPPPreference.enablePLAIN(context,true);
                MeshXMPPPreference.enableDIGESTMD5(context,true);
                MeshXMPPPreference.enableSCRAMSHA1(context,true);
                MeshXMPPPreference.setShouldReport(context,true);
                MeshXMPPPreference.setReportTo(context,"mars2");
                MeshXMPPUser user =
                        new MeshXMPPUser(
                                MeshTVPreferenceManager.getXUsername(context),
                                MeshTVPreferenceManager.getPassword(context),
                                true,
                                "mars2"
                        );
                user.setDomain("localhost");
                user.setHost(MeshTVPreferenceManager.getXMPPHost(context));
                user.setPort(MeshTVPreferenceManager.getXMPPPort(context));
                MeshXMPPManager.connect(context,listener,user);
            return null;
        }
    }