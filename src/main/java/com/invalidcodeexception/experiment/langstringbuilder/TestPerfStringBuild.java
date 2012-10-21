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

    private static Runnable WITH_STRING_BUFFER = new Runnable() {
        @Override
        public void run() {
            for (int j = 0; j < MyObject.testObjects.length; j++) {
                MyObject o = MyObject.testObjects[j];
                new ToStringBuilder(o, ToStringStyle.DEFAULT_STYLE)
                        .append("field1", o.getField1()).append("field2", o.getField2()).append("field3", o.getField3()).build();
            }
        }
    };
    private static Runnable WITH_STRING_BUILDER = new Runnable() {
        @Override
        public void run() {
            for (int j = 0; j < MyObject.testObjects.length; j++) {
                MyObject o = MyObject.testObjects[j];
                new UnsafeToStringBuilder(o, new UnsafeToStringStyle(){})
                        .append("field1", o.getField1()).append("field2", o.getField2()).append("field3", o.getField3()).build();
            }
        }
    };

    private static void sequentialRun(int reps, Runnable r){ for (int i = 0; i < reps; i++) r.run(); }
    private static void multiThreadRun(int reps, Runnable r) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(NB_THREAD_POOL);
        for (int i = 0; i < reps; i++) pool.submit(r);
        pool.shutdown();
        pool.awaitTermination(20, TimeUnit.SECONDS);
    }

    public void timeLogStringBuffer(int reps) {
        sequentialRun(reps, WITH_STRING_BUFFER);
    }

    public void timeLogStringBuilder(int reps) {
        sequentialRun(reps, WITH_STRING_BUILDER);
    }

    public void timeMultiThreadLogStringBuffer(int reps) throws InterruptedException {
        multiThreadRun(reps, WITH_STRING_BUILDER);
    }

    public void timeMultiThreadLogStringBuilder(int reps) throws InterruptedException {
        multiThreadRun(reps, WITH_STRING_BUILDER);
    }

    public static void main(String[] args) {
        System.out.println(MyObject.testObjects); // init testObjects
        Runner.main(TestPerfStringBuild.class, new String[]{"--warmupMillis", "3000", "--runMillis", "5000"});
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
                new MyObject("idgsiugidsdds", Integer.MIN_VALUE, new HashMap<String, String>() {{ put("aa", "bb"); put("dfsfs", "uycdsgfds");}}),
                new MyObject("AnOtherExample", Integer.MAX_VALUE),
                new MyObject("AnOtherExampleLonggg", 0, null, null)
        };
    }

    private MyObject(String field1, int field2, Object... field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {return field1;}
    public int getField2() { return field2; }
    public Object[] getField3() { return field3; }
}