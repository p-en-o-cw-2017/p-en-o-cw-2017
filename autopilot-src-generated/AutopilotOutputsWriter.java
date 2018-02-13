package p_en_o_cw_2017;

public class AutopilotOutputsWriter {
    private static void writeByteArray(java.io.DataOutputStream stream, byte[] array) throws java.io.IOException {
        stream.writeInt(array.length);
        stream.write(array);
    }
    private static void writeFloatArray(java.io.DataOutputStream stream, float[] array) throws java.io.IOException {
        stream.writeInt(array.length);
        for (float f : array) { stream.writeFloat(f); }
    }
    public static void write(java.io.DataOutputStream stream, AutopilotOutputs value) throws java.io.IOException {
        stream.writeFloat(value.getThrust());
        stream.writeFloat(value.getLeftWingInclination());
        stream.writeFloat(value.getRightWingInclination());
        stream.writeFloat(value.getHorStabInclination());
        stream.writeFloat(value.getVerStabInclination());
        stream.writeFloat(value.getFrontBrakeForce());
        stream.writeFloat(value.getLeftBrakeForce());
        stream.writeFloat(value.getRightBrakeForce());
    }
}
