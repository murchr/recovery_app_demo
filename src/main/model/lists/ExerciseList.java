package model.lists;

import model.statistics.ExerciseStat;

public class ExerciseList extends LogList {
    public ExerciseList() {
        super();
    }

    @Override
    // EFFECTS: returns a copy of logVector
    public ExerciseList clone() {
        return (ExerciseList)super.clone();
    }

    // MODIFIES: summaryStat
    // EFFECTS: applies process method to each element in vector with summaryStat
    public int summary(int scaleType) {
        ExerciseStat exerciseStat = new ExerciseStat();
        summary(exerciseStat, scaleType);
        return (int)exerciseStat.getScore();
    }
}
