package edu.cczu.tmall.service;

import edu.cczu.tmall.domain.Category;
import edu.cczu.tmall.util.Page;

import java.util.List;

/**
 * @author iceorangeduxiaocheng@aliyun.com
 * @date 2021/8/15 15:45
 */
public interface CategoryService {
    /**
     * @description 获取列表
     * @return 类别的列表
     */
    List<Category> list(Page page);

    /**
     * @description 获取category表的总记录条数
     * @return category表的总记录条数
     */
    int total();

    /**
     * @description 新增分类
     * @param category 分类对象(包含了新增的分类的信息)
     * @return 影响的记录条数(后期可能使用)
     */
    int add(Category category);

    void delete(String id);

    Category queryCategoryById(String id);

    void update(Category category);
}
