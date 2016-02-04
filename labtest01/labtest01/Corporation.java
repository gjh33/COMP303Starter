package labtest01;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Represents a company that owns and operates
 * one or more grocery stores, each with its own inventory.
 */
public class Corporation implements Iterable<Item>
{
	private Map<String, Inventory> aInventories = new HashMap<String, Inventory>();
	public static Corporation aInstance;
	
	private Corporation() {
		aInstance = new Corporation();
	}
	
	public Corporation getInstance() {
		return aInstance;
	}
	
	/**
	 * @param pInventory An inventory to add to the corporation.
	 */
	public void addInventory(Inventory pInventory)
	{
		aInventories.put(pInventory.getName(), pInventory);
	}

	@Override
	public Iterator<Item> iterator()
	{
		return new Iterator<Item>() {
			
			private Iterator<Inventory> aInvenIterator = aInventories.values().iterator();
			private Iterator<Item> aItemIterator = aInvenIterator.next().iterator();

			@Override
			public boolean hasNext()
			{
				return !aInvenIterator.hasNext() && !aItemIterator.hasNext();
			}

			@Override
			public Item next()
			{
				if (!aItemIterator.hasNext()) {
					if (!aInvenIterator.hasNext()) return null;
					aItemIterator = aInvenIterator.next().iterator();
				}
				return aItemIterator.next();
			}
			
		};
	}
}
