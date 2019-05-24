package com.gungunpriatna.myunittesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {
    @BindView(R.id.edt_height)
            EditText edtHeight;
    @BindView(R.id.edt_length)
            EditText edtLength;
    @BindView(R.id.edt_width)
            EditText edtWidth;
    @BindView(R.id.tv_result)
            TextView tvResult;
    @BindView(R.id.btn_calculate)
            Button btnCalculate;

    private static final String STATE_RESULT = "state_result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        final MainPresenter presenter = new MainPresenter(this);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String length = edtLength.getText().toString().trim();
                String width = edtWidth.getText().toString().trim();
                String height = edtHeight.getText().toString().trim();

                boolean isEmptyFields = false;

                if (TextUtils.isEmpty(length)) {
                    isEmptyFields = true;
                    edtLength.setError("Field ini tidak boleh kosong");
                }

                if (TextUtils.isEmpty(width)) {
                    isEmptyFields = true;
                    edtWidth.setError("Field ini tidak boleh kosong");
                }

                if (TextUtils.isEmpty(height)) {
                    isEmptyFields = true;
                    edtHeight.setError("Field ini tidak boleh kosong");
                }

                if (! isEmptyFields) {
                    double l = Double.parseDouble(length);
                    double w = Double.parseDouble(width);
                    double h = Double.parseDouble(height);

                    presenter.calculateVolume(l,w,h);
                }
            }
        });

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        outstate.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    public void showVolume(MainModel model) {
        tvResult.setText(String.valueOf(model.getVolume()));

    }
}
