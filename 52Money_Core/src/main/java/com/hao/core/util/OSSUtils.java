package com.hao.core.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;

import java.io.ByteArrayInputStream;
import java.util.Calendar;
import java.util.List;

//基于阿里云的oss  对象存储
public class OSSUtils {
    private  String endpoint = "oss-cn-beijing.aliyuncs.com";
    private  String accessKeyId = "LTAInqLbgzi7W4OK";
    private  String accessKeySecret = "JCw92zMJzfA2RkomVD06xqLxniXja4";

    private  String bucketName = "hao1123";
        // endpoint:端点                     access 存储       bucket

    public OSSUtils(String endpoint, String accessKeyId, String accessKeySecret, String bucketName) {
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketName = bucketName;
    }

    //创建客户端  //client:客户
    private OSSClient creaeClient(){
        return new OSSClient(endpoint,accessKeyId,accessKeySecret);
    }
    //获取文件列表
    public List<OSSObjectSummary> fileList(){
        OSSClient ossClient = creaeClient();
        //设置最大个数
        final  int maxKeys = 200;
        //列举文件
        ObjectListing objectListing = ossClient.listObjects(new ListObjectsRequest(bucketName).withMaxKeys(maxKeys));
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        return  sums;
    }
    //上传文件
    public String fileUp(String filename,byte[] data){
        OSSClient ossClient = creaeClient();
        ossClient.putObject(bucketName,filename,new ByteArrayInputStream(data));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,1);
        return ossClient.generatePresignedUrl(bucketName,filename,calendar.getTime()).toString();
    }
    //删除文件
    public void delFile(String key){
        OSSClient ossClient = creaeClient();
        ossClient.deleteObject(bucketName,key);
        ossClient.shutdown();
    }
    //删除多个文件
    public void delFiles(List<String> keys){
        //创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint,accessKeySecret,accessKeyId);
        //删除文件
        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        // 关闭OSSClient
        ossClient.shutdown();
    }
    //验证是否存在
    public boolean isHave(String objName){
        OSSClient ossClient = creaeClient();
        return ossClient.doesObjectExist(bucketName,objName);
    }

    //创建文件夹
    public void createDir(String dirName){
        if(!isHave(dirName)){
            OSSClient ossClient = creaeClient();
            ossClient.putObject(bucketName,dirName,new ByteArrayInputStream(new byte[0]));
            ossClient.shutdown();
        }
    }
}
