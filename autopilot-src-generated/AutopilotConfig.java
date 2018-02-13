package p_en_o_cw_2017;

public interface AutopilotConfig {
    String getDroneID();
    float getGravity();
    float getWingX();
    float getTailSize();
    float getWheelY();
    float getFrontWheelZ();
    float getRearWheelZ();
    float getRearWheelX();
    float getTyreSlope();
    float getDampSlope();
    float getTyreRadius();
    float getRMax();
    float getFcMax();
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
