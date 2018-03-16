package com.gdky.graduation.thirdApi;

import com.gdky.graduation.Qinniu.Qiniu;
import com.gdky.graduation.domain.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/14 0014.
 */
@RestController
public class QiniuApi {

    @PostMapping("/qiniu/img")
    public Result upImg(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        byte[]  imgBytes = multipartFile.getBytes();
        //MultipartFile 转 字节数组
        String imgUrl = Qiniu.upLoadImage(imgBytes);
        Map map = new HashMap<>();
        map.put("imgUrl",imgUrl);
        return Result.genSuccessResult(map);
    }
}
