package com.chiemy.loadingdailog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created: chiemy
 * Date: 17/6/26
 * Description:
 */

public class LoadingDialog {
    private Context mContext;
    private Builder mBuilder;
    private MyDialog mMyDialog;

    private LoadingDialog(Builder builder) {
        mBuilder = builder;
        mContext = builder.mContext;
        createDialog(builder);
    }

    private void createDialog(Builder builder) {
        mMyDialog = new MyDialog(mContext);
        mMyDialog.setCancelable(false);
        mMyDialog.setCanceledOnTouchOutside(false);
    }

    public void loading() {
        mMyDialog.setContentView(mBuilder.mLoadingView);
        mMyDialog.show();
    }

    public void dimiss() {
        mMyDialog.dismiss();
    }

    public void success() {

    }

    public void error() {

    }

    public static class Builder {
        private Context mContext;
        private int mLoadingView = R.layout.layout_loading_default;
        private int mSuccessView;
        private int mErrorView;

        private boolean mBlockUserAction = true;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setLoadingView(@LayoutRes int loadingView) {
            mLoadingView = loadingView;
            return this;
        }

        public Builder setSuccessView(@LayoutRes int successView) {
            mSuccessView = successView;
            return this;
        }

        public Builder setErrorView(@LayoutRes int errorView) {
            mLoadingView = errorView;
            return this;
        }

        /**
         * 用户是否可操作
         */
        public Builder setBlockUserAction(boolean blockUserAction) {
            mBlockUserAction = blockUserAction;
            return this;
        }

        public LoadingDialog build() {
            return new LoadingDialog(this);
        }
    }


    private class MyDialog extends Dialog {
        private boolean mBlockUserAction;

        public MyDialog(Context context) {
            super(context, R.style.DialogNoTitle);
        }

        public void setBlockUserAction(boolean blockUserAction) {
            mBlockUserAction = blockUserAction;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Window window = getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0));
            // window.setWindowAnimations(R.style.Animation_CustomDialog2);
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            if (!mBlockUserAction) {
                params.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            }
            window.setAttributes(params);
        }

    }
}
