package com.ph.bittelasia.mesh.tv.libFragment.view.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.ph.bittelasia.mesh.tv.libFragment.control.annotation.FragmentLayout;
import com.ph.bittelasia.mesh.tv.libFragment.control.annotation.UpdateContents;

import java.lang.reflect.Method;
import java.util.Objects;

public abstract class BasicFragment extends Fragment
{

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) throws RuntimeException{
        return   inflater.inflate( checkLayout(this),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            getView(view);
            initializeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeObject(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(UpdateContents.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private int checkLayout(Object object) {
        if (object==null) {
            throw new RuntimeException(this.getClass().getSimpleName()+"The object to layout is null");
        }
        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(FragmentLayout.class)) {
            throw new RuntimeException(clazz.getSimpleName() + " is not annotated with Layout");
        }
        else
        {
            return Objects.requireNonNull(clazz.getAnnotation(FragmentLayout.class)).value();
        }
    }
    public abstract void getView(View v);
}
