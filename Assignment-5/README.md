# CSX42: Assignment 5
## Name: Omkar Hatwalne 

-----------------------------------------------------------------------

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile genericCheckpointing/src/build.xml clean


Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------

## Instruction to compile:

####Command: ant -buildfile genericCheckpointing/src/build.xml compile_all 

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------

## Instruction to run:

#-Darg0= deserser #(Mode)

#-Darg1= File path of checkpoint file name(genericCheckpointing/src/MyAllTypes2.txt)

#-Darg2= File path of checkpoint-verify file name(genericCheckpointing/src/MyAllTypesOutput.txt)

#-Darg3= 4 (0:NO_OUTPUT, 1:WARNINGS , 2:SUCCESS , 3:INPUT_READ , 4:OPERATION_ADD)


####Command: ant -buildfile /home/ohatwal1/DP/omkar_hatwalne_assign5/genericCheckpointing/src/build.xml run -Darg0="deserser" -Darg1="MyAllTypes2.txt" -Darg2="MyAllTypesOutput.txt" -Darg3=4



Note: Arguments accept the absolute path of the files.


-----------------------------------------------------------------------
