package serio.tim.android.com.snapchatclone.View;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import serio.tim.android.com.snapchatclone.R;

/**
 * Created by Tim on 10/23/17.
 */

public class SnapTabsView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ImageView mCenterView;
    private ImageView mStartView;
    private ImageView mBottomView;
    private ImageView mEndView;
    private View mIndicator;

    private float mCenterTranslationY;
    private float mIndicatorTranslationX;
    private float mEndViewsTranslationX;

    private int mCenterColor;
    private int mOffsetColor;
    private ArgbEvaluator mColorEval;

    public SnapTabsView(@NonNull Context context) {
        this(context, null);
    }

    public SnapTabsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SnapTabsView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_snap_tabs, this, true);

        mCenterColor = ContextCompat.getColor(getContext(), R.color.white);
        mOffsetColor = ContextCompat.getColor(getContext(), R.color.dark_grey);

        final int centerPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80,
                getContext().getResources().getDisplayMetrics());

        mColorEval = new ArgbEvaluator();

        mCenterView = (ImageView) findViewById(R.id.vst_center_image);
        mStartView = (ImageView) findViewById(R.id.vst_start_image);
        mEndView = (ImageView) findViewById(R.id.vst_end_image);
        mBottomView = (ImageView) findViewById(R.id.vst_bottom_image);
        mIndicator = findViewById(R.id.vst_indicator);

        mBottomView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                mCenterTranslationY = getHeight() - mBottomView.getBottom();
                float distanceBetween = mBottomView.getX() - mStartView.getX();
                mEndViewsTranslationX = distanceBetween - centerPadding;

                mIndicatorTranslationX = centerPadding;

                mBottomView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }

        });
    }

    public void setViewPager(final ViewPager viewPager) {
        if(viewPager != null) {
            viewPager.addOnPageChangeListener(this);
            mStartView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(viewPager.getCurrentItem() != 0)
                        viewPager.setCurrentItem(0);
                }

            });

            mEndView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(viewPager.getCurrentItem() != 2)
                        viewPager.setCurrentItem(2);
                }

            });
        }
    }

    public ImageView getCenterView() {
        return mCenterView;
    }

    public ImageView getStartView() {
        return mStartView;
    }

    public ImageView getBottomView() {
        return mBottomView;
    }

    public ImageView getEndView() {
        return mEndView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(position == 0) {
            setUpViews(
                    1 - positionOffset,
                    .7f + (positionOffset * .3f),
                    (1 - positionOffset) * mCenterTranslationY,
                    -mIndicatorTranslationX * (1-positionOffset)
            );
        }
        else if(position == 1) {
            setUpViews(
                    positionOffset,
                    .7f + ((1 - positionOffset) * .3f),
                    positionOffset * mCenterTranslationY,
                    mIndicatorTranslationX * positionOffset
            );
        }
    }

    private void setUpViews(float fractionFromCenter, float centerScale, float centerTransY, float indicatorTransX) {
        mIndicator.setAlpha(fractionFromCenter);
        mIndicator.setScaleX(fractionFromCenter);

        mStartView.setTranslationX(mEndViewsTranslationX * fractionFromCenter);
        mEndView.setTranslationX(-mEndViewsTranslationX * fractionFromCenter);

        mCenterView.setScaleX(centerScale);
        mCenterView.setScaleY(centerScale);

        mCenterView.setTranslationY(centerTransY);
        mBottomView.setTranslationY(centerTransY);

        mIndicator.setTranslationX(indicatorTransX);
        mBottomView.setAlpha(1 - fractionFromCenter);

        mBottomView.setClickable(mBottomView.getAlpha() > .5);

        int color = (int) mColorEval.evaluate(fractionFromCenter, mCenterColor, mOffsetColor);
        mStartView.setColorFilter(color);
        mEndView.setColorFilter(color);
        mCenterView.setColorFilter(color);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

//    private ImageView mCenterImage;
//    private ImageView mStartImage;
//    private ImageView mEndImage;
//    private ImageView mBottomImage;
//
//    private View mIndicator;
//
//    private ArgbEvaluator mArgbEvaluator;
//    private int mCenterColor;
//    private int mSideColor;
//
//    private int mEndViewsTranslationX;
//    private int mIndicatorTranslationX;
//    private int mCenterTranslationY;
//
//
//    public SnapTabsView(@NonNull Context context) {
//        this(context, null);
//    }
//
//    public SnapTabsView(@NonNull Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public SnapTabsView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//
//        init();
//    }
//
//    public void setUpWithViewPager(final ViewPager viewPager) {
//        viewPager.addOnPageChangeListener(this);
//
//        mStartImage.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(viewPager.getCurrentItem() != 0 ) {
//                    viewPager.setCurrentItem(0);
//                }
//            }
//        });
//
//        mEndImage.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(viewPager.getCurrentItem() != 2 ) {
//                    viewPager.setCurrentItem(2);
//                }
//            }
//        });
//    }
//
//    private void init() {
//        LayoutInflater.from(getContext()).inflate(R.layout.view_snap_tabs, this, true);
//
//        mCenterImage = (ImageView)findViewById(R.id.vst_center_image);
//        mStartImage = (ImageView)findViewById(R.id.vst_start_image);
//        mEndImage = (ImageView)findViewById(R.id.vst_end_image);
//        mBottomImage = (ImageView)findViewById(R.id.vst_bottom_image);
//
//        mIndicator = findViewById(R.id.vst_indicator);
//
//        mCenterColor = ContextCompat.getColor(getContext(), R.color.white);
//        mSideColor = ContextCompat.getColor(getContext(), R.color.dark_grey);
//
//        final int centerPadding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getContext().getResources().getDisplayMetrics());
//
//        mArgbEvaluator = new ArgbEvaluator();
//
//        mIndicatorTranslationX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());
//
//        mBottomImage.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                mCenterTranslationY = getHeight() - mBottomImage.getBottom();
//                float distanceBetween = mBottomImage.getX() - mStartImage.getX();
//                mEndViewsTranslationX = (int)distanceBetween - centerPadding;
//                //mEndViewsTranslationX = (int) ((mBottomImage.getX() - mStartImage.getX()) - mIndicatorTranslationX);
//                //mBottomImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                mIndicatorTranslationX = centerPadding;
//                mBottomImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                //mCenterTranslationY = getHeight() - mBottomImage.getBottom();
//            }
//        });
//
//    }
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        if(position == 0) {
//            setColor(1 - positionOffset);
//            moveViews(1 - positionOffset);
//
//            moveAndScaleCenter(1 - positionOffset);
//
//            mIndicator.setTranslationX((positionOffset - 1) * mIndicatorTranslationX);
//        } else if(position == 1) {
//            setColor(positionOffset);
//            moveViews(positionOffset);
//
//            moveAndScaleCenter(positionOffset);
//
//            mIndicator.setTranslationX(positionOffset * mIndicatorTranslationX);
//        }
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//
//    private void moveAndScaleCenter(float fractionFromCenter) {
//        float scale = .7f + ((1 - fractionFromCenter) * .3f);
//
//        mCenterImage.setScaleX(scale);
//        mCenterImage.setScaleY(scale);
//
//        int translation = (int)(fractionFromCenter * mCenterTranslationY);
//
//        mCenterImage.setTranslationY(translation);
//        mBottomImage.setTranslationY(translation);
//
//        mBottomImage.setAlpha(1 - fractionFromCenter);
//    }
//
//    private void moveViews(float fractionFromCenter) {
//        mStartImage.setTranslationX(fractionFromCenter * mEndViewsTranslationX);
//        mEndImage.setTranslationY(-fractionFromCenter * mEndViewsTranslationX);
//
//        mIndicator.setAlpha(fractionFromCenter);
//        mIndicator.setScaleX(fractionFromCenter);
//    }
//
//    private void setColor(float fractionFromCenter) {
//        int color = (int)mArgbEvaluator.evaluate(fractionFromCenter, mCenterColor, mSideColor);
//        mCenterImage.setColorFilter(color);
//        mStartImage.setColorFilter(color);
//        mEndImage.setColorFilter(color);
//    }
}
