

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Item;
import java.lang.Math.*;

public class InsertionSort extends Applet implements Runnable, ActionListener,  AdjustmentListener {
	
	private Thread thread;
	private Rectangle Space1, Space2, Space3;
	private Rectangle FullSpace;
	private Button start, stop, reload;
	int width = 700;
	int height = 600;
	private Image Scratch;
	private Graphics offg;
	private Color BorderColor = Color.lightGray.darker();
	private Color labelcolor =new Color(129, 105, 245);
	private Font labelfont = new Font("ARIAL", Font.BOLD , 12);
	private Item ArrayItem[];
	private int ArraySize = 20;
	private Item temp, floor;
	private int flooridx, currentidx;
	private boolean isRunning = false;
	private boolean sortingdone;
	private int wait = 500;
	private Scrollbar speed;
	private Label status1 = new Label("");
	private Label status2 = new Label("Speed : "+wait/10);
	private Label status3 = new Label("");
	private String line1 = "int CurrentIndex = 0;";
	private String line2 = "int floorIndex = 0;";
	private String line3 = "for (floorIndex = 1; floorIndex < Array.length; floorIndex++) {";
	private String line4 = "    temp = data[floorIndex];";
	private String line5 = "    for ( floorIndex=CurrentIndex; CurrentIndex >= 0; CurrentIndex--) {";
	private String line6 = "        if (CurrentIndex == 0 || Array[CurrentIndex-1] <= temp) {";
	private String line7 = "        Array[CurrentIndex] = temp;";
	private String line8 = "        break; ";
	private String line9 = "        } else {";
	private String line10 ="        Array[CurrentIndex] = Array[CurrentIndex-1]; ";
	private String line11 ="        }";
	private String line12 ="}";
	private int line = 0;
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 1:18:49 PM)
 * @param e java.awt.event.ActionEvent
 */
public void actionPerformed(ActionEvent e) {
	
	if (e.getActionCommand().equals("Start")){
		startanimation();	
	}

	if (e.getActionCommand().equals("Stop")) {
		stopanimation();
		
	}

	if (e.getActionCommand().equals("Reload")){

		if (!sortingdone) {
			status3.setText("Cannot Reload while sorting");
		}
		else {
			status3.setText("");
			isRunning=false;
			line = 0;
			defineArray();
			status1.setText("Press Start to start sorting");
		}
		
		repaint();
	}
	}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 4:35:25 PM)
 */
public void addCode() {

	if (line == 0) {

	}else if (line == 1) {
		offg.setColor(Color.gray);
		offg.drawRect(Space1.x+5, Space1.y+27, 400, 15);
		offg.setColor(Color.lightGray);
		offg.fillRect(Space1.x+6, Space1.y+28, 399, 14);
	}else if (line == 2) {
		offg.setColor(Color.gray);
		offg.drawRect(Space1.x+5, Space1.y+42, 400, 15);
		offg.setColor(Color.lightGray);
		offg.fillRect(Space1.x+6, Space1.y+43, 399, 14);
	}else if (line == 3) {
		offg.setColor(Color.gray);
		offg.drawRect(Space1.x+5, Space1.y+57, 400, 15);
		offg.setColor(Color.lightGray);
		offg.fillRect(Space1.x+6, Space1.y+58, 399, 14);
	}else if (line == 4) {
		offg.setColor(Color.gray);
		offg.drawRect(Space1.x+5, Space1.y+72, 400, 15);
		offg.setColor(Color.lightGray);
		offg.fillRect(Space1.x+6, Space1.y+73, 399, 14);
	}else if (line == 5) {
		offg.setColor(Color.gray);
		offg.drawRect(Space1.x+5, Space1.y+87, 400, 15);
		offg.setColor(Color.lightGray);
		offg.fillRect(Space1.x+6, Space1.y+88, 399, 14);
	}else if (line == 6) {
		offg.setColor(Color.gray);
		offg.drawRect(Space1.x+5, Space1.y+102, 400, 15);
		offg.setColor(Color.lightGray);
		offg.fillRect(Space1.x+6, Space1.y+103, 399, 14);
	}else if (line == 7) {
		offg.setColor(Color.gray);
		offg.drawRect(Space1.x+5, Space1.y+117, 400, 15);
		offg.setColor(Color.lightGray);
		offg.fillRect(Space1.x+6, Space1.y+118, 399, 14);
	}else if (line == 8) {
		offg.setColor(Color.gray);
		offg.drawRect(Space1.x+5, Space1.y+132, 400, 15);
		offg.setColor(Color.lightGray);
		offg.fillRect(Space1.x+6, Space1.y+133, 399, 14);
	}else if (line == 9) {
		offg.setColor(Color.gray);
		offg.drawRect(Space1.x+5, Space1.y+147, 400, 15);
		offg.setColor(Color.lightGray);
		offg.fillRect(Space1.x+6, Space1.y+148, 399, 14);
	}else if (line == 10) {
		offg.setColor(Color.gray);
		offg.drawRect(Space1.x+5, Space1.y+162, 400, 15);
		offg.setColor(Color.lightGray);
		offg.fillRect(Space1.x+6, Space1.y+163, 399, 14);
	}else if (line == 11) {
		offg.setColor(Color.gray);
		offg.drawRect(Space1.x+5, Space1.y+177, 400, 15);
		offg.setColor(Color.lightGray);
		offg.fillRect(Space1.x+6, Space1.y+178, 399, 14);
	}else if (line == 12) {
		offg.setColor(Color.gray);
		offg.drawRect(Space1.x+5, Space1.y+192, 400, 15);
		offg.setColor(Color.lightGray);
		offg.fillRect(Space1.x+6, Space1.y+193, 399, 14);
	}else {}

	

	offg.setFont(labelfont);
	offg.setColor(Color.black);
	offg.drawString(line1, Space1.x+10, Space1.y+40);
	offg.drawString(line2, Space1.x+10, Space1.y+55);
	offg.drawString(line3, Space1.x+10, Space1.y+70);
	offg.drawString(line4, Space1.x+10, Space1.y+85);
	offg.drawString(line5, Space1.x+10, Space1.y+100);
	offg.drawString(line6, Space1.x+10, Space1.y+115);
	offg.drawString(line7, Space1.x+10, Space1.y+130);
	offg.drawString(line8, Space1.x+10, Space1.y+145);
	offg.drawString(line9, Space1.x+10, Space1.y+160);
	offg.drawString(line10, Space1.x+10, Space1.y+175);
	offg.drawString(line11, Space1.x+10, Space1.y+190);
	offg.drawString(line12, Space1.x+10, Space1.y+205);
		
	
	
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 10:57:36 AM)
 */
public void addControls() {

	start = new Button("Start");
	stop = new Button("Stop");
	reload = new Button("Reload");

	speed = new Scrollbar(Scrollbar.HORIZONTAL, 0,0,0,100);	

	status1.setFont(labelfont);
	status1.setText("Press Start");
	status1.setBackground(Color.white);
	status2.setBackground(Color.white);
	status3.setBackground(Color.white);
	status2.setFont(labelfont);
	status2.setText("Speed : "+wait/10);
	status3.setFont(labelfont);
	status3.setText("");
	

	add(start);
	add(stop);
	add(reload);
	add(speed);
	add(status1);
	add(status2);
	add(status3);

	start.addActionListener(this);
	stop.addActionListener(this);
	reload.addActionListener(this);
	speed.addAdjustmentListener(this);
	speed.setValue(wait/10);

	Rectangle c1 = new Rectangle(Space2.x+5, Space2.y+5, Space2.width-12, Space2.height-(Space2.height*2/3));
	Rectangle c2 = new Rectangle(Space2.x+5, c1.height+8, c1.width, c1.height);
	Rectangle c3 = new Rectangle(c2.x, 2*c2.height+11, c2.width, c2.height-16);

	start.setBounds(c1.x+10, c1.y+c1.height/2-12, 60, 25);
	stop.setBounds(c1.x+80, c1.y+c1.height/2-12, 60,25);
	reload.setBounds(c1.x+90+60, c1.y+c1.height/2-12, 60,25);
	speed.setBounds(c2.x+10, c2.y+c2.height/2, c2.width-40, 10);
	status1.setBounds(c3.x+10, c3.y+14, c3.width-10, c3.height/3);
	status2.setBounds(c2.x+10, c2.y+c2.height/2-20, c2.width-10,20);
	status3.setBounds(c3.x+10, c3.y+c3.height/3+14, c3.width-10, c3.height/3);

	
	
	
		
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 1:26:27 PM)
 * @param e java.awt.event.AdjustmentEvent
 */
public void adjustmentValueChanged(AdjustmentEvent e) {

	int  w = e.getValue();
	
	status2.setText("Speed : "+ w);
if (w <=20 && w>10){
		wait = (100-w)*10;
	}
	else if ( w <= 10 && w > 5){
		wait = (100-w)*10;
	}
	else if(w<=5 && w>=0){
		wait = (100-w)*10;
	}
	else {
		wait = (100-w)*10;
	}

}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 12:44:29 PM)
 */
public void defineArray() {

	ArrayItem = new Item[ArraySize];
	Rectangle s3 = new Rectangle(Space3.x+40, Space3.y, Space3.width, Space3.height);
	temp = new Item("e" , 0, s3.x+25, s3.y+s3.y/2, Color.white);
	floor = new Item("f", 1, s3.x+25, s3.y+s3.y/2-60, Color.white);
	int i;
	int j=1;

	for ( i=0; i<ArrayItem.length; i++) {
		
		ArrayItem[i] = new Item(""+(int)((java.lang.Math.random()*100)), i ,s3.x+j*25, s3.y+s3.y/2-30, Color.green);	
		j++;
		
	}	
	
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 10:33:01 AM)
 */
public void defineLayout() {

	FullSpace =  new Rectangle(0, 0, width, height);
	Space1 = new Rectangle(0,0, width*2/3, height/2);
	Space2 = new Rectangle(width*2/3, 0, width*1/3, height/2);
	Space3 = new Rectangle(0, height/2, width, height/2);

	
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 12:46:04 PM)
 */
public void drawArray() {

	int i;
	for (i=0; i<ArrayItem.length; i++) {

		
		offg.setColor(Color.black);
		offg.drawRect(ArrayItem[i].getX()-1, ArrayItem[i].getY()-1, 21,21);
		offg.setColor(ArrayItem[i].getColor());
		offg.fillRect(ArrayItem[i].getX(), ArrayItem[i].getY(),20,20);
		offg.setColor(Color.black);
		offg.drawString(""+ArrayItem[i].getValue(), ArrayItem[i].getX()+2, ArrayItem[i].getY()+15);	
	}

	offg.setColor(Color.black);
	offg.drawRect(ArrayItem[temp.getApos()].getX()-1, temp.getY()-1, 21,21);
	offg.setColor(temp.getColor());
	offg.fillRect(ArrayItem[temp.getApos()].getX(), temp.getY(),20,20);
	offg.setColor(Color.black);
	offg.setFont(labelfont);
	offg.drawString(""+temp.getValue(), ArrayItem[temp.getApos()].getX()+2, temp.getY()+15);
	
	/*offg.setColor(Color.black);
	offg.drawRect(ArrayItem[floor.getApos()].getX()-1, floor.getY()-1, 21,21);
	offg.setColor(floor.getColor());
	offg.fillRect(ArrayItem[floor.getApos()].getX(), floor.getY(),20,20);*/
	offg.setColor(Color.black);
	offg.setFont(labelfont);
	offg.drawString(""+floor.getValue(), ArrayItem[floor.getApos()].getX()+9, floor.getY()+20);	
	
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 11:55:41 AM)
 */
public void drawBorder() {
	

	offg.setColor(Color.white);
	offg.fillRect(FullSpace.x, FullSpace.y, FullSpace.width, FullSpace.height);
	offg.fillRect(Space1.x+2, Space1.y+2, Space1.width-4, Space1.height-4);
	offg.fillRect(Space2.x+2, Space2.y+2, Space2.width-6, Space2.height-4);
	offg.fillRect(Space3.x+2, Space3.y+2, Space3.width-6, Space3.height-6);
	
	offg.setColor(BorderColor);
	offg.drawRect(FullSpace.x, FullSpace.y+1, FullSpace.width-1, FullSpace.height-4);

	Rectangle c1 = new Rectangle(Space2.x+5, Space2.y+5, Space2.width-12, Space2.height-(Space2.height*2/3));
	Rectangle c2 = new Rectangle(Space2.x+5, c1.height+8, c1.width, c1.height);
	Rectangle c3 = new Rectangle(c2.x, 2*c2.height+11, c2.width, c2.height-16);
	Rectangle s1 = new Rectangle(Space1.x+2, Space1.y+2, Space1.width-4, Space1.height-4);
	Rectangle s2 = new Rectangle(Space2.x+2, Space2.y+2, Space2.width-6, Space2.height-4);
	Rectangle s3 = new Rectangle(Space3.x+2, Space3.y+2, Space3.width-6, Space3.height-8);
	Rectangle CR = new Rectangle(Space3.x+160, Space3.y+250, Space3.width/2, 40);
	Rectangle c4 = new Rectangle(s3.width-160, s3.y+20, 150, 40);

	offg.setColor(BorderColor);
	offg.drawRect(c1.x, c1.y+8, c1.width, c1.height-8);
	offg.drawRect(c2.x, c2.y+8, c2.width, c2.height-8);
	offg.drawRect(c3.x, c3.y+8, c3.width, c3.height-8);
	offg.drawRect(s1.x, s1.y+8, s1.width, s1.height-8);
	offg.drawRect(s3.x, s3.y+8, s3.width, s3.height-8);
	offg.drawRect(s2.x, s2.y+8, s2.width, s2.height-8);
	offg.drawRect(CR.x, CR.y, CR.width, CR.height);
	offg.drawRect(c4.x, c4.y, c4.width, c4.height);
	offg.setColor(Color.black);
	offg.drawRect(c1.x+9, c1.y+c1.height/2-13, 61, 26);
	offg.drawRect(c1.x+79, c1.y+c1.height/2-13, 61,26);
	offg.drawRect(c1.x+90+59, c1.y+c1.height/2-13, 61,26);
	offg.setColor(Color.white);
	offg.fillRect(c1.x+7,c1.y, 104, 20);
	offg.fillRect(c2.x+7, c2.y, 84,20);
	offg.fillRect(c3.x+7, c3.y, 64,20);
	offg.fillRect(s1.x+7, s1.y+2, 66,20);
	offg.fillRect(s3.x+7, s3.y, 95,20);
	
	offg.setColor(labelcolor);
	offg.setFont(labelfont);
	offg.drawString("Animation Control", c1.x+9, c1.y+12);
	offg.drawString("Speed Control", c2.x+9, c2.y+12);
	offg.drawString("Status Pane", c3.x+9, c3.y+12);
	offg.drawString("Code Panel", s1.x+9, s1.y+12);
	offg.drawString("Animation Panel", s3.x+9, s3.y+12);
	offg.drawString("Author : Mohammad Khurshid Ali ", CR.x+70,CR.y+15);
	offg.drawString("        April , 2001", CR.x+105, CR.y+30);
	offg.drawString(" f = floor Index ", c4.x+20, c4.y+20);
	offg.drawString(" e = Empty ", c4.x+20, c4.y+35);

		
}
/**
 * Returns information about this applet.
 * @return a string of information about this applet
 */
public String getAppletInfo() {
	return "InsertionSort\n" + 
		"\n" + 
		"Insert the type's description here.\n" + 
		"Creation date: (5/9/2001 10:11:58 AM)\n" + 
		"@author: Mohammad Khurshid Ali\n" + 
		"";
}
/**
 * Initializes the applet.
 * 
 * @see #start
 * @see #stop
 * @see #destroy
 */
public void init() {
	// insert code to initialize the applet here

	setLayout(null);
	defineLayout();
	
	Scratch = createImage(FullSpace.width, FullSpace.height);
	offg = Scratch.getGraphics();
	drawBorder();
	addControls();
	addCode();
	defineArray();
	
	
}
/**
 * Paints the applet.
 * If the applet does not need to be painted (e.g. if it is only a container for other
 * awt components) then this method can be safely removed.
 * 
 * @param g  the specified Graphics window
 * @see #update
 */
public void paint(Graphics g) {

	drawBorder();
	drawArray();
	addCode();
	g.drawImage(Scratch,0,0,this);
	
}
/**
 * Contains the thread execution loop.
 */
public void run() {
	while (true) {
		try {
			Thread.sleep(200);
		}
		catch (InterruptedException e) {}

		String t;
		String v1="";
		String v2="";
		Color v1c, v2c, tc;
		

		int ti=10;
		while(!isRunning){}
		line =1;
		currentidx= 0;
		repaint();
		try { Thread.sleep(ti);} catch (InterruptedException e) {}
		flooridx = 0;
		line =2;
		try { Thread.sleep(ti);} catch (InterruptedException e) {}
		repaint();
		
		for (flooridx++ ; flooridx < ArrayItem.length; flooridx++) {
			line =3;
			try { Thread.sleep(ti);} catch (InterruptedException e) {}
			repaint();
			sortingdone = false;
			v1 = temp.getValue();
			v1c = temp.getColor();
			while(!isRunning){}
			temp.setValue(ArrayItem[flooridx].getValue());
			temp.setColor(ArrayItem[flooridx].getColor());
			ArrayItem[flooridx].setValue(v1);
			ArrayItem[flooridx].setColor(v1c);
			floor.setApos(flooridx);
			/*line = 4;
			try { Thread.sleep(wait);} catch (InterruptedException e) {}
			repaint();*/
			for ( currentidx = flooridx; currentidx >= 0; currentidx-- ) {
				line=5;
				try { Thread.sleep(ti);} catch (InterruptedException e) {}
				repaint();
				temp.setApos(currentidx);
				while(!isRunning){}
				try { Thread.sleep(wait);} catch (InterruptedException e) {}
				repaint();
				if (currentidx == 0 || Integer.parseInt(ArrayItem[currentidx-1].getValue()) <= Integer.parseInt(temp.getValue())){
					while(!isRunning){}
					line = 6;
					try { Thread.sleep(wait);} catch (InterruptedException e) {}
					repaint();
					v2 = ArrayItem[currentidx].getValue();
					v2c = ArrayItem[currentidx].getColor();
					ArrayItem[currentidx].setValue(temp.getValue());
					ArrayItem[currentidx].setColor(temp.getColor());
					temp.setValue(v2);
					temp.setColor(v2c);
					line = 7;
					while(!isRunning){}
					try { Thread.sleep(wait);} catch (InterruptedException e) {}
					line = 8;
					try { Thread.sleep(ti);} catch (InterruptedException e) {}
					repaint();
					break;
					
				}
				else {
					line = 10;
					try { Thread.sleep(120);} catch (InterruptedException e) {}
					repaint();
					t = ArrayItem[currentidx].getValue();
					tc = ArrayItem[currentidx].getColor();
					while(!isRunning){}
					try { Thread.sleep(wait);} catch (InterruptedException e) {}
					ArrayItem[currentidx].setValue(ArrayItem[currentidx-1].getValue());
					ArrayItem[currentidx].setColor(ArrayItem[currentidx-1].getColor());
					ArrayItem[currentidx-1].setValue(t);
					ArrayItem[currentidx-1].setColor(tc);
					while(!isRunning){}
					
					
				}
			}
		}
		status3.setText("Sorting is Done");
		status1.setText("Press the Reload Button");
		sortingdone=true;
		isRunning = false;
		line = 12;
		repaint();
	}
	
	
	}
/**
 * Starts up the thread.
 */
public void start() {
	if (thread == null){
		thread = new Thread(this);
		thread.start();
	}
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 1:56:33 PM)
 */
public void startanimation() {
	
	status1.setText("Animation started");
	status3.setText("");
	isRunning = true;
		
}
/**
 * Terminates the thread and leaves it for garbage collection.
 */
public void stop() {
	thread = null;
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 1:56:52 PM)
 */
public void stopanimation() {

	status1.setText("Animation stopped. Press start");
	status3.setText("");
	isRunning = false;	
	
}
/**
 * Insert the method's description here.
 * Creation date: (5/9/2001 2:03:34 PM)
 * @param g java.awt.Graphics
 */
public final void update(Graphics g) {

	paint(g);	
	
}
}
