# CSX42: Assignment 4
## Name: Omkar Hatwalne 

-----------------------------------------------------------------------

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile troubleShootSearch/src/build.xml clean


Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------

## Instruction to compile:

####Command: ant -buildfile troubleShootSearch/src/build.xml compile_all 

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------

## Instruction to run:

#-Darg0= File path of technicalInfo file(troubleShootSearch/src/technicalInfo.txt)

#-Darg1= File path of userInput file(troubleShootSearch/src/userInput.txt)

#-Darg2= File path of synonyms  file(troubleShootSearch/src/synonyms.txt)

#-Darg3= File path of output file (troubleShootSearch/src/output.txt)

#-Darg4= 2 (0:NO_OUTPUT, 1:WARNINGS , 2:SUCCESS , 3:INPUT_READ , 4:OPERATION_ADD)

####Command: ant -buildfile /home/ohatwal1/DP/omkar_hatwalne_assign4/troubleShootSearch/src/build.xml run -Darg0="technicalInfo.txt" -Darg1="userInput.txt" -Darg2="synonyms.txt" -Darg3="output.txt" -Darg4=2


Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------

Total number of Slack Days used: 1

-----------------------------------------------------------------------

### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 11/18/2019

-----------------------------------------------------------------------
