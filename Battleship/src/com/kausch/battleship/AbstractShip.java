package com.kausch.battleship;

import java.util.Random;

/**
 * @author rkausch
 */
public class AbstractShip
	{
	/**
	 * The maximum number of attempts to make at board placement before giving
	 * up.
	 */
	private static int MAXIMUM_PLACEMENT_ATTEMPTS = 25;

	/**
	 * The number of spaces occupied by the ship.
	 */
	private int length;

	/**
	 * The owner of the ship.
	 */
	private Player owner;

	/**
	 * The name of the ship.
	 */
	private String name;

	/**
	 * The character to display when showing the board.
	 */
	private String display;

	/**
	 * The sections of the ship.
	 */
	private ShipSection[] sections;

	/**
	 * Creates a new ship, using the supplied length and owner.
	 * 
	 * @param pLength
	 *            the length of the ship, expressed in squares.
	 * @param pOwner
	 *            the owner of the ship.
	 * @param pName
	 *            The textual name of the ship.
	 * @param pDisplay
	 *            the character used to display the ship on the board.
	 */
	public AbstractShip(int pLength, Player pOwner, String pName,
			String pDisplay)
		{
		length = pLength;
		owner = pOwner;
		name = pName;
		display = pDisplay;

		sections = new ShipSection[getLength()];
		}

	/**
	 * Places the ship on the supplied board. The placement and orientation are
	 * random, and the placement algorithm will give up after
	 * {@link #MAXIMUM_PLACEMENT_ATTEMPTS} tries, by throwing a
	 * {@link RuntimeException}.
	 * 
	 * @param pBoard
	 *            the board on which to place the ship.
	 * @throws RuntimeException
	 *             if the ship cannot be placed onto the board.
	 */
	public void placeOnBoard(GameBoard pBoard)
		{
		// use a random number generator to determine if the ship is to be
		// placed vertically or horizontally:
		Random randomGenerator = new Random();
		boolean horizontal = randomGenerator.nextBoolean();

		if (horizontal == true)
			{
			int maximumXCoordinate = pBoard.getSize() - getLength();

			int row = randomGenerator.nextInt(pBoard.getSize());
			int startColumn = randomGenerator.nextInt(maximumXCoordinate);
			int attemptCount = 0;
			while (!pBoard.verifySpaceAvailable(row, startColumn, getLength(),
					horizontal))
				{
				row = randomGenerator.nextInt(pBoard.getSize());
				startColumn = randomGenerator.nextInt(maximumXCoordinate);
				attemptCount++;
				if (attemptCount >= MAXIMUM_PLACEMENT_ATTEMPTS)
					{
					throw new RuntimeException(
							"Unable to locate an empty space on the board to place the ship!");
					}
				}

			for (int i = 0; i < getLength(); i++)
				{
				sections[i] = new ShipSection(this, row, startColumn + i);
				pBoard.placeShipSection(sections[i]);
				}
			} else
			{
			int maximumYCoordinate = pBoard.getSize() - getLength();

			int startRow = randomGenerator.nextInt(maximumYCoordinate);
			int column = randomGenerator.nextInt(pBoard.getSize());
			int attemptCount = 0;
			while (!pBoard.verifySpaceAvailable(startRow, column, getLength(),
					horizontal))
				{
				startRow = randomGenerator.nextInt(maximumYCoordinate);
				column = randomGenerator.nextInt(pBoard.getSize());
				attemptCount++;
				if (attemptCount >= MAXIMUM_PLACEMENT_ATTEMPTS)
					{
					throw new RuntimeException(
							"Unable to locate an empty space on the board to place the ship!");
					}
				}

			for (int i = 0; i < getLength(); i++)
				{
				sections[i] = new ShipSection(this, startRow + i, column);
				pBoard.placeShipSection(sections[i]);
				}
			}
		}

	/**
	 * Gets the value of the {@link #length} field.
	 * 
	 * @return the value of the {@link #length} field.
	 */
	public int getLength()
		{
		return length;
		}

	/**
	 * Gets the value of the {@link #name} field.
	 * 
	 * @return the value of the {@link #name} field.
	 */
	public String getName()
		{
		return name;
		}

	/**
	 * Gets the value of the {@link #owner} field.
	 * 
	 * @return the value of the {@link #owner} field.
	 */
	public Player getOwner()
		{
		return owner;
		}

	/**
	 * Gets the value of the {@link #display} field.
	 * 
	 * @return the value of the {@link #display} field.
	 */
	public String getDisplay()
		{
		return display;
		}

	/**
	 * Tests to determine if the ship has been sunk (if all of its sections have
	 * been hit, the ship is "sunk").
	 * 
	 * @return true if the ship is sunk, false otherwise.
	 */
	public boolean isSunk()
		{
		int hitCount = 0;
		for (ShipSection section : sections)
			{
			if (section.isHit())
				{
				hitCount++;
				}
			}

		boolean sunk = false;
		if (hitCount == getLength())
			{
			sunk = true;
			}

		return sunk;
		}
	}
