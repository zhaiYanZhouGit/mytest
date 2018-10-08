package com.itheima.dao;

import com.itheima.domain.Items;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemsDao {
    @Select("select * from items")
    List<Items> findAllItems();

    @Update("update items set name=#{name},price=#{price}" +
            ",pic=#{pic},detail=#{detail} where id=#{id}")
    void updateItems(Items items);

    @Select("select * from items where id=#{id}")
    Items findItemById(Integer id);

    @Delete("delete pic from items where id=#{id}")
    void deletePic(Items items);
}
