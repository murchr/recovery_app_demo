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

    // EFFECTS: applies store method to instance of ExerciseStat for each element in list and returns score
    public int summary(int scaleType) {
        ExerciseStat exerciseStat = new ExerciseStat();
        summary(exerciseStat, scaleType);
        return (int)exerciseStat.getScore();
    }
}
