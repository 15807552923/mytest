package test;

import java.io.BufferedOutputStream;  
import java.io.FileInputStream;
import java.io.FileOutputStream;  
  








import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;  
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;  

import sun.misc.BASE64Encoder;
  
public class ImgBase64 {  
  
    private static final Log log = LogFactory.getLog(ImgBase64.class);  
      
    /** 
     * 抓取图片存放目录 
     */  
    public static final String PIC_DIR = "D:\\picut";  
      
    /** 
     * 链接超时 
     */  
    private static final int TIME_OUT = 5000;  
      
    static void go3(String url) throws Exception {  
        Connection conn= Jsoup.connect(url);  
        Document doc = conn.get();  
        Elements links = doc.select("div.piclist img[src]");  
        for(int i=0;i<links.size();i++){  
            Element element = links.get(i);  
            final String imgUrl = element.attr("src");  
            log.info(imgUrl);  
            Thread.sleep(500);  
            new Thread(new Runnable() {  
                public void run() {  
                    try {  
                        save(imgUrl);  
                    } catch (Exception e) {  
                        // TODO Auto-generated catch block  
                        e.printStackTrace();  
                    }  
                }  
            }).start();  
        }  
    }  
      
    static void go2(String url) throws Exception {  
        Connection conn= Jsoup.connect(url);  
        Document doc = conn.get();  
        Elements links = doc.select("div.cc a[href]");  
        for(int i=0;i<links.size();i++){  
            Element element = links.get(i);  
            final String dirUrl = "http://www.3lian.com"+element.attr("href");  
            log.info(dirUrl);  
            Thread.sleep(500);  
            new Thread(new Runnable() {  
                public void run() {  
                    try {  
                        Connection conn= Jsoup.connect(dirUrl);  
                        Document doc = conn.get();  
                        Elements images = doc.select("div.mb_jjnr img[src]");  
                        for(int j=0;j<images.size();j++){  
                            Element img = images.get(j);  
                            String imgUrl = img.attr("src");  
                            log.info(imgUrl);  
                            save(imgUrl);  
                        }  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            }).start();  
        }  
    }  
      
    /** 
     * 处理帖子URL 
     * @param url 
     * @throws Exception 
     */  
    static void go(String url) throws Exception {  
        // JSOP创建链接  
        Connection conn = Jsoup.connect(url);  
        // 请求返回整个文档对象  
        Document doc = conn.post();  
        // 选择所有class=zoom 的img标签对象  
        Elements imgs = doc.select("img[class=zoom]");  
        // 循环每个img标签  
        for (int i = 0; i < imgs.size(); i++) {  
            Element img = imgs.get(i);  
            // 取得图片的下载地址  
            String picURL = doc.baseUri() + img.attr("file");  
            log.info(picURL);  
            // 保存图片  
            save(picURL);  
        }  
    }  
      
    //<img src="static/image/common/none.gif" file="data/attachment/forum/201105/08/174412nz3jq4z90s33s2t0.jpg" width="770" class="zoom" onclick="zoom(this, this.src)" id="aimg_180565" onmouseover="showMenu({'ctrlid':this.id,'pos':'12'})" alt="img_src_29620.jpg" title="img_src_29620.jpg" />  
    //doc.select("img[class=zoom]")  
    /** 
     * 保存图片 
     * @param url 
     * @param i 
     * @throws Exception 
     */  
    static void save(String url) throws Exception {  
        String fileName = url.substring(url.lastIndexOf("/"));  
        String filePath = PIC_DIR + "/" + fileName;  
        BufferedOutputStream out = null;  
        byte[] bit = getByte(url);  
        if (bit.length > 0) {  
            try {  
                out = new BufferedOutputStream(new FileOutputStream(filePath));  
                out.write(bit);  
                out.flush();  
                log.info("Create File success! [" + filePath + "]");  
            } finally {  
                if (out != null)  
                    out.close();  
            }  
        }  
    }  
      
    /** 
     * 获取图片字节流 
     * @param uri 
     * @return 
     * @throws Exception 
     */  
    static byte[] getByte(String uri) throws Exception {  
        HttpClient client = new DefaultHttpClient();  
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);  
        HttpGet get = new HttpGet(uri);  
        get.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);  
        try {  
            HttpResponse resonse = client.execute(get);  
            if (resonse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                HttpEntity entity = resonse.getEntity();  
                if (entity != null) {  
                    return EntityUtils.toByteArray(entity);  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            client.getConnectionManager().shutdown();  
        }  
        return new byte[0];  
    }  
  
    
    public static String imageToBase64(String path) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	    byte[] data = null;
	    // 读取图片字节数组
	    try {
	        InputStream in = new FileInputStream(path);
	        data = new byte[in.available()];
	        in.read(data);
	        in.close();
	    } catch (IOException e) {
	        System.out.println("e=====" +e);
	        return "";
	    }
	    // 对字节数组Base64编码
	    BASE64Encoder encoder = new BASE64Encoder();
	    return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}
    
    public static void main(String[] args) throws Exception {  
        // 开始抓取图片  
    	String url = "http://www.taoke.com/attachments/user/middle/554254/554254.jpg";
        save(url); 
        String fileName = url.substring(url.lastIndexOf("/"));  
        String filePath = PIC_DIR + "/" + fileName;  
        String baseImg = imageToBase64(filePath);
        StringBuffer sb = new StringBuffer();
        sb.append("data:image/png;base64,");
        sb.append(baseImg);
        System.out.println("baseImg="+sb.toString());
        //go3("http://www.ivsky.com/tupian/nvxing_gouwu_qingjing_v6969/");  
    }  
} 
