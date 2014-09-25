package com.kausch.battleship;

/**
 * @author rkausch
 */
public class AircraftCarrier extends AbstractShip
	{
	/**
	 * Creates a new Aircraft Carrier, a ship of length 5, owned by the supplied
	 * player.
	 * 
	 * @param pOwner
	 *            the player that owns the ship.
	 */
	public AircraftCarrier(Player pOwner)
		{
		super(5, pOwner, "Aircraft Carrier", "A");
		}
	}
