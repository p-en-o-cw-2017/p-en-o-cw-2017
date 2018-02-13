package p_en_o_cw_2017;

public class AutopilotInputs_v2Writer {
    private static void writeByteArray(java.io.DataOutputStream stream, byte[] array) throws java.io.IOException {
        stream.writeInt(array.length);
        stream.write(array);
    }
    private static void writeFloatArray(java.io.DataOutputStream stream, float[] array) throws java.io.IOException {
        stream.writeInt(array.length);
        for (float f : array) { stream.writeFloat(f); }
    }
    public static void write(java.io.DataOutputStream stream, AutopilotInputs_v2 value) throws java.io.IOException {
        writeByteArray(stream, value.getImage());
        stream.writeFloat(value.getX());
        stream.writeFloat(value.getY());
        stream.writeFloat(value.getZ());
        stream.writeFloat(value.getHeading());
        stream.writeFloat(value.getPitch());
        stream.writeFloat(value.getRoll());
        stream.writeFloat(value.getElapsedTime());
    }
}
