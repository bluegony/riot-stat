//package com.study;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ClassUtils;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//
///**
// * Created by bluegony on 2018. 6. 15..
// */
//@Slf4j
//@Configuration
//@ComponentScan(basePackages = "com.study")
//public class Application  {
//
//    @Autowired
//    private ApplicationContext context;
//
//    public static void main(String[] args) {
//        SpringApplication app = new SpringApplication(Application.class);
//        app.setWebEnvironment(false); //<<<<<<<<<
//        ConfigurableApplicationContext ctx = app.run(args);
//    }
//
//    @Component
//    public class CommandRunner implements CommandLineRunner {
//
//        @Override
//        public void run(String... args) throws Exception {
//            runBatch(Arrays.asList(args).toArray(new String[0]));
//        }
//
//    }
//
//    public void runBatch(String[] args) {
//
//        boolean isManualMode = args.length >= 2; // 클래스명, 메소드명, 파라미터 등 args가 2개 이상일 경우에만 아래 내용을 실행
//
//        try {
//            if (isManualMode) {
//
//                final String beanName = args[0]; // 클래스명
//                final String methodName = args[1]; // 메소드명
//
//                log.info("Execute parameter beanName: {}, methodName: {}", beanName, methodName);
//                Object bean = null;
//                try {
//                    bean = context.getBean(beanName);
//                } catch (BeansException e) {
//                    log.error("Exception occurred while searching beanName '{}' ", beanName, e);
//                }
//                if (bean == null) {
//                    log.info("BeanName '{}' not found ", beanName);
//                    return;
//                }
//
//                try {
//
//                    if (args.length > 2) { // 메소드의 파라미터가 존재할 경우
//                        executeMethodParam(args, beanName, methodName, bean);
//                    } else { // 메소드의 파라미터가 존재하지 않을 경
//                        Method executingMethod = ClassUtils.getMethod(bean.getClass(), methodName);
//                        if (executingMethod == null) {
//                            log.error("{} method not found in {} bean", methodName, beanName);
//                            return;
//                        }
//                        executingMethod.invoke(bean);
//                    }
//
//                } catch (IllegalAccessException | IllegalArgumentException
//                        | InvocationTargetException e) {
//                    log.error("Exception occurred while invoking '{}' bean '{}' method", beanName, methodName);
////                    e.printStackTrace();
//                    log.info(e.getMessage(),e);
//                }
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
////        aac.destroy();
//    }
//
//    /**
//     * 메소드에 파라미터가 존재할 경우 각 타입에 맞춰서 실행
//     * @param args
//     * @param beanName
//     * @param methodName
//     * @param bean
//     * @throws IllegalAccessException
//     * @throws InvocationTargetException
//     */
//    public static void executeMethodParam(String[] args, String beanName, String methodName, Object bean)
//            throws IllegalAccessException, InvocationTargetException {
//        boolean isExecutingMethod = false;
//
//        Method[] methods = bean.getClass().getDeclaredMethods();
//
//        List<String> paramValueList = new ArrayList<>();
//        for(int i=2; i<args.length; i++) {
//            paramValueList.add(args[i]);
//        }
//
//        for(Method executingMethod : methods) {
//
//            String beanMethodName = executingMethod.getName();
//
//            if(executingMethod.getParameterTypes().length > 0) { // 메소드의 파라미터가 1개 이상 존재한다면...
//
//                if(Objects.equals(beanMethodName, methodName)) {
//
//                    Class[] parameters = null;
//                    parameters = executingMethod.getParameterTypes();
//
//                    //public void test(int a, String b) {
//                    List<Object> tempParam = new ArrayList<>();
//
//
//                    try {
//                        for (int i = 0; i < parameters.length; i++) {
//                            if (parameters[i] == String.class) {
//                                log.info("[parameters Type : string type]");
//                                tempParam.add(paramValueList.get(i));
//                            } else if (parameters[i] == int.class) {
//                                log.info("[parameters Type : int type]");
//                                tempParam.add(Integer.parseInt(paramValueList.get(i)));
//                            } else if (parameters[i] == long.class) {
//                                log.info("[parameters Type : long type]");
//                                tempParam.add(Long.parseLong(paramValueList.get(i)));
//                            } else if (parameters[i] == double.class) {
//                                log.info("[parameters Type : double type]");
//                                tempParam.add(Double.parseDouble(paramValueList.get(i)));
//                            } else if (parameters[i] == float.class) {
//                                log.info("[parameters Type : float type]");
//                                tempParam.add(Float.parseFloat(paramValueList.get(i)));
//                            } else if (parameters[i] == Boolean.class) {
//                                log.info("[parameters Type : boolean type]");
//                                tempParam.add(Boolean.valueOf(paramValueList.get(i)));
//                            }
//                        }
//                    } catch (NumberFormatException e) {
//                        log.error("사용자가 입력한 파라미터 값의 타입이 해당 메소드 파라미터 타입과 일치하지 않습니다.");
//                        e.printStackTrace();
//                    }
//
//
//                    Object[] callParameter = new Object[tempParam.size()]; // object배열밖에 안되는것인가?
//                    for(int j=0; j<tempParam.size(); j++) {
//                        callParameter[j] = tempParam.get(j);
//                    }
//                    executingMethod.invoke(bean, callParameter);
//                    isExecutingMethod = true;
//                }
//            }
//
//        }
//
//        if(!isExecutingMethod) {
//            log.error("{} method not found in {} bean", methodName, beanName);
//        }
//    }
//}