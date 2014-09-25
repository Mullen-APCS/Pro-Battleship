package com.kausch.battleship;

/**
 * @author rkausch
 */
public class Battleship extends AbstractShip
	{

	/**
	 * Creates a new battleship, owned by the supplied player.
	 * 
	 * @param pOwner
	 *            the player that owns the ship.
	 */
	public Battleship(Player pOwner)
		{
		super(4, pOwner, "Battleship", "b");
		}

	}
