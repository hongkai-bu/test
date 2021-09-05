package com.hongkai.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExcelName {
    id("序号","id"),
    sampleId("标本编号","sampleId"),
    sampleLocation("标本位置","sampleLocation"),
    chinaName("中文名称","chnName"),
    latiName("拉丁文名称","latiName"),
    proLocation("产出地点","proLocation"),
    sampleType("标本类别","sampleType"),
    category("种属","category"),
    protectLevel("保护级别","protectLevel"),
    origNumber("原有编号","origNumber"),
    sampleSource("标本来源","sampleSource"),
    sampleStatus("标本状态","sampleStatus"),
    sampleCount("标本数量","sampleCount"),
    pictureNumber("照片编号","pictureNumber"),
    sampleGone("标本去向","sampleGone"),
    enterDate("收藏登记日期","enterDate"),
    exploreCompany("发掘单位","exploreCompany"),
    exploreDate("发掘日期","exploreDate"),
    exploreReason("发掘原因","exploreReason"),
    sampleDesc("标本描述","sampleDesc"),
    remark("备注","remark"),
    pictureCount("照片数量","pictureCount"),
    picturePaths("照片路径","picturePaths");

    String chnName;
    String engName;

    public static String getChnName(String engName){
        for(ExcelName excelName:ExcelName.values()){
            if(engName.equals(excelName.getEngName())){
                return excelName.getChnName();
            }
        }
        return null;
    }

    public static String getEngName(String chnName){
        for(ExcelName excelName:ExcelName.values()){
            if(chnName.equals(excelName.getChnName())){
                return excelName.getEngName();
            }
        }
        return null;
    }

}
