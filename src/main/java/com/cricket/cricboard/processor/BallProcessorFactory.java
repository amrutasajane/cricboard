package com.cricket.cricboard.processor;

import com.cricket.cricboard.constants.BallType;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.EnumMap;
import java.util.Map;

import static com.cricket.cricboard.constants.BallType.*;

@Named
public class BallProcessorFactory {

  private WicketProcessor wicketProcessor;
  private WideProcessor wideProcessor;
  private NoBallProcessor noBallProcessor;
  private BallProcessor ballProcessor;

  private Map<BallType, BallProcessor> processorMap;

  @Inject
  public BallProcessorFactory(
      WicketProcessor wicketProcessor,
      WideProcessor wideProcessor,
      NoBallProcessor noBallProcessor,
      BallProcessor ballProcessor) {
    this.wicketProcessor = wicketProcessor;
    this.wideProcessor = wideProcessor;
    this.noBallProcessor = noBallProcessor;
    this.ballProcessor = ballProcessor;
    this.addProcessors();
  }

  public BallProcessor getBallProcessor(BallType ballType) {

    if (ballType != null && processorMap.containsKey(ballType)) {
      return processorMap.get(ballType);
    }

    return ballProcessor;
  }

  public void addProcessors() {

    processorMap = new EnumMap<>(BallType.class);

    processorMap.put(WICKET, wicketProcessor);
    processorMap.put(WIDE, wideProcessor);
    processorMap.put(NO_BALL, noBallProcessor);
  }
}
