package ru.ileanpro.riatworker.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.ileanpro.riatworker.R;

public class RxjavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        getSupportFragmentManager().beginTransaction().add(R.id.containerOne, new OneFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.containerTwo, new TwoFragment()).commit();
    }
}
