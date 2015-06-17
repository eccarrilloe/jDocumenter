
/**
 * Insert the type's description here.
 * Creation date: (5/9/2001 10:14:02 AM)
 * @author: Administrator
 */
import java.awt.*;
public class Item {

	private int apos;
	private Color bgcolor;
	private int posx;
	private int posy;
	private String value;
	
/**
 * Item constructor comment.
 */
public Item() {
	value = "e";
	apos = 0;
	posx = 0;
	posy = 0;
	bgcolor = Color.green;
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 10:18:18 AM)
 * @param v java.lang.String
 * @param a int
 * @param x int
 * @param y int
 */
public Item(String v, int a, int x, int y) {

	value = v ;
	apos = a;
	posx = x;
	posy = y;
	bgcolor = Color.green;	
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 10:19:29 AM)
 * @param v java.lang.String
 * @param a int
 * @param x int
 * @param y int
 * @param c java.awt.Color
 */
public Item(String v, int a, int x, int y, Color c) {

	value = v;
	apos = a;
	posx = x;
	posy = y ;
	bgcolor = c;
	
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 10:20:51 AM)
 * @return int
 */
public int getApos() {
	return apos;
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 10:21:25 AM)
 * @return java.awt.Color
 */
public Color getColor() {
	return bgcolor;
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 10:21:53 AM)
 * @return java.lang.String
 */
public String getValue() {
	return value;
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 10:22:17 AM)
 * @return int
 */
public int getX() {
	return posx;
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 10:22:36 AM)
 * @return int
 */
public int getY() {
	return posy;
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 10:23:19 AM)
 * @param a int
 */
public void setApos(int a) {

	apos = a;
		
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 10:23:57 AM)
 * @param c java.awt.Color
 */
public void setColor(Color c) {

	bgcolor = c;
	
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 10:24:48 AM)
 * @param v java.lang.String
 */
public void setValue(String v) {

	value = v;	
	
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 10:25:20 AM)
 * @param x int
 */
public void setX(int x) {

	posx = x;
	
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 10:25:47 AM)
 * @param y int
 */
public void setY(int y) {

	posy = y;
	
}
}
