package com.warthur.nacos.demo.infrastructure.po;

import lombok.Data;

/**
 * @author warthur
 * @date 2021/03/18
 */
@Data
public class CronTriggerEntity {

    private String shedName;
    private String triggerName;
    private String triggerGroup;
    private String cronExpression;
    private String timeZoneId;
}
