package edu.cczu.tmall.admin.category.mapper;

import edu.cczu.tmall.admin.category.domain.Category;
import edu.cczu.tmall.admin.util.Page;

import java.util.List;

/**
 * @author iceorangeduxiaocheng@aliyun.com
 * @date 2021/8/15 15:43
 */
public interface CategoryMapper {
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

    /**
     * @desctiprion 删除分类
     * @param id 删除分类的依据(主键字段)
     */
    void delete(String id);

    /**
     * @description 根据主键字段查询Category对象信息
     * @param id 编辑分类的依据(主键字段)
     * @return 所查询的Category对象
     */
    Category selectCategoryByPrimary(String id);

    void update(Category category);

    List<Category> pageHelperList();

}
