package app.quartz_demo.start;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class StartDemo {
    public static void main(String[] args) throws Exception{
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}



