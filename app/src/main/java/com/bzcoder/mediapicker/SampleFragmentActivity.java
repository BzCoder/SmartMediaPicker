package com.bzcoder.mediapicker;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import me.bzcoder.mediapicker.R;

/**
 *
 * @author : BaoZhou
 * @date : 2019/3/26 11:43
 */
public class SampleFragmentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_fragment);
        SampleFragment fragment = new SampleFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.lay, fragment);
        transaction.commit();
    }


}
