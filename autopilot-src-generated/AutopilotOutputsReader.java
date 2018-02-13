package p_en_o_cw_2017;

public class AutopilotOutputsReader {
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
    public static AutopilotOutputs read(java.io.DataInputStream stream) throws java.io.IOException {
        final float thrust = stream.readFloat();
        final float leftWingInclination = stream.readFloat();
        final float rightWingInclination = stream.readFloat();
        final float horStabInclination = stream.readFloat();
        final float verStabInclination = stream.readFloat();
        final float frontBrakeForce = stream.readFloat();
        final float leftBrakeForce = stream.readFloat();
        final float rightBrakeForce = stream.readFloat();
        return new AutopilotOutputs() {
            public float getThrust() { return thrust; }
            public float getLeftWingInclination() { return leftWingInclination; }
            public float getRightWingInclination() { return rightWingInclination; }
            public float getHorStabInclination() { return horStabInclination; }
            public float getVerStabInclination() { return verStabInclination; }
            public float getFrontBrakeForce() { return frontBrakeForce; }
            public float getLeftBrakeForce() { return leftBrakeForce; }
            public float getRightBrakeForce() { return rightBrakeForce; }
        };
    }
}
