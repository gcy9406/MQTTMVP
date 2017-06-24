package cn.netrelay.mqttmvp.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.netrelay.mqttmvp.R;
import cn.netrelay.mqttmvp.adapter.MyAdapter;
import cn.netrelay.mqttmvp.bean.PostInfo;
import cn.netrelay.mqttmvp.callback.OnItemClickListener;
import cn.netrelay.mqttmvp.dagger.component.DaggerMyAdapterComponent;
import cn.netrelay.mqttmvp.dagger.component.DaggerMainComponent;
import cn.netrelay.mqttmvp.dagger.component.DaggerSPComponent;
import cn.netrelay.mqttmvp.dagger.component.MyAdapterComponent;
import cn.netrelay.mqttmvp.dagger.component.SPComponent;
import cn.netrelay.mqttmvp.dagger.module.MainModule;
import cn.netrelay.mqttmvp.dagger.module.MyAdapterModule;
import cn.netrelay.mqttmvp.dagger.module.SPModule;
import cn.netrelay.mqttmvp.present.Presenter;
import cn.netrelay.mqttmvp.utils.SharedP;

import static butterknife.OnTextChanged.Callback.AFTER_TEXT_CHANGED;

public class MainActivity extends AppCompatActivity implements IView, OnItemClickListener {

    @BindView(R.id.mqtt_address)
    EditText mqttAddress;
    @BindView(R.id.mqtt_port)
    EditText mqttPort;
    @BindView(R.id.mqtt_server)
    LinearLayout mqttServer;
    @BindView(R.id.mqtt_connect)
    Button mqttConnect;
    @BindView(R.id.mqtt_send_topic)
    EditText mqttSendTopic;
    @BindView(R.id.mqtt_send_mesg)
    EditText mqttSendMesg;
    @BindView(R.id.mqtt_button_send)
    Button mqttButtonSend;
    @BindView(R.id.mqtt_sub_topic)
    EditText mqttSubTopic;
    @BindView(R.id.mqtt_button_sub)
    Button mqttButtonSub;
    @BindView(R.id.mqtt_clear)
    Button mqttClear;
    @BindView(R.id.mqtt_send_show)
    CheckBox mqttSendShow;
    @BindView(R.id.mqtt_sub_list)
    RecyclerView mqttSubList;

    @Inject
    Presenter presenter;

    @Inject
    SharedP sharedP;

    @Inject
    MyAdapter myAdapter;

    private List<PostInfo> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        data = new ArrayList<>();

        SPComponent spComponent = DaggerSPComponent.builder()
                .sPModule(new SPModule(this))
                .build();
        MyAdapterComponent myAdapterComponent = DaggerMyAdapterComponent.builder()
                .myAdapterModule(new MyAdapterModule(this))
                .build();

        DaggerMainComponent.builder()
                .myAdapterComponent(myAdapterComponent)
                .sPComponent(spComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        presenter.getShared(sharedP);
        mqttSubList.setLayoutManager(new LinearLayoutManager(this));
        mqttSubList.setAdapter(myAdapter);
        mqttSubList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        myAdapter.setOnItemClickListener(this);

        EventBus.getDefault().register(this);
    }


    @Override
    public void setAddress(String address) {
        mqttAddress.setText(address);
    }

    @Override
    public void setPort(String port) {
        mqttPort.setText(port);
    }

    @Override
    public void setSendTopic(String topic) {
        mqttSendTopic.setText(topic);
    }

    @Override
    public void setSendCmd(String cmd) {
        mqttSendMesg.setText(cmd);
    }

    @Override
    public void setRecvTopic(String topic) {
        mqttSubTopic.setText(topic);
    }

    @Override
    public void setShowMsg(boolean show) {
        mqttSendShow.setChecked(show);
    }

    @OnClick({R.id.mqtt_connect, R.id.mqtt_button_send, R.id.mqtt_button_sub, R.id.mqtt_clear,R.id.mqtt_send_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mqtt_connect:
                if (mqttConnect.getText().equals("连接")){
                    presenter.mqttConnect(mqttAddress.getText().toString().trim(),
                            mqttPort.getText().toString().trim());
                    mqttServer.setVisibility(View.GONE);
                    mqttConnect.setText("取消连接");

                }else {
                    presenter.mqttDisconnect();
                    mqttServer.setVisibility(View.VISIBLE);
                    mqttConnect.setText("连接");
                    mqttSubTopic.setEnabled(true);
                    mqttButtonSub.setText("订阅");
                }

                break;
            case R.id.mqtt_button_send:
                presenter.mqttSend(mqttSendTopic.getText().toString().trim(),
                        mqttSendMesg.getText().toString().trim());

                if (mqttSendShow.isChecked()) {
                    Date date = new Date(System.currentTimeMillis());
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                    PostInfo postInfo = new PostInfo(mqttSendMesg.getText().toString().trim(), sdf.format(date), 1);
                    data.add(postInfo);
                    myAdapter.setData(data);
                }
                break;
            case R.id.mqtt_button_sub:
                presenter.mqttSubscribe(mqttSubTopic.getText().toString().trim());
                if (mqttButtonSub.getText().equals("订阅")){
                    mqttSubTopic.setEnabled(false);
                    mqttButtonSub.setText("取消订阅");
                }else {
                    mqttSubTopic.setEnabled(true);
                    mqttButtonSub.setText("订阅");
                }
                break;
            case R.id.mqtt_clear:
                data.clear();
                myAdapter.setData(data);
                break;
            case R.id.mqtt_send_show:
                presenter.setShared("mqtt_show",mqttSendShow.isChecked()?"show":"hide",sharedP);
                break;
        }
    }

    @OnTextChanged(value = R.id.mqtt_address, callback = AFTER_TEXT_CHANGED)
    public void addressChanged(CharSequence text){
        presenter.setShared("mqtt_address",text.toString(),sharedP);
    }

    @OnTextChanged(value = R.id.mqtt_port, callback = AFTER_TEXT_CHANGED)
    public void portChanged(CharSequence text){
        presenter.setShared("mqtt_port",text.toString(),sharedP);
    }

    @OnTextChanged(value = R.id.mqtt_send_topic, callback = AFTER_TEXT_CHANGED)
    public void sendTopicChanged(CharSequence text){
        presenter.setShared("mqtt_send",text.toString(),sharedP);
    }

    @OnTextChanged(value = R.id.mqtt_send_mesg, callback = AFTER_TEXT_CHANGED)
    public void sendCmdChanged(CharSequence text){
        presenter.setShared("mqtt_cmd",text.toString(),sharedP);
    }

    @OnTextChanged(value = R.id.mqtt_sub_topic, callback = AFTER_TEXT_CHANGED)
    public void recvTopicChanged(CharSequence text){
        presenter.setShared("mqtt_recv",text.toString(),sharedP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.mqttDisconnect();
        EventBus.getDefault().post(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMQTTMsg(String msg){
        Log.d("@@@", "getMQTTMsg: ==============="+msg);
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        PostInfo postInfo = new PostInfo(msg,sdf.format(date),0);
        data.add(postInfo);
        myAdapter.setData(data);

        if (data.size() > 0) {
            mqttSubList.smoothScrollToPosition(data.size() - 1);
        }

    }

    @Override
    public void doClick(int pos, String data) {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("当前内容")
                .setMessage(data)
                .setCancelable(true)
                .create()
                .show();
    }
}
