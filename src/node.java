
public class node {
	private static String piece;
	private static int ring;
	private static int line;
	public node(int inRing, int inLine)
	{
		piece = "N";
		ring = inRing;
		line = inLine;
	}
	public static String getPiece()
	{
		return piece;
	}
	public static int ringNum()
	{
		return ring;
	}
	public static int getLine()
	{
		return line;
	}
	public static void setPiece(String temp)
	{
		piece = temp;
	}
	public static void setRing(int temp)
	{
		ring = temp;
	}
	public static void setLine(int temp)
	{
		line = temp;
	}
}
