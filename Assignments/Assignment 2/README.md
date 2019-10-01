# Assignment 2:

### Problem Description:

Remember to properly comment your code. Comments should precede variables, method declarations, and major steps in your code.

Write a program which simulates a race between two contenders. Use the Java secure random number generator class to simulate the movements of the two racers. The race will continue until there is a winner (or a tie). The race track consists of 100 squares and the first racer to reach or pass the final square (i.e. 100) wins the race. The course weaves its way up the side of a slippery mountain, so occasionally the contenders lose ground. A clock ticks once per second (i.e. iteration). With each tick of the clock, your program should adjust the position of the racers according to the rules shown in Figure 1. Use variables to keep track of the positions of the racers (i.e., position numbers are 1–100). Start each racer at position 1 (the "starting gate"). If the racer slips before square 1, move it back to square 1 (i.e. no negative positions).
In each iteration of the simulation, move both racers and use the printf method to print the positions of each racer at the end of each iteration. If both racers land on the same square during the race, print the word IT's A TIE. When there is a winner, stop the race and indicate the winner; remember to use printf.
Begin the race by printing a proper message (e.g. On Your Mark, Get Set, Go)

### You must include the following in your class:

- At least one use of a static member variable
- Overload the toString􏰂􏰃 method. The method returns the string "Race simulation class".
- At least two methods in addition to main􏰂􏰃 and toString􏰂􏰃
- At least one use of WHILE loop statement
- At least one use of FOR loop statement
- At least one use of SWITCH statement
- At the end of the race print the winner and the total number of iterations the program went through.

### Hints:

- In order to simulate the percentages in Figure 1, generate a random integer 𝒳 such that... 

- Each tick of the clock is a loop iteration. For example, 100 ticks on the clock are translated to 100 iterations. At the end of each iteration (tick) display the positions using printf as shown in Figure 2.

- Think of the 100 square runway as shown below. A right move means, advance forward, a left move means backwards. Note that you do not need an array to represent the course.

## [REST OF DETAILS IN Prompt.pdf]
