package com.itheima.service.impl;

import com.itheima.dao.ItemsDao;
import com.itheima.domain.Items;
import com.itheima.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsDao itemsDao;

    @Override
    public List<Items> findAllItems() {
        return itemsDao.findAllItems();
    }

    @Override
    public void updateItems(Items items, MultipartFile uploadFile) {
        //获取用户上传的原始名
        String oriFileName = uploadFile.getOriginalFilename();
        //获取原始文件名后缀
        String extName = oriFileName.substring(oriFileName.lastIndexOf("."));
        //生成uuid码
        String uuidName = UUID.randomUUID().toString().replace("-", "");
        //拼接存储的文件名
        String picNname = null;
        if (items.getPic() != null && items.getPic().trim() != "") {
            picNname = items.getPic() + "_" + uuidName + extName;
        } else {
            picNname = uuidName + extName;
        }

//        //获取服务器地址
        //////        String basePath = this.getClass().getClassLoader().getResource("/").getPath();
        //////        //每天建一个新的文件夹存放文件
        //////        String datePath=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //////        File file = new File(basePath + "/" + datePath);
               //文件存放地址
        String realPath = "B:\\IdeaProjects\\ssm_item_project\\ssm_item_web\\src\\main\\webapp\\pic";
        Items item = itemsDao.findItemById(items.getId());
        String pic = item.getPic();
        File file = new File(realPath + "\\" + pic);
        if (file.exists()) {
            file.delete();
        }
        try {
            uploadFile.transferTo(new File(realPath, picNname));
        } catch (IOException e) {
            e.printStackTrace();
        }
        items.setPic(picNname);
        itemsDao.updateItems(items);
    }

    @Override
    public Items findItemById(Integer id) {
        return itemsDao.findItemById(id);
    }
}
