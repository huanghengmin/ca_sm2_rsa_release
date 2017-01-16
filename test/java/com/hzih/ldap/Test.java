package com.hzih.ldap;

import com.hzih.ca.utils.FileUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.bouncycastle.asn1.*;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Administrator on 15-4-27.
 */
public class Test {

//    public static String convertStreamToString(InputStream is) {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        StringBuilder sb = new StringBuilder();
//        String line = null;
//        try {
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "/n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return sb.toString();
//
//    }

//    public static void test() {
//        String url = "http://222.46.20.174:12480/X509UserAction_signClientRequest.action";
//        HttpClient client = new HttpClient();
//        client.getHttpConnectionManager().getParams().setConnectionTimeout(2 * 1000 * 60);
//        client.getHttpConnectionManager().getParams().setSoTimeout(2 * 1000 * 60);
//        PostMethod post = new PostMethod(url);
//        post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 2 * 1000 * 60);
//        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//        File file = new File("D://Test.csr");
//        try {
//            String fileupload = FileUtil.readAsString(file);
//            post.addParameter("requestFile", fileupload);
//            int statusCode = 0;
//            try {
//                statusCode = client.executeMethod(post);
//                if (statusCode == 200) {
//                    InputStream data = post.getResponseBodyAsStream();
//                    System.out.println(convertStreamToString(data));
//                    String ss = post.getResponseBodyAsString();
//                    System.out.println(ss);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }





    public static void main(String args[]) throws Exception {
       /* Map<String, String> mapParam = new HashMap<String, String>();
        File file = new File("D://Test.csr");
        String fileupload = FileUtil.readAsString(file);
        mapParam.put("requestFile", fileupload);
        String pathUrl = "http://222.46.20.174:12480/X509UserAction_signClientRequest.action";
        String result = sendPost(pathUrl, mapParam);
        System.out.println(result);*/

        //发送 POST 请求
//        File file = new File("D://Test.csr");
//        String fileupload = FileUtil.readAsString(file);
////        String sr= sendPost("http://222.46.20.174:12480/X509UserAction_signClientRequest.action", "requestFile=\""+fileupload+"\"");
////        System.out.println(sr);
//
//
//        String urlPath = new String("http://222.46.20.174:12480/X509UserAction_signClientRequest.action");
//        String upload = new String(new BASE64Encoder().encode(fileupload.getBytes()));
//        String param = "requestFile="+ upload ;     //建立连接
//        URL url = new URL(urlPath);
//        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();     //设置参数
//        httpConn.setDoOutput(true);   //需要输出
//        httpConn.setDoInput(true);   //需要输入
//        httpConn.setUseCaches(false);  //不允许缓存
//        httpConn.setRequestMethod("POST");   //设置POST方式连接     //设置请求属性
//        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//        httpConn.connect();     //建立输入流，向指向的URL传入参数
//        DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
//        dos.writeBytes(param);
//        dos.flush();
//        dos.close();     //获得响应状态
//        int resultCode = httpConn.getResponseCode();
//        if (HttpURLConnection.HTTP_OK == resultCode) {
//            StringBuffer sb = new StringBuffer();
//            String readLine = new String();
//            BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
//            while ((readLine = responseReader.readLine()) != null) {
//                sb.append(readLine).append("\n");
//            }
//            responseReader.close();
//            System.out.println(sb.toString());
//        }



        CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
        FileInputStream bais=new FileInputStream("E:\\fartec\\ichange\\ca\\certificate\\CA.cer");
        X509Certificate Cert = (X509Certificate)certificatefactory.generateCertificate(bais);


//        X509EncodedKeySpec   ksp   =   new X509EncodedKeySpec(Cert.getEncoded());
//DEROutputStream outputStream = new DEROutputStream(new FileOutputStream(new File("E:\\fartec\\ichange\\ca\\certificate\\CA.der")));
//        outputStream.writeObject();





//        ASN1InputStream asn1 = new ASN1InputStream(Cert.getEncoded());
//        DEROutputStream dos = new DEROutputStream(new FileOutputStream(new File("E:\\fartec\\ichange\\ca\\certificate\\CA.der")) );
//        dos.writeObject(asn1.readObject());

//        dos.flush();
//        dos.close();
//        asn1.close();



        FileUtil.copyBytes(Cert.getEncoded(),"E:\\fartec\\ichange\\ca\\certificate\\CA.der");
    }
}
