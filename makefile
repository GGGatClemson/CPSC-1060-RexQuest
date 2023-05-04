default: Main.java FileOutputs.java Enemy.java Player.java KeyInput.java Room.java
	javac Main.java FileOutputs.java Enemy.java Player.java KeyInput.java Room.java

run: Main.class FileOutputs.class Enemy.class Player.class KeyInput.class Room.class
	java Main

clean:
	rm -f *.class