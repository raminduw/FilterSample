package com.ramindu.weeraman.filter.sample.utils;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler getMainScheduler();
    Scheduler getBackgroundScheduler();
}
