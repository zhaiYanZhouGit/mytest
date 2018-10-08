package com.itheima.controller;

import com.itheima.domain.Items;
import com.itheima.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/items")
//此代码由b进行修改
public class ItemsController {
    @Autowired
    private ItemsService itemsService;
    @RequestMapping("/findAllItems")
    public String findAllItems(Model model){
        List<Items> itemsList = itemsService.findAllItems();
        model.addAttribute("iList",itemsList);
        return "items/itemsList";
    };
    @RequestMapping("/editItems")
    public String findItemById(Integer id,Model model) {
        Items items=itemsService.findItemById(id);
        model.addAttribute("items",items);
        return "items/editItems";
    }
    @RequestMapping("/updateItems")
    public String updateItems(Items items, MultipartFile uploadFile) {
        itemsService.updateItems(items,uploadFile);
        return "redirect:/items/findAllItems";

    }
}
