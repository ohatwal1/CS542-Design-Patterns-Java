# CSX42: Assignment 2
## Name: Omkar Hatwalne 

-----------------------------------------------------------------------
-----------------------------------------------------------------------

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile studentCoursePlanner/src/build.xml clean



Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------

## Instruction to compile:

####Command: ant -buildfile studentCoursePlanner/src/build.xml compile_all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------

## Instruction to run:

#-Darg0= File path of input file(studentCoursePlanner/src/input.txt)
#-Darg1= File path of output file (studentCoursePlanner/src/output.txt)

####Command: ant -buildfile /home/ohatwal1/DP/omkar_hatwalne_assign2/studentCoursePlanner/src/build.xml run -Darg0="input.txt" -Darg1="output.txt"


Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------

#Data Structures used in code :

#List to add students
#ArrayList
#Time complexity : 

 #   Add: Amortized O(1)
 #   Contains: O(n)
 #   Size: O(1)

#Map to store students with student details 
#Map to store group name and courses
#HashMap
#Time complexity :

    #Insert/Delete: O(1) amortized
    #Contains: O(1)

#Queue to maintaing waitlist of students
#LinkedList
#Time complexity :

    #Insert: O(1)
    #Remove: O(1)
    #Size: O(1)
	

-----------------------------------------------------------------------
	
#Format for students that were assigned < 3 courses:
#<student1_id>:<course completed>,<course completed>
#<student1_id>:Student can not graduate

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 10/08/2019

-----------------------------------------------------------------------