package com.software.hearth.ppehelp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.software.hearth.ppehelp.databinding.ActivityChooseNameBinding;

import java.io.Serializable;

public class ChooseNameActivity extends AppCompatActivity implements ChooseNameListAdapter.OnChooseNameListener {

    ActivityChooseNameBinding binding;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setFinishOnTouchOutside(false);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_name);

        binding.recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        adapter = new ChooseNameListAdapter(new String[]{"Julien", "Jean", "Yves"});
        ((ChooseNameListAdapter) adapter).setListener(this);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onChooseNameClicked(String name) {
        Intent intent = new Intent();
        intent.putExtra("message_return", name);
        setResult(RESULT_OK, intent);
        finish();
    }
}
