package com.study.benchmarkorm;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.ModelAdapter;


public class BenchmarkApplication extends android.app.Application {

//    public BenchmarkApplication() {
//        //api init
//        Gson gson = new GsonBuilder()
//                .setExclusionStrategies(new DBFlowExclusionStrategy())
//                .create();
//
//    }

    @Override
    public void onCreate() {
        super.onCreate();

        //DBFlow instantiation
        FlowManager.init(new FlowConfig.Builder(this).build());
    }


    static class DBFlowExclusionStrategy implements ExclusionStrategy {

        // Otherwise, Gson will go through base classes of DBFlow models
        // and hang forever.
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return f.getDeclaredClass().equals(ModelAdapter.class) || f.getName().equals("_id");
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    }


}
