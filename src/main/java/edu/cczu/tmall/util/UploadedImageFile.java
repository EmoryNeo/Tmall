package edu.cczu.tmall.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author iceorangeduxiaocheng@aliyun.com
 * @date 2021/8/15 20:55
 * @description 新增UploadedFile类，其中有一个MultipartFile类型的属性，用于接收
 * 上传文件的注入。
 * ps：这里的属性名称image闭合和页面中的'增加分类部分'中的type=file的name值保持一致
 * <input id="categoryPic" accept="image/*" type="file" name="image" />
 */
public class UploadedImageFile {

    MultipartFile image;

    public MultipartFile getImage(){
        return image;
    }

    public void setImage(MultipartFile image){
        this.image = image;
    }
}
