package com.example.s0282656.futureappointmentui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by s0282656 on 12/21/17.
 */

public class SHCMessageDialog extends MsgPopupWindow implements PopupWindow.OnDismissListener {

    private View mRootView;
    private ImageView mArrowUp;
    private ImageView mArrowDown;
    private LayoutInflater mInflater;
//    private ViewGroup mTrack;
    private ScrollView mScroller;
    private TextView mtitleTextView;
    private TextView mmessageTextView;


    private OnDismissListener mDismissListener;

    private String mTitle;
    private String mMessage;

    private int mAnimStyle;
    private int mOrientation;
//    private int rootWidth=0;

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    public static final int ANIM_GROW_FROM_LEFT = 1;
    public static final int ANIM_GROW_FROM_RIGHT = 2;
    public static final int ANIM_GROW_FROM_CENTER = 3;
    public static final int ANIM_REFLECT = 4;
    public static final int ANIM_AUTO = 5;

    /**
     * Constructor for default vertical layout
     *
     * @param context  Context
     */
    public SHCMessageDialog(Context context, String title, String message) {
        this(context, VERTICAL, title, message);
    }

    /**
     * Constructor allowing orientation override
     *
     * @param context    Context
     * @param orientation Layout orientation, can be vertical or horizontal
     */
    public SHCMessageDialog(Context context, int orientation, String title, String message) {
        super(context);

        mOrientation = orientation;
        mTitle = title;
        mMessage = message;

        mInflater 	 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (mOrientation == HORIZONTAL) {
            setRootViewId(R.layout.shc_message_dialog);
        }
//        else {
//            setRootViewId(R.layout.popup_vertical);
//        }

        mAnimStyle 	= ANIM_AUTO;
    }


    /**
     * Set root view.
     *
     * @param id Layout resource id
     */
    public void setRootViewId(int id) {
        mRootView	= (ViewGroup) mInflater.inflate(id, null);
//        mTrack 		= (ViewGroup) mRootView.findViewById(R.id.tracks);

        mArrowDown 	= (ImageView) mRootView.findViewById(R.id.arrow_down);
        mArrowUp 	= (ImageView) mRootView.findViewById(R.id.arrow_up);

        mScroller	= (ScrollView) mRootView.findViewById(R.id.scroller);
        mtitleTextView = (TextView)mRootView.findViewById(R.id.title_view);
        mmessageTextView = (TextView)mRootView.findViewById(R.id.msg_view);

        //This was previously defined on show() method, moved here to prevent force close that occured
        //when tapping fastly on a view to show quickaction dialog.
        mRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        setContentView(mRootView);
    }

    /**
     * Set animation style
     *
     * @param mAnimStyle animation style, default is set to ANIM_AUTO
     */
    public void setAnimStyle(int mAnimStyle) {
        this.mAnimStyle = mAnimStyle;
    }


    /**
     * Show  popup. Popup is automatically positioned, on top or bottom of anchor view.
     *
     */
    public void show (View anchor) {
        preShow();

        mtitleTextView.setText(mTitle);
        mmessageTextView.setText(mMessage);

        int xPos, yPos, arrowPos;
        int[] location 		= new int[2];

        anchor.getLocationOnScreen(location);
        Rect anchorRect 	= new Rect(location[0], location[1], location[0] + anchor.getWidth(), location[1]+ anchor.getHeight());

        mRootView.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        int rootHeight 	= mRootView.getMeasuredHeight();

        DisplayMetrics dm = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(dm);
        int screenWidth 	= dm.widthPixels;
        int screenHeight	= dm.heightPixels;

        int dyTop			= anchorRect.top;
        int dyBottom		= screenHeight - anchorRect.bottom;

        boolean onTop		= (dyTop > dyBottom) ? true : false;
        boolean isRightBottom = false;

        // get coord X
        if (anchorRect.right >  screenWidth/2   ) { // anchor is closer to the right edge of screen, get coord of RIGHT|BOTTOM corner of popup
            isRightBottom = true;
            xPos = anchorRect.right;

        } else { // get coords of TOP|LEFT corner of popup
            xPos = anchorRect.left;
        }
        arrowPos = anchorRect.centerX();
        // get Y coord
        if(onTop){
            yPos = anchorRect.top - rootHeight - dpToPixels(3, mContext);

        }else {
            yPos = anchorRect.bottom + dpToPixels(3, mContext);
        }

        showArrow(((onTop) ? R.id.arrow_down : R.id.arrow_up), arrowPos);
        setAnimationStyle(screenWidth, anchorRect.centerX(), onTop);

        int yPos1 = 0;
        if(isRightBottom && onTop){
            if(screenHeight <= 800){
                yPos1 = screenHeight - anchorRect.top;
            } else {
                yPos1 = screenHeight - anchorRect.top + anchor.getHeight();
            }
            mWindow.showAtLocation(anchor, Gravity.BOTTOM, xPos,yPos1);
        } else {
            mWindow.showAtLocation(anchor, Gravity.TOP | Gravity.LEFT, xPos, yPos);
        }
    }

    private  int dpToPixels(float dp, Context context) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return px;
    }
    public int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
    /**
     * Set animation style
     *
     * @param screenWidth screen width
     * @param requestedX distance from left edge
     * @param onTop flag to indicate where the popup should be displayed. Set TRUE if displayed on top of anchor view
     * 		  and vice versa
     */
    private void setAnimationStyle(int screenWidth, int requestedX, boolean onTop) {
        int arrowPos = requestedX - mArrowUp.getMeasuredWidth()/2;

        switch (mAnimStyle) {
            case ANIM_GROW_FROM_LEFT:
                mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Left : R.style.Animations_PopDownMenu_Left);
                break;

            case ANIM_GROW_FROM_RIGHT:
                mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Right : R.style.Animations_PopDownMenu_Right);
                break;

            case ANIM_GROW_FROM_CENTER:
                mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Center : R.style.Animations_PopDownMenu_Center);
                break;

            case ANIM_REFLECT:
                mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Reflect : R.style.Animations_PopDownMenu_Reflect);
                break;

            case ANIM_AUTO:
                if (arrowPos <= screenWidth/4) {
                    mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Left : R.style.Animations_PopDownMenu_Left);
                } else if (arrowPos > screenWidth/4 && arrowPos < 3 * (screenWidth/4)) {
                    mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Center : R.style.Animations_PopDownMenu_Center);
                } else {
                    mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Right : R.style.Animations_PopDownMenu_Right);
                }

                break;
        }
    }

    /**
     * Show arrow
     *
     * @param whichArrow arrow type resource id
     * @param requestedX distance from left screen
     */
    private void showArrow(int whichArrow, int requestedX) {
        final View showArrow = (whichArrow == R.id.arrow_up) ? mArrowUp : mArrowDown;
        final View hideArrow = (whichArrow == R.id.arrow_up) ? mArrowDown : mArrowUp;

        final int arrowWidth = mArrowUp.getMeasuredWidth();

        showArrow.setVisibility(View.VISIBLE);

        ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams)showArrow.getLayoutParams();

        param.leftMargin = requestedX - arrowWidth / 2;

        hideArrow.setVisibility(View.INVISIBLE);
    }

    /**
     * Set listener for window dismissed. This listener will only be fired if the quicakction dialog is dismissed
     * by clicking outside the dialog or clicking on sticky item.
     */
    public void setOnDismissListener(SHCMessageDialog.OnDismissListener listener) {
        setOnDismissListener(this);

        mDismissListener = listener;
    }

    @Override
    public void onDismiss() {
        if (mDismissListener != null) {
            mDismissListener.onDismiss();
        }
    }

    /**
     * Listener for window dismiss
     *
     */
    public interface OnDismissListener {
        public abstract void onDismiss();
    }
}
