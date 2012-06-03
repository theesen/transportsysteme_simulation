package simulation_berechnungen;

import java.util.Comparator;

public class StringArrayComparator implements Comparator 
{  
int sortColumn = 0;  
public int setSortColumn(int c) 
{ 
sortColumn = c; 
return c;
}  
public int compare(Object o1, Object o2) 
{    
if (o1 == null && o2 == null)      
return 0;    
if (o1 == null)      
return -1;
if (o2 == null)      
return 1;    
String[] s1 = (String[])o1;    
String[] s2 = (String[])o2;    
return s1[sortColumn].compareTo(s2[sortColumn]);  
}
}
