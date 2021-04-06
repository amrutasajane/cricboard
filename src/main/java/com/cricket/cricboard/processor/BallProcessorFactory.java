package com.cricket.cricboard.processor;

import com.cricket.cricboard.constants.BallType;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.EnumMap;
import java.util.Map;

import static com.cricket.cricboard.constants.BallType.*;

@Named
public class BallProcessorFactory {

    @Inject
    private WicketProcessor wicketProcessor;

    @Inject
    private WideProcessor wideProcessor;

    @Inject
    private NoBallProcessor noBallProcessor;

    @Inject
    private BallProcessor ballProcessor;

    private Map<BallType, BallProcessor> processorMap;

    public BallProcessorFactory() {

        addProcessors();
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
