package com.hd.ibrarybase.data;

public  class GaugeScoreRangeBean{
        private  String factorName;
        private  double rangeMax;
        private  double rangeMin;
        private  double score;

       public String getFactorName() {
           return factorName;
       }

       public void setFactorName(String factorName) {
           this.factorName = factorName;
       }

       public double getRangeMax() {
           return rangeMax;
       }

       public void setRangeMax(double rangeMax) {
           this.rangeMax = rangeMax;
       }

       public double getRangeMin() {
           return rangeMin;
       }

       public void setRangeMin(double rangeMin) {
           this.rangeMin = rangeMin;
       }

       public double getScore() {
           return score;
       }

       public void setScore(double score) {
           this.score = score;
       }

       @Override
       public String toString() {
           return "GaugeScoreRangeBean{" +
                   "factorName='" + factorName + '\'' +
                   ", rangeMax=" + rangeMax +
                   ", rangeMin=" + rangeMin +
                   ", score=" + score +
                   '}';
       }
   }