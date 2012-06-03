package simulation_berechnungen;

import java.util.Arrays;

public class SortManager {
	
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
				arr[i] = new SortElem(twodime[i]);
			}
			
			for(int i=0, len=column.length; i<len; i++)
			{
				currCol = column[i];
				currOrd = order[i];
				Arrays.sort(arr);
				for(int idx=arr.length-1; idx >= 0; idx-- )
				{
					((SortElem) arr[idx]).setNext(currCol);
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
				SortElem elem = (SortElem) arr[i];
				retArr[i] = elem.getRow();
			}
			return retArr;
		}
		
		public static void main(String args[])
		{
			/*String[][] twodime = new String[][]{{"klmnopqr", "pqrstuvw", "fghijklm", "abcdefgh"},
												{"lmnopqrs", "qrstuvwx", "hijklmno", "bcdefghi"},
												{"opqrstuvw", "rstuvwxy", "ijklmnop", "abcdefgh"},
												{"lmnopqrs", "stuvwxyz", "fghijklm", "abcdefgh"},
												{"mnopqrst", "pqrstuvw", "ghijklmn", "cdefghij"},
												{"pqrstuvw", "rstuvwxy", "fghijklm", "bcdefghi"},
												{"klmnopqr", "qrstuvwx", "hijklmno", "abcdefgh"},
												{"pqrstuvw", "pqrstuvw", "jklmnopqr", "abcdefgh"},
												{"mnopqrst", "stuvwxyz", "ijklmnop", "bcdefghi"},
												{"lmnopqrs", "rstuvwxy", "ghijklmn", "cdefghij"},
												{"opqrstuvw", "pqrstuvw", "fghijklm", "abcdefgh"},
												{"lmnopqrs", "qrstuvwx", "ijklmnop", "bcdefghi"},
												{"lmnopqrs", "rstuvwxy", "ghijklmn", "bcdefghi"},
												{"klmnopqr", "stuvwxyz", "klmnopqr", "cdefghij"},
												{"opqrstuvw", "pqrstuvw", "jklmnopqr", "abcdefgh"},
												{"klmnopqr", "stuvwxyz", "fghijklm", "bcdefghi"},
												{"mnopqrst", "rstuvwxy", "ijklmnop", "bcdefghi"},
												{"opqrstuvw", "pqrstuvw", "ghijklmn", "abcdefgh"},
												{"opqrstuvw", "stuvwxyz", "hijklmno", "cdefghij"},
												{"lmnopqrs", "rstuvwxy", "fghijklm", "bcdefghi"}
												};
			
			twodime = SortElem(twodime, new int[]{3, 1, 2}, new int[]{DES, ASC, DES});*/
			
			String[][] twodime = new String[][]{{"AAA","BBB","CCC"}, 
					{"BBB","GGG","DDD"},
					{"CCC","GGG","EEE"},
					{"CCC","DDD","FFF"},
					{"CCC","TTT","JJJ"},
					{"DDD","UUU","LLL"},
					{"DDD","AAA","MMM"},
					{"EEE","FFF","NNN"},
					{"EEE","UUU","YYY"},
					{"EEE","UUU","QQQ"},
					{"EEE","UUU","SSS"},
					{"EEE","PPP","RRR"}};
			
			twodime = sort(twodime, new int[]{0,2,1}, new int[]{DES, DES, DES}); // SortElem(2-dime-array, columns, order); 
			
			for(int i=0, len=twodime.length; i<len; i++)
			{
				String[] arr = twodime[i];
				for(int j=0, wid=arr.length; j<wid; j++) {
					System.out.print(arr[j] + " | ");
				}
				System.out.println("\n------------------------------------------------------");
			}
		}
	}

