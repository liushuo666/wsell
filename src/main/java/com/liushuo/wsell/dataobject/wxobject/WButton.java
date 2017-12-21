package com.liushuo.wsell.dataobject.wxobject;

import lombok.Data;

import java.util.List;

/**
 * Create by liushuo on 2017/12/20.
 */
@Data
public class WButton {
    private  String type;
    private  String name;
    private  String key;
    private List<SubButton> sub_button;
}
