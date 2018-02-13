package p_en_o_cw_2017;

public class AutopilotConfigReader {
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
    public static AutopilotConfig read(java.io.DataInputStream stream) throws java.io.IOException {
        final String droneID = stream.readUTF();
        final float gravity = stream.readFloat();
        final float wingX = stream.readFloat();
        final float tailSize = stream.readFloat();
        final float wheelY = stream.readFloat();
        final float frontWheelZ = stream.readFloat();
        final float rearWheelZ = stream.readFloat();
        final float rearWheelX = stream.readFloat();
        final float tyreSlope = stream.readFloat();
        final float dampSlope = stream.readFloat();
        final float tyreRadius = stream.readFloat();
        final float rMax = stream.readFloat();
        final float fcMax = stream.readFloat();
        final float engineMass = stream.readFloat();
        final float wingMass = stream.readFloat();
        final float tailMass = stream.readFloat();
        final float maxThrust = stream.readFloat();
        final float maxAOA = stream.readFloat();
        final float wingLiftSlope = stream.readFloat();
        final float horStabLiftSlope = stream.readFloat();
        final float verStabLiftSlope = stream.readFloat();
        final float horizontalAngleOfView = stream.readFloat();
        final float verticalAngleOfView = stream.readFloat();
        final int nbColumns = stream.readInt();
        final int nbRows = stream.readInt();
        return new AutopilotConfig() {
            public String getDroneID() { return droneID; }
            public float getGravity() { return gravity; }
            public float getWingX() { return wingX; }
            public float getTailSize() { return tailSize; }
            public float getWheelY() { return wheelY; }
            public float getFrontWheelZ() { return frontWheelZ; }
            public float getRearWheelZ() { return rearWheelZ; }
            public float getRearWheelX() { return rearWheelX; }
            public float getTyreSlope() { return tyreSlope; }
            public float getDampSlope() { return dampSlope; }
            public float getTyreRadius() { return tyreRadius; }
            public float getRMax() { return rMax; }
            public float getFcMax() { return fcMax; }
            public float getEngineMass() { return engineMass; }
            public float getWingMass() { return wingMass; }
            public float getTailMass() { return tailMass; }
            public float getMaxThrust() { return maxThrust; }
            public float getMaxAOA() { return maxAOA; }
            public float getWingLiftSlope() { return wingLiftSlope; }
            public float getHorStabLiftSlope() { return horStabLiftSlope; }
            public float getVerStabLiftSlope() { return verStabLiftSlope; }
            public float getHorizontalAngleOfView() { return horizontalAngleOfView; }
            public float getVerticalAngleOfView() { return verticalAngleOfView; }
            public int getNbColumns() { return nbColumns; }
            public int getNbRows() { return nbRows; }
        };
    }
}
