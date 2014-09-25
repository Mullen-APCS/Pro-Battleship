package com.kausch.battleship;

/**
 * A container representing a square game board.
 * 
 * @author rkausch
 */
public class GameBoard
	{
	/**
	 * The size of the board, representing both length and width. This is a
	 * read-only member field.
	 */
	private int size;

	/**
	 * The actual storage of the board. Note that not all positions will be
	 * occupied, only those with a ship present will actually contain data (the
	 * rest will be null).
	 */
	private ShipSection[][] board;

	/**
	 * Creates a new game board, initializing it with the supplied size.
	 * 
	 * @param pSize
	 *            the length and width to initialize the game board.
	 */
	public GameBoard(int pSize)
		{
		size = pSize;
		board = new ShipSection[size][size];
		}

	/**
	 * Gets the value of the {@link #size} field.
	 * 
	 * @return the value of the {@link #size} field.
	 */
	public int getSize()
		{
		return size;
		}

	/**
	 * Tests to determine if a ship of the given length can fit at the specified
	 * coordinates, in the requested direction. Returns true if nothing occupies
	 * the locations on the board, false otherwise.
	 * 
	 * @param pRow
	 *            the row coordinate to test.
	 * @param pColumn
	 *            the column coordinate to test.
	 * @param pLength
	 *            the length of the ship for which to test.
	 * @param pHorizontal
	 *            a flag used to specify direction (supply true to indicate a
	 *            horizontal ship, false to indicate a vertical one).
	 * @return true if a ship of the requested length can be placed at the
	 *         specified coordinate in the specified direction.
	 */
	public boolean verifySpaceAvailable(int pRow, int pColumn, int pLength,
			boolean pHorizontal)
		{
		// assume there is space, until proven otherwise:
		boolean returnValue = true;

		if (pHorizontal == true)
			{
			if (pColumn + pLength >= getSize())
				{
				returnValue = false;
				}

			for (int column = pColumn; column < pColumn + pLength; column++)
				{
				if (board[pRow][column] != null)
					{
					returnValue = false;
					}
				}
			} else
			{
			if (pRow + pLength >= getSize())
				{
				returnValue = false;
				}

			for (int row = pRow; row < pRow + pLength; row++)
				{
				if (board[row][pColumn] != null)
					{
					returnValue = false;
					}
				}
			}

		return returnValue;
		}

	/**
	 * Stores the supplied ship section on the board, extracting the coordinates
	 * from the supplied object.
	 * 
	 * @param pShipSection
	 *            the section to store on the board.
	 */
	public void placeShipSection(ShipSection pShipSection)
		{
		board[pShipSection.getXLocation()][pShipSection.getYLocation()] = pShipSection;
		}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		for (int row = 0; row < getSize(); row++)
			{
			builder.append(row).append(" ");

			for (int column = 0; column < getSize(); column++)
				{
				ShipSection boardEntry = board[row][column];
				if (boardEntry == null)
					{
					builder.append("[ ]");
					} else
					{
					if (boardEntry.isHit())
						{
						builder.append("[")
								.append(boardEntry.getShip().getDisplay()
										.toUpperCase()).append("]");
						} else
						{
						builder.append("[")
								.append(boardEntry.getShip().getDisplay()
										.toLowerCase()).append("]");
						}
					}
				}
			builder.append('\n');
			}

		builder.append("  ");
		for (int column = 0; column < getSize(); column++)
			{
			builder.append(" ");
			builder.append(column);
			builder.append(" ");
			}

		return builder.toString();
		}

	}
