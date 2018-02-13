package p_en_o_cw_2017;

public interface AutopilotOutputs {
    float getThrust();
    float getLeftWingInclination();
    float getRightWingInclination();
    float getHorStabInclination();
    float getVerStabInclination();
    float getFrontBrakeForce();
    float getLeftBrakeForce();
    float getRightBrakeForce();
}
