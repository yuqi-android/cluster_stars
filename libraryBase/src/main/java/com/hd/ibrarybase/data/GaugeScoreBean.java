package com.hd.ibrarybase.data;

import java.util.List;

/**
 * 作者 YuQi
 * 注意代码尽量不要有警告
 * 2023/11/21
 **/
public class GaugeScoreBean {
    private  double factorMax;
    private  double factorMin;

    private List<GaugeScoreRangeBean> rangeBean;



    public double getFactorMax() {
        return factorMax;
    }

    public void setFactorMax(double factorMax) {
        this.factorMax = factorMax;
    }

    public double getFactorMin() {
        return factorMin;
    }

    public void setFactorMin(double factorMin) {
        this.factorMin = factorMin;
    }

    public List<GaugeScoreRangeBean> getRangeBean() {
        return rangeBean;
    }

    public void setRangeBean(List<GaugeScoreRangeBean> rangeBean) {
        this.rangeBean = rangeBean;
    }

    @Override
    public String toString() {
        return "GaugeScoreBean{" +
                "factorMax=" + factorMax +
                ", factorMin=" + factorMin +
                ", rangeBean=" + rangeBean +
                '}';
    }
}
