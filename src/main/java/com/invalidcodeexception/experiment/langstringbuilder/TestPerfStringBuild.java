package com.invalidcodeexception.experiment.langstringbuilder;

import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;
import com.invalidcodeexception.experiment.langstringbuilder.lang3.UnsafeToStringBuilder;
import com.invalidcodeexception.experiment.langstringbuilder.lang3.UnsafeToStringStyle;
import org.apache.commons.lang3.builder.*;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestPerfStringBuild extends SimpleBenchmark {

    private static int NB_THREAD_POOL = 5;
    private static int NB_THREAD = 100;

    public void timeLogStringBuilder(int reps) {
        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < MyObject.testObjects.length; j++) {
                MyObject o = MyObject.testObjects[j];

                UnsafeToStringBuilder builder = new UnsafeToStringBuilder(o, new UnsafeToStringStyle() {
                });
                builder.append("field1", o.getField1());
                builder.append("field2", o.getField2());
                builder.append("field3", o.getField3());
            }
        }
    }

    public void timeLogStringBuffer(int reps) {
        for (int i = 0; i < reps; i++) {
            for (int j = 0; j < MyObject.testObjects.length; j++) {
                MyObject o = MyObject.testObjects[j];

                ToStringBuilder builder = new ToStringBuilder(o, ToStringStyle.DEFAULT_STYLE);
                builder.append("field1", o.getField1());
                builder.append("field2", o.getField2());
                builder.append("field3", o.getField3());
            }
        }
    }

    public void timeMultiThreadLogStringBuilder(int reps) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(NB_THREAD_POOL);
        for (int j = 0; j < reps; j++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < MyObject.testObjects.length; j++) {
                        MyObject o = MyObject.testObjects[j];

                        UnsafeToStringBuilder builder = new UnsafeToStringBuilder(o, new UnsafeToStringStyle() {
                        });
                        builder.append("field1", o.getField1());
                        builder.append("field2", o.getField2());
                        builder.append("field3", o.getField3());
                    }
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(20, TimeUnit.SECONDS);
    }

    public void timeMultiThreadLogStringBuffer(int reps) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(NB_THREAD_POOL);
        for (int j = 0; j < reps; j++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < MyObject.testObjects.length; j++) {
                        MyObject o = MyObject.testObjects[j];

                        ToStringBuilder builder = new ToStringBuilder(o, ToStringStyle.DEFAULT_STYLE);
                        builder.append("field1", o.getField1());
                        builder.append("field2", o.getField2());
                        builder.append("field3", o.getField3());
                    }
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(20, TimeUnit.SECONDS);
    }

    public void timeMultiThreadNewLogStringBuffer(int reps) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(NB_THREAD_POOL);
        for (int j = 0; j < reps; j++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < MyObject.testObjects.length; j++) {
                        MyObject o = MyObject.testObjects[j];

                        ToStringBuilder builder = new ToStringBuilder(o, new ToStringStyle() {
                        });
                        builder.append("field1", o.getField1());
                        builder.append("field2", o.getField2());
                        builder.append("field3", o.getField3());
                    }
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(20, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        System.out.println(MyObject.testObjects); // init testObjects
        Runner.main(TestPerfStringBuild.class, new String[]{"--warmupMillis", "5000", "--runMillis", "5000"});
    }

}

class MyObject {
    private final String field1;
    private final int field2;
    private final Object[] field3;
    public static final MyObject[] testObjects;

    static {
        testObjects = new MyObject[]{
                new MyObject("field1", 2, "field", "3", "iugacsgiuasgiusaiu", "hcgavusfaucsavusva", "", "wsx"),
                new MyObject("idgsiugidsdds", Integer.MIN_VALUE, new HashMap<String, String>() {{
                    put("aa", "bb");
                    put("dfsfs", "uycdsgfds");
                }}),
                new MyObject("AnOtherExample", Integer.MAX_VALUE),
                new MyObject("AnOtherExampleLonggg", 0, null, null)
        };
    }

    private MyObject(String field1, int field2, Object... field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;

    }

    public String getField1() {
        return field1;
    }

    public int getField2() {
        return field2;
    }

    public Object[] getField3() {
        return field3;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE);
        builder.append("field1", field1);
        builder.append("field2", field2);
        builder.append("field3", field3);
        return builder.build();
    }
}