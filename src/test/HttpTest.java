package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpTest {
    
     public static String sendGet(String url, String param) {
            String result = "";
            BufferedReader in = null;
            try {
                String urlNameString = url + "?" + param;
                URL realUrl = new URL(urlNameString);
                // 打开和URL之间的连接
                URLConnection connection = realUrl.openConnection();
                // 设置通用的请求属性
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("user-agent",
                        "Mozilla/5.0 (Linux; U; Android 4.1.2; zh-cn; P9981 Build/JZO54K) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025489 Mobile Safari/533.1 MicroMessenger/6.3.9.48_refecd3e.700 NetType/WIFI Language/zh_CN");
                // 建立实际的连接
                connection.connect();
                // 获取所有响应头字段
                Map<String, List<String>> map = connection.getHeaderFields();
                // 遍历所有的响应头字段
                for (String key : map.keySet()) {
                    System.out.println(key + "--->" + map.get(key));
                }
                // 定义 BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                System.out.println("发送GET请求出现异常！" + e);
                e.printStackTrace();
            }
            // 使用finally块来关闭输入流
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return result;
        }

        /**
         * 向指定 URL 发送POST方法的请求
         *
         * @param url
         *            发送请求的 URL
         * @param param
         *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
         * @return 所代表远程资源的响应结果
         */
        public static String sendPost(String url, String param) {
            PrintWriter out = null;
            BufferedReader in = null;
            String result = "";
            try {
                URL realUrl = new URL(url);
                // 打开和URL之间的连接
                URLConnection conn = realUrl.openConnection();
                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
              conn.setRequestProperty("user-agent", "Mozilla/5.0 (Linux; U; Android 4.1.2; zh-cn; P9981 Build/JZO54K) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025489 Mobile Safari/533.1 MicroMessenger/6.3.9.48_refecd3e.700 NetType/WIFI Language/zh_CN");
               
            /*  String myCookie = "GUID=1925887539; lastTime=1456157940799; firstTime=1456150001059; CNZZDATA1257543850=631407171-1456144765-%7C1456159735; _5t_trace_sid=04aacb2935e7cccf832b0ee32c64c28c; _5t_trace_tms=1";
               
               conn.setRequestProperty("Cookie", myCookie);*/
                
                conn.setRequestProperty("Referer", "http://www.htdsj.net/4/ny.aspx");
                conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                System.out.println("发送 POST 请求出现异常！"+e);
                e.printStackTrace();
            }
            //使用finally块来关闭输出流、输入流
            finally{
                try{
                    if(out!=null){
                        out.close();
                    }
                    if(in!=null){
                        in.close();
                    }
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            return result;
        }    
        
        public static void main(String[] args) {
            //发送 GET 请求
          /*  String s=HttpRequest.sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
            System.out.println(s);*/
            
            //发送 POST 请求
          
                String sr=HttpTest.sendPost("http://mp.weixin.qq.com/mp/appmsgreport", "action=vote&__biz=MzA4Nzc5OTcwMA%3D%3D&uin=NzkwODEyODE1&key=b28b03434249256b546218152f08b0cff351fcc87445443e89726b090c0ea16dac045ff258ee705948dbeb277fb7e6bf&pass_ticket=LFlg%252BFEKfzP9SbRap3DOl%252FC7jxC0cEXQSsMl4%252F738OVPM3FUXh8LQU1SYxKOTKsi&f=json&json=%7B%22super_vote_item%22%3A%5B%7B%22vote_id%22%3A4552876%2C%22item_idx_list%22%3A%7B%22item_idx%22%3A%5B%2223%22%5D%7D%7D%2C%7B%22vote_id%22%3A4552877%2C%22item_idx_list%22%3A%7B%22item_idx%22%3A%5B%224%22%5D%7D%7D%5D%2C%22super_vote_id%22%3A3289033%7D&idx=1&mid=418050778&cpid68=1064");
                System.out.println(sr);
            
        }


}
