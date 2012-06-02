package simulation_daten;

import java.util.ArrayList;
import java.util.Arrays;

public class Array_Reporting<Type> {


	ArrayList<ArrayList<Type>>	array;
 
	public Array_Reporting()
	{
		array = new ArrayList<ArrayList<Type>>();
	}
 
	/**
	 * ensures a minimum capacity of num rows. Note that this does not guarantee
	 * that there are that many rows.
	 * 
	 * @param num
	 */
	public void ensureCapacity(int num)
	{
		array.ensureCapacity(num);
	}
 
	/**
	 * Ensures that the given row has at least the given capacity. Note that
	 * this method will also ensure that getNumRows() >= row
	 * 
	 * @param row
	 * @param num
	 */
	public void ensureCapacity(int row, int num)
	{
		ensureCapacity(row);
		while (row < getNumRows())
		{
			array.add(new ArrayList<Type>());
		}
		array.get(row).ensureCapacity(num);
	}
 
	/**
	 * Adds an item at the end of the specified row. This will guarantee that at least row rows exist.
	 */
	public void Add(Type data, int row)
	{
		ensureCapacity(row);
		while(row >= getNumRows())
		{
			array.add(new ArrayList<Type>());
		}
		array.get(row).add(data);
	}
 
	public Type get(int row, int col)
	{
		return array.get(row).get(col);
	}
 
	public void set(int row, int col, Type data)
	{
		array.get(row).set(col,data);
	}
 
	public void remove(int row, int col)
	{
		array.get(row).remove(col);
	}
 
	public boolean contains(Type data)
	{
		for (int i = 0; i < array.size(); i++)
		{
			if (array.get(i).contains(data))
			{
				return true;
			}
		}
		return false;
	}
 
	public int getNumRows()
	{
		return array.size();
	}
 
	public int getNumCols(int row)
	{
		return array.get(row).size();
	}
	
	
	public Type getlastrow(int col)
	{
		return array.get(array.size() - 1).get(col);
	}
	
	public Type setlastrow(int col, Type data )
	{
		return array.get(array.size()- 1).set(col,data);
	}
	
	public void printDebugData() {
        int numRows = getNumRows();
        int numCols = getNumCols(0);

        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + array.get(i).get(j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}