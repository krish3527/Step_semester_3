package oops.assignment_problem;

import java.util.ArrayList;

interface Capability {
    String getName();

    boolean supports(String action);

    boolean execute(String action, int value);

    boolean execute(String action);
}

class PowerCapability implements Capability {

    private boolean on;

    @Override
    public String getName() {
        return "Power";
    }

    @Override
    public boolean supports(String action) {
        return action.equals("ON")
                || action.equals("OFF");
    }

    @Override
    public boolean execute(String action, int value) {
        return false;
    }

    @Override
    public boolean execute(String action) {

        if (action.equals("ON")) {
            on = true;
            return true;
        }

        if (action.equals("OFF")) {
            on = false;
            return true;
        }

        return false;
    }

    public boolean isOn() {
        return on;
    }
}

class BrightnessCapability implements Capability {

    private int brightness;

    @Override
    public String getName() {
        return "Brightness";
    }

    @Override
    public boolean supports(String action) {
        return action.equals("SET");
    }

    @Override
    public boolean execute(String action, int value) {

        if (!action.equals("SET")) {
            return false;
        }

        if (value < 0 || value > 100) {
            return false;
        }

        brightness = value;
        return true;
    }

    @Override
    public boolean execute(String action) {
        return false;
    }

    public int getBrightness() {
        return brightness;
    }
}

class TemperatureCapability implements Capability {

    private int temperature;

    @Override
    public String getName() {
        return "Temperature";
    }

    @Override
    public boolean supports(String action) {
        return action.equals("SET");
    }

    @Override
    public boolean execute(String action, int value) {

        if (!action.equals("SET")) {
            return false;
        }

        if (value < 16 || value > 30) {
            return false;
        }

        temperature = value;
        return true;
    }

    @Override
    public boolean execute(String action) {
        return false;
    }

    public int getTemperature() {
        return temperature;
    }
}

class LabDevice {

    private String name;
    private ArrayList<Capability> capabilities;

    public LabDevice(String name) {
        this.name = name;
        capabilities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCapability(Capability capability) {

        capabilities.add(capability);

        System.out.println(
                name +
                ": " +
                capability.getName() +
                " capability added."
        );
    }

    public Capability getCapability(String capabilityName) {

        for (Capability capability : capabilities) {

            if (capability.getName()
                    .equals(capabilityName)) {

                return capability;
            }
        }

        return null;
    }

    public boolean hasCapability(String capabilityName) {
        return getCapability(capabilityName) != null;
    }

    public void execute(
            String capabilityName,
            String action,
            int value) {

        Capability capability =
                getCapability(capabilityName);

        if (capability == null) {
            return;
        }

        boolean success =
                capability.execute(action, value);

        if (!success) {

            if (capabilityName.equals("Brightness")) {
                System.out.println(
                        "Rejected: " +
                        name +
                        " brightness must be between 0% and 100%."
                );
            }

            if (capabilityName.equals("Temperature")) {
                System.out.println(
                        "Rejected: " +
                        name +
                        " temperature must be between 16°C and 30°C."
                );
            }

            return;
        }

        if (capabilityName.equals("Brightness")) {

            System.out.println(
                    name +
                    ": brightness set to " +
                    value +
                    "%."
            );
        }

        if (capabilityName.equals("Temperature")) {

            System.out.println(
                    name +
                    ": temperature set to " +
                    value +
                    "°C."
            );
        }
    }

    public void execute(
            String capabilityName,
            String action) {

        Capability capability =
                getCapability(capabilityName);

        if (capability == null) {
            return;
        }

        boolean success =
                capability.execute(action);

        if (success && capabilityName.equals("Power")) {

            if (action.equals("ON")) {

                System.out.println(
                        name + ": ON."
                );
            }

            if (action.equals("OFF")) {

                System.out.println(
                        name + ": OFF."
                );
            }
        }
    }
}

class SceneStep {

    private String capabilityName;
    private String action;
    private int value;
    private boolean hasValue;

    public SceneStep(
            String capabilityName,
            String action) {

        this.capabilityName = capabilityName;
        this.action = action;
        this.hasValue = false;
    }

    public SceneStep(
            String capabilityName,
            String action,
            int value) {

        this.capabilityName = capabilityName;
        this.action = action;
        this.value = value;
        this.hasValue = true;
    }

    public int execute(LabDevice device) {

        if (!device.hasCapability(capabilityName)) {
            return 0;
        }

        if (hasValue) {

            device.execute(
                    capabilityName,
                    action,
                    value
            );

        } else {

            device.execute(
                    capabilityName,
                    action
            );
        }

        return 1;
    }
}

class LabScene {

    private String name;
    private ArrayList<SceneStep> steps;

    public LabScene(String name) {
        this.name = name;
        steps = new ArrayList<>();
    }

    public void addStep(SceneStep step) {
        steps.add(step);
    }

    public void execute(ArrayList<LabDevice> devices) {

        System.out.println(
                "Scene '" +
                name +
                "' started."
        );

        int actions = 0;

        for (SceneStep step : steps) {

            for (LabDevice device : devices) {

                actions += step.execute(device);
            }
        }

        System.out.println(
                "Scene '" +
                name +
                "' completed: " +
                actions +
                " actions applied."
        );
    }
}

public class Ques3 {

    public static void main(String[] args) {

        LabDevice labAC =
                new LabDevice("Lab AC");

        labAC.addCapability(
                new PowerCapability()
        );

        labAC.addCapability(
                new TemperatureCapability()
        );

        LabDevice ceilingLights =
                new LabDevice("Ceiling Lights");

        ceilingLights.addCapability(
                new PowerCapability()
        );

        ceilingLights.addCapability(
                new BrightnessCapability()
        );

        LabDevice projector =
                new LabDevice("Projector");

        projector.addCapability(
                new PowerCapability()
        );

        ArrayList<LabDevice> devices =
                new ArrayList<>();

        devices.add(labAC);
        devices.add(ceilingLights);
        devices.add(projector);

        LabScene lectureMode =
                new LabScene("Lecture Mode");

        lectureMode.addStep(
                new SceneStep(
                        "Power",
                        "ON"
                )
        );

        lectureMode.addStep(
                new SceneStep(
                        "Brightness",
                        "SET",
                        40
                )
        );

        lectureMode.addStep(
                new SceneStep(
                        "Temperature",
                        "SET",
                        24
                )
        );

        lectureMode.execute(devices);

        labAC.execute(
                "Temperature",
                "SET",
                12
        );

        projector.addCapability(
                new BrightnessCapability()
        );

        projector.execute(
                "Brightness",
                "SET",
                70
        );
    }
}