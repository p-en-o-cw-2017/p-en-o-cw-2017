package p_en_o_cw_2017;

public class AutopilotConfigReader {
    private static byte[] readByteArray(java.io.DataInputStream stream) throws java.io.IOException {
        int length = stream.readInt();
        byte[] array = new byte[length];
        stream.readFully(array);
        return array;
    }
    public static AutopilotConfig read(java.io.DataInputStream stream) throws java.io.IOException {
        float gravity = stream.readFloat();
        float wingX = stream.readFloat();
        float tailSize = stream.readFloat();
        float engineMass = stream.readFloat();
        float wingMass = stream.readFloat();
        float tailMass = stream.readFloat();
        float maxThrust = stream.readFloat();
        float maxAOA = stream.readFloat();
        float wingLiftSlope = stream.readFloat();
        float horStabLiftSlope = stream.readFloat();
        float verStabLiftSlope = stream.readFloat();
        float horizontalAngleOfView = stream.readFloat();
        float verticalAngleOfView = stream.readFloat();
        int nbColumns = stream.readInt();
        int nbRows = stream.readInt();
        return new AutopilotConfig() {
            public float getGravity() { return gravity; }
            public float getWingX() { return wingX; }
            public float getTailSize() { return tailSize; }
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
);    }
}
