package ru.karpov.spring.schedule;

public class Ship
{
    private String name_;
    private CargoType cargoType_;
    private int cargoWeight_;
    private int unloadTime_;
    private int day_;
    private Time time_;

    public Ship()
    {}

    public Ship(String name, CargoType cargoType, int cargoWeight, int date, Time time, int unloadTime)
    {
        this.name_ = name;
        this.cargoType_ = cargoType;
        this.cargoWeight_ = cargoWeight;
        this.day_ = date;
        this.time_ = time;
        this.unloadTime_ = unloadTime;
    }

    public Ship(Ship other)
    {
        this.name_ = other.name_;
        this.cargoType_ = other.cargoType_;
        this.cargoWeight_ = other.cargoWeight_;
        this.day_ = other.day_;
        this.unloadTime_ = other.unloadTime_;
        this.time_ = other.time_;
    }

    public int getUnloadTime()
    {
        return this.unloadTime_;
    }

    public void setUnloadTime(int unloadTime)
    {
        this.unloadTime_ = unloadTime;
    }

    public int getTime()
    {
        return time_.toMinutes() + (day_ - 1) * 60 * 24;
    }

    public void setName(int name)
    {
        this.name_ = "Ship " + name;
    }

    public CargoType getCargoType()
    {
        return this.cargoType_;
    }

    public void setCargoType(String cargoType)
    {
        switch(cargoType) {
            case "LOOSE":
                this.cargoType_ = CargoType.LOOSE;
                break;
            case "LIQUID":
                this.cargoType_ = CargoType.LIQUID;
                break;
            case "CONTAINER":
                this.cargoType_ = CargoType.CONTAINER;
                break;
        }
    }

    public void setCargoWeight(int cargoWeight)
    {
        this.cargoWeight_ = cargoWeight;
    }

    public void setDate(int day)
    {
        this.day_ = day;
    }

    public void setTime(Time time)
    {
        this.time_ = time;
    }

    public void printInfo()
    {
        System.out.println("Ship Name: " + name_);
        System.out.println("Cargo type: " + cargoType_);
        System.out.println("Cargo weight: " + cargoWeight_);
        System.out.println("Ship arrival time: " + day_ + ":" + this.time_.toString());
        System.out.println("Unloading time:" + this.unloadTime_);
    }


}
