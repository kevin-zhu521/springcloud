package com.dahantc.utils;

import com.dahantc.vo.ResultVO;

/**
 * Created by Administrator on 2018/11/14.
 */
public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
