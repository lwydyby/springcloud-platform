package com.gameley.generator;



import com.gameley.generator.entity.Menu;
import com.gameley.generator.service.ElementService;
import com.gameley.generator.service.MenuService;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Component
public class MenuGenerator {




    public  void generator(MenuService menuService,ElementService elementService) {
       try {
           InputStream inputStream = this.getClass().getResourceAsStream("/menu.xml");//也可以根据类的编译文件相对路径去找xml
           //创建SAXReader读取器，专门用于读取xml
           SAXReader saxReader = new SAXReader();
           //根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
           Document document = saxReader.read(inputStream);
           //获取根节点对象
           Element rootElement = document.getRootElement();
           //获取menu子节点信息
           List<Element> menulist = rootElement.elements("menu");
           for (Element item : menulist) {
               Menu menu = new Menu();
               menu.setCode(Optional.ofNullable(item.element("code").getTextTrim()).orElse(""));
               menu.setTitle(Optional.ofNullable(item.element("title").getTextTrim()).orElse(""));

               menu.setMenuId(Integer.valueOf(Optional.ofNullable(item.element("menuId").getTextTrim()).orElse("0")));
               menu.setParentId(Integer.valueOf(Optional.ofNullable(item.element("parentId").getTextTrim()).orElse("0")));
               menu.setIcon(Optional.ofNullable(item.element("icon").getTextTrim()).orElse(""));
               menu.setType(Optional.ofNullable(item.element("type").getTextTrim()).orElse(""));
               menu.setPath(Optional.ofNullable(item.element("path").getTextTrim()).orElse(""));
               menu.setDescription(Optional.ofNullable(item.element("description").getTextTrim()).orElse(""));
               menuService.insert(menu);
               Element elements = item.element("elements");
               if (elements != null) {
                   List<Element> elementList = elements.elements("element");
                   for (Element e : elementList) {
                       com.gameley.generator.entity.Element element = new com.gameley.generator.entity.Element();
                       element.setCode(Optional.ofNullable(e.element("code").getTextTrim()).orElse(""));
                       element.setType(Optional.ofNullable(e.element("type").getTextTrim()).orElse(""));
                       element.setName(Optional.ofNullable(e.element("name").getTextTrim()).orElse(""));
                       element.setUri(Optional.ofNullable(e.element("uri").getTextTrim()).orElse(""));
                       element.setMenuId(Optional.ofNullable(e.element("menuid").getTextTrim()).orElse(""));
                       element.setMethod(Optional.ofNullable(e.element("method").getTextTrim()).orElse(""));
                       elementService.insert(element);
                   }
               }

           }


       }catch (Exception e){
           e.printStackTrace();
       }

    }



}
