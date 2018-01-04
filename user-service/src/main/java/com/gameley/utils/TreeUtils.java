package com.gameley.utils;

import com.gameley.entity.Element;
import com.gameley.vo.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {
    public static List<Tree> findchildren( List<Element> elements){
        List<Tree> childTree=new ArrayList<>();
        for(Element element:elements){
            Tree tree=new Tree();
            tree.setId(element.getCode());
            tree.setLabel(element.getDescription());
            childTree.add(tree);
        }

        return childTree;
    }
}
