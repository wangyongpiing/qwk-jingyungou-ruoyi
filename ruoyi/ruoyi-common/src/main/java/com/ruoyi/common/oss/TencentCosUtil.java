package com.ruoyi.common.oss;
/**
 * @Description:
 * @author cjf
 * @date 2023-08-26
 */

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;

/**
 * @author cjf
 * @date 2023/8/26
 */

@Component
public class TencentCosUtil {

    // COS的SecretId//AKIDTpAdw4ydp1Ujix2sUs58yb1EB2e2EDyV //AKIDTo18CQdjeR1NYmaHl3xQ1Xt57zjT6Ypx
    private static String secretId = "AKIDTpAdw4ydp1Ujix2sUs58yb1EB2e2EDyV";
    
    // COS的SecretKey//ObaP0MTO4YkjV2KPGsHWaTAduxnV1Qfg //96uY2LRWRFsKrBKH3d1CD1c2IPJ2e7RZ
    private static String secretKey = "ObaP0MTO4YkjV2KPGsHWaTAduxnV1Qfg";
    //文件上传后访问路径的根路径，后面要最佳文件名字与类型
    private static String rootSrc = "https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/";
    //上传的存储桶的地域，可参考根路径https://qq-test-1303******.cos.地域.myqcloud.com,此参数在COS的后台能查询。
    private static String bucketAddr = "ap-shanghai";
    //存储桶的名字，是自己在存储空间自己创建的，我创建的名字是：qq-test-1303******
    private static String bucketName = "hbqwk-1318352516";

    /**
     * 1.调用静态方法getCosClient()就会获得COSClient实例
     * 2.本方法根据永久密钥初始化 COSClient的，官方是不推荐，官方推荐使用临时密钥，是可以限制密钥使用权限，创建cred时有些区别
     *
     * @return COSClient实例
     */
    private static COSClient getCosClient() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2.1 设置存储桶的地域（上文获得）
        Region region = new Region(bucketAddr);
        ClientConfig clientConfig = new ClientConfig(region);
        // 2.2 使用https协议传输
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 返回COS客户端
        return cosClient;
    }

    /**
     * 只要调用静态方法upLoadFile(MultipartFile multipartFile)就可以获取上传后文件的全路径
     *
     * @param file
     * @return 返回文件的浏览全路径
     */
    public static String upLoadFile(MultipartFile file,String filePath) {
        try {
            // 获取上传的文件的输入流
            InputStream inputStream = file.getInputStream();

            // 避免文件覆盖，获取文件的原始名称，如123.jpg,然后通过截取获得文件的后缀，也就是文件的类型
            String originalFilename = file.getOriginalFilename();
//            System.out.println("originalFilename"+originalFilename);
            //获取文件的类型
//            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileType = filePath.substring(filePath.lastIndexOf("."));
//            //使用UUID工具  创建唯一名称，放置文件重名被覆盖，在拼接上上命令获取的文件类型
//            String fileName = UUID.randomUUID().toString() + fileType;
//            // 指定文件上传到 COS 上的路径，即对象键。最终文件会传到存储桶名字中的images文件夹下的fileName名字
//            String key = "images/" + fileName;
            String key = filePath;
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            // - 使用输入流存储，需要设置请求长度
            objectMetadata.setContentLength(inputStream.available());
            // - 设置缓存
            objectMetadata.setCacheControl("no-cache");
            // - 设置Content-Type
            objectMetadata.setContentType(fileType);
            //上传文件
            PutObjectResult putResult = getCosClient().putObject(bucketName, key, inputStream, objectMetadata);
            // 创建文件的网络访问路径
            String url = rootSrc + key;
            //关闭 cosClient，并释放 HTTP 连接的后台管理线程
            getCosClient().shutdown();
            return url;

        } catch (Exception e) {
            e.printStackTrace();
            // 发生IO异常、COS连接异常等，返回空
            return null;
        }
    }


//    public String download(@RequestParam("fileName") String fileName) {
//        TransferManager transferManager = createTransferManager();
//
//        String bucket = bucketName;
//        String key = "img/" + fileName;
//        //下载到工程目录下的imgs文件夹中
//        File downloadFile = new File(System.getProperty("user.dir") + "/imgs/"+fileName);
//
//        GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);
//        try {
//            //返回一个异步结果 Donload, 可同步的调用 waitForCompletion 等待下载结束, 成功返回 void, 失败抛出异常
//            Download download = transferManager.download(getObjectRequest, downloadFile);
//            download.waitForCompletion();
//            return "下载文件成功";
//        } catch (InterruptedException e) {
//            return "下载文件失败";
//        } finally {
//            shutdownTransferManager(transferManager);
//        }
//    }
//
//
    public static String delete(String fileName){
//        COSClient cosClient = getCosClient();
//        String bucket = bucketName;
//        System.out.println("bucketName:"+bucketName);
//        System.out.println(fileName);
//        String key = "img/" + fileName;
        try {
            getCosClient().deleteObject(bucketName,fileName);
            return "删除文件成功";
        } catch (CosClientException e) {
            return "删除文件失败";
        } finally {
            getCosClient().shutdown();
        }

    }

    private void shutdownTransferManager(TransferManager transferManager) {
        // 指定参数为 true, 则同时会关闭 transferManager 内部的 COSClient 实例。
        // 指定参数为 false, 则不会关闭 transferManager 内部的 COSClient 实例。
        transferManager.shutdownNow(true);
    }

    private TransferManager createTransferManager() {
        //初始化客户端
        COSClient cosClient = getCosClient();
        //创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(32);
        //创建 TransferManager
        return new TransferManager(cosClient, threadPool);
    }

}
