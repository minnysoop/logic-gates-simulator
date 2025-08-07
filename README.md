## Logic Gates Simulator
### Situation
In my computer organization class, we learned about converting boolean expressions into circuits. I wondered if I could write a computer program to do this.  

### Task/Problem
The computer program I wanted to create takes in a boolean expression from user input and outputs the corresponding circuit diagram. 

### Action
I created this program by first parsing the boolean expression into tokens. Then the program parses these tokens to check for syntax errors and then uses the Shunting Yard algorithm to translate the boolean expression into a postfix notation (i.e. a+b would become +ab). I built a tree based on this postfix expression and manually positioned and rendered the tree using the Java's 2D Graphics library. To render this tree I had the input pane communicate with the output pane via a constructor dependency injection.

### Result
As of now it only supports (~, +, *) but that's okay because the code knows how to handle unary and binary operators, which would make adding other operations like XOR, NAND, etc. a little easier. For really large boolean expressions, it's a lot hard to view it on the output pane, which is a fixed size. It's an area of future improvement to scale the visual dynamically. But, for now, below are some of the following inputs to try: 
- `~(A * B) + (C * ~D) + ~(E + F)`
- `~(~A + B) * (~~C + ~D) + ~(E * ~F)`
- ` ~(A * ~B) + (C * (D + ~E)) + ~(F + ~G)`
- `~(A * (~B + C)) + (D * ~~(E + ~F)) * (~(G + H) + I)`
- `(~(A + ~B) * (C + ~~D)) + ((~E * F) + ~(G * (H + ~I)))`

And, positioning elements on the pane relative to others was a challenge since it's not known how complicated the expression will be. Here is a snapshot,

<img width="1201" height="700" alt="Screenshot 2025-08-08 at 7 47 52 AM" src="https://github.com/user-attachments/assets/f8f51c9f-5361-4da1-873f-8c6a1b57b71d" />
