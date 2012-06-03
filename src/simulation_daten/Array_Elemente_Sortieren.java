package simulation_daten;

public class Array_Elemente_Sortieren implements Comparable
{
	private String[] row = null;
	private String currComp = ")(+-";
	private String nextComp = "-+)(";
	
	public Array_Elemente_Sortieren(String[] row)
	{
		this.row = row;
	}
	
	public String[] getRow()
	{
		return row;
	}
	
	public void nextComp(String nextComp)
	{
		this.nextComp = nextComp;
	}
	
	public void currComp(String currComp)
	{
		this.currComp = currComp;
	}
	
	public String getComp()
	{
		return currComp;
	}
	
	public void setNext(int currCol)
	{
		currComp = nextComp;
		nextComp = "-+)(";
	}
	
	public int compareTo(Object obj) {
		Array_Elemente_Sortieren elem = (Array_Elemente_Sortieren) obj;
		
		if(!elem.getComp().equals(currComp))
			return 0;

		if(elem.getComp().equals("-+)(") || currComp.equals("-+)("))
			return 0;
		
		int res = Array_Sortier_Manager.compare(this.row, elem.getRow());
		if(res == 0)
		{
			nextComp = row[Array_Sortier_Manager.getCurrCol()];
			elem.nextComp(nextComp);
		}
		return res;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(row.length * 10);
		sb.append("|");
		for(int i=0, len=row.length; i<len; i++)
		{
			sb.append(row[i] + "|");
		}
		return sb.toString();
	}
}