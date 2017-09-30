# Shared Artifacts for P&O CW 2017-2018

- File `Autopilot.datatypes` defines the drone model and the datatypes `AutopilotConfig`, `AutopilotInputs`, and `AutopilotOutputs`.
- Directory `datatypes` implements a code generator for generating for each datatype defined in a given file three Java types:
  - a Java interface with a getter for each field of the datatype
  - a class that writes an instance of the datatype to a `DataOutputStream`
  - a class that reads an instance of the datatype from a `DataInputStream`
