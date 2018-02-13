package p_en_o_cw_2017;

public class AutopilotInputs_v2Reader {
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
    public static AutopilotInputs_v2 read(java.io.DataInputStream stream) throws java.io.IOException {
        final byte[] image = readByteArray(stream);
        final float x = stream.readFloat();
        final float y = stream.readFloat();
        final float z = stream.readFloat();
        final float heading = stream.readFloat();
        final float pitch = stream.readFloat();
        final float roll = stream.readFloat();
        final float elapsedTime = stream.readFloat();
        return new AutopilotInputs_v2() {
            public byte[] getImage() { return image; }
            public float getX() { return x; }
            public float getY() { return y; }
            public float getZ() { return z; }
            public float getHeading() { return heading; }
            public float getPitch() { return pitch; }
            public float getRoll() { return roll; }
            public float getElapsedTime() { return elapsedTime; }
        };
    }
}
