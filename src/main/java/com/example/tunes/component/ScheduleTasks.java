package com.example.tunes.component;

import com.example.tunes.service.CachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTasks {

    @Autowired
    CachingService cachingService;

    @Scheduled(fixedRate = 60000)
    public void evictAllCachesAtIntervals() {
        //Clean caches every 60 seconds
        cachingService.evictAllCaches();
    }

}
