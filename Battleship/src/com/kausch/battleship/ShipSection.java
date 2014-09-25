package com.kausch.battleship;

/**
 * A container class representing a single square of a ship. A ship is composed
 * of some number of {@link ShipSection} instances, equal to the length of the
 * ship. The square is stored on the board, and is used to keep track of the hit
 * state of an individual section of the ship.
 * 
 * @author rkausch
 */
public class ShipSection
	{
	/**
	 * The x location of the section.
	 */
	private int xLocation;

	/**
	 * The y location of the section.
	 */
	private int yLocation;

	/**
	 * A flag used to keep track of the hit state of the ship section. True
	 * indicates that the ship has already been hit. Once this flag has been set
	 * to true, it should not change back to false.
	 */
	private boolean hit;

	/**
	 * The ship to which the section belongs.
	 */
	private AbstractShip ship;

	/**
	 * Creates a new ship square.
	 * 
	 * @param pShip
	 *            the ship to which the section belongs.
	 * @param pXLocation
	 *            the x location of the ship section.
	 * @param pYLocation
	 *            the y location of the ship section.
	 */
	public ShipSection(AbstractShip pShip, int pXLocation, int pYLocation)
		{
		xLocation = pXLocation;
		yLocation = pYLocation;
		ship = pShip;
		hit = false;
		}

	/**
	 * Tests to determine if the section has already been hit.
	 * 
	 * @return true if the section was hit, false otherwise.
	 */
	public boolean isHit()
		{
		return hit;
		}

	/**
	 * Sets the value of the {@link #hit} field to true.
	 */
	public void setHit()
		{
		hit = true;
		}

	/**
	 * Gets the value of the {@link #xLocation} field.
	 * 
	 * @return the value of the {@link #xLocation} field.
	 */
	public int getXLocation()
		{
		return xLocation;
		}

	/**
	 * Gets the value of the {@link #yLocation} field.
	 * 
	 * @return the value of the {@link #yLocation} field.
	 */
	public int getYLocation()
		{
		return yLocation;
		}

	/**
	 * Gets the value of the {@link #ship} field.
	 * 
	 * @return the value of the {@link #ship} field.
	 */
	public AbstractShip getShip()
		{
		return ship;
		}
	}
