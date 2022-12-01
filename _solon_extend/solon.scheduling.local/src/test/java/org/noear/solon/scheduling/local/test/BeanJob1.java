package org.noear.solon.scheduling.local.test;

import org.noear.solon.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @author noear 2022/11/24 created
 */
@Scheduled(cron = "* * * * * ?")
public class BeanJob1 implements Runnable {
    @Override
    public void run() {
        System.out.println("job1:: " + new Date());
    }
}
