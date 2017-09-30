package p_en_o_cw_2017;

public class AutopilotConfigWriter {
    private static void writeByteArray(java.io.DataOutputStream stream, byte[] array) throws java.io.IOException {
        stream.writeInt(array.length);
        stream.write(array);
    }
    public static void write(java.io.DataOutputStream stream, AutopilotConfig value) throws java.io.IOException {
        stream.writeFloat(value.getGravity());
        stream.writeFloat(value.getWingX());
        stream.writeFloat(value.getTailSize());
        stream.writeFloat(value.getEngineMass());
        stream.writeFloat(value.getWingMass());
        stream.writeFloat(value.getTailMass());
        stream.writeFloat(value.getMaxThrust());
        stream.writeFloat(value.getMaxAOA());
        stream.writeFloat(value.getWingLiftSlope());
        stream.writeFloat(value.getHorStabLiftSlope());
        stream.writeFloat(value.getVerStabLiftSlope());
        stream.writeFloat(value.getHorizontalAngleOfView());
        stream.writeFloat(value.getVerticalAngleOfView());
        stream.writeInt(value.getNbColumns());
        stream.writeInt(value.getNbRows());
    }
}
