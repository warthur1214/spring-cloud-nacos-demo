package com.warthur.nacos.demo.infrastructure.po;

import lombok.Data;

/**
 * @author warthur
 * @date 2021/03/18
 */
@Data
public class JobEntity {

    private String schedName;
    private String jobName;
    private String jobGroup;
    private String description;
    private String jobClassName;
    private String isDurable;
    private String isNonConcurrent;
    private String requestRecovery;
    private String jobData;
}
