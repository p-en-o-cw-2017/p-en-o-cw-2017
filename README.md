# Shared Artifacts for P&O CW 2017-2018

- File `Autopilot.datatypes` defines the drone model and the datatypes `AutopilotConfig`, `AutopilotInputs`, and `AutopilotOutputs`.
- Directory `datatypes` implements a code generator for generating for each datatype defined in a given file three Java types:
  - a Java interface with a getter for each field of the datatype
  - a class that writes an instance of the datatype to a `DataOutputStream`
  - a class that reads an instance of the datatype from a `DataInputStream`
- File `Autopilot.java` defines a Java API for communication between a testbed and an autopilot. It uses the datatypes defined in `Autopilot.datatypes`.
- File `wireprotocol.md` defines a generic wire protocol. Use this to access the `Autopilot` object if the testbed and the autopilot are not in the same process.
