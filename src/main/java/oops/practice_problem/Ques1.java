package oops.practice_problem;

interface Dimmable {
    void dim(int percentage);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitor {
    void showEnergy();
}

abstract class Device {
    String id;
    boolean isOn;

    Device(String id) {
        this.id = id;
        this.isOn = false;
    }

    void turnOn() {
        isOn = true;
        System.out.println(id + " is ON");
    }

    void turnOff() {
        isOn = false;
        System.out.println(id + " is OFF");
    }
}

class Light extends Device implements Dimmable, Schedulable {

    Light(String id) {
        super(id);
    }

    public void dim(int percentage) {
        System.out.println(id + " dimmed to " + percentage);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {

    Fan(String id) {
        super(id);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends Device implements EnergyMonitor {

    double energy;

    Plug(String id, double energy) {
        super(id);
        this.energy = energy;
    }

    public void showEnergy() {
        System.out.println(id + " energy " + energy + " kWh");
    }
}

public class Ques1 {

    public static void main(String[] args) {

        Device[] devices = {
            new Light("L1"),
            new Fan("F1"),
            new Plug("P1", 12)
        };

        for (Device device : devices) {

            if (device instanceof Light) {
                Light light = (Light) device;
                light.turnOn();
                light.dim(40);
            }

            if (device instanceof Fan) {
                Fan fan = (Fan) device;
                fan.schedule("22:00");
            }

            if (device instanceof Plug) {
                Plug plug = (Plug) device;
                plug.showEnergy();
            }
        }

        Device fanDevice = devices[1];

        if (fanDevice instanceof Dimmable) {
            ((Dimmable) fanDevice).dim(30);
        } else {
            System.out.println(fanDevice.id + " rejected: DIM unsupported");
        }

        Device lightDevice = devices[0];

        if (lightDevice instanceof EnergyMonitor) {
            ((EnergyMonitor) lightDevice).showEnergy();
        } else {
            System.out.println(lightDevice.id + " rejected: ENERGY unsupported");
        }
    }
}