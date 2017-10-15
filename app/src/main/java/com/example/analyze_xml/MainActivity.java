package com.example.analyze_xml;

import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Android解析XML资源：
 * 1、在res目录中，新建一个xml目录，注意需要时Android resource directory类型
 * 2、在xml目录中创建一个名为xxx.xml的文件。例如customers.xml
 * 3、在xxx.xml文件中添加节点信息，如，根节点为customers，子节点为customer，参见示例文件res/xml/customers.xml
 * 4、解析步骤见代码
 */
public class MainActivity extends AppCompatActivity {
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv=(TextView)findViewById(R.id.tv_text);

        //获取xml文档
        XmlResourceParser xrp=getResources().getXml(R.xml.customers);
        //创建一个空的字符串构建器
        StringBuilder sb=new StringBuilder("");

        try {
            //如果没到Xml文档的结尾
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
                //判断是否为开始标记
                if (xrp.getEventType() == XmlResourceParser.START_TAG){

                    String tagName=xrp.getName();//获取标记名

                    if (tagName.equals("customer")){//如果标记名是customer
                        sb.append("姓名：").append(xrp.getAttributeValue(0)).append("， ");//获取姓名
                        sb.append("电话："+xrp.getAttributeValue(1)+"， ");//获取电话
                        sb.append("邮件："+xrp.getAttributeValue(2));//获取邮件
                        sb.append("\n");//添加换行符
                    }
                }

                xrp.next();//下一个标记
            }

            mTv.setText(sb.toString());//将获取到的xml文件内容显示出来
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
}
