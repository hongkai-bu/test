package com.hongkai.model;

import java.sql.ResultSet;

public class ShowTableModel extends MyTableModel{
    public ShowTableModel(ResultSet rs) throws Exception{
        /**
         * 记录rs   数据库字段名    JTable列明
         */
        super(rs,
                new String[]{"id", "sampleId", "sampleLocation", "chnName", "latiName", "proLocation", "sampleType", "category",
                        "protectLevel", "origNumber", "sampleSource", "sampleStatus", "sampleCount", "pictureNumber", "sampleGone",
                        "enterdate","exploreCompany","exploreDate","exploreReason","sampleDesc","remark","dbfPaths","picturePaths"},
                new String[]{"序号", "标本编号", "标本位置", "中文名称", "拉丁文名称", "产出地点", "标本类别", "种属",
                        "保护级别", "原有编号", "标本来源", "标本状态", "标本数量", "照片编号", "标本去向",
                        "收藏登记日期","发掘单位","发掘日期","发掘原因","标本描述","备注","DBF路径","图片路径"});
    }

}
