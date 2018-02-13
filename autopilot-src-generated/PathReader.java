package p_en_o_cw_2017;

public class PathReader {
    private static byte[] readByteArray(java.io.DataInputStream stream) throws java.io.IOException {
        int length = stream.readInt();
        byte[] array = new byte[length];
        stream.readFully(array);
        return array;
    }
    private static float[] readFloatArray(java.io.DataInputStream stream) throws java.io.IOException {
        int length = stream.readInt();
        float[] array = new float[length];
        for (int i = 0; i < length; i++) { array[i] = stream.readFloat(); }
        return array;
    }
    public static Path read(java.io.DataInputStream stream) throws java.io.IOException {
        final float[] x = readFloatArray(stream);
        final float[] y = readFloatArray(stream);
        final float[] z = readFloatArray(stream);
        return new Path() {
            public float[] getX() { return x; }
            public float[] getY() { return y; }
            public float[] getZ() { return z; }
        };
    }
}
