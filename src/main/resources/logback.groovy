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
        pattern = "[%d{yyMMdd HH:mm:ss.SSS,Asia/Seoul}][%X{context.transactionId}][%.5thread][%-5level][%X{member.username}][%F%L] - %m%n"
    }
}
appender("ASYNCSTDOUT", AsyncAppender) {
    queueSize = 100
    includeCallerData = true
    appenderRef("STDOUT")
}


appender("TAILSFILEOUT", RollingFileAppender) {
    file = "/data/bmriot/logs/bmriot_tails.out"
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "/data/bmriot/logs/bmriot_tails.%d{yyyy-MM-dd}.log"
        maxHistory = 30
    }
    encoder(PatternLayoutEncoder) {
//        pattern = "[%d][%X{context.transactionId}][%thread][%-5level][%X{member.username}][%c{0}] %m%n"
        pattern = "[%d{yyMMdd HH:mm:ss.SSS,Asia/Seoul}][%X{context.transactionId}][%.5thread][%-5level][%X{member.username}][%F%L] %m%n"
    }
}
appender("ASYNCTAILSFILEOUT", AsyncAppender) {
    queueSize = 100
    includeCallerData = true
    appenderRef("TAILSFILEOUT")
}


logger("org.springframework", info, ["STDOUT", "ASYNCTAILSFILEOUT"], false )
logger("org.mybatis.spring", info, ["STDOUT", "ASYNCTAILSFILEOUT"], false)   // mybatis
logger("com.study.bmservice", info, ["STDOUT", "ASYNCTAILSFILEOUT"], false)
logger("com.study.bmservice.dto.Price", info, ["STDOUT", "ASYNCTAILSFILEOUT"], false)
root(info, ["STDOUT", "ASYNCTAILSFILEOUT"])
//
////<!-- local, local_oracle, dev, tdev, alpha, prod1 구분-->
//if(activeProfile == 'prod1') {
//    logger("com.skplanet.solution", INFO, ["ASYNCSTDOUT", "ASYNCTAILSFILEOUT"], false)
//    root(INFO, ["ASYNCSTDOUT"])
//} else {
//    logger("com.skplanet.solution", debug, ["STDOUT", "TAILSFILEOUT"], false)
//    root(debug, ["ASYNCSTDOUT"])
//}