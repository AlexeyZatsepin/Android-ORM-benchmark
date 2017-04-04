package com.study.benchmarkorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.study.benchmarkorm.model.Person;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private TestController tc;

    @Bind(R.id.tv_read)
    TextView tvRead;

    @Bind(R.id.tv_write)
    TextView tvWrite;

    @Bind(R.id.tv_update)
    TextView tvUpdate;

    @Bind(R.id.tv_delete)
    TextView tvDelete;

    @Bind(R.id.et_count)
    EditText etCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.v("TAG", new Person().toString());
        tc = new IterableController();
        etCounter.setText(String.valueOf(1000));
    }

    @OnClick(R.id.button_write)
    public void testWrite(){
        tvWrite.setText(String.valueOf(tc.testWrite(Integer.valueOf(etCounter.getText().toString()))));
    }

    @OnClick(R.id.button_read)
    public void testRead(){
        tvRead.setText(String.valueOf(tc.testRead(1000)));
    }

    @OnClick(R.id.button_update)
    public void testUpdate(){
        tvUpdate.setText(String.valueOf(tc.testUpdate(1000)));
    }

    @OnClick(R.id.button_delete)
    public void testDelete(){
        tvDelete.setText(String.valueOf(tc.testDelete(1000)));
    }
}
