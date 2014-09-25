package com.kausch.battleship;

/**
 * @author rkausch
 */
public class Submarine extends AbstractShip
	{
	/**
	 * Creates a new battleship, owned by the supplied player.
	 * 
	 * @param pOwner
	 *            the player that owns the submarine.
	 */
	public Submarine(Player pOwner)
		{
		super(3, pOwner, "Submarine", "s");
		}
	}
