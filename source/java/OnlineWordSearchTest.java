public class OnlineWordSearchTest
{
	public static void main(String[] args)
	{
		OnlineWordSearch ows = new OnlineWordSearch();
		String rec;

		for(int i=0; i<10;i++){
			System.out.println((rec=ows.newWord()));
		}
	}
}