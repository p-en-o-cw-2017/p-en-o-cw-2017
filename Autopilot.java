package p_en_o_cw_2017;

public interface Autopilot {
    AutopilotOutputs simulationStarted(AutopilotConfig config, AutopilotInputs inputs);
    AutopilotOutputs timePassed(AutopilotInputs inputs);
    void simulationEnded();
}
