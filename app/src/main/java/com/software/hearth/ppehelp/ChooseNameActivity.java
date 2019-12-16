package com.software.hearth.ppehelp;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.software.hearth.ppehelp.databinding.ActivityChooseNameBinding;

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
