package p_en_o_cw_2017;

public interface AutopilotInputs_v2 {
    byte[] getImage();
    float getX();
    float getY();
    float getZ();
    float getHeading();
    float getPitch();
    float getRoll();
    float getElapsedTime();
}
