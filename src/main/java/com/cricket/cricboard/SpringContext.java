package com.cricket.cricboard;

import com.cricket.cricboard.processor.InningsManager;
import com.cricket.cricboard.processor.MatchManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {
  private static final String SPRING_CONFIG_FILE = "spring-config.xml";

  private static final ApplicationContext CONTEXT =
      new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);

  private SpringContext() {}

  public static MatchManager getMatchManager() {

    return CONTEXT.getBean(MatchManager.class);
  }
}
