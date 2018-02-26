/**
 * @author Jeremy Wong & Gary Cunningham
 * Course: ISTE 121
 * Mini Project: Mortal Kombat Zombie Breakthrough Game
 * Description: A game that resembles zombie chess while incorporating Mortal Kombat as a theme
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class MortalKombat extends JFrame
{

   private ImageIcon scorpion, dragon, subZero, kombatPic, secKombatPic;
   private JButton btnNewGame, btnFirst, btnSecond;
   String turn = "Scorpion";
   private JLabel turnText;
   private String winner;
   private int firstRow, firstCol, secondCol, secondRow;
   private JPanel north, south, center;
   private int tracker, scorpWins,subWins,totalGames;
   private JButton [][] pieces;
   private JMenuItem menuNew, stat, exit, about;
   
  
   public MortalKombat()
   {
      
      north = new JPanel();
      center = new JPanel(new GridLayout(8, 8));
      south = new JPanel();
      
      /**
       * Creates the menu bar and sets the bar to the frame
       **/
      JMenuBar menu = new JMenuBar();
      setJMenuBar(menu);
      
      /**
       * Creates the file menu and its respective menu items
       **/
      JMenu file = new JMenu("File");
      menuNew = new JMenuItem("New Game");
      stat = new JMenuItem("Stats");
      exit = new JMenuItem("Exit");
      
      /**
       * Creates the help menu and its respective menu item
       **/
      JMenu help = new JMenu("Help");
      about = new JMenuItem("About Game");
      
      /**
       * Adds the menu items to the file menu
       **/
      file.add(menuNew);
      file.add(stat);
      file.add(exit);
      
      /**
       * Adds the menu items to the help menu
       **/
      help.add(about);
      
      /**
       * Adds the menus to the bar
       **/
      menu.add(file);
      menu.add(help);
      
      /**
       * Sets the mnemonic for each menu and menu item
       **/
      help.setMnemonic('H');
      file.setMnemonic('F');
      menuNew.setMnemonic('N');
      stat.setMnemonic('S');
      exit.setMnemonic('E');
      about.setMnemonic('A');
      
     /**
      * Creates the label for the GUI program
      **/
      turnText = new JLabel("Mortal Kombaaaaat!!!: It is " + turn + "'s turn.");
      
      /**
       * Creates the image icons and the array for the pieces
       **/
      scorpion = new ImageIcon("images/scorpion.gif");
      subZero = new ImageIcon("images/subZero.gif");
      dragon = new ImageIcon("images/dragon.jpg");
      pieces = new JButton[8][8];
      
      /**
       * Loops through the array and designates the image icons to their respective places on the board
       **/
      for(int firstRow = 0; firstRow < 8; firstRow++)
      {
         for(int firstCol = 0; firstCol < 8; firstCol++)
         {
           
            if(firstRow >= 2 && firstRow < 6)
            {
               pieces[firstRow][firstCol] = new JButton(dragon);
            }
            else if(firstRow < 2)
            {
               pieces[firstRow][firstCol] = new JButton(scorpion);
            }
            else if(firstRow == 6 || firstRow == 7)
            {
               pieces[firstRow][firstCol] = new JButton(subZero);
            }
            center.add(pieces[firstRow][firstCol]);
         }
      }
      
      /**
       * Creates the button for a new game and sets mnemonic
       **/
      btnNewGame = new JButton("New Game");
      btnNewGame.setMnemonic('N');
      
      north.add(turnText);
      south.add(btnNewGame);
      
      /**
       * Adds the panels to the frame
       **/
      add(north, BorderLayout.NORTH);
      add(center, BorderLayout.CENTER);
      add(south, BorderLayout.SOUTH);
      
      /**
       * Adds the ActionListeners to the JComponents
       **/
      GameListener gameTime = new GameListener(scorpion,dragon,subZero,kombatPic,secKombatPic,btnNewGame,btnFirst,btnSecond,turn,turnText,winner,
      firstRow,firstCol,secondCol,secondRow,north,south,center,tracker,scorpWins,subWins,totalGames,pieces,menuNew,stat,exit,about);
      
      /**
       * Adds ActionListener to the buttons via loop through the array of buttons
       **/
      for(int firstRow = 0; firstRow < 8; firstRow++)
      {
         for(int firstCol = 0; firstCol < 8; firstCol++)
         {
            pieces[firstRow][firstCol].addActionListener(gameTime);
         }
      }
      
      menuNew.addActionListener(gameTime);
      stat.addActionListener(gameTime);
      btnNewGame.addActionListener(gameTime);
      about.addActionListener(gameTime);
      exit.addActionListener(gameTime);
      
      
      /**
       * Sets the title for the GUI
       * Sets the location
       * Sets the visibility
       * Sets the default close operation
       **/
      pack();
      setTitle("Breakthrough Game");
      setLocationRelativeTo(null);
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
   
   
 
   
    
   public static void main(String []args)
   {
      MortalKombat combat = new MortalKombat();
   }
}