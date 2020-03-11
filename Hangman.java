import java.util.*;
import java.io.*;
public class Hangman 
{
	static Random rand = new Random();
	static Scanner scan = new Scanner(System.in);
	static String word, guess;
	static Character[] wordArray;
	static boolean[] booleanArray;
	static ArrayList<String> lettersGuessed = new ArrayList<String>();
	static int guesses;
	static int WORD_COUNT;
	static boolean stopped = false;

	public Hangman() throws IOException
	{
		WORD_COUNT = howManyWordsAreInTheTextFileProvided();
	}

	//********************************************
	// Determines if input is word or letter guess
	// true = letter guess
	// false = word guess
	//********************************************
	public static boolean stringInterpreter(String guess)
	{
		if (guess.length() > 1)
			return false;
		return true;
	}

	//************************************************
	// Determines if the user won.
	// true = User guessed word or guessed all letters
	// false = User did not win
	//************************************************
	public static boolean checkWin()
	{
		boolean won = true;
		for (int i = 0; i < word.length(); i++)
			if (booleanArray[i] == false)
				won = false;
		return won;
	}

	//***********************************************************
	// Prints out the instructions at the beginning of each round
	//***********************************************************
	public static void printInstructions()
	{
		System.out.println("\nWelcome to Hangman. You have 6 tries to guess the randomly picked word. You can guess either individual letters or entire words at a time. Good luck!");
		System.out.println(" - Enter \"STOP\" to stop the game at any time.");
		System.out.println(" - Enter \"GUESS WORD\" to guess a word.");
		System.out.println(" - Enter \"HELP\" to see this message again.");

	}

	//***************************************************
	// Two uses:
	// Prints out the word when the user wins
	// Prints out what parts of the word user has guessed
	//***************************************************
	public static void printWord()
	{
		String returnMsg = "";
		for (int i = 0; i < word.length(); i++)
		{
			if (booleanArray[i] == true)
				returnMsg += " " + wordArray[i].toString() + " ";
			else
				returnMsg += " _ ";
		}
		System.out.println(returnMsg);

		if (lettersGuessed.size() > 0)
		{
			// prints out list of all guesses (correct & incorrect)
			String returnMsg2 = "You already guessed: ";
			for (int i = 0; i < lettersGuessed.size(); i++)
				returnMsg2 += lettersGuessed.get(i) + ", ";
			System.out.println(returnMsg2);
		}

		// prints out number of remaining guesses
		System.out.println("[GUESSES LEFT: " + guesses + "]");
	}

	//******************************************
	// Choose what word is chosen from input.txt
	//******************************************
	public static String chooseWord() throws IOException
	{
		Scanner scanWord = new Scanner(new File("input.txt"));
		String word = "";
		int random = rand.nextInt(WORD_COUNT) + 1; //1-WORD_COUNT
		for (int i = 0; i < WORD_COUNT; i++)
		{
			word = scanWord.next();
			if (i == random)
				break;
		}
		//System.out.println("word: " + word); // for testing
		return word;
	}

	//********************************************************
	// Finds out how many words are in the text file provided.
	//********************************************************
	public static int howManyWordsAreInTheTextFileProvided() throws IOException
	{
		Scanner scanWord = new Scanner(new File("input.txt"));
		int returnCount = 0;
		while (scanWord.hasNext())
		{
			returnCount++;
			scanWord.next();
		}
		return returnCount;
	}

	//*************************************
	// Checks if letter was already guessed
	// True = letter was already guessed
	// False = letter was not guessed
	//*************************************
	public static boolean checkLetter(String guess)
	{
		boolean returnVal = false;
		for (int i = 0; i < lettersGuessed.size(); i++)
		{
			if (guess.equals(lettersGuessed.get(i)))
				returnVal = true;
		}
		return returnVal;
	}

	//*********************************
	// Checks if the input given is a-z
	// True = if guess is good
	// False = theres problems
	//*********************************
	public static boolean isTheGuessInputProperSyntax(String guess)
	{
		boolean returnVal = true;
		for (int i = 0; i < guess.length(); i++)
		{
			if (guess.charAt(i) < 97 || guess.charAt(i) > 122) // if letter is not a-z or A-Z
				returnVal = false;
		}
		if (guess.length() == 0)
			returnVal = false;
		return returnVal;
	}

	//**********************************
	// Prints out picture of the hangman
	//**********************************
	public static void printASCII(int i)
	{
		if (i == 6)
		{
			System.out.println(" _________     ");
			System.out.println("|         |    ");
    		System.out.println("|         |    ");
   			System.out.println("|              ");
   			System.out.println("|              ");
    		System.out.println("|              ");
    		System.out.println("|              ");
   			System.out.println("|              ");
		}
		else if (i == 5)
		{
			System.out.println(" _________     ");
			System.out.println("|         |    ");
    		System.out.println("|         |    ");
   			System.out.println("|         0    ");
   			System.out.println("|              ");
    		System.out.println("|              ");
    		System.out.println("|              ");
   			System.out.println("|              ");
		}
		else if (i == 4)
		{
			System.out.println(" _________     ");
			System.out.println("|         |    ");
    		System.out.println("|         |    ");
   			System.out.println("|         0    ");
   			System.out.println("|         |    ");
    		System.out.println("|              ");
    		System.out.println("|              ");
   			System.out.println("|              ");
		}
		else if (i == 3)
		{
			System.out.println(" _________     ");
			System.out.println("|         |    ");
    		System.out.println("|         |    ");
   			System.out.println("|         0    ");
   			System.out.println("|        /|    ");
    		System.out.println("|              ");
    		System.out.println("|              ");
   			System.out.println("|              ");
		}
		else if (i == 2)
		{
			System.out.println(" _________     ");
			System.out.println("|         |    ");
    		System.out.println("|         |    ");
   			System.out.println("|         0    ");
   			System.out.println("|        /|\\  ");
    		System.out.println("|              ");
    		System.out.println("|              ");
   			System.out.println("|              ");
		}
		else if (i == 1)
		{
			System.out.println(" _________     ");
			System.out.println("|         |    ");
    		System.out.println("|         |    ");
   			System.out.println("|         0    ");
   			System.out.println("|        /|\\  ");
    		System.out.println("|        /     ");
    		System.out.println("|              ");
   			System.out.println("|              ");
		}
		else // if (i == 0)
		{
			System.out.println(" _________     ");
			System.out.println("|         |    ");
    		System.out.println("|         |    ");
   			System.out.println("|         0    ");
   			System.out.println("|        /|\\  ");
    		System.out.println("|        / \\  ");
    		System.out.println("|              ");
   			System.out.println("|              ");
		}
	}

	//*******************************
	// User decided to guess a letter
	//*******************************
	public static void guessLetter()
	{
		boolean bool = false;
		for (int i = 0; i < word.length(); i++)
		{
			if (guess.equalsIgnoreCase(wordArray[i].toString()))
			{
				booleanArray[i] = true;
				bool = true;
			}
		}
		if (bool)
			System.out.println("You guessed \"" + guess + "\" correctly!");
		else
		{
			if (!stopped)
			{
				System.out.println("You guessed incorrectly! You lose a life!");
				guesses--;
			}
		}
	}

	//*****************************************
	// Runs code
	//*****************************************
	public static void run() throws IOException
	{
		Hangman.printInstructions();
		System.out.println("Do you understand the rules and want to play? (YES/NO)");
		String response = scan.nextLine();
		while (!response.equals("YES") && !response.equals("NO"))
		{
			System.out.println("Only (YES/NO) responses accepted:");
			response = scan.nextLine();
		}

		boolean playing = true;
		if (response.equals("NO"))
			playing = false;

		while (playing)
		{
			// reset from previous rounds
			word = (Hangman.chooseWord()).toLowerCase(); // chooses word
			//word = "apple"; // rigged for testing remove later

			wordArray = new Character[word.length()];
			for (int i = 0; i < word.length(); i++) // fills character array with letters in the word
				wordArray[i] = word.charAt(i);

			booleanArray = new boolean[word.length()];
			for (int i = 0; i < word.length(); i++) // fills booleanArray with falses
				booleanArray[i] = false;

			boolean winConditionMet = false; // resets win condition
			boolean stopped = false;
			lettersGuessed.clear();
			guesses = 6; //subject to change

			// each turn in the same round
			while (!winConditionMet && !stopped)
			{
				boolean cancelledWordGuess = false;
				Hangman.printASCII(guesses);
				Hangman.printWord();
				System.out.println("Enter your letter guess or put \"GUESS WORD\" to guess a word:");
				guess = scan.nextLine();

				if (!guess.equals("GUESS WORD") && !guess.equals("HELP") && !guess.equals("STOP")) // runs unless input is keyword "GUESS WORD"
				{
					while (!Hangman.isTheGuessInputProperSyntax(guess) || checkLetter(guess)) // while bad input or letter guessed before
					{
						if (guess.equals("HELP"))
							Hangman.printInstructions();
						if (guess.equals("STOP"))
						{
							stopped = true;
							break;
						}
						if (guess.equals("GUESS WORD"))
							break;
						if (!Hangman.isTheGuessInputProperSyntax(guess))
							System.out.println("ERROR: Only letters a-z allowed.");
						if (checkLetter(guess))
							System.out.println("ERROR: input was already guessed.");
						System.out.println("Enter guess: ");
						guess = scan.nextLine();
					}
				}

				// checks keywords "stop" "help" "guess word"
				if (guess.equals("STOP"))
				{
					stopped = true;
				}
				else if (guess.equals("HELP"))
				{
					Hangman.printInstructions();
				}
				else if (guess.equals("GUESS WORD")) // user wants to guess a word
				{
					System.out.println("Enter your word guess: (or enter \"CANCEL\" to guess a letter)");
					guess = scan.nextLine();

					if (guess.equals("STOP"))
					{
						stopped = true;
						break;
					}
					//else if (guess.equals("HELP"))
					//{
					//	Hangman.printInstructions();
					//}

					if (!guess.equals("CANCEL")) // checks syntax of word as long as it isnt CANCEL HELP or STOP
					{
						while (!Hangman.isTheGuessInputProperSyntax(guess) || checkLetter(guess) || (guess.length() != word.length())) // while bad input or letter guessed before
						{
							if (guess.equals("CANCEL"))
								break;
							if (guess.equals("HELP"))
								Hangman.printInstructions();
							if (guess.equals("STOP"))
							{
								stopped = true;
								break;
							}
							if (!Hangman.isTheGuessInputProperSyntax(guess) && !guess.equals("HELP"))
								System.out.println("ERROR: Only letters a-z allowed.");
							if (checkLetter(guess) && !guess.equals("HELP"))
								System.out.println("ERROR: input was already guessed.");
							if (guess.length() != word.length() && !guess.equals("HELP"))
								System.out.println("ERROR: input is either longer or shorter than the word.");
							System.out.println("Enter your word guess: ");
							guess = scan.nextLine();
						}
					}

					if (guess.equals("CANCEL"))
					{
						cancelledWordGuess = true;
						System.out.println("Enter your letter guess:");
						guess = scan.nextLine();

						while (!Hangman.isTheGuessInputProperSyntax(guess) || checkLetter(guess) || guess.length() > 1) // while bad input or letter guessed before
						{
							if (guess.equals("HELP"))
								Hangman.printInstructions();
							if (guess.equals("STOP"))
							{
								stopped = true;
								break;
							}
							if (!Hangman.isTheGuessInputProperSyntax(guess) && !guess.equals("HELP"))
							System.out.println("ERROR: Only letters a-z allowed.");
							if (checkLetter(guess) && !guess.equals("HELP"))
								System.out.println("ERROR: input was already guessed.");
							if (guess.length() > 1 && !guess.equals("HELP"))
								System.out.println("ERROR: input was longer than one letter.");
							System.out.println("Enter your letter guess: ");
							guess = scan.nextLine();
						}
						
						Hangman.guessLetter();
						lettersGuessed.add(guess);
					}
					else // continue as normal
					{
						if (guess.equalsIgnoreCase(word))
							winConditionMet = true;
						else
						{
							if (!stopped)
							{
								System.out.println("You guessed incorrectly! You lose a life!");
								guesses--;
							}
						}
						//lettersGuessed.add(guess); // players are permitted to enter wrong word twice
					}
				}
				
				else if (Hangman.stringInterpreter(guess)) // if one letter input
				{
					Hangman.guessLetter();
					lettersGuessed.add(guess);
				}
				else // user entered a word without putting "guess word" before
				{
					System.out.println("ERROR: You entered a word without entering \"guess word\" before it.");
				}

				// end of the round stuff
				if (guesses < 1)
					break;
				if (Hangman.checkWin())
					winConditionMet = true;
			}
			if (stopped)
				break;
			else if (winConditionMet)
				System.out.println("You guessed \"" + word + "\" correctly!");
			else
			{
				Hangman.printASCII(guesses);
				System.out.println("You ran out of lives! You lose. The word was \"" + word + "\".");	
			}
			
			System.out.println("Do you wish to play again? (YES/NO)");
			String answer = scan.nextLine();
			while (!answer.equals("YES") && !answer.equals("NO"))
			{
				System.out.println("Only (YES/NO) responses accepted:");
				answer = scan.next();
			}
			if (answer.equals("NO"))
				playing = false;
		}
		System.out.println("Thank you for playing!");
	}
}