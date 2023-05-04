/*
Gordon Gregory
CSPC 1060
May 2
KeyInput.java
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class holds my Jframe pannel and buttons, its uses for player inputs.
 * Ill add key presses in the future
 */
public class KeyInput {
    /**
     * these are out here bc i thought i needed to seperate the actionlisteners form the constuctor but you dont.
     */
    private JButton up = new JButton("UP");
    private JButton down = new JButton("DOWN");
    private JButton left = new JButton("LEFT");
    private JButton right = new JButton("RIGHT");
    private JButton stat = new JButton("Stats");

    /**
     * builds the JFrame and adds teh buttons to JPanel so you dont have to type or something else, super cool to learn
     * @param player
     */
    KeyInput(Player player){
        JFrame win = new JFrame();
        win.setVisible(true);
        win.setSize(315,210);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setFocusable(true);

        //just some basic button sizing and postion, would like to add custom icons
        up.setBounds(110,10,80,40);
        down.setBounds(110,60,80,40);
        left.setBounds(15,60,80,40);
        right.setBounds(205,60,80,40);
        stat.setBounds(110,110,80,40);


        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(up);
        panel.add(down);
        panel.add(left);
        panel.add(right);
        panel.add(stat);


        //These check for button presses and then tell the player how to move
        win.add(panel);
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.movePlayer("UP");
            }
        });
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.movePlayer("DOWN");
            }
        });
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.movePlayer("LEFT");
            }
        });
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.movePlayer("RIGHT");
            }
        });
        stat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.printStats();
            }
        });
    }
}

