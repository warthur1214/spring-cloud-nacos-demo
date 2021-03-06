package com.warthur.nacos.demo.application.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author warthur
 * @date 2021/03/18
 */
@DisallowConcurrentExecution
@Slf4j
@Component
public class TestJob extends QuartzJobBean {

    @Override
    public void executeInternal(JobExecutionContext context) {

        // log.info("dubbo远程调用：{}", testService.getConfig());
        // log.info("Spring自动装配：{}", testService2.getConfig());

        log.info("定时任务执行，当前时间：{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }
}
