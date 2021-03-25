package cn.xqplus.equipmentsys.response;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户信息 Excel 导入导出实体类
 */

@Getter
@Setter
public class UserResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @Excel(name = "用户编号", orderNum = "0", width = 15)
    private String userNumber;

    /**
     * 用户名称
     */
    @Excel(name = "用户名称", orderNum = "1", width = 15)
    private String userName;

    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名", orderNum = "2", width = 15)
    private String trueName;

    /**
     * 角色名称
     */
    @Excel(name = "角色名称", orderNum = "3", width = 15)
    private String roleName;

    /**
     * 所在部门
     */
    @Excel(name = "所在部门", orderNum = "4", width = 20)
    private String deptName;

    /**
     * 创建时间（yyyy-MM-dd）
     */
    @Excel(name = "创建时间", orderNum = "5", width = 20)
    private String createDate;

    /**
     * 修改时间（yyyy-MM-dd）
     */
    @Excel(name = "修改时间", orderNum = "6", width = 20)
    private String updateDate;

}
