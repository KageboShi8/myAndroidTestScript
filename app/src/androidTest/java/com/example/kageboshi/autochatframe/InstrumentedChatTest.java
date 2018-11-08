package com.example.kageboshi.autochatframe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;
import android.widget.Toast;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static com.example.kageboshi.autochatframe.Constants.CHAT_BACK_ID;
import static com.example.kageboshi.autochatframe.Constants.CONTACTS_NAME;
import static com.example.kageboshi.autochatframe.Constants.DEBUG_TAG;
import static com.example.kageboshi.autochatframe.Constants.LAUNCH_TIMEOUT;
import static com.example.kageboshi.autochatframe.Constants.SEND_BACK_ID;
import static com.example.kageboshi.autochatframe.Constants.SEND_BUTTON_ID;
import static com.example.kageboshi.autochatframe.Constants.SEND_TEXTVIEW_ID;
import static com.example.kageboshi.autochatframe.Constants.TARGET_PACKAGE_NAME;
import static com.example.kageboshi.autochatframe.Constants.WECHAT_TEAM;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedChatTest {


    private static final int CHAT_NUM = 15;
    int swipedChildCount = 0;
    private UiDevice mDevice;
    private List<UiObject2> children;
    private UiScrollable listScroller;
    private UiObject2 focusdObject;
    private int count;
    private Point listPoint;
    private int index = 0;
    private List<String> dataList;

    @Before
    public void setUp() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        assertThat(mDevice, notNullValue());
        Log.e(DEBUG_TAG, "setUp");

    }

    @Test
    public void startTest() {
        try {
            mDevice.wakeUp();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        openApp();
        startChat();
        // TODO: 18-10-29 这里有一个编辑内容的问题
        List<String> chatData = initChatData();
        //      chooseContactsAndChat(chatData);
        //      chooseContactsAndChat2(chatData);
        chooseContactsAndChat3(chatData);

    }

    private void openApp() {
        String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);
        //Log.e(DEBUG_TAG,launcherPackage);
        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(TARGET_PACKAGE_NAME);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(TARGET_PACKAGE_NAME).depth(0)),
                LAUNCH_TIMEOUT);
    }

    private void startChat() {
        try {
            UiObject contact_button = mDevice.findObject(new UiSelector().text(CONTACTS_NAME));
            contact_button.clickAndWaitForNewWindow(LAUNCH_TIMEOUT);
            Log.e(DEBUG_TAG, "CLICK CONTACTS");
        } catch (UiObjectNotFoundException e) {
            // toastException();
            Log.e(DEBUG_TAG, "CLICK CONTACTS ERROR");
        }
        mDevice.wait(Until.hasObject(By.clazz("android.widget.ListView")),
                LAUNCH_TIMEOUT);

    }

    private List<String> initChatData() {
        dataList = new ArrayList<String>();
        dataList.add("什么学历?");
        dataList.add("小学没毕业.");
        dataList.add("打过架吗?");
        dataList.add("家常便饭.");
        dataList.add("有案底吗?");
        dataList.add("刚刚出来");
        dataList.add("体能呢?");
        dataList.add("还可以，一脚可以踢翻小贩的小三轮车.");
        dataList.add("敢拿人家的东西吗?");
        dataList.add("这是我的强项,就像拿自己的东西一样.");
        dataList.add("老头敢打么?");
        dataList.add("小菜，俺爹就是让俺打残的?");
        dataList.add("你考试通过了,我们城管需要的就是你这样的人才!");
        dataList.add("再问一句 出事了怎么办?");
        dataList.add("就说是临时工?");
        dataList.add("今晚上班");
        dataList.add("别紧张,我又不是什么好人……");
        dataList.add("手拿菜刀砍电线，一路火花带闪电。");
        dataList.add("最近总是失眠，16小时就醒一次。");
        dataList.add("人人都说我丑，其实我只是美得不明显。");
        dataList.add("我从不以强凌弱～～～我欺负他之前真不知道他比我弱…");
        dataList.add("绅士无非就是耐心的狼。");
        dataList.add("我又不是人民币，怎么能让人人都喜欢我？！");
        dataList.add("你为什么不找个安静的地方自个儿数数脑细胞儿去？");
        dataList.add("知道你过得不好，我也就安心了。");
        dataList.add("人生只有三天，活在昨天的人迷惑；活在明天的人等待；活在今天的人最踏实。");
        dataList.add("通往成功的路，总是在施工中。");
        dataList.add("我由衷地想把赚钱变成我的业余爱好。");
        dataList.add("我曾经爱过的男孩，有着世界上最英俊的背影。");
        dataList.add("女子无才便是德，我一定是太缺德了。");
        dataList.add("不成熟男人的标志是可以为了理想壮烈的牺牲，成熟男人的标志的可以为了理想卑贱的活着。");
        dataList.add("漫漫人生路，总会错几步。");
        dataList.add("我要让全世界都知道我很低调。");
        dataList.add("我多想一个不小心就和你白头偕老。");
        dataList.add("真的猛士，敢于直面自己未化妆的脸。");
        dataList.add("你跟我说啥？");

        return dataList;
    }

    private void chooseContactsAndChat3(List<String> chatContentList) {
        getListPointer();
        List<UiObject2> objects = mDevice.findObjects(By.clazz("android.view.View"));
        count = 0;
        while (count < CHAT_NUM) {
            chatOnePage(objects, chatContentList);
            Log.e(DEBUG_TAG, "count=" + count);
            if (count >= CHAT_NUM) {
                break;
            } else if (count <= CHAT_NUM) {
                if (focusdObject != null) {
                    //         Log.e(DEBUG_TAG, "START SCROLL");
                    swipePage(focusdObject);
                    objects = mDevice.findObjects(By.clazz("android.view.View"));
                } else {
                    Log.e(DEBUG_TAG, "focusdObject is null");
                }
            }
        }


    }

    private void getListPointer() {
        UiObject2 listView = mDevice.findObject(By.clazz("android.widget.ListView"));
        Rect visibleBounds = listView.getVisibleBounds();
        int top = visibleBounds.top;
        int left = visibleBounds.left;
        listPoint = new Point(top, left);
    }

    private void chatOnePage(List<UiObject2> objects, List<String> chatContentList) {
        for (UiObject2 object :
                objects) {
            if (object.getText() != null) {
                Log.e(DEBUG_TAG, object.getText());
                focusdObject = object;
                if (object.getText() .equals("微信团队")) {
                    continue;
                }
                object.click();
                mDevice.wait(Until.findObject(By.text("发消息")), LAUNCH_TIMEOUT);

                UiObject2 sendText = mDevice.findObject(By.text("发消息"));
                sendText.click();
                mDevice.wait(Until.findObject(By.clazz("android.widget.ScrollView")), LAUNCH_TIMEOUT);

                UiObject2 editText = mDevice.findObject(By.clazz("android.widget.EditText"));
                if (editText != null) {
                    editText.setText(chatContentList.get(index));
                    mDevice.wait(Until.findObject(By.text("发送")), LAUNCH_TIMEOUT);
                    UiObject2 sendButton = mDevice.findObject(By.clazz("android.widget.Button"));
                    if (sendButton != null) {
                        if (sendButton.getText() != null) {
                            if (sendButton.getText().equals("发送")) {
                                sendButton.click();
                            }
                        }
                    } else {
                        break;
                    }
                    count++;
//                    UiObject2 sendCheck = mDevice.findObject(By.text("发送"));
//                    if (sendCheck != null) {
//                        sendCheck.click();
//                    }
                    getRandomIndex();

                    mDevice.pressBack();
                    boolean isMain = mDevice.hasObject(By.text("通讯录"));
                    if (!isMain) {
                        mDevice.pressBack();
                    }
                    UiObject contact_button = mDevice.findObject(new UiSelector().text(CONTACTS_NAME));
                    try {
                        contact_button.clickAndWaitForNewWindow(LAUNCH_TIMEOUT);
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (count >= CHAT_NUM) {
                        break;
                    }
                } else {
                    Log.d(DEBUG_TAG, "没找到editText");
                }
            } else {
                continue;
            }
        }
    }

    private void swipePage(UiObject2 focusdObject) {
        int top = focusdObject.getVisibleBounds().top;
        int left = focusdObject.getVisibleBounds().left;
        
//        Log.e(DEBUG_TAG, "top=" + top);
//        Log.e(DEBUG_TAG, "left=" + left);
//        Log.e(DEBUG_TAG, "left=" + listPoint.x);
//        Log.e(DEBUG_TAG, "top=" + listPoint.y);


        mDevice.swipe(300, top, 300, listPoint.y, 70);
    }

    private void getRandomIndex() {
        double random = Math.random();
        index = ((int) (random * dataList.size()));
    }

    private void chooseContactsAndChat2(List<String> chatContent) {
        //    reCount();
        for (int i = 0; i < 5; i++) {
//            if (i <= swipedChildCount) {
//                child = children.get(i);
//            } else {
            UiObject2 child = children.get(i - swipedChildCount);
//            }

            child.click();
            mDevice.wait(Until.findObject(By.res("发消息")), LAUNCH_TIMEOUT);
            UiObject text = new UiObject(new UiSelector().text("发消息"));

            try {
                text.click();
                UiObject input = new UiObject(new UiSelector().resourceId(SEND_TEXTVIEW_ID));
                if (input.exists()) {
                    //   UiObject textContent = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/mq"));
                    input.setText(chatContent.get(i));
                    UiObject sendText = new UiObject(new UiSelector().resourceId(SEND_BUTTON_ID));
                    sendText.click();

                    UiObject backInSend = new UiObject(new UiSelector().resourceId(SEND_BACK_ID));
                    backInSend.click();
                } else {
                    UiObject backInSend = new UiObject(new UiSelector().resourceId(SEND_BACK_ID));
                    backInSend.click();
                    UiObject backInChat = new UiObject(new UiSelector().resourceId(CHAT_BACK_ID));
                    backInChat.click();
                    continue;
                }
                UiObject contact_button = mDevice.findObject(new UiSelector().text(CONTACTS_NAME));
                contact_button.clickAndWaitForNewWindow(LAUNCH_TIMEOUT);
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
            if ((i - swipedChildCount) == children.size() - 1) {
                try {
//                    Rect visibleBounds = child.getVisibleBounds();
//                    int top = visibleBounds.top;

                    listScroller.swipeUp(50);
                    swipedChildCount += children.size() - 1;
                } catch (UiObjectNotFoundException e) {
                    e.printStackTrace();
                }
                //      reCount();
                continue;
            }
        }
    }

//            遍历联系人列表

    private void chooseContactsAndChat(List<String> chatContent) {
        try {
            UiObject2 contact = mDevice.findObject(By.res(Constants.CONTACT_LIST));
            int childCount = contact.getChildCount();
            Log.e(DEBUG_TAG, "childCount=" + childCount);
//            遍历联系人列表
            //因为不知到怎么得到listview的大小　　所以先用这个办法解决一下　直接给他无尽的循环　不对就break
            for (int i = 0; i < 50; i++) {
                //   Log.e(DEBUG_TAG,i+"");
                //   Log.e(DEBUG_TAG,"i="+i);
                //        if (childCount > 1) {
                List<UiObject2> children = contact.getChildren();
                Log.e(DEBUG_TAG, "ChildrenLength=" + children.size());
                try {
                    List<UiObject2> objects = children.get(i).findObjects(By.res(Constants.LIST_ITEM_ID));

                    Log.e(DEBUG_TAG, "objects=" + objects.size());
                    //  UiObject2 child = contact.getChild(new UiSelector().instance(i));
                    //Log.e(DEBUG_TAG,child.getClassName());
                    UiObject2 childName = objects.get(0);
                    Log.e(DEBUG_TAG, childName.getText());
                    if (!childName.getText().equals(WECHAT_TEAM)) {
                        childName.click();
                    } else {
                        continue;
                    }
                } catch (IndexOutOfBoundsException e) {
                }


                mDevice.wait(Until.findObject(By.res("发消息")), LAUNCH_TIMEOUT);

                UiObject text = new UiObject(new UiSelector().text("发消息"));
                if (text.exists()) {
                    //    Log.e(DEBUG_TAG, text1);
                    text.click();
                } else {
                    //todo 这里最好能让他继续执行下去　　我的办法是抛出异常
                    throw new UiObjectNotFoundException("jump exception");
                }

                //   mDevice.wait(Until.findObject(By.res(SEND_VOICE_BUTTON_ID)), LAUNCH_TIMEOUT);
                UiObject input = new UiObject(new UiSelector().resourceId(SEND_TEXTVIEW_ID));
                if (input.exists()) {
                    //   UiObject textContent = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/mq"));

                    input.setText(chatContent.get(0));

                    UiObject sendText = new UiObject(new UiSelector().resourceId(SEND_BUTTON_ID));
                    sendText.click();

                    UiObject backInChat = new UiObject(new UiSelector().resourceId(SEND_BACK_ID));
                    backInChat.click();
                } else {
                    mDevice.pressBack();
                    mDevice.pressBack();
                }
                UiObject contact_button = mDevice.findObject(new UiSelector().text(CONTACTS_NAME));
                contact_button.clickAndWaitForNewWindow(LAUNCH_TIMEOUT);

            }
        } catch (UiObjectNotFoundException e) {
            Log.d(DEBUG_TAG, "CHOOSE CONTACTS ERROR");
        }
    }

    // TODO: 18-10-26 关于脚本错误的提示还要考虑一下怎么搞
    private void toastException() {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        Toast.makeText(targetContext, "脚本运行异常", Toast.LENGTH_SHORT).show();
    }
}
