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

        ormTest = new ORMTestImpl(getApplicationContext());
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_simple_first)
    public void testSimpleFirst(){
        ormTest.testSimplePartOne(tvWriteSimple);
    }

    @OnClick(R.id.button_simple_second)
    public void testSimpleSecond(){
        ormTest.testSimplePartTwo(tvReadSimple, tvUpdateSimple, tvDeleteSimple);
    }

    @OnClick(R.id.button_complex_first)
    public void testComplexFirst(){
        ormTest.testComplexPartOne(tvWriteComplex);
    }

    @OnClick(R.id.button_complex_second)
    public void testComplexSecond(){
        ormTest.testComplexPartTwo(tvReadComplex, tvUpdateComplex, tvDeleteComplex);
    }

    @OnClick(R.id.button_balanced_first)
    public void testBalancedFirst(){
        ormTest.testBalancedPartOne(tvWriteBalanced);
    }

    @OnClick(R.id.button_balanced_second)
    public void testBalancedSecond(){
        ormTest.testBalancedPartTwo(tvReadBalanced, tvUpdateBalanced, tvDeleteBalanced);
    }


}
