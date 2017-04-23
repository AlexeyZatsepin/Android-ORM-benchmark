package com.study.benchmarkorm;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.db.chart.Tools;
import com.db.chart.animation.Animation;
import com.db.chart.model.LineSet;
import com.db.chart.renderer.AxisRenderer;
import com.db.chart.view.LineChartView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasicFragment extends Fragment {

    @Bind(R.id.play_full)
    ImageButton mPlayBtnFull;

    @Bind(R.id.play_read)
    ImageButton mPlayBtnRead;
    @Bind(R.id.chart_read)
    LineChartView mChartRead;

    @Bind(R.id.play_write)
    ImageButton mPlayBtnWrite;
    @Bind(R.id.chart_write)
    LineChartView mChartWrite;

    @Bind(R.id.play_update)
    ImageButton mPlayBtnUpdate;
    @Bind(R.id.chart_update)
    LineChartView mChartUpdate;

    @Bind(R.id.play_delete)
    ImageButton mPlayBtnDelete;
    @Bind(R.id.chart_delete)
    LineChartView mChartDelete;

    @Bind(R.id.tv_read)
    TextView tv_read;
    @Bind(R.id.tv_write)
    TextView tv_write;
    @Bind(R.id.tv_update)
    TextView tv_update;
    @Bind(R.id.tv_delete)
    TextView tv_delete;

    @Bind(R.id.tv_read_result)
    TextView tv_read_result;
    @Bind(R.id.tv_write_result)
    TextView tv_write_result;
    @Bind(R.id.tv_update_result)
    TextView tv_update_result;
    @Bind(R.id.tv_delete_result)
    TextView tv_delete_result;

    private boolean isPlayedR = false;
    private boolean isPlayedW = false;
    private boolean isPlayedU = false;
    private boolean isPlayedD = false;

    private int current = 0;
    private static final String ARG = "section";
    private final String[] mLabels = {"1", "2", "3", "4", "5", "6", "7", "8", "9","10"};
    private float[] mValues = new float[10];

    private ORMTest ormTest;

    public BasicFragment() {
    }

    public static BasicFragment newInstance(int section) {
        BasicFragment fragment = new BasicFragment();
        Bundle args = new Bundle();
        args.putInt(ARG, section);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_block, container, false);
        ButterKnife.bind(this,root);
        current = getArguments().getInt(ARG);
        ormTest = new ORMTestImpl(getActivity().getApplicationContext());
        switch (current){
            case 0:
                tv_read.setText(R.string.simple_read);
                tv_write.setText(R.string.simple_write);
                tv_update.setText(R.string.simple_update);
                tv_delete.setText(R.string.simple_delete);
                break;
            case 1:
                tv_read.setText(R.string.balanced_read);
                tv_write.setText(R.string.balanced_write);
                tv_update.setText(R.string.balanced_update);
                tv_delete.setText(R.string.balanced_delete);
                break;
            case 2:
                tv_read.setText(R.string.complex_read);
                tv_write.setText(R.string.complex_write);
                tv_update.setText(R.string.complex_update);
                tv_delete.setText(R.string.complex_delete);
                break;
        }
        show(mChartWrite,BuildConfig.expectedWrite);
        show(mChartRead,BuildConfig.expectedRead);
        show(mChartUpdate,BuildConfig.expectedUpdate);
        show(mChartDelete,BuildConfig.expectedDelete);
        return root;
    }

    @OnClick(R.id.play_full)
    public void play(){
        ormTest.warmingUp();
        playWrite();
        playRead();
        playUpdate();
        playDelete();
    }

    @OnClick(R.id.play_write)
    public void playWrite(){
        switch (current){
            case 0:
                mValues = ormTest.writeSimple();
                break;
            case 1:
                mValues = ormTest.writeComplex();
                break;
            case 2:
                mValues = ormTest.writeBalanced();
                break;
        }
        tv_write_result.setText(formatResult(mValues));
        if(isPlayedW){
            lock();
            mChartWrite.dismissAllTooltips();
            mChartWrite.dismiss(new Animation().setEndAction(showAction(mChartWrite,mValues)));
        }else{
            lock();
            mPlayBtnWrite.setImageResource(R.drawable.ic_refresh);
            mChartWrite.dismissAllTooltips();
            mChartWrite.updateValues(0, mValues);
            mChartWrite.notifyDataUpdate();
            isPlayedW = true;
        }
    }

    @OnClick(R.id.play_read)
    public void playRead(){
        switch (current){
            case 0:
                mValues = ormTest.readSimple();
                break;
            case 1:
                mValues = ormTest.readComplex();
                break;
            case 2:
                mValues = ormTest.readBalanced();
                break;
        }
        tv_read_result.setText(formatResult(mValues));
        if(isPlayedR){
            lock();
            mChartRead.dismissAllTooltips();
            mChartRead.dismiss(new Animation().setEndAction(showAction(mChartRead,mValues)));
        }else{
            lock();
            mPlayBtnRead.setImageResource(R.drawable.ic_refresh);
            mChartRead.dismissAllTooltips();
            mChartRead.updateValues(0, mValues);
            mChartRead.notifyDataUpdate();
            isPlayedR = true;
        }
    }

    @OnClick(R.id.play_update)
    public void playUpdate(){
        switch (current){
            case 0:
                mValues = ormTest.updateSimple();
                break;
            case 1:
                mValues = ormTest.updateComplex();
                break;
            case 2:
                mValues = ormTest.updateBalanced();
                break;
        }
        tv_update_result.setText(formatResult(mValues));
        if(isPlayedU){
            lock();
            mChartUpdate.dismissAllTooltips();
            mChartUpdate.dismiss(new Animation().setEndAction(showAction(mChartUpdate,mValues)));
        }else{
            lock();
            mPlayBtnUpdate.setImageResource(R.drawable.ic_refresh);
            mChartUpdate.dismissAllTooltips();
            mChartUpdate.updateValues(0, mValues);
            mChartUpdate.notifyDataUpdate();
            isPlayedU = true;
        }
    }

    @OnClick(R.id.play_delete)
    public void playDelete(){
        switch (current){
            case 0:
                mValues = ormTest.deleteSimple();
                break;
            case 1:
                mValues = ormTest.deleteComplex();
                break;
            case 2:
                mValues = ormTest.deleteBalanced();
                break;
        }
        tv_delete_result.setText(formatResult(mValues));
        if(isPlayedD){
            lock();
            mChartDelete.dismissAllTooltips();
            mChartDelete.dismiss(new Animation().setEndAction(showAction(mChartDelete,mValues)));
        }else{
            lock();
            mPlayBtnDelete.setImageResource(R.drawable.ic_refresh);
            mChartDelete.dismissAllTooltips();
            mChartDelete.updateValues(0, mValues);
            mChartDelete.notifyDataUpdate();
            isPlayedD = true;
        }
    }


    protected void show(LineChartView chart,float[] values) {
        lock();
        LineSet dataset = new LineSet(mLabels, values);
        dataset.setColor(Color.parseColor("#53c1bd"))
                .setFill(Color.parseColor("#3d6c73"))
                .setGradientFill(new int[] {Color.parseColor("#364d5a"), Color.parseColor("#3f7178")},
                        null);
        chart.addData(dataset);
        chart.setBorderSpacing(1)
                .setXLabels(AxisRenderer.LabelPosition.NONE)
                .setYLabels(AxisRenderer.LabelPosition.NONE)
                .setXAxis(false)
                .setYAxis(false)
                .setBorderSpacing(Tools.fromDpToPx(5));

        Animation anim = new Animation().setEndAction(new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        unlock();
                    }
                }, 500);
            }
        });
        chart.show(anim);
    }

    private void lock() {
        mPlayBtnWrite.setEnabled(false);
        mPlayBtnRead.setEnabled(false);
        mPlayBtnUpdate.setEnabled(false);
        mPlayBtnDelete.setEnabled(false);
        mPlayBtnFull.setEnabled(false);
    }


    private void unlock() {
        mPlayBtnWrite.setEnabled(true);
        mPlayBtnRead.setEnabled(true);
        mPlayBtnUpdate.setEnabled(true);
        mPlayBtnDelete.setEnabled(true);
        mPlayBtnFull.setEnabled(true);
    }

    private Runnable showAction(final LineChartView view,final float[] values){
        return new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        show(view,values);
                    }
                }, 500);
            }
        };
    }

    public String formatResult(float[] array){
        float sum=0;
        for (float el : array) {
            sum += el;
        }
        return (int)sum/array.length + "ms";
    }
}
