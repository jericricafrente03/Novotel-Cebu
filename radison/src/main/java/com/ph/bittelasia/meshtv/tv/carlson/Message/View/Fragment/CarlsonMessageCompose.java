package com.ph.bittelasia.meshtv.tv.carlson.Message.View.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.SessionManager;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.MeshTVDialog;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Message.MeshMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ramil on 1/4/18.
 */

public class CarlsonMessageCompose extends MeshTVDialog {

    SessionManager session;

    EditText sender;
    EditText subject;
    EditText body;
    Button   send;

    double height;
    double width;

    boolean  valid=false;

    public static CarlsonMessageCompose m;

    public static CarlsonMessageCompose dialog(double h, double w)
    {
        m=new CarlsonMessageCompose();
        m.height=h;
        m.width=w;
        return m;
    }


    @Override
    public void setIDs(View view) {
        sender  =(EditText)view.findViewById(R.id.et_sender);
        subject =(EditText)view.findViewById(R.id.et_subject);
        body    =(EditText)view.findViewById(R.id.et_body);
        send    =(Button)view.findViewById(R.id.btn_send);
    }

    @Override
    public void setContent() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valid=isValid(sender,subject,body);
                if(valid)
                {
                    session=new SessionManager(getActivity());
                    session.setMessageID(session.getMessageID()+1);
                    MeshMessage meshMessage=new MeshMessage();
                    meshMessage.setId(session.getMessageID()==0?1:session.getMessageID());
                    meshMessage.setMessg_from(getText(sender));
                    meshMessage.setMessg_text(getText(body));
                    meshMessage.setMessg_datetime((new Date()+""));
                    meshMessage.setMessg_subject(getText(subject));
                    meshMessage.setRoom_id("1121A");
                    meshMessage.setMessg_status(0);
                    MeshRealmManager.insert(meshMessage);
                    Toast.makeText(getActivity(), "message has been sent", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
                else
                {
                    Toast.makeText(getActivity(), "fields must not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int setLayout() {
        return R.layout.carlson_create_message;
    }

    @Override
    public int setConstraintLayout() {
        return R.id.cl_layout;
    }

    @Override
    public double setPercentageWidth() {
        return width;
    }

    @Override
    public double setPercentageHeight() {
        return height;
    }

    // 0 - unread
    // 1 - kanina pa inbox
    // 2 - read
    //  MeshRealmManager.delete(meshMessage);
    // MeshTVDataManager-> kuha galing sa source, forward sa meshrealm manager
}
