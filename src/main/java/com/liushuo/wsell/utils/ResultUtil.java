package com.liushuo.wsell.utils;

import com.liushuo.wsell.vo.ResultVo;

/**
 * Create by liushuo on 2017/11/29.
 */
public class ResultUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
            resultVo.setCode(0);
            resultVo.setMsg("成功");
            resultVo.setData(object);
            return  resultVo;
    }

    public static ResultVo failed(){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("失败");
        return  resultVo;
    }
}
