package simulation_daten;

import java.util.Arrays;

public class Array_Sortier_Manager {
	
		private static int currCol = 0;
		public static final int ASC = 1;
		public static final int DES = -1;
		private static int currOrd = ASC;
		private static int length = 0;
		private static int width = 0;
		
		public static int getCurrCol()
		{
			return currCol;
		}
		
		public static String[][] sort(String[][] twodime, int[] column, int[] order)
		{
			length = twodime.length;
			width = length > 0? twodime[0].length: 0;
			
			Object[] arr = new Object[length];
			for(int i=0; i<length; i++)
			{
				arr[i] = new Array_Elemente_Sortieren(twodime[i]);
			}
			
			for(int i=0, len=column.length; i<len; i++)
			{
				currCol = column[i];
				currOrd = order[i];
				Arrays.sort(arr);
				for(int idx=arr.length-1; idx >= 0; idx-- )
				{
					((Array_Elemente_Sortieren) arr[idx]).setNext(currCol);
				}			
			}
			
			String[][] retArr = getTwoDimeArr(arr);
			
			return retArr;
		}
		
		public static int compare(String[] row1, String[] row2)
		{
			if (row1[currCol]!=null && row2[currCol]!=null ){
			int val = row1[currCol].compareTo(row2[currCol]);
			return currOrd * val;}
			else return(0);
		}
		
		public static String[][] getTwoDimeArr(Object[] arr)
		{
			String[][] retArr = new String[length][width];
			for(int i=0; i<length; i++) {
				Array_Elemente_Sortieren elem = (Array_Elemente_Sortieren) arr[i];
				retArr[i] = elem.getRow();
			}
			return retArr;
		}
		
		
		
	}

