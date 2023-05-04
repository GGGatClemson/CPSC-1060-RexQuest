# RexQuest
Text output based tower climber.
Uses JFrame for inputs from JButtons
Has framework for harder levels
Enemy movment is based on player


Has make file to run the game, runs from main.java.

To Run:
default: Main.java FileOutputs.java Enemy.java Player.java KeyInput.java Room.java
	javac Main.java FileOutputs.java Enemy.java Player.java KeyInput.java Room.java

run: Main.class FileOutputs.class Enemy.class Player.class KeyInput.class Room.class
	java Main

clean:
	rm -f *.class
