package com.kausch.battleship;

/**
 * @author rkausch
 * 
 */
public class Destroyer extends AbstractShip
	{
	/**
	 * Creates a new destroyer, owned by the supplied player.
	 * 
	 * @param pOwner
	 *            the player that owns the ship.
	 */
	public Destroyer(Player pOwner)
		{
		super(2, pOwner, "Destroyer", "d");
		}
	}
