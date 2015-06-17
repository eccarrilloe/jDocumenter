/*
	MontePi.java
	Gary McGath 10/9/96
	Monte Carlo method for approximating pi
	Copyright 1996, BU Center for Polymer Studies

	Important: Remember that the file name must match the public class name (eg
	public class "TrivialApplication" must be in file "TrivialApplication.java"
	(case is relevant!). 
	
	Basic principle: inscribe a circle of radius r in a square of side 2r.
	Randomly shoot points into the square. Determine the proportion of them
	which are also within the circle. Since the area of the circle is pi * r**2,
	and the area of the square is 4 * r**2, this ratio will approach pi / 4.

	We have three panes: the circle / square display (which is backed up by
	an offscreen Graphics); the graph of the estimates of pi, and the buttons
	which control the operation.
*/

import java.awt.*;
import java.applet.*;

public class MontePi extends Applet {
	public final static String version = "Version 0.2, Copyright 1996 BU Center for Polymer Studies";
				/* This is displayed to show the current version */
	public ScatterPanel scatPanel;			/* display the circle / square / points */
	protected ControlPanel ctlPanel;
	protected Thread shooter;			/* the Thread targeting scatPanel */
	protected Thread grapher;			/* the Thread targeting grPanel */
//	GraphPanel	grPanel;				/* display the graph of approximations */
	protected GraphPanel grPanel;					/* filler for now */
	protected GridBagLayout appletLayout;
	protected MonteState state;				/* object for state info */
	
	public void init() 
	{
		Dimension siz = this.size();
		
		state = new MonteState();

		setBackground(Color.white);
		appletLayout = new GridBagLayout();
		setLayout(appletLayout);
		scatPanel = new ScatterPanel(this);
		constrain(this, scatPanel, 0, 0, 1, 1, GridBagConstraints.NONE,
						GridBagConstraints.WEST, 1, 1,
						0, 0, 0, 0);
//		System.out.println("Width = " + String.valueOf(siz.width));
		scatPanel.resize(siz.width / 2, siz.height);	/* why is this necessary? */
		add(scatPanel);			/* lives on the left half */

		ctlPanel = new ControlPanel(this);
		constrain(this, ctlPanel, 1, 0, 1, 2, GridBagConstraints.NONE,
						GridBagConstraints.WEST, 0.1, 0.1,
						5, 5, 5, 5);
		add(ctlPanel);				/* lives in upper right quadrant */
		
		grPanel = new GraphPanel(this);		
		constrain(this, grPanel, 0, 1, 1, 1, GridBagConstraints.BOTH,
						GridBagConstraints.EAST, 1.5, 1,
						0, 0, 0, 0);
		add(grPanel);
//		grPanel.resize(siz.width / 2, siz.height / 2);
		scatPanel.makeOffscreen();
		grPanel.makeOffscreen();
		ctlPanel.makeControls();

		/* create a thread which will update the graph */
		grapher = new Thread(grPanel);
//		grapher.setPriority(Thread.NORM_PRIORITY - 1);
		grapher.start();
	}


	// Start the shooting of points at the pane.Supposedly this
	// is less clean than implementing an instance of Runnable;
	// I can't see why.
	void startShoot(int limit)
	{
//		reset();
		shooter = new Thread(scatPanel);
		scatPanel.setLimit(limit);
		shooter.start();
	}
	
	
	// Stop the tossing of points at the pane
	void stopShoot()
	{
		if (shooter != null) {
			scatPanel.stop();
			while (scatPanel.running) {
				try {
					Thread.currentThread().sleep(30);		/* wait for it to finish */
				}
				catch (InterruptedException e) {
					/* do we care if it's interrupted? */
				}
			}
			shooter = null;
		}
	}
	
	
	// Reset the shooter
	public void reset()
	{
		stopShoot();
		state.reset();
		scatPanel.initOffscreen();
		grPanel.initOffscreen();
	}
	
	// The constrain method is lifted from *Java in a Nutshell*, p. 111
	void constrain(Container container, Component component,
			int grid_x, int grid_y, int grid_width, int grid_height,
			int fill, int anchor, double weight_x, double weight_y,
			int top, int left, int bottom, int right)
	{
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = grid_x;
		c.gridy = grid_y;
		c.gridwidth = grid_width; 
		c.gridheight = grid_height;
		c.fill = fill;
		c.anchor = anchor;
		c.weightx = weight_x;
		c.weighty = weight_y;
		if (top + bottom + left + right > 0)
			c.insets = new Insets(top, left, bottom, right);
		((GridBagLayout)container.getLayout()).setConstraints(component, c);
	}
	
	// We override the stop method so that we are sure to kill any
	// shooter thread which is running when the user closes the applet;
	// otherwise it could haunt the interpreter forever.
	
	public void stop()
	{
		stopShoot();
//		if (grapher != null)
//			grapher.stop();		/* For some reason, doing this causes an error instead of helping */
	}
	
	
	
	public Dimension preferredSize()
	{
		return new Dimension(500, 200);
	}
	
	public MonteState getState()
	{ return state; }
}

