package edu.cczu.tmall.service.impl;

import edu.cczu.tmall.mapper.CategoryMapper;
import edu.cczu.tmall.domain.Category;
import edu.cczu.tmall.service.CategoryService;
import edu.cczu.tmall.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author iceorangeduxiaocheng@aliyun.com
 * @date 2021/8/15 15:46
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    private Log logger = LogFactory.getLog(this.getClass());

    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> list(Page page) {
        List<Category> list = categoryMapper.list(page);
        logger.info("列表：" + list);
        return list;
    }

    @Override
    public int total() {
        int total = categoryMapper.total();
        logger.info("Category表总记录条数" + total + "条");
        return total;
    }

    @Override
    public int add(Category category) {
        int count = categoryMapper.add(category);
        logger.info("增加记录条数：" + count + "条");
        return count;
    }

    @Override
    public void delete(String id) {
        categoryMapper.delete(id);
    }

    @Override
    public Category queryCategoryById(String id) {
        Category category = categoryMapper.selectCategoryByPrimary(id);
        logger.info("根据主键字段查询出来的对象：" + category);
        return category;
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public List<Category> pageHelperList() {
        return categoryMapper.pageHelperList();
    }
}
