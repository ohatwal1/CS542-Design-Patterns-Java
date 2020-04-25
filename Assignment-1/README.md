# CSX42: Assignment 1
## Name: Omkar Hatwalne 

-----------------------------------------------------------------------
-----------------------------------------------------------------------

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile coursesRegistration/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------

## Instruction to compile:

####Command: ant -buildfile coursesRegistration/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------

## Instruction to run:

#-Darg0= File path of student course preferences(coursesRegistration/src/student_coursePrefs.txt)
#-Darg1= File path of courses info (coursesRegistration/src/courseInfo.txt)
#-Darg2= File path of output file (coursesRegistration/src/regestration_results.txt)

####Command: ant -buildfile coursesRegistration/src/build.xml run -Darg0="student_coursePrefs.txt" -Darg1="courseInfo.txt" -Darg2="regestration_results.txt"

Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------

#Data Structures used in code :

#List to add students
#ArrayList
#Time complexity : 

 #   Add: Amortized O(1)
 #   Contains: O(n)
 #   Size: O(1)

#Map to store students based on their year
#HashMap
#Time complexity :

    #Insert/Delete: O(1) amortized
    #Contains: O(1)

#Queue to maintaing first come first serve students
#LinkedList
#Time complexity :

    #Insert: O(1)
    #Remove: O(1)
    #Size: O(1)
	
#Set to check wheather student enrolled with class timings
#HashSet
#Time complexity :

    #Insert/Delete: O(1) amortized
    #Contains: O(1)

-----------------------------------------------------------------------
	
#Format for students that were assigned < 3 courses:
#<student1_id>:<course_1>,<course_2>,
#<student1_id>:<course_1>,

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 09/19/2019

-----------------------------------------------------------------------