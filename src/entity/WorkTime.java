package entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class WorkTime {
    /**出勤日*/
    private LocalDate workDate;
    /**出勤時間*/
    private LocalTime startTime;
    /**退勤時間*/
    private LocalTime finishTime;
    /**休憩開始時間*/
    private LocalTime breakStartTime;
    /**休憩終了時間*/
    private LocalTime breakFinishTime;
    /**休憩時間*/
    private Duration breakTime;
    /**勤務時間*/
    private Duration workingHours;
    /**出勤日*/
    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
    }

    public LocalTime getBreakStartTime() {
        return breakStartTime;
    }

    public void setBreakStartTime(LocalTime breakStartTime) {
        this.breakStartTime = breakStartTime;
    }

    public LocalTime getBreakFinishTime() {
        return breakFinishTime;
    }

    public void setBreakFinishTime(LocalTime breakFinishTime) {
        this.breakFinishTime = breakFinishTime;
    }

    public Duration getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Duration breakTime) {
        this.breakTime = breakTime;
    }

    public Duration getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Duration workingHours) {
        this.workingHours = workingHours;
    }
    public void calcBreakTime() {
        Duration duration = Duration.between(breakStartTime, breakFinishTime);
        setBreakTime(duration);
    }
    public void calcWorkingHours() {
        Duration duration = Duration.between(startTime, finishTime);
        setWorkingHours(duration);
        if(breakTime != null) {
            duration = workingHours.minus(breakTime);
            setWorkingHours(duration);
        }
    }

}
