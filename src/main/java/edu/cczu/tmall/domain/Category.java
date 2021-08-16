package edu.cczu.tmall.domain;

/**
 * @author iceorangeduxiaocheng@aliyun.com
 * @date 2021/8/15 15:40
 */
public class Category {

    private String id;                                       // 主键
    private String name;                                     // 分类名称

    public Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    // 进行了优化，去除了前后空白
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
