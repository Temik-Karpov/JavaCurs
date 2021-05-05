package schedule;

public class Ship
{
    private String name_;
    private CargoType cargoType_;
    private int cargoWeight_;
    private Time unloadTime_;
    private int date_;
    private Time time_;

    public Ship()
    {}

    public Ship(String name, CargoType cargoType, int cargoWeight, int date, Time time)
    {
        this.name_ = name;
        this.cargoType_ = cargoType;
        this.cargoWeight_ = cargoWeight;
        this.date_ = date;
        this.time_ = time;
        setUnloadTime();
    }

    public Ship(Ship other)
    {
        this.name_ = other.name_;
        this.cargoType_ = other.cargoType_;
        this.cargoWeight_ = other.cargoWeight_;
        this.date_ = other.date_;
        this.unloadTime_ = other.unloadTime_;
        this.time_ = other.time_;
    }

    public void setUnloadTime()
    {
        switch(cargoType_)
        {
            case LOOSE:
                unloadTime_ = new Time(cargoWeight_ / 2);
                break;
            case LIQUID:
                unloadTime_ = new Time(cargoWeight_ / 4);
                break;
            case CONTAINER:
                unloadTime_ = new Time(cargoWeight_ / 6);
                break;
        }
    }

    public int getUnloadTime()
    {
        return unloadTime_.toMinutes();
    }

    public void setName(int name)
    {
        this.name_ = "Ship " + name;
    }

    public CargoType getCargoType()
    {
        return this.cargoType_;
    }

    public void setCargoType(int cargoType)
    {
        switch(cargoType) {
            case 1:
                this.cargoType_ = CargoType.LOOSE;
                break;
            case 2:
                this.cargoType_ = CargoType.LIQUID;
                break;
            case 3:
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
        this.date_ = day;
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
        System.out.println("Ship arrival date: " + date_);
        System.out.println("Ship arrival time: " + this.time_.toString());
        System.out.println("Unloading time:" + this.unloadTime_.toString());
    }


}
