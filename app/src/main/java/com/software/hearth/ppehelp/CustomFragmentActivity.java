package com.software.hearth.ppehelp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CustomFragmentActivity extends AppCompatActivity implements DummyFragment.OnFragmentInteractionListener {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_fragment);

        textView = findViewById(R.id.textView2);
    }

    @Override
    public void onFragmentInteraction(String string) {
        textView.setText(string);
    }
}
