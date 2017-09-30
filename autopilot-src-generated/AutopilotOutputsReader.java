package p_en_o_cw_2017;

public class AutopilotOutputsReader {
    private static byte[] readByteArray(java.io.DataInputStream stream) throws java.io.IOException {
        int length = stream.readInt();
        byte[] array = new byte[length];
        stream.readFully(array);
        return array;
    }
    public static AutopilotOutputs read(java.io.DataInputStream stream) throws java.io.IOException {
        float thrust = stream.readFloat();
        float leftWingInclination = stream.readFloat();
        float rightWingInclination = stream.readFloat();
        float horStabInclination = stream.readFloat();
        float verStabInclination = stream.readFloat();
        return new AutopilotOutputs() {
            public float getThrust() { return thrust; }
            public float getLeftWingInclination() { return leftWingInclination; }
            public float getRightWingInclination() { return rightWingInclination; }
            public float getHorStabInclination() { return horStabInclination; }
            public float getVerStabInclination() { return verStabInclination; }
        };
);    }
}
