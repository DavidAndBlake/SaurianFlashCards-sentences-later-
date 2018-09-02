import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.lang.invoke.WrongMethodTypeException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/***********************************************************************************************
 * David Frieder's Saurian flash cards Copyright 2018 David Frieder 8/28/2018
 * rev 1.2 displays alphabets vertically to make it easier to tell which letter corresponds to which.
 ***********************************************************************************************/
public class FlashCards extends JComponent implements KeyListener 
{

	public static int cardPicker;
	public static int correctScore;
	public static int wrongAnswerCounter;
	public static int totalTrials;
	public static int trialsLimit;
//	public static String englishAlphabet = new String("<html>A<br>B<br>C<br>D<br>E<br>F<br>G<br>H<br>I<br>J<br>K<br>L<br>M<br>N<br>O<br>P<br>Q<br>R<br>S<br>"
//			+ "T<br>U<br>V<br>W<br>X<br>Y<br>Z<html>");
//	public static String saurianUpperCaseAlphabet = new String("<html>U<br>R<br>S<br>T<br>O<br>V<br>W<br>X<br>A<br>Z<br>B<br>C<br>M<br>"
//			+ "D<br>E<br>F<br>G<br>H<br>J<br>K<br>I<br>L<br>N<br>P<br>0<br>Q<html>");
	RoundRectangle2D.Double tester;
	public static String englishAlphabet = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	public static String saurianUpperCaseAlphabet = new String("URSTOVWXAZBCMDEFGHJKILNP0Q");
	public static String saurianLowerCaseAlphabet = new String("urstovwxazbcmdefghjkilnp0q");
	public JFrame letterFrame;
	public char enlgishLetter;
	public JLabel textLabel = new JLabel();
	public JLabel instructionsLabel = new JLabel();
	public JPanel letterPanel = new JPanel();
	public JLabel englishAlphabetLabel = new JLabel();
	public JLabel saurianAlphabetLabel = new JLabel();
	public Shape yellowEnglishBox = new RoundRectangle2D.Float(300, 50, 300, 300, 7, 7);

	public static void main(String[] args)
	{
		new FlashCards().getGoing();
	}
	public void getGoing()
	{
		cardPicker = (int) (Math.random() * 26);
		correctScore = 0;
		wrongAnswerCounter = 0;
		totalTrials = 0;
		trialsLimit = 20;
		letterFrame = new JFrame("Saurian Flashcards");
		enlgishLetter = (englishAlphabet.charAt(cardPicker));
		letterFrame.add(letterPanel);
		letterFrame.setSize(800, 700);
		letterPanel.setSize(600, 500);
		letterFrame.setVisible(true);
		letterFrame.setDefaultCloseOperation(letterFrame.EXIT_ON_CLOSE);
		letterPanel.add(instructionsLabel);
		letterPanel.add(textLabel);
		letterPanel.add(englishAlphabetLabel);
		letterPanel.add(saurianAlphabetLabel);
		englishAlphabetLabel.setFont(new Font("Bank Gothic", Font.BOLD, 16));
		saurianAlphabetLabel.setFont(new Font("Bank Gothic", Font.BOLD, 16));
		englishAlphabetLabel.setText(transformStringToHtml(englishAlphabet));
		saurianAlphabetLabel.setText(transformStringToHtml(saurianUpperCaseAlphabet));
		letterFrame.setLayout(new BorderLayout());
		instructionsLabel.setText("<html>Press the Saurian letter that corresponds with the English letter you see.<br>The Saurian alphabet corresponds like this:<html>");
		textLabel.setText("" + enlgishLetter);
		letterPanel.add(textLabel);
		textLabel.setFont(new Font("Bank Gothic", Font.BOLD, 372));
		letterFrame.addKeyListener(this);
		tester = new RoundRectangle2D.Double(333,333,333,333,333,333);
		letterFrame.add(this);
		letterFrame.repaint();
	}
	public static String transformStringToHtml(String strToTransform) {
	    String ans = "<html>";
	    String br = "<br>";
	    String[] lettersArr = strToTransform.split("");
	    for (String letter : lettersArr) {
	        ans += letter + br;
	    }
	    ans += "</html>";
	    return ans;
	}
	public void letterReset(JPanel letterPanel, char enlgishLetter, JLabel textArea)
	{
		cardPicker = (int) (Math.random() * 26);
		enlgishLetter = (englishAlphabet.charAt(cardPicker));
		textArea.setText("" + enlgishLetter);
		letterPanel.add(textArea);
	}
	@Override
	public void keyTyped(KeyEvent e)
	{

		if (e.getKeyChar() == (saurianUpperCaseAlphabet.charAt(cardPicker)) || e.getKeyChar() == (saurianLowerCaseAlphabet.charAt(cardPicker)))
		{
			 System.out.println("Correct");
			letterPanel.setBackground(Color.green);
			letterReset(letterPanel, enlgishLetter, textLabel);
			correctScore++;
		} else
		{
			 System.out.println("wrong");
			 System.out.println(saurianLowerCaseAlphabet.charAt(cardPicker));
			letterPanel.setBackground(Color.red);
			wrongAnswerCounter++;
		}
		totalTrials++;
		if (totalTrials >= trialsLimit)
		{
			instructionsLabel.setText("Congratulations. Here is your score:");
			letterPanel.setBackground(Color.yellow);
			textLabel.setFont(new Font("Bank Gothic", Font.PLAIN, 22));
			textLabel.setText("Correct answers: " + correctScore + "\nWrong answers: " + wrongAnswerCounter);
		}
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
	}
	@Override
	public void keyReleased(KeyEvent e)
	{
	}

}
