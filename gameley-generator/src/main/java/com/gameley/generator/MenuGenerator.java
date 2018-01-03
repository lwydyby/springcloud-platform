package com.gameley.generator;


import com.gameley.generator.entity.Menu;
import com.gameley.generator.service.ElementService;
import com.gameley.generator.service.MenuService;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class MenuGenerator {


    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("template/main.txt.vm");
        templates.add("template/router.txt.vm");
        return templates;
    }

    public  byte[] generator(MenuService menuService,ElementService elementService) {
       try {

           Properties prop = new Properties();
           prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
           Velocity.init(prop);
           // 获取模板文件
           List<String> templates = getTemplates();
           // 设置变量
           VelocityContext ctx = new VelocityContext();
           ArrayList<String> codes=new ArrayList<>();
           ArrayList<String> paths=new ArrayList<>();
           StringBuffer sb=new StringBuffer();


           InputStream inputStream = this.getClass().getResourceAsStream("/menu.xml");//也可以根据类的编译文件相对路径去找xml
           //创建SAXReader读取器，专门用于读取xml
           SAXReader saxReader = new SAXReader();
           //根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
           Document document = saxReader.read(inputStream);
           //获取根节点对象
           Element rootElement = document.getRootElement();
           //获取menu子节点信息
           List<Element> menulist = rootElement.elements("menu");
           List<Menu> menus=new ArrayList<>();
           List<com.gameley.generator.entity.Element> elementss=new ArrayList<>();
           for (Element item : menulist) {
               Menu menu = new Menu();

               String code=Optional.ofNullable(item.element("code").getTextTrim()).orElse("");
               String href=Optional.ofNullable(item.element("href").getTextTrim()).orElse("");
               String path=Optional.ofNullable(item.element("path").getTextTrim()).orElse("");
               Integer parentid=Integer.valueOf(Optional.ofNullable(item.element("parentId").getTextTrim()).orElse("0"));
               if(!"".equals(path)){
                   sb.append("{\n" +
                           "      path:" +"'"+path+"'"+",\n" +
                           "      component: function (resolve) {\n" +
                           "        require(['"+path+".vue'], resolve)\n" +
                           "      }\n" +
                           "    },");
                   paths.add(sb.toString());
               }

               menu.setHref(href);
               menu.setCode(code);
               menu.setTitle(Optional.ofNullable(item.element("title").getTextTrim()).orElse(""));
               Integer menuid=Integer.valueOf(Optional.ofNullable(item.element("menuId").getTextTrim()).orElse("0"));
               menu.setMenuId(menuid);
               menu.setParentId(parentid);
               menu.setIcon(Optional.ofNullable(item.element("icon").getTextTrim()).orElse(""));
               menu.setType(Optional.ofNullable(item.element("type").getTextTrim()).orElse(""));
               menu.setPath(path);
               menu.setDescription(Optional.ofNullable(item.element("description").getTextTrim()).orElse(""));
               menus.add(menu);
               if(!"".equals(href)){
                   codes.add(code+":"+menuid+",");
               }
               Element elements = item.element("elements");
               if (elements != null) {
                   List<Element> elementList = elements.elements("element");
                   for (Element e : elementList) {
                       com.gameley.generator.entity.Element element = new com.gameley.generator.entity.Element();
                       String ecode=Optional.ofNullable(e.element("code").getTextTrim()).orElse("");
                       element.setCode(ecode);
                       element.setType(Optional.ofNullable(e.element("type").getTextTrim()).orElse(""));
                       String name=Optional.ofNullable(e.element("name").getTextTrim()).orElse("");
                       element.setName(name);
                       element.setUri(Optional.ofNullable(e.element("uri").getTextTrim()).orElse(""));
                       element.setMenuId(String.valueOf(menuid));
                       element.setMethod(Optional.ofNullable(e.element("method").getTextTrim()).orElse(""));
                       element.setDescription(Optional.ofNullable(item.element("description").getTextTrim()).orElse(""));
                       elementss.add(element);
                       codes.add(name+":"+ecode+",");
                   }
               }

           }
           menuService.deleteAll();
           elementService.deleteAll();
           menuService.insertBatch(menus);
           elementService.insertBatch(elementss);
           ctx.put("codes",codes);
           ctx.put("routers",paths);
           ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
           ZipOutputStream zip = new ZipOutputStream(outputStream);
           for(String template:templates){
               StringWriter sw=new StringWriter();
               Template tpl= Velocity.getTemplate(template,"UTF-8");
               tpl.merge(ctx,sw);
               zip.putNextEntry(new ZipEntry(getFileName(template)));
               IOUtils.write(sw.toString(), zip, "UTF-8");
               IOUtils.closeQuietly(sw);
               zip.closeEntry();
           }
          IOUtils.closeQuietly(zip);
           return outputStream.toByteArray();


       }catch (Exception e){
           e.printStackTrace();
       }
       return null;

    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template) {
        String frontPath = "txt" + File.separator;
        if (template.contains("main.txt.vm")) {
            return frontPath +  "main.txt";
        }
        if (template.contains("router.txt.vm")) {
            return frontPath +  "router.txt";
        }

        return null;
    }



}
