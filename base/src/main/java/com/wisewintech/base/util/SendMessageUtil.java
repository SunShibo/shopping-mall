package com.wisewintech.base.util;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;


/**
 * Created by Administrator on 2019/12/30.
 */
public class SendMessageUtil {

    static String accessKeyId="LTAIrYEzjhGJigUM";
    static String accessSecret="KG9W4CwP3kAjnNEx9eiU40XtboouNZ";


    public static CommonResponse sendSignInCodeMessage(String mobile, String content,String templateCode){
        DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", "多语咖");
        request.putQueryParameter("TemplateCode", "SMS_165412502");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+content+"\"}");
        CommonResponse response=null;
        try {
            response = client.getCommonResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return response;
    }



}
