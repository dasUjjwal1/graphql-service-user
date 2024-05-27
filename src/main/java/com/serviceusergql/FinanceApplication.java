package com.serviceusergql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinanceApplication {
    private static final Logger LOG = LoggerFactory.getLogger(FinanceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FinanceApplication.class, args);

//        final String stream1 = "stream1";
//        final String group1 = "stream1";
//
//        try (JedisPool pool = new JedisPool()) {
//            Jedis jedis = pool.getResource();
//            LOG.info("Group created{}", jedis.xgroupCreate(stream1, group1, StreamEntryID.LAST_ENTRY, true));
//        } catch (JedisDataException exception) {
//            LOG.info(exception.toString());
//        }
    }

}
