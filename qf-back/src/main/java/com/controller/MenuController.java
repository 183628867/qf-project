package com.controller;

import com.enums.ResponseEntity;
import com.pojo.Menu;
import com.pojo.request.AddMenuRequest;
import com.pojo.request.MenuRequest;
import com.pojo.request.UpdateMenuRequest;
import com.pojo.response.MenuResponse;
import com.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auth chengst
 * @Date Created in  13:53 2020/5/15
 */
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Resource
    private MenuService menuService;


    /**
     * 描述: 查询此角色所能访问的菜单
     *
     * @Author: chengst
     * @Date: 14:00 2020/5/15
     */
    @PostMapping("/findAllMenu")
    public MenuResponse findAllMenu(@RequestBody MenuRequest menuRequest) {
        MenuResponse menuResponse = new MenuResponse();
        List<Menu> rootList = menuService.findMenu(menuRequest.getId());
        menuResponse.setMenuList(rootList);
        menuResponse.setResponseEntity(ResponseEntity.CM_OK);
        return menuResponse;
    }

    /**
     * 描述: 查询此角色所能访问的菜单(不处理)
     *
     * @Author: chengst
     * @Date: 14:00 2020/5/15
     */
    @PostMapping("/findMenus")
    public MenuResponse findAllMenus(@RequestBody MenuRequest menuRequest) {
        MenuResponse menuResponse = new MenuResponse();
        List<Menu> list = menuService.findMenus(menuRequest.getId());
        menuResponse.setMenuList(list);
        menuResponse.setResponseEntity(ResponseEntity.CM_OK);
        return menuResponse;
    }


    /**
     * 描述: 查询所有的菜单
     *
     * @Author: chengst
     * @Date: 17:58 2020/7/8
     */
    @GetMapping("/selectMenus")
    public MenuResponse selectMenus(MenuRequest menuRequest) {
        MenuResponse menuResponse = new MenuResponse();
        List<Menu> rootList = menuService.selectMenus(menuRequest);
        menuResponse.setMenuList(rootList);
        menuResponse.setResponseEntity(ResponseEntity.CM_OK);
        return menuResponse;
    }


    /**
     * 描述: 添加菜单
     *
     * @Author: chengst
     * @Date: 10:33 2021-4-16
     */
    @PostMapping("addMenu")
    public MenuResponse addMenu(@Valid AddMenuRequest addMenuRequest, BindingResult bindingResult) {
        MenuResponse menuResponse = new MenuResponse();
        if (bindingResult.hasErrors()) {
            menuResponse.setMsg(bindingResult.getFieldError().getDefaultMessage());
            return menuResponse;
        }
        Integer parentId = addMenuRequest.getParentId();
        if (parentId == null) {
            addMenuRequest.setParentId(0);
        }
        boolean flag = menuService.addMenu(addMenuRequest);
        if (flag) {
            menuResponse.setResponseEntity(ResponseEntity.CM_OK);
        }
        return menuResponse;
    }



    @PostMapping("updateMenu")
    public MenuResponse updateMenu(@Valid UpdateMenuRequest updateMenuRequest, BindingResult bindingResult) {
        MenuResponse menuResponse = new MenuResponse();
        if (bindingResult.hasErrors()) {
            menuResponse.setMsg(bindingResult.getFieldError().getDefaultMessage());
            return menuResponse;
        }
        boolean b = menuService.updateMenu(updateMenuRequest);
        if(b){
            menuResponse.setResponseEntity(ResponseEntity.CM_OK);
        }
        return menuResponse;
    }



    @RequestMapping("deleteMenu")
    public MenuResponse deleteMenu(@Valid UpdateMenuRequest updateMenuRequest, BindingResult bindingResult) {
        MenuResponse menuResponse = new MenuResponse();
        if (bindingResult.hasErrors()) {
            menuResponse.setMsg(bindingResult.getFieldError().getDefaultMessage());
            return menuResponse;
        }
        Integer integer = menuService.selectMenuCount(updateMenuRequest);
        if(integer > 0){
            menuResponse.setResponseEntity(ResponseEntity.DELETE_MENU_FALSE);
            return menuResponse;
        }
        boolean b = menuService.deleteMenu(updateMenuRequest);
        if(b){
            menuResponse.setResponseEntity(ResponseEntity.CM_OK);
        }
        return menuResponse;
    }
}
