package p_en_o_cw_2017;

public class AutopilotInputsReader {
    private static byte[] readByteArray(java.io.DataInputStream stream) throws java.io.IOException {
        int length = stream.readInt();
        byte[] array = new byte[length];
        stream.readFully(array);
        return array;
    }
    public static AutopilotInputs read(java.io.DataInputStream stream) throws java.io.IOException {
        byte[] image = readByteArray(stream);
        float x = stream.readFloat();
        float y = stream.readFloat();
        float z = stream.readFloat();
        float heading = stream.readFloat();
        float pitch = stream.readFloat();
        float roll = stream.readFloat();
        float elapsedTime = stream.readFloat();
        return new AutopilotInputs() {
            public byte[] getImage() { return image; }
            public float getX() { return x; }
            public float getY() { return y; }
            public float getZ() { return z; }
            public float getHeading() { return heading; }
            public float getPitch() { return pitch; }
            public float getRoll() { return roll; }
            public float getElapsedTime() { return elapsedTime; }
        };
);    }
}
