package com.liwj.szpd.vo;

import java.util.List;

public class FinanceVO {
    private List<FinanceStepVO> steps;
    private FinanceStatisticVO statistic;

    public List<FinanceStepVO> getSteps() {
        return steps;
    }

    public void setSteps(List<FinanceStepVO> steps) {
        this.steps = steps;
    }

    public FinanceStatisticVO getStatistic() {
        return statistic;
    }

    public void setStatistic(FinanceStatisticVO statistic) {
        this.statistic = statistic;
    }
}
