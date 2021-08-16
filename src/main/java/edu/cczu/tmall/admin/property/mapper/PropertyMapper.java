package edu.cczu.tmall.admin.property.mapper;

import edu.cczu.tmall.admin.property.domain.Property;

public interface PropertyMapper {

    /**
     * 根据主键字段删除Property符合条件的记录
     * @param id 删除依据
     * @return 影响记录行
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入Property一条记录
     * @param record Property信息
     * @return 影响记录行
     */
    int insert(Property record);

    /**
     * 根据条件插入Property记录
     * @param record Property信息
     * @return 影响记录行
     */
    int insertSelective(Property record);

    /**
     * 根据主键字段查询Property信息
     * @param id 查询依据
     * @return 查询出来的符合条件的Property Object
     */
    Property selectByPrimaryKey(String id);

    /**
     * 根据条件修改Property记录信息
     * @param record Property信息(封装了修改的信息)
     * @return 影响记录行
     */
    int updateByPrimaryKeySelective(Property record);

    /**
     * 根据主键字段修改Property信息
     * @param record 条件--封装了Property被修改的信息
     * @return 影响记录行
     */
    int updateByPrimaryKey(Property record);
}