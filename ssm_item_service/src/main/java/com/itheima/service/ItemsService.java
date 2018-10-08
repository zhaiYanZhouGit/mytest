package com.itheima.service;






import com.itheima.domain.Items;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemsService {

    List<Items> findAllItems();

    void updateItems(Items items,MultipartFile uploadFile);

    Items findItemById(Integer id);
}
