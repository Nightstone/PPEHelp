package com.software.hearth.ppehelp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.software.hearth.ppehelp.databinding.ActivityConstraintBinding;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ConstraintActivity extends AppCompatActivity implements ChooseDialogFragment.ChooseDialogFragmentInterface {

    ActivityConstraintBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_constraint);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/typicode/demo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebServicesInterface webServicesInterface = retrofit.create(WebServicesInterface.class);

        Call<List<Post>> call = webServicesInterface.listPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Post post = response.body().get(0);
                binding.idPostTextView.setText(getString(R.string.idPost, post.getId()));
                binding.namePostTextView.setText(getString(R.string.titlePost, post.getTitle()));
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("JLE", "Ca passe pas");
            }
        });
    }

    public interface WebServicesInterface {
        @GET("posts")
        Call<List<Post>> listPosts();
    }

    public void onModalButtonClick(View view) {
        ChooseDialogFragment fragment = new ChooseDialogFragment();
        fragment.setDataInterface(this);
        fragment.show(getSupportFragmentManager(), "ChooseModal");
    }

    public void onModalActivityButtonClick(View view) {
        Intent intent = new Intent(this, ChooseNameActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onChooseDone(String choose) {
        binding.chooseModalTextView.setText(getString(R.string.chooseName, choose));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            binding.chooseModalActivityTextView.setText(data.getExtras().getString("message_return"));
        }
    }
}


