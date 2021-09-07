package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.rongyun_liaotian.ConversationListActivity;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.MineModel;
import com.yiyang.cn.util.AlertUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

public class Demo_rongyun extends AppCompatActivity {

    @BindView(R.id.tv_huihua)
    TextView tvHuihua;
    @BindView(R.id.tv_liebiao)
    TextView tvLiebiao;

    private String inst_accid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_rongyun);
        ButterKnife.bind(this);
        inst_accid = getIntent().getStringExtra("inst_accid");
        getLiaoTian(Demo_rongyun.this);
        String str = UserManager.getManager(Demo_rongyun.this).getRongYun();
        if (!StringUtils.isEmpty(str)) {
            RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

                /**
                 * 获取设置用户信息. 通过返回的 userId 来封装生产用户信息.
                 * @param userId 用户 ID
                 * @return
                 */
                @Override
                public UserInfo getUserInfo(String userId) {
                    String touXiangUrl = PreferenceHelper.getInstance(Demo_rongyun.this).getString(App.CUN_GEREN_TOUXIANG, "");
                    UserInfo userInfo = new UserInfo(userId, UserManager.getManager(Demo_rongyun.this).getUserName(), Uri.parse(touXiangUrl));
                    return userInfo;
                }

            }, true);
        }

        tvHuihua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * <p>启动会话界面。</p>
                 * <p>使用时，可以传入多种会话类型 {@link io.rong.imlib.model.Conversation.ConversationType} 对应不同的会话类型，开启不同的会话界面。
                 * 如果传入的是 {@link io.rong.imlib.model.Conversation.ConversationType#CHATROOM}，sdk 会默认调用
                 * {@link RongIMClient#joinChatRoom(String, int, RongIMClient.OperationCallback)} 加入聊天室。
                 * 如果你的逻辑是，只允许加入已存在的聊天室，请使用接口 {@link #startChatRoomChat(Context, String, boolean)} 并且第三个参数为 true</p>
                 *
                 * @param context          应用上下文。
                 * @param conversationType 会话类型。
                 * @param targetId         根据不同的 conversationType，可能是用户 Id、群组 Id 或聊天室 Id。
                 * @param title            聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
                 */

                //   RongIM.getInstance().startConversation(Demo_rongyun.this, );
                //RongIM.getInstance().startPrivateChat(Demo_rongyun.this, inst_accid, "店主");

                Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
                String targetId = inst_accid;
                String title = "神灯店铺";

                Bundle bundle = new Bundle();
                bundle.putString("dianpuming", title);
                RongIM.getInstance().startConversation(Demo_rongyun.this, conversationType, targetId, title, bundle);
            }
        });
        tvLiebiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Boolean> hashMap = new HashMap<>();
                //会话类型 以及是否聚合显示
                hashMap.put(Conversation.ConversationType.PRIVATE.getName(), false);
                hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(), true);
                hashMap.put(Conversation.ConversationType.SYSTEM.getName(), true);
                RongIM.getInstance().startConversationList(Demo_rongyun.this, hashMap);
            }
        });

        RongIM.setConversationClickListener(new RongIM.ConversationClickListener() {

            /**
             * 用户头像点击事件
             *
             * @param context          上下文。
             * @param conversationType 会话类型。
             * @param user             被点击的用户的信息。
             * @param targetId         会话 id
             * @return true 拦截事件; false 不拦截, 默认执行 SDK 内部逻辑
             */
            @Override
            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo user, String targetId) {
                return false;
            }

            /**
             * 用户头像长按事件
             *
             * @param context          上下文。
             * @param conversationType 会话类型。
             * @param user             被点击的用户的信息。
             * @param targetId         会话 id
             * @return true 拦截事件; false 不拦截, 默认执行 SDK 内部逻辑
             */
            @Override
            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo user, String targetId) {
                return false;
            }

            @Override
            public boolean onMessageClick(Context context, View view, Message message) {
                return false;
            }

            /**
             * 消息超链接内容点击事件
             *
             * @param context 上下文。
             * @param link  超链接文本
             * @param message 被点击的消息的实体信息。
             * @return true 拦截事件; false 不拦截, 默认执行 SDK 内部逻辑
             */
            @Override
            public boolean onMessageLinkClick(Context context, String link, Message message) {
                return false;
            }

            /**
             * 消息长按事件
             *
             * @param context 上下文。
             * @param view    触发点击的 View。
             * @param message 被点击的消息的实体信息。
             * @return true 拦截事件; false 不拦截, 默认执行 SDK 内部逻辑
             */
            @Override
            public boolean onMessageLongClick(Context context, View view, Message message) {
                return false;
            }
        });

    }

    public void getLiaoTian(Context context) {
        Map<String, String> map = new HashMap<>();
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("code", "04187");
        map.put("key", Urls.key);
        map.put("accid", inst_accid);

        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>
                post("https://shop.hljsdkj.com/shop_new/app/user").
                tag(context).
                upJson(gson.toJson(map)).
                execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Object>> response) {
                        //  UIHelper.ToastMessage(context, "发送成功", Toast.LENGTH_SHORT);
                        UIHelper.ToastMessage(Demo_rongyun.this, "建立聊天成功");
                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        AlertUtil.t(context, response.getException().getMessage());
                    }
                });
    }


    public static void actionStart(Context context, String inst_accid) {
        Intent intent = new Intent(context, Demo_rongyun.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("inst_accid", inst_accid);
        context.startActivity(intent);
    }
}
