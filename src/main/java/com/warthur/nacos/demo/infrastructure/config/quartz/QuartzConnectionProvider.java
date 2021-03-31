package com.warthur.nacos.demo.infrastructure.config.quartz;

import org.quartz.SchedulerException;
import org.quartz.utils.HikariCpPoolingConnectionProvider;

import java.sql.SQLException;
import java.util.Properties;

/**
 * @author warthur
 * @date 2021/03/31
 */
public class QuartzConnectionProvider extends HikariCpPoolingConnectionProvider {

    public QuartzConnectionProvider(String dbDriver, String dbURL, String dbUser, String dbPassword, int maxConnections, String dbValidationQuery) throws SQLException, SchedulerException {
        super(dbDriver, dbURL, dbUser, dbPassword, maxConnections, dbValidationQuery);
    }

    public QuartzConnectionProvider(Properties config) throws SchedulerException, SQLException {
        super(config);
    }
}
