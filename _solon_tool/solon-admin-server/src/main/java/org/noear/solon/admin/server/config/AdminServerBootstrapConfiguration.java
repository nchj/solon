package org.noear.solon.admin.server.config;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import java.util.concurrent.ScheduledThreadPoolExecutor;

@Slf4j
@Configuration
public class AdminServerBootstrapConfiguration {

    @Condition(onProperty = "${solon.admin.server.enabled:true} = true")
    @Bean
    public MarkedServerEnabled markedServerEnabled() {
        return new MarkedServerEnabled(Solon.cfg().get("solon.admin.server.mode", "local"));
    }

    @Bean
    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor(@Inject(required = false) MarkedServerEnabled marker) {
        if (marker == null) return null;
        return new ScheduledThreadPoolExecutor(1);
    }

    @Value
    public static class MarkedServerEnabled {
        String mode;

        public MarkedServerEnabled(String mode) {
            this.mode = mode;

            log.info("Solon Admin server has been successfully enabled in {} mode.", this.mode);
        }
    }

}
