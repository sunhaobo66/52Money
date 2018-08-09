package com.hao.uicontroller.oss;

import com.hao.core.util.FileUtils;
import com.hao.core.util.OSSUtils;
import com.hao.core.vo.R;
import com.hao.domain.oss.OSSPo;
import com.hao.service.user.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;

@RestController
public class OssController {
    @Autowired
    private OSSUtils ossUtils;
    @Autowired
    private UserDetailService service;
    //文件上传
    @PostMapping("/fileup.do")
    public R fileup(@RequestParam("feri") CommonsMultipartFile file){

        System.err.println("进入了文件##########################");
        String fn=FileUtils.createFileName(file.getOriginalFilename());
        String url=ossUtils.fileUp(fn,file.getBytes());
        OSSPo po=new OSSPo();
        po.setObjname(fn);
        po.setOurl(url);
        po.setPeriod(-1L);
        service.save(po);
        return new R(0,url,null);
    }
}
