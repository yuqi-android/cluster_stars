package com.hd.ibrarybase.utils;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 作者 YuQi
 * 注意代码尽量不要有警告
 * 2023/4/7
 **/
public class HdViewUtil {
    /**
     * 获取指定类型的子View
     * @param group
     * @param cls
     * @param <T>
     * @return
     */
    public  static  <T> T findTypeView (@Nullable ViewGroup group,Class<T> cls){
        if (group==null){
            return null;
        }
        //双端队列
        Deque<View> deque =new ArrayDeque<>();
        deque.add(group);
        while (!deque.isEmpty()){
            View node =deque.removeFirst();
            if (cls.isInstance(node)){
                return cls.cast(node);
            }else if (node instanceof  ViewGroup){
                ViewGroup container = (ViewGroup) node;
                for ( int i=0,count =container.getChildCount();i<count ;i++){
                    deque.add(container.getChildAt(i));
                }
            }
        }
        return  null;
    }
}
