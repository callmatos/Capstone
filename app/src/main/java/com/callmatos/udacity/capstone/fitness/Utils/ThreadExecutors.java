package com.callmatos.udacity.capstone.fitness.Utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadExecutors {

    //Lock object
    private static final Object LOCK = new Object();

    //Singleton
    private static ThreadExecutors sInstance;

    //Object Thread.
    private final Executor disIO;
    private final Executor mainThread;
    private final Executor networkIO;

    //Constructor to pass the Executores.
    private ThreadExecutors(Executor disIO, Executor networkIO, Executor mainThread){
        this.disIO = disIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    public static ThreadExecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new ThreadExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor getDisIO() {
        return disIO;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    public Executor getNetworkIO() {
        return networkIO;
    }

    //Intern class to represent executor on MainThread.
    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
