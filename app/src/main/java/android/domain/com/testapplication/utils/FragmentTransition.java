package android.domain.com.testapplication.utils;

import android.domain.com.testapplication.R;

/**
 * Created by mala on 17/1/17.
 */
public enum FragmentTransition
{
	/* LEFT_TO_RIGHT_TRANSITION */
	LEFT_TO_RIGHT(R.animator.slide_in_left,
				  R.animator.slide_out_right,
				  R.animator.slide_in_right, R.animator.slide_out_left);

	/* enter in transition */
	private int enterIn;

	/* enter out transition */
	private int enterOut;

	/* exit in transition */
	private int exitIn;

	/* exit out transition */
	private int exitOut;

	/**
	 * Construct a new Fragment Transtion
	 *
	 * @param enterIn
	 * @param enterOut
	 * @param exitIn
	 * @param exitOut
	 */
	private FragmentTransition(int enterIn, int enterOut, int exitIn, int exitOut)
	{
		this.enterIn = enterIn;
		this.enterOut = enterOut;
		this.exitIn = exitIn;
		this.exitOut = exitOut;
	}

	/**
	 * Gets the transtion
	 *
	 * @return the transtion array
	 */
	public int[] getTranstion()
	{
		return new int[] { enterIn, enterOut, exitIn, exitOut };
	}
}
