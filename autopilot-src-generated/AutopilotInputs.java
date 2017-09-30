package p_en_o_cw_2017;

public interface AutopilotInputs {
    byte[] getImage();
    float getX();
    float getY();
    float getZ();
    float getHeading();
    float getPitch();
    float getRoll();
    float getElapsedTime();
}
