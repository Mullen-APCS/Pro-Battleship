package com.kausch.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rkausch
 */
public class BattleShipGame
	{
	/**
	 * The default board size, representing a square of equal length and width.
	 */
	private static int DEFAULT_BOARD_SIZE = 10;

	/**
	 * The size of the board, representing both the length and the width.
	 */
	private int boardSize;

	/**
	 * Launches the battleship application, processing the supplied set of
	 * command line arguments to modify application behavior.
	 * 
	 * @param pArguments
	 *            the set of command line arguments supplied during application
	 *            invocation.
	 */
	public static void main(String[] pArguments)
		{
		BattleShipGame gameInstance = new BattleShipGame();
		try
			{
			gameInstance.processArguments(pArguments);
			} catch (Throwable t)
			{
			System.out.println("An error was encountered: " + t.getMessage());
			gameInstance.printHelp();
			System.exit(1);
			// terminate early, the user did not supply the correct parameter
			// count or values.
			}
		gameInstance.execute();
		}

	/**
	 * Creates a new instance of the battleship application.
	 */
	public BattleShipGame()
		{
		setBoardSize(DEFAULT_BOARD_SIZE);
		}

	/**
	 * Processes the supplied parameters, extracting information and storing
	 * information in member fields.
	 * 
	 * @param pArguments
	 *            the set of arguments to process.
	 */
	public void processArguments(String[] pArguments)
		{
		int argumentCount = pArguments.length;

		if (argumentCount % 2 != 0)
			{
			throw new RuntimeException(
					"The parameters must be supplied in pairs during method invocation.");
			}

		for (int argumentIndex = 0; argumentIndex < argumentCount; argumentIndex += 2)
			{
			String argumentName = pArguments[argumentIndex];
			String argumentValue = pArguments[argumentIndex + 1];

			processArgument(argumentName, argumentValue);
			}

		}

	/**
	 * Processes the named parameter, performing additional data conversions as
	 * needed. The supplied value will be stored in the appropriate member
	 * variable. Neither value can be null during invocation, or the method will
	 * fail.
	 * 
	 * @param pArgumentName
	 *            the name of the argument to process (which should still
	 *            contain the leading dash).
	 * @param pArgumentValue
	 *            the value of the argument to process (which must not be null).
	 */
	protected void processArgument(String pArgumentName, String pArgumentValue)
		{
		if (!pArgumentName.startsWith("-"))
			{
			throw new RuntimeException(
					"Unable to process argument that does not start with a dash. The user supplied '"
							+ pArgumentName
							+ "', but should have supplied -'"
							+ pArgumentName + "'");
			}

		String trimmedArgumentName = pArgumentName.substring(1);

		// always order the constant first, then the variable in .equals
		// comparisons, to avoid null pointer exceptions.
		if ("board-size".equals(trimmedArgumentName))
			{
			setBoardSize(Integer.parseInt(pArgumentValue));
			} else
			{
			throw new RuntimeException(
					"The caller provided an unrecognized argument ('"
							+ pArgumentName + "'");
			}

		}

	/**
	 * Executes the application. This method contains an execution loop, which
	 * will keep the application alive until some condition has been met.
	 */
	public void execute()
		{
		GameBoard playerOneBoard = createPlayerBoard(Player.ONE);
		GameBoard playerTwoBoard = createPlayerBoard(Player.TWO);

		// TODO: Replace the following with a game execution loop, in which user
		// input is accepted, and
		// the game continues until one player wins or loses
		// **********************************************************************
		System.out.println("Player one board: ");
		System.out.println(playerOneBoard);

		System.out.println();
		System.out.println("Player two board: ");
		System.out.println(playerTwoBoard);
		}

	/**
	 * Creates a game board for the supplied player. The initial implementation
	 * creates a board of the size defined in {@link #getBoardSize()}, with a
	 * single {@link AircraftCarrier}, a single {@link Battleship}, two
	 * {@link Destroyer}s, and four {@link Submarine}s randomly placed on the
	 * board.
	 * 
	 * @param pPlayer
	 *            the player for which to create the board.
	 * @return A fully populated game board.
	 */
	protected GameBoard createPlayerBoard(Player pPlayer)
		{
		GameBoard board = new GameBoard(getBoardSize());
		List<AbstractShip> ships = new ArrayList<>();
		ships.add(new AircraftCarrier(pPlayer));
		ships.add(new Battleship(pPlayer));

		int destroyerCount = 2;
		for (int i = 0; i < destroyerCount; i++)
			{
			ships.add(new Destroyer(pPlayer));
			}

		int submarineCount = 4;
		for (int i = 0; i < submarineCount; i++)
			{
			ships.add(new Submarine(pPlayer));
			}

		for (AbstractShip ship : ships)
			{
			ship.placeOnBoard(board);
			}
		return board;
		}

	/**
	 * Prints a help message to standard output, explaining how to invoke the
	 * application.
	 */
	protected void printHelp()
		{
		System.out.println("Usage: %> java -classpath bin "
				+ this.getClass().getName() + " -board-size <int>");
		System.out.println("\tArguments:");
		System.out
				.println("\t\tboard-size\tthe number of squares to use in the board, expressed as a positive integer.");
		}

	/**
	 * Sets the value of the {@link #boardSize} field.
	 * 
	 * @param pBoardSize
	 *            the value to store in the {@link #boardSize} field.
	 */
	public void setBoardSize(int pBoardSize)
		{
		boardSize = pBoardSize;
		}

	/**
	 * Gets the value of the {@link #boardSize} field.
	 * 
	 * @return the value of the {@link #boardSize} field.
	 */
	public int getBoardSize()
		{
		return boardSize;
		}
	}
