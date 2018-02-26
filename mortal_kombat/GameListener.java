import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class GameListener implements ActionListener
{
   private ImageIcon scorpion, dragon, subZero, kombatPic, secKombatPic;
   private JButton btnNewGame, btnPressed, secBtnPressed;
   private String turn;
   private JLabel turnText;
   private String winner;
   private int firstRow, firstCol, secondCol, secondRow;
   private JPanel north, south, center;
   private int moveTrack, scorpWins,subWins,totalGames;
   private JButton [][] pieces;
   private JMenuItem menuNew, stat, exit, about;
   
   public GameListener(ImageIcon scorp, ImageIcon monster, ImageIcon sub,ImageIcon komPicOne, ImageIcon komTwo, JButton gamer, JButton btn, 
   JButton btnTwo, String yourTurn, JLabel text, String win, int rowOne, int colOne, int colSec, int rowSec, JPanel jpOne, 
   JPanel jpTwo, JPanel jpThree, int track, int scWins, int sWins, int total, JButton [][] place, JMenuItem mNew,
   JMenuItem status, JMenuItem close, JMenuItem info)
   {
      scorpion = scorp;
      dragon = monster;
      subZero = sub;
      kombatPic = komPicOne;
      secKombatPic = komTwo;
      btnNewGame = gamer;
      btnPressed = btn;
      secBtnPressed = btnTwo;
      turn = yourTurn;
      turnText = text;
      winner = win;
      firstRow = rowOne;
      firstCol = colOne;
      secondCol = colSec;
      secondRow = rowSec;
      north = jpOne;
      south = jpTwo;
      center = jpThree;
      moveTrack = track;
      scorpWins = scWins;
      subWins = sWins;
      totalGames = total;
      pieces = place;
      menuNew = mNew;
      stat = status;
      exit = close;
      about = info;
   
   
   } 
   
  
   public void actionPerformed(ActionEvent ae){
      Object pressed = ae.getSource();
      
      /**
       * Starts a new game
       **/
      if(ae.getSource() == btnNewGame || ae.getSource() == menuNew)
      {
         for(int firstRow = 0; firstRow < 8; firstRow++)
         {
            for(int firstCol = 0; firstCol < 8; firstCol++)
            {
               if(firstRow < 2)
               {
                  pieces[firstRow][firstCol].setIcon(scorpion);
               }
               else if(firstRow >= 2 && firstRow < 6)
               {
                  pieces[firstRow][firstCol].setIcon(dragon);
               }
               else if(firstRow == 6 || firstRow == 7)
               {
                  pieces[firstRow][firstCol].setIcon(subZero);
               }
            }
         }
         turn = "Scorpion";
         winner = null;
         moveTrack = 0;
         
      }
      
      /**
       * Upon clicking the stat menu item, stats are displayed for both players
       **/
      else if(ae.getSource() == stat)
      {
         String overallWinner = "";
         
         if(scorpWins > subWins)
         {
            overallWinner = "Overall Winner is Scorpion! Scorching Fatality!!!";
         }
         else if(scorpWins < subWins)
         {
            overallWinner = "Overall Winner is Sub-Zero! Freezing Brutality!!!";
         }
         else if(scorpWins == subWins)
         {
            overallWinner = "It's a draw!!";
         }
       
         String statistics = String.format("Games played: %d\n" + "Games won by Scorpion: %d\n" + "Games won by Sub-Zero: %d\n" + "%s", totalGames, scorpWins, subWins, overallWinner);
         JOptionPane.showMessageDialog(null, statistics);
      }
      
      /**
       * Closes the game
       **/
      else if(ae.getSource() == exit)
      {
         System.exit(0);
      }
      
      /**
       * Shows the rule and info for the game
       **/
      else if(ae.getSource() == about)
      {
         String about = String.format("Rules: \n" + "  Win by moving one piece to the opposite side. Pieces move one space forward\n" +
            "  or diagonally foward, and capture diagonally foward.\n" + "Capabilities:\n" + "- Pieces can move one square straight forward\n" +
            "- Pieces can move one square diagonally forward\n" + "- Scorpion can capture opponent square diagonnaly forward\n" + "and turn it into his minion\n" +
            "-Sub-Zero can push opponent back\n"+"- The first player whose piece reaches the other side is the winner\n" + "Constraints:\n" + "- Cannot move onto/capture your own piece\n" +
            "- Cannot capture opponent piece straight forward\n" + "- Cannot move backwards");
         JOptionPane.showMessageDialog(null, about);
      }
      
      else
      {
         /**
          * Checks for the validity of the player's move
          **/
         if(moveTrack == 1)
         {
            secBtnPressed = (JButton)pressed;
            /**
             * Tracks the location of where the player is moving on the board
             **/
            for(int rowSpace = 0; rowSpace < 8; rowSpace++)
            {
               for(int colSpace = 0; colSpace < 8; colSpace++)
               {
                  if(pieces[rowSpace][colSpace] == secBtnPressed)
                  {
                     secondRow = rowSpace;
                     secondCol = colSpace;
                  }
               }
            }
            
            secKombatPic = (ImageIcon)pieces[secondRow][secondCol].getIcon();
            
            
            /**
             * Activated when it is Scorpion's turn
             **/
            if(turn.equals("Scorpion"))
            {
               /**
                * Allows Scorpion to move forward 
                * Checks to see if Scorpion won
                **/
               if(secKombatPic.getDescription().equals("images/dragon.jpg") && secondRow == firstRow + 1 && secondCol == firstCol)
               {
                  pieces[firstRow][firstCol].setIcon(dragon);
                  pieces[secondRow][secondCol].setIcon(scorpion);
                  
                  turn = "Sub-Zero";
                  moveTrack = 0;
                   
                     if(secondRow == 7)
                     {
                        JOptionPane.showMessageDialog(null, "Scorpion WINS!!! Flawless Victory!");
                     
                        totalGames++;
                        scorpWins++;
                        winner = "Scorpion";
                     }
                  
                  
               }
               /**
                * Allows Scorpion to move diagonally
                * Checks to see if Scorpion won
                **/
               else if(secKombatPic.getDescription().equals("images/dragon.jpg") && secondRow == firstRow + 1 && (secondCol == firstCol + 1 || secondCol == firstCol - 1))
               {
                  pieces[firstRow][firstCol].setIcon(dragon);
                  pieces[secondRow][secondCol].setIcon(scorpion);
                  
                  turn = "Sub-Zero";
                  moveTrack = 0;
                  if(secondRow == 7)
                  {
                     JOptionPane.showMessageDialog(null, "SCORPION WINS!!! Get over here!!!");
                     
                     totalGames++;
                     scorpWins++;
                     winner = "Scorpion";
                  }
               }
               //Allows user to move scorpion forward one space diagonally and capture an opponent piece
               else if(secKombatPic.getDescription().equals("images/subZero.gif") && secondRow == firstRow + 1 && (secondCol == firstCol + 1 || secondCol == firstCol - 1))
               {
                  pieces[firstRow][firstCol].setIcon(scorpion);
                  pieces[secondRow][secondCol].setIcon(scorpion);
                  
                  turn = "Sub-Zero";
                  moveTrack = 0;
                  if(secondRow == 7)
                  {
                     JOptionPane.showMessageDialog(null, "SCORPION WINS!!! Come here!!!");
                     winner = "Scorpion";
                     scorpWins++;
                     totalGames++;
                  }
               }
               /**
                * Error if player attempts to move backward
                * Error if player attempts to capture opponent's piece when moving straightforward
                * Error if player attempts to capture his/her own piece
                * Error for any other illegal move
                **/
               else if(secondRow < firstRow)
               {
                  JOptionPane.showMessageDialog(null, "You cannot move backwards!");
                  moveTrack = 0;
               }
               else if(secKombatPic.getDescription().equals("images/subZero.gif") && secondRow == firstRow + 1 && secondCol == firstCol){
                  JOptionPane.showMessageDialog(null, "You cannot capture opponent piece straight foward");
                  moveTrack = 0;
               }
               else if(secKombatPic.getDescription().equals("images/scorpion.gif"))
               {
                  JOptionPane.showMessageDialog(null, "You cannot move onto / capture your own piece");
                  moveTrack = 0;
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "You cannot make that move.\n Please check 'About Game' if you are unsure of rules.");
                  moveTrack = 0;
               }
            }
            
            /**
             * Activates when it is Sub-Zero's turn
             **/
            else if(turn.equals("Sub-Zero"))
            {
               /**
                * Sub-Zero is allowed to move forward one space
                **/
               if(secKombatPic.getDescription().equals("images/dragon.jpg") && secondRow == firstRow - 1 && secondCol == firstCol)
               {
                  pieces[firstRow][firstCol].setIcon(dragon);
                  pieces[secondRow][secondCol].setIcon(subZero);
                  
                  turn = "Scorpion";
                  moveTrack = 0;
                  
                  if(secondRow == 0)
                  {
                     JOptionPane.showMessageDialog(null, "Sub Zero WINS!!! Well done!!!");
                     winner = "Sub-Zero";
                     subWins++;
                     totalGames++;
                  }
               }
               
               else if(secKombatPic.getDescription().equals("images/dragon.jpg") && secondRow == firstRow - 1 && (secondCol == firstCol + 1 || secondCol == firstCol - 1))
               {
                  pieces[firstRow][firstCol].setIcon(dragon);
                  pieces[secondRow][secondCol].setIcon(subZero);
                  
                  turn = "Scorpion";
                  moveTrack = 0;
                  
                  if(secondRow == 0)
                  {
                     JOptionPane.showMessageDialog(null, "Sub Zero WINS!!! Flawless Victory!!!");
                     winner = "Sub-Zero";
                     subWins++;
                     totalGames++;
                  }
               }
               /**
                * Sub-zero is allowed to move one space diagonally to capture his oppressor
                **/
               else if(secKombatPic.getDescription().equals("images/scorpion.gif") && secondRow == firstRow - 1 && (secondCol == firstCol + 1 || secondCol == firstCol - 1))
               {
                  pieces[firstRow][firstCol].setIcon(dragon);
                  pieces[secondRow][secondCol].setIcon(subZero);
                  pieces[secondRow-1][secondCol].setIcon(scorpion);
                  turn = "Scorpion";
                  moveTrack = 0;
                  if(secondRow == 0)
                  {
                     JOptionPane.showMessageDialog(null, "Finish him!!! Sub Zero WINS!!!");
                     winner = "Sub-Zero";
                     subWins++;
                     totalGames++;
                  }
               }
               /**
                * Error if player attempts to move backward
                * Error if player attempts to capture opponent's piece when moving straightforward
                * Error if player attempts to capture his/her own piece
                * Error for any other illegal move
                **/
               else if(secondRow > firstRow)
               {
                  JOptionPane.showMessageDialog(null, "You cannot move backwards!");
                  moveTrack = 0;
               }
               
               else if(secKombatPic.getDescription().equals("images/scorpion.gif") && secondRow == firstRow - 1 && secondCol == firstCol)
               {
                  JOptionPane.showMessageDialog(null, "You cannot capture opponent piece straight foward");
                  moveTrack = 0;
               }
               
               else if(secKombatPic.getDescription().equals("images/scorpion.gif"))
               {
                  JOptionPane.showMessageDialog(null, "You cannot move onto / capture your own piece");
                  moveTrack = 0;
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "You cannot make that move.\n Please check 'About Game' if you are unsure of rules.");
                  moveTrack = 0;
               }
            }
         }
         
         else
         {
            
            btnPressed = (JButton)pressed;
            for(int rowSpace = 0; rowSpace < 8; rowSpace++)
            {
               for(int colSpace = 0; colSpace < 8; colSpace++)
               {
                  if(pieces[rowSpace][colSpace] == btnPressed)
                  {
                     firstRow = rowSpace;
                     firstCol = colSpace;
                  }
               }
            }
            kombatPic = (ImageIcon)pieces[firstRow][firstCol].getIcon();
            /**
             * Game over if a winner can be found
             **/
            if(winner != null)
            {
               JOptionPane.showMessageDialog(null, "Game Over\nPlease Start a New Game");
            }
            /**
             * Valid move for Scorpion
             **/
            else if(kombatPic.getDescription().equals("images/scorpion.gif") && turn.equals("Scorpion"))
            {
               moveTrack = 1;
              
            }
            /**
             * Valid move for Sub-Zero
             **/
            else if(kombatPic.getDescription().equals("images/subZero.gif") && turn.equals("Sub-Zero"))
            {
               moveTrack = 1;
               
            }
            /**
             * Does not allow Sub-Zero to take Scorpion's turn
             * Does not allow Scorpion to take Sub-Zero's turn
             * Cannot move empty space
             **/
            else if(kombatPic.getDescription().equals("images/subZero.gif") && turn.equals("Scorpion"))
            {
               JOptionPane.showMessageDialog(null, "It is Scorpion's turn!");
            }
            else if(kombatPic.getDescription().equals("images/scorpion.gif") && turn.equals("Sub-Zero"))
            {
               JOptionPane.showMessageDialog(null, "It is Sub-Zero's turn!");
            }
            else if(kombatPic.getDescription().equals("images/dragon.jpg") && turn.equals("Scorpion"))
            {
               JOptionPane.showMessageDialog(null, "You cannot move this piece!");
            }
         }
      }
     
   }



}