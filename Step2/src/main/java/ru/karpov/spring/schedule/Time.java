package ru.karpov.spring.schedule;

public class Time
{
    private int hours_;
    private int minutes_;

    public Time()
    {}

    public Time(int hours, int minutes)
    {
        this(hours * 60 + minutes);
    }

    public Time(int minutes)
    {
        this.hours_ = minutes / 60;
        this.minutes_ = minutes % 60;
    }

    public void setHours(int hours)
    {
        this.hours_ = hours;
    }

    public void setMinutes(int minutes)
    {
        this.minutes_ = minutes;
    }

    public int toMinutes()
    {
        return hours_ * 60 + minutes_;
    }

    @Override
    public String toString()
    {
        String hourS = Integer.toString(hours_);
        String minuteS = Integer.toString(minutes_);
        return hourS + ':' + minuteS;
    }
}
