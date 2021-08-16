package edu.cczu.tmall.admin.util;

/**
 * @author iceorangeduxiaocheng@aliyun.com
 * @date 2021/8/15 20:47
 */
import java.util.UUID;

/**
 * 该项目进行改进：主键字段采用UUID
 * 该类完成了自动生成UUID并且去掉了Java内置的生成的UUID中的"-"字符
 */
public class UUIDUtil {

    public static String getUUID(){

        return UUID.randomUUID().toString().replaceAll("-","");

    }

}
