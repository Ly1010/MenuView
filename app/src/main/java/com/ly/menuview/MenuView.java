package com.ly.menuview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MenuView extends FrameLayout {

    public Button btn_menu_1;
    public Button btn_menu_2;
    public Button btn_menu_3;
    public Button btn_menu_4;
    public Button btn_menu_5;
    public Button btn_menu_menu;

    private Boolean isOpen = false;
    private int width;
    private int height;
    private int r;

    public MenuView(Context context) {
        this(context, null);
    }

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.menu_view, this);

        btn_menu_1 = (Button)view.findViewById(R.id.btn_menu_1);
        btn_menu_2 = (Button)view.findViewById(R.id.btn_menu_2);
        btn_menu_3 = (Button)view.findViewById(R.id.btn_menu_3);
        btn_menu_4 = (Button)view.findViewById(R.id.btn_menu_4);
        btn_menu_5 = (Button)view.findViewById(R.id.btn_menu_5);
        btn_menu_menu = (Button)view.findViewById(R.id.btn_menu_menu);

        btn_menu_menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){
                    closeMenu();
                    isOpen = false;
                }else{
                    openMenu();
                    isOpen = true;
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(0, widthMeasureSpec);
        height = getDefaultSize(0, heightMeasureSpec);
        int buttonwidth = btn_menu_1.getMeasuredWidth();
        r = height - buttonwidth;
        if(width < height){
            r = width - buttonwidth;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private void closeMenu(){
        playCloseAnimate(btn_menu_1, 0);
        playCloseAnimate(btn_menu_2, 1);
        playCloseAnimate(btn_menu_3, 2);
        playCloseAnimate(btn_menu_4, 3);
        playCloseAnimate(btn_menu_5, 4);
    }

    private void openMenu(){
        playOpenAnimate(btn_menu_1, 0);
        playOpenAnimate(btn_menu_2, 1);
        playOpenAnimate(btn_menu_3, 2);
        playOpenAnimate(btn_menu_4, 3);
        playOpenAnimate(btn_menu_5, 4);
    }

    private void playOpenAnimate(View view, int i){
        int translationX = (int)(r * Math.sin(Math.toRadians((90 / 4) * i)));
        int translationY = (int)(r * Math.cos(Math.toRadians((90 / 4) * i)));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, -translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, -translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0, 1),
                ObjectAnimator.ofFloat(view, "scaleY", 0, 1),
                ObjectAnimator.ofFloat(view, "")
        );
        set.setDuration(500).start();
    }

    private void playCloseAnimate(View view, int i){
        int translationX = (int)(r * Math.sin(Math.toRadians((90 / 4) * i)));
        int translationY = (int)(r * Math.cos(Math.toRadians((90 / 4) * i)));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", -translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", -translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1, 0),
                ObjectAnimator.ofFloat(view, "scaleY", 1, 0)
        );
        set.setDuration(500).start();
    }


    public static int getDefaultSize(int size, int measureSpec){
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode){
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }


}
