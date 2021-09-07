package com.yiyang.cn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yiyang.cn.R;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CircleMenuView extends View {

	private int[] array = {
			R.drawable.car_net_kaichexiang,
			R.drawable.car_net_jiaocheqiche,
			R.drawable.car_net_deng,
			R.drawable.car_net_dengkongxin,
			R.drawable.car_net_unlock,
			R.drawable.car_net_lock,
			R.drawable.car_net_didi,
			R.drawable.car_net_jiareqi
			};
	private int color = getResources().getColor(R.color.black);
	private Paint rPaint;//文字
	private Paint mPaint;//大圆
	private Paint mPaint2;//小圆
	private Paint paint3;
	private int count = -1;//点击的是那个
	private Bitmap large_bitmap,small_bitmap;
	private float centerX,centerY;//中心店坐标
	private int roundRadius;//中心圆的半径
	private int onClickState = -2;//-2是无点击，-1是点击中心圆，其他是点击菜单
	private int abroadRadius;//半径
	private float angle;
	private float offsetAngle = 0;          //初始角度
	private Map<Integer,Float> AngleMap;    //记录每个Itme的角度值
	private float paintSize ;           //画笔宽度
	private float radiusSize;               //半径+画笔的宽度一半 = 看到圆的半径
	private Boolean isStart = false;

	private long touchTime;//按下的时间

	public CircleMenuView(Context context) {
		this(context, null);
	}

	public CircleMenuView(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public CircleMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	public Boolean isStart(){
		return isStart;
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setColor(color);
		mPaint.setAntiAlias(true);
		mPaint.setStrokeWidth(1);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint2 = new Paint();
		mPaint2.setColor(getResources().getColor(R.color.blue_light));
		mPaint2.setAntiAlias(true);
		mPaint2.setStrokeWidth(2);
		mPaint2.setStyle(Paint.Style.FILL);
		large_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.light_circle);
		small_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_net_unstart_button);
		paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
		rPaint = new Paint();
		abroadRadius = (large_bitmap.getWidth() >> 1) - large_bitmap.getHeight()/6;
		angle = 360f/8;
		AngleMap = new HashMap<>();
		paintSize = abroadRadius;
		radiusSize  = abroadRadius + (paintSize/2);
		roundRadius = small_bitmap.getWidth()/2;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		centerX = getWidth();
		centerY = getHeight();
		setFX(canvas);
	}

	private void setFX(Canvas canvas) {
		RectF rectF = new RectF((canvas.getWidth() >> 1) - (large_bitmap.getWidth() >> 1),
				(canvas.getHeight() >> 1) - (large_bitmap.getHeight() >> 1),
				(canvas.getWidth() >> 1) + (large_bitmap.getWidth() >> 1),
				(canvas.getHeight() >> 1) + (large_bitmap.getHeight() >> 1));
		paint3.setStyle(Paint.Style.STROKE);
		paint3.setStrokeWidth(2);
		for (int i = 0; i < 8; i++) {

			if (count == i) {//如果按压了，更换画笔填充方式
				paint3.setStyle(Paint.Style.FILL);
			}else {
				paint3.setStyle(Paint.Style.STROKE);
			}
			canvas.drawArc(rectF, offsetAngle, angle, true, paint3);//8个圆弧的位置
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), array[i]);
			setBitmap((int)centerX/2,(int)centerY/2,abroadRadius,offsetAngle,angle,bitmap,canvas);
			offsetAngle+=angle;
		}
		mDrawCirle(canvas);


	}

	private void setBitmap(int X,int Y ,float mRadius,float offsetAngle,float angle,Bitmap bitmap, Canvas canvas) {

		int imgWidth = (int) mRadius / 6;
		float x = (float) (X + mRadius * Math.cos(Math.toRadians(offsetAngle+12+(angle / 4))));
		float y = (float) (Y + mRadius * Math.sin(Math.toRadians(offsetAngle+12+(angle / 4))));
		RectF  rectf = new RectF(x - (imgWidth * 2) / 3, y - imgWidth*2 / 3, x + imgWidth *2/ 3, y + imgWidth*2/4);
		canvas.drawBitmap(bitmap, null, rectf, null);
	}

	private void mDrawCirle(Canvas canvas) {

		canvas.drawBitmap(large_bitmap, (canvas.getWidth() >> 1) - (large_bitmap.getWidth() >> 1), (canvas.getHeight() >> 1) - (large_bitmap.getHeight() >> 1),mPaint);
		canvas.drawBitmap(small_bitmap, (canvas.getWidth() >> 1) - (small_bitmap.getWidth() >> 1), (canvas.getHeight() >> 1) - (small_bitmap.getHeight() >> 1),mPaint);

	}




	//计算，显示的位子
	public void calculation(float downX ,float downY,float width,float height){
		downY =  downY - dip2px(25);            //25位状态的高度。这个我是直接写死的

		//防止点击角落的位子，或者边缘的位子，圆弧菜单有有一部分显示不出来，所以我们计算点击的位子，到边缘的距离
		if (downX > width  - radiusSize) {
			centerX = downX > downX - radiusSize ? width - radiusSize : downX;
		}else {
			centerX = downX < radiusSize ? radiusSize : downX;
		}
		if (downY >= height  - radiusSize) {
			centerY =  height - radiusSize  - dip2px(25);

		}else {
			centerY = downY <  radiusSize ? radiusSize : downY ;
		}
	}
	//判断显示隐藏
	private boolean isShowView = false;
	public boolean isShowView(){
		return isShowView;
	}
	//外部提供显示隐藏的方法
	public void isShow(float x, float y, float width, float height){
		isShowView = true;
		this.setVisibility(VISIBLE);
		calculation(x,y,width,height);
		AngleMap.clear();
		invalidate();
		AngleMap = new HashMap<>();
		offsetAngle = 0;
	}



	private int dip2px(float dpValue) {
		final float scale = getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				float textX = event.getX();
				float textY = event.getY();
				int distanceLine = (int) getDisForTwoSpot(centerX / 2, centerY / 2, textX, textY);//距离中心点之间的直线距离
				if (distanceLine <= roundRadius) {
					//点击的是中心圆；按下点到中心点的距离小于中心园半径，那就是点击中心园了
					onClickState = -1;
					if (isStart){
						isStart = false;
						small_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_net_unstart_button);
					}else {
						isStart = true;
						small_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_net_start_button);
					}
					invalidate();
				} else if (distanceLine <= getWidth() / 2) {
					//点击的是某个扇形；按下点到中心点的距离大于中心圆半径小于大圆半径，那就是点击某个扇形了
					float sweepAngle = 360 / 8;//每个弧形的角度
					int angle = getRotationBetweenLines(centerX / 2, centerY / 2, textX, textY);
					//这个angle的角度是从正Y轴开始，而我们的扇形是从正X轴开始，再加上偏移角度，所以需要计算一下
					angle = (angle + 360 - 90 ) % 360;
					onClickState = (int) (angle / sweepAngle);//根据角度得出点击的是那个扇形
					count = onClickState;
					invalidate();
				} else {
					//点击了外面
					onClickState = -2;
				}
				invalidate();
				break;
			case MotionEvent.ACTION_UP:
					if (onClickState == -1) {
						if (mOnMenuItemClickListener != null) {
							mOnMenuItemClickListener.itemCenterClick();
						}
//                        onClickListener = onCoreClickListener;
					} else if (onClickState >= 0 && onClickState < 8) {
//                        onClickListener = roundMenus.get(onClickState).onClickListener;
						if (mOnMenuItemClickListener != null) {
							mOnMenuItemClickListener.itemClick(onClickState);
						}
					}

				onClickState = -2;
				count = -1;
				invalidate();
				break;
		}
		return true;
	}




	/**
	 * 获取两条线的夹角 * * @param centerX * @param centerY * @param xInView * @param yInView * @return
	 */
	public static int getRotationBetweenLines(float centerX, float centerY, float xInView, float yInView) {
		double rotation = 0;

		double k1 = (double) (centerY - centerY) / (centerX * 2 - centerX);
		double k2 = (double) (yInView - centerY) / (xInView - centerX);
		double tmpDegree = Math.atan((Math.abs(k1 - k2)) / (1 + k1 * k2)) / Math.PI * 180;

		if (xInView > centerX && yInView < centerY) {  //第一象限
			rotation = 90 - tmpDegree;
		} else if (xInView > centerX && yInView > centerY) //第二象限
		{
			rotation = 90 + tmpDegree;
		} else if (xInView < centerX && yInView > centerY) { //第三象限
			rotation = 270 - tmpDegree;
		} else if (xInView < centerX && yInView < centerY) { //第四象限
			rotation = 270 + tmpDegree;
		} else if (xInView == centerX && yInView < centerY) {
			rotation = 0;
		} else if (xInView == centerX && yInView > centerY) {
			rotation = 180;
		}
		return (int) rotation;
	}

	/**
	 * 求两个点之间的距离 * * @return
	 */
	public static double getDisForTwoSpot(float x1, float y1, float x2, float y2) {
		float width, height;
		if (x1 > x2) {
			width = x1 - x2;
		} else {
			width = x2 - x1;
		}

		if (y1 > y2) {
			height = y2 - y1;
		} else {
			height = y2 - y1;
		}
		return Math.sqrt((width * width) + (height * height));
	}

	/**
	 * MenuItem的点击事件接口
	 *
	 * @author zhy
	 */
	public interface OnMenuItemClickListener {
		void itemClick(int pos);

		void itemCenterClick();
	}

	/**
	 * MenuItem的点击事件接口
	 */
	private OnMenuItemClickListener mOnMenuItemClickListener;

	/**
	 * 设置MenuItem的点击事件接口
	 *
	 * @param mOnMenuItemClickListener
	 */
	public void setOnMenuItemClickListener(OnMenuItemClickListener mOnMenuItemClickListener) {
		this.mOnMenuItemClickListener = mOnMenuItemClickListener;
	}



}