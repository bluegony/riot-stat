import ch.qos.logback.classic.AsyncAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

scan("10 seconds")

//Properties properties = new Properties();
//InputStream is = getClass().getResourceAsStream("META-INF/profile.properties" );
//properties.load(is);
//activeProfile = properties.getProperty("solution.profiles.active");
//println 'active profile = ' + activeProfile


appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
//        pattern = "[%d][%X{context.transactionId}][%thread][%-5level][%X{member.username}][%c{0}] %m%n"
        pattern = "[%d][%X{context.transactionId}][%thread][%-5level][%X{member.username}][%F%L] - %m%n"
    }
}
appender("ASYNCSTDOUT", AsyncAppender) {
    queueSize = 100
    includeCallerData = true
    appenderRef("STDOUT")
}


appender("TAILSFILEOUT", RollingFileAppender) {
    file = "/data/clodata/logs/solution_batch_tails.out"
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "/data/clodata/logs/solution_batch_tails.%d{yyyy-MM-dd}.log"
        maxHistory = 30
    }
    encoder(PatternLayoutEncoder) {
//        pattern = "[%d][%X{context.transactionId}][%thread][%-5level][%X{member.username}][%c{0}] %m%n"
        pattern = "[%d][%X{context.transactionId}][%thread][%-5level][%X{member.username}][%F%L] %m%n"
    }
}
appender("ASYNCTAILSFILEOUT", AsyncAppender) {
    queueSize = 100
    includeCallerData = true
    appenderRef("TAILSFILEOUT")
}


logger("org.springframework", info, ["ASYNCSTDOUT"], false )
logger("org.mybatis.spring", info, ["ASYNCSTDOUT"], false)   // mybatis
logger("com.skplanet.service", debug, ["STDOUT", "TAILSFILEOUT"], false)
root(debug, ["ASYNCSTDOUT"])
//
////<!-- local, local_oracle, dev, tdev, alpha, prod1 구분-->
//if(activeProfile == 'prod1') {
//    logger("com.skplanet.solution", INFO, ["ASYNCSTDOUT", "ASYNCTAILSFILEOUT"], false)
//    root(INFO, ["ASYNCSTDOUT"])
//} else {
//    logger("com.skplanet.solution", debug, ["STDOUT", "TAILSFILEOUT"], false)
//    root(debug, ["ASYNCSTDOUT"])
//}