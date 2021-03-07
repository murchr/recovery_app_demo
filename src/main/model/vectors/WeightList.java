package model.vectors;


import model.statistics.WeightStat;

public class WeightList extends LogList {
    public WeightList() {
        super();
    }

    @Override
    // EFFECTS: returns a copy of logVector
    public WeightList clone() {
        return (WeightList)super.clone();
    }

    // MODIFIES: summaryStat
    // EFFECTS: applies process method to each element in vector with summaryStat
    public double summary() {
        WeightStat weightStat = new WeightStat();
        summary(weightStat);
        return weightStat.getWeight();
    }
}
