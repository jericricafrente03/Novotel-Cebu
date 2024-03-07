package com.ph.bittelasia.meshtv.tv.carlson;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class TestActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.digital_signage_template12_layout);
        TestFragment tf_z1 = TestFragment.getFragment(R.layout.zone_1_test);
        TestFragment tf_z2 = TestFragment.getFragment(R.layout.zone_2_test);
        TestFragment tf_z3 = TestFragment.getFragment(R.layout.zone_3_test);
        TestFragment tf_z4 = TestFragment.getFragment(R.layout.zone_4_test);
        TestFragment tf_z5 = TestFragment.getFragment(R.layout.zone_5_test);
        getSupportFragmentManager().beginTransaction().add(R.id.ll_zone1,tf_z1,"TEST1").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.ll_zone2,tf_z2,"TEST2").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.ll_zone3,tf_z3,"TEST3").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.ll_zone4,tf_z4,"TEST4").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.ll_zone5,tf_z5,"TEST5").commit();
        }
    }
