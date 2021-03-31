package com.warthur.nacos.demo.interfaces.facade;

import com.warthur.nacos.demo.infrastructure.config.annotation.SignAuthExclude;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author warthur
 * @date 2021/03/18
 */
@RestController
@Slf4j
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private Scheduler scheduler;


    @PostMapping("/tasks")
    @SignAuthExclude
    public String addTasks() throws SchedulerException {

        // JobEntity jobEntity = jobDAO.findOne();

        //构建job信息
        // JobDetail job = JobBuilder.newJob(TestJob.class)
        //         .withIdentity(RandomStringUtils.randomAlphabetic(10), "default")
        //         .withDescription("描述")
        //         .build();
        //
        // // 触发时间点
        // CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        // Trigger trigger = TriggerBuilder.newTrigger()
        //         .withIdentity("trigger-" + RandomStringUtils.randomAlphabetic(10), "default")
        //         .withDescription("描述")
        //         .startNow()
        //         .withSchedule(cronScheduleBuilder)
        //         .build();
        //
        // //交由Scheduler安排触发
        // scheduler.scheduleJob(job, trigger);

        scheduler.resumeTrigger(new TriggerKey("trigger-000010"));

        return "success";
    }
}
