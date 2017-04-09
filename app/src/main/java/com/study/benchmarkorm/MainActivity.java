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

    @Bind(R.id.tv_write_simple)
    TextView tvWriteSimple;

    @Bind(R.id.tv_write_complex)
    TextView tvWriteComplex;

    @Bind(R.id.tv_write_balanced)
    TextView tvWriteBalanced;

    @Bind(R.id.tv_read_simple)
    TextView tvReadSimple;

    @Bind(R.id.tv_read_complex)
    TextView tvReadComplex;

    @Bind(R.id.tv_read_balanced)
    TextView tvReadBalanced;

    @Bind(R.id.tv_update_simple)
    TextView tvUpdateSimple;

    @Bind(R.id.tv_update_complex)
    TextView tvUpdateComplex;

    @Bind(R.id.tv_update_balanced)
    TextView tvUpdateBalanced;

    @Bind(R.id.tv_delete_simple)
    TextView tvDeleteSimple;

    @Bind(R.id.tv_delete_complex)
    TextView tvDeleteComplex;

    @Bind(R.id.tv_delete_balanced)
    TextView tvDeleteBalanced;

    private ORMTest ormTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ormTest = new ORMTestImpl();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_write_simple)
    public void testWriteSimple(){
        tvWriteSimple.setText(String.format("%dms", ormTest.writeSimple()) + "ms");
    }

    @OnClick(R.id.button_write_complex)
    public void testWriteComplex(){
        tvWriteComplex.setText(String.format("%dms", ormTest.writeComplex()) + "ms");
    }

    @OnClick(R.id.button_write_balanced)
    public void testWriteBalanced(){
        tvWriteBalanced.setText(String.format("%dms", ormTest.writeBalanced()) + "ms");
    }

    @OnClick(R.id.button_read_simple)
    public void testReadSimple(){
        tvReadSimple.setText(String.format("%dms", ormTest.readSimple()) + "ms");
    }

    @OnClick(R.id.button_read_complex)
    public void testReadComplex(){
        tvReadComplex.setText(String.format("%dms", ormTest.readComplex()) + "ms");
    }

    @OnClick(R.id.button_read_balanced)
    public void testReadBalanced(){
        tvReadBalanced.setText(String.format("%dms", ormTest.readBalanced()) + "ms");
    }

    @OnClick(R.id.button_update_simple)
    public void testUpdateSimple(){
        tvUpdateSimple.setText(String.format("%dms", ormTest.updateSimple()) + "ms");
    }

    @OnClick(R.id.button_update_complex)
    public void testUpdateComplex(){
        tvUpdateComplex.setText(String.format("%dms", ormTest.updateComplex()) + "ms");
    }

    @OnClick(R.id.button_update_balanced)
    public void testUpdateBalanced(){
        tvUpdateBalanced.setText(String.format("%dms", ormTest.updateBalanced()) + "ms");
    }

    @OnClick(R.id.button_delete_simple)
    public void testDeleteSimple(){
        tvDeleteSimple.setText(String.format("%dms", ormTest.deleteSimple()) + "ms");
    }

    @OnClick(R.id.button_delete_complex)
    public void testDeleteComplex(){
        tvDeleteComplex.setText(String.format("%dms", ormTest.writeComplex()) + "ms");
    }

    @OnClick(R.id.button_delete_balanced)
    public void testDeleteBalanced(){
        tvDeleteBalanced.setText(String.format("%dms", ormTest.deleteBalanced()) + "ms");
    }
}
