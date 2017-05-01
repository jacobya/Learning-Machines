package board2;


public class node {
	private  String piece;
	private  int ring;
	private  int line;
	public node(int inRing, int inLine)
	{
		piece = "N";
		ring = inRing;
		line = inLine;
	}
	public  String getPiece()
	{
		return piece;
	}
	public  int ringNum()
	{
		return ring;
	}
	public  int getLine()
	{
		return line;
	}
	public  int getRing()
	{
		return ring;
	}
	public  void setPiece(String temp)
	{
		piece = temp;
	}
	public  void setRing(int temp)
	{
		ring = temp;
	}
	public  void setLine(int temp)
	{
		line = temp;
	}
	public void setPiece(char temp)
	{
		piece = Character.toString(temp);
	}
}
