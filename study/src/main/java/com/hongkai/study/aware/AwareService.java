package com.hongkai.study.aware;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class AwareService implements BeanNameAware, ResourceLoaderAware {
    private String beanName;
    private ResourceLoader loader;
    @Override
    public void setBeanName(String s) {
        this.beanName=s;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.loader=resourceLoader;
    }

    public void outputResult(){
        System.out.println("Bean的名称是："+beanName);
        Resource resource=loader.getResource("text.txt");
        try{
            System.out.println("加载内容为："+ IOUtils.toString(resource.getInputStream()));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
