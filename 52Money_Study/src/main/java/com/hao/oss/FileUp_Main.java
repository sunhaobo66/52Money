package com.hao.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;


import java.io.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;


;
import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;

public class FileUp_Main {

    private static String endpoint = "oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAInqLbgzi7W4OK";
    private static String accessKeySecret = "JCw92zMJzfA2RkomVD06xqLxniXja4";

    private static String bucketName = "hao1123";

    public static void main(String[] args)throws Exception {
        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        System.out.println(ossClient);
        System.out.println(ossClient.getBucketInfo(bucketName).getBucket().getName());
        // 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。

        //关闭OSSClien

        File file = new File("52Money_Study/2018-7-28_888.jpg");
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len =0;
        while ((len=fis.read(data))!= -1){
            baos.write(data,0,len);
        }

        PutObjectResult result=ossClient.putObject(bucketName, file.getName(), new ByteArrayInputStream(baos.toByteArray()));
        ossClient.shutdown();
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,3);
        System.out.println("URL:"+ossClient.generatePresignedUrl(bucketName,file.getName(),calendar.getTime()));
    }
}
