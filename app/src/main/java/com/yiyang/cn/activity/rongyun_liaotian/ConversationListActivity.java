package com.yiyang.cn.activity.rongyun_liaotian;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.xin_tuanyou.YouZhanDetailsActivity;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.model.Conversation;

public class ConversationListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManage = getSupportFragmentManager();
        ConversationListFragment fragement = (ConversationListFragment) fragmentManage.findFragmentById(R.id.conversationlist);
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")
                .build();
        fragement.setUri(uri);


        RongIM.setConversationListBehaviorListener(new RongIM.ConversationListBehaviorListener() {
            /**
             * 会话头像点击监听
             *
             * @param context          上下文。
             * @param conversationType 会话类型。
             * @param targetId         被点击的用户id。
             * @return  true 拦截事件, false 执行融云 SDK 内部默认处理逻辑
             */
            @Override
            public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String targetId) {
                UIHelper.ToastMessage(mContext,"点击了头像");
                return false;
            }
            /**
             * 会话头像长按监听
             *
             * @param context          上下文。
             * @param conversationType 会话类型。
             * @param targetId         被点击的用户id。
             * @return true 拦截事件, false 执行融云 SDK 内部默认处理逻辑
             */
            @Override
            public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String targetId) {
                UIHelper.ToastMessage(mContext,"长按了头像");
                return false;
            }

            /**
             * 会话列表中的 Item 长按监听
             *
             * @param context      上下文。
             * @param view         触发点击的 View。
             * @param conversation 长按时的会话条目
             * @return true 拦截事件, false 执行融云 SDK 内部默认处理逻辑
             */
            @Override
            public boolean onConversationLongClick(Context context, View view, UIConversation conversation) {
                UIHelper.ToastMessage(mContext,"会话列表的长按监听");
                return false;
            }
            /**
             * 会话列表中的 Item 点击监听
             *
             * @param context      上下文。
             * @param view         触发点击的 View。
             * @param conversation 长按时的会话条目
             * @return true 拦截事件, false 执行融云 SDK 内部默认处理逻辑
             */
            @Override
            public boolean onConversationClick(Context context, View view, UIConversation conversation) {
                UIHelper.ToastMessage(mContext,"会话列表中的 Item 点击监听");
                return false;
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.conversationlist;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */

}
