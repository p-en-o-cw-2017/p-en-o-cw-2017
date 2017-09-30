package p_en_o_cw_2017;

public interface AutopilotConfig {
    float getGravity();
    float getWingX();
    float getTailSize();
    float getEngineMass();
    float getWingMass();
    float getTailMass();
    float getMaxThrust();
    float getMaxAOA();
    float getWingLiftSlope();
    float getHorStabLiftSlope();
    float getVerStabLiftSlope();
    float getHorizontalAngleOfView();
    float getVerticalAngleOfView();
    int getNbColumns();
    int getNbRows();
}
