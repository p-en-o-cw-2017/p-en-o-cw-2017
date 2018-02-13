package p_en_o_cw_2017;

public class PathWriter {
    private static void writeByteArray(java.io.DataOutputStream stream, byte[] array) throws java.io.IOException {
        stream.writeInt(array.length);
        stream.write(array);
    }
    private static void writeFloatArray(java.io.DataOutputStream stream, float[] array) throws java.io.IOException {
        stream.writeInt(array.length);
        for (float f : array) { stream.writeFloat(f); }
    }
    public static void write(java.io.DataOutputStream stream, Path value) throws java.io.IOException {
        writeFloatArray(stream, value.getX());
        writeFloatArray(stream, value.getY());
        writeFloatArray(stream, value.getZ());
    }
}
