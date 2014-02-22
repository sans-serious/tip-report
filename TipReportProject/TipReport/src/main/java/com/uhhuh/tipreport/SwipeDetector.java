package com.uhhuh.tipreport;

import android.widget.Toast;
import android.view.View;
import android.view.MotionEvent;

import java.lang.Math;


public class SwipeDetector implements View.OnTouchListener{

     public static enum Action{
		 LR,
		 RL,
		 UD,
		 DU,
		 None
	 }

     private static final int SWIPE_MIN_DISTANCE = 10;
	 private float downX, downY, upX, upY;
	 private Action mDetected = Action.None;
	 
	 public boolean swipeDetected(){
		 return mDetected != Action.None;
	 }
	 
	 public Action getAction(){
		 return mDetected;
	 }
	 
	 
	 
	 @Override
	 public boolean onTouch(View v, MotionEvent event){
		 switch(event.getAction()){
			 case MotionEvent.ACTION_DOWN:
			     downX = event.getX();
				 downY = event.getY();
				 mDetected = Action.None;
				 return false;
			 case MotionEvent.ACTION_UP:
			     upX = event.getX();
				 upY = event.getY();
				 
				 float deltaX = downX - upX;
				 float deltaY = downY - upY;
				 
				 if(Math.abs(deltaX) > SWIPE_MIN_DISTANCE){
					 if(deltaX < 0){
						 mDetected = Action.LR;
						 return false;
						 
					 }else if(deltaX > 0){
						 mDetected = Action.RL;
						 return false;
					 }
					
				 }
				 return false;
		 }
		 return false;
	 }
	 
	 

}
