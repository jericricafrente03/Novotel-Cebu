package com.ph.bittelasia.mesh.tv.libFragment.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ph.bittelasia.mesh.tv.libFragment.control.annotation.AttachActivityFragment;
import com.ph.bittelasia.mesh.tv.libFragment.control.annotation.ActivityLayout;
import com.ph.bittelasia.mesh.tv.libFragment.control.annotation.Title;
import com.ph.bittelasia.mesh.tv.libFragment.control.annotation.UpdateContents;
import com.ph.bittelasia.mesh.tv.libFragment.view.fragment.BasicFragment;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public abstract class HotelActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(checkLayout(this));
            initializeObject(this);
            updateObject(this);
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.e(TAG, "onAttachFragment: "+fragment.getClass().getSimpleName());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private int checkLayout(Object object) {
        if (object==null) {
            throw new RuntimeException(this.getClass().getSimpleName()+"The object to layout is null");
        }
        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(ActivityLayout.class)) {
            throw new RuntimeException(clazz.getSimpleName() + " is not annotated with Layout");
        }
        else
        {
            return Objects.requireNonNull(clazz.getAnnotation(ActivityLayout.class)).value();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initializeObject(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(AttachActivityFragment.class)) {
                 int container = Objects.requireNonNull(field.getAnnotation(AttachActivityFragment.class)).containerID();
                 if(field.get(object) instanceof BasicFragment) {
                     BasicFragment fragment = (BasicFragment) field.get(object);
                     assert fragment != null;
                     attachFragment(fragment, container);
                 }else{
                     throw new RuntimeException(TAG+": instance is not a subclass of BasicFragment");
                 }
            }else if(field.isAnnotationPresent(Title.class)){
                if(field.getType()== TextView.class)
                    field.set(object,(TextView)findViewById(field.getAnnotation(Title.class).containerID()));
            }
        }
    }

    private void updateObject(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(UpdateContents.class)) {
                method.setAccessible(true);
                Log.e(TAG, "updateObject: "+ method.getName());
                try {
                    method.invoke(object, "Steward");
                }catch (Exception e){
                    method.invoke(object);
                }

            }
        }
    }

    public <T extends BasicFragment>void attachFragment(@NonNull T player, int containerID) {
        if(containerID==0)
            throw new RuntimeException(player.getClass().getSimpleName() + " -> Must use non-zero containerViewId");
        getSupportFragmentManager().beginTransaction().replace(
                containerID,
                player,
                player.getClass().getSimpleName()).
                commitAllowingStateLoss();
    }

    public abstract void initialize();

}
