package cn.xqplus.equipmentsys.service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 公共接口, 页面 Service 接口都应该继承这个接口
 */
public interface ICommonService {

    /**
     * Excel 导出
     * @param ids id 集合
     * @param response HttpServletResponse
     */
    void exportExcel(List<String> ids, HttpServletResponse response);
}
