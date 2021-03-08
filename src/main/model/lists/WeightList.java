package model.lists;


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

    // EFFECTS: applies store method to instance of WeightStat for each element in list and returns weight
    public double summary() {
        WeightStat weightStat = new WeightStat();
        summary(weightStat);
        return weightStat.getWeight();
    }
}
