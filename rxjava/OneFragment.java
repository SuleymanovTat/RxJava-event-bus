package ru.ileanpro.riatworker.rxjava;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ru.ileanpro.riatworker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment implements View.OnClickListener {


    public OneFragment() {
        // Required empty public constructor
    }

    private EditText message;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        message = view.findViewById(R.id.edText);
        textView = view.findViewById(R.id.tvText);
        Button btnSendOne = view.findViewById(R.id.btnSend);
        btnSendOne.setOnClickListener(this);
        RxEventBus.instanceOf().getEventsOne().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String message) {
                textView.setText(message);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        RxEventBus.instanceOf().setString(message.getText().toString());
    }
}
